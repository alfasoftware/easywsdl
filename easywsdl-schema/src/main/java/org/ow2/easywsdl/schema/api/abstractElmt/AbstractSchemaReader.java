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
 
package org.ow2.easywsdl.schema.api.abstractElmt;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import org.ow2.easywsdl.schema.api.Schema;
import org.ow2.easywsdl.schema.api.SchemaException;
import org.ow2.easywsdl.schema.api.SchemaReader;
import org.ow2.easywsdl.schema.api.absItf.AbsItfSchema;

/**
 * This interface describes an XSD reader to be used internally.
 * @author Christophe DENEUX - Capgemini Sud
 */
public abstract class AbstractSchemaReader implements SchemaReader {

    /**
     * Read an external XML Schema URI according to a base URI.
     * 
     * @throws SchemaException
     * @throws MalformedURLException
     *             The URL based on the external XSD URI and the current base
     *             URI is a malformed URL.
     * @throws URISyntaxException
     *             The URL based on the external XSD URI and the current base
     *             URI is an URL not formatted strictly according to to RFC2396
     *             and cannot be converted to a URI.
     */
    protected abstract Schema readExternalPart(final URI schemaURI, final URI baseURI,
            final Map<URI, AbsItfSchema> imports)
            throws SchemaException, MalformedURLException, URISyntaxException;


}
