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

/**
 * @author Nicolas Salatge - EBM WebSourcing
 */
public class Constants {

    /**
     * SchemaImpl Namespace
     */
    public static final String SCHEMA_NAMESPACE = "http://www.w3.org/2001/XMLSchema";

    /**
     * SchemaImpl root tag
     */
    public static final String SCHEMA_ROOT_TAG = "schema";

    /**
     * SchemaImpl name
     */
    public static final String XSD_SCHEMA = "org/ow2/easywsdl/schema/XMLSchema.xsd";

    public static final String XML_XSD = "org/ow2/easywsdl/schema/xml.xsd";

    public static final URI XML_URI = URI.create("http://www.w3.org/2001/xml.xsd");    
}
