package com.github.anthonyfer0220.random_words_generator_api.word;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WordRepository extends JpaRepository<Word, Integer> { }