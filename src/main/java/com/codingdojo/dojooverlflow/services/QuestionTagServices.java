package com.codingdojo.dojooverlflow.services;

import com.codingdojo.dojooverlflow.models.QuestionTag;
import com.codingdojo.dojooverlflow.repositories.QuestionRepository;
import com.codingdojo.dojooverlflow.repositories.QuestionTagRepository;
import org.springframework.stereotype.Service;

@Service
public class QuestionTagServices {
    private final QuestionTagRepository questionTagRepository;
    public QuestionTagServices(QuestionTagRepository questionTagRepository){
        this.questionTagRepository = questionTagRepository;
    }

    //Crear question tag (guardar)
    public QuestionTag guardadQuestionTag(QuestionTag questionTag){
        return questionTagRepository.save(questionTag);
    }
}
