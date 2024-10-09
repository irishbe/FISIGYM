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

public class Distrito {
    private int idDistrito;
    private int idCiudad;  // Relación con Ciudad
    private String nombreDistrito;

    private final Scanner scanner = new Scanner(System.in);
    private final String archivoDistrito = "distritos.txt";
    private final Utilidades utilidades = new Utilidades();
    
    // Constructor
    public Distrito(int idDistrito, int idCiudad, String nombreDistrito) {
        this.idDistrito = idDistrito;
        this.idCiudad = idCiudad;
        this.nombreDistrito = nombreDistrito;
    }

    public Distrito() {
    }
    
    // Método toString para formatear el guardado en archivo
    @Override
    public String toString() {
        return idDistrito + "><" + idCiudad + "><" + nombreDistrito;
    }
    
    // Método para generar un ID único para distritos
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

    // Método para obtener todos los IDs de distritos almacenados en el archivo
    private List<Integer> obtenerIdsExistentes() {
        List<Integer> idsExistentes = new ArrayList<>();
        
        try (BufferedReader archivoDistritos = new BufferedReader(new FileReader(archivoDistrito))) {
            String line;
            while ((line = archivoDistritos.readLine()) != null) {
                String[] datos = line.split("><");
                int idDistritoGuardado = Integer.parseInt(datos[0]);
                idsExistentes.add(idDistritoGuardado);
            }
        } catch (IOException e) {
            System.out.println("Ocurrió un error al leer los IDs existentes.");
        }
        
        return idsExistentes;
    }

    public void registrarDistrito() {
        System.out.println("\n------------------- REGISTRAR DISTRITO -------------------");
        
        // Seleccionar ciudad existente
        Ciudad ciudad = new Ciudad();
        ciudad.listarCiudades();
        
        System.out.print("ID de la ciudad: ");
        idCiudad = Integer.parseInt(scanner.nextLine().trim());
        
        System.out.print("Nombre de distrito: ");
        nombreDistrito = scanner.nextLine().trim();

        if (nombreDistrito.isEmpty()) {
            System.out.println("El nombre del distrito no puede estar vacío.");
            utilidades.pausa();
            return;
        }

        // Generar un ID único
        idDistrito = generarIdUnico();
        System.out.println("ID del distrito: " + idDistrito);
        System.out.println("---------------------------------------------------------");
        
        this.guardarDistrito();
    }
    
    private void guardarDistrito() {    
        try (BufferedWriter archivoDistritos = new BufferedWriter(new FileWriter(archivoDistrito, true))) {
            archivoDistritos.write(this.toString());
            archivoDistritos.newLine();
            System.out.println("\n¡Distrito registrado con éxito!");
            utilidades.pausa();
        } catch (IOException e) {
            System.out.println("Ocurrió un error al registrar el distrito.");
            utilidades.pausa();
        }
    }
    
    public void listarDistritos() {
        System.out.println("\n------------------- LISTA DE DISTRITOS -------------------");
        
        try (BufferedReader archivoDistritos = new BufferedReader(new FileReader(archivoDistrito))) {
            String line;
            boolean hayDistritos = false;

            System.out.println("\tID\tID Ciudad\tNombres");

            while ((line = archivoDistritos.readLine()) != null) {
                String[] datos = line.split("><");
                System.out.println("\t" + datos[0] + "\t" + datos[1] + "\t\t" + datos[2]);
                hayDistritos = true;
            }

            if (!hayDistritos) {
                System.out.println("No hay distritos registrados.");
                utilidades.pausa();
            }
        } catch (IOException e) {
            System.out.println("Ocurrió un error al leer los distritos.");
            utilidades.pausa();
        }
    }
    
