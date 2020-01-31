package sample;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import org.json.JSONException;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.HashMap;
import java.util.List;

public class Controller {

    @FXML public ComboBox<String> combo1;
    @FXML public ComboBox combo2;
    @FXML public Label label2;
    @FXML public Label label3;
    @FXML public Button button;
    @FXML public Label popLabel;
    @FXML public Label countryLabel;
    @FXML public Label cityLabel;
    @FXML public Button button2;
    @FXML public Label lblTemp;
    @FXML public Label lblHum;
    @FXML public Label lblLon;
    @FXML public Label lblLat;
    @FXML public CheckBox chk;
    @FXML public Label vs;
    @FXML public Label zs;
    @FXML public Label visibility;
    @FXML public Button map;
    private List<City> cities = null;
    List<String> countries = null;
    public Controller() {
        Database db = new Database();
        countries = db.getCountries();
        countries = db.getCountries();
    }
    public void countrySelect(Event event) {
        combo1.getItems().setAll(countries);
    }
    public void citySelect(Event event) {
      /*  Database db = new Database();
        List cities = db.getCities((String)combo1.getValue());
        combo2.getItems().setAll(cities);*/

        String country1 = null;
        country1 = combo1.getValue();
        if(country1!=null) {
            Database db = new Database();
            cities = db.getCities(country1);
            combo2.getItems().clear();
            for (City s : cities) {
                combo2.getItems().add(s.getName());
            }
            combo2.setDisable(false);
        }

        combo2.setVisible(true);
        label2.setVisible(true);
    }

    public void buttonShow(ActionEvent actionEvent) {
        button.setVisible(true);
    }
    public void popSelect(Event event) throws IOException, ParseException {
    /*  Database db = new Database();
        popLabel.setText("Population: "+db.getPopulation((String)combo2.getValue()));
        System.out.println(db.getPopulation((String)combo2.getValue()));*/
        button2.setVisible(true);

        String nameCity = (String) combo2.getValue();
        City city = null;
        for(City s : cities) {
            if (s.getName().equalsIgnoreCase(nameCity)) {
                city = s;
                break;
            }
        }
            if(city == null){
                return;
            }
            cityLabel.setText("City: "+city.getName());
            countryLabel.setText("Country: "+city.getCountry());
            popLabel.setText("Population: "+city.getPopulation());

             /*
            URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q="+city.getName()+","+city.getCode2()+"&APPID=0f72b411d3955f8612fb64b3b33bc963");
            URLConnection yc = url.openConnection();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader( yc.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                System.out.println(inputLine);
            }
            in.close();
             */


    }


    public void infoSelect(ActionEvent actionEvent) throws IOException, ParseException, JSONException {

        lblTemp.setVisible(true);
        lblHum.setVisible(true);
        lblLat.setVisible(true);
        lblLon.setVisible(true);
        chk.setVisible(true);

        String nameCity = (String) combo2.getValue();
        City city = null;
        for(City s : cities) {
            if (s.getName().equalsIgnoreCase(nameCity)) {
                city = s;
                break;
            }
        }
        if(city == null){
            return;
        }
        Weather w = new WebWeather().getData(city.getName(),city.getCode2());

        if(w != null) {
            lblTemp.setText("Temp: " + w.getTemp() + "°C");
            lblHum.setText("Humidity: " + w.getHumidity() + "%");
            lblLat.setText("Lat: " + w.getLat());
            lblLon.setText("Lon: " + w.getLon());
        }
        else {
            lblTemp.setText("Temp: ");
            lblHum.setText("Humidity: ");
            lblLat.setText("Lat: ");
            lblLon.setText("Lon: ");

        }


    }

    public void checkBoxClick(ActionEvent actionEvent) throws IOException, JSONException {
        vs.setVisible(true);
        zs.setVisible(true);
        visibility.setVisible(true);
        map.setVisible(true);

        String nameCity = (String) combo2.getValue();
        City city = null;
        for(City s : cities) {
            if (s.getName().equalsIgnoreCase(nameCity)) {
                city = s;
                break;
            }
        }
        if(city == null){
            return;
        }

        Weather w = new WebWeather().getData(city.getName(),city.getCode2());

        vs.setText("Východ slnka: "+w.getVsUpdate());
        zs.setText("Západ slnka: "+w.getZsUpdate());
        visibility.setText("Visiblity: "+w.getVisibility());


    }


    public void goMap(ActionEvent actionEvent) throws URISyntaxException, IOException, JSONException {
        String nameCity = (String) combo2.getValue();
        City city = null;
        for(City s : cities) {
            if (s.getName().equalsIgnoreCase(nameCity)) {
                city = s;
                break;
            }
        }
        if(city == null){
            return;
        }

        Weather w = new WebWeather().getData(city.getName(),city.getCode2());

        java.awt.Desktop.getDesktop().browse(new URI("https://www.latlong.net/c/?lat="+w.getLat()+"&long="+w.getLon()));


    }
}

