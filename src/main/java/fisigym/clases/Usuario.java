package fisigym.clases;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Usuario {
    private Membresia membresia = new Membresia();
    final Scanner scanner = new Scanner(System.in);
    
    private String nombreUsuario;
    private String correo;
    private String dni;
    private String nombre;
    private String apellido;
    private String contrasenia;
    
    public Usuario(){};
    
    @Override
    public String toString() {
        return nombreUsuario + "><" + correo + "><" + contrasenia + "><" +  dni + "><" + nombre + "><" + apellido;
    }
    
    public void iniciarSesion() {
        var servicios = new Servicios();
        var utilidades = new Utilidades();
        
        String identificadorIngresado;
        String contraseniaIngresada;
        
        System.out.println("\n-------------------- INICIAR SESIÓN --------------------");
        System.out.print("Correo o usuario:  "); identificadorIngresado = scanner.nextLine();
        System.out.print("Contraseña:  "); contraseniaIngresada = scanner.nextLine();
        System.out.println("---------------------------------------------------------");
        
        if (verificarUsuario(identificadorIngresado, contraseniaIngresada)) {
            System.out.println("Inicio de sesión exitoso.");
            utilidades.pausa();
            servicios.menuServicios();
        
        } else {
            System.out.println("Correo/usuario o contraseña incorrectos.");
            utilidades.pausa();
        }
    }
    
    public void registrarUsuario() {
        System.out.println("\n------------------- REGISTRAR CUENTA -------------------");
        System.out.print("Nombre de usuario:  "); nombreUsuario = scanner.nextLine();
        System.out.print("Correo: "); correo  = scanner.nextLine();
        System.out.print("Contraseña: "); contrasenia  = scanner.nextLine();
        System.out.print("DNI:  "); dni = scanner.nextLine();
        System.out.print("Nombres:  "); nombre = scanner.nextLine();
        System.out.print("Apellidos:  "); apellido = scanner.nextLine();
        System.out.println("---------------------------------------------------------");
        
        membresia.registrarMembresia(dni);
        
        this.almacenarUsuario();
    }
    
    private void almacenarUsuario() {
        var utilidades = new Utilidades();
        
        try (BufferedWriter archivoUsuarios = new BufferedWriter(new FileWriter("usuarios.txt", true))) {
            archivoUsuarios.write(this.toString());
            archivoUsuarios.newLine();
            
            utilidades.limpiarPantalla();
            System.out.println("\n¡Usuario registrado con exito!");
            utilidades.pausa();
            
        } catch (IOException e) {
            System.out.println("Ocurrió un error al registrar el usuario.\n" + e.getMessage());
        }
    }
    
    private boolean verificarUsuario(String identificadorIngresado, String contraseniaIngresada) {
        try ( BufferedReader archivoUsuarios = new BufferedReader( new FileReader("usuarios.txt") ) ) {
            String linea;

            while ((linea = archivoUsuarios.readLine()) != null) {
                String[] datos = linea.split("><");

                // datos[0] es el nombre de usuario, datos[1] es el correo, datos[2] es la contraseña
                String nombreUsuarioRegistrado = datos[0];
                String correoRegistrado = datos[1];
                String contraseniaRegistrada = datos[2];

                if (identificadorIngresado.equals(nombreUsuarioRegistrado) && contraseniaIngresada.equals(contraseniaRegistrada)) {
                    return true;
                }
                
                if (identificadorIngresado.equals(correoRegistrado) && contraseniaIngresada.equals(contraseniaRegistrada)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("Ocurrió un error al iniciar sesión.\n" + e.getMessage() );
        }
        
        return false;
    }

    public Membresia getMembresia() {
        return membresia;
    }

    public void setMembresia(Membresia membresia) {
        this.membresia = membresia;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
}