    public void actualizarDistrito() {
        String idDistritoIngresada;
        boolean distritoEncontrado = false;
        String nuevoNombreDistrito;
        List<String> distritos = new ArrayList<>();

        listarDistritos();
        System.out.print("\nIngrese el ID del distrito a actualizar: ");
        idDistritoIngresada = scanner.nextLine();
        
        try (BufferedReader archivoDistritos = new BufferedReader(new FileReader(archivoDistrito))) {
            String line;

            while ((line = archivoDistritos.readLine()) != null) {
                String[] datos = line.split("><");
                String idDistritoGuardado = datos[0];

                if (idDistritoGuardado.equals(idDistritoIngresada)) {
                    System.out.print("\nIngrese el nuevo nombre del distrito: ");
                    nuevoNombreDistrito = scanner.nextLine();

                    if (nuevoNombreDistrito.isEmpty()) {
                        System.out.println("El nombre no puede estar vacío.");
                        utilidades.pausa();
                        return;
                    }

                    distritos.add(idDistritoGuardado + "><" + datos[1] + "><" + nuevoNombreDistrito);
                    distritoEncontrado = true;
                } else {
                    distritos.add(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Ocurrió un error al leer los distritos.");
            utilidades.pausa();
        }
        
        if (distritoEncontrado) {
            try (BufferedWriter archivoDistritos = new BufferedWriter(new FileWriter(archivoDistrito))) {
                for (String distrito : distritos) {
                    archivoDistritos.write(distrito);
                    archivoDistritos.newLine();
                }

                System.out.println("El distrito ha sido actualizado exitosamente.");
                utilidades.pausa();

            } catch (IOException e) {
                System.out.println("Ocurrió un error al actualizar el distrito.");
                utilidades.pausa();
            }
        } else {
            System.out.println("No se encontró un distrito con el ID proporcionado.");
            utilidades.pausa();
        }
    }
    
    public void eliminarDistrito() {
        String idDistritoIngresada;
        boolean distritoEncontrado = false;
        List<String> distritos = new ArrayList<>();
        
        listarDistritos();
        System.out.print("\nIngrese el ID del distrito a eliminar: ");
        idDistritoIngresada = scanner.nextLine();
        
        try (BufferedReader archivoDistritos = new BufferedReader(new FileReader(archivoDistrito))) {
            String line;

            while ((line = archivoDistritos.readLine()) != null) {
                String[] datos = line.split("><");
                String idDistritoGuardado = datos[0];

                if (idDistritoGuardado.equals(idDistritoIngresada)) {
                    distritoEncontrado = true;
                } else {
                    distritos.add(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Ocurrió un error al leer los distritos.");
            utilidades.pausa();
        }
        
        if (distritoEncontrado) {
            try (BufferedWriter archivoDistritos = new BufferedWriter(new FileWriter(archivoDistrito))) {
                for (String distrito : distritos) {
                    archivoDistritos.write(distrito);
                    archivoDistritos.newLine();
                }

                System.out.println("El distrito ha sido eliminado correctamente.");
                utilidades.pausa();
            } catch (IOException e) {
                System.out.println("Ocurrió un error al eliminar el distrito.");
                utilidades.pausa();
            }
        } else {
            System.out.println("No se encontró un distrito con el ID proporcionado.");
            utilidades.pausa();
        }
    }

    public void menuDistrito() {
        utilidades.verificarArchivo(archivoDistrito);

        int opcion = -1;

        do {
            utilidades.limpiarPantalla();
            System.out.println("\n------------------- MENÚ DISTRITO -------------------");
            System.out.println("1.\tRegistrar distrito");
            System.out.println("2.\tListar distritos");
            System.out.println("3.\tActualizar distrito");
            System.out.println("4.\tEliminar distrito");
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
                        registrarDistrito();
                    }
                    case 2 -> {
                        utilidades.limpiarPantalla();
                        listarDistritos();
                        utilidades.pausa();
                    }
                    case 3 -> {
                        utilidades.limpiarPantalla();
                        actualizarDistrito();
                        
                    }
                    case 4 -> {
                        utilidades.limpiarPantalla();
                        eliminarDistrito();
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
    public int getIdDistrito() {
        return idDistrito;
    }

    public void setIdDistrito(int idDistrito) {
        this.idDistrito = idDistrito;
    }

    public int getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(int idCiudad) {
        this.idCiudad = idCiudad;
    }

    public String getNombreDistrito() {
        return nombreDistrito;
    }

    public void setNombreDistrito(String nombreDistrito) {
        this.nombreDistrito = nombreDistrito;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public String getArchivoDistrito() {
        return archivoDistrito;
    }

    public Utilidades getUtilidades() {
        return utilidades;
    }
}