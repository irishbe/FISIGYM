package fisigym.clases;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Utilidades {
    private final Scanner scanner = new Scanner(System.in);
    
    public void limpiarPantalla() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    public void pausa() {
        System.out.println("\nPresione Enter para continuar...");
        scanner.nextLine();  // Espera a que el usuario presione Enter
    }
    
    public boolean confirmacion() {
        int opcion = -1;
        
        do{
            try {
                System.out.print("¿Desea confirmar su selección? (0. Cancelar / 1. Aceptar): ");
                opcion = scanner.nextInt();
                
                switch (opcion){
                    case 0 -> {
                        System.out.println("Selección cancelada. Volviendo al menú de selección.");
                        pausa();
                        return false;
                    }
                    case 1 -> {
                        System.out.println("¡Selección confirmada! Gracias por su elección.");
                        pausa();
                        return true;
                    }
                    default -> {
                        System.out.println("Entrada no válida. Por favor, ingrese una de las opciones.");
                        pausa();
                        scanner.nextLine();
                    }
                }                
                
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Por favor, ingrese una de las opciones.");
                pausa();
                scanner.nextLine();
            }
            
        } while(opcion!=0 && opcion!=1);
        
        return false;
    }

    public void verificarArchivo(String nombreArchivo) {
        File archivo = new File(nombreArchivo);
    
        if (!archivo.exists()) { 
            try {
                archivo.createNewFile();
            } catch (IOException e) {
                System.out.println("Ocurrió un error al crear el archivo " + nombreArchivo);
                pausa();
            }
        }
    }

    public boolean verificarArchivoConContenido(String nombreArchivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {

            if (br.readLine() != null) {
                return true;
            }
        } catch (IOException e) {
            System.out.println("Ocurrió un error al intentar leer el archivo: " + nombreArchivo);
            pausa();
        }
        return false;
    }

    private boolean verificarId(int id, String archivo) {
        try (BufferedReader archivoReader = new BufferedReader(new FileReader(archivo))) {
            String line;

            while ((line = archivoReader.readLine()) != null) {
                String[] datos = line.split("><");
                int idGuardado = Integer.parseInt(datos[0]);

                if (idGuardado == id) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("Ocurrió un error al leer el archivo.");
        } catch (NumberFormatException e) {
            System.out.println("Error al convertir el ID. Asegúrate de que el formato es correcto.");
        }

        return false;
    }

    public int solicitarId(String archivo, String mensaje) {
        int idIngresado = -1;
        boolean existe = false;

        while (!existe && idIngresado != 0) {
            System.out.print("\n" + mensaje);
            
            try {
                idIngresado = scanner.nextInt();
                scanner.nextLine();

                existe = verificarId(idIngresado, archivo);

                if (idIngresado == 0){
                    limpiarPantalla();
                    System.out.println("Regresando al menú anterior...");
                    pausa();
                    return idIngresado;
                }
                
                if (!existe && idIngresado != 0) {
                    System.out.println("El ID " + idIngresado + " no existe. Intente nuevamente.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Por favor, ingrese un número válido.");
                scanner.nextLine();
            }
        }

        return idIngresado;
    }
}