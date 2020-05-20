package br.com.leneo.springboot_mongodb_workshop.resources;

import br.com.leneo.springboot_mongodb_workshop.domains.User;
import br.com.leneo.springboot_mongodb_workshop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
//        User maria = new User("1", "Maria", "maria@gmail.com");
//        User pedro = new User("2", "Pedro", "pedro@gmail.com");
//        User joao = new User("3", "João", "joao@gmail.com");
//        //List<User> users = Arrays.asList(maria, pedro, joao);

        return ResponseEntity.ok().body(this.service.findAll());
    }
}
