package fisigym.clases;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Usuario {
    private Membresia membresia = new Membresia();
    private String nombreUsuario;
    private String correo;
    private String dni;
    private String nombre;
    private String apellido;
    private String contrasenia;
    
    private final Scanner scanner = new Scanner(System.in);
    
    public Usuario(){};
    
    @Override
    public String toString() {
        return nombreUsuario + "><" + correo + "><" + contrasenia + "><" +  dni + "><" + nombre + "><" + apellido;
    }
    
    private void almacenarUsuario() {
        try (BufferedWriter archivoUsuarios = new BufferedWriter(new FileWriter("usuarios.txt", true))) {
            archivoUsuarios.write(this.toString());
            archivoUsuarios.newLine();
        } catch (IOException e) {
            System.out.println("Ocurrió un error al registrar el usuario.\n" + e.getMessage());
        }
    }

    public void registrarUsuario() {
        var utilidades = new Utilidades();
        
        System.out.println("\n------------------- REGISTRAR CUENTA -------------------");
        System.out.print("Nombre de usuario:  "); nombreUsuario = scanner.nextLine();
        System.out.print("Correo: "); correo  = scanner.nextLine();
        System.out.print("Contraseña: "); contrasenia  = scanner.nextLine();
        System.out.print("DNI:  "); dni = scanner.nextLine();
        System.out.print("Nombres:  "); nombre = scanner.nextLine();
        System.out.print("Apellidos:  "); apellido = scanner.nextLine();
        System.out.println("---------------------------------------------------------");
        
        membresia.registrarPlanUsuario();
        
        this.almacenarUsuario();
        
        utilidades.limpiarPantalla();
        System.out.println("\n¡Usuario registrado con exito!");
        utilidades.pausa();
    }
    
    private boolean verificarUsuario(String correoUsuarioIngresado, String contraseniaIngresada) {
        try (BufferedReader archivoUsuarios = new BufferedReader(new FileReader("usuarios.txt"))) {
            String linea;

            while ((linea = archivoUsuarios.readLine()) != null) {
                String[] datos = linea.split("><");

                // datos[0] es el nombre de usuario, datos[1] es el correo, datos[2] es la contraseña
                String nombreUsuarioRegistrado = datos[0];
                String correoRegistrado = datos[1];
                String contraseniaRegistrada = datos[2];

                if (correoUsuarioIngresado.equals(nombreUsuarioRegistrado) && contraseniaIngresada.equals(contraseniaRegistrada)) {
                    return true;
                }
                
                if (correoUsuarioIngresado.equals(correoRegistrado) && contraseniaIngresada.equals(contraseniaRegistrada)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("Ocurrió un error al iniciar sesión.\n" + e.getMessage() );
        }
        
        return false;
    }
    
    public void iniciarSesion() {
        String correoUsuarioIngresado;
        String contraseniaIngresada;
        
        System.out.println("\n-------------------- INICIAR SESIÓN --------------------");
        System.out.print("Correo o usuario:  "); correoUsuarioIngresado = scanner.nextLine();
        System.out.print("Contraseña:  "); contraseniaIngresada = scanner.nextLine();
        System.out.println("---------------------------------------------------------");
        
        if (verificarUsuario(correoUsuarioIngresado, contraseniaIngresada)) {
            System.out.println("Inicio de sesión exitoso.");
        } else {
            System.out.println("Correo o contraseña incorrectos.");
        }
    }
}
