package com.gather.jsfcommons.util;

import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.component.*;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.model.SelectItem;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: rodrigotroy
 * Date: 5/11/12
 * Time: 11:17 AM
 * To change this template use File | Settings | File Templates.
 */
public class Renderers {
    public static Object getConvertedValue(FacesContext context,
                                           UIComponent component,
                                           Object submittedValue) throws
                                                                  ConverterException {
        if (submittedValue instanceof String) {
            Converter converter = getConverter(context,
                                               component);
            if (converter != null) {
                return converter.getAsObject(context,
                                             component,
                                             (String) submittedValue);
            }
        }

        return submittedValue;
    }

    public static Converter getConverter(FacesContext context,
                                         UIComponent component) {
        if (!(component instanceof ValueHolder)) {
            return null;
        }

        ValueHolder holder = (ValueHolder) component;

        Converter converter = holder.getConverter();
        if (converter != null) {
            return converter;
        }

        ValueExpression expr = component.getValueExpression("value");
        if (expr == null) {
            return null;
        }

        Class<?> targetType = expr.getType(context.getELContext());
        if (targetType == null) {
            return null;
        }
        // Version 1.0 of the reference implementation will not apply a converter
        // if the target type is String or Object, but that is a bug.

        Application app = context.getApplication();

        return app.createConverter(targetType);
    }

    public static String getFormId(FacesContext context,
                                   UIComponent component) {
        UIComponent parent = component;

        while (!(parent instanceof UIForm)) {
            parent = parent.getParent();
        }

        return parent.getClientId(context);
    }

    public static List<SelectItem> getSelectItems(UIComponent component) {
        ArrayList<SelectItem> list = new ArrayList<SelectItem>();

        for (UIComponent child : component.getChildren()) {
            if (child instanceof UISelectItem) {
                Object value = ((UISelectItem) child).getValue();

                if (value == null) {
                    UISelectItem item = (UISelectItem) child;
                    list.add(new SelectItem(item.getItemValue(),
                                            item.getItemLabel(),
                                            item.getItemDescription(),
                                            item.isItemDisabled()));
                } else if (value instanceof SelectItem) {
                    list.add((SelectItem) value);
                }

            } else if (child instanceof UISelectItems) {
                Object value = ((UISelectItems) child).getValue();

                if (value instanceof SelectItem) {
                    list.add((SelectItem) value);
                } else if (value instanceof SelectItem[]) {
                    list.addAll(Arrays.asList((SelectItem[]) value));
                } else if (value instanceof Collection<?>) {
                    @SuppressWarnings("unchecked")
                    Collection<SelectItem> values = (Collection<SelectItem>) value;
                    list.addAll(values);
                } else if (value instanceof Map<?, ?>) {
                    for (Map.Entry<?, ?> entry : ((Map<?, ?>) value).entrySet()) {
                        list.add(new SelectItem(entry.getKey(),
                                                "" + entry.getValue()));
                    }
                }
            }
        }

        return list;
    }
}
