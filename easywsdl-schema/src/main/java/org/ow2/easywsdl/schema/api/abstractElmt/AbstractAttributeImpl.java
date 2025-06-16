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
import org.ow2.easywsdl.schema.api.absItf.AbsItfSchema;
import org.ow2.easywsdl.schema.api.absItf.AbsItfType;

/**
 * @author Nicolas Salatge - EBM WebSourcing
 */
public abstract class AbstractAttributeImpl<E, T extends AbsItfType> extends AbstractSchemaElementImpl<E> implements
AbsItfAttribute<T> {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;


	/**
	 * the type of element
	 */
	protected T type;

	/**
	 * referenced attribute
	 */
	protected AbsItfAttribute referencedAttribute;

	/**
	 *
	 * @param model
	 * @param parent
	 */
	public AbstractAttributeImpl(final E model, final AbstractSchemaElementImpl parent) {
		super(model, parent);
		this.referencedAttribute = null;
	}

	public AbsItfSchema getSchema() {
		return this.parent.getSchema();
	}

	public T getType() {
		return this.type;
	}

	public void setType(T type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return this.getName();
	}
}
