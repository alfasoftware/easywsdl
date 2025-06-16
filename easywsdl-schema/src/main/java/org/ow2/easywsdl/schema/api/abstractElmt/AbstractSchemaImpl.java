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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.xml.namespace.QName;

import org.ow2.easywsdl.schema.SchemaFactory;
import org.ow2.easywsdl.schema.api.Redefine;
import org.ow2.easywsdl.schema.api.SchemaException;
import org.ow2.easywsdl.schema.api.SchemaReader.FeatureConstants;
import org.ow2.easywsdl.schema.api.absItf.AbsItfAttribute;
import org.ow2.easywsdl.schema.api.absItf.AbsItfComplexType;
import org.ow2.easywsdl.schema.api.absItf.AbsItfElement;
import org.ow2.easywsdl.schema.api.absItf.AbsItfImport;
import org.ow2.easywsdl.schema.api.absItf.AbsItfInclude;
import org.ow2.easywsdl.schema.api.absItf.AbsItfRedefine;
import org.ow2.easywsdl.schema.api.absItf.AbsItfSchema;
import org.ow2.easywsdl.schema.api.absItf.AbsItfType;
import org.ow2.easywsdl.schema.api.extensions.NamespaceMapperImpl;
import org.ow2.easywsdl.schema.api.extensions.SchemaLocatorImpl;

/**
 * @author Nicolas Salatge - EBM WebSourcing
 */
public abstract class AbstractSchemaImpl<E, T extends AbsItfType, El extends AbsItfElement, A extends AbsItfAttribute, Incl extends AbsItfInclude, Impt extends AbsItfImport, Red extends AbsItfRedefine> extends AbstractSchemaElementImpl<E> implements AbsItfSchema<T, El, A, Incl, Impt, Red> {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = Logger.getLogger(AbstractSchemaImpl.class.getName());

	/**
	 * Features
	 */
	private Map<FeatureConstants, Object> features = new HashMap<FeatureConstants, Object>();

	/**
	 * the namespace context
	 */
	private NamespaceMapperImpl namespaceMapper = new org.ow2.easywsdl.schema.api.extensions.NamespaceMapperImpl();

	/**
	 * list of imports
	 */
	private List<Impt> imports = new ArrayList<Impt>();

	/**
	 * list of includes
	 */
	private List<Incl> includes = new ArrayList<Incl>();
	
	/**
	 * list of redefines
	 */
	private List<Red> redefines = new ArrayList<Red>();

	/**
	 * the list of types
	 */
	private List<T> types = new ArrayList <T>();

	/**
	 * the list of elements
	 */
	private List<El> elements = new ArrayList<El>();

	/**
	 * the list of attributes
	 */
	private List<A> attributes = new ArrayList<A>();

	/**
	 * the baseUri string
	 */
	private String documentBaseURIString;

	private URI documentURI = null;

	public AbstractSchemaImpl() {

	}

	public abstract void initialize();
	
	public AbstractSchemaImpl(URI documentURI, final E schema, final NamespaceMapperImpl namespaceMapper, final SchemaLocatorImpl schemaLocator) {
		super(schema, null);
		this.namespaceMapper = namespaceMapper;
		this.documentURI = documentURI;
	}

	public AbstractSchemaImpl(final String baseURIString) {
		this.documentBaseURIString = baseURIString;
	}

	public void addImportElementsInAllList() {
		for (final Impt impt : this.imports) {
			if (impt.getSchema() != null) {
				this.types.addAll(impt.getSchema().getTypes());
				this.elements.addAll(impt.getSchema().getElements());
				this.attributes.addAll(impt.getSchema().getAttributes());
			}
		}
	}

	public void addIncludeElementsInAllList() {
		for (final Incl incl : this.includes) {
			if (incl.getSchema() != null) {
				this.types.addAll(incl.getSchema().getTypes());
				this.elements.addAll(incl.getSchema().getElements());
				this.attributes.addAll(incl.getSchema().getAttributes());
			}
		}
	}

	public void addIncludeElementsInAllList(AbsItfInclude incl) {
		if (incl.getSchema() != null) {
			this.types.addAll(incl.getSchema().getTypes());
			this.elements.addAll(incl.getSchema().getElements());
			this.attributes.addAll(incl.getSchema().getAttributes());
		}
	}
	
