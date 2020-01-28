package sample;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.util.List;

public class Controller {

    @FXML
    public ComboBox combo1;
    @FXML public ComboBox combo2;
    @FXML public Label population;

    public Controller() {
    }
    public void countrySelect(Event event) {
        Database db = new Database();
        List countries = db.getCountries();
        combo1.getItems().setAll(countries);
    }
    public void citySelect(Event event) {
        Database db = new Database();
        List cities = db.getCities((String)combo1.getValue());
        combo2.getItems().setAll(cities);
    }
    public void popSelect(Event event) {
        Database db = new Database();
        String pop = db.getPopulation((String)combo2.getValue());
        population.setText(pop);
    }






}
