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

import org.ow2.easywsdl.schema.api.Schema;
import org.ow2.easywsdl.schema.api.SchemaException;
import org.ow2.easywsdl.schema.api.SchemaReader;
import org.ow2.easywsdl.schema.api.SchemaWriter;
import org.ow2.easywsdl.schema.api.SchemaReader.FeatureConstants;
import org.ow2.easywsdl.schema.impl.SchemaImpl;
import org.ow2.easywsdl.schema.impl.SchemaReaderImpl;

/**
 * This class is a concrete implementation of the abstract class SchemaFactory.
 * <p>
 * Some ideas used here have been shamelessly copied from the wonderful JAXP and
 * Xerces work.
 * </p>
 *
 * @author Nicolas Salatge - EBM WebSourcing
 */
public class SchemaFactoryImpl extends SchemaFactory {

    /**
     * Create a new instance of a Definition, with an instance of a
     * PopulatedExtensionRegistry as its ExtensionRegistry.
     * @throws SchemaException 
     */
    @Override
    public Schema newSchema() throws SchemaException {
        return new SchemaImpl();
    }

    /**
     * Create a new instance of a SchemaReaderImpl.
     * @throws SchemaException 
     */
    @Override
    public SchemaReader newSchemaReader() throws SchemaException {
        return new org.ow2.easywsdl.schema.impl.SchemaReaderImpl();
    }

    /**
     * Create a new instance of a SchemaReaderImpl.
     * @throws SchemaException 
     */
    @Override
    public SchemaReader newSchemaReader(final Map<FeatureConstants, Object> features) throws SchemaException {
        final SchemaReader reader = this.newSchemaReader();
        ((SchemaReaderImpl) reader).setFeatures(features);
        return reader;
    }

    /**
     * Create a new instance of a SchemaWriterImpl.
     * @throws SchemaException 
     */
    @Override
    public SchemaWriter newSchemaWriter() throws SchemaException {
        return new org.ow2.easywsdl.schema.impl.SchemaWriterImpl();
    }

}
