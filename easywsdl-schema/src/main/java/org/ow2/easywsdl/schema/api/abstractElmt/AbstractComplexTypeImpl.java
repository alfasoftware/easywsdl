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
 
package org.ow2.easywsdl.schema.api.abstractElmt;

import java.util.ArrayList;
import java.util.List;

import org.ow2.easywsdl.schema.api.absItf.AbsItfAll;
import org.ow2.easywsdl.schema.api.absItf.AbsItfAttribute;
import org.ow2.easywsdl.schema.api.absItf.AbsItfChoice;
import org.ow2.easywsdl.schema.api.absItf.AbsItfComplexContent;
import org.ow2.easywsdl.schema.api.absItf.AbsItfComplexType;
import org.ow2.easywsdl.schema.api.absItf.AbsItfSequence;
import org.ow2.easywsdl.schema.api.absItf.AbsItfSimpleContent;
import org.ow2.easywsdl.schema.org.w3._2001.xmlschema.Annotated;

/**
 * @author Nicolas Salatge - EBM WebSourcing
 */
@SuppressWarnings("unchecked")
public abstract class AbstractComplexTypeImpl<E extends Annotated, V, A extends AbsItfAttribute, S extends AbsItfSequence, All extends AbsItfAll, Ch extends AbsItfChoice, CC extends AbsItfComplexContent, SC extends AbsItfSimpleContent>
        extends AbstractTypeImpl<E, V> implements AbsItfComplexType<A, S, All, Ch, CC, SC> {

    /**
	 *
	 */
    private static final long serialVersionUID = 1L;

    private S sequence;
    
    private All all;
    
    private Ch choice;

    private CC complexContent;

    private SC simpleContent;

    private List<A> attributes;

    
    public AbstractComplexTypeImpl(final E model, final AbstractSchemaElementImpl parent) {
        super(model, parent);
    }

    public S getSequence() {
    	return this.sequence;
    }
    
    public boolean hasSequence() {
    	return (this.sequence != null);
    }

    public void setSequence(S sequence) {
    	this.sequence = sequence;
    }
    
    public All getAll() {
    	return this.all;
    }

    public boolean hasAll() {
    	return (this.all != null);
    }
    
    public void setAll(All all) {
    	this.all = all;
    }

    public Ch getChoice() {
    	return this.choice;
    }
    
    public boolean hasChoice() {
    	return (this.choice != null);
    }

    public void setChoice(Ch choice) {
    	this.choice = choice;
    }
    
    public CC getComplexContent() {
    	return this.complexContent;
    }

    public boolean hasComplexContent() {
    	return (this.complexContent != null);
    }
    
    public void setComplexContent(CC complexContent) {
    	this.complexContent = complexContent;
    }
    
    public SC getSimpleContent() {
    	return this.simpleContent;
    }

    public boolean hasSimpleContent() {
    	return (this.simpleContent != null);
    }
    
    public void setSimpleContent(SC simpleContent) {
    	this.simpleContent = simpleContent;
    }
    
    

    /*
     * Attributes methods
     */

    /*
     * (non-Javadoc)
     *
     * @see org.ow2.easywsdl.schema.api.Schema#getAttributes()
     */
    public List<A> getAttributes() {
        if (this.attributes == null) {
            this.attributes = new ArrayList<A>();
        }
        return this.attributes;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.ow2.easywsdl.schema.api.Schema#getAttribute(javax.xml.namespace
     * .QName)
     */
    public A getAttribute(final String element) {
        A res = null;
        if (this.attributes == null) {
            this.attributes = new ArrayList<A>();
        }
        for (final A e : this.attributes) {
            if (e.getName().equals(element)) {
                res = e;
                break;
            }
        }
        return res;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.ow2.easywsdl.schema.api.Schema#addAttribute(com.ebmwebsourcing
     * .commons.schema.api.Attribute)
     */
    public void addAttribute(final A elmt) {
        if (this.attributes == null) {
            this.attributes = new ArrayList<A>();
        }
        this.attributes.add(elmt);
    }
}
