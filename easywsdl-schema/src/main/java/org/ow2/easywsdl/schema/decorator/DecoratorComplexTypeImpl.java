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

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.ow2.easywsdl.schema.api.SchemaException;
import org.ow2.easywsdl.schema.api.absItf.AbsItfAll;
import org.ow2.easywsdl.schema.api.absItf.AbsItfAttribute;
import org.ow2.easywsdl.schema.api.absItf.AbsItfChoice;
import org.ow2.easywsdl.schema.api.absItf.AbsItfComplexContent;
import org.ow2.easywsdl.schema.api.absItf.AbsItfComplexType;
import org.ow2.easywsdl.schema.api.absItf.AbsItfSequence;
import org.ow2.easywsdl.schema.api.absItf.AbsItfSimpleContent;

/**
 * @author Nicolas Salatge - EBM WebSourcing
 */
public abstract class DecoratorComplexTypeImpl<A extends AbsItfAttribute, S extends AbsItfSequence, All extends AbsItfAll, Ch extends AbsItfChoice, CC extends AbsItfComplexContent, SC extends AbsItfSimpleContent>
extends DecoratorTypeImpl<AbsItfComplexType<A,S,All,Ch,CC,SC>> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private S sequence;
	private All all;
	private Ch choice;
	private CC complex;
	private SC simple;
	private List<A> attributes = new ArrayList<A>();

	@SuppressWarnings("unchecked")
    public DecoratorComplexTypeImpl(final AbsItfComplexType complexType, Class<? extends A> aImpl, Class<? extends S> sImpl, Class<? extends All> allImpl, Class<? extends Ch> chImpl, Class<? extends CC> ccImpl, Class<? extends SC> scImpl) throws SchemaException {
        super(complexType);
        
		final List<A> oldArgs = ((AbsItfComplexType)this.internalObject).getAttributes();
		
        try {
        	if(((AbsItfComplexType)this.internalObject).hasSequence()) {
        		this.sequence = (S) sImpl.getConstructors()[0].newInstance(((AbsItfComplexType)this.internalObject).getSequence());	
        	} else if (((AbsItfComplexType)this.internalObject).hasAll()) {
    			this.all = (All) allImpl.getConstructors()[0].newInstance(((AbsItfComplexType)this.internalObject).getAll());
        	} else if(((AbsItfComplexType)this.internalObject).hasChoice()) {
        		this.choice = (Ch) chImpl.getConstructors()[0].newInstance(((AbsItfComplexType)this.internalObject).getChoice());
        	} else if(((AbsItfComplexType)this.internalObject).hasComplexContent()){
        		this.complex = (CC) ccImpl.getConstructors()[0].newInstance(((AbsItfComplexType)this.internalObject).getComplexContent());
        	} else if(((AbsItfComplexType)this.internalObject).hasSimpleContent()){
        		this.simple = (SC) scImpl.getConstructors()[0].newInstance(((AbsItfComplexType)this.internalObject).getSimpleContent());
        	}

			if(!oldArgs.isEmpty()) {
				Iterator<A> iArg = oldArgs.iterator();
				while(iArg.hasNext()){
					attributes.add((A) aImpl.getConstructors()[0].newInstance(iArg.next()));
				}
			}
			
		} catch (IllegalArgumentException e) {
			throw new SchemaException(e);
		} catch (SecurityException e) {
			throw new SchemaException(e);
		} catch (InstantiationException e) {
			throw new SchemaException(e);
		} catch (IllegalAccessException e) {
			throw new SchemaException(e);
		} catch (InvocationTargetException e) {
			throw new SchemaException(e);
		}
    }

	public S createSequence() {
		return (S) ((AbsItfComplexType<A,S,All,Ch,CC,SC>) this.internalObject).createSequence();
	}

	public S getSequence() {
		return this.sequence;
	}

	public boolean hasSequence() {
		return ((AbsItfComplexType<A,S,All,Ch,CC,SC>) this.internalObject).hasSequence();
	}
	
	public void setSequence(S sequence) {
		this.sequence = sequence;
		((AbsItfComplexType<A,S,All,Ch,CC,SC>) this.internalObject).setSequence((S)((Decorator)sequence).getInternalObject());
	}
	
	public All createAll() {
		return (All) ((AbsItfComplexType<A,S,All,Ch,CC,SC>) this.internalObject).createAll();
	}

	public All getAll() {
		return this.all;
	}
	
	public boolean hasAll() {
		return ((AbsItfComplexType<A,S,All,Ch,CC,SC>) this.internalObject).hasAll();
	}

	public void setAll(All all) {
		this.all = all;
		((AbsItfComplexType<A,S,All,Ch,CC,SC>) this.internalObject).setAll((All)((Decorator)all).getInternalObject());
	}


	public Ch createChoice() {
		return (Ch) ((AbsItfComplexType<A,S,All,Ch,CC,SC>) this.internalObject).createChoice();
	}

	public Ch getChoice() {
		return this.choice;
	}

	public boolean hasChoice() {
		return ((AbsItfComplexType<A,S,All,Ch,CC,SC>) this.internalObject).hasChoice();
	}
	
	public void setChoice(Ch choice) {
		this.choice = choice;
		((AbsItfComplexType<A,S,All,Ch,CC,SC>) this.internalObject).setChoice((Ch)((Decorator)choice).getInternalObject());
	}
	
	public CC createComplexContent() {
		return (CC) ((AbsItfComplexType<A,S,All,Ch,CC,SC>) this.internalObject).createComplexContent();
	}

	public CC getComplexContent() {
		return this.complex;
	}
	
	public boolean hasComplexContent() {
		return ((AbsItfComplexType<A,S,All,Ch,CC,SC>) this.internalObject).hasComplexContent();
	}

	public void setComplexContent(CC complexContent) {
		this.complex = complexContent;
		((AbsItfComplexType<A,S,All,Ch,CC,SC>) this.internalObject).setComplexContent((CC)((Decorator)complex).getInternalObject());
	}

	public SC createSimpleContent() {
		return (SC) ((AbsItfComplexType<A,S,All,Ch,CC,SC>) this.internalObject).createSimpleContent();
	}

	public SC getSimpleContent() {
		return this.simple;
	}
	
	public boolean hasSimpleContent() {
		return ((AbsItfComplexType<A,S,All,Ch,CC,SC>) this.internalObject).hasSimpleContent();
	}

	public void setSimpleContent(SC simpleContent) {
		this.simple = simpleContent;
		((AbsItfComplexType<A,S,All,Ch,CC,SC>) this.internalObject).setSimpleContent((SC)((Decorator)simple).getInternalObject());
	}

    public void addAttribute(final A attr) {
    	this.attributes.add(attr);
    	((AbsItfComplexType<A,S,All,Ch,CC,SC>) this.internalObject).addAttribute((A)((Decorator)attr).getInternalObject());
    }

    public A getAttribute(final String attr) {
        A res = null;
        if (this.attributes == null) {
            this.attributes = new ArrayList<A>();
        }
        for (final A e : this.attributes) {
            if (e.getName().equals(attr)) {
                res = e;
                break;
            }
        }
        return res;    
    }

    public List<A> getAttributes() {
        return this.attributes;
    }

}
