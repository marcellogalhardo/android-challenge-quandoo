package com.marcellogalhardo.quandoo.data;

import android.os.Parcel;
import android.os.Parcelable;

public class Table implements Parcelable {

    private int number;

    private Customer customer;

    private Boolean available;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Boolean isAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.number);
        dest.writeParcelable(this.customer, flags);
        dest.writeValue(this.available);
    }

    public Table() {
    }

    protected Table(Parcel in) {
        this.number = in.readInt();
        this.customer = in.readParcelable(Customer.class.getClassLoader());
        this.available = (Boolean) in.readValue(Boolean.class.getClassLoader());
    }

    public static final Creator<Table> CREATOR = new Creator<Table>() {
        @Override
        public Table createFromParcel(Parcel source) {
            return new Table(source);
        }

        @Override
        public Table[] newArray(int size) {
            return new Table[size];
        }
    };
}
