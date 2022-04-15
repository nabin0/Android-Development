package com.nabin.fragmentsdemo;

import android.os.Parcel;
import android.os.Parcelable;

public class Employee implements Parcelable {
    private String mName;
    private String mAddress;
    private Integer age;


    public Employee(String mName, String mAddress, Integer age) {
        this.mName = mName;
        this.mAddress = mAddress;
        this.age = age;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmAddress() {
        return mAddress;
    }

    public void setmAddress(String mAddress) {
        this.mAddress = mAddress;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mName);
        dest.writeString(this.mAddress);
        dest.writeValue(this.age);
    }

    public void readFromParcel(Parcel source) {
        this.mName = source.readString();
        this.mAddress = source.readString();
        this.age = (Integer) source.readValue(Integer.class.getClassLoader());
    }

    protected Employee(Parcel in) {
        this.mName = in.readString();
        this.mAddress = in.readString();
        this.age = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Parcelable.Creator<Employee> CREATOR = new Parcelable.Creator<Employee>() {
        @Override
        public Employee createFromParcel(Parcel source) {
            return new Employee(source);
        }

        @Override
        public Employee[] newArray(int size) {
            return new Employee[size];
        }
    };
}
