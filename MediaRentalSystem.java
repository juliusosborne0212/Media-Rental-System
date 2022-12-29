
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.List;
/**
 * Uses Swing i.e. GUI (graphical user interface) for managing
 * and using all classes. This is main starting app.
 */
public class MediaRentalSystem extends JFrame implements ActionListener, ChangeListener {
    //controls
    private Button btnLoad,btnShow, btnFind,btnRent,btnClear, btnQuit;
    private JTextField txtInput;
    private JLabel lblPrompt;
    private JTextArea txtOutput;
    private JCheckBox chkUseDialogs;
    private JMenuBar mBar;
    private JMenu menu;
    private JMenuItem itemLoad, itemShow, itemFind, itemRent, itemClear, itemQuit;
    private JPanel pnlNorth;
    //manager object to manage media
    private Manager manager = new Manager();


    //create all gui in constructor
    public MediaRentalSystem() {
        //set title and size of frame
        setTitle("Welcome Media Rental System");
        setSize(600, 500);

        //get content of the frame
        Container contentPane = getContentPane();

        //north panel consist of buttons and input field
        pnlNorth = new JPanel(new GridLayout(3, 1,5,5));
        //add panel to frame
        contentPane.add(pnlNorth,BorderLayout.NORTH);
        pnlNorth.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        //add buttons to menu
        //create menu bar
        mBar = new JMenuBar();
        //create menu with name Menu
        menu = new JMenu("Menu");
        //add menu to menu bar
        mBar.add(menu);

        //add items to menu
        itemLoad = new JMenuItem("Load Media Objects...");
        itemLoad.addActionListener(this);
        menu.add(itemLoad);

        itemShow = new JMenuItem("Show Media Objects...");
        itemShow.addActionListener(this);
        menu.add(itemShow);

        itemFind = new JMenuItem("Find Media Objects...");
        itemFind.addActionListener(this);
        menu.add(itemFind);

        itemRent = new JMenuItem("Rent Media Objects...");
        itemRent.addActionListener(this);
        menu.add(itemRent);

        itemClear = new JMenuItem("Clear output window");
        itemClear.addActionListener(this);
        menu.add(itemClear);

        menu.addSeparator();

        itemQuit = new JMenuItem("Quit");
        itemQuit.addActionListener(this);
        menu.add(itemQuit);

        //add menu to north panel
        pnlNorth.add(mBar);

        //button panel
        JPanel pnlButtons = new JPanel(new GridLayout(1, 5,5,5));

        //create buttons, add listener which is current class itself and add to buttons panel
        btnLoad = new Button("Load");
        btnLoad.addActionListener(this);
        pnlButtons.add(btnLoad);

        btnShow = new Button("Show");
        btnShow.addActionListener(this);
        pnlButtons.add(btnShow);

        btnFind = new Button("Find");
        btnFind.addActionListener(this);
        pnlButtons.add(btnFind);

        btnRent = new Button("Rent");
        btnRent.addActionListener(this);
        pnlButtons.add(btnRent);

        btnClear = new Button("Clear");
        btnClear.addActionListener(this);
        pnlButtons.add(btnClear);

        btnQuit = new Button("Quit");
        btnQuit.addActionListener(this);
        pnlButtons.add(btnQuit);

        //add button panel to north panel
        pnlNorth.add(pnlButtons);

        //create input panel
        JPanel pnlInput = new JPanel(new GridLayout(1, 3,5,5));
        //add checkbox to it
        chkUseDialogs = new JCheckBox("Use dialogs for input");
        pnlInput.add(chkUseDialogs);

        //add label to it
        lblPrompt = new JLabel("No dialog mode input:");
        pnlInput.add(lblPrompt);

        //add text field to it
        txtInput = new JTextField();
        pnlInput.add(txtInput);

        //add listener to check box
        chkUseDialogs.addChangeListener(this);
        //mark it checked by default
        chkUseDialogs.setSelected(true);

        //add input panel to north panel
        pnlNorth.add(pnlInput);

        //create center panel
        JPanel pnlCenter = new JPanel();

        //add output area
        txtOutput = new JTextArea(20, 80);
        Font font = new Font("Monospaced", Font.BOLD, 12);
        txtOutput.setFont(font);
        JScrollPane sp = new JScrollPane(txtOutput);
        pnlCenter.add(sp);

        //add panel to frame center
        contentPane.add(pnlCenter,BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }


    //based on which button or menu item is clicked,
    //process it.
    @Override
    public void actionPerformed(ActionEvent e) {
        //get clicked button
        Object source = e.getSource();
        String str;
        //try block to catch exceptions
        try {
            //based on which button is clicked, call appropriate manager methods
            if (source == btnLoad || source==itemLoad) {
                //create file chooser
                JFileChooser folderChooser = new JFileChooser();
                //set filter to folders
                folderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                //set current directory
                folderChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
                //if folder is chosen
                if(folderChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                    //load media objects from folder
                    manager.loadMedia(folderChooser.getSelectedFile().getName());
                    //show loaded objects
                    txtOutput.setText(manager.toString());
                }
            } else if (source == btnShow || source==itemShow) {
                //show loaded objects
                txtOutput.setText(manager.toString());
            }
            else if (source == btnFind || source==itemFind) {
                //get title
                String title;
                if(chkUseDialogs.isSelected()) {
                    title = JOptionPane.showInputDialog(this,"Enter the Title");
                } else {
                    title = txtInput.getText();
                }
                //get media list
                List<Media> mediaList = manager.find(title);
                //create output string
                str = "";
                for(Media media:mediaList) {
                    str+=media+"\r\n";
                }
                //display output
                txtOutput.setText(str);
            } else if (source == btnClear || source==itemClear) {
                //clear output area
                txtOutput.setText("");
                //clear input area
                txtInput.setText("");
            }
            else if (source == btnRent || source==itemRent) {
                String strId;
                if(chkUseDialogs.isSelected()) {
                    strId = JOptionPane.showInputDialog(this,"Enter the id");
                } else {
                    strId = txtInput.getText();
                }
                int id = Integer.parseInt(strId);
                //display info for user
                str = String.format("Media was successfully rented. Rental fee is $%.2f",manager.rent(id));
                //display output
                txtOutput.setText(str);
            }
            else if (source == btnQuit || source==itemQuit) {
                //quit app
                System.exit(0);
            }
        } catch(Exception ex) {
            //display error
            txtOutput.setText(ex.getMessage());
        }

        //repaint panel as after menu is shown, some part of panel were not show
        pnlNorth.revalidate();
    }

    //if check box state changed, enable or disable input box
    @Override
    public void stateChanged(ChangeEvent e) {
        if(chkUseDialogs.isSelected()) {
            lblPrompt.setEnabled(false);
            txtInput.setEnabled(false);
        } else {
            lblPrompt.setEnabled(true);
            txtInput.setEnabled(true);
        }
    }


   //Main method. Program starts from this point.
    public static void main(String[] args) {
        //call constructor by creating instance of rental system
        new MediaRentalSystem();
    }


}
