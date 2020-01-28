package sample;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Database {
    private final String selectCodes ="SELECT city.Name, city.countrycode, country.Code2, country.Name, json_extract(Info, '$.Population') as Info FROM city inner join country on country.Code = city.CountryCode;";


    public List getCountries(String country){
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT Code,Name FROM country");
            ResultSet rs = statement.executeQuery();
            String country2,id;
            List<String> list = new ArrayList<>();
            while(rs.next()){
                id = rs.getString("Code");
                country2 = rs.getString("Name");
                list.add(country2);

            }
            statement.close();
            return list;
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public List getCities(String country){
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(selectCodes);
            //statement.setString(1, country);
            ResultSet rs = statement.executeQuery();
            List<City> list = new ArrayList<>();
            while(rs.next()){
                String name = rs.getString("city.Name");
                String code2 = rs.getString("country.Code2");
                String code3 = rs.getString("city.CountryCode");
                String countryName = rs.getString("country.Name");
                int population = rs.getInt("Info");
                City newCity = new City(name,population,code3,code2,countryName);
                list.add(newCity);
                //System.out.println(name+" "+code2+" "+code3+" "+countryName+" "+population);
            }
            statement.close();
            return list;

        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return null;

    }
    public String getPopulation(String city){
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("select json_extract(Info, '$.Population') from city where Name like ?");
            statement.setString(1, city);
            ResultSet rs = statement.executeQuery();
            String pop = null;
            City c = null;
            if(rs.next()){
                pop = (rs.getString(1));
                return pop;
            }
            statement.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return null;

    }



    private Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://itsovy.sk:3306/world_x?useLegacyDatetimeCode=false&serverTimezone=UTC", "student", "kosice2019");
        return connection;
    }

}
