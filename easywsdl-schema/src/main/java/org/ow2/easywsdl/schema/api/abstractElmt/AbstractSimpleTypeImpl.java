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

import org.ow2.easywsdl.schema.api.absItf.AbsItfAttribute;
import org.ow2.easywsdl.schema.api.absItf.AbsItfRestriction;
import org.ow2.easywsdl.schema.api.absItf.AbsItfSimpleType;
import org.ow2.easywsdl.schema.org.w3._2001.xmlschema.Annotated;

/**
 * @author Nicolas Salatge - EBM WebSourcing
 */
@SuppressWarnings("unchecked")
public abstract class AbstractSimpleTypeImpl<E extends Annotated, V, A extends AbsItfAttribute, R extends AbsItfRestriction>
        extends AbstractTypeImpl<E, V> implements AbsItfSimpleType<A, R> {

    /**
	 *
	 */
    private static final long serialVersionUID = 1L;

    protected R restriction = null;
    
    public AbstractSimpleTypeImpl(final E model, final AbstractSchemaElementImpl parent) {
        super(model, parent);
    }
    
    public R getRestriction(){
    	return this.restriction;
    }

    public void setRestriction(R restriction){
    	this.restriction = restriction;
    }
}