	public void addRedefineElementsInAllList() {
		for (final Red red : this.redefines) {
			if (red.getSchema() != null) {
				this.types.addAll(red.getSchema().getTypes());
				this.elements.addAll(red.getSchema().getElements());
				this.attributes.addAll(red.getSchema().getAttributes());
			}
		}
	}


	/**
	 * ImportImpl operation
	 */
	public void addImport(final Impt impt) {
		if (this.imports == null) {
			this.imports = new ArrayList<Impt>();
		}
		this.imports.add(impt);
	}

	public List<Impt> getImports() {
		if (this.imports == null) {
			this.imports = new ArrayList<Impt>();
		}
		return this.imports;
	}

	public List<Impt> getImports(final String namespaceUri) {
		final List<Impt> res = new ArrayList<Impt>();
		for (final Impt impt : this.imports) {
			if (impt.getNamespaceURI().equals(namespaceUri)) {
				res.add(impt);
			}
		}
		return res;
	}

	/**
	 * IncludeImpl operation
	 * 
	 * @throws SchemaException
	 */
	public void addInclude(final Incl incl) throws SchemaException {
		if (this.includes == null) {
			this.includes = new ArrayList<Incl>();
		}
		this.includes.add(incl);
	}

	public List<Incl> getIncludes() {
		if (this.includes == null) {
			this.includes = new ArrayList<Incl>();
		}
		return this.includes;
	}

	public List<Incl> getIncludes(final URI locationUri) {
		final List<Incl> res = new ArrayList<Incl>();
		for (final Incl incl : this.includes) {
			if (incl.getLocationURI().equals(locationUri)) {
				res.add(incl);
			}
		}
		return res;
	}
	
	
	/**
	 * RedefineImpl operation
	 */
	public void addRedefine(final Red red) {
		if (this.redefines == null) {
			this.redefines = new ArrayList<Red>();
		}
		this.redefines.add(red);
	}

	public List<Red> getRedefines() {
		if (this.redefines == null) {
			this.redefines = new ArrayList<Red>();
		}
		return this.redefines;
	}

	public List<Red> getRedefines(String namespaceURI) {
		final List<Red> res = new ArrayList<Red>();
		for (final Red red : this.redefines) {
			if (red.getLocationURI().toString().equals(namespaceURI)) {
				res.add(red);
			}
		}
		return res;
	}
	

	/*
	 * Types methods
	 */

	/*
	 * (non-Javadoc)
	 * @see org.ow2.easywsdl.schema.api.Schema#getTypes()
	 */
	public List<T> getTypes() {
		if (this.types == null) {
			this.types = new ArrayList<T>();
		}
		return this.types;
	}

	/*
	 * (non-Javadoc)
	 * @see org.ow2.easywsdl.schema.api.Schema#getType(javax.xml.namespace .QName)
	 */
	public T getType(final QName type) {
		T res = null;
		if (this.types == null) {
			this.types = new ArrayList<T>();
		}
		for (final T t : this.types) {
			if (t.getQName().equals(type)) {
				res = t;
				break;
			}
		}

		if ((res == null) && (SchemaFactory.getDefaultSchema() != null)) {
			for (final Object ob : SchemaFactory.getDefaultSchema().getTypes()) {
				final AbsItfType t = (AbsItfType) ob;
				if (t.getQName().equals(type)) {
					res = (T) t;
					break;
				}
			}
		}
		return res;
	}

	/*
	 * (non-Javadoc)
	 * @see org.ow2.easywsdl.schema.api.Schema#addType(com.ebmwebsourcing .commons.schema.api.Type)
	 */
	public void addType(final T type) {
		if (this.types == null) {
			this.types = new ArrayList<T>();
		}
		this.types.add(type);
	}

	/*
	 * Attributes methods
	 */

	/*
	 * (non-Javadoc)
	 * @see org.ow2.easywsdl.schema.api.Schema#getAttributes()
	 */
	public List<A> getAttributes() {
		if (this.attributes == null) {
			this.attributes = new ArrayList<A>();
		}
		return this.attributes;
	}

