package com.gather.jsfcommons.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * Created with IntelliJ IDEA.
 * User: rodrigotroy
 * Date: 5/14/13
 * Time: 4:15 PM
 * To change this template use File | Settings | File Templates.
 */

@FacesConverter("com.mkyong.URLConverter")
public class BooleanConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context,
                              UIComponent component,
                              String value) {
        System.out.println("getAsObject = " + value);
        if (context == null || component == null) {
            throw new NullPointerException();
        }

        // If the specified value is null or zero-length, return null
        if (value == null) {
            return (null);
        }

        return value.equals("true");
    }

    @Override
    public String getAsString(FacesContext context,
                              UIComponent component,
                              Object value) {
        System.out.println("getAsString = " + value);
        if (context == null || component == null) {
            throw new NullPointerException();
        }

        // If the specified value is null or zero-length, return null
        if (value == null) {
            return "";
        }

        if (value instanceof Boolean) {
            return ((Boolean) value) ? "1" : "0";
        }

        return "";
    }
}
