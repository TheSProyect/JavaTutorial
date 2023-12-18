import java.util.Scanner;
import java.io.*;
import javax.swing.*; 
import javax.swing.event.*;
import java.awt.event.*; 
import java.awt.*; 

public class Report extends JFrame implements ActionListener{
    private JLabel reportType, ciLabel, total, quantity, money;
    private JTextField ciField;
    private JTextArea generalTable;
    private JScrollPane Scroller;
    private JButton continueButton, totalizeButton;
    private JRadioButton individualButton, generalButton;
    private ButtonGroup bg;
    private JPanel individualReport, generalReport;

    public Report(){
        setLayout(null);

        int amount = 0;
        int bs = 0;

        reportType = new JLabel("Tipo de Reporte:");
        reportType.setBounds(10, 20, 200, 20);
        add(reportType);

        individualButton = new JRadioButton("Individual");
        individualButton.setBounds(100, 20, 100, 20);
        individualButton.addActionListener(this);
        add(individualButton);

        generalButton = new JRadioButton("General");
        generalButton.setBounds(200, 20, 200, 20);
        generalButton.addActionListener(this);
        add(generalButton);
        
        bg = new ButtonGroup();
        bg.add(individualButton);

        bg.add(generalButton);

        continueButton = new JButton("Continuar");
        continueButton.setBounds(450, 260, 100, 30);
        add(continueButton);
        continueButton.addActionListener(this);

        ciLabel = new JLabel("C.I. del Responsable de Equipos:");
        ciLabel.setBounds(10, 30, 300, 20);
        ciField = new JTextField();
        ciField.setBounds(200, 30, 200, 20);

        totalizeButton = new JButton("Totalizar");
        totalizeButton.setBounds(410, 30, 100, 20);
        totalizeButton.addActionListener(this);

        generalTable = new JTextArea();
        Scroller = new JScrollPane(generalTable);
        Scroller.setBounds(30,10, 520, 120);

        individualReport = new JPanel();
        individualReport.setLayout(null);
        individualReport.setBounds(0, 50, 600, 190);
        individualReport.add(ciLabel);
        individualReport.add(ciField);
        individualReport.add(totalizeButton);

        add(individualReport);
            
        generalReport = new JPanel();
        generalReport.setLayout(null);
        generalReport.setBounds(0, 50, 600, 190);
        generalReport.add(Scroller);


        add(generalReport);

        total = new JLabel("Totalizacion:");
        total.setBounds(10, 240, 100, 20);
        add(total);

        money = new JLabel(bs + " Bs.");
        money.setBounds(10, 255, 100, 20);
        add(money);

        quantity = new JLabel(amount + " equipos");
        quantity.setBounds(10, 270, 100, 20);
        add(quantity);

    
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == individualButton){
            individualReport.setVisible(true);
            generalReport.setVisible(false);
            
        }
        
        if(e.getSource() == generalButton){
            individualReport.setVisible(false);
            generalReport.setVisible(true);
            int amount = 0;
            int bs = 0;
            try{
                BufferedReader reader = new BufferedReader(new FileReader("inventario.txt"));
                String Line;
                while ((Line = reader.readLine()) != null){
                    
                String Format = Line;
                String[] parts = Format.split("#");
                
                
                
                    
                amount = amount + Integer.parseInt(parts[1]);
                bs = (bs + Integer.parseInt(parts[2]))*Integer.parseInt(parts[1]);
                quantity.setText(amount + " equipos");
                money.setText(bs + " Bs.");
                
            
                
                }
                reader.close();
                } catch (IOException ex){
                    ex.printStackTrace();
                }



        }
        if(e.getSource() == continueButton){
            App ICentro = new App();

        ICentro.setBounds(0,0, 600, 350);
        ICentro.setVisible(true);
        ICentro.setLocationRelativeTo(null);
        ICentro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ICentro.setResizable(false);
        this.setVisible(false);
            
        }
        if(e.getSource() == totalizeButton){
            String ci = ciField.getText().trim();
            int amount = 0;
            int bs = 0;
            if(ci.equals("")){
                JOptionPane.showMessageDialog(null, "Ingrese una Cedula");

            }else{
                try{
                BufferedReader reader = new BufferedReader(new FileReader("inventario.txt"));
                String Line;
                while ((Line = reader.readLine()) != null){
                    
                String Format = Line;
                String[] parts = Format.split("#");
                String[] lparts = parts[5].split(";");
                int cint = Integer.parseInt(ci);
                int intlparts = Integer.parseInt(lparts[0]);
                
                if(cint == intlparts){
                    
                amount = amount + Integer.parseInt(parts[1]);
                bs = (bs + Integer.parseInt(parts[2]))*Integer.parseInt(parts[1]);
                quantity.setText(amount + " equipos");
                money.setText(bs + " Bs.");
                
            
                }
                }
                reader.close();
                } catch (IOException ex){
                    ex.printStackTrace();
                }
            }
        }
        

    }

    public static void main(String[] args) throws Exception {
        Report IReporte = new Report();
        IReporte.setBounds(0,0, 600, 350);
        IReporte.setVisible(true);
        IReporte.setLocationRelativeTo(null);
        IReporte.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        IReporte.setResizable(false);
        IReporte.setTitle("Reporte del Inventario del Centro de Investigación");




    }
}
