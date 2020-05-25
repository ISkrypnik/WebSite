package com.ilyaskrypnik.website.controller;

import com.ilyaskrypnik.website.dao.PublicationDao;
import com.ilyaskrypnik.website.domain.Publication;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("publication")
@RequiredArgsConstructor
public class PublicationController {

    private final PublicationDao publicationDao;

    @GetMapping
    public Iterable<Publication> list() {
        return publicationDao.getAll();
    }

    @GetMapping("{id}")
    public Publication getOne(@PathVariable("id") Publication publication) {
        return publication;
    }

    @PostMapping
    public Publication create(@RequestBody Publication publication) {
        return publicationDao.save(publication);
    }

    @PutMapping("{id}")
    public Publication update(@PathVariable("id") Publication publicationFromDb, @RequestBody Publication publication) {
        return publicationDao.update(publicationFromDb, publication);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Publication publication) {
        publicationDao.delete(publication);
    }
}
