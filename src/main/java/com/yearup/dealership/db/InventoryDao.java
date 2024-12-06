package com.yearup.dealership.db;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InventoryDao {
    private DataSource dataSource;

    public InventoryDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addVehicleToInventory(String vin, int dealershipId) throws SQLException {

    String query = "INSERT INTO inventory (vin) " +
            "VALUES (?)";

    try(Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(query,
                PreparedStatement.RETURN_GENERATED_KEYS)){

        statement.setString(1, vin);

        try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                dealershipId = generatedKeys.getInt(1);
                System.out.println("Generated Dealership ID: " + dealershipId);
            }
        }



    }

    }

    public void removeVehicleFromInventory(String vin) throws SQLException{

    String query = "DELETE FROM inventory " +
            "WHERE vin = ?";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query,
                    PreparedStatement.RETURN_GENERATED_KEYS)){
            statement.setString(1, vin);

            
        }

    }
}
