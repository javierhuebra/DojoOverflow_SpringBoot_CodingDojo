package com.codingdojo.dojooverlflow.repositories;

import com.codingdojo.dojooverlflow.models.Tag;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends CrudRepository<Tag,Long> {
}
