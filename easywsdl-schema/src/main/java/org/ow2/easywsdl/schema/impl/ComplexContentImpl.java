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

import org.ow2.easywsdl.schema.api.ComplexContent;
import org.ow2.easywsdl.schema.api.Extension;
import org.ow2.easywsdl.schema.api.abstractElmt.AbstractComplexContentImpl;
import org.ow2.easywsdl.schema.api.abstractElmt.AbstractSchemaElementImpl;

/**
 * @author Nicolas Salatge - EBM WebSourcing
 */
public class ComplexContentImpl
        extends
        AbstractComplexContentImpl<org.ow2.easywsdl.schema.org.w3._2001.xmlschema.ComplexContent, Extension>
        implements ComplexContent {

    /**
	 *
	 */
    private static final long serialVersionUID = 1L;

    public ComplexContentImpl(
            final org.ow2.easywsdl.schema.org.w3._2001.xmlschema.ComplexContent model,
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
		this.model.setExtension((org.ow2.easywsdl.schema.org.w3._2001.xmlschema.ExtensionType) ((AbstractSchemaElementImpl)this.extension).getModel());

	}

	public Extension createExtension() {
		return new ExtensionImpl(new org.ow2.easywsdl.schema.org.w3._2001.xmlschema.ExtensionType(), this);
	}


}
