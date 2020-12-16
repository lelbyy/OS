import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Vector;

public class Main{

    private JFrame frame ;
    private JTextField textFieldName;
    private JTextField textField_1;
    Disk disk= new Disk();

    private JList list;
    FileSystem jfs = new FileSystem();
    DefaultListModel dlm = new DefaultListModel();

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Main window = new Main();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Main() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 767, 725);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);


        list = new JList(dlm);
        list.setBounds(17, 23, 357, 220);
        frame.getContentPane().add(list);
        dlm.addAll(jfs.getCurrentCatalog().getListFiles());
        list.addListSelectionListener(new listSelectionListener());

        textFieldName = new JTextField();
        textFieldName.setBounds(21, 391, 86, 20);
        frame.getContentPane().add(textFieldName);
        textFieldName.setColumns(10);

        JButton btnCreateCatalog = new JButton("\u0421\u043E\u0437\u0434\u0430\u0442\u044C \u043A\u0430\u0442\u0430\u043B\u043E\u0433");
        btnCreateCatalog.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(textFieldName.getText().equals("")){
                    return;
                }
                jfs.createCatalog(textFieldName.getText());
                dlm.addElement((Catalogs)jfs.getCurrentCatalog().getLast());
                frame.repaint();
            }
        });
        btnCreateCatalog.setBounds(125, 391, 119, 23);
        frame.getContentPane().add(btnCreateCatalog);

        JButton btnCreateFile = new JButton("\u0421\u043E\u0437\u0434\u0430\u0442\u044C \u0444\u0430\u0439\u043B");
        btnCreateFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(textFieldName.getText().equals("")){
                    return;
                }
                jfs.createFile(textFieldName.getText());
                dlm.addElement((Files)jfs.getCurrentCatalog().getLast());
                // frame.repaint();
            }
        });
        btnCreateFile.setBounds(254, 390, 120, 23);
        frame.getContentPane().add(btnCreateFile);






        JButton btnOpen = new JButton("\u041E\u0442\u043A\u0440\u044B\u0442\u044C");
        btnOpen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(list.getSelectedValue().getClass().getSimpleName().equals("Catalogs")){
                    Catalogs catalog = (Catalogs)list.getSelectedValue();
                    jfs.openCatalog(list.getSelectedIndex());
                    textField_1.setText(textField_1.getText() + catalog.getNameCatalog() + "/");
                    dlm.clear();
                    dlm.addAll(jfs.getCurrentCatalog().getListFiles());
                }
            }
        });
        btnOpen.setBounds(125, 260, 135, 23);
        frame.getContentPane().add(btnOpen);

        JButton btnBack = new JButton("\u041D\u0430\u0437\u0430\u0434");
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(jfs.getCurrentCatalog().getParentCatalog() == null){
                    return;
                }

                jfs.returnToParentCatalog();

                StringBuilder strB = new StringBuilder(textField_1.getText());
                strB.reverse();
                strB = new StringBuilder(strB.substring(1));
                strB = new StringBuilder(strB.substring(strB.indexOf("/")));
                strB.reverse();
                String str = new String(strB);
                textField_1.setText(str);

                dlm.clear();
                dlm.addAll(jfs.getCurrentCatalog().getListFiles());
            }
        });
        btnBack.setBounds(21, 260, 89, 23);
        frame.getContentPane().add(btnBack);



        textField_1 = new JTextField();
        textField_1.setEditable(false);
        textField_1.setBounds(24, 0, 717, 20);
        frame.getContentPane().add(textField_1);
        textField_1.setColumns(10);
        textField_1.setText(jfs.getRootCatalog().nameCatalog + ":/");

        JButton btnDelete = new JButton("\u0423\u0434\u0430\u043B\u0438\u0442\u044C");
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (list.getSelectedValue().getClass().getSimpleName().equals("Catalogs")) {
                    int indexCatalog = list.getSelectedIndex();
                    jfs.deleteCatalog(indexCatalog);
                    dlm.removeElement(list.getSelectedValue());
                    frame.repaint();
                    return;

                }

                if (list.getSelectedValue().getClass().getSimpleName().equals("Files")) {
                    int indexFiles = list.getSelectedIndex();
                    jfs.deleteFile(indexFiles);
                    dlm.removeElement(list.getSelectedValue());
                    frame.repaint();
                    return;
                }

            }
        });
        btnDelete.setBounds(394, 425, 89, 23);
        frame.getContentPane().add(btnDelete);

        JButton btnEnlargeFile = new JButton("\u0423\u0432\u0435\u043B\u0438\u0447\u0438\u0442\u044C \u0444\u0430\u0439\u043B");
        btnEnlargeFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int indexCatalog = list.getSelectedIndex();
                jfs.enlargeFile(indexCatalog);
                frame.repaint();
            }
        });
        btnEnlargeFile.setBounds(21, 357, 135, 23);
        frame.getContentPane().add(btnEnlargeFile);

        JButton btnMove = new JButton("\u041F\u0435\u0440\u0435\u043C\u0435\u0441\u0442\u0438\u0442\u044C");
        JButton btnCopy = new JButton("\u041A\u043E\u043F\u0438\u0440\u043E\u0432\u0430\u0442\u044C");
        JButton btnPaste = new JButton("Вставить");
        btnPaste.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnEnlargeFile.setEnabled(true);
                btnCopy.setEnabled(true);
                btnCreateCatalog.setEnabled(true);
                btnDelete.setEnabled(true);
                btnMove.setEnabled(true);
                btnCreateFile.setEnabled(true);
                if(jfs.getRememberObject().getClass().getSimpleName().equals("Catalogs")){
                    Catalogs catalog = (Catalogs)jfs.getRememberObject();
                    catalog.setParentCatalog(jfs.currentCatalog);
                    jfs.getCurrentCatalog().getListFiles().add(catalog);
                    dlm.addElement((Catalogs)jfs.getCurrentCatalog().getLast());
                }

                if(jfs.getRememberObject().getClass().getSimpleName().equals("Files")){
                    Files file = (Files)jfs.getRememberObject();
                    jfs.getCurrentCatalog().getListFiles().add(file);
                    dlm.addElement((Files)jfs.getCurrentCatalog().getLast());
                }
                jfs.setRememberObject(null);
                frame.repaint();
            }
        });
        btnPaste.setBounds(10, 425, 100, 23);
        frame.getContentPane().add(btnPaste);


        btnMove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(jfs.getRememberObject() != null){
                    return;
                }
                btnEnlargeFile.setEnabled(false);
                btnCopy.setEnabled(false);
                btnCreateCatalog.setEnabled(false);
                btnDelete.setEnabled(false);
                btnMove.setEnabled(false);
                btnCreateFile.setEnabled(false);
                jfs.setRememberObject(list.getSelectedValue());
                if (list.getSelectedValue().getClass().getSimpleName().equals("Catalogs")) {
                    Catalogs catalog = (Catalogs)list.getSelectedValue();
                    jfs.deleteLinkToCatalog(catalog.getNameCatalog());
                    dlm.removeElement(list.getSelectedValue());
                    return;
                }
                if (list.getSelectedValue().getClass().getSimpleName().equals("Files")) {
                    Files file = (Files)list.getSelectedValue();
                    jfs.deleteLinkToFile(file.getNameFile());
                    dlm.removeElement(list.getSelectedValue());
                }
            }
        });
        btnMove.setBounds(254, 425, 120, 23);
        frame.getContentPane().add(btnMove);

        btnCopy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(jfs.getRememberObject() != null){
                    return;
                }
                btnEnlargeFile.setEnabled(false);
                btnCopy.setEnabled(false);
                btnCreateCatalog.setEnabled(false);
                btnDelete.setEnabled(false);
                btnMove.setEnabled(false);
                btnCreateFile.setEnabled(false);
                //      jfs.setRememberObject(list.getSelectedValue());
                if (list.getSelectedValue().getClass().getSimpleName().equals("Catalogs")) {
                    Catalogs catalog = (Catalogs) list.getSelectedValue();
                    try {
                        jfs.setRememberObject(catalog.clone2());
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    } catch (ClassNotFoundException classNotFoundException) {
                        classNotFoundException.printStackTrace();
                    }
                }
                if (list.getSelectedValue().getClass().getSimpleName().equals("Files")) {
                    Files file = (Files) list.getSelectedValue();
                    try {
                        jfs.setRememberObject(file.clone2());
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    } catch (ClassNotFoundException classNotFoundException) {
                        classNotFoundException.printStackTrace();
                    }

                }
            }
        });
        btnCopy.setBounds(125, 425, 119, 23);
        frame.getContentPane().add(btnCopy);
    }

    class listSelectionListener implements ListSelectionListener {
        Vector<Integer> selectVector;
        Vector<Integer> currentSelectVector;
        @Override
        public void valueChanged(ListSelectionEvent e) {
            if(dlm == null){
                return;
            }
            try {
                if (((JList<Object>) e.getSource()).getSelectedValue().getClass().getSimpleName().equals("Catalogs")) {
                    Catalogs selected = (Catalogs) ((JList<Object>) e.getSource()).getSelectedValue();
                    selectVector = selected.getSegmentsVector();
                    if (currentSelectVector != null) {
                        for (int i = 0; i < currentSelectVector.size(); i++) {
                            disk.getSegment(currentSelectVector.get(i)).setSelect(false);
                        }
                    }
                    for (int i = 0; i < selectVector.size(); i++) {
                        disk.getSegment(selectVector.get(i)).setSelect(true);
                    }
                    currentSelectVector = selectVector;
                    frame.repaint();
                    return;
                }


                if (((JList<Object>) e.getSource()).getSelectedValue().getClass().getSimpleName().equals("Files")) {
                    Files selected = (Files) ((JList<Object>) e.getSource()).getSelectedValue();
                    selectVector = selected.getSegmentsVector();
                    if (currentSelectVector != null) {
                        for (int i = 0; i < currentSelectVector.size(); i++) {
                            disk.getSegment(currentSelectVector.get(i)).setSelect(false);
                        }
                    }
                    for (int i = 0; i < selectVector.size(); i++) {
                        disk.getSegment(selectVector.get(i)).setSelect(true);
                    }
                    currentSelectVector = selectVector;

                }
            }catch (NullPointerException a){

            }
            frame.repaint();
        }
    }
}
