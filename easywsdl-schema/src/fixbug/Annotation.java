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
 
package org.ow2.easywsdl.schema.org.w3._2001.xmlschema;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.jvnet.hyperjaxb3.item.Item;
import org.jvnet.hyperjaxb3.item.ItemUtils;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.HashCode;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.builder.JAXBEqualsBuilder;
import org.jvnet.jaxb2_commons.lang.builder.JAXBHashCodeBuilder;
import org.jvnet.jaxb2_commons.lang.builder.JAXBToStringBuilder;


/**
 * Java class for anonymous complex type.
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.w3.org/2001/XMLSchema}openAttrs">
 *       &lt;choice maxOccurs="unbounded" minOccurs="0">
 *         &lt;element ref="{http://www.w3.org/2001/XMLSchema}appinfo"/>
 *         &lt;element ref="{http://www.w3.org/2001/XMLSchema}documentation"/>
 *       &lt;/choice>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "appinfoOrDocumentation"
})
@XmlRootElement(name = "annotation")
@Entity(name = "org.w3._2001.xmlschema.Annotation")
@Table(name = "ANNOTATION")
public class Annotation
    extends OpenAttrs
    implements Equals, HashCode, ToString
{

    @XmlElements({
        @XmlElement(name = "documentation", type = Documentation.class),
        @XmlElement(name = "appinfo", type = Appinfo.class)
    })
    protected List<Object> appinfoOrDocumentation;
    @XmlAttribute
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;
    @XmlTransient
    protected List<Annotation.AppinfoOrDocumentationItem> appinfoOrDocumentationItems;

    /**
     * Gets the value of the appinfoOrDocumentation property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the appinfoOrDocumentation property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAppinfoOrDocumentation().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Documentation }
     * {@link Appinfo }
     *
     *
     */
    @Transient
    public List<Object> getAppinfoOrDocumentation() {
        if (appinfoOrDocumentation == null) {
            appinfoOrDocumentation = new ArrayList<Object>();
        }
        return this.appinfoOrDocumentation;
    }

    /**
     *
     *
     */
    public void setAppinfoOrDocumentation(List<Object> appinfoOrDocumentation) {
        this.appinfoOrDocumentation = appinfoOrDocumentation;
    }

    /**
     * Gets the value of the id property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    @Basic
    @Column(name = "ID")
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setId(String value) {
        this.id = value;
    }

    public void toString(ToStringBuilder toStringBuilder) {
        super.toString(toStringBuilder);
        {
            List<Object> theAppinfoOrDocumentation;
            theAppinfoOrDocumentation = this.getAppinfoOrDocumentation();
            toStringBuilder.append("appinfoOrDocumentation", theAppinfoOrDocumentation);
        }
        {
            String theId;
            theId = this.getId();
            toStringBuilder.append("id", theId);
        }
    }

    public String toString() {
        final ToStringBuilder toStringBuilder = new JAXBToStringBuilder(this);
        toString(toStringBuilder);
        return toStringBuilder.toString();
    }

    public void equals(Object object, EqualsBuilder equalsBuilder) {
        if (!(object instanceof Annotation)) {
            equalsBuilder.appendSuper(false);
            return ;
        }
        if (this == object) {
            return ;
        }
        super.equals(object, equalsBuilder);
        final Annotation that = ((Annotation) object);
        equalsBuilder.append(this.getAppinfoOrDocumentation(), that.getAppinfoOrDocumentation());
        equalsBuilder.append(this.getId(), that.getId());
    }

    public boolean equals(Object object) {
        if (!(object instanceof Annotation)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final EqualsBuilder equalsBuilder = new JAXBEqualsBuilder();
        equals(object, equalsBuilder);
        return equalsBuilder.isEquals();
    }

    public void hashCode(HashCodeBuilder hashCodeBuilder) {
        super.hashCode(hashCodeBuilder);
        hashCodeBuilder.append(this.getAppinfoOrDocumentation());
        hashCodeBuilder.append(this.getId());
    }

    public int hashCode() {
        final HashCodeBuilder hashCodeBuilder = new JAXBHashCodeBuilder();
        hashCode(hashCodeBuilder);
        return hashCodeBuilder.toHashCode();
    }

    @OneToMany(cascade = {
        CascadeType.ALL
    })
    @JoinTable(name = "ANNOTATION_APPINFOORDOCUMENT_0", joinColumns = {
        @JoinColumn(name = "PARENT_ANNOTATION_ID")
    }, inverseJoinColumns = {
        @JoinColumn(name = "CHILD_ANNOTATIONAPPINFOORDOC_0")
    })
    @OrderBy
    public List<Annotation.AppinfoOrDocumentationItem> getAppinfoOrDocumentationItems() {
        if (this.appinfoOrDocumentationItems == null) {
            this.appinfoOrDocumentationItems = new ArrayList<Annotation.AppinfoOrDocumentationItem>();
        }
        if (ItemUtils.shouldBeWrapped(this.appinfoOrDocumentation)) {
            this.appinfoOrDocumentation = ItemUtils.wrap((List)this.appinfoOrDocumentation, this.appinfoOrDocumentationItems, Annotation.AppinfoOrDocumentationItem.class);
        }
        return this.appinfoOrDocumentationItems;
    }

    public void setAppinfoOrDocumentationItems(List<Annotation.AppinfoOrDocumentationItem> value) {
        this.appinfoOrDocumentation = null;
        this.appinfoOrDocumentationItems = null;
        this.appinfoOrDocumentationItems = value;
        if (this.appinfoOrDocumentationItems == null) {
            this.appinfoOrDocumentationItems = new ArrayList<Annotation.AppinfoOrDocumentationItem>();
        }
        if (ItemUtils.shouldBeWrapped(this.appinfoOrDocumentation)) {
            this.appinfoOrDocumentation = ItemUtils.wrap((List)this.appinfoOrDocumentation, this.appinfoOrDocumentationItems, Annotation.AppinfoOrDocumentationItem.class);
        }
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @Entity(name = "org.w3._2001.xmlschema.Annotation$AppinfoOrDocumentationItem")
    @Table(name = "ANNOTATIONAPPINFOORDOCUMENTA_0")
    @Inheritance(strategy = InheritanceType.JOINED)
    public static class AppinfoOrDocumentationItem
        implements Item<Equals>
    {

        @XmlElements({
            @XmlElement(name = "documentation", type = Documentation.class),
            @XmlElement(name = "appinfo", type = Appinfo.class)
        })
        protected Equals item;
        @XmlTransient
        protected Long hjid;

        /**
         *
         *
         * @return
         *     possible object is
         *     {@link Documentation }
         *     {@link Appinfo }
         *
         */
        @Transient
        public Equals getItem() {
            return item;
        }

        /**
         *
         *
         * @param value
         *     allowed object is
         *     {@link Documentation }
         *     {@link Appinfo }
         *
         */
        public void setItem(Equals value) {
            this.item = value;
        }

        /**
         *
         *
         * @return
         *     possible object is
         *     {@link Long }
         *
         */
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "HJID")
        public Long getHjid() {
            return hjid;
        }

        /**
         *
         *
         * @param value
         *     allowed object is
         *     {@link Long }
         *
         */
        public void setHjid(Long value) {
            this.hjid = value;
        }

        @ManyToOne(cascade = {
            CascadeType.ALL
        })
        @JoinColumn(name = "ITEMDOCUMENTATION_DOCUMENTAT_0")
        public Documentation getItemDocumentation() {
            if (this.getItem() instanceof Documentation) {
                return ((Documentation) this.getItem());
            } else {
                return null;
            }
        }

        public void setItemDocumentation(Documentation target) {
            if (target!= null) {
                setItem(target);
            }
        }

        @ManyToOne(cascade = {
            CascadeType.ALL
        })
        @JoinColumn(name = "ITEMAPPINFO_APPINFO_ID")
        public Appinfo getItemAppinfo() {
            if (this.getItem() instanceof Appinfo) {
                return ((Appinfo) this.getItem());
            } else {
                return null;
            }
        }

        public void setItemAppinfo(Appinfo target) {
            if (target!= null) {
                setItem(target);
            }
        }

    }

}
