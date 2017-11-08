/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ErrorObj;

import java.io.Serializable;

/**
 *
 * @author khoi_
 */
public class insertErr implements Serializable {

    private boolean idLengthError;
    private boolean idDuplicaError;
    private boolean lastnameError;
    private boolean firstnameError;
    private boolean address1Error;
    private boolean address2Error;
    private boolean phoneError;
    private boolean classError;

    public insertErr() {
        idLengthError = false;
        idDuplicaError = false;
        lastnameError = false;
        firstnameError = false;
        address1Error = false;
        address2Error = false;
        phoneError = false;
        classError = false;
    }

    public boolean isError() {
        return idLengthError || idDuplicaError || lastnameError || firstnameError || address1Error || address2Error || phoneError || classError;
    }

    /**
     * @return the idLengthError
     */
    public boolean isIdLengthError() {
        return idLengthError;
    }

    /**
     * @param idLengthError the idLengthError to set
     */
    public void setIdLengthError(boolean idLengthError) {
        this.idLengthError = idLengthError;
    }

    /**
     * @return the idDuplicaError
     */
    public boolean isIdDuplicaError() {
        return idDuplicaError;
    }

    /**
     * @param idDuplicaError the idDuplicaError to set
     */
    public void setIdDuplicaError(boolean idDuplicaError) {
        this.idDuplicaError = idDuplicaError;
    }

    /**
     * @return the lastnameError
     */
    public boolean isLastnameError() {
        return lastnameError;
    }

    /**
     * @param lastnameError the lastnameError to set
     */
    public void setLastnameError(boolean lastnameError) {
        this.lastnameError = lastnameError;
    }

    /**
     * @return the firstnameError
     */
    public boolean isFirstnameError() {
        return firstnameError;
    }

    /**
     * @param firstnameError the firstnameError to set
     */
    public void setFirstnameError(boolean firstnameError) {
        this.firstnameError = firstnameError;
    }

    /**
     * @return the address1Error
     */
    public boolean isAddress1Error() {
        return address1Error;
    }

    /**
     * @param address1Error the address1Error to set
     */
    public void setAddress1Error(boolean address1Error) {
        this.address1Error = address1Error;
    }

    /**
     * @return the address2Error
     */
    public boolean isAddress2Error() {
        return address2Error;
    }

    /**
     * @param address2Error the address2Error to set
     */
    public void setAddress2Error(boolean address2Error) {
        this.address2Error = address2Error;
    }

    /**
     * @return the phoneError
     */
    public boolean isPhoneError() {
        return phoneError;
    }

    /**
     * @param phoneError the phoneError to set
     */
    public void setPhoneError(boolean phoneError) {
        this.phoneError = phoneError;
    }

    /**
     * @return the classError
     */
    public boolean isClassError() {
        return classError;
    }

    /**
     * @param classError the classError to set
     */
    public void setClassError(boolean classError) {
        this.classError = classError;
    }

}
