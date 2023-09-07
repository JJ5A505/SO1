import javax.swing.*;
import java.io.*;


public class Metodos {
    File archivo = new File("C:\\Users\\win\\OneDrive\\Escritorio\\archivos\\archivositemasoperativos\\productos.txt");

    static int mostrarMenu() {
        String[] opciones = {"Alta", "Baja", "Modificar", "Mostrar", "Salir"};

        return JOptionPane.showOptionDialog(null, "Selecciona una opción", "Gestión de Productos",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, opciones, opciones[0]) + 1;

    }

    public void alta() {
        try {
            FileWriter escribir = new FileWriter(archivo, true);
            BufferedWriter bufferEscribir = new BufferedWriter(escribir);

            String claveProducto;
            boolean claveExistente;

            do {
                claveProducto = JOptionPane.showInputDialog("Ingrese la clave del producto");
                claveExistente = false;  // Inicialmente, suponemos que la clave no existe

                FileReader leer = new FileReader(archivo);
                BufferedReader bufferLeer = new BufferedReader(leer);
                String linea;

                while ((linea = bufferLeer.readLine()) != null) {
                    if (linea.trim().startsWith("Clave: " + claveProducto)) {
                        claveExistente = true;
                        break;  // La clave existe, no es necesario seguir buscando
                    }
                }

                bufferLeer.close();

                if (claveExistente) {
                    JOptionPane.showMessageDialog(null, "La clave ya existe en el archivo. Ingrese una clave diferente.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } while (claveExistente);

            // Escribir la clave en el archivo
            escribir.write("Clave: " + claveProducto + "\n");


            String nombre = JOptionPane.showInputDialog("Ingrese el nombre del producto");
            escribir.write("Nombre: " + nombre + "\n");

            String marca = JOptionPane.showInputDialog("Ingrese la marca del producto");
            escribir.write("Marca: " + marca + "\n");

            Double precio = null;
            while (precio == null || precio <= 0) {
                try {
                    String precioStr = JOptionPane.showInputDialog("Ingrese el precio del producto:");
                    precio = Double.parseDouble(precioStr);
                    if (precio <= 0) {
                        JOptionPane.showMessageDialog(null, "Ingrese un precio válido (mayor que cero).", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Ingrese un valor numérico válido para el precio.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            escribir.write("Precio: " + precio + "\n");

            int cantidad = 0;
            while (cantidad <= 0) {
                try {
                    String cantidadStr = JOptionPane.showInputDialog("Ingrese la cantidad de producto:");
                    cantidad = Integer.parseInt(cantidadStr);
                    if (cantidad <= 0) {
                        JOptionPane.showMessageDialog(null, "Ingrese una cantidad válida (mayor que cero).", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Ingrese un valor numérico válido para la cantidad.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            escribir.write("Cantidad: " + cantidad + "\n");

            escribir.write("-----------------------------------\n");
            escribir.close(); // ¡No olvides cerrar el archivo después de escribir!
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void baja() {
        try {
            String claveABuscar = JOptionPane.showInputDialog("Ingrese la clave del producto que desea dar de baja:");
            File archivoTemporal = new File("C:\\Users\\win\\OneDrive\\Escritorio\\archivos\\archivositemasoperativos\\productostem.txt");
            BufferedReader lector = new BufferedReader(new FileReader(archivo));
            BufferedWriter escritor = new BufferedWriter(new FileWriter(archivoTemporal));

            String linea;
            boolean encontrado = false;

            while ((linea = lector.readLine()) != null) {
                if (linea.contains("Clave: " + claveABuscar)) {
                    encontrado = true;
                    // Saltar las líneas del producto que deseamos dar de baja
                    for (int i = 0; i < 5; i++) {
                        lector.readLine();
                    }
                } else {
                    escritor.write(linea + "\n");
                }
            }

            lector.close();
            escritor.close();

            if (encontrado) {
                archivo.delete();
                archivoTemporal.renameTo(archivo);
                JOptionPane.showMessageDialog(null, "Producto dado de baja exitosamente.");
            } else {
                JOptionPane.showMessageDialog(null, "Producto no encontrado.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void modificar() {
        try {
            String claveAModificar = JOptionPane.showInputDialog("Ingrese la clave del producto que desea modificar:");
            File archivoTemporal = new File("C:\\Users\\win\\OneDrive\\Escritorio\\archivos\\archivositemasoperativos\\productostem.txt");
            BufferedReader lector = new BufferedReader(new FileReader(archivo));
            BufferedWriter escritor = new BufferedWriter(new FileWriter(archivoTemporal));

            String linea;
            boolean encontrado = false;

            while ((linea = lector.readLine()) != null) {
                if (linea.contains("Clave: " + claveAModificar)) {
                    encontrado = true;
                    // Saltar las líneas del producto original
                    for (int i = 0; i < 5; i++) {
                        lector.readLine();
                    }
                    // Modificar todos los campos del producto y escribir los nuevos datos
                    escritor.write("Clave: " + claveAModificar + "\n");

                    String nuevoNombre = JOptionPane.showInputDialog("Ingrese el nuevo nombre del producto:");
                    escritor.write("Nombre: " + nuevoNombre + "\n");

                    String nuevaMarca = JOptionPane.showInputDialog("Ingrese la nueva marca del producto:");
                    escritor.write("Marca: " + nuevaMarca + "\n");

                    Double nuevoPrecio = null;
                    while (nuevoPrecio == null || nuevoPrecio <= 0) {
                        try {
                            String precioStr = JOptionPane.showInputDialog("Ingrese el nuevo precio del producto:");
                            nuevoPrecio = Double.parseDouble(precioStr);
                            if (nuevoPrecio <= 0) {
                                JOptionPane.showMessageDialog(null, "Ingrese un precio válido (mayor que cero).", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Ingrese un valor numérico válido para el precio.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    escritor.write("Precio: " + nuevoPrecio + "\n");

                    int nuevaCantidad = 0;
                    while (nuevaCantidad <= 0) {
                        try {
                            String cantidadStr = JOptionPane.showInputDialog("Ingrese la nueva cantidad de producto:");
                            nuevaCantidad = Integer.parseInt(cantidadStr);
                            if (nuevaCantidad <= 0) {
                                JOptionPane.showMessageDialog(null, "Ingrese una cantidad válida (mayor que cero).", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Ingrese un valor numérico válido para la cantidad.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    escritor.write("Cantidad: " + nuevaCantidad + "\n");
                    escritor.write("-----------------------------------\n");
                } else {
                    escritor.write(linea + "\n");
                }
            }

            lector.close();
            escritor.close();

            if (encontrado) {
                archivo.delete();
                archivoTemporal.renameTo(archivo);
                JOptionPane.showMessageDialog(null, "Producto modificado exitosamente.");
            } else {
                JOptionPane.showMessageDialog(null, "Producto no encontrado.");
                archivoTemporal.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void mostrar() {
        try {
            FileReader leer = new FileReader(archivo);
            BufferedReader bufferLeer = new BufferedReader(leer);

            String linea;
            StringBuilder contenido = new StringBuilder();

            while ((linea = bufferLeer.readLine()) != null) {
                // Muestra el contenido de cada línea (puedes personalizar cómo lo deseas mostrar)
                contenido.append(linea).append("\n");
            }

            // Cierra el archivo
            bufferLeer.close();

            // Muestra el contenido en una ventana de diálogo (JOptionPane)
            JOptionPane.showMessageDialog(null, contenido.toString(), "Contenido del archivo", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}