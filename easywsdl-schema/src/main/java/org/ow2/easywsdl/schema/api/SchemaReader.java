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
 
package org.ow2.easywsdl.schema.api;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

/**
 * This interface describes a collection of methods that enable conversion of a
 * SchemaImpl document (in XML, following the SchemaImpl parent described in the
 * SchemaImpl specification) into a SchemaImpl model.
 * 
 * @author Nicolas Salatge - EBM WebSourcing
 */
public interface SchemaReader {

    /**
     * Constants for the Message Exchange Patterns.
     * 
     */
    public enum FeatureConstants {
        VERBOSE("org.ow2.easywsdl.schema.verbose"), IMPORT_DOCUMENTS(
                "org.ow2.easywsdl.schema.importDocuments");

        private final String value;

        /**
         * Creates a new instance of {@link FeatureConstants}
         * 
         * @param value
         */
        private FeatureConstants(final String value) {
            this.value = value;
        }

        /**
         * 
         * @return
         */
        public String value() {
            return this.value;
        }

        /**
         * Please use this equals method instead of using :<code>
         * value().equals(value)
         * </code> which is
         * almost 10 times slower...
         * 
         * @param val
         * @return
         */
        public boolean equals(final String val) {
            return this.toString().equals(val);
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.lang.Enum#toString()
         */
        @Override
        public String toString() {
            return this.value;
        }
    }

    /**
     * <p>
     * Sets the specified feature to the specified value.
     * </p>
     * <p>
     * The minimum features that must be supported are:
     * <p>
     * <table border=1>
     * <caption>Minimal features to support</caption>
     * <tr>
     * <th>Name</th>
     * <th>Description</th>
     * <th>Default Value</th>
     * </tr>
     * <tr>
     * <td>org.ow2.easywsdl.schema.verbose</td>
     * <td>If set to true, status messages will be displayed.</td>
     * <td>type: boolean - default value: false</td>
     * </tr>
     * <tr>
     * <td>org.ow2.easywsdl.schema.importDocuments</td>
     * <td>If set to true, imported WSDL documents will be retrieved and processed.</td>
     * <td>type: boolean - default value: true</td>
     * </tr>
     * <tr>
     * <td>org.ow2.easywsdl.schema.pathDirectoryOfImportLocations</td>
     * <td>If the location is set, imported WSDL documents will be retrieved at this location (Set the importDocuments
     * Features at true).</td>
     * <td>type: String</td>
     * </tr>
     * </table>
     * <p>
     * All feature names must be fully-qualified, Java package style. All names starting with com.ebmwebsourcing. are
     * reserved for features defined by the specification. It is recommended that implementation-specific features be
     * fully-qualified to match the package name of that implementation. For example: com.abc.featureName
     * </p>
     * 
     * @param name
     *            the name of the feature to be set.
     * @param value
     *            the value to set the feature to.
     * @throws IllegalArgumentException
     *             if the feature name is not recognized.
     * @see #getFeature(FeatureConstants)
     */
    void setFeature(FeatureConstants name, Object value) throws IllegalArgumentException;

    /**
     * Set the specified features. See
     * {@link #setFeature(FeatureConstants, Object)} for more information on
     * available features.
     * 
     * @param features
     *            Set of features to set.
     */
    void setFeatures(final Map<FeatureConstants, Object> features);

    /**
     * Gets the value of the specified feature.
     * 
     * @param name
     *            the name of the feature to get the value of.
     * @return the value of feature
     * @throws IllegalArgumentException
     *             if the feature name is not recognized.
     * @see #setFeature(FeatureConstants, Object)
     */
    Object getFeature(FeatureConstants name);

    /**
     * Gets all features.
     * 
     * @return the features
     * @see #setFeature(FeatureConstants, Object)
     */
    Map<FeatureConstants, Object> getFeatures();

    /**
     * <p>
     * Read the XMLSchema definition available at the location identified by the
     * specified URL, and bind it into a {@link Schema} object.
     * </p>
     * <p>
     * <b>Note</b>: all relative URIs are resolved according to the specified
     * URL.
     * </p>
     * 
     * @param schemaURL
     *            an URL pointing to a XMLSchema definition.
     * @return the {@link Schema} definition.
     * @throws SchemaException
     *             An error occurs during the parsing or the binding of the
     *             XMLSchema
     * @throws URISyntaxException
     *             If the URL is not formatted strictly according to to RFC2396
     *             and cannot be converted to a URI.
     * @throws IOException
     *             An I/O error occurs openning the URL stream.
     */
	Schema read(final URL schemaURL) throws SchemaException, URISyntaxException, IOException;

	/**
     * <p>
     * Read the XMLSchema definition accessible via the specified DOM
     * {@link Document}, and bind it into a {@link Schema} object.
     * </p>
     * <p>
     * <b>Note</b>: To be able to resolve relative URIs, the {@link Document}
     * base URI must be set.
     * </p>
     * 
     * @param document
     *            a DOM {@link Document} pointing to a XMLSchema definition.
     * @return the {@link Schema} definition.
     * @throws SchemaException
     *             An error occurs during the parsing or the binding of the
     *             XMLSchema
     */
	Schema read(final Document document) throws SchemaException;

    /**
     * <p>
     * Read the XMLSchema definition accessible via the specified
     * {@link InputSource}, and bind it into a {@link Schema} object.
     * </p>
     * <p>
     * <b>Note</b>: To be able to resolve relative URIs, the {@link InputSource}
     * system identifier must be set.
     * </p>
     * 
     * @param inputSource
     *            an {@link InputSource} pointing to a XMLSchema definition.
     * @return the {@link Schema} definition.
     * @throws SchemaException
     *             An error occurs during the parsing or the binding of the
     *             XMLSchema
     * @throws URISyntaxException
     *             If the system identifier URL is not formatted strictly
     *             according to to RFC2396 and cannot be converted to a URI.
     * @throws MalformedURLException
     *             The system identifier URL is not a well-formed URL
     */
    Schema read(final InputSource inputSource)
            throws SchemaException, URISyntaxException, MalformedURLException;

    /**
     * Set the document base URI of the reader to be able to resolve imported
     * parts.
     * 
     * @param documentBaseURI
     */
    void setDocumentBaseURI(final URI documentBaseURI);

    /**
     * Get the document base URI of the reader.
     * 
     * @return The document base URI.
     */
    URI getDocumentBaseURI();
}
