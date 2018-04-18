package sample;


import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;


public class SearchArea {

    private final AnchorPane pane = new AnchorPane();



    public SearchArea(PhoneBook phoneBook) {
        final Button searchButton = new Button("Search");
        final TextField searchField = new TextField();
        AnchorPane.setLeftAnchor(searchField, 10.0);
        AnchorPane.setTopAnchor(searchField, 10.0);
        AnchorPane.setBottomAnchor(searchField, 10.0);
        AnchorPane.setRightAnchor(searchField, 90.0);

        AnchorPane.setRightAnchor(searchButton, 10.0);
        AnchorPane.setTopAnchor(searchButton, 10.0);
        AnchorPane.setBottomAnchor(searchButton, 10.0);
        searchButton.setPrefWidth(70.0);

        getPane().getChildren().addAll(searchField, searchButton);
    }


    public Pane getPane() {
        return pane;
    }
}
