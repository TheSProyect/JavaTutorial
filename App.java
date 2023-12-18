import java.util.Scanner;
import java.io.*;
import javax.swing.*; 
import javax.swing.event.*;
import java.awt.event.*; 
import java.awt.*; 

public class App extends JFrame implements ActionListener{
  private JLabel insertData, descLabel, bsLabel, ciLabel, nfLabel, dateLabel, amountLabel;
  private JButton closeButton, registerButton, reportButton;
  private JTextField descField, amountField, bsField, ciField, dateField, nfField;

  



  public App(){
    setTitle("Registro y Control de Equipos en Centro de Investigación");

    setLayout(null);
    insertData = new JLabel("Ingrese data del equipo:"); 
    insertData.setBounds(10, 20, 200, 20);
    add(insertData);

    descLabel = new JLabel("Descripción:");
    descLabel.setBounds(15, 60, 200, 20);
    add(descLabel);

    descField = new JTextField();
    descField.setBounds(90, 60, 200, 20);
    add(descField);

    amountLabel = new JLabel("Cantidad:");
    amountLabel.setBounds(25, 100, 200, 20);
    add(amountLabel);

    amountField = new JTextField();
    amountField.setBounds(90, 100, 70, 20);
    add(amountField);
    
    bsLabel = new JLabel("Costo Unitario (Bs.):");
    bsLabel.setBounds(310, 100, 200, 20);
    add(bsLabel);

    bsField = new JTextField();
    bsField.setBounds(430, 100, 110, 20);
    add(bsField);

    dateLabel = new JLabel("<html>Fecha de adquisicion:<br>dd/mm/aaaa</html>");
    dateLabel.setBounds(15, 140, 200, 30);
    add(dateLabel);

    dateField = new JTextField();
    dateField.setBounds(140, 140, 120, 20);
    add(dateField);

    nfLabel = new JLabel("Nro. de Factura:");
    nfLabel.setBounds(330, 140, 200, 20);
    add(nfLabel);

    nfField = new JTextField();
    nfField.setBounds(430, 140, 110, 20);
    add(nfField);

    ciLabel = new JLabel("C.I. del Responsable del equipo:");
    ciLabel.setBounds(15, 180, 200, 20);
    add(ciLabel);

    ciField = new JTextField();
    ciField.setBounds(200, 180, 200, 20);
    add(ciField);

    registerButton = new JButton("Registrar data");
    registerButton.setBounds(170, 260, 140, 30);
    add(registerButton);
    registerButton.addActionListener(this);

    reportButton = new JButton("Generar Reporte");
    reportButton.setBounds(310, 260, 140, 30);
    add(reportButton);
    reportButton.addActionListener(this);

    closeButton = new JButton("Salir");
    closeButton.setBounds(450, 260, 100, 30);
    add(closeButton);
    closeButton.addActionListener(this);


  }

  public void actionPerformed(ActionEvent Event){
    if(Event.getSource() == closeButton){
      System.exit(0);
    }

    if(Event.getSource() == registerButton){
      String desc = descField.getText().trim();
      String ci = ciField.getText().trim();
      String date = dateField.getText().trim();
      String nf = nfField.getText().trim();
      String ct = amountField.getText().trim();
      String mu = bsField.getText().trim();

      if (desc.equals("") || ci.equals("") || date.equals("") || nf.equals("") || ct.equals("") || mu.equals("")){
        JOptionPane.showMessageDialog(null, "Se deben llenar todos los campos con datos validos");
      } else {
      try {
      BufferedWriter writer = new BufferedWriter(new FileWriter("inventario.txt", true));
      writer.write(desc + "#" + ct + "#" + mu + "#" + date + "#" + nf + "#" + ci + ";" + "\n");
      writer.close();
      
      } catch (IOException e) {
        e.printStackTrace();
      }
    }


    } 
    if(Event.getSource() == reportButton){
      Report IReporte = new Report();
      IReporte.setBounds(0,0, 600, 350);
      IReporte.setVisible(true);
      IReporte.setLocationRelativeTo(null);
      IReporte.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      IReporte.setResizable(false);
      IReporte.setTitle("Reporte del Inventario del Centro de Investigación");
      this.setVisible(false);
      
      
    }
  }

  


  public static void main(String[] args) throws Exception {
    
    App ICentro = new App();

    ICentro.setBounds(0,0, 600, 350);
    ICentro.setVisible(true);
    ICentro.setLocationRelativeTo(null);
    ICentro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    ICentro.setResizable(false);
    

    
    
  }


}
