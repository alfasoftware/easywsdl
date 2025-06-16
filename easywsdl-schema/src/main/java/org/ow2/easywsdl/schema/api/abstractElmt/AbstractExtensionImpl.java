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
import org.ow2.easywsdl.schema.api.absItf.AbsItfSequence;
import org.ow2.easywsdl.schema.api.absItf.AbsItfType;
import org.ow2.easywsdl.schema.org.w3._2001.xmlschema.Annotated;

/**
 * @author Nicolas Salatge - EBM WebSourcing
 */
@SuppressWarnings("unchecked")
public abstract class AbstractExtensionImpl<E extends Annotated, T extends AbsItfType, S extends AbsItfSequence>
          extends AbstractSchemaElementImpl<E> implements AbsItfExtension<T, S> {

    /**
	 *
	 */
    private static final long serialVersionUID = 1L;

    protected T baseType;
    
    protected S sequence;


    public AbstractExtensionImpl(final E model, final AbstractSchemaElementImpl parent) {
        super(model, parent);
    }

    public S getSequence() {
    	return this.sequence;
    }
    
	public void setSequence(S sequence) {
    	this.sequence = sequence;
    }

    public T getBase() {
		return baseType;
	}

	public void setBase(T baseType) {
		this.baseType = baseType;
	}



}
