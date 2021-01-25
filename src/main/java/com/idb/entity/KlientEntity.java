package com.idb.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="klient",schema = "wypozyczalnia")
public class KlientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name= "id_klienta", nullable = false)
    private Long id;
    @Column(name = "id_adres", nullable = false)
    private Long adres;
    @Column(nullable = false)
    private String imie;
    @Column(nullable = false)
    private String nazwisko;
    @Column(name = "data_urodzenia", nullable = false)
    private String dataUr;
    @Column(nullable = false)
    private String pesel;
    @Column(nullable = false)
    private int telefon;
}
