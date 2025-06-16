/**
 * Copyright (c) 2008-2012 EBM WebSourcing, 2012-2023 Linagora
 * 
 * This program/library is free software: you can redistribute it and/or modify
 * it under the terms of the New BSD License (3-clause license).
 *
 * This program/library is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the New BSD License (3-clause license)
 * for more details.
 *
 * You should have received a copy of the New BSD License (3-clause license)
 * along with this program/library; If not, see http://directory.fsf.org/wiki/License:BSD_3Clause/
 * for the New BSD License (3-clause license).
 */
 
package org.ow2.easywsdl.schema.api.abstractElmt;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.logging.Logger;

import org.ow2.easywsdl.schema.api.SchemaException;
import org.ow2.easywsdl.schema.api.SchemaReader.FeatureConstants;
import org.ow2.easywsdl.schema.api.absItf.AbsItfInclude;
import org.ow2.easywsdl.schema.api.absItf.AbsItfSchema;

/**
 * @author Nicolas Salatge - EBM WebSourcing
 */
public abstract class AbstractIncludeImpl<E, S extends AbsItfSchema> extends AbstractSchemaElementImpl<E> implements AbsItfInclude<S> {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = Logger.getLogger(AbstractIncludeImpl.class.getName());

	private final Map<FeatureConstants, Object> features;
	private final Map<URI, AbsItfSchema> imports;
	private final URI baseURI;
	private final AbstractSchemaReader reader;
	
	private S schema;

	
	
	private AbstractIncludeImpl(final E model, final Map<FeatureConstants, Object> features, final AbstractSchemaElementImpl parent, final Map<URI, AbsItfSchema> imports, final URI baseURI, final AbstractSchemaReader reader) throws SchemaException {
		super(model, parent);
		if (features != null) {
			this.features = features;
		} else if (parent != null) {
			this.features = ((AbstractSchemaImpl) this.parent).getFeatures();
		} else {
			this.features = null;
		}
		this.imports = imports;
		this.baseURI = baseURI;
		this.reader = reader;

		// force include retrieval at construction time.
//		getSchema();
	}


    /**
     * Default constructor
     *
     * @param model
     *            the model
     */
	public AbstractIncludeImpl(final E model, final Map<FeatureConstants, Object> features, final Map<URI, AbsItfSchema> imports, final URI baseURI, final AbstractSchemaReader reader) throws SchemaException {
		this(model, features, null, imports, baseURI, reader);
	}

	
	/**
	 * Default constructor
	 *
	 * @param model
	 *            the model
	 * @param parent
	 *            the parent description
	 */
	public AbstractIncludeImpl(final E model, final AbstractSchemaElementImpl parent, final Map<URI, AbsItfSchema> imports, final URI baseURI, final AbstractSchemaReader reader) throws SchemaException {
		this(model, null, parent, imports, baseURI, reader);
	}

	private void retrieveInclude(final URI schemaLocation, final Map<FeatureConstants, Object> features, final Map<URI, AbsItfSchema> imports, final URI baseURI, final AbstractSchemaReader reader) throws SchemaException {
		assert imports != null;
		if (schemaLocation == null) return; 
		
		try {
			// Try to identify a cyclic import loop
			if (!imports.containsKey(schemaLocation)) {
				S externalSchema = (S) reader.readExternalPart(schemaLocation, baseURI, imports);
				imports.put(schemaLocation, externalSchema);
				((AbstractSchemaImpl) externalSchema).initialize();
			}
			this.schema = (S) imports.get(schemaLocation);

		} catch (final SchemaException e) {
			throw new SchemaException("the imported document cannot be import at: " + schemaLocation.toString(), e);
		} catch (final URISyntaxException e) {
			throw new SchemaException("the imported document cannot be import at: " + schemaLocation.toString() + " because the URI is invalid", e);
		} catch (final MalformedURLException e) {
            throw new SchemaException("the imported document cannot be import at: " + schemaLocation.toString() + " because the URI is invalid", e);
        }
	}

	
	private boolean haveImportDocumentsFeature() {
		if (features == null) return false;
		Boolean importDocuments = (Boolean) features.get(FeatureConstants.IMPORT_DOCUMENTS);
		if (importDocuments == null) return false;
		return importDocuments.booleanValue();
	}
	
	
	/**
	 * @return the parent
	 */
	public S getSchema() {
		if (this.schema == null) {
			if (haveImportDocumentsFeature()) {
				try {
					
					this.retrieveInclude(getLocationURI(), features, imports, baseURI, reader);
				} catch (SchemaException se) {
					throw new RuntimeException(se);
				}
			}
	
			if (this.schema != null) {
				((AbstractSchemaImpl) this.schema).setFeatures(features);
			}
		}
		
		return this.schema;
	}

	public void setSchema(final S schema) {
		this.schema = schema;
		if(this.schema != null) {
			((AbstractSchemaImpl)this.getParentSchema()).addIncludeElementsInAllList(this);
		}
	}

	/**
	 * @return the parent parent
	 */
	public S getParentSchema() {
		return (S) this.parent.getSchema();
	}

}
