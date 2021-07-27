package com.atm_machine_web.model;

import javax.persistence.*;
import java.util.List;
@Entity(name = "User")
@Table(
        name = "user",
        uniqueConstraints= {
                @UniqueConstraint(name = "user_username_unique"
        , columnNames = "email")
        }
)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    @Column(name = "username", nullable = false, unique = true)
    private String userName;
    @Column(name = "type_user", nullable = false, unique = true)
    private String userType;
    @Column(name = "phoneNr", nullable = false, unique = true)
    private String phoneNr;
    @Column(name = "email",  nullable = false)
    private String email;


    public User(Long userId, String userName, String userType, String phoneNr, String email) {
        this.userId = userId;
        this.userName = userName;
        this.userType = userType;
        this.phoneNr = phoneNr;
        this.email = email;
    }

    public User() {

    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getPhoneNr() {
        return phoneNr;
    }

    public void setPhoneNr(String phoneNr) {
        this.phoneNr = phoneNr;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

