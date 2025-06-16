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
import java.util.List;
import java.util.Map;

import org.ow2.easywsdl.schema.api.Attribute;
import org.ow2.easywsdl.schema.api.ComplexType;
import org.ow2.easywsdl.schema.api.Element;
import org.ow2.easywsdl.schema.api.Import;
import org.ow2.easywsdl.schema.api.Include;
import org.ow2.easywsdl.schema.api.Redefine;
import org.ow2.easywsdl.schema.api.Schema;
import org.ow2.easywsdl.schema.api.SchemaException;
import org.ow2.easywsdl.schema.api.SchemaReader.FeatureConstants;
import org.ow2.easywsdl.schema.api.SimpleType;
import org.ow2.easywsdl.schema.api.Type;
import org.ow2.easywsdl.schema.api.absItf.AbsItfSchema;
import org.ow2.easywsdl.schema.api.abstractElmt.AbstractSchemaImpl;
import org.ow2.easywsdl.schema.api.abstractElmt.AbstractTypeImpl;
import org.ow2.easywsdl.schema.api.extensions.NamespaceMapperImpl;
import org.ow2.easywsdl.schema.api.extensions.SchemaLocatorImpl;
import org.ow2.easywsdl.schema.org.w3._2001.xmlschema.Annotated;
import org.ow2.easywsdl.schema.org.w3._2001.xmlschema.FormChoice;
import org.ow2.easywsdl.schema.org.w3._2001.xmlschema.OpenAttrs;
import org.ow2.easywsdl.schema.org.w3._2001.xmlschema.TopLevelElement;

/**
 * @author Nicolas Salatge - EBM WebSourcing
 */
public class SchemaImpl extends AbstractSchemaImpl<org.ow2.easywsdl.schema.org.w3._2001.xmlschema.Schema, Type, Element, Attribute, Include, Import, Redefine> implements Schema {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private final SchemaReaderImpl reader;

    /**
     * @param documentURI
     * @param schema
     * @param namespaceMapper
     * @param schemaLocator
     * @param features
     * @param imports
     * @param reader
     * @throws SchemaException
     * @throws URISyntaxException
     *             If a schemaLocation attribute of an import, include or
     *             redefine is not a valid URI.
     */
	public SchemaImpl(final URI documentURI, final org.ow2.easywsdl.schema.org.w3._2001.xmlschema.Schema schema, final NamespaceMapperImpl namespaceMapper, final SchemaLocatorImpl schemaLocator, final Map<FeatureConstants, Object> features, final Map<URI, AbsItfSchema> imports, SchemaReaderImpl reader) throws SchemaException, URISyntaxException {
		super(documentURI, schema, namespaceMapper, schemaLocator);

		this.setFeatures(features);
		this.model = schema;

		// set the reader
		this.reader = reader;
		
		if(reader == null) {
			throw new SchemaException("schema reader cannot be null");
		}


		for (final OpenAttrs item : this.model.getIncludeOrImportOrRedefine()) {
			// get imports
			if (item instanceof org.ow2.easywsdl.schema.org.w3._2001.xmlschema.Import) {
				final Import impt = new org.ow2.easywsdl.schema.impl.ImportImpl((org.ow2.easywsdl.schema.org.w3._2001.xmlschema.Import) item, this, imports, this.getDocumentURI(), reader);
				this.getImports().add(impt);
			}

			// get includes
			if (item instanceof org.ow2.easywsdl.schema.org.w3._2001.xmlschema.Include) {
				final Include incl = new org.ow2.easywsdl.schema.impl.IncludeImpl((org.ow2.easywsdl.schema.org.w3._2001.xmlschema.Include) item, this, imports, this.getDocumentURI(), reader);
				this.getIncludes().add(incl);
			}
			
			// get redefines
			if (item instanceof org.ow2.easywsdl.schema.org.w3._2001.xmlschema.Redefine) {
				final Redefine red = new org.ow2.easywsdl.schema.impl.RedefineImpl((org.ow2.easywsdl.schema.org.w3._2001.xmlschema.Redefine) item, this, imports, this.getDocumentURI(), reader);
				this.getRedefines().add(red);
			}
		}
		
	}
	
