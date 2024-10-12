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

public class Ciudad {
    private int idCiudad;
    private String nombreCiudad;
    
    private final Scanner scanner = new Scanner(System.in);
    private final String archivoCiudad = "ciudades.txt";
    private final Utilidades utilidades = new Utilidades();
    
    // Constructor
    public Ciudad(int idCiudad, String nombreCiudad) {
        this.idCiudad = idCiudad;
        this.nombreCiudad = nombreCiudad;
    }

    public Ciudad() {
    }
    
    @Override
    public String toString() {
        return idCiudad + "><" + nombreCiudad;
    }
    
    // Método para generar un ID único que sea ascendente
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

    // Método para obtener todos los IDs de ciudades almacenadas en el archivo
    private List<Integer> obtenerIdsExistentes() {
        List<Integer> idsExistentes = new ArrayList<>();
        
        try (BufferedReader archivoCiudades = new BufferedReader(new FileReader(archivoCiudad))) {
            String line;

            while ((line = archivoCiudades.readLine()) != null) {
                String[] datos = line.split("><");
                int idCiudadGuardada = Integer.parseInt(datos[0]);
                
                idsExistentes.add(idCiudadGuardada);
            }
        } catch (IOException e) {
            System.out.println("Ocurrió un error al leer los IDs existentes.");
        }
        
        return idsExistentes;
    }

    public void registrarCiudad() {
        System.out.println("\n------------------- REGISTRAR CIUDAD -------------------");
        System.out.print("Nombre de ciudad: ");
        nombreCiudad = scanner.nextLine().trim();

        if (nombreCiudad.isEmpty()) {
            System.out.println("El nombre de la ciudad no puede estar vacío.");
            utilidades.pausa();
            return;
        }
    
        // Generar un ID único
        idCiudad = generarIdUnico();
        System.out.println("ID de la ciudad: " + idCiudad);
        System.out.println("---------------------------------------------------------");
        
        this.guardarCiudad();
    }
    
    private void guardarCiudad() {    
        try (BufferedWriter archivoCiudades = new BufferedWriter(new FileWriter(archivoCiudad, true))) {
            archivoCiudades.write(this.toString());
            archivoCiudades.newLine();
            System.out.println("\n¡Ciudad registrada con éxito!");
            utilidades.pausa();
        } catch (IOException e) {
            System.out.println("Ocurrió un error al registrar la ciudad.");
            utilidades.pausa();
        }
    }
    
    public void listarCiudades() {

        System.out.println("\n------------------- LISTA DE CIUDADES -------------------");
        
        try (BufferedReader archivoCiudades = new BufferedReader(new FileReader(archivoCiudad))) {
            String line;
            boolean hayCiudades = false;

            System.out.println("\tID"+ "\tNombres");

            while ((line = archivoCiudades.readLine()) != null) {
                String[] datos = line.split("><");
                System.out.println("\t" + datos[0] + "\t" + datos[1]);
                hayCiudades = true;
            }

            if (!hayCiudades) {
                System.out.println("No hay ciudades registradas.");
                //utilidades.pausa(); no poner pausa en método listar
            }
        } catch (IOException e) {
            System.out.println("Ocurrió un error al leer las ciudades.");
            //utilidades.pausa(); no poner pausa en método listar
        }
    }

    public String obtenerNombreCiudad(int idCiudad){
        try (BufferedReader archivoCiudades = new BufferedReader(new FileReader(archivoCiudad))){
            String line;
            
            while ((line = archivoCiudades.readLine()) != null){
                String[] datos = line.split("><");
                int idGuardado = Integer.parseInt(datos[0]);
                if (idGuardado == idCiudad){
                    return datos[1];
                }
            }

        }catch(IOException e){
            System.out.println("Ocurrió un error al leer las ciudades.");
        }
        
        return "Desconocida";
    }
    
