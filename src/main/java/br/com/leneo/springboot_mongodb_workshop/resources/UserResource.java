package br.com.leneo.springboot_mongodb_workshop.resources;

import br.com.leneo.springboot_mongodb_workshop.domains.User;
import br.com.leneo.springboot_mongodb_workshop.dto.UserDTO;
import br.com.leneo.springboot_mongodb_workshop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        List<User> users = this.service.findAll();
        List<UserDTO> usersDtos = users.stream().map(user -> new UserDTO(user)).collect(Collectors.toList());
        return ResponseEntity.ok().body(usersDtos);
    }

    @GetMapping (value = "/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable String id) {
        User user = this.service.findById(id);
        return ResponseEntity.ok().body(new UserDTO(user));
    }
}
