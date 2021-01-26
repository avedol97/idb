package com.idb.gui;

import com.idb.entity.KlientEntity;
import com.idb.service.KlientServiceImpl;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

import java.io.IOException;
import java.net.URISyntaxException;

@Route("")
@CssImport("./styles/shared-styles.css")
public class MainViewGUI extends VerticalLayout {

    private KlientEntity klientEntity;
    private KlientServiceImpl klientService;
    private Grid<KlientEntity> grid = new Grid<>(KlientEntity.class);
    private TextField textFieldFilter = new TextField();
    private KlienciForm form;

    public MainViewGUI(KlientServiceImpl klientService) {
        this.klientService = klientService;
        addClassName("list-view");
        setSizeFull();
        configureGrid();

        form = new KlienciForm();
        form.addListener(KlienciForm.SaveEvent.class, this::saveKlienci);
        form.addListener(KlienciForm.DeleteEvent.class, this::deleteKlienci);
        form.addListener(KlienciForm.CloseEvent.class, e -> closeEditor());

        Div content = new Div(grid, form);
        content.addClassName("content");
        content.setSizeFull();

        add(getToolBar(), content);
        updateList();
        closeEditor();
    }

    private void deleteKlienci(KlienciForm.DeleteEvent evt) {
        System.out.println("*** Usunieto Klienta " + evt.getKlientEntity().getImie() + " " + evt.getKlientEntity().getNazwisko() + " ***");
        klientService.deleteById(evt.getKlientEntity().getId());
        updateList();
        closeEditor();
    }

    private void saveKlienci(KlienciForm.SaveEvent evt) {
        System.out.println("*** Edytowano ***");
        klientService.save(evt.getKlientEntity());
        updateList();
        closeEditor();
    }

    private HorizontalLayout getToolBar() {
        textFieldFilter.setPlaceholder("Filter by name...");
        textFieldFilter.setClearButtonVisible(true);
        textFieldFilter.setValueChangeMode(ValueChangeMode.LAZY);
        textFieldFilter.addValueChangeListener(e -> updateList());

        Button buttonKlient = new Button("Add Customer", click -> {
            try {
                addKlient();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        });

        HorizontalLayout toolbar = new HorizontalLayout(textFieldFilter, buttonKlient);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void addKlient() throws IOException, URISyntaxException {
        try {
            grid.asSingleSelect().clear();
            editKlient(new KlientEntity());
        } catch (IllegalStateException e) {
        }
    }

    private void closeEditor() {
        System.out.println("*** Zamknieto Edytor! ***");
        form.setKlient(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private void configureGrid() {
        grid.addClassName("klienci-grid");
        grid.setSizeFull();
        grid.setColumns("id", "adres", "imie", "nazwisko", "dataUr", "pesel", "telefon");

        grid.asSingleSelect().addValueChangeListener(evt -> editKlient(evt.getValue()));
    }

    private void editKlient(KlientEntity klientEntity) {
        if (klientEntity == null) {
            closeEditor();
        } else {
            System.out.println(klientEntity.toString());
            form.setVisible(true);
            form.setKlient(klientEntity);
            addClassName("editing");
        }
    }

    private void updateList() {
        grid.setItems(klientService.findAll(textFieldFilter.getValue()));
    }
}
