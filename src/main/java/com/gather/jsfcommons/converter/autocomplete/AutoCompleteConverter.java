package com.gather.jsfcommons.converter.autocomplete;

import com.gather.gathercommons.util.Validator;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * $ Project: bci_ffmm_mesa_incidencias
 * User: rodrigotroy
 * Date: 23-06-16
 * Time: 19:50
 */
public class AutoCompleteConverter implements Converter {
    private List<IAutoCompleteItem> items;

    public AutoCompleteConverter(List<IAutoCompleteItem> items) {
        this.items = items;
    }

    public Object getAsObject(FacesContext context,
                              UIComponent component,
                              String value) {
        if (context == null || component == null) {
            throw new NullPointerException();
        }

        if (value == null) {
            return (null);
        }

        if (Validator.validateList(items)) {
            for (IAutoCompleteItem item : items) {
                if (item.getID() != null && item.getID().toString().equals(value)) {
                    return item;
                }
            }
        }

        return null;
    }

    public String getAsString(FacesContext context,
                              UIComponent component,
                              Object value) {
        if (value instanceof IAutoCompleteItem) {
            final Object id = ((IAutoCompleteItem) value).getID();

            if (id != null) {
                return id.toString();
            }
        }

        return null;
    }
}
