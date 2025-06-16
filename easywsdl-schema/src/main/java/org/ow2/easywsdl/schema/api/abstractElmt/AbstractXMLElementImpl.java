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

import java.util.logging.Logger;

import org.ow2.easywsdl.schema.api.XMLElement;

/**
 * Abstract super class for all WSDL Elements, providing some basic
 * common functionality.
 * 
 * @author Nicolas Salatge - EBM WebSourcing
 */
public abstract class AbstractXMLElementImpl<E> extends AbstractSchemaElementImpl<E> implements XMLElement {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private static Logger log = Logger.getLogger(AbstractXMLElementImpl.class.getName());


    public AbstractXMLElementImpl() {
    	super();
    }
    
    public AbstractXMLElementImpl(E model, AbstractXMLElementImpl parent) {
        super(model, parent);
    }

    

}
