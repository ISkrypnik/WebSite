package com.ilyaskrypnik.website.dao;

import com.ilyaskrypnik.website.domain.Publication;

import java.util.List;

public interface PublicationDao {

    Publication save(Publication publication);

    Publication update(Publication publicationFromDb, Publication publication);

    void delete(Publication publication);

    List<Publication> getAll();
}
