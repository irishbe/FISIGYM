package fisigym.clases;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.time.LocalDate;

public class Membresia {
    private final Scanner scanner = new Scanner(System.in);
    
    private String dniUsuario;
    private boolean estadoMembresia;
    private String tipoMembresia;
    private int duracionMembresia;
    private LocalDate fechaInicial;
    private LocalDate fechaFinal;
    private float precioMembresia;
    private float descuento;
    private float precioFinal;
    
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
    
    public String seleccionarMembresia() {
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
                        return "DIAMANTE";
                    }
                    case 2 -> {
                        System.out.println("Usted ha escogido el plan ZAFIRO");
                        return "ZAFIRO";
                    }
                    case 3 -> {
                        System.out.println("Usted ha escogido el plan ESMERALDA");
                        return "ESMERALDA";
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
        
        return "";
    }
    
    public int seleccionarMeses() {
        var utilidades = new Utilidades();
        
        int mesEscogido = -1;
        
        do {
            utilidades.limpiarPantalla();
            mostrarMeses();
            
            try {
                System.out.print("\nSeleccione el número de meses que desea inscribirse): ");
                mesEscogido = scanner.nextInt();

                switch (mesEscogido){
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
        } while (mesEscogido != 1 && mesEscogido != 2 && mesEscogido != 3 && mesEscogido !=6 && mesEscogido !=12);
        
        return mesEscogido;
    }
    
    //------------------------------------------------------------------------------------------------------------------------------------------
    @Override
    public String toString() {
        return dniUsuario + "><" + estadoMembresia + "><" + tipoMembresia + "><" +  duracionMembresia + "><" + fechaInicial + "><" + fechaFinal + "><" + precioMembresia + "><" + descuento + "><" + precioFinal;
    }
    
    private void almacenarMembresia() {
        var utilidades = new Utilidades();
        
        try (BufferedWriter archivoUsuarios = new BufferedWriter(new FileWriter("membresias.txt", true))) {
            archivoUsuarios.write(this.toString());
            archivoUsuarios.newLine();
            
            utilidades.limpiarPantalla();
            System.out.println("\n¡Membresía registrada con exito!");
            utilidades.pausa();
            
        } catch (IOException e) {
            System.out.println("Ocurrió un error al registrar la membresia.\n" + e.getMessage());
        }
    }
    
    public void registrarMembresia(String dni) {
        var utilidades = new Utilidades();
        
        dniUsuario = dni;
        
        do {
            tipoMembresia = seleccionarMembresia();
        } while(utilidades.confirmacion() == false);
        
        do {
            duracionMembresia = seleccionarMeses();
        } while(utilidades.confirmacion() == false);
        
        switch (tipoMembresia){
            case "DIAMANTE" -> precioMembresia = 89.99F;
            case "ZAFIRO" -> precioMembresia = 69.99F;
            case "ESMERALDA" -> precioMembresia = 59.99F;
        }
        
        switch (duracionMembresia){
            case 1 -> descuento = 0.00F;
            case 2 -> descuento = 0.10F;
            case 3 -> descuento = 0.15F;
            case 6 -> descuento = 0.30F;
            case 12 -> descuento = 0.45F;
        }
        
        precioFinal = (1 - descuento)*(precioMembresia * duracionMembresia);
        estadoMembresia = true;
        fechaInicial = LocalDate.now();
        fechaFinal = fechaInicial.plusMonths(duracionMembresia);
        
        this.almacenarMembresia();
    }
}
