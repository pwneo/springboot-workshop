package br.com.leneo.springboot_mongodb_workshop.config;

import br.com.leneo.springboot_mongodb_workshop.domains.Post;
import br.com.leneo.springboot_mongodb_workshop.domains.User;
import br.com.leneo.springboot_mongodb_workshop.dto.AuthorDTO;
import br.com.leneo.springboot_mongodb_workshop.repositories.PostRepository;
import br.com.leneo.springboot_mongodb_workshop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

@Configuration
public class Instatiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));

        userRepository.deleteAll();
        postRepository.deleteAll();

        User maria = new User(null, "Maria", "maria@gmail.com");
        User pedro = new User(null, "Pedro", "pedro@gmail.com");
        User joao = new User(null, "João", "joao@gmail.com");

        userRepository.saveAll(Arrays.asList(maria, pedro, joao));

        Post p1 = new Post(null, simpleDateFormat.parse("21/03/2020"), "Partiu Viagem!", "Vou viajar para São Paulo. Abraços", new AuthorDTO(maria));
        Post p2 = new Post(null, simpleDateFormat.parse("23/03/2020"), "Bom dia!", "Acordei feliz hoje", new AuthorDTO(maria));

        postRepository.saveAll(Arrays.asList(p1, p2));

        maria.getPosts().addAll(Arrays.asList(p1, p2));
        userRepository.save(maria);
    }
}
