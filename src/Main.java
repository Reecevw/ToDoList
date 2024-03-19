import javax.swing.JFrame;
public class Main extends JFrame{
    public Main() {
        //setTitle("Todo");
        final Persistence persistence = Persistence.load();
        MainPanel mainPanel = new MainPanel(persistence);
        getContentPane().add(mainPanel);
        setSize(persistence.getWindowWidth(),
                persistence.getWindowHeight());
        setLocation(persistence.getWindowPosition());
        Runtime.getRuntime().addShutdownHook(new Thread()
        {
            @Override
            public void run()
            {
                persistence.setWindowWidth(Main.this.getWidth());
                persistence.setWindowHeight(Main.this.getHeight());
                persistence.setWindowPosition(Main.this.getLocation());
            }
        });
    }
    public static void main(String[] args) {
        Main mainFrame = new Main();
        mainFrame.setVisible(true);
    }
}
