package Service;

import Model.Message;
import DAO.MessageDAO;
import DAO.AccountDAO;

import java.util.List;

public class MessageService {
    MessageDAO messageDAO;
    AccountDAO accountDAO;
    
    public MessageService(){
        messageDAO = new MessageDAO();
    }

    public MessageService(MessageDAO messageDAO){
        this.messageDAO = messageDAO;
    }

    public List<Message> getAllMessages(){
        return messageDAO.getAllMessages();
    }

    public Message addMessage(Message message){
        //check to see if account exists
        //if (accountDAO.getAccountById(message.getPosted_by()) == null){
        //    return null;
        //}
        
        return messageDAO.insertMessage(message);
    }

    public List<Message> getMessagesById(int id){
        return messageDAO.getMessagesByIdHandler(id);
    }
}
