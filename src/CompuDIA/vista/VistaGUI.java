package CompuDIA.vista;

import CompuDIA.modelo.Computador;
import CompuDIA.modelo.ComputadorDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaGUI extends JFrame {

    private String codText;
    private ComputadorDTO computo;
    private JPanel mainPanel;
    private JTextArea textArea;
    private JTextField textCode;
    private JTextField textMemoria;
    private JTextField textMarca;


    //Constructor
    public VistaGUI(String tittle){
        super(tittle);
        computo = new ComputadorDTO();
        this.pack();
        this.setVisible(true);
        this.setBounds(250,250,800,500);
        this.setResizable(false);
        // LLAMAMOS METODOS DE LOS COMPONENTES
        panel();
        labels();
        txtFields();
        txtArea();
        buttonInsert();
        buttonDelete();
        buttonEdit();
        buttonSearch();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void panel(){
        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(Color.gray);
        this.getContentPane().add(mainPanel);
    }

    private void labels(){
        JLabel labelTittle = new JLabel();
        labelTittle.setText("BASE DE DATOS COMPULISTO");
        labelTittle.setBounds(250,20,250,30);
        mainPanel.add(labelTittle);

        JLabel labelCod = new JLabel();
        labelCod.setText("Código");
        labelCod.setBounds(70,100,250,30);
        mainPanel.add(labelCod);

        JLabel labelRam = new JLabel();
        labelRam.setText("Memoria");
        labelRam.setBounds(70,150,250,30);
        mainPanel.add(labelRam);

        JLabel labelMarca = new JLabel();
        labelMarca.setText("Marca");
        labelMarca.setBounds(70,200,250,30);
        mainPanel.add(labelMarca);
    }

    private void txtFields (){
        textCode = new JTextField();
        textCode.setBounds(150,100,250,30);
        mainPanel.add(textCode);

        textMemoria = new JTextField();
        textMemoria.setBounds(150,150,250,30);
        mainPanel.add(textMemoria);

        textMarca = new JTextField();
        textMarca.setBounds(150,200,250,30);
        mainPanel.add(textMarca);
    }

    private void txtArea(){
        textArea= new JTextArea();
        textArea.setBounds(450, 20, 300, 370);
        mainPanel.add(textArea);
    }

    public String getCodText() {
        return codText;
    }

    public void setCodText(String codText) {
        this.codText = codText;
    }

//Buttons

    private void buttonInsert(){
        JButton btInsert = new JButton("Crear");
        btInsert.setBounds(100,400,150,40);
        mainPanel.add(btInsert);


        ActionListener Scan = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String c = "", m = "", mr = "";
                c = textCode.getText();
                m = textMemoria.getText();
                mr = textMarca.getText();
                if (computo.getComputadorDAO().agregarComputador(c, m, mr, computo.getComputadores(), computo.getFile())) {
                    JOptionPane.showMessageDialog(rootPane,"Computador Guardado Cod " + textCode.getText());
                    textCode.setText(null);
                    textMemoria.setText(null);
                    textMarca.setText(null);
                } else {
                    JOptionPane.showMessageDialog(rootPane,"NO se guardo el registro");
                }
            }
        };
        btInsert.addActionListener(Scan);
    }

    private void buttonEdit(){
        JButton btEdit = new JButton("Editar");
        btEdit.setBounds(400,400,150,40);
        mainPanel.add(btEdit);


        ActionListener Scan = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String c = "", m = "", mr = "";
                c = textCode.getText();
                Computador rta = computo.getComputadorDAO().buscarComputador(c, computo.getComputadores());

                if (rta != null) {
                    m = textMemoria.getText();
                    mr = textMarca.getText();
                    computo.getComputadorDAO().eliminarComputador(c, computo.getComputadores(), computo.getFile());
                    computo.getComputadorDAO().agregarComputador(c, m, mr, computo.getComputadores(), computo.getFile());
                    JOptionPane.showMessageDialog(rootPane,"Se modifico el computador");
                    textCode.setText(null);
                    textMemoria.setText(null);
                    textMarca.setText(null);
                } else {
                    JOptionPane.showMessageDialog(rootPane,"No se encuentra el registro a modificar");
                }
            }
        };
        btEdit.addActionListener(Scan);
    }

    private void buttonDelete(){
        JButton btDelete = new JButton("Borrar");
        btDelete.setBounds(550, 400, 150,40);
        mainPanel.add(btDelete);


        ActionListener Scan = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String c = "";
                c = textCode.getText();
                if (computo.getComputadorDAO().eliminarComputador(c, computo.getComputadores(), computo.getFile())) {
                    JOptionPane.showMessageDialog(rootPane,"Computador Borrado: " + textCode.getText());
                    textCode.setText(null);
                    textMemoria.setText(null);
                    textMarca.setText(null);
                } else {
                    JOptionPane.showMessageDialog(rootPane,"Computador No borado: "+ textCode.getText());
                }
            }
        };
        btDelete.addActionListener(Scan);
    }

    private void buttonSearch() {
        JButton btSearch = new JButton("MOSTRAR");
        btSearch.setBounds(250, 400, 150, 40);
        mainPanel.add(btSearch);

        JLabel labelSearch = new JLabel();
        labelSearch.setBounds(170,2250,300,30);
        mainPanel.add(labelSearch);

        ActionListener Scan = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText(computo.getComputadorDAO().verComputadores(computo.getFile()));
                mainPanel.add(textArea);
            }
        };
        btSearch.addActionListener(Scan);
    }


}
