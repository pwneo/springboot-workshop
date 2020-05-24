package br.com.leneo.springboot_mongodb_workshop.configs;

import br.com.leneo.springboot_mongodb_workshop.domains.Comment;
import br.com.leneo.springboot_mongodb_workshop.domains.Post;
import br.com.leneo.springboot_mongodb_workshop.domains.User;
import br.com.leneo.springboot_mongodb_workshop.dtos.AuthorDTO;
import br.com.leneo.springboot_mongodb_workshop.repositories.CommentRepository;
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

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public void run(String... args) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));

        userRepository.deleteAll();
        postRepository.deleteAll();
        commentRepository.deleteAll();

        User u1 = new User(null, "Maria", "maria@gmail.com");
        User u2 = new User(null, "Pedro", "pedro@gmail.com");
        User u3 = new User(null, "João", "joao@gmail.com");
        userRepository.saveAll(Arrays.asList(u1, u2, u3));

        Post p1 = new Post(null, simpleDateFormat.parse("21/03/2020"), "Partiu Viagem!", "Vou viajar para São Paulo. Abraços", new AuthorDTO(u1));
        Post p2 = new Post(null, simpleDateFormat.parse("23/03/2020"), "Bom dia!", "Acordei feliz hoje", new AuthorDTO(u2));
        postRepository.saveAll(Arrays.asList(p1, p2));

        Comment c1 = new Comment(null, "Boa viagem, minha amiga!", simpleDateFormat.parse("21/03/2020"), new AuthorDTO(u2));
        Comment c2 = new Comment(null, "Aproveite Bastante!", simpleDateFormat.parse("22/03/2020"), new AuthorDTO(u3));

        Comment c3 = new Comment(null, "Bom dia para voce também!", simpleDateFormat.parse("22/03/2020"), new AuthorDTO(u1));
        commentRepository.saveAll(Arrays.asList(c1, c2, c3));

        p1.getComments().addAll(Arrays.asList(c1, c2));
        p2.getComments().addAll(Arrays.asList(c3));

        postRepository.saveAll(Arrays.asList(p1, p2));

        u1.getPosts().addAll(Arrays.asList(p1));
        u2.getPosts().addAll(Arrays.asList(p2));

        userRepository.save(u1);
        userRepository.save(u2);
    }
}
