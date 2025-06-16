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

import org.ow2.easywsdl.schema.api.SchemaElement;

/**
 * @author Nicolas Salatge - EBM WebSourcing
 */
public interface AbsItfInclude<S extends AbsItfSchema> extends SchemaElement {

    /**
     * Set the location URI of this import.
     * 
     * @param locationURI
     *            the desired location URI
     */
    void setLocationURI(URI locationURI);

    /**
     * Get the location URI of this import.
     * 
     * @return the schema location {@link URI} of this import or
     *         <code>null</code> if undefined.
     */
    URI getLocationURI();

    /**
     * This property can be used to hang a referenced SchemaImpl, and the
     * top-level SchemaImpl (i.e. the one with the &lt;import&gt;) will use this
     * SchemaImpl when resolving referenced parts. This would need to be made
     * into a generic reference to handle other types of referenced documents.
     */
    void setSchema(S schema);

    /**
     * This property can be used to hang a referenced SchemaImpl, and the
     * top-level SchemaImpl (i.e. the one with the &lt;import&gt;) will use this
     * SchemaImpl when resolving referenced parts. This would need to be made
     * into a generic reference to handle other types of referenced documents.
     */
    S getSchema();
}
