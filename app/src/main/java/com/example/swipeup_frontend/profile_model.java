package com.example.swipeup_frontend;

public class profile_model {

    String _id;
    String reg_id;
    String username;
    String pagename;
    String birthdate;
    String email;
    String phone;
    String name;
    String website;
    String img_url;
    String bio;

    public profile_model(String img_url) {
        this.img_url = img_url;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public void set_Id(String _id) {
        this._id = _id;
    }

    public void setReg_id(String reg_id) {
        this.reg_id = reg_id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPagename(String pagename) {
        this.pagename = pagename;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String get_Id() {
        return _id;
    }

    public String getReg_id() {
        return reg_id;
    }

    public String getUsername() {
        return username;
    }

    public String getPagename() {
        return pagename;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getName() {
        return name;
    }

    public String getWebsite() {
        return website;
    }

    public String getBio() {
        return bio;
    }


}
