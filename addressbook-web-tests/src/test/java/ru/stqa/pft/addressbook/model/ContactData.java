package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {
    private int id = Integer.MAX_VALUE;
    private String fname;
    private String mname;
    private String lname;
    private String nname;
    private String phoneNumber;
    private String email;
    private String group;

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

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withFname(String fname) {
        this.fname = fname;
        return this;
    }

    public ContactData withMname(String mname) {
        this.mname = mname;
        return this;
    }

    public ContactData withLname(String lname) {
        this.lname = lname;
        return this;
    }

    public ContactData withNname(String nname) {
        this.nname = nname;
        return this;
    }

    public ContactData withPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }

    public ContactData withGroup(String group) {
        this.group = group;
        return this;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return Objects.equals(fname, that.fname) &&
                Objects.equals(lname, that.lname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fname, lname);
    }


}
