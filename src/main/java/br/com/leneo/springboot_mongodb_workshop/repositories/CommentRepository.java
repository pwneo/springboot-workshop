package br.com.leneo.springboot_mongodb_workshop.repositories;

import br.com.leneo.springboot_mongodb_workshop.domains.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {
}
