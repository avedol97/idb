package com.idb.controller;

import com.idb.dto.KlientDto;
import com.idb.entity.KlientEntity;
import com.idb.service.KlientServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/klienci")
public class KlientApi {

    private KlientServiceImpl klientService;

    public KlientApi(KlientServiceImpl klientService) {
        this.klientService = klientService;
    }

    @GetMapping("/all")
    public Iterable<KlientEntity> getAll() {
        return klientService.findAl();
    }

//    @PostMapping
//    public KlientEntity addKlient(@RequestBody KlientDto klientDto) {
//        return klientService.save(KlientEntity
//                .builder()
//                .adres(klientDto.getAdres())
//                .imie(klientDto.getImie())
//                .nazwisko(klientDto.getNazwisko())
//                .dataUr(klientDto.getDataUr())
//                .pesel(klientDto.getPesel())
//                .telefon(klientDto.getTelefon())
//                .build());
//    }

    @PutMapping
    public KlientEntity updateKlient(@RequestBody KlientEntity klientEntity) {
        return klientService.save(klientEntity);
    }

    @DeleteMapping
    public void deleteKlient(@RequestParam Long index) {
        klientService.deleteById(index);
    }
}
