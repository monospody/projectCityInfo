package sample;

import org.json.simple.JSONObject;

public class Weather {
    private String name;
    private String country;
    private double temp;
    private long humidity;
    private double lon;
    private double lat;
    private String vsUpdate;
    private String zsUpdate;
    private int visibility;

    public Weather(String name, String country, double temp, long humidity, double lon, double lat,String vsUpdate,String zsUpdate, int visibility) {
        this.name = name;
        this.country = country;
        this.temp = temp;
        this.humidity = humidity;
        this.lon = lon;
        this.lat = lat;
        this.vsUpdate = vsUpdate;
        this.zsUpdate = zsUpdate;
        this.visibility = visibility;
    }

    public String getName() {
        return name;

    }

    public String getCountry() {
        return country;
    }

    public double getTemp() {
        return temp;
    }

    public long getHumidity() {
        return humidity;
    }

    public double getLon() {
        return lon;
    }

    public double getLat() {
        return lat;
    }

    public String getVsUpdate() {
        return vsUpdate;
    }

    public String getZsUpdate() {
        return zsUpdate;
    }
    public int getVisibility() {
        return visibility;
    }

}
