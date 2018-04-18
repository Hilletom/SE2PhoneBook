package sample;

import javafx.beans.property.SimpleStringProperty;

public class TelefonEntry {

    private final SimpleStringProperty lastName = new SimpleStringProperty();
    private final SimpleStringProperty  firstName = new SimpleStringProperty();
    private final SimpleStringProperty  number = new SimpleStringProperty();
    private boolean isNew = true; // TODO boolsche Property mit Listener in PhoneBook

    public TelefonEntry() {
        this.lastName.set("...");
        this.firstName.set("...");
        this.number.set("...");
        isNew = true;
    }

    public TelefonEntry(String lastName, String firstName, String number) {
        this.lastName.set(lastName);
        this.firstName.set(firstName);
        this.number.set(number);
        isNew = false;
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String value) {
        lastName.set(value);
        isNew = false;
    }

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String value) {
        firstName.set(value);
        isNew = false;
    }

    public String getNumber() {
        return number.get();
    }

    public void setNumber(String value) {
        number.set(value);
        isNew = false;
    }

    public boolean isNew() {
        return isNew;
    }

    @Override
    public boolean equals(Object o) {
        if (o != null && o instanceof TelefonEntry) {
            TelefonEntry te = (TelefonEntry) o;
            if (te.firstName.get().equals(this.firstName.get()) && te.lastName.get().equals(this.lastName.get()) && te.number.get().equals(this.number.get()))
                return true;
        }

        return false;
    }

}