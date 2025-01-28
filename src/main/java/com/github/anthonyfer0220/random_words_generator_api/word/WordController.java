package com.github.anthonyfer0220.random_words_generator_api.word;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/v1/words")
public class WordController {

    private final WordService wordService;

    public WordController(WordService wordService) {
        this.wordService = wordService;
    }

    @GetMapping
    public List<Word> getAllWords() {
        return wordService.getWords();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Object> getWordById(@PathVariable Integer id) {
        return wordService.getWordById(id);
    }

    @PostMapping
    public ResponseEntity<Object> createWord(@RequestBody Word word) {
        return wordService.newWord(word);
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
