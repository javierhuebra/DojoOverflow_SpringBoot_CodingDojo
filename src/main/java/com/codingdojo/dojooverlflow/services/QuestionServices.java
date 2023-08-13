package com.codingdojo.dojooverlflow.services;

import com.codingdojo.dojooverlflow.models.Question;
import com.codingdojo.dojooverlflow.repositories.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServices {
    private final QuestionRepository questionRepository;
    public QuestionServices(QuestionRepository questionRepository){
        this.questionRepository = questionRepository;
    }

    //Crear Question
    public Question crearQuestion(Question question){
        return questionRepository.save(question);
    }

    //Todas las questions
    public List<Question> allQuestions(){
        return (List<Question>) questionRepository.findAll();
    }//Preguntar por que me esta devolvuendo un iterable en vez de una lista

    //Buscar question por id
    public Question buscarQuestion(Long id){
        return questionRepository.findById(id).orElse(null);
    }
}
