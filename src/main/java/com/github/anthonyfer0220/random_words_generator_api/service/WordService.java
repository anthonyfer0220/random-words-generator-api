package com.github.anthonyfer0220.random_words_generator_api.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.github.anthonyfer0220.random_words_generator_api.model.Word;
import com.github.anthonyfer0220.random_words_generator_api.repository.WordRepository;

/**
 * Provides business logic for Word operations
 */
@Service
public class WordService {

    private final WordRepository wordRepository;

    public WordService(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    /**
     * Fetch a paginated list of words
     * 
     * @param pageable Pageable object specifying the page and size
     * @return A Page of words
     */
    public Page<Word> getWords(Pageable pageable) {
        return wordRepository.findAll(pageable);
    }

    /**
     * Fetch a word by ID
     * 
     * @param id The ID of the word to fetch
     * @return A ResponseEntity containing the word or a 404 status
     */
    public ResponseEntity<Object> getWordById(Integer id) {

        Optional<Word> wordOptional = wordRepository.findById(id);

        if (wordOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Word word = wordOptional.get();

        return ResponseEntity.ok(word);
    }

    /**
     * Fetch a random word from the database
     * 
     * @return A random word
     */
    public Word getRandomWord() {
        return wordRepository.selectRandomWord();
    }

    /**
     * Create a new word
     * 
     * @param word The word to save
     * @return A ResponseEntity with the saved word and a 201 status
     */
    public ResponseEntity<Object> newWord(Word word) {
        
        wordRepository.save(word);
        return new ResponseEntity<>(word, HttpStatus.CREATED);
    }

    /**
     * Update an existing word in the database
     * 
     * @param id The ID of the word to update
     * @param updatedWord The updated word
     * @return A ResponseEntity with the updated word or a 404 status if not found
     */
    public ResponseEntity<Object> updateWord(Integer id, Word updatedWord) {

        Optional<Word> wordOptional = wordRepository.findById(id);

        if (wordOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Word existingWord = wordOptional.get();
        existingWord.setContent(updatedWord.getContent());
        wordRepository.save(existingWord);

        return ResponseEntity.ok(existingWord);
    }

    /**
     * Delete all words in the database
     * 
     * @return A ResponseEntity indicating the status of the deletion
     */
    public ResponseEntity<Object> deleteAllWords() {
        
        if (wordRepository.count() == 0) {
            return ResponseEntity.noContent().build();
        }

        wordRepository.deleteAll();

        return ResponseEntity.ok("All words have been deleted");
    }

    /**
     * Delete a word by ID
     * 
     * @param id The ID of the word to delete
     * @return A ResponseEntity indicating the status of the deletion
     */
    public ResponseEntity<Object> deleteWord(Integer id) {
        
        Optional<Word> wordOptional = wordRepository.findById(id);

        if (wordOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        wordRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }
    
}
