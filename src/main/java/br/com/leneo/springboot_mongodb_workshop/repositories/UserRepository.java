package br.com.leneo.springboot_mongodb_workshop.repositories;

import br.com.leneo.springboot_mongodb_workshop.domains.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    User findByNameLike(String name);
}
