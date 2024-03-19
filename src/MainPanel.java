import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
class MainPanel extends JPanel {
    private final Persistence persistence;
    private final JTextField inputTextField = new JTextField();
    private final JButton addButton = new JButton("Add");
    private final JButton editButton = new JButton("Edit item");
    private final JButton completeButton = new JButton("Mark item as complete");
    private final JButton deleteButton = new JButton("Delete item");
    JPanel SpacingPanel = new JPanel();
    public JList<ToDoItem> list;
    private boolean isInEditMode = false;
    private int itemToEdit = -1;
    public MainPanel(Persistence _persistence) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450,350);
        frame.setResizable(false);
        frame.setLayout(new FlowLayout());
        frame.setTitle("Personal Todo list");
        SpacingPanel.setPreferredSize(new Dimension(200,0));
        //Jlabel I will be using for instruction
        JLabel emptyLabelToFillSpace = new JLabel();
        //menu bar
        JMenuBar menu = new JMenuBar();
        frame.setJMenuBar(menu); // Puts the menu into the frame
        //create menus
        JMenu File = new JMenu("File |");
        JMenu Sort = new JMenu("Sort |");
        //Adding to menu
        menu.add(File);
        menu.add(Sort);
        //next is making the drop down
        JMenuItem Save = new JMenuItem("Save file");
        JMenuItem Load = new JMenuItem("Load file");
        JMenuItem Ascending = new JMenuItem("Ascending");
        JMenuItem Prio = new JMenuItem("Due date");
        //adding the drop downs to the JMenu
        File.add(Save);
        File.add(Load);
        Sort.add(Ascending);
        Sort.add(Prio);
//adding buttons into frame
        frame.add(addButton);
        frame.add(editButton);
        frame.add(completeButton);
        frame.add(deleteButton);
        //frame.add(SpacingPanel);
        frame.add(emptyLabelToFillSpace);
        frame.add(inputTextField);
        frame.add(SpacingPanel);
        emptyLabelToFillSpace.setText("Enter item to be saved");//test
        ///adding Jtextfield for user to enter data
        inputTextField.setPreferredSize( new Dimension(220,20));
        this.persistence = _persistence;
        this.list = new JList<ToDoItem>(persistence.getListModel());
        inputTextField.addKeyListener(new KeyListener(){
            @Override
            public void keyPressed(KeyEvent arg0) {
                if(arg0.getKeyCode() == KeyEvent.VK_ENTER){
                    if(isInEditMode){
                        saveEdited();
                    } else {
                        addItem();
                    }
                }
            }
            @Override
            public void keyReleased(KeyEvent arg0) {
                // TODO Auto-generated method stub
            }
            @Override
            public void keyTyped(KeyEvent arg0) {
                // TODO Auto-generated method stub
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if(isInEditMode){
                    saveEdited();
                } else {
                    addItem();
                }
            }
        });
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startEdit();
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                persistence.removeElementFromListModel(list.getSelectedIndex());
            }
        });
        completeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                markSelectedAsDone();
            }
        });
        menu.setVisible(true);
        frame.setVisible(true);
        list.setVisibleRowCount(-1);
        frame.add(list);}
    private void markSelectedAsDone(){
        persistence.markAsDone(list.getSelectedIndex());
        list.validate();
        list.repaint();
    }
    private void markSelectedAsNotDoneYet(){
        persistence.markAsNotDoneYet(list.getSelectedIndex());
        list.validate();
        list.repaint();
    }
    private void addItem(){

        persistence.addElementToListModel(new com.trueagain.swingtodolist.ToDoItem(inputTextField.getText()));
        inputTextField.setText("");
    }
    private void startEdit(){

        inputTextField.setText(persistence.getListModel().getElementAt(list.getSelectedIndex()).toClearString());
        isInEditMode = true;
        itemToEdit = list.getSelectedIndex();
        addButton.setText("Save new");
        list.validate();
        list.repaint();
    }
    private void saveEdited(){
        persistence.updateListModelElementName(itemToEdit,
                inputTextField.getText());
        isInEditMode = false;
        itemToEdit = -1;
        addButton.setText("Add items");
        //inputTextField.setText("");
        list.validate();
        list.repaint();
    }
}
