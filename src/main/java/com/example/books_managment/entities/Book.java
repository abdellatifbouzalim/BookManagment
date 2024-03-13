package com.example.books_managment.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Entity
@Data

public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank

    @Size(min = 10 ,message= "size  should be greter than 500")
    private String title;

    @NotBlank

    @Size(min = 5 ,message= "size  should be greter than 500")
    private String description;

    @ManyToOne
    @JoinColumn(name = "author_id")
    @JsonIgnore
    private Author author;
}
