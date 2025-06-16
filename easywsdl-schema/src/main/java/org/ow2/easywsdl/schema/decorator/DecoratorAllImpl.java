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

import org.ow2.easywsdl.schema.api.SchemaException;
import org.ow2.easywsdl.schema.api.absItf.AbsItfAll;
import org.ow2.easywsdl.schema.api.absItf.AbsItfElement;

/**
 * @author Nicolas Boissel-Dallier - EBM WebSourcing
 */
public abstract class DecoratorAllImpl<E extends AbsItfElement> extends DecoratorGroupImpl<E, AbsItfAll<E>> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DecoratorAllImpl(final AbsItfAll<E> all, Class<? extends E> eImpl) throws SchemaException{
		super(all, eImpl);
		this.internalObject = all;
	}

}
