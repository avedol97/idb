package com.idb.service;

import com.idb.entity.KlientEntity;
import com.idb.repo.KlientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KlientServiceImpl {

    private final KlientRepo klientRepo;

    @Autowired
    public KlientServiceImpl(KlientRepo klientRepo){
        this.klientRepo=klientRepo;
    }

    public List<KlientEntity> findAll(String stringFilter){
        if(stringFilter == null || stringFilter.isEmpty()){
            return klientRepo.findAll();
        }else{
            return klientRepo.search(stringFilter);
        }

    }

    public Iterable<KlientEntity> findAl(){
        return klientRepo.findAll();
    }


    public KlientEntity save(KlientEntity klientEntity){return klientRepo.save(klientEntity);}
    public void deleteById(Long id){klientRepo.deleteById(id);}


}
