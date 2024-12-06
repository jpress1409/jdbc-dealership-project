package com.yearup.dealership.db;

import com.yearup.dealership.models.SalesContract;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SalesDao {
    private DataSource dataSource;

    public SalesDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addSalesContract(SalesContract salesContract) throws SQLException{

            VehicleDao dao = new VehicleDao(dataSource);

            String query = "INSERT INTO lease_contract (VIN, sale_date, price)" +
                    "VALUES (?, ?, ?)";

            try(Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(query,
                        PreparedStatement.RETURN_GENERATED_KEYS)){

                statement.setString(1, salesContract.getVin());
                statement.setString(2, salesContract.getSaleDate().toString());
                statement.setDouble(3, salesContract.getPrice());


                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                    try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            int contractId = generatedKeys.getInt(1);
                            System.out.println("Contract ID: " + contractId);
                        } else {
                            System.out.println("No contract ID generated.");
                        }
                    }
                } else {
                    System.out.println("Insert failed, no rows affected.");
                }
            }

        }
}
