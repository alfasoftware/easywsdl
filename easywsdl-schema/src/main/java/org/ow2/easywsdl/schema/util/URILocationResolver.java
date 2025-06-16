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

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

/**
 * URI resolver used to resolve URI as URL, based on a base URI, to load
 * external document.
 * 
 * @author Christophe DENEUX - Capgemini Sud
 */
public final class URILocationResolver {

    /**
     * <p>
     * Resolve the <code>uri</code> according to the <code>baseURI</code>.
     * </p>
     * <p>
     * If <code>baseURI</code> is <code>null</code>, the return value is the
     * <code>uri</code> converted into {@link URL} using {@link URI#toURL()}.
     * Otherwise the return value is the result of {@link URL#URL(URL, String)}.
     * </p>
     * 
     * @param baseURI
     *            The base {@link URI}.
     * @param uri
     *            The {@link URI} to resolve.
     * @return The resolved URI as an {@link URL}.
     * @throws MalformedURLException
     *             The resolved URI is not a well-formed URL.
     */
    public URL resolve(final URI baseURI, final URI uri) throws MalformedURLException {
        if (baseURI == null) {
            // No base URI defined, we return the uri as URL
            return uri.toURL();
        } else {
            // We try to resolve the uri according to the baseURI
            // Note: Usage of URL(URL, String) is needed to handle correctly
            // baseURI based on a JAR URL
            return new URL(baseURI.toURL(), uri.toString());
        }
    }
}
