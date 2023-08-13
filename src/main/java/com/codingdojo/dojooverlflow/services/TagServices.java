package com.codingdojo.dojooverlflow.services;

import com.codingdojo.dojooverlflow.models.Question;
import com.codingdojo.dojooverlflow.models.Tag;
import com.codingdojo.dojooverlflow.repositories.TagRepository;
import org.springframework.stereotype.Service;

@Service
public class TagServices {
    private final TagRepository tagRepository;
    public TagServices(TagRepository tagRepository){
        this.tagRepository = tagRepository;
    }

    //Crear Tag
    public Tag crearTag(Tag tag){
        return tagRepository.save(tag);
    }
}
