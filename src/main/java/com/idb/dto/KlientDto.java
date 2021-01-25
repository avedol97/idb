package com.idb.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class KlientDto {
    private Long adres;
    private String imie;
    private String nazwisko;
    private Date dataUr;
    private String pesel;
    private int telefon;
}
