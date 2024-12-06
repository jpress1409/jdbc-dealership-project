package com.yearup.dealership.db;

import com.yearup.dealership.models.LeaseContract;
import com.yearup.dealership.models.Vehicle;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LeaseDao {
    private DataSource dataSource;

    public LeaseDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addLeaseContract(LeaseContract leaseContract) throws SQLException{

        String query = "INSERT INTO lease_contract (VIN, lease_start, lease_end, monthly_payment)" +
                "VALUES (?, ?, ?, ?)";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query,
            PreparedStatement.RETURN_GENERATED_KEYS)){

            statement.setString(1, leaseContract.getVin());
            statement.setString(2, leaseContract.getLeaseStart().toString());
            statement.setString(3, leaseContract.getLeaseEnd().toString());
            statement.setDouble(4, leaseContract.getMonthlyPayment());

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
