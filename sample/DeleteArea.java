package sample;

import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class DeleteArea {

    private final AnchorPane anchorPane = new AnchorPane();


    public DeleteArea(EntryArea entryArea, PhoneBook phoneBook) {
        final Button deleteButton = new Button("Delete");

        AnchorPane.setTopAnchor(deleteButton, 0.0);
        AnchorPane.setBottomAnchor(deleteButton, 10.0);
        AnchorPane.setRightAnchor(deleteButton, 10.0);
        deleteButton.setPrefWidth(70.0);

        deleteButton.setOnAction(e -> {
            phoneBook.deleteEntries(entryArea.getSelectedEntries());
            phoneBook.addEmptyEntry();
        });

        getAnchorPane().getChildren().addAll(deleteButton);
    }

    public AnchorPane getAnchorPane() {
        return anchorPane;
    }


}
