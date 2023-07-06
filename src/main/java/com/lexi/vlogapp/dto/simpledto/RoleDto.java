package com.lexi.vlogapp.dto.simpledto;

import java.util.Set;

public class RoleDto {

    private Long id;

    private String name;

    private String allowedResources;

    private boolean allowedReadFlag;

    private boolean allowedCreateFlag;

    private boolean allowedUpdateFlag;

    private boolean allowedDeleteFlag;

    private Set<UserSimpleDto> userDtos;

    ///////////////// Converter /////////////////
    // code for converter if needed

    ////////////////////// Getter & Setter ///////////////////////
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

    public Set<UserSimpleDto> getUserSimpleDtos() {
        return userDtos;
    }

    public void setUserSimpleDtos(Set<UserSimpleDto> userSimpleDtos) {
        this.userDtos = userSimpleDtos;
    }

    //////////////////// To String ////////////////////////
    @Override
    public String toString() {
        return "RoleSimpleDto{" +
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
