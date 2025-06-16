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

import java.io.OutputStream;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.ow2.easywsdl.schema.api.Schema;
import org.ow2.easywsdl.schema.api.SchemaException;
import org.ow2.easywsdl.schema.api.extensions.NamespaceMapperImpl;
import org.w3c.dom.Document;

/**
 * @author Nicolas Salatge - EBM WebSourcing
 */
public class SchemaWriterImpl implements org.ow2.easywsdl.schema.api.SchemaWriter {
	private DocumentBuilderFactory builder = null;
	
	/*
	 * Private object initializations
	 */
	public SchemaWriterImpl() throws SchemaException {
        // just to verify everything is ok
        SchemaJAXBContext.getInstance();

		builder = DocumentBuilderFactory.newInstance();
		builder.setNamespaceAware(true);
	}

	@SuppressWarnings("unchecked")
	public Document convertSchema2DOMElement(final org.ow2.easywsdl.schema.org.w3._2001.xmlschema.Schema schemaDescriptor, NamespaceMapperImpl namespaceMapper) throws SchemaException {
		Document doc = null;
		try {
			doc = builder.newDocumentBuilder().newDocument();

			// TODO : Check if it is a Thread safe method
			final JAXBElement element = new JAXBElement(new QName(Constants.SCHEMA_NAMESPACE, Constants.SCHEMA_ROOT_TAG), schemaDescriptor.getClass(), schemaDescriptor);

            Marshaller marshaller = SchemaJAXBContext.getInstance().getJaxbContext().createMarshaller();

			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			// marshaller.setProperty("com.sun.xml.bind.namespacePrefixMapper",
			// namespaceMapper);
			marshaller.marshal(element, doc);
			
		} catch (final JAXBException ex) {
			throw new SchemaException("Failed to build XML binding from SchemaImpl descriptor Java classes", ex);
		} catch (final ParserConfigurationException ex) {
			throw new SchemaException("Failed to build XML binding from SchemaImpl descriptor Java classes", ex);

		}
		return doc;
	}



	public Document getDocument(final Schema schemaDef) throws SchemaException {
		Document doc = null;
		if ((schemaDef != null) && (schemaDef instanceof org.ow2.easywsdl.schema.impl.SchemaImpl)) {
			try {
				doc = this.convertSchema2DOMElement(((org.ow2.easywsdl.schema.impl.SchemaImpl) schemaDef).getModel(), schemaDef.getAllNamespaces());
				if (schemaDef.getDocumentURI() != null) {
					doc.setDocumentURI(schemaDef.getDocumentURI().toString());
				}
			} catch (final SchemaException e) {
				throw new SchemaException("Can not write wsdl description", e);
			}
		}
		return doc;
	}

	public boolean getFeature(final String name) throws IllegalArgumentException {
        throw new UnsupportedOperationException();
	}

	public void setFeature(final String name, final boolean value) throws IllegalArgumentException {
        throw new UnsupportedOperationException();
	}

	public void writeSchema(final Schema schemaDef, OutputStream output) throws SchemaException {
		if ((schemaDef != null) && (schemaDef instanceof org.ow2.easywsdl.schema.impl.SchemaImpl)) {
			try {

				org.ow2.easywsdl.schema.org.w3._2001.xmlschema.Schema schemaDescriptor = ((org.ow2.easywsdl.schema.impl.SchemaImpl) schemaDef).getModel();
				final JAXBElement element = new JAXBElement(new QName(Constants.SCHEMA_NAMESPACE, Constants.SCHEMA_ROOT_TAG), schemaDescriptor.getClass(), schemaDescriptor);
                Marshaller marshaller = SchemaJAXBContext.getInstance().getJaxbContext().createMarshaller();

				marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

				// NamespaceMapperImpl namespaceMapper = schemaDef.getAllNamespaces();
				// marshaller.setProperty("com.sun.xml.bind.namespacePrefixMapper",
				// namespaceMapper);
				marshaller.marshal(element, output);

			} catch (final JAXBException e) {
				throw new SchemaException("Failed to build XML binding from Agreement descriptor Java classes", e);
			}
		}
	}
}
