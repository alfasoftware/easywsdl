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

import javax.xml.namespace.QName;

import jakarta.xml.bind.JAXBElement;

import org.ow2.easywsdl.schema.api.Enumeration;
import org.ow2.easywsdl.schema.api.Restriction;
import org.ow2.easywsdl.schema.api.abstractElmt.AbstractEnumerationImpl;
import org.ow2.easywsdl.schema.api.abstractElmt.AbstractRestrictionImpl;
import org.ow2.easywsdl.schema.api.abstractElmt.AbstractSchemaElementImpl;
import org.ow2.easywsdl.schema.org.w3._2001.xmlschema.NoFixedFacet;
import org.ow2.easywsdl.schema.org.w3._2001.xmlschema.ObjectFactory;

/**
 * @author Nicolas Boissel-Dallier - EBM WebSourcing
 */
public class RestrictionImpl
	extends AbstractRestrictionImpl<org.ow2.easywsdl.schema.org.w3._2001.xmlschema.Restriction, Enumeration>
	implements Restriction {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public RestrictionImpl(org.ow2.easywsdl.schema.org.w3._2001.xmlschema.Restriction model, AbstractSchemaElementImpl parent) {
		super(model, parent);

		// get elements
		for (Object item : this.model.getFacets()) {
			if (item instanceof JAXBElement) {
				// Enumeration management
				if (((JAXBElement) item).getValue() instanceof NoFixedFacet && ((JAXBElement) item).getName().equals(
						new QName("http://www.w3.org/2001/XMLSchema", "enumeration"))) {
					this.enums.add(new EnumerationImpl((NoFixedFacet)((JAXBElement) item).getValue(), this));
				}
				// TODO: finish to parse (Pattern, Length...)
			}
		}
	}

	@Override
  public Enumeration createEnumeration() {
		return new EnumerationImpl(new NoFixedFacet(), this);
	}

	@Override
	public void addEnumeration(Enumeration enumeration) {
		super.addEnumeration(enumeration);
		ObjectFactory factory = new ObjectFactory();
		JAXBElement<NoFixedFacet> enumElmt = factory
				.createEnumeration((NoFixedFacet)((AbstractEnumerationImpl) enumeration).getModel());
		this.model.getFacets().add(enumElmt);
	}

	@Override
  public QName getBase() {
		return this.model.getBase();
	}

	@Override
  public void setBase(QName base) {
		this.model.setBase(base);
	}

}
