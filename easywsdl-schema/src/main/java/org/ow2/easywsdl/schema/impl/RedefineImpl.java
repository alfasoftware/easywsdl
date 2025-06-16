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
import java.net.URISyntaxException;
import java.util.Map;

import org.ow2.easywsdl.schema.api.Redefine;
import org.ow2.easywsdl.schema.api.Schema;
import org.ow2.easywsdl.schema.api.SchemaException;
import org.ow2.easywsdl.schema.api.absItf.AbsItfSchema;
import org.ow2.easywsdl.schema.api.abstractElmt.AbstractIncludeImpl;
import org.ow2.easywsdl.schema.api.abstractElmt.AbstractSchemaElementImpl;
import org.ow2.easywsdl.schema.api.abstractElmt.AbstractSchemaImpl;
import org.ow2.easywsdl.schema.api.abstractElmt.AbstractSchemaReader;

/**
 * @author Nicolas Salatge - EBM WebSourcing
 */
public class RedefineImpl
        extends
        AbstractIncludeImpl<org.ow2.easywsdl.schema.org.w3._2001.xmlschema.Redefine, Schema>
        implements Redefine {

    /**
	 *
	 */
    private static final long serialVersionUID = 1L;

    /**
     * @param model
     * @param parent
     * @param imports
     * @param reader
     * @throws SchemaException
     * @throws URISyntaxException
     *             If the schemaLocation attribute of the redefine is not a valid
     *             URI.
     */
    public RedefineImpl(
            final org.ow2.easywsdl.schema.org.w3._2001.xmlschema.Redefine model,
            final AbstractSchemaElementImpl parent, final Map<URI, AbsItfSchema> imports, final URI baseURI, final AbstractSchemaReader reader) throws SchemaException, URISyntaxException {
        super(model, parent, imports, baseURI, reader);
        // TODO Auto-generated constructor stub
    }

    /**
     * {@inheritDoc}
     */
    public URI getLocationURI() {
        if (this.model.getSchemaLocation() != null) {
            return URI.create(this.model.getSchemaLocation());
        } else {
            return null;
        }
    }

    public void setLocationURI(final URI locationURI) {
        this.model.setSchemaLocation(locationURI.toString());
    }


}
