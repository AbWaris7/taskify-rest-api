package com.khan.code.taskify_rest_api.response;

import com.khan.code.taskify_rest_api.entity.Authority;

import java.util.List;

public class UserResponse {

    private long id;

    private String fullName;
    private String email;
    private List<Authority> authorityList;


    public UserResponse(long id, String fullName, String email, List<Authority> authorityList) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.authorityList = authorityList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Authority> getAuthorityList() {
        return authorityList;
    }

    public void setAuthorityList(List<Authority> authorityList) {
        this.authorityList = authorityList;
    }
}
