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

import org.ow2.easywsdl.schema.SchemaFactory;
import org.ow2.easywsdl.schema.api.Attribute;
import org.ow2.easywsdl.schema.api.Type;
import org.ow2.easywsdl.schema.api.absItf.AbsItfType;
import org.ow2.easywsdl.schema.api.abstractElmt.AbstractAttributeImpl;
import org.ow2.easywsdl.schema.api.abstractElmt.AbstractSchemaElementImpl;


/**
* @author Nicolas Salatge - EBM WebSourcing
*/
public class AttributeImpl extends
        AbstractAttributeImpl<org.ow2.easywsdl.schema.org.w3._2001.xmlschema.Attribute, Type>
        implements Attribute {

    /**
	 *
	 */
    private static final long serialVersionUID = 1L;

    /**
     *
     * @param model
     * @param parent
     */
    public AttributeImpl(
            final org.ow2.easywsdl.schema.org.w3._2001.xmlschema.Attribute model,
            final AbstractSchemaElementImpl parent) {
        super(model, parent);

        if (SchemaFactory.getDefaultSchema() != null) {
            // type define in parent
            for (final Object obj : parent.getSchema().getTypes()) {
                if (obj instanceof AbsItfType) {
                    final AbsItfType t = (AbsItfType) obj;
                    if (t.getQName().equals(this.model.getType())) {
                        this.type = (Type) t;
                        break;
                    }
                }
            }

            // type define in default parent
            if (this.type == null) {
                if (SchemaFactory.getDefaultSchema().getTypes() != null) {
                    for (final Object obj : SchemaFactory.getDefaultSchema().getTypes()) {
                        final AbsItfType t = (AbsItfType) obj;
                        if (t.getQName().equals(this.model.getType())) {
                            this.type = (Type) t;
                            break;
                        }
                    }
                }
            }
        }

        // if referenced attribute
        if (this.model.getRef() != null) {
            this.referencedAttribute = this.parent.getSchema().getAttribute(this.model.getRef());
        }

    }

    public String getName() {
        String name = null;
        if (this.model.getName() != null) {
            name = this.model.getName();
        } else if (this.referencedAttribute != null) {
            name = this.referencedAttribute.getName();
        }
        return name;
    }

    public String getNamespaceUri() {
        String ns = null;
        if (this.model.getName() != null) {
            ns = this.parent.getSchema().getTargetNamespace();
        } else if (this.referencedAttribute != null) {
            ns = this.referencedAttribute.getNamespaceUri();
        }
        return ns;
    }

    public String getValue() {
        throw new UnsupportedOperationException();
    }

	public Use getUse() {
		Use res = Use.OPTIONAL;
		if(this.model.getUse() != null) {
			res = Use.value(this.model.getUse());
		}
		return res;
	}

}
