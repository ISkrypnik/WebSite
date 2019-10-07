package com.ilyaskrypnik.website.controller;

import com.ilyaskrypnik.website.domain.Publication;
import com.ilyaskrypnik.website.repo.PublicationRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("publication")
public class PublicationController {

    private final PublicationRepository publicationRepository;

    PublicationController(PublicationRepository publicationRepository) {
        this.publicationRepository = publicationRepository;
    }

    @GetMapping
    public Iterable<Publication> list() {
        return publicationRepository.findAll();
    }

    @GetMapping("{id}")
    public Publication getOne(@PathVariable("id") Publication publication) {
        return publication;
    }

    @PostMapping
    public Publication create(@RequestBody Publication publication) {
        return publicationRepository.save(publication);
    }

    @PutMapping("{id}")
    public Publication update(@PathVariable("id") Publication publicationFromDb, @RequestBody Publication publication) {
        BeanUtils.copyProperties(publication, publicationFromDb, "id");
        return publicationRepository.save(publicationFromDb);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Publication publication) {
        publicationRepository.delete(publication);
    }
}
