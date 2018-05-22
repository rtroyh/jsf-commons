package com.gather.jsfcommons.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Created with IntelliJ IDEA.
 * $ Project: jsfcommons
 * User: rodrigotroy
 * Date: 16-11-15
 * Time: 13:21
 */
@FacesValidator("com.gather.emailValidator")
public class EmailValidator implements Validator {
    public void validate(FacesContext fc,
                         UIComponent c,
                         Object o) throws
                                   ValidatorException {
        if (o == null || "".equals(o)) {
            FacesMessage msg = new FacesMessage("No existe mail",
                                                "Error en la validación");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }

        if (!com.gather.gathercommons.util.Validator.validateMail(o.toString())) {
            FacesMessage msg = new FacesMessage("Valor invalido!",
                                                "Error en la validación");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }
}