import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;

//bibliotecas del proyecto, tambien se puede hacer con import javax.swing.*;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.WindowConstants;
import javax.swing.border.Border;


//clase para redondear los botones
class RoundedBorder implements Border {
    private int radius;
    RoundedBorder(int radius) {
        this.radius = radius;
    }
    public Insets getBorderInsets(Component c) {
        return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
    }
    public boolean isBorderOpaque() {
        return true;
    }
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.drawRoundRect(x, y, width-1, height-1, radius, radius);
    }
}

//clase principal
public class FrmEstadistica extends JFrame{

    //aqui se crean los componentes
    JTextField txtDato;
    JList lstMuestra;
    JTextField txtEstadistica;
    JComboBox cmbEstadistica;

    public FrmEstadistica (){

        //Opciones de ventana
        setSize (500,300);
        setTitle("Estadistica");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        
        //aqui se crean los campos de texto
        txtDato=new JTextField();
        txtDato.setBounds(110, 10, 100, 25);
        getContentPane().add(txtDato);
        
        txtEstadistica=new JTextField();
        txtEstadistica.setBounds(270, 210, 130, 25);
        getContentPane().add(txtEstadistica);

        //aqui se crean los textos
        JLabel lblDato=new JLabel("Dato");
        lblDato.setBounds(45, 10, 100, 25);
        getContentPane().add(lblDato);
        
        JLabel lblDatos=new JLabel("Muestra");
        lblDatos.setBounds(312, 10, 210, 25);
        getContentPane().add(lblDatos);

        //aqui se crean los botones
        JButton btnAgregar=new JButton("Agregar");
        btnAgregar.setBorder(new RoundedBorder(10));
        btnAgregar.setBounds(110, 50, 100, 25);
        getContentPane().add(btnAgregar);

        JButton btnQuitar=new JButton("Quitar");
        btnQuitar.setBorder(new RoundedBorder(10));
        btnQuitar.setBounds(115, 80, 90, 25);
        getContentPane().add(btnQuitar);
        
        JButton btnEstadistica=new JButton("Calcular");
        btnEstadistica.setBorder(new RoundedBorder(10));
        btnEstadistica.setBounds(100, 170, 110, 25);
        getContentPane().add(btnEstadistica);

        //aqui se crean los listados
        lstMuestra=new JList();
        JScrollPane spMuestra=new JScrollPane(lstMuestra);
        spMuestra.setBounds(285, 40, 100, 150);
        getContentPane().add(spMuestra);

        //aqui se crean los combobox
        cmbEstadistica=new JComboBox();
        String[] opciones=new String[]{"Sumatoria", "Promedio", "Desviacion", "Maximo", "Minimo", "Moda"}; 
        DefaultComboBoxModel mdlEstadistica=new DefaultComboBoxModel(opciones);
        cmbEstadistica.setModel(mdlEstadistica);
        cmbEstadistica.setBounds(80, 210, 160, 25);
        getContentPane().add(cmbEstadistica);

        //aqui se crean los eventos
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

        btnEstadistica.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                calcularEstadistica();
            }
        });
    }

    private double[] muestra=new double[1000];
    private int totalDatos = -1;

    //aqui se crean los metodos
    private void agregarDato(){
        try{
        double dato=Double.parseDouble(txtDato.getText());
        totalDatos++;
        muestra[totalDatos] = dato;
        txtDato.setText("");
        mostrarMuestra();
        }

        catch(Exception ex){
            JOptionPane.showMessageDialog(null, "debe especificar un valor numerico");
            txtDato.setText("");
        }
    }

    private void mostrarMuestra(){
        String[] strmuestra=new String[totalDatos+1];

        lstMuestra.removeAll();
        for(int i=0; i<=totalDatos;i++){
           strmuestra[i]=(String.valueOf(muestra[i]));
        }
        lstMuestra.setListData(strmuestra);

    }

    private void quitarDato(){
        int posicion=lstMuestra.getSelectedIndex();

        if (posicion >= 0){
            for (int i=posicion;i<totalDatos;i++){
                muestra[i]=muestra[i+1];
            }
            totalDatos--;
            mostrarMuestra();
        }else{
            JOptionPane.showMessageDialog(null, "debe seleccionar una posicion");
        }
    }

    private double sumatoria(){
        double suma=0;
        for (int i = 0; i<= totalDatos; i++){
            suma += muestra[i];
        }
        return suma;
    }

    private double promedio(){
        double promedioCalculado = 0;
        if(totalDatos >= 0){
            promedioCalculado = sumatoria() / (totalDatos + 1);
        }
        return promedioCalculado;
    }
    
    private double desviacion(){
        double desviacionCalculada = 0;
        if(totalDatos >= 0){
            double promedio = promedio();
            double sumatoria = 0;
            for(int i = 0; i <= totalDatos; i++){
                sumatoria += (muestra[i] - promedio) * (muestra[i] - promedio);
            }
            double varianza = sumatoria / (totalDatos + 1);
            desviacionCalculada = raizCuadrada(varianza);
        }
        return desviacionCalculada;
    }
    private double raizCuadrada(double numero) {
        if (numero == 0) return 0;
        double estimacion = numero;
        double precision = 1e-10;
        while (Math.abs(estimacion * estimacion - numero) > precision) {
            estimacion = (estimacion + numero / estimacion) / 2;
        }
        return estimacion;
    }

    private double maximo(){
        double maximo = 0;
        if(totalDatos >= 0){
            maximo = muestra[0];
            for(int i = 1; i <= totalDatos; i++){
                if(muestra[i] > maximo){
                    maximo = muestra[i];
                }
            }
        }
        return maximo;
    }

    private double minimo(){
        double minimo = 0;
        if(totalDatos >= 0){
            minimo = muestra[0];
            for(int i = 1; i <= totalDatos; i++){
                if(muestra[i] < minimo){
                    minimo = muestra[i];
                }
            }
        }
        return minimo;
    }

    private double moda(){
        double moda = 0;
        if(totalDatos >= 0){
            int[] frecuencias = new int[totalDatos + 1];
            for(int i = 0; i <= totalDatos; i++){
                for(int j = 0; j <= totalDatos; j++){
                    if(muestra[i] == muestra[j]){
                        frecuencias[i]++;
                    }
                }
            }
            int maximoFrecuencia = 0;
            for(int i = 0; i <= totalDatos; i++){
                if(frecuencias[i] > maximoFrecuencia){
                    moda = muestra[i];
                    maximoFrecuencia = frecuencias[i];
                }
            }
        }
        return moda;
    }

    private void calcularEstadistica(){
        switch (cmbEstadistica.getSelectedIndex()) {
            case 0:
                txtEstadistica.setText(String.valueOf(sumatoria()));
                break;
            
            case 1:
                txtEstadistica.setText(String.valueOf(promedio()));
                break;

            case 2:
                txtEstadistica.setText(String.valueOf(desviacion()));
                break;
            
            case 3:
                txtEstadistica.setText(String.valueOf(maximo()));
                break;
            
            case 4:
                txtEstadistica.setText(String.valueOf(minimo()));
                break;

            case 5:
                txtEstadistica.setText(String.valueOf(moda()));
                break;
        
            default:
                break;
        }
    }
}