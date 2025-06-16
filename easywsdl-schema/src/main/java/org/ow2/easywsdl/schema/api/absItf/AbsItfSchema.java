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
 
package org.ow2.easywsdl.schema.api.absItf;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.xml.namespace.QName;

import org.ow2.easywsdl.schema.api.SchemaElement;
import org.ow2.easywsdl.schema.api.SchemaException;
import org.ow2.easywsdl.schema.api.extensions.NamespaceMapperImpl;
import org.ow2.easywsdl.schema.org.w3._2001.xmlschema.FormChoice;

/**
 * @author Nicolas Salatge - EBM WebSourcing
 */
public interface AbsItfSchema<T extends AbsItfType, E extends AbsItfElement, A extends AbsItfAttribute, Incl extends AbsItfInclude, Impt extends AbsItfImport, Red extends AbsItfRedefine> extends SchemaElement {

	/*
	 * method for types
	 */

	/**
	 * @return
	 */
	List<T> getTypes();

	void addType(T absItfType);

	T getType(QName type);

	T createComplexType();

	T createSimpleType();
	

	/*
	 * Method for elements
	 */
	List<E> getElements();

	void addElement(E elmt);

	E getElement(QName element);

	E createElement();
	
	List<E> findElementsInAllSchema(QName element);

	/*
	 * Method for attributes
	 */
	List<A> getAttributes();

	void addAttribute(A attr);

	A getAttribute(QName attr);

	A createAttribute();

	/*
	 * method for namespace
	 */
	String getTargetNamespace();

	void setTargetNamespace(String tns);

	NamespaceMapperImpl getAllNamespaces();

	/**
	 * Add an import to this parent.
	 * 
	 * @param importDef
	 *            the import to be added
	 */
	void addImport(Impt importDef);

	/**
	 * Create an import to this parent.
	 * 
	 * @throws SchemaException
	 *             if an imported element can't be retrieved
	 */
	Impt createImport() throws SchemaException;

	/**
	 * Remove an import from this parent.
	 * 
	 * @param importDef
	 *            the import to be removed
	 * @return the removed ImportImpl
	 */
	Impt removeImport(Impt importDef);

	/**
	 * Get the list of imports for the specified namespaceURI.
	 * 
	 * @param namespaceURI
	 *            the namespaceURI associated with the desired imports.
	 * @return a list of the corresponding imports, or null if there weren't any matching imports
	 */
	List<Impt> getImports(String namespaceURI);

	/**
	 * Get a map of lists containing all the imports defined here. The map's keys are the namespaceURIs, and the map's values are lists. There is one list for each namespaceURI for which imports have been defined.
	 */
	List<Impt> getImports();

	/**
	 * Add an include to this parent.
	 * 
	 * @param includeDef
	 *            the include to be added
	 * @throws SchemaException
	 *             if an included element can't be retrieved
	 */
	void addInclude(Incl includeDef) throws SchemaException;

	/**
	 * Create an include to this parent.
	 * 
	 * @throws SchemaException
	 *             if an included element can't be retrieved
	 */
	Incl createInclude() throws SchemaException;

	/**
	 * Remove an include from this parent.
	 * 
	 * @param includeDef
	 *            the include to be removed
	 * @return the removed include
	 * @throws SchemaException
	 */
	Incl removeInclude(Incl includeDef) throws SchemaException;

    /**
     * Get the list of includes for the specified locationURI.
     * 
     * @param locationURI
     *            the locationURI associated with the desired includes.
     * @return a list of the corresponding includes, or null if there weren't
     *         any matching includes
     * @throws URISyntaxException
     *             The schemaLocation definition of an include of the schema is
     *             invalid.
     */
	// FIXME: Is it possible to have several include for one locationURI ?
    List<Incl> getIncludes(URI locationURI) throws URISyntaxException;

	/**
	 * Get a map of lists containing all the includes defined here. The map's keys are the namespaceURIs, and the map's values are lists. There is one list for each locationURI for which imports have been defined.
	 */
	List<Incl> getIncludes();

	/**
	 * Set the document base URI of this definition. Can be used to represent the origin of the Definition, and can be exploited when resolving relative URIs (e.g. in &lt;import&gt;s).
	 * 
	 * @param documentBaseURI
	 *            the document base URI of this definition
	 */
	void setDocumentURI(URI documentBaseURI);

	URI getDocumentURI();

	FormChoice getElementFormDefault();
	
	void setElementFormDefault(FormChoice form);

	FormChoice getAttributeFormDefault();
	
	void setAttributeFormDefault(FormChoice form);

	String getLang();

	String getVersion();

	List<String> getBlockDefault();

	List<String> getFinalDefault();
	
	

	void addRedefine(Red redDef);


	Red createRedefine() throws SchemaException;


	Red removeRedefine(Red redDef);


	List<Red> getRedefines(String namespaceURI);

	
	List<Red> getRedefines();


}
