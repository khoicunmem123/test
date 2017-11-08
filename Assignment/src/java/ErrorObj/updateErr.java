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
public class updateErr implements Serializable {

    private boolean classError;
    private boolean address1Error;
    private boolean statusError;
    private boolean phoneError;

    public updateErr() {
        classError = false;
        address1Error = false;
        statusError = false;
        phoneError = false;
    }

    /**
     * @return the classError
     */
    public boolean isClassError() {
        return classError;
    }

    public boolean isError() {
        return classError || address1Error || statusError || phoneError;
    }

    /**
     * @param classError the classError to set
     */
    public void setClassError(boolean classError) {
        this.classError = classError;
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
     * @return the statusError
     */
    public boolean isStatusError() {
        return statusError;
    }

    /**
     * @param statusError the statusError to set
     */
    public void setStatusError(boolean statusError) {
        this.statusError = statusError;
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

}
