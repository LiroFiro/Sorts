import java.io.*;
import java.util.*;

public class AlgoritmosOrdenamiento {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Ordenar datos");
            System.out.println("2. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();

            if (opcion == 1) {
                System.out.println("¿Qué algoritmo de ordenamiento desea utilizar?");
                System.out.println("1. Gnome Sort");
                System.out.println("2. Merge Sort");
                System.out.println("3. Quick Sort");
                System.out.println("4. Radix Sort");
                System.out.println("5. Selection Sort");
                System.out.println("6. Shell Sort");
                System.out.println("7. Heap Sort");
                System.out.print("Seleccione un algoritmo de ordenamiento: ");
                int algoritmoSeleccionado = scanner.nextInt();

                System.out.print("¿Cuántos números aleatorios desea ordenar?: ");
                int cantidadNumeros = scanner.nextInt();

                System.out.print("¿Cómo desea ordenar los datos? (ascendente/descendente): ");
                String orden = scanner.next().toLowerCase();

                boolean ordenDescendente = orden.equals("descendente");

                // Generar números aleatorios y guardarlos en un archivo CSV
                String rutaArchivo = "C:\\Users\\lirof\\OneDrive\\Escritorio\\HTW\\numeros_aleatorios.csv";
                generarYGuardarNumerosAleatorios(rutaArchivo, cantidadNumeros, 10000);

                // Leer los datos del archivo y guardarlos en un arreglo
                int[] datos = leerDatosDesdeArchivo(rutaArchivo, cantidadNumeros);

                switch (algoritmoSeleccionado) {
                    case 1:
                        ordenarYGuardar(datos.clone(), "Gnome Sort", ordenDescendente);
                        break;
                    case 2:
                        ordenarYGuardar(datos.clone(), "Merge Sort", ordenDescendente);
                        break;
                    case 3:
                        ordenarYGuardar(datos.clone(), "Quick Sort", ordenDescendente);
                        break;
                    case 4:
                        ordenarYGuardar(datos.clone(), "Radix Sort", ordenDescendente);
                        break;
                    case 5:
                        ordenarYGuardar(datos.clone(), "Selection Sort", ordenDescendente);
                        break;
                    case 6:
                        ordenarYGuardar(datos.clone(), "Shell Sort", ordenDescendente);
                        break;
                    case 7:
                        ordenarYGuardar(datos.clone(), "Heap Sort", ordenDescendente);
                        break;
                    default:
                        System.out.println("Opción no válida.");
                        break;
                }
            } else if (opcion == 2) {
                System.out.println("Saliendo del programa...");
                break;
            } else {
                System.out.println("Opción no válida.");
            }
        }
    }

