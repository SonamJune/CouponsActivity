
package com.manash.purpllebase.model.common;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Option implements Parcelable {
    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("display_name")
    @Expose
    private String displayName;
    @SerializedName("icon_type")
    @Expose
    private String iconType;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("icon_hover")
    @Expose
    private String iconHover;
    @SerializedName("is_selected")
    @Expose
    private boolean isSelected;

    private String displayType;

    private String parameter;

    private List<Option> list;

    private String rowType;

    public Option() {

    }

    private Option(Parcel in) {
        value = in.readString();
        displayName = in.readString();
        iconType = in.readString();
        icon = in.readString();
        iconHover = in.readString();
        isSelected = in.readByte() != 0;
        displayType = in.readString();
        parameter = in.readString();
        list = in.createTypedArrayList(Option.CREATOR);
        rowType = in.readString();
    }

    public static final Creator<Option> CREATOR = new Creator<Option>() {
        @Override
        public Option createFromParcel(Parcel in) {
            return new Option(in);
        }

        @Override
        public Option[] newArray(int size) {
            return new Option[size];
        }
    };

    public String getIconHover() {
        return iconHover;
    }

    public void setIconHover(String iconHover) {
        this.iconHover = iconHover;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIconType() {
        return iconType;
    }

    public void setIconType(String iconType) {
        this.iconType = iconType;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayType() {
        return displayType;
    }

    public void setDisplayType(String displayType) {
        this.displayType = displayType;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public List<Option> getList() {
        return list;
    }

    public void setList(List<Option> options) {
        this.list = options;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public String getRowType() {
        return rowType;
    }

    public void setRowType(String rowType) {
        this.rowType = rowType;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(value);
        parcel.writeString(displayName);
        parcel.writeString(iconType);
        parcel.writeString(icon);
        parcel.writeString(iconHover);
        parcel.writeByte((byte) (isSelected ? 1 : 0));
        parcel.writeString(displayType);
        parcel.writeString(parameter);
        parcel.writeTypedList(list);
        parcel.writeString(rowType);
    }
}
