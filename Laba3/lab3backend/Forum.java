package lab3backend;

/**
 *
 * @author Andrei
 */
import java.util.ArrayList;

public class Forum {
    private String forumName;
    private ArrayList<Message> messageList;
    public Forum () {
        forumName = "Forum";
        messageList = new ArrayList<>();
    }
    public ArrayList<Message> returnMessages() {
        return messageList;
    }
    public void addMessage(Message msg) {
        messageList.add(msg);
    }
    public void deleteMessage(int number){
        number -= 1;
        if (number < messageList.size() && number >= 0)
            messageList.remove(number);
    }
    public String getAllMsg(){
        StringBuilder str = new StringBuilder(); 
        str.append("<html>");
        str.append(this.forumName);
        str.append(": <br>");
        Integer tmp;
        for (int i = 0; i < messageList.size(); i++){
            tmp = i + 1;
            str.append(tmp.toString()+") ");
            str.append(messageList.get(i).getText());
            
            if(!messageList.get(i).getImg().equals("")){
                str.append("код изображения: ");   
                str.append(messageList.get(i).getImg());
            }
            str.append("<br>");
        }      
        str.append("</html>");
        String res = str.toString();
        return res;
    }
    
}
