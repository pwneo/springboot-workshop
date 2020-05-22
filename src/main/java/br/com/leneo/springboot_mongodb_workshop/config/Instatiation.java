package br.com.leneo.springboot_mongodb_workshop.config;

import br.com.leneo.springboot_mongodb_workshop.domains.User;
import br.com.leneo.springboot_mongodb_workshop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class Instatiation implements CommandLineRunner {

    @Autowired
    private UserRepository repository;

    @Override
    public void run(String... args) throws Exception {
//        User maria = new User(null, "Maria", "maria@gmail.com");
//        User pedro = new User(null, "Pedro", "pedro@gmail.com");
//        User joao = new User(null, "João", "joao@gmail.com");
//        repository.saveAll(Arrays.asList(maria, pedro, joao));
    }
}
