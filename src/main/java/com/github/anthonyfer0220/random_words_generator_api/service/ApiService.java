package com.github.anthonyfer0220.random_words_generator_api.service;

import org.json.JSONArray;
import org.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.github.anthonyfer0220.random_words_generator_api.model.Word;
import com.github.anthonyfer0220.random_words_generator_api.repository.WordRepository;

/**
 * Handles external API calls and database population
 */
@Service
public class ApiService {

    private static final Logger logger = LoggerFactory.getLogger(ApiService.class);
    private static final int MAX_WORDS_PER_PATTERN = 10;

    private final WordRepository wordRepository;
    private final HttpClient client = HttpClient.newHttpClient();
    
    public ApiService(WordRepository wordRepository) {
            this.wordRepository = wordRepository;
    }

    /**
     * Fetch words from the Datamuse API using randomized patterns
     * 
     * @return A list of Word objects fetched from the API
     */
    public List<Word> fetchWordsFromApi() {

        List<String> patterns = new ArrayList<>();
        
        // Generate patterns from "a*" to "z*"
        for (char letter = 'a'; letter <= 'z'; letter++) {
            patterns.add(letter + "*");
        }

        // Randomize the order of patterns and limit to 10
        Collections.shuffle(patterns);
        List<String> limitedPatterns = patterns.subList(0,10);

        List<Word> allWords = new ArrayList<>();

        // for-each loop to fetch words
        for (String pattern : limitedPatterns) {
            try {
                
                HttpResponse<String> response = fetchApiResponse(pattern);
                List<Word> words = parse(response.body());
                allWords.addAll(words);

            } catch (IOException | InterruptedException e) {
                logger.error("Failed to fetch words for pattern: {}", pattern, e);
            }
        }

        return allWords;

    }

    /**
     * Send an HTTP request to the Datamuse API for a given pattern
     * 
     * @param pattern The word pattern to query
     * @return The HTTP response containing the API result
     * @throws IOException If the API call fails
     * @throws InterruptedException If the API call is interrupted
     */

    private HttpResponse<String> fetchApiResponse(String pattern) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.datamuse.com/words?sp=" + pattern + "&max=" + MAX_WORDS_PER_PATTERN))
                    .build();

                return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    /**
     * Parse the API response JSON into a list of Word objects
     * 
     * @param responseBody The JSON response from the API
     * @return A list of Word objects parsed from the response
     */
    private List<Word> parse(String responseBody) {

        JSONArray wordsJson = new JSONArray(responseBody);
        List<Word> words = new ArrayList<>();

        for (int i = 0; i < wordsJson.length(); i++) {
            
            JSONObject wordJson = wordsJson.getJSONObject(i);
            String content = wordJson.getString("word");

            Word wordToSave = new Word();
            wordToSave.setContent(content);

            words.add(wordToSave);
        }

        return words;
    }

    /**
     * Populate or replace the database with words fetched from the Datamuse API
     * 
     * @return A ResponseEntity with the status of the action
     */
    public ResponseEntity<Object> populateDatabase() {
        
        boolean hasWords = wordRepository.count() > 0;

        if (hasWords) {
            wordRepository.deleteAll();
        }

        List<Word> words = fetchWordsFromApi();

        wordRepository.saveAll(words);

        return ResponseEntity.status(HttpStatus.CREATED).body("Successfully populated the database with new words.");
    }

}
