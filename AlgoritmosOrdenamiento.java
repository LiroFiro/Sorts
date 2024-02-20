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

    // Método para generar números aleatorios y guardarlos en un archivo CSV
    private static void generarYGuardarNumerosAleatorios(String archivo, int cantidad, int rango) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            Random random = new Random();
            for (int i = 0; i < cantidad; i++) {
                writer.write(String.valueOf(random.nextInt(rango)));
                writer.newLine();
            }
            System.out.println("Datos aleatorios generados y guardados en el archivo: " + archivo);
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }

    // Método para leer los datos desde un archivo CSV y guardarlos en un arreglo
    private static int[] leerDatosDesdeArchivo(String archivo, int cantidad) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            List<Integer> lista = new ArrayList<>();
            String linea;
            int count = 0;
            while ((linea = br.readLine()) != null && count < cantidad) {
                lista.add(Integer.parseInt(linea));
                count++;
            }
            return lista.stream().mapToInt(Integer::intValue).toArray();
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
            return new int[0];
        }
    }

    // Método para guardar los datos en un archivo CSV
    private static void guardarDatosEnCSV(String archivo, int[] datos) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            for (int dato : datos) {
                writer.write(String.valueOf(dato));
                writer.newLine();
            }
            System.out.println("Datos ordenados guardados en el archivo: " + archivo);
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }

    // Método para revertir un arreglo
    private static void reverseArray(int[] arr) {
        int start = 0;
        int end = arr.length - 1;
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }

    // Método para ordenar los datos y guardarlos en un archivo CSV
    private static void ordenarYGuardar(int[] datos, String nombreAlgoritmo, boolean descendente) {
        switch (nombreAlgoritmo) {
            case "Gnome Sort":
                gnomeSort(datos);
                break;
            case "Merge Sort":
                mergeSort(datos);
                break;
            case "Quick Sort":
                quickSort(datos);
                break;
            case "Radix Sort":
                radixSort(datos);
                break;
            case "Selection Sort":
                selectionSort(datos);
                break;
            case "Shell Sort":
                shellSort(datos);
                break;
            case "Heap Sort":
                heapSort(datos);
                break;
            default:
                System.out.println("Algoritmo no válido.");
                return;
        }

        if (descendente) {
            reverseArray(datos);
        }

        String orden = descendente ? "descendente" : "ascendente";
        String archivo = "C:\\Users\\lirof\\OneDrive\\Escritorio\\HTW\\" + nombreAlgoritmo.toLowerCase().replace(" ", "_") + "_" + orden + ".csv";
        guardarDatosEnCSV(archivo, datos);
    }

