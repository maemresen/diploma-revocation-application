package com.maemresen.fxregisterapp.constants;

import com.maemresen.fxregisterapp.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public enum Page {

    HOME("home");


    /**/
    private final String pageName;

    Page(String pageName) {
        this.pageName = pageName;
    }

    private String getFileName() {
        return pageName + ".fxml";
    }

    private Parent getParent() throws IOException {
        return loadParent();
    }

    private String getPath() {
        return "/pages/" + getFileName();
    }

    private Parent loadParent() throws IOException {
        return FXMLLoader.load(Main.class.getClass().getResource(getPath()));
    }

    private static final Page startPage = Page.HOME;

    /**/
    public static Scene getStartPage() throws IOException {
        return new Scene(startPage.getParent());
    }


}