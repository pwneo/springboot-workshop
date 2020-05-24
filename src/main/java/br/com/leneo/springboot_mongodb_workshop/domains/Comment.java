package br.com.leneo.springboot_mongodb_workshop.domains;

import br.com.leneo.springboot_mongodb_workshop.dtos.AuthorDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Document
public class Comment implements Serializable {
    private static final Long serialVersionUID = 1L;

    @Id
    private String id;
    private String text;
    private Date date;
    private AuthorDTO author;

    public Comment(String id, String text, Date date, AuthorDTO author) {
        this.id = id;
        this.text = text;
        this.date = date;
        this.author = author;
    }

    public Comment() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public AuthorDTO getAuthor() {
        return author;
    }

    public void setAuthor(AuthorDTO author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(id, comment.id) &&
                Objects.equals(text, comment.text) &&
                Objects.equals(date, comment.date) &&
                Objects.equals(author, comment.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, date, author);
    }
}
