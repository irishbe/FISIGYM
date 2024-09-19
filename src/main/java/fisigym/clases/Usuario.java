package fisigym.clases;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Usuario {
    
    private String dni;
    private String nombre;
    private String apellido;
    private String correo;
    private String contrasenia;
    
    private final Scanner scanner = new Scanner(System.in);
    
    public Usuario(){};
    
    @Override
    public String toString() {
        return dni + "><" + nombre + "><" + apellido + "><" + correo + "><" + contrasenia;
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
        System.out.println("\n------------------- REGISTRAR CUENTA -------------------");
        System.out.print("DNI:  "); dni = scanner.nextLine();
        System.out.print("Nombres:  "); nombre = scanner.nextLine();
        System.out.print("Apellidos:  "); apellido = scanner.nextLine();
        System.out.print("Correo: "); correo  = scanner.nextLine();
        System.out.print("Contraseña: "); contrasenia  = scanner.nextLine();
        System.out.println("---------------------------------------------------------");
        
        this.almacenarUsuario();
        System.out.println("Usuario registrado con exito");
    }
    
    private boolean verificarUsuario(String correoIngresado, String contraseniaIngresada) {
        try (BufferedReader archivoUsuarios = new BufferedReader(new FileReader("usuarios.txt"))) {
            String linea;

            while ((linea = archivoUsuarios.readLine()) != null) {
                String[] datos = linea.split("><");

                // datos[3] es el correo, datos[4] es la contraseña
                String correoRegistrado = datos[3];
                String contraseniaRegistrada = datos[4];

                if (correoIngresado.equals(correoRegistrado) && contraseniaIngresada.equals(contraseniaRegistrada)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("Ocurrió un error al iniciar sesión.\n" + e.getMessage() );
        }
        
        return false;
    }
    
    public void iniciarSesion() {
        String correoIngresado;
        String contraseniaIngresada;
        
        System.out.println("\n-------------------- INICIAR SESIÓN --------------------");
        System.out.print("Correo:  "); correoIngresado = scanner.nextLine();
        System.out.print("Contraseña:  "); contraseniaIngresada = scanner.nextLine();
        System.out.println("---------------------------------------------------------");
        
        if (verificarUsuario(correoIngresado, contraseniaIngresada)) {
            System.out.println("Inicio de sesión exitoso.");
        } else {
            System.out.println("Correo o contraseña incorrectos.");
        }
    }
}
