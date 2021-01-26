package com.idb;

import com.idb.dto.KlientDto;
import com.idb.entity.KlientEntity;
import com.idb.repo.KlientRepo;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.converter.StringToDateConverter;
import com.vaadin.flow.data.converter.StringToIntegerConverter;
import com.vaadin.flow.data.converter.StringToLongConverter;
import com.vaadin.flow.shared.Registration;
import org.springframework.beans.factory.annotation.Autowired;


import java.text.ParseException;
import java.text.SimpleDateFormat;


public class KlienciForm extends FormLayout {

    private TextField id;
    private TextField adres;
    private TextField imie;
    private TextField nazwisko;
    private TextField dataUr;
    private TextField pesel;
    private TextField telefon;
    private Button buttonSave;
    private Button buttonDelete;
    private Button buttonCancel;

  Binder<KlientEntity> binder = new BeanValidationBinder<>(KlientEntity.class);

    private KlientEntity klientEntity;


    public KlienciForm() {
        id= new TextField("Podaj id Adresu :");
        adres = new TextField("Podaj id Adresu :");
        imie = new TextField("Podaj Imie :");
        nazwisko = new TextField("Podaj Nazwisko :");
        dataUr = new TextField("Podaj Datę Urodzenia :");
        pesel = new TextField("Podaj Pesel :");
        telefon = new TextField("Podaj Telefon :");

        addClassName("klienci-form");
        binder.forField(id)
                .withConverter(
                        new StringToLongConverter("Must enter a number"))
                .bind(KlientEntity::getId,KlientEntity::setId);
        binder.forField(adres)
                .withConverter(
                        new StringToLongConverter("Must enter a number"))
                .bind(KlientEntity::getAdres,KlientEntity::setAdres);
        binder.forField(dataUr)
                .withConverter(
                        new StringToDateConverter())
                .bind(KlientEntity::getDataUr,KlientEntity::setDataUr);
        binder.forField(telefon)
                .withConverter(
                        new StringToIntegerConverter("Must enter a number"))
                .bind(KlientEntity::getTelefon,KlientEntity::setTelefon);
        binder.bindInstanceFields(this);


        buttonSave = new Button("Save");
        buttonDelete = new Button("Delete!");
        buttonCancel = new Button("Cancel");


        add(id,adres,imie,nazwisko,dataUr,pesel,telefon,createButtonsLayout());


    }

    public void setKlient(KlientEntity klientEntity) {
        this.klientEntity = klientEntity;
        binder.readBean(klientEntity);
    }


    private Component createButtonsLayout() {
        buttonSave.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonDelete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        buttonCancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        buttonSave.addClickShortcut(Key.ENTER);
        buttonCancel.addClickShortcut(Key.ESCAPE);

        buttonSave.addClickListener(event -> validateAndSave());
        buttonDelete.addClickListener(event -> fireEvent(new DeleteEvent(this, klientEntity)));
        buttonCancel.addClickListener(event -> fireEvent(new CloseEvent(this)));

    binder.addStatusChangeListener(evt -> buttonSave.setEnabled(binder.isValid()));

        return new HorizontalLayout(buttonSave, buttonDelete, buttonCancel);
    }

    private void validateAndSave() {

        try {
            System.out.println("save");
            binder.writeBean(klientEntity);
            fireEvent(new SaveEvent(this, klientEntity));
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }


    public static abstract class KlienciFormEvent extends ComponentEvent<KlienciForm> {
        private KlientEntity klientEntity;

        protected KlienciFormEvent(KlienciForm source, KlientEntity klientEntity) {
            super(source, false);
            this.klientEntity = klientEntity;
        }

        public KlientEntity getKlientEntity() {
            return klientEntity;
        }
    }

    public static class SaveEvent extends KlienciFormEvent {
        SaveEvent(KlienciForm source, KlientEntity klientEntity) {
            super(source, klientEntity);
        }
    }

    public static class DeleteEvent extends KlienciFormEvent {
        DeleteEvent(KlienciForm source, KlientEntity klientEntity) {
            super(source, klientEntity);
        }
    }

    public static class CloseEvent extends KlienciFormEvent {
        CloseEvent(KlienciForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }

}
