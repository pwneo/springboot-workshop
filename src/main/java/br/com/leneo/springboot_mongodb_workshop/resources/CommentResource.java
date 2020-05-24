package br.com.leneo.springboot_mongodb_workshop.resources;

import br.com.leneo.springboot_mongodb_workshop.domains.Comment;
import br.com.leneo.springboot_mongodb_workshop.dtos.CommentDTO;
import br.com.leneo.springboot_mongodb_workshop.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/comments")
public class CommentResource {

    @Autowired
    private CommentService service;

    @GetMapping
    public ResponseEntity<List<CommentDTO>> findAll() {
        List<CommentDTO> commentDTOS = service.findAll().stream().map(comment -> new CommentDTO(comment)).collect(Collectors.toList());
        return ResponseEntity.ok().body(commentDTOS);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CommentDTO> findById(@PathVariable String id) {
        return ResponseEntity.ok().body(new CommentDTO(this.service.findById(id)));
    }

    @PostMapping
    public ResponseEntity<CommentDTO> create(@RequestBody CommentDTO commentDTO) {
        Comment comment = service.insert(service.fromDtoToComment(commentDTO));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(comment.getId()).toUri();
        return ResponseEntity.created(uri).body(new CommentDTO(comment));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CommentDTO> update(@PathVariable String id, @RequestBody CommentDTO commentDTO) {
        commentDTO.setId(id);
        Comment comment = this.service.update(service.fromDtoToComment(commentDTO));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(commentDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(new CommentDTO(comment));
    }
}