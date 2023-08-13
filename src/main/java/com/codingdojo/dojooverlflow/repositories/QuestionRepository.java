package com.codingdojo.dojooverlflow.repositories;

import com.codingdojo.dojooverlflow.models.Question;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends CrudRepository<Question, Long> {
}
