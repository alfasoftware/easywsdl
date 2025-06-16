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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.namespace.QName;

import org.ow2.easywsdl.schema.api.SchemaException;
import org.ow2.easywsdl.schema.api.absItf.AbsItfEnumeration;
import org.ow2.easywsdl.schema.api.absItf.AbsItfRestriction;

/**
 * @author Nicolas Boissel-Dallier - EBM WebSourcing
 */
public abstract class DecoratorRestrictionImpl<En extends AbsItfEnumeration> extends Decorator<AbsItfRestriction> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<En> enumerations = new ArrayList<En>();
	
	public DecoratorRestrictionImpl(final AbsItfRestriction restriction, Class<? extends En> enumImpl) throws SchemaException{
		this.internalObject = restriction;
		
		List<En> oldEnums = restriction.getEnumerations();

		try {
			if(!oldEnums.isEmpty()) {
				Iterator<En> iEnum = oldEnums.iterator();
				while(iEnum.hasNext()){
					enumerations.add((En) enumImpl.getConstructors()[0].newInstance(iEnum.next()));
				}
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

	public List<En> getEnumerations(){
		return this.enumerations;
	}

	public void addEnumeration(En enumeration){
		this.enumerations.add(enumeration);
		this.internalObject.addEnumeration((AbsItfEnumeration)((Decorator)enumeration).getInternalObject());
	}

	public En createEnumeration(){
		return (En) this.internalObject.createEnumeration();
	}
	
	public QName getBase(){
		return this.internalObject.getBase();
	}
	
	public void setBase(QName base){
		this.internalObject.setBase(base);
	}
}
