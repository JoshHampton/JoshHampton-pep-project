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

    public Message getMessageByMessageId(int id){
        return messageDAO.getMessageByMessageIdHandler(id);
    }

    public List<Message> getMessagesByAccountId(int id){
        return messageDAO.getMessagesByAccountIdHandler(id);
    }

    public Message deleteMessageById(int id){
        Message message = this.messageDAO.getMessageByMessageIdHandler(id);

        messageDAO.deleteMessageByIdHandler(id);
        
        if (message == null){
            return null;
        }return message;
    }

    public Message patchMessageById(int id, String body){
        messageDAO.patchMessageByIdHandler(id, body);
        return messageDAO.getMessageByMessageIdHandler(id);
    }
}
