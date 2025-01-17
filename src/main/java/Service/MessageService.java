package Service;

import java.util.List;

import DAO.MessageDAO;
import Model.Message;

public class MessageService {

    private MessageDAO messageDAO;

    public MessageService(){
        messageDAO = new MessageDAO();;
    }

    public MessageService(MessageDAO messageDAO){
        this.messageDAO = messageDAO;
    }

    public List<Message> getAllMessagesByAccountId() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllMessagesByAccountId'");
    }

    public Message createNewMessage(Message message) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createNewMessage'");
    }

    public Message updateMessageById(Message message) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateMessageById'");
    }

    public Message getMessageById() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMessageById'");
    }

}
