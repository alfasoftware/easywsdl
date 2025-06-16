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
 
package org.ow2.easywsdl.schema.impl;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import org.ow2.easywsdl.schema.api.Import;
import org.ow2.easywsdl.schema.api.Schema;
import org.ow2.easywsdl.schema.api.SchemaException;
import org.ow2.easywsdl.schema.api.SchemaReader.FeatureConstants;
import org.ow2.easywsdl.schema.api.absItf.AbsItfSchema;
import org.ow2.easywsdl.schema.api.abstractElmt.AbstractImportImpl;
import org.ow2.easywsdl.schema.api.abstractElmt.AbstractSchemaElementImpl;
import org.ow2.easywsdl.schema.api.abstractElmt.AbstractSchemaReader;

import com.ebmwebsourcing.easycommons.uri.URIHelper;

/**
 * @author Nicolas Salatge - EBM WebSourcing
 */
public class ImportImpl extends AbstractImportImpl<org.ow2.easywsdl.schema.org.w3._2001.xmlschema.Import, Schema> implements Import {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param impt
	 * @param features
	 * @param imports
	 * @param reader
	 * @throws SchemaException
     * @throws URISyntaxException
     *             If the schemaLocation attribute of the import is not a valid
     *             URI.
	 */
	public ImportImpl(final org.ow2.easywsdl.schema.org.w3._2001.xmlschema.Import impt, final Map<FeatureConstants, Object> features, final Map<URI, AbsItfSchema> imports, final URI baseURI, final AbstractSchemaReader reader) throws SchemaException, URISyntaxException {
		super(impt, features, imports, baseURI, reader);

		// get the documentation
		// this.documentation = new
		// org.ow2.easywsdl.schema.impl.DocumentationImpl
		// (this.model.getDocumentation());
	}

	/**
	 * @param impt
	 * @param parent
	 * @param imports
	 * @param reader
	 * @throws SchemaException
     * @throws URISyntaxException
     *             If the schemaLocation attribute of the import is not a valid
     *             URI.
	 */
	public ImportImpl(final org.ow2.easywsdl.schema.org.w3._2001.xmlschema.Import impt, final AbstractSchemaElementImpl parent, final Map<URI, AbsItfSchema> imports, final URI baseURI, final AbstractSchemaReader reader) throws SchemaException, URISyntaxException {
		super(impt, parent, imports, baseURI, reader);

		// get the documentation
		// this.documentation = new
		// org.ow2.easywsdl.schema.impl.DocumentationImpl
		// (this.model.getDocumentation());
	}

	public String getNamespaceURI() {
		return this.model.getNamespace();
	}

	public void setNamespaceURI(final String namespaceURI) {
		this.model.setNamespace(namespaceURI);
	}

	/**
     * {@inheritDoc}
     */
	public URI getLocationURI() {
	    if (this.model.getSchemaLocation() != null) {
			return URIHelper.filePathToUri(this.model.getSchemaLocation());
        } else {
            return null;
        }
	}
	


	public void setLocationURI(final URI locationURI) {
        this.model.setSchemaLocation(locationURI.toString());
	}

}
