package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Message;
import Util.ConnectionUtil;

public class MessageDAO {
    
    
    // FIXME: finish implementing the correct data / methods 
    /*
    Whem retrieving a message from the database, all fields will be needed. In that case, a constructor with all
    * fields is needed.
    * @param message_id
    * @param posted_by
    * @param message_text
    * @param time_posted_epoch
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

    public Message createNewMessage(Message message){
        Connection connection = ConnectionUtil.getConnection();
        try {
            //Write SQL logic here
            String sql = "INSERT INTO message (posted_by, message_text, time_posted_epoch) VALUES (?, ?, ?);";
    
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //write preparedStatement's setString and setInt methods here.
            preparedStatement.setInt(1, message.getPosted_by());
            preparedStatement.setString(2, message.getMessage_text());
            preparedStatement.setLong(3, message.getTime_posted_epoch());

            preparedStatement.executeUpdate();
            return message;
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }



    
 

    public Message updateMessageById(int message_id){
        
    }

    public Message deleteMessageById(int message_id){

    }       


}
