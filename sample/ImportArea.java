package sample;


import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


public class ImportArea {

    private final AnchorPane pane = new AnchorPane();



    public ImportArea(EntryArea entryAreaR, PhoneBook phoneBookR, PhoneBook phoneBookL) {
        final Label labelImport = new Label("Import");
        final Button importOne = GlyphsDude.createIconButton(FontAwesomeIcon.LONG_ARROW_LEFT);
        final Button importAll = new Button("All");

        //AnchorPane.setLeftAnchor(labelImport, 10.0);
        //AnchorPane.setTopAnchor(labelImport, 10.0);
        //AnchorPane.setBottomAnchor(labelImport, 10.0);
        //AnchorPane.setRightAnchor(labelImport, 10.0);

        //AnchorPane.setLeftAnchor(importOne, 10.0);
        //AnchorPane.setTopAnchor(importOne, 10.0);
        //AnchorPane.setBottomAnchor(importOne, 10.0);
        //AnchorPane.setRightAnchor(importOne, 10.0);

        //AnchorPane.setLeftAnchor(importAll, 10.0);
        //AnchorPane.setTopAnchor(importAll, 10.0);
        //AnchorPane.setBottomAnchor(importAll, 10.0);
        //AnchorPane.setRightAnchor(importAll, 10.0);

        // setPrefWidth()

        VBox importVBox = new VBox();
        importVBox.setPadding(new Insets(10));
        importVBox.setSpacing(8);
        importVBox.setAlignment(Pos.CENTER);
        AnchorPane.setTopAnchor(importVBox, 130.0);
        importVBox.setStyle("-fx-border-color: lightgrey;");
        importVBox.setPadding(new Insets(20));
        importVBox.getChildren().addAll(labelImport, importOne, importAll);

        getPane().getChildren().addAll(importVBox);




        importOne.setOnAction(e -> phoneBookL.cleanAddAll(entryAreaR.getSelectedEntries()));
        importAll.setOnAction(e -> phoneBookL.cleanAddAll(phoneBookR.getPhoneBook()));

    }


    public Pane getPane() {
        return pane;
    }
}
