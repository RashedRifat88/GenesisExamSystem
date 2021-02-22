package com.egsystem.genesisexamsystem.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ErrorResponseGeneric {

    @SerializedName("message")
    @Expose
    private Errors message;

    @SerializedName("errors")
    @Expose
    private Errors errors;


    public Errors getMessage() {
        return message;
    }

    public void setMessage(Errors message) {
        this.message = message;
    }

    public Errors getErrors() {
        return errors;
    }

    public void setErrors(Errors errors) {
        this.errors = errors;
    }


    public class Errors {

        @SerializedName("bmdc_no")
        @Expose
        private List<String> bmdcNo = null;
        @SerializedName("password")
        @Expose
        private List<String> password = null;

        public List<String> getBmdcNo() {
            return bmdcNo;
        }

        public void setBmdcNo(List<String> bmdcNo) {
            this.bmdcNo = bmdcNo;
        }

        public List<String> getPassword() {
            return password;
        }

        public void setPassword(List<String> password) {
            this.password = password;
        }

    }

}
