package fisigym.clases;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Membresia {
    private final Scanner scanner = new Scanner(System.in);
    
    public void planDiamante(){
        System.out.println("\n------------------- NUESTROS PLANES DE MEMBRESÍA -------------------");    
        System.out.println("""
                           \n,------.  ,--.                                    ,--.         
                           |  .-.  \\ `--' ,--,--.,--,--,--. ,--,--.,--,--, ,-'  '-. ,---. 
                           |  |  \\  :,--.' ,-.  ||        |' ,-.  ||      \\'-.  .-'| .-. :
                           |  '--'  /|  |\\ '-'  ||  |  |  |\\ '-'  ||  ||  |  |  |  \\   --.
                           `-------' `--' `--`--'`--`--`--' `--`--'`--''--'  `--'   `----'""");
        System.out.println("\n-\tAcceso a todas las sedes");
        System.out.println("-\tAcceso al área de spa");
        System.out.println("-\tAcceso a todas las clases");
        System.out.println("-\tAcceso a invitados");
        System.out.println("-\tPrecio: s/89.99");
    }
    
    public void planZafiro(){
        System.out.println("\n------------------- NUESTROS PLANES DE MEMBRESÍA -------------------");
        System.out.println("""
                           \n,-------.         ,---.,--.               
                           `--.   /  ,--,--./  .-'`--',--.--. ,---.  
                             /   /  ' ,-.  ||  `-,,--.|  .--'| .-. | 
                            /   `--.\\ '-'  ||  .-'|  ||  |   ' '-' ' 
                           `-------' `--`--'`--'  `--'`--'    `---'""");
        System.out.println("\n-\tAcceso a una sede");
        System.out.println("-\tAcceso al spa con descuento");
        System.out.println("-\tAcceso a todas las clases");
        System.out.println("-\tPrecio: s/69.99");
    }
    
    public void planEsmeralda(){
        System.out.println("\n------------------- NUESTROS PLANES DE MEMBRESÍA -------------------"); 
        System.out.println("""
                           \n,------.                                       ,--.   ,--.         
                           |  .---' ,---. ,--,--,--. ,---. ,--.--. ,--,--.|  | ,-|  | ,--,--. 
                           |  `--, (  .-' |        || .-. :|  .--'' ,-.  ||  |' .-. |' ,-.  | 
                           |  `---..-'  `)|  |  |  |\\   --.|  |   \\ '-'  ||  |\\ `-' |\\ '-'  | 
                           `------'`----' `--`--`--' `----'`--'    `--`--'`--' `---'  `--`--'""");
        System.out.println("\n-\tAcceso a una sede");
        System.out.println("-\tAcceso a todas las clases");
        System.out.println("-\tPrecio: s/59.99");
    }
    
    public void listaPlanes() {
        System.out.println("\n------------------- NUESTROS PLANES DE MEMBRESÍA -------------------");    
        System.out.println("\n\tDIAMANTE: ");
        System.out.println("-\tAcceso a todas las sedes");
        System.out.println("-\tAcceso al area de spa");
        System.out.println("-\tAcceso a todas las clases");
        System.out.println("-\tAcceso a invitados");
        System.out.println("-\tPrecio: s/89.99");
        
        System.out.println("\n\tZAFIRO: ");
        System.out.println("-\tAcceso a una sede");
        System.out.println("-\tAcceso al spa con descuento");
        System.out.println("-\tAcceso a todas las clases");
        System.out.println("-\tPrecio: s/69.99");

        System.out.println("\n\tESMERALDA: ");
        System.out.println("-\tAcceso a una sede");
        System.out.println("-\tAcceso a todas las clases");
        System.out.println("-\tPrecio: s/59.99");
        
        System.out.println("\n\t¡INSCRÍBETE YA!");
    }
    
    public void mostrarMeses(){
        System.out.println("\n------------------- NUESTROS DESCUENTOS POR MES -------------------");    
        System.out.println("\n\t1 MES: ");
        System.out.println("-\tSin descuento");
 
        System.out.println("\n\t2 MESES: ");
        System.out.println("-\t10% descuento");
        
        System.out.println("\n\t3 MESES: ");
        System.out.println("-\t15% descuento");
        
        System.out.println("\n\t6 MESES: ");
        System.out.println("-\t30% descuento");
        
        System.out.println("\n\t12 MESES: ");
        System.out.println("-\t45% descuento");

        System.out.println("\n\t¡INSCRÍBETE YA!");
    }
    
    public void registrarMembresia() {
        var utilidades = new Utilidades();
        
        int opcion = -1;
        
        utilidades.limpiarPantalla();
        planDiamante();
        utilidades.pausa();
        
        utilidades.limpiarPantalla();
        planZafiro();
        utilidades.pausa();
        
        utilidades.limpiarPantalla();
        planEsmeralda();
        utilidades.pausa();
        
        do {
            utilidades.limpiarPantalla();
            listaPlanes();
            
            try {
                System.out.print("\nSeleccione el tipo de membresia (1. Diamante, 2. Zafiro, 3. Esmeralda): ");
                opcion = scanner.nextInt();

                switch (opcion){
                    case 1 -> {
                        System.out.println("Usted ha escogido el plan DIAMANTE");
                    }
                    case 2 -> {
                        System.out.println("Usted ha escogido el plan ZAFIRO");
                    }
                    case 3 -> {
                        System.out.println("Usted ha escogido el plan ESMERALDA");
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
        } while (opcion != 1 && opcion != 2 && opcion !=3);
    }
    
    public void registrarMes() {
        var utilidades = new Utilidades();
        
        int opcion = -1;
        
        do {
            utilidades.limpiarPantalla();
            mostrarMeses();
            
            try {
                System.out.print("\nSeleccione el número de meses que desea inscribirse): ");
                opcion = scanner.nextInt();

                switch (opcion){
                    case 1 -> {
                        System.out.println("Usted ha escogido 1 MES");
                    }
                    case 2 -> {
                        System.out.println("Usted ha escogido 2 MESES");
                    }
                    case 3 -> {
                        System.out.println("Usted ha escogido 3 MESES");
                    }
                    case 6 -> {
                        System.out.println("Usted ha escogido 6 MESES");
                    }
                    case 12 -> {
                        System.out.println("Usted ha escogido 12 MESES");
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
        } while (opcion != 1 && opcion != 2 && opcion != 3 && opcion !=6 && opcion !=12);
    }
    
    public void registrarPlanUsuario() {
        var utilidades = new Utilidades();
        
        do {
            registrarMembresia();
        } while(utilidades.confirmacion() == false);
        
        do {
            registrarMes();
        } while(utilidades.confirmacion() == false);
    }
}
