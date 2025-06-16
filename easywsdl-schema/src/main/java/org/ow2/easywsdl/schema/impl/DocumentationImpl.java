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

import org.ow2.easywsdl.schema.api.abstractElmt.AbstractSchemaElementImpl;

/**
 * @author Nicolas Salatge - EBM WebSourcing
 */
public class DocumentationImpl
		extends
		AbstractSchemaElementImpl<org.ow2.easywsdl.schema.org.w3._2001.xmlschema.Documentation>
		implements org.ow2.easywsdl.schema.api.Documentation {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public DocumentationImpl() {
		super(
				new org.ow2.easywsdl.schema.org.w3._2001.xmlschema.Documentation(),
				null);
	}

	public DocumentationImpl(
			final org.ow2.easywsdl.schema.org.w3._2001.xmlschema.Documentation doc) {
		super(doc, null);
	}

	public String getContent() {
        final StringBuilder res = new StringBuilder();

		for (final Object item : this.model.getContent()) {
			if (item instanceof String) {
				res.append(item.toString());
			}
		}
		return res.toString();
	}

	public void setContent(final String content) {
		this.model.getContent().clear();
		this.model.getContent().add(content);
	}

}
