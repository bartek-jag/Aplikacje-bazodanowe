package main.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.util.HashMap;

abstract class Utils {

    static FXMLLoader load(HashMap<String, HBox> screens, HBox hBox, String fxml){
        if (hBox.getChildren().size() == 2)
            hBox.getChildren().remove(1);
        if (!screens.containsKey(fxml)){
            //System.out.println("lolol " + fxml);
            FXMLLoader loader = new FXMLLoader(OrderDetailsViewController.class.getResource("/main/fxml/add/" + fxml + ".fxml"));
            try {
                screens.put(fxml, loader.load());
                //System.out.println("lololeee " + fxml);
                return loader;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    static void loadODScreen(HBox hBox){
        if (hBox.getChildren().size() == 2)
            hBox.getChildren().remove(1);
        FXMLLoader loader = new FXMLLoader(OrderDetailsViewController.class.getResource("/main/fxml/OrderDetailsViewScreen.fxml"));
        try {
            HBox orderDetails = loader.load();
            hBox.getChildren().add(1, orderDetails);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
