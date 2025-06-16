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

import java.util.Map;
import java.util.logging.Logger;

import org.ow2.easywsdl.schema.api.Schema;
import org.ow2.easywsdl.schema.api.SchemaException;
import org.ow2.easywsdl.schema.api.SchemaReader;
import org.ow2.easywsdl.schema.api.SchemaWriter;
import org.ow2.easywsdl.schema.api.SchemaReader.FeatureConstants;


/**
 * This abstract class defines a factory API that enables applications to obtain
 * a SchemaFactory capable of producing new Definitions, new SchemaReaders, and
 * new SchemaWriters.
 *
 *  <p>
 * Some ideas used here have been shamelessly copied from the wonderful JAXP and
 * Xerces work.
 * </p>
 *
 * @author Nicolas Salatge - EBM WebSourcing
 */
public abstract class SchemaFactory {

    private static final Logger LOG = Logger.getLogger(SchemaFactory.class.getName());



    /**
     * Get a new instance of a SchemaFactory. This method follows (almost) the
     * same basic sequence of steps that JAXP follows to determine the
     * fully-qualified class name of the class which implements SchemaFactory.
     * <p>
     * The steps in order are:
     * <ol>
     * <li>Check the property file
     * META-INF/services/javax.wsdl.factory.SchemaFactory.</li>
     * <li>Check the javax.wsdl.factory.SchemaFactory system property.</li>
     * <li>Check the lib/wsdl.properties file in the JRE directory. The key will
     * have the same name as the above system property.</li>
     * <li>Use the default class name provided by the implementation.</li>
     * </ol>
     * <p>
     * Once an instance of a SchemaFactory is obtained, invoke newDefinition(),
     * newSchemaReader(), or newSchemaWriter(), to create the desired instances.
     */
    public static SchemaFactory newInstance() throws SchemaException {
        return new SchemaFactoryImpl();
    }

    /**
     * Create a new instance of a Definition.
     * @throws SchemaException 
     */
    public abstract Schema newSchema() throws SchemaException;

    /**
     * Create a new instance of a SchemaReaderImpl.
     * @throws SchemaException 
     */
    public abstract SchemaReader newSchemaReader() throws SchemaException;

    /**
     * Create a new instance of a SchemaReaderImpl.
     * @throws SchemaException 
     */
    public abstract SchemaReader newSchemaReader(Map<FeatureConstants, Object> features) throws SchemaException;

    /**
     * Create a new instance of a SchemaWriterImpl.
     * @throws SchemaException 
     */
    public abstract SchemaWriter newSchemaWriter() throws SchemaException;

    /**
     * Get the default parent
     *
     * @return the default parent
     */
    public static DefaultSchema getDefaultSchema() {
        return DefaultSchemaImpl.getInstance();
    }
}
