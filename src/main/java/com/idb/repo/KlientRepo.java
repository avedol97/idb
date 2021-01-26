package com.idb.repo;

import com.idb.entity.KlientEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KlientRepo extends CrudRepository<KlientEntity,Long> {
    @Query("select c from KlientEntity c " +
            "where lower(c.imie) like lower(concat('%', :searchTerm, '%')) " +
            "or lower(c.nazwisko) like lower(concat('%', :searchTerm, '%'))")
    List<KlientEntity> search(@Param("searchTerm") String searchTerm);
    List<KlientEntity> findAll();
}
