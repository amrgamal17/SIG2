package GUI;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class FirstFrame extends JFrame implements ActionListener {

JFrame window=new JFrame("Multiple Panels");

JPanel panel_1=new JPanel();
JPanel panel_2=new JPanel();

    private JTable tableTotalInvoice;
    private DefaultTableModel model;
    private JTable tableInvoiceDetails;
public     String[] cols={"No.","Date","Customer","Total"};
    private String[][] data= {
            {"1","22-02-2022","Ahmed","150"},
            {"2","21-02-2022","Moahmed","200"},
            {"3","21-02-2022","Ali","200"},
    };
    private String[] colsInvoiceDetails={"No.","Item Name","Item Price","Count","Item Count"};
    private String[][] dataInvoiceDetails= {
            {"1", "Mobile", "3200", "1","4"},
            {"1", "Cover", "20", "2","5"},
            {"1", "Headphone", "130", "1","6"},
            {"2", "Laptop", "4000", "1","7"},
            {"2", "Mouse", "35", "1","15"}


    };
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem LoadMenuItem;
    private JMenuItem SaveMenuItem;
    private JButton btnCreateNewInvoice;
    private JButton btnDeleteInvoice ;
    private JButton btnSave;
    private JButton btnCancel;
    private JButton btnOK;
    private JLabel labelInvoiceNum;
    private JLabel labelInvoiceTotal;
    private JTextField InvoiceDateTF;
    private JTextField AddInvoiceDateTF;
    private JTextField CustomerNameTF;
    private JTextField AddCustomerNameTF;
    public FirstFrame() {
        super("Multiple Panels");
        //Creating the Frame
        window.setLocation(100, 100);
        window.setSize(new Dimension(1024, 800));
        GridLayout layout = new GridLayout(1, 2);
        window.setLayout(layout);


//Adding panels to Frame
        window.add(panel_1);
        window.add(panel_2);

//Left Panel UI
        model = new DefaultTableModel(data,cols);
        this.tableTotalInvoice = new JTable(model);
        //this.tableTotalInvoice = new JTable(new MyModel());
        tableTotalInvoice.setSize(1024, 50);
        panel_1.add(new JScrollPane(tableTotalInvoice));


        btnCreateNewInvoice = new JButton("Create New Invoice");
        //btnCreateNewInvoice.setBounds(BorderLayout.SOUTH);
        panel_1.add(btnCreateNewInvoice, BorderLayout.SOUTH);

        btnDeleteInvoice = new JButton("Delete Invoice");
        panel_1.add(btnDeleteInvoice, BorderLayout.SOUTH);


//Right Panel UI
        panel_2.setLayout(new FlowLayout());


        panel_2.add(new JLabel("Invoice Number"));
        labelInvoiceNum=new JLabel("");
        panel_2.add(labelInvoiceNum);



        InvoiceDateTF = new JTextField(15);

        CustomerNameTF = new JTextField(15);
        panel_2.add(new JLabel("Customer Name:"));
        panel_2.add(CustomerNameTF);
        panel_2.add(new JLabel("Invoice Date:"));
        panel_2.add(InvoiceDateTF);

        panel_2.add(new JLabel("Invoice Total"));
        labelInvoiceTotal=new JLabel("");
        panel_2.add(labelInvoiceTotal);


        tableInvoiceDetails = new JTable(dataInvoiceDetails, colsInvoiceDetails);
        tableInvoiceDetails.setSize(400, 50);
        panel_2.add(new JScrollPane(tableInvoiceDetails));

        btnSave = new JButton("Save");
        panel_2.add(btnSave);

        btnCancel = new JButton("Cancel");
        panel_2.add(btnCancel);



//Menu Bar UI

        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        LoadMenuItem = new JMenuItem("Load");
        SaveMenuItem = new JMenuItem("Save");

        menuBar.add(fileMenu);
        fileMenu.add(LoadMenuItem);

        fileMenu.add(SaveMenuItem);
        window.setJMenuBar(menuBar);

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }


    public class CSVFile {
        private final ArrayList<String[]> Rs = new ArrayList<String[]>();
        private String[] OneRow;

        public ArrayList<String[]> ReadCSVfile(File DataFile) {
            try {
                BufferedReader brd = new BufferedReader(new FileReader(DataFile));
                while (brd.ready()) {
                    String st = brd.readLine();
                    OneRow = st.split(",|\\s|;");
                    Rs.add(OneRow);

                }
            }
            catch (Exception e) {
                String errmsg = e.getMessage();
                System.out.println("File not found:" + errmsg);
            }
            return Rs;
        }
    }

    public void SaveFile()
    {

        SaveMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exportToCSV(tableTotalInvoice,"C:\\Users\\amr\\Downloads\\sales-invoice-generator\\Sales Invoice Generator\\exported.csv");
                JOptionPane.showMessageDialog(null, "File Saved Successfully");
            }
        });
    }

    public void SaveFileInvoice()
    {

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exportToCSV(tableInvoiceDetails,"C:\\Users\\amr\\Downloads\\sales-invoice-generator\\Sales Invoice Generator\\exportedInvoiceDetails.csv");
                JOptionPane.showMessageDialog(null, "Invoice Details Saved Successfully");
            }
        });
    }
    public void AddRow()
    {
        JFrame AddDialogue=new JFrame("Create Invoice");
        AddDialogue.setLayout(new FlowLayout());
        AddDialogue.setLocation(100, 100);
        AddDialogue.setSize(new Dimension(400, 400));
       AddInvoiceDateTF=new JTextField(15);
        AddDialogue.add(new JLabel("Invoice Date:"));
       AddDialogue.add(AddInvoiceDateTF);

        AddCustomerNameTF=new JTextField(15);
        AddDialogue.add(new JLabel("Customer Name:"));


        AddDialogue.add(AddCustomerNameTF);
        btnOK=new JButton("OK");
        AddDialogue.add(btnOK,BorderLayout.SOUTH);
        //AddDialogue.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        tableTotalInvoice.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        btnCreateNewInvoice.addActionListener(new ActionListener() {


            @Override
            public void actionPerformed(ActionEvent e) {
                AddDialogue.setVisible(true);
                //if (tableTotalInvoice.getSelectedRow() != -1) {
                    // remove selected row from the model

                int  IdValue= tableTotalInvoice.getModel().getRowCount()-1;
                int ID=Integer.parseInt((String) tableTotalInvoice.getValueAt(IdValue,0));
                btnOK.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                    //    String text = AddInvoiceDateTF.getText();
                   //     String text2=AddCustomerNameTF.getText();
                        Object[] row = {ID+1, AddInvoiceDateTF.getText(),AddCustomerNameTF.getText() };
                        MyModel NewModel = new MyModel();
                        model.addRow(row);

                    }
                });

                 // JOptionPane.showMessageDialog(null, "Selected row deleted successfully");
                //}
            }
        });
    }

    public static void exportToCSV(JTable table,
                                   String path) {

        try {

            TableModel model = table.getModel();
            FileWriter csv = new FileWriter(new File(path));

            for (int i = 0; i < model.getColumnCount(); i++) {
                csv.write(model.getColumnName(i) + ",");
            }

            csv.write("\n");

            for (int i = 0; i < model.getRowCount(); i++) {
                for (int j = 0; j < model.getColumnCount(); j++) {
                    csv.write(model.getValueAt(i, j).toString() + ",");
                }
                csv.write("\n");
            }

            csv.close();
        } catch (IOException e) {
            System.out.println("Error "+e);
        }
    }
    public  void LoadFile() {
        LoadMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                File selectedFile = null;
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    selectedFile = fileChooser.getSelectedFile();
                }

                CSVFile Rd = new CSVFile();
                MyModel NewModel = new MyModel();
                ArrayList<String[]> Rs2 = Rd.ReadCSVfile(selectedFile);
                String file = selectedFile.toString();
                String ext=(file.substring(0, file.lastIndexOf('.')));
                if(ext=="csv"){
                    NewModel.AddCSVData(Rs2);
                    tableTotalInvoice.setModel(NewModel);}
                else {
                    JOptionPane.showMessageDialog(null, "Please choose CSV file");

                }


            }

        });
    }

