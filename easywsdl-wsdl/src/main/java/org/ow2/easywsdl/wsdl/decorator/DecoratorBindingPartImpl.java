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
 
package org.ow2.easywsdl.wsdl.decorator;

import javax.xml.namespace.QName;

import org.ow2.easywsdl.wsdl.api.BindingPart;
import org.ow2.easywsdl.wsdl.api.WSDLException;

/**
 * @author Nicolas Salatge - EBM WebSourcing
 */
public abstract class DecoratorBindingPartImpl  extends Decorator<BindingPart> {

    /**
	 *
	 */
    private static final long serialVersionUID = 1L;

    public DecoratorBindingPartImpl(final BindingPart bindingPart) throws WSDLException {
        this.internalObject = bindingPart;
    }

    public QName getPartQName() {
        return this.internalObject.getPartQName();
    }

    public void setPartQName(final QName arg0) {
        this.internalObject.setPartQName(arg0);
    }
}
