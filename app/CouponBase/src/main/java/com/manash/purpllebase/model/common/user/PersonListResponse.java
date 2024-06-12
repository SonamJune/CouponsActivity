package com.manash.purpllebase.model.common.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PersonListResponse {
    private String status;
    private String message;
    @SerializedName("person_id")
    @Expose
    private String personId;
    @SerializedName("list")
    @Expose
    private List<Person> personList;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }

    public String getMessage() {
        return message;
    }

    public String getPersonId() {
        return personId;
    }
}
