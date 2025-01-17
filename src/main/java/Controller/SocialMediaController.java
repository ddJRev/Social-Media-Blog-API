package Controller;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


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
        app.get("example-endpoint", this::exampleHandler);
        app.post("/messages/", this::createNewMessageHandler);
        app.post("/messages/{message_id}", this::updateMessageByIdHandler);
        app.delete("/messages/{message_id}", this::deleteMessageByIdHandler);
        app.get("/messages/", this::getAllMessagesHandler);
        app.get("/messages/{message_id}", this::getMessageByIdHandler);
        app.get("/accounts/{account_id}/", this::getAllMessagesByAccountIdHandler);
        return app;
    }



    /**
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void createNewMessageHandler(Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(context.body(), Message.class);
        Message newMessage = messageService.createNewMessage(message);
        
        if(newMessage!=null){
            context.json(mapper.writeValueAsString(newMessage));
        }else{
            context.status(400);
        }
    }

    /**
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void updateMessageByIdHandler(Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(context.body(), Message.class);
        Message updatedMessage = messageService.updateMessageById(message);
        
        if(updatedMessage!=null){
            context.json(mapper.writeValueAsString(updatedMessage));
        }else{
            context.status(400);
        }
    }

    /**
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void deleteMessageByIdHandler(Context context) {
            
    }

    /**
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void getAllMessagesByAccountIdHandler(Context context) {
        List<Message> accountMessages = messageService.getAllMessagesByAccountId();
        context.json(accountMessages);
    }

    /**
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void getAllMessagesHandler(Context context) {
        List<Message> messages = messageService.getAllMessagesByAccountId();
        context.json(messages);
    }

    /**
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */ 
    private void getMessageByIdHandler(Context context){
        Message message = messageService.getMessageById();
        if(message!=null){
            context.json(message);
        }else{
            context.status(400);
        }
    }




    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void exampleHandler(Context context) {
        context.json("sample text");
    }




}