package sample;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Separator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Initialize empty Phone Books
        PhoneBook phoneBookL = new PhoneBook();
        PhoneBook phoneBookR = new PhoneBook();

        // Instantiate Components (left)
        SearchArea searchAreaL = new SearchArea(phoneBookL);
        EntryArea entryAreaL = new EntryArea(phoneBookL, searchAreaL);
        LoadArea loadAreaL = new LoadArea(entryAreaL, phoneBookL, "defaultL.json");
        DeleteArea deleteAreaL = new DeleteArea(entryAreaL, phoneBookL);

        // Instantiate Components (right)
        SearchArea searchAreaR = new SearchArea(phoneBookR);
        EntryArea entryAreaR = new EntryArea(phoneBookR, searchAreaR);
        LoadArea loadAreaR = new LoadArea(entryAreaR, phoneBookR, "defaultR.jason");
        DeleteArea deleteAreaR = new DeleteArea(entryAreaR, phoneBookR);

        // Instantiate importArea (center)
        ImportArea importArea = new ImportArea(entryAreaR, phoneBookR, phoneBookL);

        // Create BorderPane layouts
        BorderPane rootL = new BorderPane();
        BorderPane rootR = new BorderPane();

        // Build Top-Left view
        VBox vBoxL = new VBox();
        vBoxL.getChildren().add(loadAreaL.getPane());
        vBoxL.getChildren().add(new Separator());
        vBoxL.getChildren().add(searchAreaL.getPane());
        rootL.setTop(vBoxL);

        // Build Top-Right view
        VBox vBoxR = new VBox();
        vBoxR.getChildren().add(loadAreaR.getPane());
        vBoxR.getChildren().add(new Separator());
        vBoxR.getChildren().add(searchAreaR.getPane());
        rootR.setTop(vBoxR);

        // Build Center views
        rootL.setCenter(entryAreaL.getAnchorPane());
        rootR.setCenter(entryAreaR.getAnchorPane());

        // Build Bottom views
        rootL.setBottom(deleteAreaL.getAnchorPane());
        rootR.setBottom(deleteAreaR.getAnchorPane());

        // Build wrapping HBox
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(rootL, importArea.getPane(), rootR);




        // Configure Stage and Scene
        primaryStage.setTitle("Phone Book");
        primaryStage.setScene(new Scene(hBox, 600, 400));
        primaryStage.setResizable(false);
        primaryStage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}
