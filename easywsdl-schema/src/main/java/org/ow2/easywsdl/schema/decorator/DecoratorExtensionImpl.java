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

import org.ow2.easywsdl.schema.api.absItf.AbsItfExtension;
import org.ow2.easywsdl.schema.api.absItf.AbsItfSequence;
import org.ow2.easywsdl.schema.api.absItf.AbsItfType;

/**
 * @author Nicolas Salatge - EBM WebSourcing
 */
public abstract class DecoratorExtensionImpl<T extends AbsItfType, S extends AbsItfSequence> extends Decorator<AbsItfExtension<T,S>> {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DecoratorExtensionImpl(final AbsItfExtension<T,S> extension) {
        this.internalObject = extension;
    }

	public S createSequence() {
		return (S) this.internalObject.createSequence();
	}

	public T getBase() {
		return (T) this.internalObject.getBase();
	}

	public S getSequence() {
		return (S) this.internalObject.getSequence();
	}

	public void setBase(T name) {
		this.internalObject.setBase(name);
	}

	public void setSequence(S sequence) {
		this.internalObject.setSequence(sequence);
	}

}
