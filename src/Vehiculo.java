import java.io.Serializable;

public class Vehiculo implements Serializable {
    //atributos
    private static final long serialVersionUID =1L;
    private String marca;
    private String modelo;
    private String anio;
    private String cilindraje;

    //constructor
    public Vehiculo(String marca, String modelo, String anio, String cilindraje) {
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.cilindraje = cilindraje;
    }

    //setters y getters
    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getCilindraje() {
        return cilindraje;
    }

    public void setCilindraje(String cilindraje) {
        this.cilindraje = cilindraje;
    }

    @Override
    public String toString(){
        return "\nMarca: " +marca+ "\nModelo: " +modelo+ "\nAño: " +anio+ "\nCilindraje: " +cilindraje;
    }
}









