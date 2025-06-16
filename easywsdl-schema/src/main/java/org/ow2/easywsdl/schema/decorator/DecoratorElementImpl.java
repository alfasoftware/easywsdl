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

import javax.xml.namespace.QName;

import org.ow2.easywsdl.schema.api.SchemaException;
import org.ow2.easywsdl.schema.api.absItf.AbsItfComplexType;
import org.ow2.easywsdl.schema.api.absItf.AbsItfElement;
import org.ow2.easywsdl.schema.api.absItf.AbsItfSimpleType;
import org.ow2.easywsdl.schema.api.absItf.AbsItfType;
import org.ow2.easywsdl.schema.org.w3._2001.xmlschema.FormChoice;

/**
 * @author Nicolas Salatge - EBM WebSourcing
 */
public abstract class DecoratorElementImpl<T extends AbsItfType> extends Decorator<AbsItfElement<T>> {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private T type;

    public DecoratorElementImpl(final AbsItfElement<T> element, Class<? extends T> tSImpl, Class<? extends T> tCImpl) throws SchemaException {
        this.internalObject = element; 
        try {
        	if(element.getType() instanceof AbsItfSimpleType) {
        		this.type = (T) tSImpl.getConstructors()[0].newInstance(this.internalObject.getType());
        	} else if(element.getType() instanceof AbsItfComplexType){
        		this.type = (T) tCImpl.getConstructors()[0].newInstance(this.internalObject.getType());
        	} else {
        		this.type = (T) this.internalObject.getType();
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

	public QName getQName() {
        return this.internalObject.getQName();
    }

    public  T getType() {
        return type;
    }

	public String getMaxOccurs() {
		return this.internalObject.getMaxOccurs();
	}

	public int getMinOccurs() {
		return this.internalObject.getMinOccurs();
	}

	public void setMaxOccurs(String max) {
		this.internalObject.setMaxOccurs(max);
	}

	public void setMinOccurs(int min) {
		this.internalObject.setMinOccurs(min);
	}

	public void setQName(QName name) {
		this.internalObject.setQName(name);
	}

	public void setType(T type) {
		this.type = type;
		if(type instanceof Decorator) {
			this.internalObject.setType((T)((Decorator)type).getInternalObject());
		} else {
			this.internalObject.setType(type);
		}
	}

	public QName getRef() {
		return this.internalObject.getRef();
	}

	public void setRef(QName name) {
		this.internalObject.setRef(name);
	}

	public boolean isNillable() {
		return this.internalObject.isNillable();
	}

	public void setNillable(boolean nill) {
		this.internalObject.setNillable(nill);
	}

	public FormChoice getForm() {
		return this.internalObject.getForm();
	}

}
