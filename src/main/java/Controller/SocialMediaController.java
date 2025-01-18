package Controller;

import static org.mockito.ArgumentMatchers.anyList;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import Model.Account;
import Model.Message;
import Service.AccountService;
import Service.MessageService;
import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.Handler;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    MessageService messageService;
    AccountService accountService;

    public SocialMediaController(){
        this.messageService = new MessageService();
    }   
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.post("/messages/", this::createNewMessageHandler);
        app.patch("/messages/{message_id}", this::updateMessageByIdHandler);
        app.delete("/messages/{message_id}", this::deleteMessageByIdHandler);
        app.get("/messages/", this::getAllMessagesHandler);
        app.get("/messages/{message_id}", this::getMessageByIdHandler);
        app.get("/accounts/{account_id}/messages", this::getAllMessagesByAccountIdHandler);
        return app;
    }


    /**
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void createNewMessageHandler(Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(context.body(), Message.class);
        Message newMessage = messageService.createNewMessage(message);
        
        if(newMessage != null){
            context.json(mapper.writeValueAsString(newMessage));
            context.status(200);
        }else{
            context.status(400);
        }
    }

    /**
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void updateMessageByIdHandler(Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode requestBody = mapper.readTree(context.body());
        Message message = mapper.readValue(context.body(), Message.class);
        int messageId = Integer.parseInt(context.pathParam("message_id")); // or posted_by
        
        Message updatedMessage = messageService.updateMessageById(messageId, message);
        
        if(updatedMessage!=null){
            context.json(mapper.writeValueAsString(updatedMessage));
            //context.json(updatedMessage);
            context.status(200);
        }else{
            context.status(200);
        }
    }

    /**
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void deleteMessageByIdHandler(Context context) throws JsonProcessingException {
        int messageId = Integer.parseInt(context.pathParam("message_id")); // or posted_by
        Message message = messageService.deleteMessageById(messageId);

        if(message!=null){
            context.json(message);
        }else{
            context.status(200);
        }
        
    }

    /**
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     * @throws JsonProcessingException 
     * @throws JsonMappingException 
     */
    private void getAllMessagesByAccountIdHandler(Context context) throws JsonMappingException, JsonProcessingException {
        int accountId = Integer.parseInt(context.pathParam("account_id")); // or posted_by

        List<Message> accountMessages = messageService.getAllMessagesByAccountId(accountId);
        
        
        if(accountMessages!=null){
            context.json(accountMessages);
        }else{
            context.status(200); 
        }
        
    }

    /**
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void getAllMessagesHandler(Context context) throws JsonMappingException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        List<Message> messages = messageService.getAllMessages();
        context.json(messages);
        if(messages!=null){
            context.json(mapper.writeValueAsString(messages));
        }else{
            context.status(200); 
        }
       
        
    }

    /**
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */ 
    private void getMessageByIdHandler(Context context) {
        int messageId = Integer.parseInt(context.pathParam("message_id")); // or posted_by
        Message message = messageService.getMessageById(messageId);
       
        if(message!=null){
            context.json(message);
        }else{
            context.status(200);
        }
    }


}