package com.codingdojo.dojooverlflow.repositories;

import com.codingdojo.dojooverlflow.models.QuestionTag;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionTagRepository extends CrudRepository<QuestionTag, Long> {
}
