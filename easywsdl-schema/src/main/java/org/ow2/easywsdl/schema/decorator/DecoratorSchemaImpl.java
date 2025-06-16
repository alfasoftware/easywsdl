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
 
package org.ow2.easywsdl.schema.decorator;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.xml.namespace.QName;

import org.ow2.easywsdl.schema.api.SchemaException;
import org.ow2.easywsdl.schema.api.absItf.AbsItfAttribute;
import org.ow2.easywsdl.schema.api.absItf.AbsItfElement;
import org.ow2.easywsdl.schema.api.absItf.AbsItfImport;
import org.ow2.easywsdl.schema.api.absItf.AbsItfInclude;
import org.ow2.easywsdl.schema.api.absItf.AbsItfRedefine;
import org.ow2.easywsdl.schema.api.absItf.AbsItfSchema;
import org.ow2.easywsdl.schema.api.absItf.AbsItfType;
import org.ow2.easywsdl.schema.api.extensions.NamespaceMapperImpl;
import org.ow2.easywsdl.schema.org.w3._2001.xmlschema.FormChoice;

/**
 * @author Nicolas Salatge - EBM WebSourcing
 */
public abstract class DecoratorSchemaImpl<T extends AbsItfType, E extends AbsItfElement, A extends AbsItfAttribute, Incl extends AbsItfInclude, Impt extends AbsItfImport, Red extends AbsItfRedefine>
extends Decorator<AbsItfSchema<T, E, A, Incl, Impt, Red>> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DecoratorSchemaImpl(final AbsItfSchema<T, E, A, Incl, Impt, Red> schema) {
		this.internalObject = schema;
	}

	public void addAttribute(final A attr) {
		this.internalObject.addAttribute(attr);
	}

	public void addElement(final E elmt) {
		this.internalObject.addElement(elmt);
	}

	public void addImport(final Impt importDef) {
		this.internalObject.addImport(importDef);
	}

	public void addInclude(final Incl includeDef) throws SchemaException {
		this.internalObject.addInclude(includeDef);
	}
	
	public void addRedefine(final Red redefineDef) {
		this.internalObject.addRedefine(redefineDef);
	}

	public void addType(final T absItfType) {
		this.internalObject.addType(absItfType);
	}

	public NamespaceMapperImpl getAllNamespaces() {
		return this.internalObject.getAllNamespaces();
	}

	public A getAttribute(final QName attr) {
		return this.internalObject.getAttribute(attr);
	}

	public List<A> getAttributes() {
		return this.internalObject.getAttributes();
	}

	public E getElement(final QName element) {
		return this.internalObject.getElement(element);
	}

	public List<E> getElements() {
		return this.internalObject.getElements();
	}

	public List<Impt> getImports(final String namespaceURI) {
		return this.internalObject.getImports();
	}

	public List<Impt> getImports() {
		return this.internalObject.getImports();
	}

	public List<Incl> getIncludes(final URI locationURI) throws URISyntaxException {
		return this.internalObject.getIncludes(locationURI);
	}

	public List<Incl> getIncludes() {
		return this.internalObject.getIncludes();
	}
	
	public List<Red> getRedefines(final String locationURI) {
		return this.internalObject.getRedefines(locationURI);
	}

	public List<Red> getRedefines() {
		return this.internalObject.getRedefines();
	}

	public String getTargetNamespace() {
		return this.internalObject.getTargetNamespace();
	}

	public T getType(final QName type) {
		return this.internalObject.getType(type);
	}

	public List<T> getTypes() {
		return this.internalObject.getTypes();
	}

	public Impt removeImport(final Impt importDef) {
		return this.internalObject.removeImport(importDef);
	}

	public Incl removeInclude(final Incl includeDef) throws SchemaException {
		return this.internalObject.removeInclude(includeDef);
	}
	
	public Red removeRedefine(final Red redefineDef) {
		return this.internalObject.removeRedefine(redefineDef);
	}

	public void setDocumentURI(final URI documentBaseURI) {
		this.internalObject.setDocumentURI(documentBaseURI);
	}

	public A createAttribute() {
		return this.internalObject.createAttribute();
	}

	public T createComplexType() {
		return this.internalObject.createComplexType();
	}

	public E createElement() {
		return this.internalObject.createElement();
	}

	public Impt createImport() throws SchemaException {
		return this.internalObject.createImport();
	}

	public Incl createInclude() throws SchemaException {
		return this.internalObject.createInclude();
	}
	
	public Red createRedefine() throws SchemaException {
		return this.internalObject.createRedefine();
	}

	public T createSimpleType() {
		return this.internalObject.createSimpleType();
	}

	public void setTargetNamespace(String tns) {
		this.internalObject.setTargetNamespace(tns);
	}

	public FormChoice getAttributeFormDefault() {
		return this.internalObject.getAttributeFormDefault();
	}

	public List<String> getBlockDefault() {
		return this.internalObject.getBlockDefault();
	}

	public FormChoice getElementFormDefault() {
		return this.internalObject.getElementFormDefault();
	}

	public List<String> getFinalDefault() {
		return this.internalObject.getFinalDefault();
	}

	public String getLang() {
		return this.internalObject.getLang();
	}

	public String getVersion() {
		return this.internalObject.getVersion();
	}

	public URI getDocumentURI() {
		return this.internalObject.getDocumentURI();
	}
	
	public  List<E> findElementsInAllSchema(QName element) {
		return this.internalObject.findElementsInAllSchema(element);
	}
	
	public void setAttributeFormDefault(FormChoice form) {
		this.internalObject.setAttributeFormDefault(form);
	}

	public void setElementFormDefault(FormChoice form) {
		this.internalObject.setElementFormDefault(form);
	}
}