	public SchemaImpl() throws SchemaException {
		super("./");
		this.reader = new SchemaReaderImpl();
		this.model = new org.ow2.easywsdl.schema.org.w3._2001.xmlschema.Schema();
		this.getAllNamespaces().addNamespace("xsd", Constants.SCHEMA_NAMESPACE);
	}

	
	public void initialize() {
		this.addImportElementsInAllList();
		this.addIncludeElementsInAllList();
		this.addRedefineElementsInAllList();
		
		for (final OpenAttrs item : this.model.getSimpleTypeOrComplexTypeOrGroup()) {

			// get simple type
			if (item instanceof org.ow2.easywsdl.schema.org.w3._2001.xmlschema.SimpleType) {
				final SimpleType st = new org.ow2.easywsdl.schema.impl.SimpleTypeImpl((org.ow2.easywsdl.schema.org.w3._2001.xmlschema.SimpleType) item, this);
				this.getTypes().add(st);
			}

			// get complex type
			if (item instanceof org.ow2.easywsdl.schema.org.w3._2001.xmlschema.ComplexType) {
				final ComplexTypeImpl ct = new org.ow2.easywsdl.schema.impl.ComplexTypeImpl((org.ow2.easywsdl.schema.org.w3._2001.xmlschema.ComplexType) item, this);
				this.getTypes().add(ct);
			}
			
			// get elements
            if (item instanceof org.ow2.easywsdl.schema.org.w3._2001.xmlschema.Element) {
                final ElementImpl e = new org.ow2.easywsdl.schema.impl.ElementImpl((org.ow2.easywsdl.schema.org.w3._2001.xmlschema.Element) item, this);
                this.getElements().add(e);
            }

            // get attributes
            if (item instanceof org.ow2.easywsdl.schema.org.w3._2001.xmlschema.Attribute) {
                final AttributeImpl a = new org.ow2.easywsdl.schema.impl.AttributeImpl((org.ow2.easywsdl.schema.org.w3._2001.xmlschema.Attribute) item, this);
                this.getAttributes().add(a);
            }
			
		}

		
		// set all reference
		for(Element elmt: this.getElements()) {
			((ElementImpl)elmt).findReferencedElementIfExist();
		}
		
		for(Type type: this.getTypes()) {
			if(type instanceof ComplexType) {
				((ComplexTypeImpl)type).findReferencedElementIfExist();
			}
		}
	}
	
	
	public String getTargetNamespace() {
		return this.model.getTargetNamespace();
	}

	public FormChoice getElementFormDefault() {
		return this.model.getElementFormDefault();
	}

	public FormChoice getAttributeFormDefault() {
		return this.model.getAttributeFormDefault();
	}

	public String getLang() {
		return this.model.getLang();
	}

	public String getVersion() {
		return this.model.getVersion();
	}

	public List<String> getBlockDefault() {
		return this.model.getBlockDefault();
	}

	public List<String> getFinalDefault() {
		return this.model.getFinalDefault();
	}

	@Override
	public void addAttribute(Attribute att) {
		super.addAttribute(att);
		this.model.getSimpleTypeOrComplexTypeOrGroup().add(((AttributeImpl) att).getModel());

	}

	@Override
	public void addImport(Import impt) {
		super.addImport(impt);
		this.model.getIncludeOrImportOrRedefine().add(((ImportImpl) impt).getModel());
	}

	@Override
	public void addInclude(Include incl) throws SchemaException {
		super.addInclude(incl);
		this.model.getIncludeOrImportOrRedefine().add(((IncludeImpl) incl).getModel());
	}

	@Override
	public void addRedefine(Redefine red) {
		super.addRedefine(red);
		this.model.getIncludeOrImportOrRedefine().add(((RedefineImpl) red).getModel());
	}
	
	@Override
	public void addElement(Element elmt) {
		super.addElement(elmt);
		this.model.getSimpleTypeOrComplexTypeOrGroup().add(((ElementImpl) elmt).getModel());
	}

	@Override
	public void addType(Type type) {
		super.addType(type);
		this.model.getSimpleTypeOrComplexTypeOrGroup().add(((AbstractTypeImpl<? extends Annotated,?>) type).getModel());
	}

	public Import removeImport(final Import importDef) {
        throw new UnsupportedOperationException();
	}

	public Include removeInclude(final Include includeDef) throws SchemaException {
        throw new UnsupportedOperationException();
	}
	
	public Redefine removeRedefine(final Redefine redefineDef) {
        throw new UnsupportedOperationException();
	}

	public Attribute createAttribute() {
		return new AttributeImpl(new org.ow2.easywsdl.schema.org.w3._2001.xmlschema.Attribute(), this);
	}

	public Type createComplexType() {
		return new ComplexTypeImpl(new org.ow2.easywsdl.schema.org.w3._2001.xmlschema.TopLevelComplexType(), this);
	}

	public Element createElement() {
		return new ElementImpl(new TopLevelElement(), this);
	}

	public Type createSimpleType() {
		return new SimpleTypeImpl(new org.ow2.easywsdl.schema.org.w3._2001.xmlschema.TopLevelSimpleType(), this);
	}

	public void setTargetNamespace(String tns) {
		this.model.setTargetNamespace(tns);
	}

	public Import createImport() throws SchemaException {
		try {
            return new ImportImpl(new org.ow2.easywsdl.schema.org.w3._2001.xmlschema.Import(), this, null, this.getDocumentURI(), this.reader);
        } catch (final URISyntaxException e) {
            // No URI is already set, so this exception cannot occur
            throw new SchemaException(e);
        }
	}

	public Include createInclude() throws SchemaException {
		try {
            return new IncludeImpl(new org.ow2.easywsdl.schema.org.w3._2001.xmlschema.Include(), this, null, this.getDocumentURI(), this.reader);
        } catch (final URISyntaxException e) {
            // No URI is already set, so this exception cannot occur
            throw new SchemaException(e);
        }
	}
	
	public Redefine createRedefine() throws SchemaException {
		try {
            return new RedefineImpl(new org.ow2.easywsdl.schema.org.w3._2001.xmlschema.Redefine(), this, null, this.getDocumentURI(), this.reader);
        } catch (final URISyntaxException e) {
            // No URI is already set, so this exception cannot occur
            throw new SchemaException(e);
        }
	}

	public void setAttributeFormDefault(FormChoice form) {
		this.model.setAttributeFormDefault(form);
	}

	public void setElementFormDefault(FormChoice form) {
		this.model.setElementFormDefault(form);
	}


}
