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
 
package org.ow2.easywsdl.schema.api.absItf;

import java.util.List;

/**
 * @author Nicolas Salatge - EBM WebSourcing
 */
public interface AbsItfComplexType<A extends AbsItfAttribute, S extends AbsItfSequence, All extends AbsItfAll, Ch extends AbsItfChoice, CC extends AbsItfComplexContent, SC extends AbsItfSimpleContent>
		extends AbsItfType {
    
	// Attributes
	List<A> getAttributes();

    void addAttribute(A attr);

    A getAttribute(String attr);
    
    // Sequence
    S getSequence();

    boolean hasSequence();
    
    void setSequence(S sequence);

    S createSequence();
    
    // All
    All getAll();
    
    boolean hasAll();

    void setAll(All all);

    All createAll();
    
    // Sequence
    Ch getChoice();

    boolean hasChoice();
    
    void setChoice(Ch choice);

    Ch createChoice();
    
    // ComplexContent
	CC getComplexContent();
    
    boolean hasComplexContent();
    
    void setComplexContent(CC complexContent);

	CC createComplexContent();

	// SimpleContent
	SC getSimpleContent();

	boolean hasSimpleContent();

	void setSimpleContent(SC simpleContent);
    
    SC createSimpleContent();
    

}
