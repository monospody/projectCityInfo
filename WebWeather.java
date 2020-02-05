package sample;

import com.mysql.cj.xdevapi.JsonParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.NoSuchFileException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import org.json.*;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;

public class WebWeather {
    public WebWeather() {

    }
    public Weather getData(String city, String code2) throws IOException, JSONException {
        Weather weather = null;
        URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q="+city+","+code2+"units=metric&APPID=0f72b411d3955f8612fb64b3b33bc963");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");
        if (conn.getResponseCode() == 200) {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    conn.getInputStream()
            ));
            String output = br.readLine();
/*          System.out.println(output);
            System.out.println();

            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(output);
            String name = (String) jsonObject.get("name");
            System.out.println(name);*/

/*          String lon = (String) jsonObject.get("coord").getString("lon");
            System.out.println(lon);

            JSONArray arr = (JSONArray) jsonObject.get("coord");
            for (int i = 0; i < arr.size(); i++)
            {
                String post_id = jsonObject.get(i).getString("lon");
                System.out.println(jsonObject.getJSONObject(i).getString("lon"));

            }

            for (int i = 0; i <array.length ; i++)
            {
                JSONObject innerObject = array.getJSONObject(i);
                JSONArray innerArray = jsonObject.optJSONArray("product");
            }
*/
/*
            JSONObject jsonObject2 = (JSONObject) parser.parse(output);
            JSONObject getSth = (JSONObject) jsonObject2.get("coord");
            Object lon = getSth.get("lon");
            System.out.println(lon);

            Object lat = getSth.get("lat");
            System.out.println(lat);

            JSONObject jsonObject3 = (JSONObject) parser.parse(output);
            JSONObject getSth3 = (JSONObject) jsonObject3.get("main");
            Object temp = getSth3.get("temp");
            System.out.println(temp);

            Object humidity = getSth3.get("humidity");
            System.out.println(humidity);
 */


            JSONObject jsonObject5 = new JSONObject(output);
            JSONObject main = jsonObject5.getJSONObject("main");
            JSONObject coord = jsonObject5.getJSONObject("coord");
            JSONObject sys = jsonObject5.getJSONObject("sys");

            String name = jsonObject5.getString("name");
            String country = sys.getString("country");

            double temp = main.getDouble("temp");
            int humidity = main.getInt("humidity");
            double lon = coord.getDouble("lon");
            double lat = coord.getDouble("lat");


            long vs = sys.getLong("sunrise");
            long zs = sys.getLong("sunset");
//            int visibility = jsonObject5.getInt("visibility");

            SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
            String vsUpdate = timeFormat.format(vs);

            SimpleDateFormat timeFormat2 = new SimpleDateFormat("h:mm a");
            String zsUpdate = timeFormat2.format(zs);



            return new Weather(city, name, temp, humidity, lon, lat,vsUpdate,zsUpdate);

        }else throw new NoSuchFileException("City not found");



    }

}

