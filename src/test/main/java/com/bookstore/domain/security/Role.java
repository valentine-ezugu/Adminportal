package com.bookstore.domain.security;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

//the role persists the database
@Entity
public class Role {

    @Id
    private int Roleid;

    private String name;

    //Using set because it has no duplicate value
//one role can have multiple user
    @OneToMany(mappedBy = "role",cascade= CascadeType.ALL, fetch = FetchType.LAZY)
   private Set<UserRole>  userRoles = new HashSet<>();

    public int getRoleid() {
        return Roleid;
    }

    public void setRoleid(int roleid) {
        Roleid = roleid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }
}
