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

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import org.ow2.easywsdl.schema.api.SchemaException;
import org.ow2.easywsdl.schema.api.SchemaReader.FeatureConstants;
import org.ow2.easywsdl.schema.api.absItf.AbsItfImport;
import org.ow2.easywsdl.schema.api.absItf.AbsItfSchema;

/**
 * @author Nicolas Salatge - EBM WebSourcing
 */
public abstract class AbstractImportImpl<E, S extends AbsItfSchema> extends AbstractIncludeImpl<E, S> implements AbsItfImport<S> {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor
	 * 
	 * @param model
	 *            the model
     * @throws URISyntaxException
     *             If the schemaLocation attribute of the import is not a valid
     *             URI.
	 */
	public AbstractImportImpl(final E model, final Map<FeatureConstants, Object> features, final Map<URI, AbsItfSchema> imports, final URI baseURI, AbstractSchemaReader reader) throws SchemaException, URISyntaxException {
		super(model, features, imports, baseURI, reader);
	}

	/**
	 * Default constructor
	 * 
	 * @param model
	 *            the model
     * @throws URISyntaxException
     *             If the schemaLocation attribute of the import is not a valid
     *             URI.
	 */
	public AbstractImportImpl(final E model, final AbstractSchemaElementImpl parent, final Map<URI, AbsItfSchema> imports, final URI baseURI, final AbstractSchemaReader reader) throws SchemaException, URISyntaxException {
		super(model, parent, imports, baseURI, reader);
	}

}
