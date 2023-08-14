package com.mjc.school.repository.implementation;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.interfaces.NewsRepositoryInterface;
import com.mjc.school.repository.interfaces.TagRepositoryInterface;
import com.mjc.school.repository.model.implementation.NewsEntity;
import com.mjc.school.repository.model.implementation.TagEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class TagRepository extends AbstractRepository<TagEntity, Long> implements TagRepositoryInterface {
    @Qualifier("newsRepository")
    private NewsRepositoryInterface newsRepository;

    @Override
    @Transactional(readOnly = true)
    public List<TagEntity> readByNewsId(Long newsId) {
        return newsRepository.readById(newsId).get().getTags();
    }
}
