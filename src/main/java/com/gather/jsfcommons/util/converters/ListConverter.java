package com.gather.jsfcommons.util.converters;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;

import com.gather.gathercommons.util.Validator;

public class ListConverter implements Converter {

    @SuppressWarnings("unchecked")
    @Override
    public Object getAsObject(FacesContext context,
                              UIComponent component,
                              String value) {
        Logger.getLogger(ListConverter.class).info("INICIO GETASOBJECT()");

        try {
            if (component instanceof javax.faces.component.html.HtmlSelectOneMenu) {
                List<UIComponent> childrens = component.getChildren();

                for (UIComponent uic : childrens) {
                    if (uic instanceof UISelectItems) {

                        if (Validator.validateList(((UISelectItems) uic).getValue())) {
                            List<Object> items = (List<Object>) ((UISelectItems) uic).getValue();

                            for (Object o : items) {
                                if (o instanceof SelectItem) {
                                    SelectItem si = (SelectItem) o;

                                    if (si.getValue().toString().equals(value)) {
                                        return si;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            Logger.getLogger(ListConverter.class).error(e.getMessage());
        }

        return value;
    }

    @Override
    public String getAsString(FacesContext context,
                              UIComponent component,
                              Object value) {
        Logger.getLogger(ListConverter.class).info("INICIO GETASSTRING()");

        try {
            if (value != null) {
                return value.toString();
            }
        } catch (Exception e) {
            Logger.getLogger(ListConverter.class).error(e.getMessage());
        }

        return "";
    }

}
