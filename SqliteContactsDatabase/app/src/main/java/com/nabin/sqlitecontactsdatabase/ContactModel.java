package com.nabin.sqlitecontactsdatabase;

public class ContactModel {
    int id;
    String name, contact_no;

    public ContactModel(int id, String name, String contact_no) {
        this.id = id;
        this.name = name;
        this.contact_no = contact_no;
    }

    public ContactModel(String name, String contact_no) {
        this.name = name;
        this.contact_no = contact_no;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact_no() {
        return contact_no;
    }

    public void setContact_no(String contact_no) {
        this.contact_no = contact_no;
    }
}
