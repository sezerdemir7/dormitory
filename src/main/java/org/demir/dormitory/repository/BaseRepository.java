package org.demir.dormitory.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface BaseRepository<T, ID> extends MongoRepository<T, ID> {

    List<T> findAllByIsDeletedFalse();

    Optional<T> findByIdAndIsDeletedFalse(ID id);

    default Optional<T> findById(ID id) {
        return findByIdAndIsDeletedFalse(id);
    }
}
