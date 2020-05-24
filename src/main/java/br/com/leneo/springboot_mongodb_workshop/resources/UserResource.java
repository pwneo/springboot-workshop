package br.com.leneo.springboot_mongodb_workshop.resources;

import br.com.leneo.springboot_mongodb_workshop.domains.Post;
import br.com.leneo.springboot_mongodb_workshop.domains.User;
import br.com.leneo.springboot_mongodb_workshop.dtos.CommentDTO;
import br.com.leneo.springboot_mongodb_workshop.dtos.UserDTO;
import br.com.leneo.springboot_mongodb_workshop.services.CommentService;
import br.com.leneo.springboot_mongodb_workshop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        List<UserDTO> userDTOS = userService.findAll().stream().map(UserDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(userDTOS);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable String id) {
        return ResponseEntity.ok().body(new UserDTO(this.userService.findById(id)));
    }

    @GetMapping(value = "/{id}/posts")
    public ResponseEntity<Set<Post>> findAllPosts(@PathVariable String id) {
        return ResponseEntity.ok().body(this.userService.findById(id).getPosts());
    }

    @GetMapping(value = "/{id}/comments")
    public ResponseEntity<Set<CommentDTO>> findAllComments(@PathVariable String id) {
        Set<CommentDTO> commentsDTOS = this.commentService.findAll()
                .stream().map(CommentDTO::new)
                .filter(commentDto -> commentDto.getAuthor().getId().equals(id)).collect(Collectors.toSet());
        return ResponseEntity.ok().body(commentsDTOS);
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody UserDTO userDTO) {
        User user = userService.insert(userService.fromDTOUser(userDTO));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(user);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable String id, @RequestBody UserDTO userDTO) {
        userDTO.setId(id);
        User user = this.userService.update(userService.fromDTOUser(userDTO));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(new UserDTO(user));
    }
}