    public void actualizarCiudad() {
        int idCiudadIngresada;
        boolean ciudadEncontrada = false;
        String nuevoNombreCiudad;
        List<String> ciudades = new ArrayList<>();
        
        if (utilidades.verificarArchivoConContenido(archivoCiudad) == false) {
            System.out.println("Primero registre una ciudad en el menú.");
            utilidades.pausa();
            return;
        }

        // PEDIR EL ID DE UNA CIUDAD VÁLIDA
        listarCiudades();
        System.out.println("\n------------------- ACTUALIZAR CIUDAD -------------------");
        System.out.println("                 Presione 0 para regresar                 ");
        idCiudadIngresada = utilidades.solicitarId(archivoCiudad, "Ingrese el ID de la ciudad a actualizar: ");

        if (idCiudadIngresada == 0){
            return;
        }
        
        try (BufferedReader archivoCiudades = new BufferedReader(new FileReader(archivoCiudad))) {
            String line;

            while ((line = archivoCiudades.readLine()) != null) {
                String[] datos = line.split("><");
                int idCiudadGuardada = Integer.parseInt(datos[0]);

                if (idCiudadGuardada == idCiudadIngresada) {
                    System.out.print("\nIngrese el nuevo nombre de la ciudad: ");
                    nuevoNombreCiudad = scanner.nextLine();

                    if (nuevoNombreCiudad.isEmpty()) {
                        System.out.println("El nombre no puede estar vacío.");
                        utilidades.pausa();
                        return;
                    }

                    ciudades.add(idCiudadGuardada + "><" + nuevoNombreCiudad);
                    ciudadEncontrada = true;
                } else {
                    ciudades.add(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Ocurrió un error al leer las ciudades.");
            utilidades.pausa();
        }
        
        if (ciudadEncontrada) {
            try (BufferedWriter archivoCiudades = new BufferedWriter(new FileWriter(archivoCiudad))) {
                for (String ciudad : ciudades) {
                    archivoCiudades.write(ciudad);
                    archivoCiudades.newLine();
                }

                System.out.println("La ciudad ha sido actualizada exitosamente.");
                utilidades.pausa();
            } catch (IOException e) {
                System.out.println("Ocurrió un error al actualizar la ciudad.");
                utilidades.pausa();
            }
        } else {
            System.out.println("No se encontró una ciudad con el ID proporcionado.");
            utilidades.pausa();
        }
    }
    
    public void eliminarCiudad() {
        int idCiudadIngresada;
        boolean ciudadEncontrada = false;
        List<String> ciudades = new ArrayList<>();

        if (utilidades.verificarArchivoConContenido(archivoCiudad) == false) {
            System.out.println("Primero registre una ciudad en el menú.");
            utilidades.pausa();
            return;
        }

        // PEDIR EL ID DE UNA CIUDAD VÁLIDA
        listarCiudades();
        System.out.println("\n------------------- ELIMINAR CIUDAD -------------------");
        System.out.println("                 Presione 0 para regresar                 ");
        idCiudadIngresada = utilidades.solicitarId(archivoCiudad, "Ingrese el ID de la ciudad a eliminar: ");

        if (idCiudadIngresada == 0){
            return;
        }
        
        try (BufferedReader archivoCiudades = new BufferedReader(new FileReader(archivoCiudad))) {
            String line;

            while ((line = archivoCiudades.readLine()) != null) {
                String[] datos = line.split("><");
                int idCiudadGuardada = Integer.parseInt(datos[0]);

                if (idCiudadGuardada == idCiudadIngresada) {
                    ciudadEncontrada = true;
                } else {
                    ciudades.add(line);
                }
            }

        } catch (IOException e) {
            System.out.println("Ocurrió un error al leer las ciudades.");
            utilidades.pausa();
        }
        
        if (ciudadEncontrada) {
            try (BufferedWriter archivoCiudades = new BufferedWriter(new FileWriter(archivoCiudad))) {
                for (String ciudad : ciudades) {
                    archivoCiudades.write(ciudad);
                    archivoCiudades.newLine();
                }

                System.out.println("La ciudad ha sido eliminada correctamente.");
                utilidades.pausa();
            } catch (IOException e) {
                System.out.println("Ocurrió un error al eliminar la ciudad.");
                utilidades.pausa();
            }
        } else {
            System.out.println("No se encontró una ciudad con el ID proporcionado.");
            utilidades.pausa();
        }
    }

    public void menuCiudad() {
        utilidades.verificarArchivo(archivoCiudad);

        int opcion = -1;

        do {
            utilidades.limpiarPantalla();
            System.out.println("\n------------------- MENÚ CIUDAD -------------------");
            System.out.println("1.\tRegistrar ciudad");
            System.out.println("2.\tListar ciudades");
            System.out.println("3.\tActualizar ciudad");
            System.out.println("4.\tEliminar ciudad");
            System.out.println("\n0.\tSalir");

            try {
                System.out.print("\nDigite su opción: ");
                opcion = scanner.nextInt();
                scanner.nextLine();
                
                switch (opcion){
                    case 0 -> {
                        utilidades.limpiarPantalla();
                        System.out.println("Saliendo del menú...");
                    }
                    case 1 -> {
                        utilidades.limpiarPantalla();
                        registrarCiudad();
                    }
                    case 2 -> {
                        utilidades.limpiarPantalla();
                        listarCiudades();
                        utilidades.pausa();
                    }
                    case 3 -> {
                        utilidades.limpiarPantalla();
                        actualizarCiudad();
                        
                    }
                    case 4 -> {
                        utilidades.limpiarPantalla();
                        eliminarCiudad();
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

    //Getters y Setters
    public int getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(int idCiudad) {
        this.idCiudad = idCiudad;
    }

    public String getNombreCiudad() {
        return nombreCiudad;
    }

    public void setNombreCiudad(String nombreCiudad) {
        this.nombreCiudad = nombreCiudad;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public String getArchivoCiudad() {
        return archivoCiudad;
    }

    public Utilidades getUtilidades() {
        return utilidades;
    }
}