package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

import Model.Account;
import Util.ConnectionUtil;

public class AccountDAO {

    /*
     * 
     * @Param
     */
    public Account createAccount(Account account){
        Connection connection = ConnectionUtil.getConnection();
        try{
            String sqlStatement = "INSERT INTO account (username, password) VALUES (?, ?) ";

            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());    

            preparedStatement.executeUpdate();
            ResultSet resultKeySet = preparedStatement.getGeneratedKeys();
            while (resultKeySet.next()){
               int generatedAccountId = resultKeySet.getInt(1);
               account.setAccount_id(generatedAccountId);
               return account;
            }
            }catch (SQLException exception) {
                exception.printStackTrace();
            }
        return null;
    }

    /*
     * 
     * @Param int account ID number
     */
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
            System.out.println(exception.getMessage());

         }
        return null;
    }

    /*
     * 
     * @Param String username
     */
    public Account getAccountByUsername(String username) {
        Connection connection = ConnectionUtil.getConnection();
         try{
            String sqlStatement = "SELECT * FROM account WHERE username = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setString(1, username);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                Account mappedAccount = new Account(rs.getInt("account_id"), rs.getString("username"),rs.getString("password"));
                return mappedAccount;
            }
         }catch (SQLException exception) {
            System.out.println(exception.getMessage());

         }
        return null;
    }

}
