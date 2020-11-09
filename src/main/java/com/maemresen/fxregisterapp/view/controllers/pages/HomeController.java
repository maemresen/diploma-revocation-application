package com.maemresen.fxregisterapp.view.controllers.pages;

import com.maemresen.fxregisterapp.view.StudentForTable;
import com.maemresen.fxregisterapp.view.controllers.layouts.RegisterFormController;
import com.maemresen.fxregisterapp.view.controllers.layouts.SearchFormController;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;


import java.sql.SQLException;

public class HomeController {


    @SuppressWarnings("unused")
    @FXML
    private RegisterFormController registerController;

    @SuppressWarnings("unused")
    @FXML
    private SearchFormController searchController;

    public void initialize() throws SQLException {
        initRegisterButton();
        initClearSelectionButton();
        initResultTable();
        initSearchButton();
        searchController.listStudents();
    }

    private void initRegisterButton() {
        registerController.registerStudentButton.setOnAction(e -> {
            registerController.tryRegisterSelectedStudentInfo();
            searchController.listStudents();

        });
    }

    private void initClearSelectionButton() {
        registerController.clearSelectionButton.setOnAction(e -> {
            registerController.clearSelectedStudent();

            searchController.resultTable.getSelectionModel().clearSelection();
            searchController.listStudents();
        });
    }

    private void initSearchButton() {
        registerController.searchButton.setOnAction(e -> {
            searchController.listStudents(registerController.searchStudent());
        });
    }

    private void initResultTable() {
        TableView<StudentForTable> resultTable = searchController.resultTable;
        resultTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->
        {
            if (newValue != null) {
                registerController.setSelectedStudent(newValue);
                registerController.registerStudentButton.setText("Kaydet");
            }
        });
    }

}
