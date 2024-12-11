package com.yearup.dealership.db;

import com.yearup.dealership.models.Vehicle;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleDao {
    private final DataSource dataSource;

    public VehicleDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addVehicle(Vehicle vehicle) throws SQLException {
        String query = "INSERT INTO vehicles (vin, make, model, year, sold, vehicleType, color, odometer, price) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, vehicle.getVin());
            statement.setString(2, vehicle.getMake());
            statement.setString(3, vehicle.getModel());
            statement.setInt(4, vehicle.getYear());
            statement.setBoolean(5, vehicle.isSold());
            statement.setString(6, vehicle.getVehicleType());
            statement.setString(7, vehicle.getColor());
            statement.setInt(8, vehicle.getOdometer());
            statement.setDouble(9, vehicle.getPrice());
            statement.executeUpdate();
        }
    }

    public void removeVehicle(String vin) throws SQLException {
        String query = "DELETE FROM vehicles WHERE vin = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, vin);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                System.out.println("Vehicle not found.");
            }
        }
    }

    public List<Vehicle> searchByPriceRange(double minPrice, double maxPrice) throws SQLException {
        String query = "SELECT * FROM vehicles WHERE price BETWEEN ? AND ?";
        List<Vehicle> vehicles = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setDouble(1, minPrice);
            preparedStatement.setDouble(2, maxPrice);
            try (ResultSet results = preparedStatement.executeQuery()) {
                while (results.next()) {
                    vehicles.add(createVehicleFromResultSet(results));
                }
            }
        }
        return vehicles;
    }

    public List<Vehicle> searchByMakeModel(String make, String model) throws SQLException {
        String query = "SELECT * FROM vehicles WHERE make LIKE ? AND model LIKE ?";
        List<Vehicle> vehicles = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, "%" + make + "%");
            preparedStatement.setString(2, "%" + model + "%");
            try (ResultSet results = preparedStatement.executeQuery()) {
                while (results.next()) {
                    vehicles.add(createVehicleFromResultSet(results));
                }
            }
        }
        return vehicles;
    }

    public List<Vehicle> searchByYearRange(int minYear, int maxYear) throws SQLException {
        String query = "SELECT * FROM vehicles WHERE year BETWEEN ? AND ?";
        List<Vehicle> vehicles = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, minYear);
            preparedStatement.setInt(2, maxYear);
            try (ResultSet results = preparedStatement.executeQuery()) {
                while (results.next()) {
                    vehicles.add(createVehicleFromResultSet(results));
                }
            }
        }
        return vehicles;
    }

    public List<Vehicle> searchByColor(String color) throws SQLException {
        String query = "SELECT * FROM vehicles WHERE color = ?";
        List<Vehicle> vehicles = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, color);
            try (ResultSet results = preparedStatement.executeQuery()) {
                while (results.next()) {
                    vehicles.add(createVehicleFromResultSet(results));
                }
            }
        }
        return vehicles;
    }

    public List<Vehicle> searchByMileageRange(int minMileage, int maxMileage) throws SQLException {
        String query = "SELECT * FROM vehicles WHERE odometer BETWEEN ? AND ?";
        List<Vehicle> vehicles = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, minMileage);
            preparedStatement.setInt(2, maxMileage);
            try (ResultSet results = preparedStatement.executeQuery()) {
                while (results.next()) {
                    vehicles.add(createVehicleFromResultSet(results));
                }
            }
        }
        return vehicles;
    }

    public List<Vehicle> searchByType(String type) throws SQLException {
        String query = "SELECT * FROM vehicles WHERE vehicleType = ?";
        List<Vehicle> vehicles = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, type);
            try (ResultSet results = preparedStatement.executeQuery()) {
                while (results.next()) {
                    vehicles.add(createVehicleFromResultSet(results));
                }
            }
        }
        return vehicles;
    }

    private Vehicle createVehicleFromResultSet(ResultSet resultSet) throws SQLException {
        return new Vehicle(
                resultSet.getString("vin"),
                resultSet.getString("make"),
                resultSet.getString("model"),
                resultSet.getInt("year"),
                resultSet.getBoolean("SOLD"),
                resultSet.getString("color"),
                resultSet.getString("vehicleType"),
                resultSet.getInt("odometer"),
                resultSet.getDouble("price")
        );
    }
}
