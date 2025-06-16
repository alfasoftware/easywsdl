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
 
package org.ow2.easywsdl.schema.decorator;

import java.util.Map;

import javax.xml.namespace.QName;

import org.ow2.easywsdl.schema.api.Documentation;
import org.ow2.easywsdl.schema.api.SchemaElement;
import org.ow2.easywsdl.schema.api.XmlException;
import org.ow2.easywsdl.schema.api.abstractElmt.AbstractSchemaElementImpl;

/**
 * @author Nicolas Salatge - EBM WebSourcing
 */
public abstract class Decorator<IO extends SchemaElement> extends AbstractSchemaElementImpl{

	protected IO internalObject;
	
    public Documentation createDocumentation() {
        return this.internalObject.createDocumentation();
    }

    public Documentation getDocumentation() {
        return this.internalObject.getDocumentation();
    }

    public Map<QName, String> getOtherAttributes() throws XmlException {
        return this.internalObject.getOtherAttributes();
    }

    public void setDocumentation(final Documentation doc) {
        this.internalObject.setDocumentation(doc);
    }

	public Object getModel() {
		return ((AbstractSchemaElementImpl)this.internalObject).getModel();
	}

	public AbstractSchemaElementImpl getParent() {
		return ((AbstractSchemaElementImpl)this.internalObject).getParent();
	}

	public SchemaElement getInternalObject() {
		return this.internalObject;
	}
	
    public boolean equals(Object o){
    	return this.internalObject.equals(o);
    }
    
    public int hashCode(){
    	return this.internalObject.hashCode();
    }
}
