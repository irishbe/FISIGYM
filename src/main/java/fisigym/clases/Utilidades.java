package fisigym.clases;

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
}