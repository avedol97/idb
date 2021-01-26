package com.idb.service;

import com.idb.entity.KlientEntity;
import com.idb.repo.KlientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KlientServiceImpl {

    private final KlientRepo klientRepo;

    @Autowired
    public KlientServiceImpl(KlientRepo klientRepo){
        this.klientRepo=klientRepo;
    }

    public Iterable<KlientEntity> findAll(){return klientRepo.findAll();}
    public KlientEntity save(KlientEntity klientEntity){return klientRepo.save(klientEntity);}
    public void deleteById(Long id){klientRepo.deleteById(id);}

}
