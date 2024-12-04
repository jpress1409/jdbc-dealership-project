package com.yearup.dealership.db;

import com.yearup.dealership.models.Vehicle;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleDao {
    private DataSource dataSource;

    public VehicleDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addVehicle(Vehicle vehicle) throws SQLException{
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO vehicles (vin, vehicleYear, make, model, vehicle type, color, mileage, price) " +
                             "VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, vehicle.getVin());
            statement.setInt(2, vehicle.getYear());
            statement.setString(3, vehicle.getMake());
            statement.setString(4, vehicle.getModel());
            statement.setString(5, vehicle.getVehicleType());
            statement.setString(6, vehicle.getColor());
            statement.setInt(7, vehicle.getOdometer());
            statement.setDouble(8, vehicle.getPrice());

            statement.executeUpdate();
        }
    }

    public void removeVehicle(String VIN) {
        // TODO: Implement the logic to remove a vehicle
    }

    public List<Vehicle> searchByPriceRange(double minPrice, double maxPrice) throws SQLException{

        String query = "SELECET * FROM vehicles" +
                "WHERE price >= ? AND price <= ?";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)){

            preparedStatement.setDouble(1, minPrice);
            preparedStatement.setDouble(2, maxPrice);

            try(ResultSet results = preparedStatement.executeQuery()){
                while(results.next()){
                    String vin = results.getString("vin");
                    int year = results.getInt("vehicleYear");
                    String make = results.getString("make");
                    String model = results.getString("model");
                    String type = results.getString("vehicle type");
                    String color = results.getString("color");
                    int miles = results.getInt("mileage");
                    double price = results.getDouble("price");

                    new ArrayList<>().add(new Vehicle(vin, year, make, model, type, color, miles, price));

                }
            }
        }
        return new ArrayList<>();
    }

    public List<Vehicle> searchByMakeModel(String make, String model) throws SQLException {
        String query = "SELECET * FROM vehicles" +
                "WHERE make like ?% OR model like ?%";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)){

            preparedStatement.setString(1, make);
            preparedStatement.setString(2, model);

            try(ResultSet results = preparedStatement.executeQuery()){
                while(results.next()){
                    String vin = results.getString("vin");
                    int year = results.getInt("vehicleYear");
                    make = results.getString("make");
                    model = results.getString("model");
                    String type = results.getString("vehicle type");
                    String color = results.getString("color");
                    int miles = results.getInt("mileage");
                    double price = results.getDouble("price");

                    new ArrayList<>().add(new Vehicle(vin, year, make, model, type, color, miles, price));

                }
            }
        }
        return new ArrayList<>();
    }

    public List<Vehicle> searchByYearRange(int minYear, int maxYear) throws SQLException{
        String query = "SELECET * FROM vehicles" +
                "WHERE year >= ? AND year <= ?";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)){

            preparedStatement.setDouble(1, minYear);
            preparedStatement.setDouble(2, maxYear);

            try(ResultSet results = preparedStatement.executeQuery()){
                while(results.next()){
                    String vin = results.getString("vin");
                    int year = results.getInt("vehicleYear");
                    String make = results.getString("make");
                    String model = results.getString("model");
                    String type = results.getString("vehicle type");
                    String color = results.getString("color");
                    int miles = results.getInt("mileage");
                    double price = results.getDouble("price");

                    new ArrayList<>().add(new Vehicle(vin, year, make, model, type, color, miles, price));

                }
            }
        }
        return new ArrayList<>();
    }

    public List<Vehicle> searchByColor(String color) throws SQLException{
        String query = "SELECET * FROM vehicles" +
                "WHERE color = ?";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, color);

            try (ResultSet results = preparedStatement.executeQuery()) {
                while (results.next()) {
                    String vin = results.getString("vin");
                    int year = results.getInt("vehicleYear");
                    String make = results.getString("make");
                    String model = results.getString("model");
                    String type = results.getString("vehicle type");
                    color = results.getString("color");
                    int miles = results.getInt("mileage");
                    double price = results.getDouble("price");

                    new ArrayList<>().add(new Vehicle(vin, year, make, model, type, color, miles, price));

                }
            }
        }
        return new ArrayList<>();
    }

    public List<Vehicle> searchByMileageRange(int minMileage, int maxMileage) throws SQLException{
            String query = "SELECET * FROM vehicles" +
                    "WHERE mileage >= ? AND mileage <= ?";

            try(Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                preparedStatement.setDouble(1, minMileage);
                preparedStatement.setDouble(2, maxMileage);

                try (ResultSet results = preparedStatement.executeQuery()) {
                    while (results.next()) {
                        String vin = results.getString("vin");
                        int year = results.getInt("vehicleYear");
                        String make = results.getString("make");
                        String model = results.getString("model");
                        String type = results.getString("vehicle type");
                        String color = results.getString("color");
                        int miles = results.getInt("mileage");
                        double price = results.getDouble("price");

                        new ArrayList<>().add(new Vehicle(vin, year, make, model, type, color, miles, price));

                    }
                }
            }
        return new ArrayList<>();
    }

    public List<Vehicle> searchByType(String type) throws SQLException{
        String query = "SELECET * FROM vehicles" +
                "WHERE type = ?";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, type);

            try (ResultSet results = preparedStatement.executeQuery()) {
                while (results.next()) {
                    String vin = results.getString("vin");
                    int year = results.getInt("vehicleYear");
                    String make = results.getString("make");
                    String model = results.getString("model");
                    type = results.getString("vehicle type");
                    String color = results.getString("color");
                    int miles = results.getInt("mileage");
                    double price = results.getDouble("price");

                    new ArrayList<>().add(new Vehicle(vin, year, make, model, type, color, miles, price));

                }
            }
        }        return new ArrayList<>();
    }

    private Vehicle createVehicleFromResultSet(ResultSet resultSet) throws SQLException {
        Vehicle vehicle = new Vehicle();
        vehicle.setVin(resultSet.getString("VIN"));
        vehicle.setMake(resultSet.getString("make"));
        vehicle.setModel(resultSet.getString("model"));
        vehicle.setYear(resultSet.getInt("year"));
        vehicle.setSold(resultSet.getBoolean("SOLD"));
        vehicle.setColor(resultSet.getString("color"));
        vehicle.setVehicleType(resultSet.getString("vehicleType"));
        vehicle.setOdometer(resultSet.getInt("odometer"));
        vehicle.setPrice(resultSet.getDouble("price"));
        return vehicle;
    }
}
