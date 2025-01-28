package com.github.anthonyfer0220.random_words_generator_api.word;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "word")
public class Word {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String content;

}
