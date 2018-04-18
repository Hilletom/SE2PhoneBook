package sample;


import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;


public class SearchArea {

    private final AnchorPane pane = new AnchorPane();

    private SortedList<TelefonEntry> sortedEntries;



    public SearchArea(PhoneBook phoneBook) {

        final TextField searchField = new TextField();
        searchField.setPromptText("Search...");
        AnchorPane.setLeftAnchor(searchField, 25.0);
        AnchorPane.setTopAnchor(searchField, 10.0);
        AnchorPane.setBottomAnchor(searchField, 10.0);
        AnchorPane.setRightAnchor(searchField, 25.0);


        getPane().getChildren().addAll(searchField);


        FilteredList<TelefonEntry> filteredEntires = new FilteredList<>(phoneBook.getPhoneBook(), p -> true);

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredEntires.setPredicate(entry -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (entry.getFirstName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (entry.getLastName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (entry.getNumber().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            }
                return false;
            });
        });

        sortedEntries = new SortedList<>(filteredEntires);


    }

    public SortedList<TelefonEntry> getSortedEntries() {
        return sortedEntries;
    }

    public Pane getPane() {
        return pane;
    }
}