public void RemoveRow()
{
    tableTotalInvoice.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    btnDeleteInvoice.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (tableTotalInvoice.getSelectedRow() != -1) {
                // remove selected row from the model
                model.removeRow(tableTotalInvoice.getSelectedRow());
                   JOptionPane.showMessageDialog(null, "Selected row deleted successfully");
            }
        }
    });
}
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    private final String[] columnNames = this.cols;
    private ArrayList<String[]> Data = new ArrayList<String[]>();
/*
    public void AddCSVData(ArrayList<String[]> DataIn) {
        this.Data = DataIn;


        model.fireTableDataChanged();
    }*/


        public void ShowInvoiceDetails() {

            tableTotalInvoice.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableTotalInvoice.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                String InDate=(String) tableTotalInvoice.getValueAt(tableTotalInvoice.getSelectedRow(),1);
                String CuName=(String) tableTotalInvoice.getValueAt(tableTotalInvoice.getSelectedRow(),2);
                InvoiceDateTF.setText(InDate);
                CustomerNameTF.setText((CuName));
                labelInvoiceNum.setText((String) tableTotalInvoice.getValueAt(tableTotalInvoice.getSelectedRow(),0));
                labelInvoiceTotal.setText((String) tableTotalInvoice.getValueAt(tableTotalInvoice.getSelectedRow(),3));
            }
        });



    }

public static void main(String[] args) {
        new FirstFrame().setVisible(true);
}
}



class MyModel extends AbstractTableModel {
    private final String[] columnNames = {"No.","Date","Customer","Total"};
    private ArrayList<String[]> Data = new ArrayList<String[]>();
    public void AddCSVData(ArrayList<String[]> DataIn) {
        this.Data = DataIn;
        this.fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return Data.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int row, int col) {
        return Data.get(row)[col];
    }
    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

}

