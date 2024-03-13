package com.example.books_managment.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(min = 10 ,message= "size  should be greter than 100")
    private String firstName;
    private String lastName;

    @NotBlank
    @Size(min = 10 ,message= "size  should be greter than 500")

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Book> books;

}