package com.gather.jsfcommons.converter.autocomplete;

import com.gather.gathercommons.model.IListModel;
import com.gather.gathercommons.util.Validator;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import java.util.List;

/**
 * Created by rodrigotroy on 10/23/14.
 */
public class AutocompleteListModelConverter implements Converter {
    private IListModel model;
    private Integer index;

    public AutocompleteListModelConverter(IListModel model,
                                          Integer index) {
        this.model = model;
        this.index = index;
    }

    public AutocompleteListModelConverter(IListModel model) {
        this.model = model;
        this.index = 1;
    }

    @Override
    public Object getAsObject(FacesContext context,
                              UIComponent component,
                              String value) {
        for (List<Object> list : model.getRows()) {
            if (list.get(this.index).toString().equals(value)) {
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
            return ((List<Object>) value).get(this.index).toString();
        }

        return null;
    }
}
