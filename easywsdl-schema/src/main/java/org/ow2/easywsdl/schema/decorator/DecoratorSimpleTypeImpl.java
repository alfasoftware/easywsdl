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

import java.lang.reflect.InvocationTargetException;

import org.ow2.easywsdl.schema.api.SchemaException;
import org.ow2.easywsdl.schema.api.absItf.AbsItfAttribute;
import org.ow2.easywsdl.schema.api.absItf.AbsItfRestriction;
import org.ow2.easywsdl.schema.api.absItf.AbsItfSimpleType;

/**
 * @author Nicolas Salatge - EBM WebSourcing
 */
public abstract class DecoratorSimpleTypeImpl<A extends AbsItfAttribute, R extends AbsItfRestriction> 
extends DecoratorTypeImpl<AbsItfSimpleType<A, R>> {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private R restriction;
    
    @SuppressWarnings("unchecked")
	public DecoratorSimpleTypeImpl(final AbsItfSimpleType<A, R> simpleType, Class<? extends R> rImpl) throws SchemaException {
        super(simpleType);
		try {
			if(simpleType.getRestriction() != null) {
				restriction = (R)rImpl.getConstructors()[0].newInstance(simpleType.getRestriction());
			}
		} catch (IllegalArgumentException e) {
			throw new SchemaException(e);
		} catch (SecurityException e) {
			throw new SchemaException(e);
		} catch (InstantiationException e) {
			throw new SchemaException(e);
		} catch (IllegalAccessException e) {
			throw new SchemaException(e);
		} catch (InvocationTargetException e) {
			throw new SchemaException(e);
		}
    }
    
	public R createRestriction() {
		return (R) this.internalObject.createRestriction();
	}

	public R getRestriction() {
		return this.restriction;
	}

	public void setRestriction(R restriction) {
		this.restriction = restriction;
		this.internalObject.setRestriction(((R)((Decorator)restriction).getInternalObject()));
	}

}
