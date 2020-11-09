package com.maemresen.fxregisterapp;


import com.maemresen.fxregisterapp.constants.Page;

import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setScene(Page.getStartPage());

        primaryStage.show();

    }


}
