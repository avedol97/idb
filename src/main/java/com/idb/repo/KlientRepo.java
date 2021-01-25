package com.idb.repo;

import com.idb.entity.KlientEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KlientRepo extends CrudRepository<KlientEntity,Long> {
}
