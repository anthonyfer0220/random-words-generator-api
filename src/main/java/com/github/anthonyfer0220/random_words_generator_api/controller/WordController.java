package com.github.anthonyfer0220.random_words_generator_api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.github.anthonyfer0220.random_words_generator_api.model.Word;
import com.github.anthonyfer0220.random_words_generator_api.service.ApiService;
import com.github.anthonyfer0220.random_words_generator_api.service.WordService;


@RestController
@RequestMapping("/api/v1/words")
public class WordController {

    private final WordService wordService;
    private final ApiService apiService;

    public WordController(WordService wordService, ApiService apiService) {
        this.wordService = wordService;
        this.apiService = apiService;
    }

    @GetMapping
    public List<Word> getAllWords() {
        return wordService.getWords();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Object> getWordById(@PathVariable Integer id) {
        return wordService.getWordById(id);
    }

    @GetMapping("/random")
    public Word getRandWord() {
        return wordService.getRandomWord();
    }

    @PostMapping
    public ResponseEntity<Object> createWord(@RequestBody Word word) {
        return wordService.newWord(word);
    }

    @PostMapping("/populate")
    public ResponseEntity<Object> populateDB() {
        return apiService.populateDatabase();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateWordById(@PathVariable Integer id, @RequestBody Word updatedWord) {
        return wordService.updateWord(id, updatedWord);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteWordById(@PathVariable Integer id) {
        return wordService.deleteWord(id);
    }

}
