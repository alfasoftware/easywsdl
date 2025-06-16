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

import org.ow2.easywsdl.schema.api.Extension;
import org.ow2.easywsdl.schema.api.SimpleContent;
import org.ow2.easywsdl.schema.api.abstractElmt.AbstractSchemaElementImpl;
import org.ow2.easywsdl.schema.api.abstractElmt.AbstractSimpleContentImpl;

/**
 * @author Christophe DENEUX - Capgemini Sud
 */
public class SimpleContentImpl
        extends
        AbstractSimpleContentImpl<org.ow2.easywsdl.schema.org.w3._2001.xmlschema.SimpleContent, Extension>
        implements SimpleContent {

    /**
	 *
	 */
    private static final long serialVersionUID = 1L;

    public SimpleContentImpl(
            final org.ow2.easywsdl.schema.org.w3._2001.xmlschema.SimpleContent model,
            final AbstractSchemaElementImpl parent) {
        super(model, parent);


        // get the extension
        if(this.model.getExtension() != null) {
        	this.extension = new ExtensionImpl(this.model.getExtension(), this);
        }
        
    }

	@Override
	public void setExtension(Extension extension) {
		super.setExtension(extension);
		this.model.setExtension((org.ow2.easywsdl.schema.org.w3._2001.xmlschema.SimpleExtensionType) ((AbstractSchemaElementImpl)this.extension).getModel());

	}

	public Extension createExtension() {
		return new ExtensionImpl(new org.ow2.easywsdl.schema.org.w3._2001.xmlschema.SimpleExtensionType(), this);
	}


}
