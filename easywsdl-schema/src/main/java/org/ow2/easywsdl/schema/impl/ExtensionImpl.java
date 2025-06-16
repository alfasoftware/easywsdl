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
import org.ow2.easywsdl.schema.api.Sequence;
import org.ow2.easywsdl.schema.api.Type;
import org.ow2.easywsdl.schema.api.abstractElmt.AbstractExtensionImpl;
import org.ow2.easywsdl.schema.api.abstractElmt.AbstractSchemaElementImpl;
import org.ow2.easywsdl.schema.org.w3._2001.xmlschema.ExplicitGroup;

/**
 * @author Nicolas Salatge - EBM WebSourcing
 */
public class ExtensionImpl
        extends
        AbstractExtensionImpl<org.ow2.easywsdl.schema.org.w3._2001.xmlschema.ExtensionType, Type, Sequence>
        implements Extension {

    /**
	 *
	 */
    private static final long serialVersionUID = 1L;

    public ExtensionImpl(
            final org.ow2.easywsdl.schema.org.w3._2001.xmlschema.ExtensionType model,
            final AbstractSchemaElementImpl parent) {
        super(model, parent);

        // get the base
        if(this.model.getBase() != null) {
        	this.baseType = (Type) this.getSchema().getType(this.model.getBase());
        }

        // get the sequence
        if(this.model.getSequence() != null) {
        	this.sequence = new SequenceImpl(this.model.getSequence(), this);
        }
        
    }

    public Type getBase() {
        return this.baseType;
    }

    public void setBase(Type base) {
    	this.baseType = base;
    	this.model.setBase(this.baseType.getQName());
    }
    

    @Override
	public void setSequence(Sequence sequence) {
		super.setSequence(sequence);
		this.model.setSequence((ExplicitGroup) ((AbstractSchemaElementImpl)this.sequence).getModel());
	}


	public Sequence createSequence() {
		return new SequenceImpl(new ExplicitGroup(), this);
	}

}
