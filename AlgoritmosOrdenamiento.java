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

    // Implementación de Gnome Sort
    private static void gnomeSort(int[] datos) {
        for (int i = 1; i < datos.length;) {
            if (i == 0 || datos[i - 1] <= datos[i]) {
                i++;
            } else {
                int temp = datos[i];
                datos[i] = datos[i - 1];
                datos[--i] = temp;
            }
        }
    }

    // Implementación de Merge Sort
    private static void mergeSort(int[] datos) {
        if (datos.length > 1) {
            int mid = datos.length / 2;
            int[] left = Arrays.copyOfRange(datos, 0, mid);
            int[] right = Arrays.copyOfRange(datos, mid, datos.length);

            mergeSort(left);
            mergeSort(right);

            int i = 0, j = 0, k = 0;
            while (i < left.length && j < right.length) {
                if (left[i] < right[j]) {
                    datos[k++] = left[i++];
                } else {
                    datos[k++] = right[j++];
                }
            }
            while (i < left.length) {
                datos[k++] = left[i++];
            }
            while (j < right.length) {
                datos[k++] = right[j++];
            }
        }
    }

    // Implementación de Quick Sort
    private static void quickSort(int[] datos) {
        quickSort(datos, 0, datos.length - 1);
    }

    private static void quickSort(int[] datos, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(datos, low, high);
            quickSort(datos, low, pivotIndex - 1);
            quickSort(datos, pivotIndex + 1, high);
        }
    }

    private static int partition(int[] datos, int low, int high) {
        int pivot = datos[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (datos[j] < pivot) {
                i++;
                int temp = datos[i];
                datos[i] = datos[j];
                datos[j] = temp;
            }
        }
        int temp = datos[i + 1];
        datos[i + 1] = datos[high];
        datos[high] = temp;
        return i + 1;
    }

    // Implementación de Radix Sort
    private static void radixSort(int[] datos) {
        int max = getMax(datos);
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countSort(datos, exp);
        }
    }

    private static int getMax(int[] datos) {
        int max = datos[0];
        for (int i = 1; i < datos.length; i++) {
            if (datos[i] > max) {
                max = datos[i];
            }
        }
        return max;
    }

    private static void countSort(int[] datos, int exp) {
        int n = datos.length;
        int[] output = new int[n];
        int[] count = new int[10];
        Arrays.fill(count, 0);

        for (int i = 0; i < n; i++) {
            count[(datos[i] / exp) % 10]++;
        }

        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            output[count[(datos[i] / exp) % 10] - 1] = datos[i];
            count[(datos[i] / exp) % 10]--;
        }

        for (int i = 0; i < n; i++) {
            datos[i] = output[i];
        }
    }

    // Implementación de Selection Sort
    private static void selectionSort(int[] datos) {
        for (int i = 0; i < datos.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < datos.length; j++) {
                if (datos[j] < datos[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = datos[minIndex];
            datos[minIndex] = datos[i];
            datos[i] = temp;
        }
    }

    // Implementación de Shell Sort
    private static void shellSort(int[] datos) {
        int n = datos.length;
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i += 1) {
                int temp = datos[i];
                int j;
                for (j = i; j >= gap && datos[j - gap] > temp; j -= gap) {
                    datos[j] = datos[j - gap];
                }
                datos[j] = temp;
            }
        }
    }

    // Implementación de Heap Sort
    private static void heapSort(int[] datos) {
        int n = datos.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(datos, n, i);
        }
        for (int i = n - 1; i > 0; i--) {
            int temp = datos[0];
            datos[0] = datos[i];
            datos[i] = temp;
            heapify(datos, i, 0);
        }
    }

    private static void heapify(int[] datos, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        if (left < n && datos[left] > datos[largest]) {
            largest = left;
        }
        if (right < n && datos[right] > datos[largest]) {
            largest = right;
        }
        if (largest != i) {
            int swap = datos[i];
            datos[i] = datos[largest];
            datos[largest] = swap;
            heapify(datos, n, largest);
        }
    }
}
