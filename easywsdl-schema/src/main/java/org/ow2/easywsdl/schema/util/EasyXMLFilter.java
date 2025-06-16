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
 
package org.ow2.easywsdl.schema.util;

import java.util.StringTokenizer;

import org.ow2.easywsdl.schema.api.extensions.NamespaceMapperImpl;
import org.ow2.easywsdl.schema.api.extensions.SchemaLocatorImpl;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLFilterImpl;

/**
 * @author Nicolas Salatge - EBM WebSourcing
 */
public class EasyXMLFilter extends XMLFilterImpl {

	private boolean first=true;
	
	private SchemaLocatorImpl schemaLocator = new SchemaLocatorImpl();
	
	private NamespaceMapperImpl namespaceContext = new NamespaceMapperImpl();
	
	public EasyXMLFilter(XMLReader parent) {
		super(parent);
	}

	@Override
	public void startElement(String uri, String localName, String name,
			Attributes atts) throws SAXException {
		if (first){
			String schemaLocation = atts.getValue("http://www.w3.org/2001/XMLSchema-instance", "schemaLocation");
			if (schemaLocation!=null){
				for (StringTokenizer stringTokenizer = new StringTokenizer(schemaLocation); stringTokenizer.hasMoreTokens(); ){
					String schemaUri = stringTokenizer.nextToken();
					if (stringTokenizer.hasMoreTokens()){
						String location = stringTokenizer.nextToken();
						schemaLocator.addSchemaLocation(schemaUri,location);
					}
				}
			}
			first = false;
		}
		super.startElement(uri, localName, name, atts);
	}

	@Override
	public void startPrefixMapping(String prefix, String uri)
			throws SAXException {
		namespaceContext.addNamespace(prefix, uri);
		super.startPrefixMapping(prefix, uri);
	}
	
	
	@Override
	public void startDocument() throws SAXException {
		first = true;
		super.startDocument();
	}

	public SchemaLocatorImpl getSchemaLocator() {
		return schemaLocator;
	}

	public NamespaceMapperImpl getNamespaceMapper() {
		return namespaceContext;
	}

}
