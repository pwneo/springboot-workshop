package br.com.leneo.springboot_mongodb_workshop.services;

import br.com.leneo.springboot_mongodb_workshop.domains.User;
import br.com.leneo.springboot_mongodb_workshop.dtos.UserDTO;
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

    public User insert(User user) {
        return repository.insert(user);
    }

    public void delete(String id) {
        findById(id);
        repository.deleteById(id);
    }

    public User update(User user) {
        User userNew = repository.findById(user.getId()).get();
        userNew.setName(user.getName());
        userNew.setEmail(user.getEmail());
        return this.repository.save(userNew);
    }

    public User fromDTOUser(UserDTO objDto) {
        return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
    }


    public User findByName(String name){
        return this.repository.findByNameLike(name);
    }
}
