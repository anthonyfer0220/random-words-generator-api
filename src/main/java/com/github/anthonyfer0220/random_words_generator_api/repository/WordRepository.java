package com.github.anthonyfer0220.random_words_generator_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.github.anthonyfer0220.random_words_generator_api.model.Word;

@Repository
public interface WordRepository extends JpaRepository<Word, Integer> { 

    @Query(value = "SELECT * FROM word ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
    Word selectRandomWord();
}