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
 
package org.ow2.easywsdl.wsdl.api;

import org.ow2.easywsdl.wsdl.api.abstractItf.AbsItfBindingOperation;

/**
 * This interface represents a WSDL operation binding.
 * <p>
 * It holds information about the operation element contained within
 * a binding element.
 * </p>
 * 
 * @author Nicolas Salatge - EBM WebSourcing
 */
public interface BindingOperation extends
        AbsItfBindingOperation<Operation, BindingInput, BindingOutput, BindingFault> {

}
