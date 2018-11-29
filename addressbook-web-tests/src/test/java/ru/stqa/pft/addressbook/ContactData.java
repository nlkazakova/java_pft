package ru.stqa.pft.addressbook;

public class ContactData {
    private final String fname;
    private final String mname;
    private final String lname;
    private final String nname;
    private final String phoneNumber;
    private final String email;

    public ContactData(String fname, String mname, String lname, String nname, String phoneNumber, String email) {
        this.fname = fname;
        this.mname = mname;
        this.lname = lname;
        this.nname = nname;
        this.phoneNumber = phoneNumber;
        this.email = email;
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
}
