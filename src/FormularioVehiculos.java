import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class FormularioVehiculos {
    private JPanel rootPanel;
    private JTextField brand_in;
    private JTextField model_in;
    private JTextField year_in;
    private JTextField cilindraje_in;
    private JLabel main_label;
    private JLabel brand_label;
    private JLabel model_label;
    private JLabel year_label;
    private JLabel cilindraje_label;
    private JButton cargarButton;
    private JButton guardarButton;
    private JButton anteriorButton;
    private JButton siguienteButton;

    //lista para almacenar listaVehiculos
    ArrayList<Vehiculo> listaVehiculos = new ArrayList<>();

    //archivo definido
    String filePath = "datosVehiculos.dat";

    //variables para el registro del indice actual del vehiculo mostrado
    private int indiceActual = 0;
    private int totalVehiculos = 0;

    public FormularioVehiculos() {
        cargarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try(
                        ObjectInputStream objectIn = new ObjectInputStream(new FileInputStream(filePath))
                ){

                    listaVehiculos = (ArrayList<Vehiculo>) objectIn.readObject();

                    totalVehiculos = listaVehiculos.size();
                    indiceActual = 0;

                    for(Vehiculo vehiculo:listaVehiculos){
                        System.out.println(vehiculo.toString());
                    }

                    JOptionPane.showMessageDialog(null, "Datos cargados correctamente.", "Cargar Datos",
                            JOptionPane.INFORMATION_MESSAGE);

                } catch (IOException | ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "Error al cargar los datos.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String marca = brand_in.getText();
                String modelo = model_in.getText();
                String anio = year_in.getText();
                String cilindraje = cilindraje_in.getText();

                Vehiculo vehiculo = new Vehiculo(marca,modelo,anio,cilindraje);

                listaVehiculos.add(vehiculo);

                guardarDatos();

                clearFields();
            }
        });
        anteriorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(indiceActual>0){
                    indiceActual--;
                    mostrarDatosVehiculo();
                }
            }
        });
        siguienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(indiceActual < totalVehiculos-1){
                    indiceActual++;
                    mostrarDatosVehiculo();
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("FormularioVehiculos");
        frame.setContentPane(new FormularioVehiculos().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(400,250);
        frame.setResizable(false);
    }

    private void guardarDatos() {
        try (ObjectOutputStream objectOut = new ObjectOutputStream(new FileOutputStream(filePath))) {
            objectOut.writeObject(listaVehiculos);
            JOptionPane.showMessageDialog(null, "Datos guardados correctamente.", "Guardar Datos",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error al guardar los datos.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields(){
        brand_in.setText("");
        model_in.setText("");
        year_in.setText("");
        cilindraje_in.setText("");
    }

    private void mostrarDatosVehiculo() {
        Vehiculo vehiculoActual = listaVehiculos.get(indiceActual);
        brand_in.setText(vehiculoActual.getMarca());
        model_in.setText(vehiculoActual.getModelo());
        year_in.setText(vehiculoActual.getAnio());
        cilindraje_in.setText(vehiculoActual.getCilindraje());
    }
}