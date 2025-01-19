package Service;

import java.util.List;

import DAO.MessageDAO;
import Model.Message;
import java.util.ArrayList;
import java.util.List;

public class MessageService {

    private MessageDAO messageDAO;

    /*
     * 
     * @Param
     */
    public MessageService(){
        messageDAO = new MessageDAO();
    }

    /*
     * 
     * @Param
     */
    public MessageService(MessageDAO messageDAO){
        this.messageDAO = messageDAO;
    }

    /*
     * 
     * @Param
     */
    public List<Message> getAllMessages() {
        return messageDAO.getAllMessages();
    }

    /*
     * 
     * @Param int account ID number
     */
    public List<Message> getAllMessagesByAccountId(int account_id) {
        List<Message> matchingMessages = messageDAO.getAllMessagesByAccountId(account_id);
        return (matchingMessages != null) ? matchingMessages : null;
    }

    /*
     * 
     * @Param
     */
    public Message createNewMessage(Message message) {
        return (message != null && !message.getMessage_text().trim().isEmpty() && message.getMessage_text().length() < 255) ? messageDAO.createNewMessage(message) : null;
    }

    /*
     * 
     * @Param
     */
    public Message updateMessageById(int message_id, Message message) {
        Message messageToUpdate = messageDAO.getMessageById(message_id);
        if (message.getMessage_text() != null && messageToUpdate != null && !message.getMessage_text().trim().isEmpty() && message.getMessage_text().length() < 255){
            messageToUpdate.setMessage_text(message.getMessage_text());
            return messageDAO.updateMessageById(message_id, messageToUpdate);
        }

        return null;
    }

    /*
     * 
     * @Param int message ID number
     */
    public Message getMessageById(int message_id) {
        return messageDAO.getMessageById(message_id);
    }

    /*
     * 
     * @Param int message ID number
     */
    public Message deleteMessageById(int message_id) {
        return messageDAO.deleteMessageById(message_id);
    }


}
