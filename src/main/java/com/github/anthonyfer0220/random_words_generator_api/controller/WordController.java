package com.github.anthonyfer0220.random_words_generator_api.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.github.anthonyfer0220.random_words_generator_api.model.Word;
import com.github.anthonyfer0220.random_words_generator_api.service.ApiService;
import com.github.anthonyfer0220.random_words_generator_api.service.WordService;

/**
 * Handles HTTP requests for Word operations, including CRUD, random word retrieval,
 * and database population via external API
 */

@RestController
@RequestMapping("/api/v1/words")
public class WordController {

    private final WordService wordService;
    private final ApiService apiService;

    /**
     * Constructor for WordController
     * 
     * @param wordService Service for Word entity operations
     * @param apiService Service for external API interactions
     */
    public WordController(WordService wordService, ApiService apiService) {
        this.wordService = wordService;
        this.apiService = apiService;
    }

    /**
     * Get a paginated list of words
     * 
     * @param page The page number (0-based)
     * @param size The number of records per page
     * @return A paginated list of words
     */
    @GetMapping
    public Page<Word> getAllWords(
        @RequestParam(defaultValue = "0") int page, 
        @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        return wordService.getWords(pageable);
    }
    
    /**
     * Get a specific word by ID
     * 
     * @param id The ID of the word to retrieve
     * @return The word if found, or a 404 status
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> getWordById(@PathVariable Integer id) {
        return wordService.getWordById(id);
    }

    /** Get a random word
     * 
     * @return A randomly selected word from the database
     */
    @GetMapping("/random")
    public Word getRandWord() {
        return wordService.getRandomWord();
    }

    /**
     * Create a new word
     * 
     * @param word The Word object to save
     * @return The created word with a 201 status
     */
    @PostMapping
    public ResponseEntity<Object> createWord(@RequestBody Word word) {
        return wordService.newWord(word);
    }

    /**
     * Populate the database with random words fetched from an external API
     * 
     * @return The status of the population action
     */
    @PostMapping("/populate")
    public ResponseEntity<Object> populateDB() {
        return apiService.populateDatabase();
    }

    /**
     * Update a word by ID
     * 
     * @param id The ID of the word to update
     * @param updatedWord The updated word details
     * @return The updated word or a 404 status if not found
     */
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateWordById(@PathVariable Integer id, @RequestBody Word updatedWord) {
        return wordService.updateWord(id, updatedWord);
    }
    
    /**
     * Delete a word by ID
     * 
     * @param id The ID of the word to delete
     * @return A 200 status if deletion is successful
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteWordById(@PathVariable Integer id) {
        return wordService.deleteWord(id);
    }

}
