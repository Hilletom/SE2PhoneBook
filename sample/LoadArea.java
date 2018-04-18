package sample;


import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;


public class LoadArea {

    private final AnchorPane pane = new AnchorPane();

    private Path currentPhoneBook = Paths.get("default.json");
    private PhoneBook phoneBook;



    public LoadArea(EntryArea entryArea, PhoneBook phoneBook) {
        final Label currentPBlabel = new Label("Currently Loaded: default");
        final Button loadButton = new Button("Load...");
        final Button saveButton = new Button("Save");

        this.phoneBook = phoneBook;

        FileChooser chooser = new FileChooser();
        chooser.setTitle("Load PhoneBook File");
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json"));

        loadButton.setOnAction(e -> {
            // savePhoneBook();
            Path pathToPhoneBook =  chooser.showOpenDialog(loadButton.getScene().getWindow()).toPath();
            if (pathToPhoneBook != null)
                if (loadPhoneBook(pathToPhoneBook)) {
                    currentPhoneBook = pathToPhoneBook;
                }

        });

        saveButton.setOnAction(e -> savePhoneBook());


        AnchorPane.setLeftAnchor(currentPBlabel, 10.0);
        AnchorPane.setTopAnchor(currentPBlabel, 10.0);
        AnchorPane.setBottomAnchor(currentPBlabel, 10.0);
        AnchorPane.setRightAnchor(currentPBlabel, 90.0);

        AnchorPane.setRightAnchor(saveButton, 80.0);
        AnchorPane.setTopAnchor(saveButton, 10.0);
        AnchorPane.setBottomAnchor(saveButton, 10.0);
        loadButton.setPrefWidth(70.0);

        AnchorPane.setRightAnchor(loadButton, 10.0);
        AnchorPane.setTopAnchor(loadButton, 10.0);
        AnchorPane.setBottomAnchor(loadButton, 10.0);
        loadButton.setPrefWidth(70.0);

        getPane().getChildren().addAll(currentPBlabel, loadButton, saveButton);
    }


    public Pane getPane() {
        return pane;
    }


    private boolean loadPhoneBook(Path pathToPhoneBook) {

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
            return false;
        }

        phoneBook.setPhoneBook(tempList);

        return true;
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
            e. printStackTrace () ;
        }

    }
}
