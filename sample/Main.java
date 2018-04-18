package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Separator;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{

        // Initialize empty Phone Books
        PhoneBook phoneBookL = new PhoneBook();
        PhoneBook phoneBookR = new PhoneBook();

        // Create BorderPane layouts
        BorderPane rootL = new BorderPane();
        BorderPane rootR = new BorderPane();

        // Instantiate Components
        EntryArea entryAreaL = new EntryArea(phoneBookL);
        LoadArea loadAreaL = new LoadArea(entryAreaL, phoneBookL);
        SearchArea searchAreaL = new SearchArea(phoneBookL);
        DeleteArea deleteAreaL = new DeleteArea(entryAreaL, phoneBookL);

        EntryArea entryAreaR = new EntryArea(phoneBookR);
        LoadArea loadAreaR = new LoadArea(entryAreaR, phoneBookR);
        SearchArea searchAreaR = new SearchArea(phoneBookR);
        DeleteArea deleteAreaR = new DeleteArea(entryAreaR, phoneBookR);

        ImportArea importArea = new ImportArea(entryAreaR, phoneBookL);

        // Build Top components, Load/Search, vertically arranged
        VBox vboxL = new VBox();
        vboxL.getChildren().add(loadAreaL.getPane());
        vboxL.getChildren().add(new Separator());
        vboxL.getChildren().add(searchAreaL.getPane());
        rootL.setTop(vboxL);

        // Build Top components, Load/Search, vertically arranged
        VBox vboxR = new VBox();
        vboxR.getChildren().add(loadAreaR.getPane());
        vboxR.getChildren().add(new Separator());
        vboxR.getChildren().add(searchAreaR.getPane());
        rootR.setTop(vboxR);

        //
        rootL.setCenter(entryAreaL.getAnchorPane());
        rootR.setCenter(entryAreaR.getAnchorPane());



        rootL.setBottom(deleteAreaL.getAnchorPane());
        rootR.setBottom(deleteAreaR.getAnchorPane());


        HBox hBox = new HBox();
        AnchorPane.setRightAnchor(hBox, 10.0);
        hBox.getChildren().addAll(rootL, importArea.getPane(), rootR);



        // Configure Stage and Scene
        primaryStage.setTitle("Phone Book");
        primaryStage.setScene(new Scene(hBox, 750, 275));
        primaryStage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}
