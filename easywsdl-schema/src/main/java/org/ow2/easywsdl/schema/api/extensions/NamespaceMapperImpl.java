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

package org.ow2.easywsdl.schema.api.extensions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.XMLConstants;

import org.glassfish.jaxb.runtime.marshaller.NamespacePrefixMapper;


/**
 * @author Nicolas Salatge - EBM WebSourcing
 */
public class NamespaceMapperImpl extends NamespacePrefixMapper{

    /**
     * Map: key = prefix - value = namespaceUri
     */
    public Map<String, String> ns = new HashMap<>();

    public Map<String, String> getNamespaces() {
		return ns;
	}

	public NamespaceMapperImpl(){
    	super();
    	addNamespace(XMLConstants.XML_NS_PREFIX, XMLConstants.XML_NS_URI);
 //   	addNamespace(XMLConstants.XMLNS_ATTRIBUTE, XMLConstants.XMLNS_ATTRIBUTE_NS_URI);
    }

	// TODO: This constructor should be replaced by a constructor using a Map<prefix, uri>
	public NamespaceMapperImpl(String[] initialNamespaces) {
		this();
		try {

			for (int i = 0; i < initialNamespaces.length; i++) {

				String prefix = initialNamespaces[i++];
				// TODO: check array size to prevent ArrayIndexOutOfBoundsException
				String namespace = initialNamespaces[i];

				this.ns.put(namespace, prefix);
			}
		}
		catch (Exception e) {
			System.out
					.println("Error while initialising custom namespaces. Using default namespaces.");
			this.ns.clear();
		}

	}

	public void addNamespace(final String prefix, final String namespaceUri) {
    	if (!"".equals(prefix)){
        	this.ns.put(prefix,namespaceUri);
    	}
    }

    public String getNamespaceURI(final String prefix) {
        return this.ns.get(prefix);
    }

    @Override
	public String[] getPreDeclaredNamespaceUris() {

		String[] custNS = new String[this.ns.size() * 2];
		int i = 0;
		for (Map.Entry<String, String> entry : ns.entrySet()) {
			String prefix = entry.getKey();
			String namespaceURI = entry.getValue();
			custNS[i++] = prefix;
			custNS[i++] = namespaceURI;
		}
		return custNS;
	}

    @Override
	public String getPreferredPrefix(String namespaceUri, String suggestion,
			boolean requirePrefix) {

		String res = getPrefix(namespaceUri);
		if (res != null){
			return res;
		}

		return suggestion;
	}

    // from javax.xml.namespace.NamespaceContext
    public String getPrefix(final String namespaceURI) {
        String res = null;
        for (final Entry<String, String> entry : this.ns.entrySet()) {
            if (entry.getValue().equals(namespaceURI)) {
                res = entry.getKey();
                break;
            }
        }

        return res;
    }

    public Iterator<String> getPrefixes(final String namespaceURI) {
        final List<String> res = new ArrayList<>();
        for (final Entry<String, String> entry : this.ns.entrySet()) {
            if (entry.getValue().equals(namespaceURI)) {
                res.add(entry.getKey());
            }
        }
        return res.iterator();
    }

    @Override
    public String toString() {
        final StringBuilder res = new StringBuilder();
        for (final Entry<String, String> entry : this.ns.entrySet()) {
            res.append("xmlns:");
            res.append(entry.getKey());
            res.append("=");
            res.append(entry.getValue());
            res.append(" \n");
        }
        return res.toString();
    }
}
