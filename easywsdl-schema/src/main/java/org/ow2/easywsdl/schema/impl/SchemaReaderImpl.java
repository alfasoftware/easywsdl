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

package org.ow2.easywsdl.schema.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.JAXBException;

import org.ow2.easywsdl.schema.api.Schema;
import org.ow2.easywsdl.schema.api.SchemaException;
import org.ow2.easywsdl.schema.api.absItf.AbsItfSchema;
import org.ow2.easywsdl.schema.api.abstractElmt.AbstractSchemaReader;
import org.ow2.easywsdl.schema.util.EasyXMLFilter;
import org.ow2.easywsdl.schema.util.URILocationResolver;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import com.ebmwebsourcing.easycommons.xml.Transformers;

/**
 * @author Nicolas Salatge - EBM WebSourcing
 * @author Gael Blondelle - EBM WebSourcing
 */
public class SchemaReaderImpl extends AbstractSchemaReader implements org.ow2.easywsdl.schema.api.SchemaReader {

	private static final Logger LOG = Logger.getLogger(SchemaReaderImpl.class.getName());

	private Map<FeatureConstants, Object> features = new HashMap<>();

	private final Map<URI, AbsItfSchema> importList = new HashMap<>();

    private final URILocationResolver schemaLocationResolver = new URILocationResolver();

    private URI documentBaseURI;

	/*
	 * Private object initializations
	 */
	public SchemaReaderImpl() throws SchemaException {
		// just to validate that it works
		SchemaJAXBContext.getInstance();
		this.features.put(FeatureConstants.VERBOSE, false);
		this.features.put(FeatureConstants.IMPORT_DOCUMENTS, true);
	}

	/**
     * {@inheritDoc}
     */
    @Override
    public void setDocumentBaseURI(final URI documentBaseURI) {
	    this.documentBaseURI = documentBaseURI;
	}

    /**
     * {@inheritDoc}
     */
    @Override
    public URI getDocumentBaseURI() {
        return this.documentBaseURI;
    }

	/**
	 * {@inheritDoc}
	 */
	@Override
  public Schema read(final URL schemaURL) throws SchemaException, URISyntaxException, IOException {
        InputStream stream = schemaURL.openStream();
	    try {
            final InputSource inputSource = new InputSource(stream);
            inputSource.setSystemId(schemaURL.toString());
            Schema schema = this.read(inputSource);
            return schema;
        } catch (final MalformedURLException e) {
            throw new SchemaException("The provided well-formed URL has been detected as malformed !!", e);
        } finally {
            if (stream != null) stream.close();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema read(final InputSource inputSource)
            throws SchemaException, URISyntaxException, MalformedURLException {
    	if(inputSource.getSystemId() != null) {
    		this.documentBaseURI = new URI(inputSource.getSystemId());
    	} else {
    		this.documentBaseURI = new URI(".");
    	}
        return this.readSchema(inputSource, new HashMap<>());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema read(final Document doc) throws SchemaException {
        // The DOM Document needs to be converted into an InputStream
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final StreamResult streamResult = new StreamResult(baos);

        // FIXME: The Transformer creation is not thread-safe
        Transformer transformer = null;
        try {
            transformer = Transformers.takeTransformer();
            transformer.transform(new DOMSource(doc), streamResult);
            baos.flush();
            baos.close();

            final InputSource documentInputSource = new InputSource(
                    new ByteArrayInputStream(baos.toByteArray()));
            documentInputSource.setSystemId(doc.getBaseURI());

            return this.read(documentInputSource);
        } catch (final TransformerException e) {
            throw new SchemaException(e);
        } catch (final IOException e) {
            throw new SchemaException(e);
        } catch (final URISyntaxException e) {
            throw new SchemaException(e);
        } finally {
            if(transformer != null) {
                Transformers.releaseTransformer(transformer);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
	protected Schema readExternalPart(final URI schemaLocationURI, final URI baseURI, Map<URI, AbsItfSchema> imports) throws SchemaException, MalformedURLException, URISyntaxException {
        InputStream is = null;
		try {
	        is = this.schemaLocationResolver.resolve(baseURI, schemaLocationURI).openStream();
	        InputSource inputSource = new InputSource(is);
			inputSource.setSystemId(this.schemaLocationResolver.resolve(baseURI, schemaLocationURI).toString());
	        Schema schema = this.readSchema(inputSource, imports, false);
	        return schema;
		} catch (IOException e) {
			throw new SchemaException(e);
		} finally {
		    try {
		        if (is != null) is.close();
		    } catch (IOException ioe) {
		        throw new RuntimeException(ioe);
		    }
		}
	}


    protected Schema readSchema(final InputSource source, Map<URI, AbsItfSchema> imports) throws SchemaException, MalformedURLException, URISyntaxException {
    	return readSchema(source, imports, true);
    }


	/**
     * @throws MalformedURLException
     *             The {@link InputSource} systemId is a malformed URL.
     * @throws URISyntaxException
     *             The {@link InputSource} systemId is an URL not formatted
     *             strictly according to to RFC2396 and cannot be converted to a
     *             URI.
     */
    private Schema readSchema(final InputSource source, Map<URI, AbsItfSchema> imports, boolean init) throws SchemaException, MalformedURLException, URISyntaxException {
		try {
	        final String systemId = source.getSystemId();
	        URI systemIdURI;
	        if (systemId != null ) {
	            systemIdURI = new URI(systemId);
	            this.setDocumentBaseURI(systemIdURI);
	        } else {
	            systemIdURI = new File(".").toURI();
	        }
			final XMLReader xmlReader = XMLReaderFactory.createXMLReader();
			final EasyXMLFilter filter = new EasyXMLFilter(xmlReader);
			final SAXSource saxSource = new SAXSource(filter, source);

			// TODO use SAX validation instead of JAXB validation
			// turn off the JAXB provider's default validation mechanism to
			// avoid duplicate validation
			// SchemaReaderImpl.getUnMarshaller().setValidating( false );
			LOG.fine("Loading " + systemIdURI);


			// unmarshal
            final JAXBElement<org.ow2.easywsdl.schema.org.w3._2001.xmlschema.Schema> schemaBinding = SchemaJAXBContext
                    .getInstance().getJaxbContext().createUnmarshaller()
                    .unmarshal(saxSource, org.ow2.easywsdl.schema.org.w3._2001.xmlschema.Schema.class);

			final org.ow2.easywsdl.schema.org.w3._2001.xmlschema.Schema def = schemaBinding.getValue();

			SchemaImpl schema = new org.ow2.easywsdl.schema.impl.SchemaImpl(systemIdURI, def, filter.getNamespaceMapper(), filter.getSchemaLocator(), this.getFeatures(), imports, this);
			if (init) schema.initialize();
			return schema;
		} catch (SAXException e) {
			throw new SchemaException("Can not get schema: ", e);
		} catch (JAXBException e) {
			throw new SchemaException("Can not get schema: ", e);
		}
	}

	@Override
  public void setFeature(final FeatureConstants name, final Object value)
	    throws IllegalArgumentException {
		this.features.put(name, value);
		SchemaReaderImpl.LOG.finest("set features: name = " + name + " - value = " + value);
	}

	@Override
  public Object getFeature(final FeatureConstants name) throws IllegalArgumentException {
		return this.features.get(name);
	}

	@Override
  public Map<FeatureConstants, Object> getFeatures() {
		return this.features;
	}

	@Override
  public void setFeatures(final Map<FeatureConstants, Object> features) {
		this.features = features;
	}


}
