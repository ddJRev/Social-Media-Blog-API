package Service;

import java.util.List;

import DAO.MessageDAO;
import Model.Message;
import java.util.ArrayList;
import java.util.List;

public class MessageService {

    private MessageDAO messageDAO;

    /**
     * 
     */
    public MessageService(){
        messageDAO = new MessageDAO();
    }

    /**
     * 
     * Parameterized constructor for MessageService
     */
    public MessageService(MessageDAO messageDAO){
        this.messageDAO = messageDAO;
    }

    /**
     * 
     * @Return - List<Message> - all valid Message objects
     */
    public List<Message> getAllMessages() {
        return messageDAO.getAllMessages();
    }

    /**
     * 
     * @Param int account ID number
     * @Return - List<Message> - all matching Message objects
     * @Return null
     */
    public List<Message> getAllMessagesByAccountId(int account_id) {
        List<Message> matchingMessages = messageDAO.getAllMessagesByAccountId(account_id);
        return (matchingMessages != null) ? matchingMessages : null;
    }

    /**
     * 
     * @Param - Message object
     * @Return - result of method call to createNewMessage of messageDAO class
     * @Return null

     */
    public Message createNewMessage(Message message) {
        return (message != null && !message.getMessage_text().trim().isEmpty() && message.getMessage_text().length() < 255) ? messageDAO.createNewMessage(message) : null;
    }

    /**
     * 
     * @Param - int messageId
     * @Param - Message object
     * @Return - result of method call to updateMessagebyId of messageDAO class
     * @Return null
     */
    public Message updateMessageById(int message_id, Message message) {
        Message messageToUpdate = messageDAO.getMessageById(message_id);
        if (message.getMessage_text() != null && messageToUpdate != null && !message.getMessage_text().trim().isEmpty() && message.getMessage_text().length() < 255){
            messageToUpdate.setMessage_text(message.getMessage_text());
            return messageDAO.updateMessageById(message_id, messageToUpdate);
        }

        return null;
    }

    /**
     * 
     * @Param int message ID number
     * @Return - result of method call to getMessageById of messageDAO class
     */
    public Message getMessageById(int message_id) {
        return messageDAO.getMessageById(message_id);
    }

    /**
     * 
     * @Param int message ID number
     * @Return - result of method call to deleteMessageById of messageDAO class
     */
    public Message deleteMessageById(int message_id) {
        return messageDAO.deleteMessageById(message_id);
    }


}
