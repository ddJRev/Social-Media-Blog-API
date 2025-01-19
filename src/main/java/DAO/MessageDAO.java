package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import Model.Message;
import Util.ConnectionUtil;

public class MessageDAO {
    
    
    /**
    *
    * @param message_id
    * @Return Message - the retrieved message object 
    * @Return null
    */
    public Message getMessageById(int message_id){
        Connection connection = ConnectionUtil.getConnection();
        try{
            String sqlStatement = "SELECT * FROM message WHERE message_id =  ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setInt(1, message_id);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                Message mappedMessage = new Message(rs.getInt("message_id"), rs.getInt("posted_by"),rs.getString("message_text"),rs.getLong("time_posted_epoch"));
                return mappedMessage;
            }
        }catch (SQLException exception) {
            System.out.println(exception.getMessage());

        }
        return null;
    }
    
    
    /**
     * 
     * @Param int account ID number
     * @Return List<Message> - all matching Message objects
     */
    public List<Message> getAllMessagesByAccountId(int account_id){
        List<Message> messages = new ArrayList<>();
        Connection connection = ConnectionUtil.getConnection();
        
        try{
            String sqlStatement = "SELECT * FROM message WHERE posted_by = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setInt(1, account_id);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                
                Message mappedMessage = new Message(rs.getInt("message_id"), rs.getInt("posted_by"),rs.getString("message_text"),rs.getLong("time_posted_epoch"));
                messages.add(mappedMessage);
            }
        }catch (SQLException exception) {
            System.out.println(exception.getMessage());

        }
        return messages;
    }
    

    /**
     * 
     * @Return List<Message> - all matching Message objects
     */
    public List<Message> getAllMessages(){
        List<Message> messages = new ArrayList<>();
        Connection connection = ConnectionUtil.getConnection();
        try{
            String sqlStatement = "SELECT * FROM message;";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                Message mappedMessage = new Message(rs.getInt("message_id"), rs.getInt("posted_by"),rs.getString("message_text"),rs.getLong("time_posted_epoch"));
                messages.add(mappedMessage);
            }
        }catch (SQLException exception) {
            System.out.println(exception.getMessage());

        }
        return messages;
    }

    /**
     * 
     * @Param Message object
     * @Return Message - newly created message
     * @Return null
     */
    public Message createNewMessage(Message message){
        Connection connection = ConnectionUtil.getConnection();
        try {
            //Write SQL logic here
            String sql = "INSERT INTO message (posted_by, message_text, time_posted_epoch) VALUES (?, ?, ?);";
    
            //https://docs.oracle.com/en/java/javase/21/docs/api/java.sql/java/sql/Statement.html#RETURN_GENERATED_KEYS
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            //write preparedStatement's setString and setInt methods here.
            preparedStatement.setInt(1, message.getPosted_by());
            preparedStatement.setString(2, message.getMessage_text());
            preparedStatement.setLong(3, message.getTime_posted_epoch());

            preparedStatement.executeUpdate();
            ResultSet pkeyResultSet = preparedStatement.getGeneratedKeys();
            if(pkeyResultSet.next()){
                int generated_message_id = (int) pkeyResultSet.getLong(1);
                return new Message(generated_message_id, message.getPosted_by(), message.getMessage_text(), message.getTime_posted_epoch());
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * 
     * @Param int message ID number
     * @Param Message object
     * @Return Message - newly updated message
     * @Return null
     */
    public Message updateMessageById(int messageId, Message updatedMessage){
        Connection connection = ConnectionUtil.getConnection();
        try{
            String sqlStatement = "UPDATE message SET message_text = ? WHERE message_id =  ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);

            preparedStatement.setString(1, updatedMessage.getMessage_text());
            preparedStatement.setInt(2, messageId);
            
            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0){
                return updatedMessage;
            }
        }catch (SQLException exception) {
            System.out.println(exception.getMessage());

        }
        return null;
    }  

    /**
     * 
     * @Param int message ID number
     * @Return Message - the deleted message
     * @Return null
     */
    public Message deleteMessageById(int message_id){
        Connection connection = ConnectionUtil.getConnection();
        try{
            // create message object to return on success
            Message deletedMessage = getMessageById(message_id);
            String sqlStatement = "DELETE FROM message WHERE message_id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setInt(1, message_id);

            int rowsDeleted = preparedStatement.executeUpdate();
            if (deletedMessage != null && rowsDeleted > 0){
                return deletedMessage;
            }
        }catch (SQLException exception) {
            System.out.println(exception.getMessage());

        }
        return null;
    }


}
