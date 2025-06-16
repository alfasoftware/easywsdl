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

import org.ow2.easywsdl.schema.api.absItf.AbsItfElement;
import org.ow2.easywsdl.schema.api.absItf.AbsItfSequence;
import org.ow2.easywsdl.schema.org.w3._2001.xmlschema.Annotated;

/**
 * @author Nicolas Salatge - EBM WebSourcing
 */
@SuppressWarnings("unchecked")
public abstract class AbstractSequenceImpl<E extends Annotated, Elmt extends AbsItfElement>
        extends AbstractSchemaElementImpl<E> implements AbsItfSequence<Elmt> {

    /**
	 *
	 */
    private static final long serialVersionUID = 1L;


    protected List<Elmt> elements = new ArrayList<Elmt>();

    public AbstractSequenceImpl(final E model, AbstractSchemaElementImpl parent) {
        super(model, parent);
    }


	public List<Elmt> getElements() {
		return this.elements;
	}

	public void addElement(Elmt elmt) {
		this.elements.add(elmt);
	}

}
