import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.print.DocFlavor.STRING;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class FrmEstadistica extends JFrame {

    JTextField txtDato;
    JList lstMuestra;
    JTextField txtEstadistica;
    JComboBox cmbEstadistica;

    public FrmEstadistica() {

        setSize(500, 300);
        setTitle("Formularion");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        txtDato = new JTextField();
        txtDato.setBounds(110, 10, 100, 25);
        getContentPane().add(txtDato);
        
        txtEstadistica= new JTextField();
        txtEstadistica.setBounds(270, 210, 130, 25);
        getContentPane().add(txtEstadistica);

        JLabel lblDato = new JLabel("Dato");
        lblDato.setBounds(45, 10, 100, 25);
        getContentPane().add(lblDato);

        JLabel lblDatos = new JLabel("Muestra");
        lblDatos.setBounds(312, 10, 210, 25);
        getContentPane().add(lblDatos);

        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.setBounds(110, 50, 100, 25);
        getContentPane().add(btnAgregar);

        JButton btnQuitar = new JButton("Quitar");
        btnQuitar.setBounds(120, 80, 90, 25);
        getContentPane().add(btnQuitar);
        
        JButton btnEstadistica=new JButton("Calcular");
        btnEstadistica.setBounds(100, 170, 110, 25);
        getContentPane().add(btnEstadistica);

        lstMuestra = new JList();
        JScrollPane spMuestra=new JScrollPane(lstMuestra);
        spMuestra.setBounds(285, 40, 100, 150);
        getContentPane().add(spMuestra);


        cmbEstadistica=new JComboBox();
        String[] opciones=new String[]{"Sumatoria", "Promedio", "Desviación", "Máximo", "Minimo", "Moda"};
        DefaultComboBoxModel mdlEstadistica=new DefaultComboBoxModel(opciones);
        cmbEstadistica.setModel(mdlEstadistica);
        cmbEstadistica.setBounds(80, 210, 160, 25);
        getContentPane().add(cmbEstadistica);

        // eventos de la GUI
        btnAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                agregarDato();
            }
        });

        btnQuitar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                quitarDato();
            }
        });
    }

    // declarar el arreglo que almacenará los datos de la muestra
    private double[] muestra = new double[1000];
    private int totalDatos = -1;

    private void agregarDato() {
        try {
            double dato = Double.parseDouble(txtDato.getText());
            totalDatos++;
            muestra[totalDatos] = dato;
            txtDato.setText("");
            mostrarMuestra();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Debe ingresar un número");
            txtDato.setText("");
        }
        double dato = Double.parseDouble(txtDato.getText());
        totalDatos++;
        muestra[totalDatos] = dato;
        txtDato.setText("");
        mostrarMuestra();
    }

    private void mostrarMuestra() {
        String[] strMuestra = new String[totalDatos + 1];
        for (int i = 0; i <= totalDatos; i++) {
            strMuestra[i] = String.valueOf(muestra[i]);
        }
        lstMuestra.setListData(strMuestra);
    }

    private void quitarDato() {
        int posicion=lstMuestra.getSelectedIndex();
        if(posicion>=0){
            for(int i=posicion;i<totalDatos;i++){
                muestra[i]=muestra[i+1];
            }
            totalDatos--;
            mostrarMuestra();
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un dato");
        }
    } 
}