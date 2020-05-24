package br.com.leneo.springboot_mongodb_workshop.services;

import br.com.leneo.springboot_mongodb_workshop.domains.Post;
import br.com.leneo.springboot_mongodb_workshop.dtos.PostDTO;
import br.com.leneo.springboot_mongodb_workshop.repositories.PostRepository;
import br.com.leneo.springboot_mongodb_workshop.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository repository;

    public List<Post> findAll() {
        return this.repository.findAll();
    }

    public Post findById(String id) {
        return this.repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id));
    }

    public Post insert(Post post) {
        return repository.insert(post);
    }


    public void delete(String id) {
        findById(id);
        repository.deleteById(id);
    }

    public Post update(Post post) {
        Post postNew = repository.findById(post.getId()).get();
        postNew.setDate(post.getDate());
        postNew.setTitle(post.getTitle());
        postNew.setBody(post.getBody());
        postNew.setAuthor(post.getAuthor());
        return this.repository.save(postNew);
    }

    public Post fromPostToPost(PostDTO objDto) {
        return new Post(objDto.getId(), objDto.getDate(), objDto.getTitle(), objDto.getBody(), objDto.getAuthor());
    }
}
