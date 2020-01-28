package sample;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.util.List;

public class Controller {

    @FXML
    public ComboBox combo1;
    @FXML public ComboBox combo2;
    @FXML public Label population;
    @FXML public Label label2;
    @FXML public Label label3;
    @FXML public Button button;
    @FXML public Label popLabel;
    @FXML public Label countryLabel;
    @FXML public Label cityLabel;
    private List<City> cities = null;
    public Controller() {
    }
    public void countrySelect(Event event) {
        Database db = new Database();
        List countries = db.getCountries((String)combo1.getValue());
        combo1.getItems().setAll(countries);
    }
    public void citySelect(Event event) {
      /*  Database db = new Database();
        List cities = db.getCities((String)combo1.getValue());
        combo2.getItems().setAll(cities);*/

        String country1 = null;
        country1 = (String) combo1.getValue();
        if(country1!=null) {
            Database db = new Database();
            List<City> cities = db.getCities(country1);
            combo2.getItems().removeAll();
            for (City s : cities) {
                combo2.getItems().add(s.getName());
            }
        }
    }
    public void popSelect(Event event) {

        Database db = new Database();
        popLabel.setText("Population: "+db.getPopulation((String)combo2.getValue()));
        System.out.println(db.getPopulation((String)combo2.getValue()));


        String country1 = (String) combo2.getValue();
        City city = null;
        /*for(City s : cities){
            if(s.getName().equalsIgnoreCase(country1)) {
                city=s;
                break;
            }
            if(city == null){
                return;
            }*/
            /*countryLabel.setText("Country: "+city.getCountry());
            cityLabel.setText("City: "+city.getName());*/
    }
    public void visibleCity(ActionEvent actionEvent) {
        combo2.setVisible(true);
        label2.setVisible(true);
        button.setVisible(true);
    }
}
