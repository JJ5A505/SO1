import javax.swing.*;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        Metodos metodos = new Metodos();

        File archivo1= new File("C:\\Users\\win\\OneDrive\\Escritorio\\archivos\\archivositemasoperativos\\productos.txt");
        System.out.println("Existe: "+ archivo1.exists());
        System.out.println("leer: "+archivo1.canRead());
        System.out.println("escribir: "+archivo1.canWrite());

        while (true) {
            int opcion = Metodos.mostrarMenu();
            switch (opcion) {
                case 1:
                    metodos.alta();
                    break;
                case 2:
                    metodos.baja();
                    break;
                case 3:
                    metodos.modificar();
                    break;
                case 4:
                    metodos.mostrar();
                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida");
            }
        }
    }
}