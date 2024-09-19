package fisigym.clases;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        var usuario = new Usuario();
        int opcion;
        
        String logo = """
               ,------.,--. ,---.  ,--.        ,----. ,--.   ,--.,--.   ,--. 
               |  .---'|  |'   .-' |  |,-----.'  .-./  \\  `.'  / |   `.'   | 
               |  `--, |  |`.  `-. |  |'-----'|  | .---.'.    /  |  |'.'|  | 
               |  |`   |  |.-'    ||  |       '  '--'  |  |  |   |  |   |  | 
               `--'    `--'`-----' `--'        `------'   `--'   `--'   `--' 
               """;
        
        System.out.println(logo);
        System.out.println("Tu entrenamiento, a un clic de distancia\n");
        System.out.println("1.\tIniciar sesión");
        System.out.println("2.\tRegistrarse");
        System.out.println("\n0.\tSalir del programa");
        
        do{
            
            System.out.print("\nDigite su opción: ");
            opcion = scanner.nextInt();
            
            switch (opcion){
                case 0 -> System.out.println("Saliendo del programa");
                case 1 -> usuario.iniciarSesion();
                case 2 -> usuario.registrarUsuario();
                default -> System.out.println("Opción inválida");

            }
        } while(opcion!=0);
        
        scanner.close();
    }
}
