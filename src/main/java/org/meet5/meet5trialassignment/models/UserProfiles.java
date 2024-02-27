package org.meet5.meet5trialassignment.models;

import java.util.*;

import static org.meet5.meet5trialassignment.util.Validations.*;


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
        if (firstName != null && !firstName.isEmpty()) {
            this.firstName = firstName;
        } else {
            throw new IllegalArgumentException("First name cannot be null or empty");
        }
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        if (lastName != null && !lastName.isEmpty()) {
            this.lastName = lastName;
        } else {
            throw new IllegalArgumentException("Last name cannot be null or empty");
        }
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
        if (email != null && !email.isEmpty()) {
            if (isValidEmail(email)) {
                this.email = email;
            } else {
                throw new IllegalArgumentException("Invalid email format");
            }
        } else {
            throw new IllegalArgumentException("Email name cannot be null or empty");
        }
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

    private void calculateAge() {
        if (dateOfBirth != null) {
            this.age = calculateAgeFromDateOfBirth(dateOfBirth);
        }
    }
}