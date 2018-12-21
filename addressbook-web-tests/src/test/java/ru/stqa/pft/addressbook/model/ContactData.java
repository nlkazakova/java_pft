package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {
    private int id;
    private final String fname;
    private final String mname;
    private final String lname;
    private final String nname;
    private final String phoneNumber;
    private final String email;
    private String group;

    public ContactData(String fname, String mname, String lname, String nname, String phoneNumber, String email, String group) {
        this.id = 0;
        this.fname = fname;
        this.mname = mname;
        this.lname = lname;
        this.nname = nname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.group = group;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id &&
                Objects.equals(fname, that.fname) &&
                Objects.equals(lname, that.lname) &&
                Objects.equals(phoneNumber, that.phoneNumber) &&
                Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fname, lname, phoneNumber, email);
    }

    public ContactData(int id, String lname, String fname, String email, String phoneNumber) {
        this.id = id;
        this.fname = fname;
        this.mname = null;
        this.lname = lname;
        this.nname = null;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.group = null;
    }


    public int getId() {
        return id;
    }

    public String getFname() {
        return fname;
    }

    public String getMname() {
        return mname;
    }

    public String getLname() {
        return lname;
    }

    public String getNname() {
        return nname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getGroup() {
        return group;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id='" + id + '\'' +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}
