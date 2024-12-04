package com.yearup.dealership.db;

import com.yearup.dealership.models.LeaseContract;

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

        String query = "INSERT INTO lease_contract (date, customer_name, customer_email, vin, expected_ending_value, lease_fee)" +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query,
            PreparedStatement.RETURN_GENERATED_KEYS)){

            statement.setString(1, "date");
            statement.setString(2, "customer_name");
            statement.setString(3, "customer_email");
            statement.setString(4, "vin");
            statement.setString(5, "expected_ending_value");
            statement.setString(6, "lease_fee");


            try(ResultSet generatedKeys = statement.getGeneratedKeys()){
                if(generatedKeys.next()){
                    int contractId = generatedKeys.getInt(1);
                }
            }
        }

    }
}
