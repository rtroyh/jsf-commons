package com.gather.jsfcommons.converter;

import com.gather.gathercommons.model.IEnum;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * $ Project: jsf-commons
 * User: rodrigotroy
 * Date: 8/8/17
 * Time: 12:01
 */
@FacesConverter(value = "gather.EnumConverter")
public class EnumConverter implements Converter {
    private static final String ATTRIBUTE_ENUM_TYPE = "EnumConverter.";
    private static final String ERROR_NO_ENUM_TYPE = "Given type is not an enum.";

    @SuppressWarnings({"rawtypes", "unchecked"})
    public String getAsString(FacesContext context,
                              UIComponent component,
                              Object anEnum) {
        if (anEnum == null) {
            return "-";
        }

        if (anEnum instanceof IEnum) {
            Class<Enum> enumType = ((Enum) anEnum).getDeclaringClass();
            setViewAttribute(context,
                             ATTRIBUTE_ENUM_TYPE + component.getClientId(context),
                             enumType);

            final IEnum theEnum = (IEnum) anEnum;
            if (theEnum.getID() != null) {
                return theEnum.getLabel();
            } else {
                throw new ConverterException(ERROR_NO_ENUM_TYPE);
            }
        } else {
            throw new ConverterException(ERROR_NO_ENUM_TYPE);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public Object getAsObject(FacesContext context,
                              UIComponent component,
                              String submittedValue) {

        Class<Enum> enumType = this.getViewAttribute(ATTRIBUTE_ENUM_TYPE + component.getClientId(context),
                                                     context);

        try {
            return Enum.valueOf(enumType,
                                submittedValue.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ConverterException("Error",
                                         e);
        }
    }

    public static void setViewAttribute(FacesContext context,
                                        String name,
                                        Object value) {
        Map<String, Object> viewMap = context.getViewRoot().getViewMap();

        viewMap.put(name,
                    value);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private <T> T getViewAttribute(String name,
                                   FacesContext context) {
        Map<String, Object> viewMap = context.getViewRoot().getViewMap();

        return (T) viewMap.get(name);
    }
}
