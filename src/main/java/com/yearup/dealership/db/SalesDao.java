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

        String query = "INSERT INTO lease_contract (date, customer_name, customer_email, vin, sales_tax, recording_fee)" +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query,
                    PreparedStatement.RETURN_GENERATED_KEYS)){

            statement.setString(1, "date");
            statement.setString(2, "customer_name");
            statement.setString(3, "customer_email");
            statement.setString(4, "vin");
            statement.setString(5, "sales_tax");
            statement.setString(6, "recording_fee");


            try(ResultSet generatedKeys = statement.getGeneratedKeys()){
                if(generatedKeys.next()){
                    int contractId = generatedKeys.getInt(1);
                }
            }
        }    }
}
