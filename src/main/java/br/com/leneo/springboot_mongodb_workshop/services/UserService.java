package br.com.leneo.springboot_mongodb_workshop.services;

import br.com.leneo.springboot_mongodb_workshop.domains.User;
import br.com.leneo.springboot_mongodb_workshop.repositories.UserRepository;
import br.com.leneo.springboot_mongodb_workshop.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> findAll() {
        return this.repository.findAll();
    }

    public User findById(String id) {
        return this.repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id));
    }
}
