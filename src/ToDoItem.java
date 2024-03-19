import java.io.Serializable;
public class ToDoItem implements Serializable{
    private String name;
    private boolean isDone = false;
    public void markAsDone(){
        this.isDone = true;
    }
    public void markAsNotDoneYet(){
        this.isDone = false;
    }
    public void setName(String newName){
        this.name = newName;
    }
    public ToDoItem(String _name){
        this.name = _name;
    }
    public String toClearString(){
        return this.name;
    }
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        if(isDone){
            sb.append("<strike>");
        }
        sb.append(this.name);
        if(isDone){
            sb.append("</strike>");
        }
        sb.append("</html>");
        return sb.toString();
    }
}


