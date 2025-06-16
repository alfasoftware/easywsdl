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
import org.ow2.easywsdl.schema.api.absItf.AbsItfExtension;
import org.ow2.easywsdl.schema.api.absItf.AbsItfSimpleContent;

/**
 * @author Nicolas Salatge - EBM WebSourcing
 */
public abstract class DecoratorSimpleContentImpl<Ex extends AbsItfExtension> extends Decorator<AbsItfSimpleContent> {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Ex extension;
    
    public DecoratorSimpleContentImpl(final AbsItfSimpleContent<Ex> simpleContent, Class<? extends Ex> exImpl) throws SchemaException {
        this.internalObject = simpleContent;
        
        try {
			this.extension = (Ex) exImpl.getConstructors()[0].newInstance(simpleContent.getExtension());
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

	public Ex createExtension() {
		return (Ex) this.internalObject.createExtension();
	}

	public Ex getExtension() {
		return this.extension;
	}

	public void setExtension(Ex extension) {
		this.extension = extension;
		this.internalObject.setExtension((AbsItfExtension)((Decorator)extension).getInternalObject());
	}

}