	/*
	 * (non-Javadoc)
	 * @see org.ow2.easywsdl.schema.api.Schema#getAttribute(javax.xml.namespace .QName)
	 */
	public A getAttribute(final QName element) {
		A res = null;
		if (this.attributes == null) {
			this.attributes = new ArrayList<A>();
		}
		for (final A e : this.attributes) {
			if ((e.getName().equals(element.getLocalPart())) && (e.getNamespaceUri().equals(element.getNamespaceURI()))) {
				res = e;
				break;
			}
		}
		return res;
	}

	/*
	 * (non-Javadoc)
	 * @see org.ow2.easywsdl.schema.api.Schema#addAttribute(com.ebmwebsourcing .commons.schema.api.Attribute)
	 */
	public void addAttribute(final A elmt) {
		if (this.attributes == null) {
			this.attributes = new ArrayList<A>();
		}
		this.attributes.add(elmt);
	}

	/*
	 * Elements methods
	 */

	/*
	 * (non-Javadoc)
	 * @see org.ow2.easywsdl.schema.api.Schema#getElements()
	 */
	public List<El> getElements() {
		if (this.elements == null) {
			this.elements = new ArrayList<El>();
		}
		return this.elements;
	}

	/*
	 * (non-Javadoc)
	 * @see org.ow2.easywsdl.schema.api.Schema#getAttribute(javax.xml.namespace .QName)
	 */
	public El getElement(final QName element) {
		El res = null;
		if (this.elements == null) {
			this.elements = new ArrayList<El>();
		}
		for (final El e : this.elements) {
			if (e.getQName().equals(element)) {
				res = e;
				break;
			}
		}
		return res;
	}

	public List<El> findElementsInAllSchema(QName element) {
		List<El> res = new ArrayList<El>();

		El elmt = this.getElement(element);
		if(elmt != null) {
			res.add(elmt);
		}

		// anonymous element
		AbsItfComplexType ct = null;
		for(El elmtA: this.getElements()) {
			if(elmtA.getType() instanceof AbsItfComplexType) {
				ct = (AbsItfComplexType)elmtA.getType();
				findElmtInComplexType(element, ct, res);
			}
		}
		for(T type: this.getTypes()) {
			if(type instanceof AbsItfComplexType) {
				ct = (AbsItfComplexType)type;
				findElmtInComplexType(element, ct, res);
			}
		}

		return res;
	}



	private void findElmtInComplexType(QName element, AbsItfComplexType ct, List<El> res) {
		if(ct.getSequence() != null) {
			for(El elmtItem: (List<El>)ct.getSequence().getElements()) {
				if(elmtItem.getQName().equals(element)) {
					res.add(elmtItem);
				}
			}
		}
		if(ct.getAll() != null) {
			for(El elmtItem: (List<El>)ct.getAll().getElements()) {
				if(elmtItem.getQName().equals(element)) {
					res.add(elmtItem);
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.ow2.easywsdl.schema.api.Schema#addElement(com.ebmwebsourcing .commons.schema.api.Element)
	 */
	public void addElement(final El elmt) {
		if (this.elements == null) {
			this.elements = new ArrayList<El>();
		}
		this.elements.add(elmt);
	}

	/*
	 * Namespace method
	 */

	/*
	 * (non-Javadoc)
	 * @see org.ow2.easywsdl.schema.api.Schema#getAllNamespaces()
	 */
	public NamespaceMapperImpl getAllNamespaces() {
		return this.namespaceMapper;
	}

	/**
	 * @return the features
	 */
	public Map<FeatureConstants, Object> getFeatures() {
		if (this.features == null) {
			this.features = new HashMap<FeatureConstants, Object>();
		}
		return this.features;
	}

	/**
	 * @param features
	 *            the features to set
	 */
	public void setFeatures(final Map<FeatureConstants, Object> features) {
		this.features = features;
	}

	/**
	 * methods for baseURI
	 */

	public URI getDocumentURI() {
		return documentURI;
	}

	public void setDocumentURI(final URI documentURI) {
		this.documentURI = documentURI;
	}

	@Override
	public String toString() {
		return this.model.toString();
	}
}
