package com.gather.jsfcommons.uicomponents;

import java.io.IOException;

import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import com.gather.gathercommons.util.Validator;

public class UIHTMLChart extends HtmlOutputText {

    private Object userObject;
    private String fileName;
    private String id;
    private String width;
    private String height;
    private String xml;

    public UIHTMLChart() {
        super();
        setRendererType(null);
    }

    public Object getUserObject() {
        return userObject;
    }

    public void setUserObject(Object userObject) {
        this.userObject = userObject;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }

    public void encodeBegin(FacesContext context) throws
                                                  IOException {
        ResponseWriter writer = context.getResponseWriter();

        writer.write("\t\t<!-- START Code Block for Chart " +
                             this.getId() +
                             "-->\n");
        writer.write("\t\t");
        writer.startElement("object",
                            this);

        writer.writeAttribute("classid",
                              "clsid:d27cdb6e-ae6d-11cf-96b8-444553540000",
                              null);
        writer.writeAttribute("codebase",
                              "http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=8,0,0,0",
                              null);
        writer.writeAttribute("width",
                              this.getWidth(),
                              null);
        writer.writeAttribute("height",
                              this.getHeight(),
                              null);
        writer.writeAttribute("id",
                              this.getId(),
                              null);

        writer.write("\n");

        // movie
        writer.write("\t\t\t");
        writer.startElement("param",
                            this);
        writer.writeAttribute("name",
                              "movie",
                              null);
        writer.writeAttribute("value",
                              this.getFileName(),
                              null);
        writer.endElement("param");
        writer.write("\n");

        // wmode
        writer.write("\t\t\t");
        writer.startElement("param",
                            this);
        writer.writeAttribute("name",
                              "WMode",
                              null);
        writer.writeAttribute("value",
                              "Transparent",
                              null);
        writer.endElement("param");
        writer.write("\n");

        // quality
        writer.write("\t\t\t");
        writer.startElement("param",
                            this);
        writer.writeAttribute("name",
                              "quality",
                              null);
        writer.writeAttribute("value",
                              "high",
                              null);
        writer.endElement("param");
        writer.write("\n");
    }

    public void encodeEnd(FacesContext context) throws
                                                IOException {
        String xml = this.getXml();

        String strFlashVars = "";

        if (Validator.validateString(xml)) {
            // Strip out all carriage returns and line feeds if any.
            xml = xml.replaceAll("[\\r\\n]+",
                                 "");
            xml = xml.trim();
            strFlashVars = "&dataXML=" +
                    xml +
                    "&chartWidth=" +
                    this.getWidth() +
                    "&chartHeight=" +
                    this.getHeight() +
                    "&debugMode=0";
        }

        ResponseWriter writer = context.getResponseWriter();

        // FlashVars
        writer.write("\t\t\t");
        writer.startElement("param",
                            this);
        writer.writeAttribute("name",
                              "FlashVars",
                              null);
        // value for FlashVars
        writer.writeAttribute("value",
                              strFlashVars,
                              null);
        writer.endElement("param");
        writer.write("\n");

        // embed
        writer.write("\t\t\t");
        writer.startElement("embed",
                            this);
        writer.writeAttribute("src",
                              this.getFileName(),
                              null);
        writer.writeAttribute("FlashVars",
                              strFlashVars,
                              null);
        writer.writeAttribute("src",
                              this.getFileName(),
                              null);
        writer.writeAttribute("quality",
                              "high",
                              null);
        writer.writeAttribute("width",
                              this.getWidth(),
                              null);
        writer.writeAttribute("height",
                              this.getHeight(),
                              null);
        writer.writeAttribute("name",
                              this.getId(),
                              null);
        writer.writeAttribute("name",
                              this.getId(),
                              null);
        writer.writeAttribute("WMode",
                              "transparent",
                              null);
        writer.writeAttribute("type",
                              "application/x-shockwave-flash",
                              null);
        writer.writeAttribute("pluginspage",
                              "http://www.macromedia.com/go/getflashplayer",
                              null);

        writer.write("\n");
        writer.write("\t\t\t");
        writer.endElement("embed");
        writer.write("\n");

        // End the object tag
        writer.write("\t\t");
        writer.endElement("object");
        writer.write("\n");
        writer.write("\t\t<!--END Code Block for Chart " +
                             this.getId() +
                             "-->\n");
    }

}
