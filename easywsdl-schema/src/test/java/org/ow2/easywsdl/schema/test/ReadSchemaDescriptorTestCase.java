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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.Assert;
import org.ow2.easywsdl.schema.SchemaFactory;
import org.ow2.easywsdl.schema.api.ComplexType;
import org.ow2.easywsdl.schema.api.Element;
import org.ow2.easywsdl.schema.api.Import;
import org.ow2.easywsdl.schema.api.Schema;
import org.ow2.easywsdl.schema.api.SchemaException;
import org.ow2.easywsdl.schema.api.SchemaReader;
import org.ow2.easywsdl.schema.api.Sequence;
import org.ow2.easywsdl.schema.api.SimpleType;
import org.ow2.easywsdl.schema.api.Type;
import org.ow2.easywsdl.schema.api.absItf.AbsItfEnumeration;
import org.ow2.easywsdl.schema.api.absItf.AbsItfSimpleType;
import org.ow2.easywsdl.schema.api.abstractElmt.AbstractSchemaElementImpl;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import junit.framework.TestCase;

/**
 * @author Nicolas Salatge - EBM WebSourcing
 */
public class ReadSchemaDescriptorTestCase extends TestCase {

    /**
     * <p>
     * Test {@link SchemaReader#read(URL)} using a file URL provided by the classloader.
     * </p>
     * <p>
     * The read XMLSchema is {@link Environement#SAMPLE_XSD}.
     * </p>
     */
    public void testReaderSchema_FromURL() throws URISyntaxException, SchemaException,
            IOException {

	    final URL xsdUrl = Thread.currentThread().getContextClassLoader().getResource(Environement.SAMPLE_XSD);
        Assert.assertNotNull(
                "XMLSchema '" + Environement.SAMPLE_XSD + "' not found in the classpath.", xsdUrl);
        final Schema schema = SchemaFactory.newInstance().newSchemaReader().read(xsdUrl);
        
        this.assertSampleXSD(schema);

	}

    /**
     * <p>
     * Test {@link SchemaReader#read(Document)} using a DOM Document loaded from a stream provided by the classloader.
     * </p>
     * <p>
     * The read XMLSchema is {@link Environement#SAMPLE_XSD}.
     * </p>
     */
    public void testReaderSchema_FromDocument() throws URISyntaxException, SchemaException,
            IOException, SAXException, ParserConfigurationException {

        final InputStream xsdInputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(Environement.SAMPLE_XSD);
        Assert.assertNotNull(
                "XMLSchema '" + Environement.SAMPLE_XSD + "' not found in the classpath.", xsdInputStream);
        
        final Document xsdDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(xsdInputStream);
        final URL xsdUrl = Thread.currentThread().getContextClassLoader().getResource(Environement.SAMPLE_XSD);
        Assert.assertNotNull(
                "XMLSchema '" + Environement.SAMPLE_XSD + "' not found in the classpath.", xsdUrl);
        xsdDocument.setDocumentURI(xsdUrl.toString());
        final Schema schema = SchemaFactory.newInstance().newSchemaReader().read(xsdDocument);

        this.assertSampleXSD(schema);

    }

    /**
     * <p>
     * Test {@link SchemaReader#read(InputSource)} using a stream provided by the classloader.
     * </p>
     * <p>
     * The read XMLSchema is {@link Environement#SAMPLE_XSD}.
     * </p>
     */
    public void testReaderSchema_FromInputSource() throws URISyntaxException, SchemaException,
            IOException {
        
        final InputStream xsdInputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(Environement.SAMPLE_XSD);
        Assert.assertNotNull(
                "XMLSchema '" + Environement.SAMPLE_XSD + "' not found in the classpath.", xsdInputStream);
        final URL xsdUrl = Thread.currentThread().getContextClassLoader().getResource(Environement.SAMPLE_XSD);
        Assert.assertNotNull(
                "XMLSchema '" + Environement.SAMPLE_XSD + "' not found in the classpath.", xsdUrl);
        final InputSource xsdInputSource = new InputSource(xsdInputStream);
        xsdInputSource.setSystemId(xsdUrl.toString());
        final Schema schema = SchemaFactory.newInstance().newSchemaReader().read(xsdInputSource);
        
        this.assertSampleXSD(schema);

    }

