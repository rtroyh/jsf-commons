package com.gather.jsfcommons.validator;

import com.gather.gathercommons.rut.RUTHolder;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Created with IntelliJ IDEA.
 * $ Project: bci_ffmm_apv
 * User: rodrigotroy
 * Date: 12/12/17
 * Time: 13:48
 */
@FacesValidator("com.gather.rutValidator")
public class RUTValidator implements Validator {
    @Override
    public void validate(FacesContext facesContext,
                         UIComponent uiComponent,
                         Object o) throws
                                   ValidatorException {
        if (com.gather.gathercommons.util.Validator.validateString(o)) {
            if (!com.gather.gathercommons.util.Validator.validateRUT(o.toString())) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                                              null,
                                                              "Campo RUT: Formato invalido"));
            }

            RUTHolder rutHolder = new RUTHolder(o.toString());

            if (!rutHolder.esValido()) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                                              null,
                                                              "Campo RUT: Dígito verificador erroneo"));
            }
        }
    }
}
