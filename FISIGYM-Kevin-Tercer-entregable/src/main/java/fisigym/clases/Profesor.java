/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fisigym.clases;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Profesor extends Usuario {
    private String dniProfesor;
    private String nombreProfesor;
    private String correo;
    private String telefono;
    private String ruc;
    private final Scanner scanner = new Scanner(System.in);
    private final Utilidades utilidades = new Utilidades();  // Instancia para usar limpiarPantalla

    // Método para mostrar el menú de gestión de profesores
    public void gestionarProfesores() {
        int opcion;
        do {
            utilidades.limpiarPantalla();  // Limpiar pantalla antes de mostrar el menú
            System.out.println("\n------------------- GESTIONAR PROFESORES -------------------");
            System.out.println("1. Ver lista de profesores");
            System.out.println("2. Registrar profesor");
            System.out.println("0. Salir");
            System.out.print("\nSeleccione una opcion: ");
            opcion = scanner.nextInt();
            scanner.nextLine();  // Limpiar buffer

            switch (opcion) {
                case 1 -> {
                    utilidades.limpiarPantalla();  // Limpiar pantalla antes de ver lista
                    verListaProfesores();
                }
                case 2 -> {
                    utilidades.limpiarPantalla();  // Limpiar pantalla antes de registrar
                    registrarProfesor();
                }
                case 0 -> System.out.println("Volviendo al menu anterior...");
                default -> System.out.println("Opcion no valida. Intente nuevamente.");
            }
        } while (opcion != 0);
    }

    // Método para registrar profesor y guardar en archivo .txt
    public void registrarProfesor() {
        System.out.println("\n------------------- REGISTRAR PROFESOR -------------------");
        System.out.print("Nombre del profesor: ");
        nombreProfesor = scanner.nextLine();
        System.out.print("DNI del profesor: ");
        dniProfesor = scanner.nextLine();
        System.out.print("Correo de contacto: ");
        correo = scanner.nextLine();
        System.out.print("Telefono: ");
        telefono = scanner.nextLine();
        System.out.print("RUC: ");
        ruc = scanner.nextLine();

        // Guardar en el archivo .txt
        almacenarProfesor();
        // Registrar disciplinas usando la clase anidada DisciplinasProfesor
        DisciplinasProfesor disciplinasProfesor = new DisciplinasProfesor();
        disciplinasProfesor.registrarDisciplinas();
    }

    // Método para almacenar el profesor en el archivo .txt
    private void almacenarProfesor() {
        try (BufferedWriter archivoProfesores = new BufferedWriter(new FileWriter("profesores.txt", true))) {
            archivoProfesores.write(dniProfesor + "><" + nombreProfesor + "><" + correo + "><" + telefono + "><" + ruc);
            archivoProfesores.newLine();
            System.out.println("\n¡Profesor registrado con exito!");
        } catch (IOException e) {
            System.out.println("Ocurrio un error al registrar el profesor.\n" + e.getMessage());
        }
    }

// Método para mostrar la lista de profesores registrados
private void verListaProfesores() {
    ArrayList<String> listaProfesores = new ArrayList<>();
    try (BufferedReader archivoProfesores = new BufferedReader(new FileReader("profesores.txt"))) {
        String linea;
        while ((linea = archivoProfesores.readLine()) != null) {
            listaProfesores.add(linea);
        }
    } catch (IOException e) {
        System.out.println("Ocurrio un error al leer la lista de profesores.\n" + e.getMessage());
    }

    if (listaProfesores.isEmpty()) {
        System.out.println("\nNingun profesor esta registrado.");
        utilidades.pausa();
        return;
    }

    int opcion;
    do {
        utilidades.limpiarPantalla();  // Limpiar pantalla antes de mostrar el menú
        System.out.println("\n------------------- LISTA DE PROFESORES -------------------");
        for (int i = 0; i < listaProfesores.size(); i++) {
            String[] datosProfesor = listaProfesores.get(i).split("><");
            System.out.println((i + 1) + ". " + datosProfesor[1]);  // Mostrar solo el nombre del profesor
        }
        System.out.println("0. Volver al menu anterior");

        System.out.print("\nSeleccione el numero del profesor para ver detalles (0. Para retornar): ");
        opcion = scanner.nextInt();
        scanner.nextLine();  // Limpiar buffer

        if (opcion > 0 && opcion <= listaProfesores.size()) {
            utilidades.limpiarPantalla();  // Limpiar pantalla antes de mostrar detalles
            mostrarDetallesProfesor(listaProfesores.get(opcion - 1));
            //utilidades.pausa();  // Esperar a que el administrador presione "ENTER"
        } else if (opcion != 0) {
            System.out.println("Opcion no valida. Intente nuevamente.");
        }

    } while (opcion != 0);
}

    // Método para mostrar detalles completos de un profesor seleccionado
    private void mostrarDetallesProfesor(String datosProfesor) {
        String[] detalles = datosProfesor.split("><");
        String dni = detalles[0];
        System.out.println("\n------------------- DETALLES DEL PROFESOR -------------------");
        System.out.println("Nombre: " + detalles[1]);
        System.out.println("DNI: " + detalles[0]);
        System.out.println("Correo: " + detalles[2]);
        System.out.println("Telefono: " + detalles[3]);
        System.out.println("RUC: " + detalles[4]);
        System.out.println("\nDisciplinas:");

        // Mostrar las disciplinas asociadas al profesor
        mostrarDisciplinasProfesor(dni);

        utilidades.pausa();  // Espera hasta que el usuario presione Enter
    }

    // Método para mostrar las disciplinas del profesor desde el archivo DisciplinasProfesor.txt
    private void mostrarDisciplinasProfesor(String dniProfesor) {
        try (BufferedReader archivoDisciplinas = new BufferedReader(new FileReader("DisciplinasProfesor.txt"))) {
            String linea;
            boolean disciplinasEncontradas = false;
            while ((linea = archivoDisciplinas.readLine()) != null) {
                String[] datos = linea.split("><");
                if (datos[0].equals(dniProfesor)) {
                    System.out.println("- " + datos[1]);
                    disciplinasEncontradas = true;
                }
            }
            if (!disciplinasEncontradas) {
                System.out.println("No tiene disciplinas registradas.");
            }
        } catch (IOException e) {
            System.out.println("Ocurrio un error al leer las disciplinas del profesor.\n" + e.getMessage());
        }
    }

    // Clase anidada para gestionar las disciplinas del profesor
    public class DisciplinasProfesor {
        private final ArrayList<String> disciplinasDisponibles = new ArrayList<>();
        private final ArrayList<String> disciplinasSeleccionadas = new ArrayList<>();

        public DisciplinasProfesor() {
            // Inicializar disciplinas disponibles
            disciplinasDisponibles.add("Zumba");
            disciplinasDisponibles.add("Pilates");
            disciplinasDisponibles.add("Boxeo");
            disciplinasDisponibles.add("Aqua Fitness");
            disciplinasDisponibles.add("TRX");
            disciplinasDisponibles.add("HIIT");
            disciplinasDisponibles.add("Spinning");
            disciplinasDisponibles.add("Cross Fit");
        }

        // Método para registrar disciplinas asociadas al profesor
        public void registrarDisciplinas() {
            boolean agregarOtra = true;
            var utilidades = new Utilidades();

            while (agregarOtra) {
                utilidades.limpiarPantalla();  // Limpiar pantalla antes de mostrar disciplinas
                mostrarDisciplinas();
                int opcion = seleccionarDisciplina();
                String disciplinaSeleccionada = disciplinasDisponibles.get(opcion - 1);

                // Verificar si la disciplina ya ha sido seleccionada
                if (disciplinasSeleccionadas.contains(disciplinaSeleccionada)) {
                    System.out.println("Esa disciplina ya ha sido registrada para este profesor.");
                } else {
                    // Confirmar selección antes de agregar la disciplina
                    System.out.println("\nDisciplina seleccionada: " + disciplinaSeleccionada+"\n");
                    if (utilidades.confirmacion()) {  // Si el administrador confirma
                        disciplinasSeleccionadas.add(disciplinaSeleccionada);  // Agregar la disciplina a la lista
                        almacenarDisciplina(disciplinaSeleccionada);  // Guardar en el archivo
                    }
                }

                // Preguntar si desea agregar otra disciplina
                System.out.print("\n¿Desea agregar otra disciplina mas? (0. Cancelar / 1. Aceptar): ");
                int agregar = scanner.nextInt();
                scanner.nextLine(); // Limpiar buffer
                agregarOtra = (agregar == 1);
            }
        }

        // Mostrar menú de disciplinas
        private void mostrarDisciplinas() {
            System.out.println("\n------------------- LISTA DE DISCIPLINAS -------------------");
            for (int i = 0; i < disciplinasDisponibles.size(); i++) {
                System.out.println((i + 1) + ". " + disciplinasDisponibles.get(i));
            }
        }

        // Seleccionar disciplina
        private int seleccionarDisciplina() {
            int opcion = -1;
            while (opcion < 1 || opcion > disciplinasDisponibles.size()) {
                try {
                    System.out.print("\nSeleccione una disciplina: ");
                    opcion = scanner.nextInt();
                    scanner.nextLine(); // Limpiar buffer
                } catch (InputMismatchException e) {
                    System.out.println("Entrada no valida. Por favor, ingrese un numero.");
                    scanner.nextLine();
                }
            }
            return opcion;
        }

        // Almacenar disciplina en archivo .txt
        private void almacenarDisciplina(String disciplina) {
            try (BufferedWriter archivoDisciplinas = new BufferedWriter(new FileWriter("DisciplinasProfesor.txt", true))) {
                archivoDisciplinas.write(dniProfesor + "><" + disciplina);
                archivoDisciplinas.newLine();
                //System.out.println("\n¡Disciplina registrada con exito!");
            } catch (IOException e) {
                System.out.println("Ocurrio un error al registrar la disciplina.\n" + e.getMessage());
            }
        }
    }
}