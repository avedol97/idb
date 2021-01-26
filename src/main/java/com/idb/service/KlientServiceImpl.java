package com.idb.service;

import antlr.build.Tool;
import com.idb.entity.KlientEntity;
import com.idb.repo.KlientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;

@Service
public class KlientServiceImpl {

    private static final Logger LOGGER = Logger.getLogger(KlientServiceImpl.class.getName());

    private final KlientRepo klientRepo;

    @Autowired
    public KlientServiceImpl(KlientRepo klientRepo) {
        this.klientRepo = klientRepo;
    }

    public List<KlientEntity> findAll(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) {
            return klientRepo.findAll();
        } else {
            return klientRepo.search(stringFilter);
        }

    }

    public Iterable<KlientEntity> findAl() {
        return klientRepo.findAll();
    }


    public KlientEntity save(KlientEntity klientEntity) {
        if (klientEntity == null) {
            LOGGER.log(Level.SEVERE, "Contact is null. Are you sure you have connected your form to the application?");
        }
        return klientRepo.save(klientEntity);
    }

    public void deleteById(Long id) {
        klientRepo.deleteById(id);
    }


}
