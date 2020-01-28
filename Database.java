package sample;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class Database {

    public List getCountries(){
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT Code,Name FROM country");
            ResultSet rs = statement.executeQuery();
            String country,id;
            List<String> list = new ArrayList<>();
            while(rs.next()){
                id = rs.getString("Code");
                country = rs.getString("Name");
                list.add(country);
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
            PreparedStatement statement = connection.prepareStatement("select * from city inner join country on country.Code = city.CountryCode where country.Name like ?");
            statement.setString(1, country);
            ResultSet rs = statement.executeQuery();
            String city;
            List<String> list = new ArrayList<>();
            while(rs.next()){
                city = rs.getString("Name");
                list.add(city);
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
