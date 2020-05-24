package br.com.leneo.springboot_mongodb_workshop.services;

import br.com.leneo.springboot_mongodb_workshop.domains.Comment;
import br.com.leneo.springboot_mongodb_workshop.dtos.CommentDTO;
import br.com.leneo.springboot_mongodb_workshop.repositories.CommentRepository;
import br.com.leneo.springboot_mongodb_workshop.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository repository;

    public List<Comment> findAll() {
        return this.repository.findAll();
    }

    public Comment findById(String id) {
        return this.repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id));
    }

    public Comment insert(Comment comment) {
        return repository.insert(comment);
    }

    public void delete(String id) {
        findById(id);
        repository.deleteById(id);
    }

    public Comment update(Comment comment) {
        Comment commentNew = repository.findById(comment.getId()).get();
        commentNew.setDate(comment.getDate());
        commentNew.setAuthor(comment.getAuthor());
        commentNew.setText(comment.getText());
        commentNew.setAuthor(comment.getAuthor());
        return this.repository.save(commentNew);
    }

    public Comment fromDtoToComment(CommentDTO objDto) {
        return new Comment(objDto.getId(), objDto.getText(), objDto.getDate(), objDto.getAuthor());
    }
}