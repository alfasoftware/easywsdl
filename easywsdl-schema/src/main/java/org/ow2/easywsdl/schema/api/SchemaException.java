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

/**
 * @author Nicolas Salatge - EBM WebSourcing
 */
public class SchemaException extends XmlException {
    public static final long serialVersionUID = 1;

    public static final String INVALID_SCHEMA = "INVALID_Schema";

    public static final String PARSER_ERROR = "PARSER_ERROR";

    public static final String OTHER_ERROR = "OTHER_ERROR";

    public static final String CONFIGURATION_ERROR = "CONFIGURATION_ERROR";

    public static final String UNBOUND_PREFIX = "UNBOUND_PREFIX";

    public static final String NO_PREFIX_SPECIFIED = "NO_PREFIX_SPECIFIED";

    private String faultCode = null;

    private Throwable targetThrowable = null;

    private String location = null;

    public SchemaException(final String faultCode, final String msg, final Throwable t) {
        super(msg, t);
        this.setFaultCode(faultCode);
    }

    public SchemaException(final String msg, final Throwable t) {
        super(msg, t);
    }

    public SchemaException(final Throwable t) {
        super(t);
    }

    public SchemaException(final String msg) {
        super(msg);
    }

    public SchemaException(final String faultCode, final String msg) {
        this(faultCode, msg, null);
    }

    @Override
    public void setFaultCode(final String faultCode) {
        this.faultCode = faultCode;
    }

    @Override
    public String getFaultCode() {
        return this.faultCode;
    }

    @Override
    public void setTargetException(final Throwable targetThrowable) {
        this.targetThrowable = targetThrowable;
    }

    @Override
    public Throwable getTargetException() {
        if (this.targetThrowable == null) {
            return this.getCause();
        } else {
            return this.targetThrowable;
        }
    }

    /**
     * Set the location using an XPath expression. Used for error messages.
     * 
     * @param location
     *            an XPath expression describing the location where the
     *            exception occurred.
     */
    @Override
    public void setLocation(final String location) {
        this.location = location;
    }

    /**
     * Get the location, if one was set. Should be an XPath expression which is
     * used for error messages.
     */
    @Override
    public String getLocation() {
        return this.location;
    }

//    @Override
//    public String getMessage() {
//        final StringBuffer strBuf = new StringBuffer();
//
//        strBuf.append("SchemaException");
//
//        if (this.location != null) {
//            try {
//                strBuf.append(" (at " + this.location + ")");
//            } catch (final IllegalArgumentException e) {
//            }
//        }
//
//        if (this.faultCode != null) {
//            strBuf.append(": faultCode=" + this.faultCode);
//        }
//
//        final String thisMsg = super.getMessage();
//        String targetMsg = null;
//        String targetName = null;
//        if (this.getTargetException() != null) {
//            targetMsg = this.getTargetException().getMessage();
//            targetName = this.getTargetException().getClass().getName();
//        }
//
//        if ((thisMsg != null) && ((targetMsg == null) || !thisMsg.equals(targetMsg))) {
//            strBuf.append(": " + thisMsg);
//        }
//
//        if (targetName != null) {
//            strBuf.append(": " + targetName);
//        }
//
//        if (targetMsg != null) {
//            strBuf.append(": " + targetMsg);
//        }
//
//        return strBuf.toString();
//    }
}
