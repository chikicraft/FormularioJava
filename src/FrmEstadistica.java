
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.print.DocFlavor.STRING;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class FrmEstadistica extends JFrame{

    JTextField txtDato;
    JList lstMuestra;

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

        JTextField txtDesviacion=new JTextField();
        txtDesviacion.setBounds(270, 210, 130, 25);
        getContentPane().add(txtDesviacion);

        JButton btnAgregar=new JButton("Agregar");
        btnAgregar.setBounds(110, 50, 100, 25);
        getContentPane().add(btnAgregar);

        JButton btnQuitar=new JButton("Quitar");
        btnQuitar.setBounds(120, 80, 90, 25);
        getContentPane().add(btnQuitar);

        JButton btnDesviacion=new JButton("Desviación");
        btnDesviacion.setBounds(140, 210, 110, 25);
        getContentPane().add(btnDesviacion);

        lstMuestra=new JList();
        lstMuestra.setBounds(285, 40, 100, 150);
        getContentPane().add(lstMuestra);

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
