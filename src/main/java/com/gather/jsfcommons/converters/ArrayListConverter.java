package com.gather.jsfcommons.converters;

import com.gather.gathercommons.util.Validator;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: rodrigotroy
 * Date: 11/15/12
 * Time: 6:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class ArrayListConverter implements Converter {
    private List<List<Object>> options;
    private Integer keyIndex;

    public ArrayListConverter(List<List<Object>> options,
                              Integer keyIndex) {
        this.options = options;
        this.keyIndex = keyIndex;
    }

    @Override
    public Object getAsObject(FacesContext context,
                              UIComponent component,
                              String value) {
        if (context == null || component == null) {
            throw new NullPointerException();
        }

        // If the specified value is null or zero-length, return null
        if (value == null) {
            return (null);
        }

        if (Validator.validateList(options) && keyIndex != null && keyIndex >= 0) {
            for (List<Object> list : options) {
                if (keyIndex < list.size()) {
                    if (list.get(keyIndex).toString().equals(value)) {
                        return list;
                    }
                }
            }
        }

        return null;
    }

    @Override
    public String getAsString(FacesContext context,
                              UIComponent component,
                              Object value) {

        if (Validator.validateList(value)) {
            return ((List<Object>) value).get(keyIndex).toString();
        }

        return null;
    }
}
