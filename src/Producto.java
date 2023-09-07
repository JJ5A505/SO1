import javax.swing.*;
import java.io.Serializable;

class Producto implements Serializable {
    String claveProducto;
    double precio;
    String marca;
    String nombre;
    int cantidad;

    public Producto() {
        this.claveProducto = "";
        this.precio = 0.0;
        this.marca = "";
        this.nombre = "";
        this.cantidad = 0;
    }

    public void agregarProducto() {

        this.claveProducto = JOptionPane.showInputDialog("Ingrese la clave del producto");
        this.nombre = JOptionPane.showInputDialog("Ingrese el nombre del producto:");
        this.marca = JOptionPane.showInputDialog("Ingrese la marca del producto");
        this.precio = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el precio del producto:"));
        this.cantidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de producto:"));
    }
}