    /**
     * <p>
     * Test {@link SchemaReader#read(URL)} to read a WSDL resource through the classloader and available inside a JAR.
     * This WSDL resource needs imports available in the same JAR.
     * </p>
     * <p>
     * The read XMLSchema is an on-the-fly copy of {@link Environement#RESERVATIONDETAILS_XSD}.
     * </p>
     */
    public void testReaderSchemaWithImport_FromJarURL() throws URISyntaxException, SchemaException,
            IOException {

        // First we create the JAR file containing all needed files associated
        // to Environement.RESERVATIONLIST_XSD: descriptors/reservationList.xsd,
        // descriptor/reservationDetails.xsd, descriptors/xwdl20-instance.xsd
        
        // We need to change the context Environement.DESCRIPTOR_CTX to
        // Environement.DESCRIPTOR_IN_JAR_CTX to be sure that the WSDL will be
        // read from the JAR file.
        
        final File jarFile = File.createTempFile("testReaderSchemaWithImport_FromJarURL", ".jar");
        final JarOutputStream jarOutputStream = new JarOutputStream(new FileOutputStream(jarFile));
        final String[] filesToJar = new String[] {
                Environement.RESERVATIONLIST_XSD,
                Environement.RESERVATIONDETAILS_XSD,
                "descriptors/wsdl20-instance.xsd"};
        final ClassLoader currentClassloader = Thread.currentThread().getContextClassLoader();
        for (final String fileToJar : filesToJar) {
            final String entryName = fileToJar.replace(Environement.DESCRIPTOR_CTX, Environement.DESCRIPTOR_IN_JAR_CTX);
            final JarEntry jarEntry = new JarEntry(entryName);
            jarOutputStream.putNextEntry(jarEntry);
            
            final InputStream entryInputStream = currentClassloader.getResourceAsStream(fileToJar);
            Assert.assertNotNull("File to jar not found.", entryInputStream);
            int nRead;
            byte[] buffer = new byte[1024];
            do {
                nRead = entryInputStream.read(buffer);
                if (nRead > 0) {
                    jarOutputStream.write(buffer, 0, nRead);
                }
            } while (nRead != -1);
            entryInputStream.close();
            jarOutputStream.closeEntry();
        }
        jarOutputStream.close();

        // To be able to read a resource inside the JAR, we need to add it to the
        // classloader
        final ClassLoader newClassloader = new URLClassLoader(new URL[] { jarFile.toURI().toURL() }, currentClassloader);
        Thread.currentThread().setContextClassLoader(newClassloader);
        
        try {
            final URL xsdUrl = Thread.currentThread().getContextClassLoader().getResource(Environement.RESERVATIONLIST_XSD_IN_JAR);
            Assert.assertNotNull(
                    "XMLSchema '" + Environement.RESERVATIONLIST_XSD_IN_JAR + "' not found in the classpath.", xsdUrl);
            final Schema schema = SchemaFactory.newInstance().newSchemaReader().read(xsdUrl);

            this.assertReservationListXSD(schema);
            
            jarFile.delete();
        } finally {
            // We restore the classloader
            Thread.currentThread().setContextClassLoader(currentClassloader);
        }

    }

