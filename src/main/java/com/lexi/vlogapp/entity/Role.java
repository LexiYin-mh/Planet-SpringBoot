package com.lexi.vlogapp.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "roles")
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "role_name")
    private String name;

    @Column(name = "allowed_resources")
    private String allowedResources;

    @Column(name = "allowed_read")
    private boolean allowedReadFlag;

    @Column(name = "allowed_create")
    private boolean allowedCreateFlag;

    @Column(name = "allowed_update")
    private boolean allowedUpdateFlag;

    @Column(name = "allowed_delete")
    private boolean allowedDeleteFlag;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private Set<User> users;


    ////////////////////// Getter & Setter //////////////////////////////
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAllowedResources() {
        return allowedResources;
    }

    public void setAllowedResources(String allowedResources) {
        this.allowedResources = allowedResources;
    }

    public boolean isAllowedReadFlag() {
        return allowedReadFlag;
    }

    public void setAllowedReadFlag(boolean allowedReadFlag) {
        this.allowedReadFlag = allowedReadFlag;
    }

    public boolean isAllowedCreateFlag() {
        return allowedCreateFlag;
    }

    public void setAllowedCreateFlag(boolean allowedCreateFlag) {
        this.allowedCreateFlag = allowedCreateFlag;
    }

    public boolean isAllowedUpdateFlag() {
        return allowedUpdateFlag;
    }

    public void setAllowedUpdateFlag(boolean allowedUpdateFlag) {
        this.allowedUpdateFlag = allowedUpdateFlag;
    }

    public boolean isAllowedDeleteFlag() {
        return allowedDeleteFlag;
    }

    public void setAllowedDeleteFlag(boolean allowedDeleteFlag) {
        this.allowedDeleteFlag = allowedDeleteFlag;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }


    ///////////////////// To String /////////////////////////


    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", allowedResources='" + allowedResources + '\'' +
                ", allowedReadFlag=" + allowedReadFlag +
                ", allowedCreateFlag=" + allowedCreateFlag +
                ", allowedUpdateFlag=" + allowedUpdateFlag +
                ", allowedDeleteFlag=" + allowedDeleteFlag +
                '}';
    }
}
