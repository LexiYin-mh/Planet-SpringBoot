package com.lexi.vlogapp.dto;

public class RoleDto {

    private Long id;

    private String name;

    ///////////////////////////// Getter & Setter //////////////////////////////

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

    //////////////////////////// To String ///////////////////////////////////////

    @Override
    public String toString() {
        return "RoleDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
