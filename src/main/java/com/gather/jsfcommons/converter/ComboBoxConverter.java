package com.gather.jsfcommons.converter;

import com.gather.gathercommons.model.IComboBoxModel;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * $ Project: jsfcommons
 * User: rodrigotroy
 * Date: 11-08-14
 * Time: 17:15
 */
public class ComboBoxConverter implements Converter {
    private Integer matchIndex;
    private IComboBoxModel model;

    public ComboBoxConverter(IComboBoxModel model,
                             Integer matchIndex) {
        this.model = model;
        this.matchIndex = matchIndex;
    }

    @Override
    public Object getAsObject(FacesContext context,
                              UIComponent component,
                              String value) {
        for (List<Object> list : model.getOptions()) {
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
        if (value != null) {
            return value.toString();
        }

        return null;
    }
}
