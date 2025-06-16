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
 
package org.ow2.easywsdl.schema.test;

import javax.xml.namespace.QName;

import org.ow2.easywsdl.schema.impl.Constants;

/**
 * Define the unit test environement.
 * @author Christophe DENEUX - Capgemini Sud
 */
public class Environement {
    
    protected final static String DESCRIPTOR_CTX = "descriptors";
    
    protected final static String DESCRIPTOR_IN_JAR_CTX = "descriptors-in-jar";
    
    protected final static String SAMPLE_XSD = DESCRIPTOR_CTX + "/sample.xsd";
    
    protected final static String SAMPLE_XSD_EQUALS = DESCRIPTOR_CTX + "/sample_equals.xsd";
    
    protected final static String RESERVATIONLIST_XSD = DESCRIPTOR_CTX + "/reservationList.xsd";

    protected final static String RESERVATIONLIST_XSD_IN_JAR = DESCRIPTOR_IN_JAR_CTX + "/reservationList.xsd";

    protected final static String RESERVATIONDETAILS_XSD = DESCRIPTOR_CTX + "/reservationDetails.xsd";
    
    protected static class SampleWithSpaceInItsName {
        
        protected final static String XSD = DESCRIPTOR_CTX + "/sample with space in its name.xsd";
        
    }
    
    protected static class EltRefWithoutImport {
        
        protected final static String XSD = DESCRIPTOR_CTX + "/element-ref without import.xsd";
        
        protected final static String NAMESPACE = "http://foo.example.com/myNamespace";
        
        protected final static QName MYELT_ELT = new QName(NAMESPACE, "myElt");
        
        protected final static QName SCHEMA_ELT = new QName(Constants.SCHEMA_NAMESPACE, "schema");
            
    }
    
    
    protected final static String RESERVATIONDETAILS_NAMESPACE = "http://greath.example.com/2004/schemas/reservationDetails";
    
    protected final static QName RESERVATIONDETAILS_CONFNUMBER_ELT = new QName(RESERVATIONDETAILS_NAMESPACE, "confirmationNumber");
    
    protected final static String RESERVATIONLIST_NAMESPACE = "http://greath.example.com/2004/schemas/reservationList";
    
    protected final static QName RESERVATIONLIST_RESERVATION_ELT = new QName(RESERVATIONLIST_NAMESPACE, "reservation");

    
}
