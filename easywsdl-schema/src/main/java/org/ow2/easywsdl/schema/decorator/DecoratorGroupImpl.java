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

import org.ow2.easywsdl.schema.api.SchemaException;
import org.ow2.easywsdl.schema.api.absItf.AbsItfElement;
import org.ow2.easywsdl.schema.api.absItf.AbsItfGroup;

/**
 * @author Nicolas Salatge - EBM WebSourcing
 */
public abstract class DecoratorGroupImpl<E extends AbsItfElement, G extends AbsItfGroup<E>> extends Decorator<G> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<E> elements = new ArrayList<E>();
	
    public DecoratorGroupImpl(final AbsItfGroup<E> group, Class<? extends E> eImpl) throws SchemaException {
    	this.internalObject = (G)group;
    	
		List<E> oldElems = internalObject.getElements();

		try {
			if(!oldElems.isEmpty()) {
				Iterator<E> iElem = oldElems.iterator();
				while(iElem.hasNext()){
					elements.add((E) eImpl.getConstructors()[0].newInstance(iElem.next()));
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

	public void addElement(E elmt) {
		this.elements.add(elmt);
		this.internalObject.addElement((E)((Decorator)elmt).getInternalObject());
	}
	
	public E createElement() {
		return (E) this.internalObject.createElement();
	}
	
	public List<E> getElements() {
		return this.elements;
	}
}
