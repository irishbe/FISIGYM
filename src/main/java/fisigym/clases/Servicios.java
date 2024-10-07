package fisigym.clases;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Servicios {
    private final Scanner scanner = new Scanner(System.in);
    
    public void menuServicios() {
        var utilidades = new Utilidades();
        
        int opcion = -1;
        
        do{
            utilidades.limpiarPantalla();
            System.out.println("\n------------------- MENÚ DE SERVICIOS -------------------");
            System.out.println("\n1.\tBeneficios (masajes, piscina)");
            System.out.println("2.\tReservas de clases");
            System.out.println("3.\tVer disponibilidad de clases");
            System.out.println("4.\tVisualizar mis datos de membresía");
            System.out.println("5.\tRegistrar membresía");
            System.out.println("\n0.\tCerrar sesión");

            try {
                System.out.print("\nDigite su opción: ");
                opcion = scanner.nextInt();
                
                switch (opcion){
                    case 0 -> {
                        utilidades.limpiarPantalla();
                        System.out.println("Cerrando sesion...");
                        utilidades.pausa();
                    }
                    case 1 -> {
                        utilidades.limpiarPantalla();
                        System.out.println("Accediendo a beneficios...");
                        utilidades.pausa();
                    }
                    case 2 -> {
                        utilidades.limpiarPantalla();
                        System.out.println("Accediendo a reserva de clases...");
                        utilidades.pausa();
                    }
                    case 3 -> {
                        utilidades.limpiarPantalla();
                        System.out.println("Mostrando disponibilidad de clases...");
                        utilidades.pausa();
                    }
                    case 4 -> {
                        utilidades.limpiarPantalla();
                        System.out.println("Mostrando mis datos de membresía...");
                        utilidades.pausa();
                    }
                    case 5 -> {
                        utilidades.limpiarPantalla();
                        System.out.println("Accediendo a beneficios...");
                        utilidades.pausa();
                    }
                    default -> {
                        System.out.println("Entrada no válida. Por favor, ingrese una de las opciones.");
                        utilidades.pausa();
                        scanner.nextLine();
                    }
                }
                
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Por favor, ingrese una de las opciones.");
                utilidades.pausa();
                scanner.nextLine();
            }
            
        } while(opcion!=0);
    }
}