    /**
     * <p>
     * Test {@link SchemaReader#read(URL)} against usage of element declared as referenced from element not imported
     * (using the file URL provided by the classloader.
     * </p>
     * <p>
     * The read XMLSchema is {@link Environement.EltRefWithoutImport#XSD}.
     * </p>
     */
    public void testReaderSchema_EltRefWithoutImport_FromURL() throws URISyntaxException, SchemaException,
            IOException {

        final URL xsdUrl = Thread.currentThread().getContextClassLoader().getResource(Environement.EltRefWithoutImport.XSD);
        Assert.assertNotNull(
                "XMLSchema '" + Environement.EltRefWithoutImport.XSD + "' not found in the classpath.", xsdUrl);
        final Schema schema = SchemaFactory.newInstance().newSchemaReader().read(xsdUrl);

        Assert.assertNotNull(schema);
        final Element myElt = schema.getElement(Environement.EltRefWithoutImport.MYELT_ELT);
        Assert.assertNotNull("The element " + Environement.EltRefWithoutImport.MYELT_ELT.toString()
                + " is missing in XSD '" + Environement.EltRefWithoutImport.XSD + "'.",
                myElt);
        Assert.assertEquals(Environement.EltRefWithoutImport.MYELT_ELT, myElt.getQName());
        final Type myEltType = myElt.getType();
        Assert.assertNotNull("The element " + Environement.EltRefWithoutImport.MYELT_ELT.toString()
                + " has no type in XSD '" + Environement.EltRefWithoutImport.XSD + "'.", myEltType);
        Assert.assertTrue("The type of the element " + Environement.EltRefWithoutImport.MYELT_ELT.toString()
                + " is not a complex type.", myEltType instanceof ComplexType);
        final ComplexType myEltCplType = (ComplexType)myEltType;
        final Sequence myEltSeq = myEltCplType.getSequence();
        Assert.assertNotNull("The complex type of the element " + Environement.EltRefWithoutImport.MYELT_ELT.toString()
                + " does not contain a sequence.", myEltSeq);
        final List<Element> myEltSeqElts = myEltSeq.getElements();
        Assert.assertNotNull("The sequence of the complex type of the element " + Environement.EltRefWithoutImport.MYELT_ELT.toString()
                + " does not contain element.", myEltSeqElts);
        Assert.assertEquals(
                "The expected element number isn't correct in the sequence of the complex type of the element " + Environement.EltRefWithoutImport.MYELT_ELT.toString() + "'.",
                1, myEltSeqElts.size());
        Assert.assertEquals(
                "The expected element isn't the correct one in the sequence of the complex type of the element " + Environement.EltRefWithoutImport.MYELT_ELT.toString() + "'.",
                Environement.EltRefWithoutImport.SCHEMA_ELT, myEltSeqElts.get(0).getQName());
    }

    /**
     * Assert to verify the content of the {@link Schema} associated to the
     * XMLSchema {@link Environement#SAMPLE_XSD}.
     * 
     * @param schema
     *            The {@link Schema} to verify.
     */
    private void assertSampleXSD(final Schema schema) {
        // assert on parent
        Assert.assertNotNull(schema);
        
        // assert on types
        Assert.assertEquals(5, schema.getTypes().size());
        Assert.assertEquals("PurchaseOrderType", schema.getTypes().get(0).getQName().getLocalPart());
        Assert.assertEquals("USAddress", schema.getTypes().get(1).getQName().getLocalPart());
        Assert.assertEquals("Items", schema.getTypes().get(2).getQName().getLocalPart());
        Assert.assertEquals("SKU", schema.getTypes().get(3).getQName().getLocalPart());
        Assert.assertEquals("string", ((AbsItfSimpleType)schema.getTypes().get(3)).getRestriction().getBase().getLocalPart());
        Assert.assertEquals("123-AB", ((AbsItfEnumeration)((AbsItfSimpleType)schema.getTypes().get(3)).getRestriction().getEnumerations().get(1)).getValue());
        Assert.assertEquals("PurchaseAllOrderType", schema.getTypes().get(4).getQName().getLocalPart());
        
        // Assert on sequence
        ComplexType seqType = (ComplexType)schema.getTypes().get(0);
        Assert.assertEquals(4,seqType.getSequence().getElements().size());
        
        // Assert on all
        ComplexType allType = (ComplexType)schema.getTypes().get(4);
        Assert.assertEquals(4,allType.getAll().getElements().size());
        
        // assert on elements
        Assert.assertEquals(2, schema.getElements().size());
        Assert.assertEquals("purchaseOrder", schema.getElements().get(0).getQName().getLocalPart());
        Assert.assertEquals("comment", schema.getElements().get(1).getQName().getLocalPart());
    }
    
