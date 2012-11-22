package com.gather.jsfcommons.converters;

import com.gather.gathercommons.util.Validator;
import org.apache.log4j.Logger;

import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;
import java.util.List;

public class ListConverter implements Converter {
    private static final Logger LOG = Logger.getLogger(ListConverter.class);

    @SuppressWarnings("unchecked")
    @Override
    public Object getAsObject(FacesContext context,
                              UIComponent component,
                              String value) {
        LOG.info("INICIO GETASOBJECT()");

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
            LOG.error(e.getMessage());
        }

        return value;
    }

    @Override
    public String getAsString(FacesContext context,
                              UIComponent component,
                              Object value) {
        LOG.info("INICIO GETASSTRING()");

        try {
            if (value != null) {
                return value.toString();
            }
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }

        return "";
    }

}
