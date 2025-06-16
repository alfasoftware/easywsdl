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
 
package org.ow2.easywsdl.schema;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.xml.namespace.QName;

import org.ow2.easywsdl.schema.api.Attribute;
import org.ow2.easywsdl.schema.api.Documentation;
import org.ow2.easywsdl.schema.api.Element;
import org.ow2.easywsdl.schema.api.Import;
import org.ow2.easywsdl.schema.api.Include;
import org.ow2.easywsdl.schema.api.Redefine;
import org.ow2.easywsdl.schema.api.Schema;
import org.ow2.easywsdl.schema.api.SchemaException;
import org.ow2.easywsdl.schema.api.Type;
import org.ow2.easywsdl.schema.api.XmlException;
import org.ow2.easywsdl.schema.api.absItf.AbsItfAttribute;
import org.ow2.easywsdl.schema.api.absItf.AbsItfElement;
import org.ow2.easywsdl.schema.api.absItf.AbsItfImport;
import org.ow2.easywsdl.schema.api.absItf.AbsItfInclude;
import org.ow2.easywsdl.schema.api.absItf.AbsItfRedefine;
import org.ow2.easywsdl.schema.api.absItf.AbsItfType;
import org.ow2.easywsdl.schema.api.extensions.NamespaceMapperImpl;
import org.ow2.easywsdl.schema.impl.Constants;
import org.ow2.easywsdl.schema.impl.SchemaJAXBContext;
import org.ow2.easywsdl.schema.org.w3._2001.xmlschema.FormChoice;

/**
 * @author Nicolas Salatge - EBM WebSourcing
 */
public final class DefaultSchemaImpl implements DefaultSchema {

	private static final long serialVersionUID = 1L;

	private static final Logger LOG = Logger.getLogger(DefaultSchemaImpl.class.getName());

	private final Schema defaultSchema;
	
	private final static DefaultSchemaImpl INSTANCE;

	static {
		final URL schemaUrl = SchemaJAXBContext.class.getResource("/" + Constants.XSD_SCHEMA);
		Schema defaultSchema = null;
		try {
			defaultSchema = new org.ow2.easywsdl.schema.impl.SchemaReaderImpl()
					.read(schemaUrl);
		} catch (final XmlException e) {
			e.printStackTrace();
			DefaultSchemaImpl.LOG.warning("Error to read default parent => " + e.getMessage());
		} catch (final URISyntaxException e) {
			e.printStackTrace();
			DefaultSchemaImpl.LOG.warning("Error to read default parent => " + e.getMessage());
		} catch (final IOException e) {
		    e.printStackTrace();
            DefaultSchemaImpl.LOG.warning("I/O error reading " + schemaUrl.toString() + " : " + e.getMessage());
		}
		
		INSTANCE = new DefaultSchemaImpl(defaultSchema);
	}

	private DefaultSchemaImpl(final Schema defaultSchema) {
	    this.defaultSchema = defaultSchema;
	}

	public static DefaultSchema getInstance() {
		return DefaultSchemaImpl.INSTANCE;
	}

	public Type getTypeInt() {
		return getType(new QName(Constants.SCHEMA_NAMESPACE, "int"));
	}

	public Type getTypeString() {
		return getType(new QName(Constants.SCHEMA_NAMESPACE, "string"));
	}
	
	public Type getTypeDateTime() {
		return getType(new QName(Constants.SCHEMA_NAMESPACE, "dateTime"));
	}
	
	public Type getTypeBoolean() {
		return getType(new QName(Constants.SCHEMA_NAMESPACE, "boolean"));
	}

	@SuppressWarnings("unchecked")
	public List<Type> getTypes() {
		List<Type> res = null;
		if(this.defaultSchema != null) {
			res = this.defaultSchema.getTypes();
		}
		return res;
	}

	public Type getType(QName type) {
		Type res = null;
		for(Type t: this.getTypes()) {
			if((t.getQName().getNamespaceURI().equals(type.getNamespaceURI()))
					&&(t.getQName().getLocalPart().equals(type.getLocalPart()))) {
				res = t;
				break;
			}
		}
		return res;
	}

	public Type getTypeDouble() {
		return getType(new QName(Constants.SCHEMA_NAMESPACE, "double"));
	}

	public Type getTypeDuration() {
		return getType(new QName(Constants.SCHEMA_NAMESPACE, "duration"));
	}

	public Type getTypeFloat() {
		return getType(new QName(Constants.SCHEMA_NAMESPACE, "float"));
	}

	public Type getTypeInteger() {
		return getType(new QName(Constants.SCHEMA_NAMESPACE, "integer"));
	}

	public Type getTypeLong() {
		return getType(new QName(Constants.SCHEMA_NAMESPACE, "long"));
	}

