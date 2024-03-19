import java.awt.Point;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import javax.swing.DefaultListModel;
public class Persistence implements Serializable{
    private Point windowPosition = new Point(0, 0);
    private int windowWidth = 250;
    private int windowHeight = 400;
    private DefaultListModel<ToDoItem> listModel = new
            DefaultListModel<ToDoItem>();
    private static final String saveFile = "todolist.ser";
    public void markAsDone(int index){
        listModel.getElementAt(index).markAsDone();
        save();
    }
    public void markAsNotDoneYet(int index){
        listModel.getElementAt(index).markAsNotDoneYet();
        save();
    }
    public Point getWindowPosition() {
        return windowPosition;
    }
    public void setWindowPosition(Point windowPosition) {
        this.windowPosition = windowPosition;
        this.save();
    }
    public int getWindowWidth() {
        return windowWidth;
    }
    public void setWindowWidth(int windowWidth) {
        this.windowWidth = windowWidth;
        this.save();
    }
    public int getWindowHeight() {
        return windowHeight;
    }
    public void setWindowHeight(int windowHeight) {
        this.windowHeight = windowHeight;
        this.save();
    }
    public DefaultListModel<ToDoItem> getListModel() {
        return listModel;
    }
    public void setListModel(DefaultListModel<ToDoItem> listModel) {
        this.listModel = listModel;
        this.save();
    }
    public void addElementToListModel(ToDoItem item){
        listModel.addElement(item);
        save();
    }
    public void updateListModelElementName(int index, String newName){
        listModel.getElementAt(index).setName(newName);
        save();
    }
    public void removeElementFromListModel(int index){
        listModel.remove(index);
        save();
    }
    public void save(){
        save(this);
    }
    public static void save(Persistence toSave){
        try{
            FileOutputStream fileOut = new FileOutputStream(saveFile);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(toSave);
            out.close();
            fileOut.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public static Persistence load(){
        Persistence result = new Persistence();
        FileInputStream fileIn;
        try {
            fileIn = new FileInputStream(saveFile);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            result = (Persistence) in.readObject();
            in.close();
            fileIn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
