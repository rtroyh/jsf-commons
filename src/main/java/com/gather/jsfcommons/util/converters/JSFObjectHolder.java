package com.gather.jsfcommons.util.converters;

import java.io.Serializable;

public class JSFObjectHolder implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 5178838897454416730L;
    private String componentID;
    private Object componentObject;

    public JSFObjectHolder(String componentID,
                           Object componentObject) {
        super();
        this.componentID = componentID;
        this.componentObject = componentObject;
    }

    public String getComponentID() {
        return componentID;
    }

    public void setComponentID(String componentID) {
        this.componentID = componentID;
    }

    public Object getComponentObject() {
        return componentObject;
    }

    public void setComponentObject(Object componentObject) {
        this.componentObject = componentObject;
    }

}
