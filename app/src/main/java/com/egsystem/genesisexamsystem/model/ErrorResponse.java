package com.egsystem.genesisexamsystem.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ErrorResponse {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("validation_error")
    @Expose
    private List<String> validationError = null;
    @SerializedName("message")
    @Expose
    private String message;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<String> getValidationError() {
        return validationError;
    }

    public void setValidationError(List<String> validationError) {
        this.validationError = validationError;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}