    /**
     * <p>
     * Test {@link SchemaReader#read(URL)} using a file URL provided by the classloader and containing imports.
     * </p>
     * <p>
     * The read XMLSchema is {@link Environement#RESERVATIONLIST_XSD}.
     * </p>
     */
    public void testImportSchema_FromURL() throws URISyntaxException, SchemaException,
            IOException {

	    final URL xsdUrl = Thread.currentThread().getContextClassLoader().getResource(Environement.RESERVATIONLIST_XSD);
        Assert.assertNotNull(
                "XMLSchema '" + Environement.RESERVATIONLIST_XSD + "' not found in the classpath.", xsdUrl);
		final Schema schema = SchemaFactory.newInstance().newSchemaReader().read(xsdUrl);

        this.assertReservationListXSD(schema);

	}
    
    /**
     * <p>
     * Test {@link SchemaReader#read(InputSource)} using a stream provided by the classloader and containing imports.
     * </p>
     * <p>
     * The read XMLSchema is {@link Environement#RESERVATIONLIST_XSD}.
     * </p>
     */
    public void testImportSchema_FromInputSource() throws URISyntaxException, SchemaException,
            IOException {

        final InputStream xsdInputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(Environement.RESERVATIONLIST_XSD);
        Assert.assertNotNull(
                "XMLSchema '" + Environement.RESERVATIONLIST_XSD + "' not found in the classpath.", xsdInputStream);
        final URL xsdUrl = Thread.currentThread().getContextClassLoader().getResource(Environement.RESERVATIONLIST_XSD);
        Assert.assertNotNull(
                "XMLSchema '" + Environement.RESERVATIONLIST_XSD + "' not found in the classpath.", xsdUrl);
        final InputSource xsdInputSource = new InputSource(xsdInputStream);
        xsdInputSource.setSystemId(xsdUrl.toString());
        final Schema schema = SchemaFactory.newInstance().newSchemaReader().read(xsdInputSource);

        this.assertReservationListXSD(schema);

    }
    
