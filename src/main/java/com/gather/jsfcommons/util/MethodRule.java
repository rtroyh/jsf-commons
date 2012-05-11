package com.gather.jsfcommons.util;

import javax.el.MethodExpression;
import javax.faces.component.UIComponent;
import javax.faces.view.facelets.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA.
 * User: rodrigotroy
 * Date: 5/11/12
 * Time: 11:18 AM
 * To change this template use File | Settings | File Templates.
 */
public class MethodRule extends MetaRule {

    private String name;
    private Class<?> returnType;
    private Class<?>[] parameterTypes;

    public MethodRule(String name,
                      Class<?> returnType,
                      Class<?>[] parameterTypes) {
        this.name = name;
        this.returnType = returnType;
        this.parameterTypes = parameterTypes;
    }

    @Override
    public Metadata applyRule(final String name,
                              final TagAttribute attribute,
                              MetadataTarget meta) {
        if (!this.name.equals(name)) {
            return null;
        }

        final Method writeMethod = meta.getWriteMethod(name);

        return new Metadata() {

            @Override
            public void applyMetadata(FaceletContext ctx,
                                      Object instance) {
                MethodExpression expr = attribute.getMethodExpression(ctx,
                                                                      returnType,
                                                                      parameterTypes);
                try {
                    if (writeMethod == null) {
                        ((UIComponent) instance).getAttributes().put(name,
                                                                     expr);
                    } else {
                        writeMethod.invoke(instance,
                                           expr);
                    }
                } catch (InvocationTargetException e) {
                    throw new TagAttributeException(attribute,
                                                    e.getCause());
                } catch (Exception e) {
                    throw new TagAttributeException(attribute,
                                                    e);
                }
            }
        };
    }
}