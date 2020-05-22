package br.com.leneo.springboot_mongodb_workshop.dto;

import br.com.leneo.springboot_mongodb_workshop.domains.User;

import java.io.Serializable;

public class UserDTO implements Serializable {
    private static final Long serialVersionUID = 1L;

    private String id;
    private String name;
    private String email;

    public UserDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
    }

    public UserDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void toUserDTOFrom(User user){
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
    }
}
