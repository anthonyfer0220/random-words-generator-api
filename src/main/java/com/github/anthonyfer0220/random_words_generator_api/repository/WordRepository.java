package com.github.anthonyfer0220.random_words_generator_api.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.github.anthonyfer0220.random_words_generator_api.model.Word;

/**
 * Repository interface for Word entities. Provides CRUD and custom query operations
 */
@Repository
public interface WordRepository extends JpaRepository<Word, Integer> { 

    /**
     * Fetch a random word from the database
     * 
     * @return A randomly selected word
     */
    @Query(value = "SELECT * FROM word ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
    Word selectRandomWord();

    /**
     * Fetch a paginated list of words
     * 
     * @param pageable Pageable object containing page number and size
     * @return A Page of words
     */
    Page<Word> findAll(Pageable pageable);
    
}