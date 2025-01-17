package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.Account;
import Util.ConnectionUtil;

public class AccountDAO {


    public Account getAccountById(int account_id){
        Connection connection = ConnectionUtil.getConnection();
         try{
            String sqlStatement = "SELECT * FROM account WHERE account_id =  ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setInt(1, account_id);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                Account mappedAccount = new Account(rs.getInt("account_id"), rs.getString("username"),rs.getString("password"));
                return mappedAccount;
            }
         }catch (SQLException exception) {
            System.out.println();

         }
        return null;
    }

}
