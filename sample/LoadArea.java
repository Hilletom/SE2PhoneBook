package sample;


import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class LoadArea {

    private final AnchorPane pane = new AnchorPane();

    private Path currentPhoneBook;//
    private PhoneBook phoneBook;
    private final Label currentPBlabel = new Label();


    public LoadArea(EntryArea entryArea, PhoneBook phoneBook, String defaultPhoneBook) {

        //final Button loadButton = new Button("Load...");
        final Button loadButton = GlyphsDude.createIconButton(FontAwesomeIcon.FOLDER_OPEN);
        final Button saveButton = GlyphsDude.createIconButton(FontAwesomeIcon.SAVE);


        this.phoneBook = phoneBook;
        this.currentPhoneBook = Paths.get(defaultPhoneBook);

        if (currentPhoneBook.toFile().exists())
            loadPhoneBook(currentPhoneBook);





        loadButton.setOnAction(e -> {
            FileChooser chooser = new FileChooser();
            chooser.setTitle("Load PhoneBook");
            chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json"));
            Path pathToPhoneBook =  chooser.showOpenDialog(loadButton.getScene().getWindow()).toPath();
            if (pathToPhoneBook != null)
                loadPhoneBook(pathToPhoneBook);
        });

        saveButton.setOnAction(e -> {
            if (!currentPhoneBook.toFile().exists()) {
                FileChooser chooser = new FileChooser();
                chooser.setTitle("Save PhoneBook");
                chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json"));
                Path pathToPhoneBook =  chooser.showSaveDialog(loadButton.getScene().getWindow()).toPath();
                currentPhoneBook = pathToPhoneBook;
                currentPBlabel.setText("PhoneBook: " + currentPhoneBook.getFileName().toString().replace(".json", ""));
            }

            savePhoneBook();
        });


        AnchorPane.setLeftAnchor(currentPBlabel, 10.0);
        AnchorPane.setTopAnchor(currentPBlabel, 10.0);
        AnchorPane.setBottomAnchor(currentPBlabel, 10.0);
        AnchorPane.setRightAnchor(currentPBlabel, 90.0);

        AnchorPane.setRightAnchor(saveButton, 40.0);
        AnchorPane.setTopAnchor(saveButton, 10.0);
        AnchorPane.setBottomAnchor(saveButton, 10.0);
        //loadButton.setPrefWidth(700.0);

        AnchorPane.setRightAnchor(loadButton, 10.0);
        AnchorPane.setTopAnchor(loadButton, 10.0);
        AnchorPane.setBottomAnchor(loadButton, 10.0);
        //loadButton.setPrefWidth(70.0);

        getPane().getChildren().addAll(currentPBlabel, loadButton, saveButton);
    }


    public Pane getPane() {
        return pane;
    }


    private void loadPhoneBook(Path pathToPhoneBook) {

        List<TelefonEntry> tempList = new ArrayList<TelefonEntry>();

        try (InputStream is = Files.newInputStream (pathToPhoneBook))  {
            ObjectMapper mapper = new ObjectMapper () ;
            JsonNode rootArray = mapper.readTree(is);

            for (JsonNode root : rootArray) {
                TelefonEntry tempEntry = new TelefonEntry();

                tempEntry.setLastName(root.path("lastName").asText());
                tempEntry.setFirstName(root.path("firstName").asText());
                tempEntry.setNumber(root.path("number").asText());

                System.out.println(root.path("lastName").asText());
                tempList.add(tempEntry);
            }


        } catch ( Exception e) {
            e. printStackTrace ();
            Alert alert = new Alert(Alert.AlertType.ERROR, pathToPhoneBook.toString() + " could not be loaded!", ButtonType.OK);
            alert.showAndWait();
        }

        phoneBook.setPhoneBook(tempList);

        currentPhoneBook = pathToPhoneBook;
        currentPBlabel.setText("PhoneBook: " + currentPhoneBook.getFileName().toString().replace(".json", ""));
    }

    private void savePhoneBook() {
        JsonFactory factory = new JsonFactory () ;
        try (OutputStream os = Files.newOutputStream(currentPhoneBook);
             JsonGenerator jg = factory.createGenerator(os)) {
            jg.writeStartArray();
            jg.setCodec(new ObjectMapper());
            for (TelefonEntry entry : phoneBook.getPhoneBook()) {
                if (!entry.isNew())
                    jg.writeObject(entry);
            }
            jg.writeEndArray();


        } catch ( Exception e) {
            e. printStackTrace ();
            Alert alert = new Alert(Alert.AlertType.ERROR, currentPhoneBook.toString() + " could not be saved!", ButtonType.OK);
            alert.showAndWait();
        }

    }
}
