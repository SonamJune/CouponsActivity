package com.manash.purpllebase.model.common.user;

public class PostAddressResponse {
    private String type;
    private String status;
    private String address_id;
    private String message;
    private String statusMessage;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    /**
     * @return The type
     */
    public String getType() {
        return type;
    }

    /**
     * @param Type The -type
     */
    public void setType(String Type) {
        this.type = Type;
    }

    /**
     * @return The status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return The addressId
     */
    public String
    getAddressId() {
        return address_id;
    }

    /**
     * @param addressId The addressId
     */
    public void setAddressId(String addressId) {
        this.address_id = addressId;
    }

}
