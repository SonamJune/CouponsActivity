package com.manash.purpllebase.model.common.user;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Address implements Parcelable {
    private String Type;
    private String addressee;
    private String street1;
    private String street2;
    private String city;
    private String stateId;
    private String addressId;
    private String stateName;
    private String postalCode;
    private String phone;
    private String country;
    private boolean isActive;
    @SerializedName("alternate_phone")
    private String alternatePhone;
    @SerializedName("is_default")
    private int isDefault;
    @SerializedName("address_type")
    private String addressType;
    private String landmark;
    private boolean isChecked;
    @SerializedName("lable")
    private String label;

    @SerializedName("house_no")
    private String houseNo;

    public String getHouse_no() {
        return houseNo;
    }

    public void setHouse_no(String house_no) {
        this.houseNo = house_no;
    }

    public int getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(int isDefault) {
        this.isDefault = isDefault;
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    /**
     * @return The Type
     */
    public String getType() {
        return Type;
    }

    /**
     * @param Type The -type
     */
    public void setType(String Type) {
        this.Type = Type;
    }

    /**
     * @return The addressee
     */
    public String getAddressee() {
        return addressee;
    }

    /**
     * @param addressee The addressee
     */
    public void setAddressee(String addressee) {
        this.addressee = addressee;
    }

    /**
     * @return The street1
     */
    public String getStreet1() {
        return street1;
    }

    /**
     * @param street1 The street1
     */
    public void setStreet1(String street1) {
        this.street1 = street1;
    }

    /**
     * @return The street2
     */
    public String getStreet2() {
        return street2;
    }

    /**
     * @param street2 The street2
     */
    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    /**
     * @return The city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city The city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return The stateId
     */
    public String getStateId() {
        return stateId;
    }

    /**
     * @param stateId The stateId
     */
    public void setStateId(String stateId) {
        this.stateId = stateId;
    }

    /**
     * @return The stateName
     */
    public String getStateName() {
        return stateName;
    }

    /**
     * @param stateName The stateName
     */
    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    /**
     * @return The postalCode
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * @param postalCode The postalCode
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * @return The phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone The phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return The country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country The country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public boolean equals(Object v) {
        return ((Address) v).postalCode.equalsIgnoreCase(this.postalCode);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + (this.postalCode != null ? this.postalCode.hashCode() : 0);
        return hash;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getAlternatePhone() {
        return alternatePhone;
    }

    public void setAlternatePhone(String alternatePhone) {
        this.alternatePhone = alternatePhone;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "Address{" +
                "postalCode='" + postalCode + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.Type);
        dest.writeString(this.addressee);
        dest.writeString(this.street1);
        dest.writeString(this.street2);
        dest.writeString(this.city);
        dest.writeString(this.stateId);
        dest.writeString(this.addressId);
        dest.writeString(this.stateName);
        dest.writeString(this.postalCode);
        dest.writeString(this.phone);
        dest.writeString(this.alternatePhone);
        dest.writeString(this.country);
        dest.writeString(this.houseNo);
        dest.writeByte(this.isActive ? (byte) 1 : (byte) 0);
        dest.writeInt(this.isDefault);
        dest.writeString(this.addressType);
        dest.writeString(this.landmark);
        dest.writeByte(this.isChecked ? (byte) 1 : (byte) 0);
    }

    public Address() {
    }

    protected Address(Parcel in) {
        this.Type = in.readString();
        this.addressee = in.readString();
        this.houseNo=in.readString();
        this.street1 = in.readString();
        this.street2 = in.readString();
        this.city = in.readString();
        this.stateId = in.readString();
        this.addressId = in.readString();
        this.stateName = in.readString();
        this.postalCode = in.readString();
        this.phone = in.readString();
        this.alternatePhone = in.readString();
        this.country = in.readString();
        this.isActive = in.readByte() != 0;
        this.isDefault = in.readInt();
        this.addressType = in.readString();
        this.landmark = in.readString();
        this.isChecked = in.readByte() != 0;
    }

    public static final Parcelable.Creator<Address> CREATOR = new Parcelable.Creator<Address>() {
        @Override
        public Address createFromParcel(Parcel source) {
            return new Address(source);
        }

        @Override
        public Address[] newArray(int size) {
            return new Address[size];
        }
    };
}