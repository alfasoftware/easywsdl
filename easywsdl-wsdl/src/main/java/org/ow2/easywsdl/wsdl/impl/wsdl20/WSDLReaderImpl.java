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

package org.ow2.easywsdl.wsdl.impl.wsdl20;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;
import java.util.logging.Logger;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

import org.ow2.easywsdl.schema.api.absItf.AbsItfSchema;
import org.ow2.easywsdl.schema.util.EasyXMLFilter;
import org.ow2.easywsdl.wsdl.api.Description;
import org.ow2.easywsdl.wsdl.api.WSDLException;
import org.ow2.easywsdl.wsdl.api.WSDLImportException;
import org.ow2.easywsdl.wsdl.api.abstractElmt.AbstractWSDLReaderImpl;
import org.ow2.easywsdl.wsdl.api.abstractItf.AbsItfDescription;
import org.ow2.easywsdl.wsdl.org.w3.ns.wsdl.DescriptionType;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import com.ebmwebsourcing.easycommons.xml.Transformers;

/**
 * @author Nicolas Salatge - EBM WebSourcing
 */
public class WSDLReaderImpl extends AbstractWSDLReaderImpl implements org.ow2.easywsdl.wsdl.api.WSDLReader {

	private static final Logger LOG = Logger.getLogger(WSDLReaderImpl.class.getName());

	/*
	 * Private object initializations
	 */
	public WSDLReaderImpl() throws WSDLException {
		super();
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
  public Description read(final URL wsdlURL) throws WSDLException, IOException, URISyntaxException {
        InputStream is = wsdlURL.openStream();
		try {
			InputSource inputSource = new InputSource(is);
			inputSource.setSystemId(wsdlURL.toString());

			return this.read(inputSource);
		} catch (final MalformedURLException e) {
			throw new RuntimeException("The provided well-formed URL has been detected as malformed !!");
		} finally {
		    if (is != null) is.close();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
  public Description read(final InputSource inputSource) throws WSDLException, MalformedURLException, URISyntaxException {
		final String systemId = inputSource.getSystemId();
		if (systemId != null ) {
			this.setDocumentBaseURI(new URI(inputSource.getSystemId()));
		}
		return this.read(inputSource, null, null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
  public Description read(final Document doc) throws WSDLException, URISyntaxException {
        // The DOM Document needs to be converted into an InputStource
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final StreamResult streamResult = new StreamResult(baos);

        // FIXME: The Transformer creation is not thread-safe
        Transformer transformer = null;
        try {
            transformer = Transformers.takeTransformer();
            transformer.transform(new DOMSource(doc), streamResult);
            baos.flush();
            baos.close();

            final InputSource documentInputSource = new InputSource(new ByteArrayInputStream(
                    baos.toByteArray()));
            documentInputSource.setSystemId(doc.getBaseURI());

            return this.read(documentInputSource);

        } catch (final TransformerException e) {
            throw new WSDLException(e);
        } catch (final IOException e) {
            throw new WSDLException(e);
        } finally {
            if (transformer != null) {
                Transformers.releaseTransformer(transformer);
            }
        }
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
  public Description read(InputSource inputSource, Map<URI, AbsItfDescription> descriptionImports, Map<URI, AbsItfSchema> schemaImports) throws WSDLException, MalformedURLException, URISyntaxException {
		final String systemId = inputSource.getSystemId();
		URI systemIdURI;
		if (systemId != null ) {
			systemIdURI = new URI(systemId);
			this.setDocumentBaseURI(systemIdURI);
		} else {
			systemIdURI = new File(".").toURI();
		}

		Description desc = null;
		try {
            Unmarshaller unmarshaller = WSDLJAXBContext.getJaxbContext().createUnmarshaller();
			LOG.fine("Loading " + systemIdURI);
			XMLReader xmlReader = XMLReaderFactory.createXMLReader();
			EasyXMLFilter filter = new EasyXMLFilter(xmlReader);
			SAXSource saxSource = new SAXSource(filter, inputSource);

			// TODO use SAX validation instead of JAXB validation
			// turn off the JAXB provider's default validation mechanism to
			// avoid duplicate validation
			// SchemaReaderImpl.getUnMarshaller().setValidating( false );

			final JAXBElement<DescriptionType> wsdlBinding = unmarshaller.unmarshal(saxSource, DescriptionType.class);

			DescriptionType def = wsdlBinding.getValue();

			desc = new org.ow2.easywsdl.wsdl.impl.wsdl20.DescriptionImpl(systemIdURI, def, filter.getNamespaceMapper(), filter.getSchemaLocator(), this.getFeatures(), descriptionImports, schemaImports, this);

		} catch (JAXBException e) {
			throw new WSDLException("Can not get wsdl at: " + systemIdURI.toString(), e);
		} catch (SAXException e) {
			throw new WSDLException("Can not get wsdl at: " + systemIdURI.toString(), e);
		} catch (WSDLImportException e) {
			throw new WSDLException("Can not get wsdl at: " + systemIdURI.toString(), e);
		} catch (NumberFormatException e) {
			throw new WSDLException("Can not get wsdl at: " + systemIdURI.toString() + " WSDL too large !", e);
		}

		return desc;
	}



}
