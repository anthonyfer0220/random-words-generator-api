package com.github.anthonyfer0220.random_words_generator_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.anthonyfer0220.random_words_generator_api.model.Word;

public interface WordRepository extends JpaRepository<Word, Integer> { }