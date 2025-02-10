
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

public class FrmEstadistica extends JFrame{

    JTextField txtDato;
    JList lstMuestra;
    JTextField txtEstadistica;

    public FrmEstadistica(){

        setSize(600,300);
        setTitle("Formularion");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        JLabel lblDato=new JLabel("Dato");
        lblDato.setBounds(45, 10, 100, 25);
        getContentPane().add(lblDato);

        JLabel lblMuestra=new JLabel("Muestra");
        lblMuestra.setBounds(312, 10, 210, 25);
        getContentPane().add(lblMuestra);

        txtDato=new JTextField();
        txtDato.setBounds(110, 10, 100, 25);
        getContentPane().add(txtDato);

        txtEstadistica=new JTextField();
        txtEstadistica.setBounds(270, 210, 130, 25);
        getContentPane().add(txtEstadistica);

        JButton btnAgregar=new JButton("Agregar");
        btnAgregar.setBounds(110, 50, 100, 25);
        getContentPane().add(btnAgregar);

        JButton btnQuitar=new JButton("Quitar");
        btnQuitar.setBounds(120, 80, 90, 25);
        getContentPane().add(btnQuitar);

        JButton btnEstadistica=new JButton("Calcular");
        btnEstadistica.setBounds(100, 170, 110, 25);
        getContentPane().add(btnEstadistica);

        JComboBox cmbEstadistica=new JComboBox();
        String[] opciones=new String[]{"Sumatoria", "Promedio", "Desviacion Estandar","maximo","minimo","moda"};
        DefaultComboBoxModel mdlEstadistica=new DefaultComboBoxModel(opciones);
        cmbEstadistica.setModel(mdlEstadistica);
        cmbEstadistica.setBounds(80, 210, 160, 25);
        getContentPane().add(cmbEstadistica);

        lstMuestra=new JList();
        lstMuestra.setBounds(285, 40, 100, 150);
        getContentPane().add(lstMuestra);

        lstMuestra = new JList();
        JScrollPane spMuestra = new JScrollPane(lstMuestra);
        spMuestra.setBounds(285, 40, 100, 150);
        getContentPane().add(spMuestra);

        btnAgregar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                agregarDato();
            }
        });

        btnQuitar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                quitarDato();
            }
        });
        /*falata hacer la jlist, no se como hacer para que funcione,                                preguntar */

    }

    private double[] muestra=new double[1000];
    private int totalDatos = -1;

    private void agregarDato(){
        double dato=Double.parseDouble(txtDato.getText());
        totalDatos++;
        muestra[totalDatos]=dato;
        motrarMuestra();
    }

    private void motrarMuestra(){
        String[] strMuestra=new String[totalDatos + 1];
        for(int i=0;i<=totalDatos;i++){
            strMuestra[i] = String.valueOf(muestra[i]);
        }
        lstMuestra.setListData(strMuestra);
    }
    private void quitarDato(){
        JOptionPane.showMessageDialog(null, "Quitando dato");
    }
}
