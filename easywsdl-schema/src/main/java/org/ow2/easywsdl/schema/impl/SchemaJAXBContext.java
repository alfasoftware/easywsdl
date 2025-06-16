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

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.ow2.easywsdl.schema.api.SchemaException;
/**
 * @author Nicolas Salatge - EBM WebSourcing
 */
public final class SchemaJAXBContext {

    private static final Class<?>[] SCHEMA_DEFAULT_OBJECT_FACTORIES = new Class<?>[] {
            org.ow2.easywsdl.schema.org.w3._2001.xmlschema.ObjectFactory.class };

	private static SchemaJAXBContext instance = null;
	private static SchemaException fail = null;

	static {
		try {
			instance = new SchemaJAXBContext();
        } catch (final SchemaException e) {
			fail = e;
		}
	}

    public static Class<?>[] getDefaultObjectFactories() {
        return SCHEMA_DEFAULT_OBJECT_FACTORIES.clone();
    }

    private Set<Class<?>> currentObjectFactories = new HashSet<Class<?>>();

    /**
     * The JAXB context
     */
    private JAXBContext jaxbContext;

	/**
	 * Private object initializations
	 */
	private SchemaJAXBContext() throws SchemaException {
		//SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

        this.addOtherObjectFactory(getDefaultObjectFactories());

	}

	public static SchemaJAXBContext getInstance() throws SchemaException {
		if(fail == null) {
			return instance;
		} else {
			throw fail;
		}
	}

    public synchronized void addOtherObjectFactory(Class<?>[] addedObjectFactories) throws SchemaException {
        addOtherObjectFactory(Arrays.asList(addedObjectFactories));
    }

    public synchronized void addOtherObjectFactory(List<Class<?>> addedObjectFactories) throws SchemaException {

        if (addedObjectFactories != null) {

            currentObjectFactories.addAll(addedObjectFactories);

			try {
				//factory.newSchema(new StreamSource[] { new StreamSource(schemaUrl11.openStream()) });

                this.jaxbContext = JAXBContext
                        .newInstance(currentObjectFactories.toArray(new Class[currentObjectFactories.size()]));

			} catch (final JAXBException e) {
				throw new SchemaException(e);
			}
		}
	}


	/**
	 * @return the jaxbContext
	 */
	public synchronized JAXBContext getJaxbContext() {
		return this.jaxbContext;
	}
}
