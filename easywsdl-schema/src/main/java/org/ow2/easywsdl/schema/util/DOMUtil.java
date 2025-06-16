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
 
package org.ow2.easywsdl.schema.util;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author Nicolas Salatge - EBM WebSourcing
 */
public class DOMUtil {

	public static Element getFirstElement(Element parent) {
		Element res = null;
		NodeList list = parent.getChildNodes();
		for(int i = 0; i < list.getLength(); i++) {
			if(list.item(i).getNodeType() == Node.ELEMENT_NODE) {
				res = (Element) list.item(i);
				break;
			}
		}
		return res;
	}
}
