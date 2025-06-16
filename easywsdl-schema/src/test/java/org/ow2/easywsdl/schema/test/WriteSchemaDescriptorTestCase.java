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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import org.junit.Assert;
import junit.framework.TestCase;

import org.ow2.easywsdl.schema.SchemaFactory;
import org.ow2.easywsdl.schema.api.Schema;
import org.ow2.easywsdl.schema.api.SchemaException;
import org.w3c.dom.Document;

/**
 * @author Nicolas Salatge - EBM WebSourcing
 */
public class WriteSchemaDescriptorTestCase extends TestCase {

	public void testWriterSchema_Document() throws SchemaException, URISyntaxException, SchemaException, IOException {
		
		final URL xsdUrl = Thread.currentThread().getContextClassLoader().getResource(Environement.SAMPLE_XSD);
        Assert.assertNotNull(
                "XMLSchema '" + Environement.SAMPLE_XSD + "' not found in the classpath.", xsdUrl);
        final Schema desc = SchemaFactory.newInstance().newSchemaReader().read(xsdUrl);
		
		final Document doc = SchemaFactory.newInstance().newSchemaWriter().getDocument(desc);
		Assert.assertNotNull(doc);
		Assert.assertFalse(
                "The DOM document base URI contains unencoded spaces", doc
                        .getDocumentURI().contains(" "));
		
		// TODO: Has some assert about the document content
	}
	
	public void testWriterSchema_DocumentBaseURIContainingSpaces() throws SchemaException, URISyntaxException, SchemaException, IOException {
        
        final URL xsdUrl = Thread.currentThread().getContextClassLoader().getResource(Environement.SampleWithSpaceInItsName.XSD);
        Assert.assertNotNull(
                "XMLSchema '" + Environement.SampleWithSpaceInItsName.XSD + "' not found in the classpath.", xsdUrl);
        final Schema desc = SchemaFactory.newInstance().newSchemaReader().read(xsdUrl);
        
        final Document doc = SchemaFactory.newInstance().newSchemaWriter().getDocument(desc);
        Assert.assertNotNull(doc);
        Assert.assertFalse(
                "The DOM document base URI contains unencoded spaces", doc
                        .getDocumentURI().contains(" "));
    }
    
    public void testWriterSchema_OutputStream() throws SchemaException, URISyntaxException, SchemaException, IOException {
        
        final URL xsdUrl = Thread.currentThread().getContextClassLoader().getResource(Environement.SAMPLE_XSD);
        Assert.assertNotNull(
                "XMLSchema '" + Environement.SAMPLE_XSD + "' not found in the classpath.", xsdUrl);
        final Schema desc = SchemaFactory.newInstance().newSchemaReader().read(xsdUrl);
        
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        SchemaFactory.newInstance().newSchemaWriter().writeSchema(desc, baos);
        final String xsd = baos.toString();
        Assert.assertTrue("Nothing has been written.", xsd.length() > 0);
        Assert.assertTrue("Not enough bytes written.", xsd.length() > 3000);
    }
}
