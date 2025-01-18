package Service;

import java.util.List;

import DAO.MessageDAO;
import Model.Message;
import java.util.ArrayList;
import java.util.List;

public class MessageService {

    private MessageDAO messageDAO;

    public MessageService(){
        messageDAO = new MessageDAO();
    }

    public MessageService(MessageDAO messageDAO){
        this.messageDAO = messageDAO;
    }

    public List<Message> getAllMessages() {
        return messageDAO.getAllMessages();
    }

    public List<Message> getAllMessagesByAccountId(int account_id) {
        List<Message> matchingMessages = messageDAO.getAllMessagesByAccountId(account_id);
        return (matchingMessages != null) ? matchingMessages : null;
    }

    public Message createNewMessage(Message message) {
        return (message != null && !message.getMessage_text().trim().isEmpty() && message.getMessage_text().length() < 255) ? messageDAO.createNewMessage(message) : null;
    }

    public Message updateMessageById(int message_id, Message message) {
        return (message != null && !message.getMessage_text().trim().isEmpty() && message.getMessage_text().length() < 255) ? messageDAO.updateMessageById(message_id, message) : null;
    }

    public Message getMessageById(int message_id) {
        return messageDAO.getMessageById(message_id);
    }

    public Message deleteMessageById(int message_id) {
        return messageDAO.deleteMessageById(message_id);
    }


}
