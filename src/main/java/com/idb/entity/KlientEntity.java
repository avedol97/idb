package com.idb.entity;

import lombok.*;
import org.dom4j.tree.AbstractEntity;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="klient",schema = "wypozyczalnia")
public class KlientEntity extends AbstractEntity implements  Cloneable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name= "id_klienta")
    private Long id;
    @Column(name = "id_adres", nullable = false)
    private Long adres;
    @Column(nullable = false)
    private String imie;
    @Column(nullable = false)
    private String nazwisko;
    @Column(name = "data_urodzenia", nullable = false)
    private Date dataUr;
    @Column(nullable = false)
    private String pesel;
    @Column(nullable = false)
    private int telefon;
    }