	public Type getTypeShort() {
		return getType(new QName(Constants.SCHEMA_NAMESPACE, "short"));
	}

	public void addAttribute(Attribute attr) {
		this.defaultSchema.addAttribute(attr);
	}

	public void addElement(Element elmt) {
	    this.defaultSchema.addElement(elmt);
	}

	public void addImport(Import importDef) {
	    this.defaultSchema.addImport(importDef);
	}

	public void addInclude(Include includeDef) throws SchemaException {
	    this.defaultSchema.addInclude(includeDef);
	}
	
	public void addRedefine(Redefine redefineDef) {
	    this.defaultSchema.addRedefine(redefineDef);
	}

	public void addType(Type absItfType) {
	    this.defaultSchema.addType(absItfType);
	}

	public Attribute createAttribute() {
		return this.defaultSchema.createAttribute();
	}

	public Type createComplexType() {
		return this.defaultSchema.createComplexType();
	}

	public Element createElement() {
		return this.defaultSchema.createElement();
	}

	public Import createImport() throws SchemaException {
		return this.defaultSchema.createImport();
	}

	public Include createInclude() throws SchemaException {
		return this.defaultSchema.createInclude();
	}
	
	public Redefine createRedefine() throws SchemaException {
		return this.defaultSchema.createRedefine();
	}

	public Type createSimpleType() {
		return this.defaultSchema.createSimpleType();
	}

	public List<Element> findElementsInAllSchema(QName element) {
		return this.defaultSchema.findElementsInAllSchema(element);
	}

	public NamespaceMapperImpl getAllNamespaces() {
		return this.defaultSchema.getAllNamespaces();
	}

	public Attribute getAttribute(QName attr) {
		return this.defaultSchema.getAttribute(attr);
	}

	public List<Attribute> getAttributes() {
		return this.defaultSchema.getAttributes();
	}

	public List<String> getBlockDefault() {
		return this.defaultSchema.getBlockDefault();
	}

	public URI getDocumentURI() {
		return this.defaultSchema.getDocumentURI();
	}

	public Element getElement(QName element) {
		return this.defaultSchema.getElement(element);
	}

	public List<Element> getElements() {
		return this.defaultSchema.getElements();
	}

	public List<String> getFinalDefault() {
		return this.defaultSchema.getFinalDefault();
	}

	public List<Import> getImports(String namespaceURI) {
		return this.defaultSchema.getImports(namespaceURI);
	}

	public List<Import> getImports() {
		return this.defaultSchema.getImports();
	}

	public List<Include> getIncludes(URI locationURI) throws URISyntaxException {
		return this.defaultSchema.getIncludes(locationURI);
	}

	public List<Include> getIncludes() {
		return this.defaultSchema.getIncludes();
	}
	
	public List<Redefine> getRedefines(String namespaceURI) {
		return this.defaultSchema.getRedefines(namespaceURI);
	}

	public List<Redefine> getRedefines() {
		return this.defaultSchema.getRedefines();
	}

	public String getLang() {
		return this.defaultSchema.getLang();
	}

	public String getTargetNamespace() {
		return this.defaultSchema.getTargetNamespace();
	}

	public String getVersion() {
		return this.defaultSchema.getVersion();
	}

	public Import removeImport(Import importDef) {
		return this.defaultSchema.removeImport(importDef);
	}

	public Include removeInclude(Include includeDef) throws SchemaException {
		return this.defaultSchema.removeInclude(includeDef);
	}
	
	public Redefine removeRedefine(Redefine redefineDef) {
		return this.defaultSchema.removeRedefine(redefineDef);
	}

	public void setDocumentURI(URI documentBaseURI) {
		this.defaultSchema.setDocumentURI(documentBaseURI);
	}

	public void setTargetNamespace(String tns) {
		this.defaultSchema.setTargetNamespace(tns);
	}

	public Map<QName, String> getOtherAttributes() throws XmlException {
		return this.defaultSchema.getOtherAttributes();
	}

	public void setDocumentation(Documentation doc) {
		this.defaultSchema.setDocumentation(doc);
	}

	public FormChoice getAttributeFormDefault() {
		return this.defaultSchema.getAttributeFormDefault();
	}

	public FormChoice getElementFormDefault() {
		return this.defaultSchema.getElementFormDefault();
	}

	public void setAttributeFormDefault(FormChoice form) {
		this.defaultSchema.setAttributeFormDefault(form);
	}

	public void setElementFormDefault(FormChoice form) {
		this.defaultSchema.setElementFormDefault(form);
	}

	public Documentation createDocumentation() {
		return this.defaultSchema.createDocumentation();
	}

	public Documentation getDocumentation() {
		return this.defaultSchema.getDocumentation();
	}


}
