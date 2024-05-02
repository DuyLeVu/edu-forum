package com.edu.forum.application.repository;

import com.edu.forum.application.model.entity.Category;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>, JpaSpecificationExecutor<Category> {
    Optional<Category> findByName(String name);

    @Modifying
    @Query(value = "select * from Category order by id desc limit 7;", nativeQuery = true)
    Iterable<Category> findTop7Category();

    @Modifying
    @Query(value = "select * from category left join roles " +
            "on category.role_id = roles.id " +
            "where roles.id = 2", nativeQuery = true)
    Iterable<Category> getCategoryForUser();

    @Transactional
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query(value = "select * from category c where c.id = :id", nativeQuery = true)
    Optional<Category> getLockedCategory(Long id);
}
