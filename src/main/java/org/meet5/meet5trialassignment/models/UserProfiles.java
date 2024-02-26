package org.meet5.meet5trialassignment.models;
import java.util.*;


public class UserProfiles {
    private Integer id;
    private String firstName;
    private String lastName;
    private Integer age;
    private String email;
    private Date dateOfBirth;
    private List<UserDefinedFields<Object>> userDefinedFields;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setUserDefinedFields(List<UserDefinedFields<Object>> userDefinedFields) {
        this.userDefinedFields = userDefinedFields;
    }
    public List<UserDefinedFields<Object>> getUserDefinedFields() {
        return userDefinedFields;
    }
}