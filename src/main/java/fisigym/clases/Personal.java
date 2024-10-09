package fisigym.clases;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;

public class Personal extends Usuario {
    private String rol;

    @Override
    public String toString() {
        return super.toString() + "><" + rol;
    }

    protected void almacenarPersonal() {
        var utilidades = new Utilidades();

        try (BufferedWriter archivoAdministradores = new BufferedWriter(new FileWriter("administradores.txt", true))) {
            archivoAdministradores.write(this.toString());
            archivoAdministradores.newLine();

            utilidades.limpiarPantalla();
            System.out.println("\n¡Administrador registrado con éxito!");
            utilidades.pausa();

        } catch (IOException e) {
            System.out.println("Ocurrió un error al registrar el administrador.\n" + e.getMessage());
        }
    }

    private String seleccionarRol() {
        var utilidades = new Utilidades();
        int opcion = -1;

        do {
            utilidades.limpiarPantalla();
            try {
                System.out.print("\nSeleccione el tipo de rol (1. Administrador, 2. Recepcionista): ");
                opcion = scanner.nextInt();

                switch (opcion) {
                    case 1 -> {
                        System.out.println("Usted ha escogido el rol ADMINISTRADOR");
                        return "ADMINISTRADOR";
                    }
                    case 2 -> {
                        System.out.println("Usted ha escogido el rol RECEPCIONISTA");
                        return "RECEPCIONISTA";
                    }
                    default -> {
                        System.out.println("Entrada no válida. Por favor, ingrese una de las opciones.");
                        utilidades.pausa();
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Por favor, ingrese una de las opciones.");
                utilidades.pausa();
                scanner.nextLine(); // Limpiar el buffer del scanner
            }
        } while (opcion != 1 && opcion != 2);

        return "";
    }

    public void registrarPersonal() {
        System.out.println("\n---------------- REGISTRAR ADMINISTRADOR ----------------");
        super.registrarUsuario();  // Llama al método de Usuario
        setRol(seleccionarRol());
        this.almacenarPersonal();
    }

    public void menuAdministrador() {
        var utilidades = new Utilidades();
        var ciudad = new Ciudad();
        var distrito = new Distrito();

        int opcion = -1;

        do {
            utilidades.limpiarPantalla();
            System.out.println("\n------------------- MENÚ DE ADMINISTRADOR -------------------");
            System.out.println("\n1.\tGestionar sedes (ciudad)");
            System.out.println("2.\tGestionar sedes (distritos)");
            System.out.println("3.\tGestionar gimnasios");
            System.out.println("4.\tGestionar profesores");
            System.out.println("5.\tGestionar entrenadores");
            System.out.println("6.\tGestionar clases");
            System.out.println("7.\tRegistrar membresía");
            System.out.println("\n0.\tCerrar sesión");

            try {
                System.out.print("\nDigite su opción: ");
                opcion = scanner.nextInt();

                switch (opcion) {
                    case 0 -> {
                        utilidades.limpiarPantalla();
                        System.out.println("Cerrando sesión...");
                        utilidades.pausa();
                    }
                    case 1 -> {
                        utilidades.limpiarPantalla();
                        ciudad.menuCiudad();
                        utilidades.pausa();
                    }
                    case 2 -> {
                        utilidades.limpiarPantalla();
                        distrito.menuDistrito();
                        utilidades.pausa();
                    }
                    case 3 -> {
                        utilidades.limpiarPantalla();
                        System.out.println("Gestionando gimnasios...");
                        utilidades.pausa();
                    }
                    case 4 -> {
                        utilidades.limpiarPantalla();
                        System.out.println("Gestionando profesores...");
                        utilidades.pausa();
                    }
                    case 5 -> {
                        utilidades.limpiarPantalla();
                        System.out.println("Gestionando entrenadores...");
                        utilidades.pausa();
                    }
                    case 6 -> {
                        utilidades.limpiarPantalla();
                        System.out.println("Gestionando clases...");
                        utilidades.pausa();
                    }
                    case 7 -> {
                        utilidades.limpiarPantalla();
                        System.out.println("Registrando membresía...");
                        utilidades.pausa();
                    }
                    default -> {
                        System.out.println("Entrada no válida. Por favor, ingrese una de las opciones.");
                        utilidades.pausa();
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Por favor, ingrese una de las opciones.");
                utilidades.pausa();
                scanner.nextLine(); // Limpiar el buffer del scanner
            }
        } while (opcion != 0);
    }
    
    public void menuRecepcionista() {
        var utilidades = new Utilidades();
        int opcion = -1;

        do {
            utilidades.limpiarPantalla();
            System.out.println("\n------------------- MENÚ DE RECEPCIONISTA -------------------");
            System.out.println("\n1.\tVerificar usuarios");
            System.out.println("2.\tVerificar clases");
            System.out.println("\n0.\tCerrar sesión");

            try {
                System.out.print("\nDigite su opción: ");
                opcion = scanner.nextInt();

                switch (opcion) {
                    case 0 -> {
                        utilidades.limpiarPantalla();
                        System.out.println("Cerrando sesión...");
                        utilidades.pausa();
                    }
                    case 1 -> {
                        utilidades.limpiarPantalla();
                        System.out.println("Verificando usuarios...");
                        utilidades.pausa();
                    }
                    case 2 -> {
                        utilidades.limpiarPantalla();
                        System.out.println("Verificando clases...");
                        utilidades.pausa();
                    }
                    default -> {
                        System.out.println("Entrada no válida. Por favor, ingrese una de las opciones.");
                        utilidades.pausa();
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Por favor, ingrese una de las opciones.");
                utilidades.pausa();
                scanner.nextLine(); // Limpiar el buffer del scanner
            }
        } while (opcion != 0);
    }

    private boolean verificarPersonal(String identificadorIngresado, String contraseniaIngresada) {
        try ( BufferedReader archivoUsuarios = new BufferedReader( new FileReader("administradores.txt") ) ) {
            String linea;

            while ((linea = archivoUsuarios.readLine()) != null) {
                String[] datos = linea.split("><");

                // datos[0] es el nombre de usuario, datos[1] es el correo, datos[2] es la contraseña
                String nombreUsuarioRegistrado = datos[0];
                String correoRegistrado = datos[1];
                String contraseniaRegistrada = datos[2];

                if (identificadorIngresado.equals(nombreUsuarioRegistrado) && contraseniaIngresada.equals(contraseniaRegistrada)) {
                    rol = datos[6];
                    return true;
                }
                
                if (identificadorIngresado.equals(correoRegistrado) && contraseniaIngresada.equals(contraseniaRegistrada)) {
                    rol = datos[6];
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("Ocurrió un error al iniciar sesión.\n" + e.getMessage() );
        }
        
        return false;
    }
    
    
    @Override
    public void iniciarSesion() {
        var utilidades = new Utilidades();

        System.out.println("\n------------------ INICIAR SESIÓN (ADMIN) ------------------");
        System.out.print("Correo o usuario:  ");
        String identificadorIngresado = scanner.nextLine();
        System.out.print("Contraseña:  ");
        String contraseniaIngresada = scanner.nextLine();
        System.out.println("------------------------------------------------------------");

        if ( verificarPersonal(identificadorIngresado, contraseniaIngresada) ) {
            System.out.println("Inicio de sesión de administrador exitoso.");
            System.out.println("Usted tiene el rol de: " + rol);
            utilidades.pausa();
            if ("ADMINISTRADOR".equals(rol)){
                menuAdministrador();
            } else {
                menuRecepcionista();
            }
        } else {
            System.out.println("Correo/usuario o contraseña incorrectos.");
            utilidades.pausa();
        }
    }

    // Getter y Setter para rol
    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
