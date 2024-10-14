package fisigym.clases;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Gimnasio {
    private int idGimnasio;
    private int idCiudad;
    private int idDistrito;
    private String nombreGimnasio;
    private String direccion;
    private ServiciosGimnasio serviciosGimnasio;

    private final Scanner scanner = new Scanner(System.in);
    private final String archivoGimnasio = "gimnasios.txt";
    private final Utilidades utilidades = new Utilidades();
    
    // Constructor
    public Gimnasio(int idGimnasio, int idCiudad, int idDistrito, String nombreGimnasio, String direccion, ServiciosGimnasio serviciosGimnasio) {
        this.idGimnasio = idGimnasio;
        this.idCiudad = idCiudad;
        this.idDistrito = idDistrito;
        this.nombreGimnasio = nombreGimnasio;
        this.direccion = direccion;
        this.serviciosGimnasio = new ServiciosGimnasio();
    }

    public Gimnasio(){
    }

    // Método toString para formatear el guardado en archivo
    @Override
    public String toString() {
        return idGimnasio + "><" + idCiudad + "><" + idDistrito + "><" + nombreGimnasio + "><" + direccion + "><" + serviciosGimnasio.getPiscina() + "><"+
            serviciosGimnasio.getSpa() + "><" + serviciosGimnasio.getCochera() + "><" + serviciosGimnasio.getCafeteria() + "><" + serviciosGimnasio.getVestidores() + "><" + serviciosGimnasio.getWifi();
    }

    public void menuGimnasio(){
        utilidades.verificarArchivo(archivoGimnasio);

        int opcion = -1;

        do {
            utilidades.limpiarPantalla();
            System.out.println("\n------------------- MENÚ DE GIMNASIO -------------------");
            System.out.println("\n1.\tRegistrar gimnasio");
            System.out.println("2.\tListar gimnasios");
            System.out.println("3.\tActualizar gimnasio");
            System.out.println("4.\tEliminar gimnasio");
            System.out.println("5.\tListar Servicios");
            System.out.println("\n0.\tRegresar al menú anterior");

            try {
                System.out.print("\nDigite su opción: ");
                opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 0 -> {
                        utilidades.limpiarPantalla();
                        System.out.println("Regresando al menú anterior...");
                    }
                    case 1 -> {
                        utilidades.limpiarPantalla();
                        registrarGimnasio();
                    }
                    case 2 -> {
                        utilidades.limpiarPantalla();
                        listarGimnasios();
                        utilidades.pausa(); // Solo pausa en listar
                    }
                    case 3 -> {
                        utilidades.limpiarPantalla();
                        actualizarGimnasio();
                    }
                    case 4 -> {
                        utilidades.limpiarPantalla();
                        eliminarGimnasio();
                    }
                    case 5 -> {
                        utilidades.limpiarPantalla();
                        listarServicios();
                        utilidades.pausa(); // Solo pausa en listar
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
        } while (opcion != 0);
    }

    // Método para generar un ID único para gimnasios
    private int generarIdUnico() {
        List<Integer> idsExistentes = obtenerIdsExistentes();
        int nuevoId = 1;

        if (!idsExistentes.isEmpty()) {
            int idMaximo = idsExistentes.stream().max(Integer::compare).orElse(0);
            nuevoId = idMaximo + 1;

            while (idsExistentes.contains(nuevoId)) {
                nuevoId++;
            }
        }

        return nuevoId;
    }

    // Método para obtener todos los IDs de gimnasios almacenados en el archivo
    private List<Integer> obtenerIdsExistentes() {
        List<Integer> idsExistentes = new ArrayList<>();

        try (BufferedReader archivoGimnasios = new BufferedReader(new FileReader(archivoGimnasio))){
            String line;

            while ((line = archivoGimnasios.readLine()) != null){
                String[] datos = line.split("><");
                
                if (datos.length > 0 && !datos[0].isEmpty()){
                    int idGimnasioGuardado = Integer.parseInt(datos[0]);
                    
                    idsExistentes.add(idGimnasioGuardado);
                }
            }
        } catch (IOException e){
            System.out.println("Ocurrió un error al leer los IDs existentes.");
        }

        return idsExistentes;
    }

    public void registrarGimnasio(){
        Ciudad ciudad = new Ciudad();
        Distrito distrito = new Distrito();
        String confirmacionServicios;

        if (utilidades.verificarArchivoConContenido("ciudades.txt") == false) {
            System.out.println("Primero registre una ciudad en el menú de gestor de ciudades.");
            utilidades.pausa();
            return;
        }

        if (utilidades.verificarArchivoConContenido("distritos.txt") == false) {
            System.out.println("Primero registre una distrito en el menú de gestor de distritos.");
            utilidades.pausa();
            return;
        }
        
        // PEDIR EL ID DE UNA CIUDAD VÁLIDA
        ciudad.listarCiudades();
        System.out.println("\n------------------- REGISTRAR GIMNASIO -------------------");
        System.out.println("                 Presione 0 para regresar                 ");
        idCiudad = utilidades.solicitarId("ciudades.txt", "Ingrese el ID de la ciudad: ");

        if (idCiudad == 0){
            return;
        }
        
        // PEDIR EL ID DE UN DISTRITO VÁLIDO
        utilidades.limpiarPantalla();
        if (distrito.listarDistritosPorCiudad(idCiudad) == false){
            utilidades.pausa();
            return;
        }
        
        System.out.println("\n------------------- REGISTRAR GIMNASIO -------------------");
        System.out.println("                 Presione 0 para regresar                 ");
        idDistrito = utilidades.solicitarRelacionId("distritos.txt", idCiudad, "Ingrese el ID del distrito: ");
        //idDistrito = utilidades.solicitarId("distritos.txt", "Ingrese el ID del distrito: ");
        
        if (idDistrito == 0){
            return;
        }
        
        utilidades.limpiarPantalla();
        System.out.println("------------------- REGISTRAR GIMNASIO -------------------");
        System.out.print("Nombre del gimnasio: ");
        nombreGimnasio = scanner.nextLine().trim();

        if (nombreGimnasio.equals("0")){
            System.out.println("Regresando al menú anterior...");
            utilidades.pausa();
            return;
        }

        if (nombreGimnasio.isEmpty()){
            System.out.println("El nombre del gimnasio no puede estar vacío.");
            utilidades.pausa();
            return;
        }
        
        System.out.print("Dirección del gimnasio: ");
        direccion = scanner.nextLine().trim();

        if (direccion.equals("0")){
            System.out.println("Regresando al menú anterior...");
            utilidades.pausa();
            return;
        }

        if (direccion.isEmpty()){
            System.out.println("La dirección del gimnasio no puede estar vacía.");
            utilidades.pausa();
            return;
        }
        
        this.serviciosGimnasio = new ServiciosGimnasio();

        System.out.print("\n¿Desea agregar servicios al gimnasio? (0: NO) (1: SÍ): ");
        confirmacionServicios = scanner.nextLine();
                            
        if( confirmacionServicios.equals("1") ) {
            serviciosGimnasio.registrarServicios();
        } else {
            System.out.println("\nNo se registró ningún servicio en el gimnasio");
            utilidades.pausa();
        }
        
        idGimnasio = generarIdUnico();
        utilidades.limpiarPantalla();
        System.out.println("---------------------------------------------------------");
        System.out.println("ID del gimnasio: " + idGimnasio);
        System.out.println("Nombre: " + nombreGimnasio);
        System.out.println("Direccion: " + direccion);
        System.out.println("---------------------------------------------------------");
        this.guardarGimnasio();
    }

    private void guardarGimnasio() {
        try (BufferedWriter archivoGimnasios = new BufferedWriter(new FileWriter(archivoGimnasio, true))) {
            archivoGimnasios.write(this.toString());
            archivoGimnasios.newLine();
            System.out.println("\n¡Gimnasio registrado con éxito!");
            utilidades.pausa();
        } catch (IOException e) {
            System.out.println("\nOcurrió un error al registrar el gimnasio.");
            utilidades.pausa();
        }
    }
    
    public void listarGimnasios() {
        Ciudad ciudad = new Ciudad();
        Distrito distrito = new Distrito();

        System.out.println("\n------------------- LISTA DE GIMNASIOS -------------------");
        
        try(BufferedReader archivoGimnasios = new BufferedReader(new FileReader(archivoGimnasio))){
            String line;
            boolean hayGimnasios=false;

            // Encabezado de la tabla
            // %s: Representa una cadena, %d: representa un entero, - indica alineación a la izquierda
            System.out.printf("%-15s %-15s %-20s %-30s %-20s\n",
                        "ID Gimnasio", "Ciudad", "Distrito", "Nombres", "Dirección");
            
            while((line = archivoGimnasios.readLine()) !=null){
                String[] datos = line.split("><");

                //idCiudad = Integer.parseInt(datos[1]);
                //idDistrito = Integer.parseInt(datos[2]);
                String nombreCiudad = ciudad.obtenerNombreCiudad( Integer.parseInt(datos[1]) );
                String nombreDistrito = distrito.obtenerNombreDistrito( Integer.parseInt(datos[2]) );
                String direccionGimnasio = datos[4];
                nombreGimnasio = datos[3];

                // Limitar el tamaño de las cadenas si son muy largas
                nombreCiudad = (nombreCiudad.length() > 15) ? nombreCiudad.substring(0, 12) + "..." : nombreCiudad;
                nombreDistrito = (nombreDistrito.length() > 20) ? nombreDistrito.substring(0, 17) + "..." : nombreDistrito;
                nombreGimnasio = (nombreGimnasio.length() > 30) ? nombreGimnasio.substring(0, 27) + "..." : nombreGimnasio;
                direccionGimnasio = (direccionGimnasio.length() > 20) ? direccionGimnasio.substring(0, 17) + "..." : direccionGimnasio;

                System.out.printf("%-15s %-15s %-20s %-30s %-20s\n",
                            datos[0], nombreCiudad, nombreDistrito, nombreGimnasio, direccionGimnasio);
                
                hayGimnasios=true;
            }

            if (!hayGimnasios){
                System.out.println("No hay gimnasios registrados.");
                // utilidades.pausa(); no poner pausa en método listar
            }

        } catch (IOException e){
            System.out.println("Ocurrió un error al leer los gimnasios.");
            // utilidades.pausa(); No poner pausa en método listar
        }
    }

    public void listarServicios() {
        //Ciudad ciudad = new Ciudad();
        //Distrito distrito = new Distrito();

        System.out.println("\n------------------- LISTA DE SERVICIOS -------------------");
        
        try(BufferedReader archivoGimnasios = new BufferedReader(new FileReader(archivoGimnasio))){
            String line;
            boolean hayGimnasios=false;

            // Encabezado de la tabla
            System.out.printf("%-15s %-30s %-10s %-10s %-10s %-15s %-15s %-10s\n",
                        "ID Gimnasio",  "Nombre", "Piscina", "Spa", "Cochera", "Cafetería", "Vestidores", "WiFi");

            while((line = archivoGimnasios.readLine()) !=null){
                String[] datos = line.split("><");
                //idCiudad=Integer.parseInt(datos[1]);
                //idDistrito=Integer.parseInt(datos[2]);
                //String nombreCiudad = ciudad.obtenerNombreCiudad(idCiudad);
                //String nombreDistrito = distrito.obtenerNombreDistrito(idDistrito);
                nombreGimnasio = datos[3];

                // Limitar el tamaño de las cadenas si son muy largas
                nombreGimnasio = (nombreGimnasio.length() > 30) ? nombreGimnasio.substring(0, 27) + "..." : nombreGimnasio;

                System.out.printf("%-15s %-30s %-10s %-10s %-10s %-15s %-15s %-10s\n",
                            datos[0], nombreGimnasio, datos[5], datos[6], datos[7], datos[8], datos[9], datos[10]);
                hayGimnasios=true;
            }

            if (!hayGimnasios){
                System.out.println("No hay gimnasios registrados.");
                // utilidades.pausa(); No poner pausa en método listar
            }
        }catch (IOException e){
            System.out.println("Ocurrió un error al leer los gimnasios.");
            // utilidades.pausa(); No poner pausa en método listar
        }
    }

    public void actualizarGimnasio(){
        int idGimnasioIngresado;
        boolean gimnasioEncontrado=false;
        String nuevoNombreGimnasio;
        String nuevaDireccionGimnasio;
        List<String> gimnasios = new ArrayList<>();

        if (utilidades.verificarArchivoConContenido(archivoGimnasio) == false) {
            System.out.println("Primero registre una gimnasio en el menú.");
            utilidades.pausa();
            return;
        }

        listarGimnasios();
        System.out.println("\n------------------- ACTUALIZAR GIMNASIO -------------------");
        System.out.println("                  Presione 0 para regresar                 ");
        idGimnasioIngresado = utilidades.solicitarId(archivoGimnasio, "Ingrese el ID del gimnasio a actualizar: ");

        if (idGimnasioIngresado == 0){
            return;
        }
        
        try (BufferedReader archivoGimnasios = new BufferedReader(new FileReader(archivoGimnasio))) {
            String line;
            
            while ((line = archivoGimnasios.readLine()) != null) {
                String[] datos = line.split("><");
                int idGimnasioGuardado = Integer.parseInt(datos[0]);
                
                if (idGimnasioGuardado == idGimnasioIngresado) {
                    System.out.print("\nIngrese el nuevo nombre del gimnasio (presione enter para mantener el actual): ");
                    nuevoNombreGimnasio = scanner.nextLine();
                    
                    if (nuevoNombreGimnasio.isEmpty()) {
                        nuevoNombreGimnasio = datos[3]; // Mantener el nombre actual
                    }
                    
                    System.out.print("\nIngrese la nueva dirección del gimnasio (presione enter para mantener la actual): ");
                    nuevaDireccionGimnasio = scanner.nextLine();
                    
                    if (nuevaDireccionGimnasio.isEmpty()) {
                        if (datos.length > 4) {
                            nuevaDireccionGimnasio = datos[4];
                        } else {
                            nuevaDireccionGimnasio = ""; // Asignar vacío si no hay dirección guardada
                        }
                    }

                    String gimnasioActualizado = idGimnasioGuardado + "><" + datos[1] + "><" + datos[2] + "><" + nuevoNombreGimnasio + "><" + nuevaDireccionGimnasio + "><" + datos[5] + "><" + datos[6] + "><" + datos[7] + "><" + datos[8] +"><"+ datos[9] + "><" + datos[10] ;
                    gimnasios.add(gimnasioActualizado);
                    gimnasioEncontrado = true;
                } else {
                    gimnasios.add(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Ocurrió un error al leer los gimnasios.");
            utilidades.pausa();
            return;
        }

        if (gimnasioEncontrado) {
            try (BufferedWriter archivoGimnasios = new BufferedWriter(new FileWriter(archivoGimnasio))) {
                for (String gimnasio : gimnasios) {
                    archivoGimnasios.write(gimnasio);
                    archivoGimnasios.newLine();
                }

                System.out.println("\nEl gimnasio ha sido actualizado exitosamente.");
                utilidades.pausa();
            } catch (IOException e) {
                System.out.println("\nOcurrió un error al actualizar el gimnasio.");
                utilidades.pausa();
            }
        } else {
            System.out.println("\nNo se encontró un gimnasio con el ID proporcionado.");
            utilidades.pausa();
        }
    }
    
    public void eliminarGimnasio(){
        int idGimnasioIngresado;
        boolean gimnasioEncontrado = false;
        List<String> gimnasios = new ArrayList<>();
        
        if (utilidades.verificarArchivoConContenido(archivoGimnasio) == false) {
            System.out.println("Primero registre una gimnasio en el menú.");
            utilidades.pausa();

            return;
        }

        listarGimnasios();
        System.out.println("\n------------------- ELIMINAR GIMNASIO -------------------");
        System.out.println("                  Presione 0 para regresar                 ");
        idGimnasioIngresado = utilidades.solicitarId(archivoGimnasio, "Ingrese el ID del gimnasio a eliminar: ");

        if (idGimnasioIngresado == 0){
            return;
        }

        try (BufferedReader archivoGimnasios = new BufferedReader(new FileReader(archivoGimnasio))){
            String line;

            while((line = archivoGimnasios.readLine()) != null){
                String[] datos = line.split("><");
                int idGimnasioGuardado = Integer.parseInt(datos[0]);

                if (idGimnasioGuardado == idGimnasioIngresado){
                    gimnasioEncontrado = true;
                } else {
                    gimnasios.add(line);
                }
            }
        } catch (IOException e){
            System.out.println("Ocurrió un error al leer los gimnasios.");
            utilidades.pausa();
            
            return;
        }
        
        if (gimnasioEncontrado){
            try (BufferedWriter archivoGimnasios = new BufferedWriter(new FileWriter(archivoGimnasio))){
                for (String gimnasio : gimnasios){
                    archivoGimnasios.write(gimnasio);
                    archivoGimnasios.newLine();
                }

                System.out.println("\nEl gimnasio ha sido eliminado exitosamente.");
                utilidades.pausa();
            }catch (IOException e){
                System.out.println("\nOcurrió un error al eliminar el gimnasio.");
                utilidades.pausa();
            }
        }else {
            System.out.println("\nNo se encontró un gimnasio con el ID proporcionado.");
            utilidades.pausa();
        }
    }

    // Clase anidada para los servicios
    public class ServiciosGimnasio{
        private boolean piscina;
        private boolean spa;
        private boolean cochera;
        private boolean cafeteria;
        private boolean vestidores;
        private boolean wifi;

        public ServiciosGimnasio(boolean piscina, boolean spa, boolean cochera, boolean cafeteria, boolean vestidores, boolean wifi){
            this.piscina = piscina;
            this.spa = spa;
            this.cochera = cochera;
            this.cafeteria = cafeteria;
            this.vestidores = vestidores;
            this.wifi = wifi;
        }

        public ServiciosGimnasio(){
            this.piscina = false;
            this.spa = false;
            this.cochera = false;
            this.cafeteria = false;
            this.vestidores = false;
            this.wifi = false;
        }

        public void registrarServicios(){
            utilidades.verificarArchivo(archivoGimnasio);

            int opcion = -1;
            String respuestaServicio;

            do {
                utilidades.limpiarPantalla();
                System.out.println("\n------------------- ACTUALIZAR SERVICIOS -------------------");
                System.out.println("\n1.\tPiscina");
                System.out.println("2.\tSpa");
                System.out.println("3.\tCochera");
                System.out.println("4.\tCafetería");
                System.out.println("5.\tVestidores");
                System.out.println("6.\tWiFi");
                System.out.println("\n0.\tRegresar al menú anterior");
                
                try {
                    System.out.print("\nDigite su opción: ");
                    opcion = scanner.nextInt();
                    scanner.nextLine();

                    switch (opcion) {
                        case 0 -> {
                            utilidades.limpiarPantalla();
                            System.out.println("Regresando al menú anterior...");
                            utilidades.pausa();
                        }
                        case 1 -> {
                            utilidades.limpiarPantalla();
                            System.out.print("El gimnasio tiene piscina (0: NO) (1: SÍ): ");
                            respuestaServicio = scanner.nextLine();
                            
                            if( respuestaServicio.equals("1") ) {
                                System.out.println("Piscina: Sí");
                                setPiscina(true);
                            } else {
                                System.out.println("Piscina: No");
                                setPiscina(false);            
                            }

                            utilidades.pausa();
                        }
                        case 2 -> {
                            utilidades.limpiarPantalla();
                            System.out.print("El gimnasio tiene spa (0: NO) (1: SÍ): ");
                            respuestaServicio = scanner.nextLine();
                            
                            if( respuestaServicio.equals("1") ) {
                                System.out.println("Spa: Sí");
                                setSpa(true);
                            } else {
                                System.out.println("Spa: No");
                                setSpa(false);            
                            }

                            utilidades.pausa();
                        }
                        case 3 -> {
                            utilidades.limpiarPantalla();
                            System.out.print("El gimnasio tiene cochera (0: NO) (1: SÍ): ");
                            respuestaServicio = scanner.nextLine();
                            
                            if( respuestaServicio.equals("1") ) {
                                System.out.println("Cochera: Sí");
                                setCochera(true);
                            } else {
                                System.out.println("Cochera: No");
                                setCochera(false);            
                            }

                            utilidades.pausa();
                        }
                        case 4 -> {
                            utilidades.limpiarPantalla();
                            System.out.print("El gimnasio tiene cafetería (0: NO) (1: SÍ): ");
                            respuestaServicio = scanner.nextLine();
                            
                            if( respuestaServicio.equals("1") ) {
                                System.out.println("Cafetería: Sí");
                                setCafeteria(true);
                            } else {
                                System.out.println("Cafetería: No");
                                setCafeteria(false);            
                            }

                            utilidades.pausa();
                        }
                        case 5 -> {
                            utilidades.limpiarPantalla();
                            System.out.print("El gimnasio tiene vestidores (0: NO) (1: SÍ): ");
                            respuestaServicio = scanner.nextLine();
                            
                            if( respuestaServicio.equals("1") ) {
                                System.out.println("Vestidores: Sí");
                                setVestidores(true);
                            } else {
                                System.out.println("Vestidores: No");
                                setVestidores(false);            
                            }

                            utilidades.pausa();
                        }
                        case 6 -> {
                            utilidades.limpiarPantalla();
                            System.out.print("El gimnasio tiene WiFi (0: NO) (1: SÍ): ");
                            respuestaServicio = scanner.nextLine();
                            
                            if( respuestaServicio.equals("1") ) {
                                System.out.println("WiFi: Sí");
                                setWifi(true);
                            } else {
                                System.out.println("WiFi: No");
                                setWifi(false);            
                            }

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
            } while (opcion != 0);
        }

        public void setPiscina(boolean piscina) {
            this.piscina = piscina;
        }

        public boolean getPiscina() {
            return piscina;
        }

        public void setSpa(boolean spa) {
            this.spa = spa;
        }

        public boolean getSpa() {
            return spa;
        }

        public void setCochera(boolean cochera) {
            this.cochera = cochera;
        }

        public boolean getCochera() {
            return cochera;
        }

        public void setCafeteria(boolean cafeteria) {
            this.cafeteria = cafeteria;
        }

        public boolean getCafeteria() {
            return cafeteria;
        }

        public void setVestidores(boolean vestidores) {
            this.vestidores = vestidores;
        }

        public boolean getVestidores() {
            return vestidores;
        }

        public void setWifi(boolean wifi) {
            this.wifi = wifi;
        }

        public boolean getWifi() {
            return wifi;
        }
    }
    
    //Getters y Setters
    public int getIdGimnasio(){
        return idGimnasio;
    }
    
    public void setIdGimnasio(int idGimnasio) {
        this.idGimnasio = idGimnasio;
    }
    
    public int getIdCiudad(){
        return idCiudad;
    }

    public void setIdCiudad(int idCiudad){
        this.idCiudad = idCiudad;
    }
    
    public int getIdDistrito(){
        return idDistrito;
    }

    public void setIdDistrito(int idDistrito){
        this.idDistrito = idDistrito;
    }
    
    public String getNombreGimnasio(){
        return nombreGimnasio;
    }

    public void setNombreGimnasio(String nombreGimnasio){
        this.nombreGimnasio = nombreGimnasio;
    }
    
    public String getDireccion(){
        return direccion;
    }

    public void setDireccion(String direccion){
        this.direccion = direccion;
    }
    
    public Scanner getScanner(){
        return scanner;
    }
    
    public String getArchivoGimnasio(){
        return archivoGimnasio;
    }
    
    public Utilidades getUtilidades(){
        return utilidades;
    }
}