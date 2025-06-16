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

import org.ow2.easywsdl.schema.api.absItf.AbsItfExtension;
import org.ow2.easywsdl.schema.api.absItf.AbsItfSimpleContent;
import org.ow2.easywsdl.schema.org.w3._2001.xmlschema.Annotated;

/**
 * @author Christophe DENEUX - Capgemini Sud
 */
@SuppressWarnings("unchecked")
public abstract class AbstractSimpleContentImpl<E extends Annotated, Ex extends AbsItfExtension>
          extends AbstractSchemaElementImpl<E> implements AbsItfSimpleContent<Ex> {

    /**
	 *
	 */
    private static final long serialVersionUID = 1L;

    protected Ex extension;

    public AbstractSimpleContentImpl(final E model, final AbstractSchemaElementImpl parent) {
        super(model, parent);
    }

    public Ex getExtension() {
    	return this.extension;
    }

    public void setExtension(Ex extension) {
    	this.extension = extension;
    }

}
