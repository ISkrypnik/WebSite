package com.ilyaskrypnik.website.controller;

import com.ilyaskrypnik.website.dao.PublicationDao;
import com.ilyaskrypnik.website.domain.Publication;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("publication")
@RequiredArgsConstructor
@SwaggerDefinition(tags = {@Tag(name = "Publications controller", description = "Операции с сущностью Публикация")})
public class PublicationController {

    private final PublicationDao publicationDao;

    @ApiOperation(value = "Получить список всех публикаций.", notes = "Позволяет получить GET запросом список всех" +
            "имеющихся публикаций.")
    @GetMapping
    public ResponseEntity<Iterable<Publication>> list() {
        return ResponseEntity.ok(publicationDao.getAll());
    }

    @ApiOperation(value = "Получить объект Публикация по id.", notes = "Позволяет получить публикацию по id, переданному " +
            "через перменную пути.")
    @GetMapping("{id}")
    public ResponseEntity<Publication> getOne(@PathVariable("id") Publication publication) {
        return ResponseEntity.ok(publication);
    }

    @ApiOperation(value = "Создаёт объект Публикация.", notes = "Позволяет создать публикацию с переданными значениями. " +
            "Возвращается созданный объект Публикация.")
    @PostMapping
    public ResponseEntity<Publication> create(@RequestBody Publication publication) {
        return ResponseEntity.ok(publicationDao.save(publication));
    }

    @ApiOperation(value = "Имзеняет объект Публикация.", notes = "Позволяет изенить публикацию переданными значениями. " +
            "Возвращается изменённый объект Публикация.")
    @PutMapping("{id}")
    public ResponseEntity<Publication> update(@PathVariable("id") Publication publicationFromDb, @RequestBody Publication publication) {
        return ResponseEntity.ok(publicationDao.update(publicationFromDb, publication));
    }

    @ApiOperation(value = "Удалить объект Публикация.", notes = "Удаляет объект Публикация по переданному id, переданному " +
            "через перменную пути.")
    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Publication publication) {
        publicationDao.delete(publication);
    }
}
