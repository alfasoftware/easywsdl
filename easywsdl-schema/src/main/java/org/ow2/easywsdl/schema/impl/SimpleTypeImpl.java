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

import org.ow2.easywsdl.schema.api.Attribute;
import org.ow2.easywsdl.schema.api.Restriction;
import org.ow2.easywsdl.schema.api.SimpleType;
import org.ow2.easywsdl.schema.api.abstractElmt.AbstractSchemaElementImpl;
import org.ow2.easywsdl.schema.api.abstractElmt.AbstractSimpleTypeImpl;

/**
 * @author Nicolas Salatge - EBM WebSourcing
 */
public class SimpleTypeImpl
        extends
        AbstractSimpleTypeImpl<org.ow2.easywsdl.schema.org.w3._2001.xmlschema.SimpleType, String, Attribute, Restriction>
        implements SimpleType {

    /**
	 *
	 */
    private static final long serialVersionUID = 1L;

    public SimpleTypeImpl(
            final org.ow2.easywsdl.schema.org.w3._2001.xmlschema.SimpleType model,
            final AbstractSchemaElementImpl parent) {
        super(model, parent);

		// get restriction
		if(this.model.getRestriction() != null) {
			this.restriction = new RestrictionImpl(this.model.getRestriction(), this);
		}
    }

    public QName getQName() {
        QName res = null;
        if ((this.getModel()).getName() != null) {
            res = new QName(this.parent.getSchema().getTargetNamespace(), (this.getModel()).getName());
        } else {
            res = null;
        }
        return res;
    }

    public void setQName(QName name) {
    	this.model.setName(name.getLocalPart());
    }

    public void setRestriction(Restriction restriction){
    	super.setRestriction(restriction);
		this.model.setRestriction((org.ow2.easywsdl.schema.org.w3._2001.xmlschema.Restriction)((AbstractSchemaElementImpl)this.restriction).getModel());
    }

    public Restriction createRestriction(){
    	return new RestrictionImpl(new org.ow2.easywsdl.schema.org.w3._2001.xmlschema.Restriction(), this);
    }
    
}
