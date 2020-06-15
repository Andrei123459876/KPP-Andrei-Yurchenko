package lab3backend;

/**
 *
 * @author Andrei
 */
public class Message {
    private String text;
    private String image;
    Message(String text, String image){
        this.text=text;
        this.image=image;
    }
    
    public String getText(){
        return text;
    }
    
    public String getImg(){
        return image;
    }
}
