/*
 * Copyright (c) 2002-2012 Manorrock.com. All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice,
 *     this list of conditions and the following disclaimer.
 *  2. Redistributions in binary form must reproduce the above copyright, this
 *     list of conditions and the following disclaimer in the documentation
 *     and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY Manorrock.com "AS IS" AND ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO
 * EVENT SHALL Manorrock.com OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * The views and conclusions contained in the software and documentation are
 * those of the authors and should not be interpreted as representing official
 * policies, either expressed or implied, of Manorrock.com.
 */
package com.gather.jsfcommons.core;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;
import java.io.IOException;

/**
 * An abstract renderer exposing utility methods related to rendering.
 *
 * @author Manfred Riem (mriem@manorrock.com)
 */
public abstract class AbstractRenderer extends Renderer {

    /**
     * Decode a boolean.
     *
     * @param context   the Faces context.
     * @param component the UI component.
     * @param property  the property.
     * @return a boolean if found.
     */
    protected boolean decodeBoolean(FacesContext context,
                                    UIComponent component,
                                    String property) {
        return decodeBoolean(context,
                             component,
                             property,
                             false);
    }

    /**
     * Decode a boolean.
     *
     * @param context      the Faces context.
     * @param component    the UI component.
     * @param property     the property.
     * @param defaultValue the default value.
     * @return a boolean if found.
     */
    protected boolean decodeBoolean(FacesContext context,
                                    UIComponent component,
                                    String property,
                                    boolean defaultValue) {

        boolean result = defaultValue;

        if (component.getValueExpression(property) != null) {
            ValueExpression ve = component.getValueExpression(property);
            result = (Boolean) ve.getValue(context.getELContext());
        } else {
            if (component.getAttributes().get(property) != null) {
                result = (Boolean) component.getAttributes().get(property);
            }
        }

        return result;
    }

    /**
     * Render the core (style, styleClass and title) attributes.
     *
     * @param context        the Faces context.
     * @param component      the UI component.
     * @param responseWriter the response writer.
     * @throws java.io.IOException when an I/O error occurs.
     */
    protected void writeCoreAttributes(FacesContext context,
                                       UIComponent component,
                                       ResponseWriter responseWriter) throws
                                                                      IOException {
        writeAttribute(context,
                       component,
                       responseWriter,
                       "style",
                       "style");
        writeAttribute(context,
                       component,
                       responseWriter,
                       "class",
                       "styleClass");
        writeAttribute(context,
                       component,
                       responseWriter,
                       "title",
                       "title");
    }

    /**
     * Render the i18n (lang and dir) attributes.
     *
     * @param context        the Faces context.
     * @param component      the UI component.
     * @param responseWriter the response writer.
     * @throws java.io.IOException when an I/O error occurs.
     */
    protected void writeI18NAttributes(FacesContext context,
                                       UIComponent component,
                                       ResponseWriter responseWriter) throws
                                                                      IOException {
        writeAttribute(context,
                       component,
                       responseWriter,
                       "lang",
                       "lang");
        writeAttribute(context,
                       component,
                       responseWriter,
                       "dir",
                       "dir");
    }

    /**
     * Render the event attributes.
     *
     * @param context        the Faces context.
     * @param component      the UI component.
     * @param responseWriter the response writer.
     * @throws java.io.IOException when an I/O error occurs.
     */
    protected void writeEventAttributes(FacesContext context,
                                        UIComponent component,
                                        ResponseWriter responseWriter) throws
                                                                       IOException {
        writeAttribute(context,
                       component,
                       responseWriter,
                       "onchange",
                       "onchange");
        writeAttribute(context,
                       component,
                       responseWriter,
                       "onclick",
                       "onclick");
        writeAttribute(context,
                       component,
                       responseWriter,
                       "ondblclick",
                       "ondblclick");
        writeAttribute(context,
                       component,
                       responseWriter,
                       "onmousedown",
                       "onmousedown");
        writeAttribute(context,
                       component,
                       responseWriter,
                       "onmouseup",
                       "onmouseup");
        writeAttribute(context,
                       component,
                       responseWriter,
                       "onmouseover",
                       "onmouseover");
        writeAttribute(context,
                       component,
                       responseWriter,
                       "onmousemove",
                       "onmousemove");
        writeAttribute(context,
                       component,
                       responseWriter,
                       "onmouseout",
                       "onmouseout");
        writeAttribute(context,
                       component,
                       responseWriter,
                       "onkeypress",
                       "onkeypress");
        writeAttribute(context,
                       component,
                       responseWriter,
                       "onkeydown",
                       "onkeydown");
        writeAttribute(context,
                       component,
                       responseWriter,
                       "onkeyup",
                       "onkeyup");
    }

    /**
     * Render the attribute by getting actual string for a given key.
     *
     * @param context        the Faces context.
     * @param component      the UI component.
     * @param responseWriter the response writer.
     * @param htmlKey        the HTML attribute name.
     * @param key            the key.
     * @throws java.io.IOException when an I/O error occurs.
     */
    protected void writeAttribute(FacesContext context,
                                  UIComponent component,
                                  ResponseWriter responseWriter,
                                  String htmlKey,
                                  String key) throws
                                              IOException {

        String value = null;
        ValueExpression expression = component.getValueExpression(key);
        if (expression != null) {
            value = expression.getValue(context.getELContext()).toString();
        } else {
            if (component.getAttributes().get(key) != null) {
                value = component.getAttributes().get(key).toString();
            }
        }

        if (value != null) {
            responseWriter.writeAttribute(htmlKey,
                                          value,
                                          null);
        }
    }
}
