package com.github.anthonyfer0220.random_words_generator_api.model;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Represents a Word entity in the database
 */
@Data
@Entity
@Table(name = "word")
public class Word {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id; // Unique identifier for the Word

    private String content; // The actual word

}
