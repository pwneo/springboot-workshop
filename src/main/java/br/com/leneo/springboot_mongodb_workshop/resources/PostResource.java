package br.com.leneo.springboot_mongodb_workshop.resources;

import br.com.leneo.springboot_mongodb_workshop.domains.Post;
import br.com.leneo.springboot_mongodb_workshop.dtos.PostDTO;
import br.com.leneo.springboot_mongodb_workshop.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

    @Autowired
    private PostService service;

    @GetMapping
    public ResponseEntity<List<PostDTO>> findAll() {
        List<PostDTO> postDTOS = service.findAll().stream().map(PostDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(postDTOS);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PostDTO> findById(@PathVariable String id) {
        return ResponseEntity.ok().body(new PostDTO(this.service.findById(id)));
    }

    @PostMapping
    public ResponseEntity<PostDTO> create(@RequestBody PostDTO postDTO) {
        Post post = service.insert(service.fromPostToPost(postDTO));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId()).toUri();
        return ResponseEntity.created(uri).body(new PostDTO(post));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PostDTO> update(@PathVariable String id, @RequestBody PostDTO postDTO) {
        postDTO.setId(id);
        Post post = this.service.update(service.fromPostToPost(postDTO));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId()).toUri();
        return ResponseEntity.created(uri).body(new PostDTO(post));
    }
}
