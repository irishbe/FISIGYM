package fisigym.clases;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        var usuario = new Usuario();
        var utilidades = new Utilidades();
        
        int opcion = -1;
        
        String logo = """
               ,------.,--. ,---.  ,--.        ,----. ,--.   ,--.,--.   ,--. 
               |  .---'|  |'   .-' |  |,-----.'  .-./  \\  `.'  / |   `.'   | 
               |  `--, |  |`.  `-. |  |'-----'|  | .---.'.    /  |  |'.'|  | 
               |  |`   |  |.-'    ||  |       '  '--'  |  |  |   |  |   |  | 
               `--'    `--'`-----' `--'        `------'   `--'   `--'   `--' 
               """;
        
        do{
            utilidades.limpiarPantalla();
            System.out.println(logo);
            System.out.println("Tu entrenamiento, a un clic de distancia\n");
            System.out.println("1.\tIniciar sesión");
            System.out.println("2.\tRegistrarse");
            System.out.println("\n0.\tSalir del programa");

            try {
                System.out.print("\nDigite su opción: ");
                opcion = scanner.nextInt();
                
                switch (opcion){
                    case 0 -> {
                        utilidades.limpiarPantalla();
                        System.out.println("Saliendo del programa...");
                    }
                    case 1 -> {
                        utilidades.limpiarPantalla();
                        usuario.iniciarSesion();
                    }
                    case 2 -> {
                        utilidades.limpiarPantalla();
                        usuario.registrarUsuario();
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
        
        scanner.close();
    }
}
