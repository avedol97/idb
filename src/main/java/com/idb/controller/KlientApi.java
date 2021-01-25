package com.idb.controller;

import com.idb.entity.KlientEntity;
import com.idb.service.KlientServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/klienci")
public class KlientApi {

    private KlientServiceImpl klientService;

    public KlientApi(KlientServiceImpl klientService) {
        this.klientService = klientService;
    }

    @GetMapping("/all")
    public Iterable<KlientEntity> getAll(){
        return klientService.findAll();
    }
}
