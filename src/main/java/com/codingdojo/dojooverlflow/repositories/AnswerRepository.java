package com.codingdojo.dojooverlflow.repositories;

import com.codingdojo.dojooverlflow.models.Answer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends CrudRepository<Answer,Long> {
    List<Answer> findAll();
    List<Answer> findAllByQuestionId(Long questionId);//Esto es como una consulta SQL pero el orm la hace automática
}
