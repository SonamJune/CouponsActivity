package com.manash.purpllebase.model.common;

import com.google.gson.annotations.SerializedName;

public class AlertMessage {
    private String heading;
    private String type;
    private String message;
    @SerializedName("button_text")
    private String buttonText;
    private String action;
    @SerializedName("action_value")
    private String actionValue;
    private String moduleType;
    @SerializedName("type_icon")
    private String typeIcon;

    public String getHeading() {
        return heading;
    }

    public String getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    public String getButtonText() {
        return buttonText;
    }

    public String getAction() {
        return action;
    }

    public String getActionValue() {
        return actionValue;
    }

    public String getModuleType() {
        return moduleType;
    }

    public String getTypeIcon() {
        return typeIcon;
    }

    public void setTypeIcon(String typeIcon) {
        this.typeIcon = typeIcon;
    }
}
