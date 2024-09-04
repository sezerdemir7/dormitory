package org.demir.dormitory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;


@NoRepositoryBean
public interface BaseRepository<T, ID> extends JpaRepository<T, ID> {

    List<T> findAllByIsDeletedFalse();

    Optional<T> findByIdAndIsDeletedFalse(ID id);

    @Override
    default Optional<T> findById(ID id) {
        return findByIdAndIsDeletedFalse(id);
    }
}
