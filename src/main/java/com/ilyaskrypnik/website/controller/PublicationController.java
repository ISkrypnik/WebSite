package com.ilyaskrypnik.website.controller;

import com.ilyaskrypnik.website.dao.PublicationDao;
import com.ilyaskrypnik.website.domain.Publication;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("publication")
@RequiredArgsConstructor
public class PublicationController {

    private final PublicationDao publicationDao;

    @GetMapping
    public ResponseEntity<Iterable<Publication>> list() {
        return ResponseEntity.ok(publicationDao.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Publication> getOne(@PathVariable("id") Publication publication) {
        return ResponseEntity.ok(publication);
    }

    @PostMapping
    public ResponseEntity<Publication> create(@RequestBody Publication publication) {
        return ResponseEntity.ok(publicationDao.save(publication));
    }

    @PutMapping("{id}")
    public ResponseEntity<Publication> update(@PathVariable("id") Publication publicationFromDb, @RequestBody Publication publication) {
        return ResponseEntity.ok(publicationDao.update(publicationFromDb, publication));
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Publication publication) {
        publicationDao.delete(publication);
    }
}
