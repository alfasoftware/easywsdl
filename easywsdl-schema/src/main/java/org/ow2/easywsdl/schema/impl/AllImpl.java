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

import jakarta.xml.bind.JAXBElement;

import org.ow2.easywsdl.schema.api.All;
import org.ow2.easywsdl.schema.api.Element;
import org.ow2.easywsdl.schema.api.abstractElmt.AbstractAllImpl;
import org.ow2.easywsdl.schema.api.abstractElmt.AbstractElementImpl;
import org.ow2.easywsdl.schema.api.abstractElmt.AbstractSchemaElementImpl;
import org.ow2.easywsdl.schema.org.w3._2001.xmlschema.LocalElement;
import org.ow2.easywsdl.schema.org.w3._2001.xmlschema.ObjectFactory;

/**
 * @author Nicolas Salatge - EBM WebSourcing
 */
public class AllImpl extends AbstractAllImpl<org.ow2.easywsdl.schema.org.w3._2001.xmlschema.All, Element> implements All {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public AllImpl(org.ow2.easywsdl.schema.org.w3._2001.xmlschema.All model, AbstractSchemaElementImpl parent) {
		super(model, parent);

		// get elements
		for (Object item : this.model.getParticle()) {
			if (item instanceof JAXBElement) {
				if (((JAXBElement) item).getValue() instanceof org.ow2.easywsdl.schema.org.w3._2001.xmlschema.Element) {
					this.elements
							.add(new ElementImpl(
									(org.ow2.easywsdl.schema.org.w3._2001.xmlschema.Element) ((JAXBElement) item)
											.getValue(), this));

				}
			}
			// TODO: finish to analyze
		}

	}

	@Override
  public Element createElement() {
		return new ElementImpl(new LocalElement(), this);
	}

	@Override
	public void addElement(Element elmt) {
		super.addElement(elmt);
		ObjectFactory factory = new ObjectFactory();
		JAXBElement<LocalElement> element = factory
				.createGroupElement((LocalElement) ((AbstractElementImpl) elmt)
						.getModel());
		this.model.getParticle().add(element);
	}


}