    /**
     * Assert to verify the content of the {@link Schema} associated to the
     * XMLSchema {@link Environement#RESERVATIONLIST_XSD}.
     * 
     * @param schema
     *            The {@link Schema} to verify.
     */
    private void assertReservationListXSD(final Schema schema) {
        // assert on parent
        Assert.assertNotNull("The description returned is null.", schema);

        // In the wide description
        Assert.assertEquals(
                "The expected import number isn't correct in the XSD '" + Environement.RESERVATIONLIST_XSD + "'.",
                2, schema.getImports().size());
        Assert.assertEquals(
                "The expected type number isn't correct in the XSD '" + Environement.RESERVATIONLIST_XSD + "'.",
                1, schema.getTypes().size());
        Assert.assertEquals(
                "The expected element number isn't correct in the XSD '" + Environement.RESERVATIONLIST_XSD + "'.",
                8, schema.getElements().size());
        Assert.assertEquals(
                "The expected attribute number isn't correct in the XSD '" + Environement.RESERVATIONLIST_XSD + "'.",
                1, schema.getAttributes().size());
        
        // In the import 
        final List<Import> importsReservationDetails = schema.getImports(Environement.RESERVATIONDETAILS_NAMESPACE);
        Assert.assertEquals(1, importsReservationDetails.size());
        final Import importReservationDetails = importsReservationDetails.get(0);
        final Schema importedSchemaReservationDetails = importReservationDetails.getSchema();
        Assert.assertNotNull(
                "No schema in import '" + Environement.RESERVATIONDETAILS_NAMESPACE + "'.",
                importedSchemaReservationDetails);
        Assert.assertEquals(
                "The expected type number isn't correct in the imported XSD '" + Environement.RESERVATIONDETAILS_NAMESPACE + "'.",
                1, importedSchemaReservationDetails.getTypes().size());
        Assert.assertEquals(
                "The expected element number isn't correct in the imported XSD '" + Environement.RESERVATIONDETAILS_NAMESPACE + "'.",
                6, importedSchemaReservationDetails.getElements().size());
        
        // Check on element 'reservation' defined in the namespace Environement.RESERVATIONLIST_NAMESPACE
        final Element reservationElt = schema.getElement(Environement.RESERVATIONLIST_RESERVATION_ELT);
        Assert.assertNotNull("The element " + Environement.RESERVATIONLIST_RESERVATION_ELT.toString()
                + " is missing in import '" + Environement.RESERVATIONLIST_NAMESPACE + "'.",
                reservationElt);
        Assert.assertEquals(Environement.RESERVATIONLIST_RESERVATION_ELT, reservationElt.getQName());
        final Type reservationEltType = reservationElt.getType();
        Assert.assertNotNull("The element " + Environement.RESERVATIONLIST_RESERVATION_ELT.toString()
                + " has no type in import '" + Environement.RESERVATIONLIST_NAMESPACE + "'.", reservationEltType);
        Assert.assertTrue("The type of the element " + Environement.RESERVATIONLIST_RESERVATION_ELT.toString()
                + " is not a complex type.", reservationEltType instanceof ComplexType);
        final ComplexType reservationEltCplType = (ComplexType)reservationEltType;
        final Sequence reservationEltSeq = reservationEltCplType.getSequence();
        Assert.assertNotNull("The complex type of the element " + Environement.RESERVATIONLIST_RESERVATION_ELT.toString()
                + " does not contain a sequence.", reservationEltSeq);
        final List<Element> reservationEltSeqElts = reservationEltSeq.getElements();
        Assert.assertNotNull("The sequence of the complex type of the element " + Environement.RESERVATIONLIST_RESERVATION_ELT.toString()
                + " does not contain element.", reservationEltSeqElts);
        Assert.assertEquals(
                "The expected element number isn't correct in the sequence of the complex type of the element " + Environement.RESERVATIONLIST_RESERVATION_ELT.toString() + "'.",
                4, reservationEltSeqElts.size());
        boolean foundConfNumber = false;
        for (final Element reservationEltSeqElt : reservationEltSeqElts) {
            if (reservationEltSeqElt.getQName().equals(Environement.RESERVATIONDETAILS_CONFNUMBER_ELT)) {
                foundConfNumber = true;
                break;
            }
        }
        Assert.assertTrue("The sequence of the complex type of the element " + Environement.RESERVATIONLIST_RESERVATION_ELT.toString()
                + " does not contain element '" + Environement.RESERVATIONDETAILS_CONFNUMBER_ELT + "'.", foundConfNumber);
    }
    
    public void testReaderSchemaEquals() throws URISyntaxException, SchemaException,
    IOException {
		final URL xsdUrl = Thread.currentThread().getContextClassLoader().getResource(Environement.SAMPLE_XSD);
		final URL xsdUrl2 = Thread.currentThread().getContextClassLoader().getResource(Environement.SAMPLE_XSD_EQUALS);
		
		Assert.assertNotNull(
		        "XMLSchema '" + Environement.SAMPLE_XSD + "' not found in the classpath.", xsdUrl);
		final Schema schema1 = SchemaFactory.newInstance().newSchemaReader().read(xsdUrl);
		
		// Test with the same schema
		final Schema schema2 = SchemaFactory.newInstance().newSchemaReader().read(xsdUrl);
		Assert.assertTrue(schema1.equals(schema2));
		
		// Test of any internal element
		Assert.assertTrue(((AbstractSchemaElementImpl)schema1.getElements().get(0)).equals(schema2.getElements().get(0)));
		
		// Test with namespace change
		final Schema schema3 = SchemaFactory.newInstance().newSchemaReader().read(xsdUrl);
		schema3.setTargetNamespace("http://easywsdl.ow2.org/namespace/toto");
		Assert.assertFalse(schema1.equals(schema3));
		
		// Test with new type
		final Schema schema4 = SchemaFactory.newInstance().newSchemaReader().read(xsdUrl);
		SimpleType st = (SimpleType) schema4.createSimpleType();
		st.setQName(new QName("http://namespace","test"));
		schema4.addType(st);
		Assert.assertFalse(schema1.equals(schema4));
		
		// Test with another schema file
		final Schema schema5 = SchemaFactory.newInstance().newSchemaReader().read(xsdUrl2);
		Assert.assertTrue(schema1.equals(schema5));
		
    }
    
}
