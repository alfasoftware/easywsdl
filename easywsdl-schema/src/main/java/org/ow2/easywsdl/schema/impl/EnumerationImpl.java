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
 
package org.ow2.easywsdl.schema.impl;

import org.ow2.easywsdl.schema.api.Element;
import org.ow2.easywsdl.schema.api.Enumeration;
import org.ow2.easywsdl.schema.api.abstractElmt.AbstractEnumerationImpl;
import org.ow2.easywsdl.schema.api.abstractElmt.AbstractSchemaElementImpl;
import org.ow2.easywsdl.schema.org.w3._2001.xmlschema.LocalElement;
import org.ow2.easywsdl.schema.org.w3._2001.xmlschema.NoFixedFacet;

/**
 * @author Nicolas Boissel-Dallier - EBM WebSourcing
 */
public class EnumerationImpl extends AbstractEnumerationImpl<NoFixedFacet> implements Enumeration {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public EnumerationImpl(NoFixedFacet model, AbstractSchemaElementImpl parent) {
		super(model, parent);
	}

	public Element createElement() {
		return new ElementImpl(new LocalElement(), this);
	}

	public String getValue() {
		return this.model.getValue();
	}

	public void setValue(String value) {
		this.model.setValue(value);
	}

}
