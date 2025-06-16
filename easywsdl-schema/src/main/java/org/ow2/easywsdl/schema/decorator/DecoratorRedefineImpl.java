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
 
package org.ow2.easywsdl.schema.decorator;

import java.net.URI;

import org.ow2.easywsdl.schema.api.absItf.AbsItfRedefine;
import org.ow2.easywsdl.schema.api.absItf.AbsItfSchema;

/**
 * @author Nicolas Salatge - EBM WebSourcing
 */
public abstract class DecoratorRedefineImpl<S extends AbsItfSchema> extends Decorator<AbsItfRedefine<S>> {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DecoratorRedefineImpl(final AbsItfRedefine<S> redefine) {
        this.internalObject = redefine;
    }

    public URI getLocationURI() {
        return this.internalObject.getLocationURI();
    }

    public S getSchema() {
        return this.internalObject.getSchema();
    }

    public void setLocationURI(final URI locationURI) {
        this.internalObject.setLocationURI(locationURI);
    }

    public void setSchema(final S schema) {
        this.internalObject.setSchema(schema);
    }
}
