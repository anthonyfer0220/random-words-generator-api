package com.github.anthonyfer0220.random_words_generator_api.word;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class WordService {

    private final WordRepository wordRepository;

    public WordService(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    public List<Word> getWords() {
        return wordRepository.findAll();
    }

    public ResponseEntity<Object> getWordById(Integer id) {

        Optional<Word> wordOptional = wordRepository.findById(id);

        if (wordOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Word word = wordOptional.get();

        return ResponseEntity.ok(word);
    }

    public ResponseEntity<Object> newWord(Word word) {
        
        wordRepository.save(word);
        return new ResponseEntity<>(word, HttpStatus.CREATED);
    }

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

    public ResponseEntity<Object> deleteWord(Integer id) {
        
        Optional<Word> wordOptional = wordRepository.findById(id);

        if (wordOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        wordRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
