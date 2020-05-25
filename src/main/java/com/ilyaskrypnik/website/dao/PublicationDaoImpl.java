package com.ilyaskrypnik.website.dao;

import com.ilyaskrypnik.website.domain.Publication;
import com.ilyaskrypnik.website.repo.PublicationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class PublicationDaoImpl implements PublicationDao {

    private final PublicationRepository publicationRepository;

    @Override
    public Publication save(Publication publication) {
        return publicationRepository.save(publication);
    }

    @Override
    public Publication update(Publication publicationFromDb, Publication publication) {
        log.info("Update publication: source - {}, updating - {}", publication, publicationFromDb);
        BeanUtils.copyProperties(publication, publicationFromDb, "id");
        return publicationRepository.save(publicationFromDb);
    }

    @Override
    public void delete(Publication publication) {
        publicationRepository.delete(publication);
    }

    @Override
    public List<Publication> getAll() {
        return publicationRepository.findAll();
    }
}
