package br.com.leneo.springboot_mongodb_workshop.dto;

import br.com.leneo.springboot_mongodb_workshop.domains.User;

import java.io.Serializable;

public class AuthorDTO implements Serializable {
    private static final Long serialVersionUID = 1L;

    private String id;
    private String name;

    public AuthorDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
    }

    public AuthorDTO() {
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

    public void toAuthorDTOFrom(User user) {
        this.id = user.getId();
        this.name = user.getName();
    }
}