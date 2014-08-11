package com.gather.jsfcommons.converters;

import com.gather.gathercommons.model.IDataTableModel;
import com.gather.gathercommons.util.Validator;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * $ Project: jsfcommons
 * User: rodrigotroy
 * Date: 11-08-14
 * Time: 17:13
 */
public class DataTableConverter implements Converter {
    private Integer matchIndex;
    private IDataTableModel model;

    public DataTableConverter(IDataTableModel model,
                              Integer matchIndex) {
        this.model = model;
        this.matchIndex = matchIndex;
    }

    @Override
    public Object getAsObject(FacesContext context,
                              UIComponent component,
                              String value) {
        for (List<Object> list : model.getRows()) {
            if (list.get(matchIndex).toString().equals(value)) {
                return list;
            }
        }

        return null;
    }

    @Override
    public String getAsString(FacesContext context,
                              UIComponent component,
                              Object value) {
        if (Validator.validateList(value)) {
            return ((List<Object>) value).get(matchIndex).toString();
        }

        return null;
    }
}
