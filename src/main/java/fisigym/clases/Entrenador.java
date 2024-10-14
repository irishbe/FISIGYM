package fisigym.clases;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class Entrenador extends Usuario {
    private String correoContacto;
    private String telefono;
    private String ruc;
    private DisciplinasEntrenador disciplinasEntrenador;

    private final String archivoEntrenador = "entrenadores.txt";

    @Override
    public String toString() {
        return super.toString() + "><" + correoContacto + "><" + telefono + "><" + ruc + "><" + 
        disciplinasEntrenador.isZumba() + "><" + disciplinasEntrenador.isHiit() + "><" + 
        disciplinasEntrenador.isPilates() + "><" + disciplinasEntrenador.isBoxeo() + "><" +
        disciplinasEntrenador.isAquaFitness() + "><" + disciplinasEntrenador.isTrx() + "><" +
        disciplinasEntrenador.isSpinning() + "><" + disciplinasEntrenador.isCrossFit();
    }

    public void menuEntrenadores(){
        utilidades.verificarArchivo(archivoEntrenador);

        int opcion = -1;

        do {
            utilidades.limpiarPantalla();
            System.out.println("\n------------------- MENÚ DE ENTRENADORES -------------------");
            System.out.println("\n1.\tRegistrar entrenador");
            System.out.println("2.\tListar entrenadores");
            System.out.println("3.\tActualizar entrenador");
            System.out.println("4.\tEliminar entrenador");
            System.out.println("5.\tListar disciplinas");
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
                        registrarEntrenador();
                    }
                    case 2 -> {
                        utilidades.limpiarPantalla();
                        listarEntrenadores();
                        utilidades.pausa(); // Solo pausa en listar
                    }
                    case 3 -> {
                        utilidades.limpiarPantalla();
                        actualizarEntrenador();
                    }
                    case 4 -> {
                        utilidades.limpiarPantalla();
                        eliminarEntrenador();
                    }
                    case 5 -> {
                        utilidades.limpiarPantalla();
                        listarDisciplinas();
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

    public void registrarEntrenador() {
        String confirmacionDisciplinas;
    
        System.out.println("\n------------------- REGISTRAR ENTRENADOR -------------------");
    
        System.out.print("Nombre de usuario:  "); 
        super.nombreUsuario = scanner.nextLine().trim();
        if (super.nombreUsuario.isEmpty()) {
            System.out.println("\nEl campo no puede estar vacío.");
            utilidades.pausa();
            return;
        }
    
        System.out.print("Correo: "); 
        super.correo = scanner.nextLine().trim();
        if (super.correo.isEmpty()) {
            System.out.println("\nEl campo no puede estar vacío.");
            utilidades.pausa();
            return;
        }
    
        System.out.print("Contraseña: "); 
        super.contrasenia = scanner.nextLine().trim();
        if (super.contrasenia.isEmpty()) {
            System.out.println("\nEl campo no puede estar vacío.");
            utilidades.pausa();
            return;
        }
    
        System.out.print("DNI:  "); 
        super.dni = scanner.nextLine().trim();
        if (super.dni.isEmpty()) {
            System.out.println("\nEl campo no puede estar vacío.");
            utilidades.pausa();
            return;
        }
    
        System.out.print("Nombres:  "); 
        super.nombre = scanner.nextLine().trim();
        if (super.nombre.isEmpty()) {
            System.out.println("\nEl campo no puede estar vacío.");
            utilidades.pausa();
            return;
        }
    
        System.out.print("Apellidos:  "); 
        super.apellido = scanner.nextLine().trim();
        if (super.apellido.isEmpty()) {
            System.out.println("\nEl campo no puede estar vacío.");
            utilidades.pausa();
            return;
        }
    
        System.out.print("Correo de contacto (Presione enter para registrar el anterior):  "); 
        String correoContactoInput = scanner.nextLine().trim();
        if (correoContactoInput.isEmpty()) {
            correoContacto = super.correo;  // Mantener el correo anterior
            System.out.println("El correo de contacto se definió con: " + correoContacto);
        } else {
            correoContacto = correoContactoInput;  // Si se ingresa un nuevo correo
        }
    
        System.out.print("Teléfono:  "); 
        telefono = scanner.nextLine().trim();
        if (telefono.isEmpty()) {
            System.out.println("\nEl campo no puede estar vacío.");
            utilidades.pausa();
            return;
        }
    
        System.out.print("RUC:  "); 
        ruc = scanner.nextLine().trim();
        if (ruc.isEmpty()) {
            System.out.println("\nEl campo no puede estar vacío.");
            utilidades.pausa();
            return;
        }
    
        System.out.println("---------------------------------------------------------");
        
        this.disciplinasEntrenador = new DisciplinasEntrenador();
    
        System.out.print("\n¿Desea agregar disciplinas que imparte el entrenador? (0: NO) (1: SÍ): ");
        confirmacionDisciplinas = scanner.nextLine();
                            
        if (confirmacionDisciplinas.equals("1")) {
            disciplinasEntrenador.registrarDisciplinas();
        } else {
            System.out.println("\nNo se registró ninguna disciplina al entrenador");
            utilidades.pausa();
        }
        
        utilidades.limpiarPantalla();
        System.out.println("---------------------------------------------------------");
        System.out.println("Nombre de usuario: " + super.nombreUsuario);
        System.out.println("DNI: " + super.dni);
        System.out.println("Nombres: " + super.nombre);
        System.out.println("Apellidos: " + super.apellido);
        System.out.println("Correo: " + super.correo);
        System.out.println("Correo de contacto: " + correoContacto);
        System.out.println("Teléfono: " + telefono);
        System.out.println("RUC: " + ruc);
        System.out.println("---------------------------------------------------------");
    
        guardarEntrenador();
    }

    private void guardarEntrenador() {
        try (BufferedWriter archivoEntrenadores = new BufferedWriter(new FileWriter(archivoEntrenador, true))) {
            archivoEntrenadores.write( this.toString() );
            archivoEntrenadores.newLine();
            System.out.println("\n¡Entrenador registrado con exito!");
            utilidades.pausa();
        } catch (IOException e) {
        System.out.println("\nOcurrió un error al registrar el entrenador.\n" + e.getMessage());
            utilidades.pausa();
        }
    }

    public void listarEntrenadores() {
        System.out.println("\n------------------- LISTA DE ENTRENADORES -------------------");
        
        try(BufferedReader archivoEntrenadores = new BufferedReader(new FileReader(archivoEntrenador))){
            String line;
            boolean hayEntrenadores=false;

            // Encabezado de la tabla
            // %s: Representa una cadena, %d: representa un entero, - indica alineación a la izquierda
            System.out.printf("%-15s %-25s %-25s %-25s %-15s %-15s\n",
                        "DNI", "Nombres", "Apellidos", "Correo de contacto", "Teléfono", "RUC");
            
            while((line = archivoEntrenadores.readLine()) !=null){
                String[] datos = line.split("><");

                dni = datos[3];
                nombre = datos[4];
                apellido = datos[5];
                correoContacto = datos[6];
                telefono = datos[7];
                ruc = datos[8];

                // Limitar el tamaño de las cadenas si son muy largas
                nombre = (nombre.length() > 25) ? nombre.substring(0, 22) + "..." : nombre;
                apellido = (apellido.length() > 20) ? apellido.substring(0, 17) + "..." : apellido;
                correoContacto = (correoContacto.length() > 20) ? correoContacto.substring(0, 17) + "..." : correoContacto;
                telefono = (telefono.length() > 15) ? telefono.substring(0, 12) + "..." : telefono;
                ruc = (ruc.length() > 15) ? ruc.substring(0, 12) + "..." : ruc;

                System.out.printf("%-15s %-25s %-25s %-25s %-15s %-15s\n",
                            dni, nombre, apellido, correoContacto, telefono, ruc);

                hayEntrenadores=true;
            }

            if (!hayEntrenadores){
                System.out.println("No hay entrenadores registrados.");
                // utilidades.pausa(); no poner pausa en método listar
            }

        } catch (IOException e){
            System.out.println("Ocurrió un error al leer los entrenadores.");
            // utilidades.pausa(); No poner pausa en método listar
        }
    }

    public void listarDisciplinas() {
        System.out.println("\n------------------- LISTA DE DISCIPLINAS -------------------");
        
        try(BufferedReader archivoGimnasios = new BufferedReader(new FileReader(archivoEntrenador))){
            String line;
            boolean hayEntrenadores = false;

            // Encabezado de la tabla
            System.out.printf("%-15s %-20s %-20s %-10s %-10s %-10s %-10s %-15s %-10s %-10s %-10s\n",
                        "DNI",  "Nombres", "Apellidos", "Zumba", "Hiit", "Pilates", "Boxeo", "AquaFitness", "TRX", "Spinning", "CrossFit");

            while((line = archivoGimnasios.readLine()) !=null){
                String[] datos = line.split("><");
                
                dni = datos[3];
                nombre = datos[4];
                apellido = datos[5];

                // Limitar el tamaño de las cadenas si son muy largas
                nombre = (nombre.length() > 20) ? nombre.substring(0, 17) + "..." : nombre;
                apellido = (apellido.length() > 20) ? apellido.substring(0, 17) + "..." : apellido;

                System.out.printf("%-15s %-20s %-20s %-10s %-10s %-10s %-10s %-15s %-10s %-10s %-10s\n",
                            dni, nombre, apellido, datos[9], datos[10], datos[11], datos[12], datos[13], datos[14], datos[15], datos[16]);
                hayEntrenadores = true;
            }

            if (!hayEntrenadores){
                System.out.println("No hay entrenadores registrados.");
                // utilidades.pausa(); No poner pausa en método listar
            }
        }catch (IOException e){
            System.out.println("Ocurrió un error al leer los entrenadores.");
            // utilidades.pausa(); No poner pausa en método listar
        }
    }

    // Método para mostrar detalles completos de un entrenador seleccionado
    /*private void mostrarDetallesEntrenador(String datosEntrenador) {
        String[] detalles = datosEntrenador.split("><");
        String dni = detalles[0];
        System.out.println("\n------------------- DETALLES DEL ENTRENADOR -------------------");
        System.out.println("Nombre: " + detalles[1]);
        System.out.println("DNI: " + dni);
        System.out.println("Correo: " + detalles[2]);
        System.out.println("Telefono: " + detalles[3]);
        System.out.println("RUC: " + detalles[4]);
        System.out.println("\nDisciplinas:");

        // Mostrar las disciplinas asociadas al entrenador
        mostrarDisciplinasEntrenador(dni);

        utilidades.pausa();
    }*/

    public void actualizarEntrenador() {
        boolean entrenadorEncontrado = false;
        String dniIngresado;
        String nuevoDni;
        String nuevoNombre;
        String nuevoApellido;
        String nuevoCorreoContacto;
        String nuevoTelefono;
        String nuevoRUC;
        List<String> entrenadores = new ArrayList<>();

        if (!utilidades.verificarArchivoConContenido(archivoEntrenador)) {
            System.out.println("Primero registre un entrenador en el menú.");
            utilidades.pausa();
            return;
        }

        listarEntrenadores();
        System.out.println("\n------------------- ACTUALIZAR ENTRENADOR -------------------");
        System.out.println("                  Presione 0 para regresar                 ");
        
        dniIngresado = solicitarDni(archivoEntrenador, "Ingrese el DNI del entrenador a actualizar: ");

        if (dniIngresado.equals("0")) {
            return;
        }

        try (BufferedReader archivoEntrenadores = new BufferedReader(new FileReader(archivoEntrenador))) {
            String line;

            while ((line = archivoEntrenadores.readLine()) != null) {
                String[] datos = line.split("><");
                String dniGuardado = datos[3];

                if (dniGuardado.equals(dniIngresado)) {
                    System.out.print("\nIngrese el nuevo DNI del entrenador (presione enter para mantener el actual): ");
                    nuevoDni = scanner.nextLine().trim();
                    if (nuevoDni.isEmpty()) {
                        nuevoDni = datos[3];
                    }

                    System.out.print("\nIngrese el nuevo nombre del entrenador (presione enter para mantener el actual): ");
                    nuevoNombre = scanner.nextLine().trim();
                    if (nuevoNombre.isEmpty()) {
                        nuevoNombre = datos[4];
                    }

                    System.out.print("\nIngrese el nuevo apellido del entrenador (presione enter para mantener el actual): ");
                    nuevoApellido = scanner.nextLine().trim();
                    if (nuevoApellido.isEmpty()) {
                        nuevoApellido = datos[5];
                    }

                    System.out.print("\nIngrese el nuevo correo de contacto (presione enter para mantener el actual): ");
                    nuevoCorreoContacto = scanner.nextLine().trim();
                    if (nuevoCorreoContacto.isEmpty()) {
                        nuevoCorreoContacto = datos[6];
                    }

                    System.out.print("\nIngrese el nuevo teléfono (presione enter para mantener el actual): ");
                    nuevoTelefono = scanner.nextLine().trim();
                    if (nuevoTelefono.isEmpty()) {
                        nuevoTelefono = datos[7];
                    }

                    System.out.print("\nIngrese el nuevo RUC (presione enter para mantener el actual): ");
                    nuevoRUC = scanner.nextLine().trim();
                    if (nuevoRUC.isEmpty()) {
                        nuevoRUC = datos[8];
                    }

                    String entrenadorActualizado = datos[0] + "><" + datos[1] + "><" + datos[2] + "><" + nuevoDni + "><" + nuevoNombre + "><" + nuevoApellido + "><" + nuevoCorreoContacto + "><" + nuevoTelefono + "><" + nuevoRUC;
                    entrenadores.add(entrenadorActualizado);
                    entrenadorEncontrado = true;
                } else {
                    entrenadores.add(line);
                }
            }
        } catch (IOException e) {
            System.out.println("\nOcurrió un error al leer los entrenadores.");
            utilidades.pausa();
            return;
        }

        if (entrenadorEncontrado) {
            try (BufferedWriter archivoEntrenadores = new BufferedWriter(new FileWriter(archivoEntrenador))) {
                for (String entrenador : entrenadores) {
                    archivoEntrenadores.write(entrenador);
                    archivoEntrenadores.newLine();
                }
                System.out.println("\nEl entrenador ha sido actualizado exitosamente.");
            } catch (IOException e) {
                System.out.println("\nOcurrió un error al actualizar el entrenador.");
            }
        } else {
            System.out.println("\nNo se encontró un entrenador con el DNI proporcionado.");
        }

        utilidades.pausa();
    }

    public void eliminarEntrenador() {
        String dniIngresado;
        boolean entrenadorEncontrado = false;
        List<String> entrenadores = new ArrayList<>();

        if (!utilidades.verificarArchivoConContenido(archivoEntrenador)) {
            System.out.println("Primero registre un entrenador en el menú.");
            utilidades.pausa();
            return;
        }

        listarEntrenadores();
        System.out.println("\n------------------- ELIMINAR ENTRENADOR -------------------");
        System.out.println("                  Presione 0 para regresar                 ");
        
        // Utilizamos el método para solicitar el DNI
        dniIngresado = solicitarDni(archivoEntrenador, "Ingrese el DNI del entrenador a eliminar: ");

        if (dniIngresado.equals("0")) {
            return;
        }

        try (BufferedReader archivoEntrenadores = new BufferedReader(new FileReader(archivoEntrenador))) {
            String line;

            while ((line = archivoEntrenadores.readLine()) != null) {
                String[] datos = line.split("><");
                String dniGuardado = datos[3];

                if (dniGuardado.equals(dniIngresado)) {
                    entrenadorEncontrado = true;
                } else {
                    entrenadores.add(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Ocurrió un error al leer los entrenadores.");
            utilidades.pausa();
            return;
        }

        if (entrenadorEncontrado) {
            // Guardar la lista actualizada de entrenadores, excluyendo el eliminado
            try (BufferedWriter archivoEntrenadores = new BufferedWriter(new FileWriter(archivoEntrenador))) {
                for (String entrenador : entrenadores) {
                    archivoEntrenadores.write(entrenador);
                    archivoEntrenadores.newLine();
                }
                System.out.println("\nEl entrenador ha sido eliminado exitosamente.");
            } catch (IOException e) {
                System.out.println("\nOcurrió un error al eliminar el entrenador.");
            }
        } else {
            System.out.println("\nNo se encontró un entrenador con el DNI proporcionado.");
        }

        utilidades.pausa();
    }
    

    // Clase anidada para gestionar las disciplinas del entrenador
    public class DisciplinasEntrenador {
        private boolean zumba;
        private boolean hiit;
        private boolean pilates;
        private boolean boxeo;
        private boolean aquaFitness;
        private boolean trx;
        private boolean spinning;
        private boolean crossFit;

        public DisciplinasEntrenador() {
            this.zumba = false;
            this.hiit = false;
            this.pilates = false;
            this.boxeo = false;
            this.aquaFitness = false;
            this.trx = false;
            this.spinning = false;
            this.crossFit = false;
        }

        // Método para registrar disciplinas asociadas al entrenador
        public void registrarDisciplinas(){
            utilidades.verificarArchivo(archivoEntrenador);

            int opcion = -1;
            String respuestaDisciplinas;

            do {
                utilidades.limpiarPantalla();
                System.out.println("\n------------------- REGISTRAR DISCIPLINAS -------------------\n");
                System.out.println("1.\tZumba");
                System.out.println("2.\tHIIT");
                System.out.println("3.\tPilates");
                System.out.println("4.\tBoxeo");
                System.out.println("5.\tAqua Fitness");
                System.out.println("6.\tTRX");
                System.out.println("7.\tSpinning");
                System.out.println("6.\tCross Fit");
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
                            System.out.print("El entrenador enseña Zumba (0: NO) (1: SÍ): ");
                            respuestaDisciplinas = scanner.nextLine();
                            
                            if( respuestaDisciplinas.equals("1") ) {
                                System.out.println("Zumba: Sí");
                                setZumba(true);
                            } else {
                                System.out.println("Zumba: No");
                                setZumba(false);            
                            }

                            utilidades.pausa();
                        }
                        case 2 -> {
                            utilidades.limpiarPantalla();
                            System.out.print("El entrenador enseña HIIT (0: NO) (1: SÍ): ");
                            respuestaDisciplinas = scanner.nextLine();
                            
                            if( respuestaDisciplinas.equals("1") ) {
                                System.out.println("HIIT: Sí");
                                setHiit(true);
                            } else {
                                System.out.println("HIIT: No");
                                setHiit(false);            
                            }

                            utilidades.pausa();
                        }
                        case 3 -> {
                            utilidades.limpiarPantalla();
                            System.out.print("El entrenador enseña Pilates (0: NO) (1: SÍ): ");
                            respuestaDisciplinas = scanner.nextLine();
                            
                            if( respuestaDisciplinas.equals("1") ) {
                                System.out.println("Pilates: Sí");
                                setPilates(true);
                            } else {
                                System.out.println("Pilates: No");
                                setPilates(false);       
                            }

                            utilidades.pausa();
                        }
                        case 4 -> {
                            utilidades.limpiarPantalla();
                            System.out.print("El entrenador enseña Boxeo (0: NO) (1: SÍ): ");
                            respuestaDisciplinas = scanner.nextLine();
                            
                            if( respuestaDisciplinas.equals("1") ) {
                                System.out.println("Boxeo: Sí");
                                setBoxeo(true);
                            } else {
                                System.out.println("Boxeo: No");
                                setBoxeo(false);          
                            }

                            utilidades.pausa();
                        }
                        case 5 -> {
                            utilidades.limpiarPantalla();
                            System.out.print("El entrenador enseña AquaFitness (0: NO) (1: SÍ): ");
                            respuestaDisciplinas = scanner.nextLine();
                            
                            if( respuestaDisciplinas.equals("1") ) {
                                System.out.println("AquaFitness: Sí");
                                setAquaFitness(true);
                            } else {
                                System.out.println("AquaFitness: No");
                                setAquaFitness(false);           
                            }

                            utilidades.pausa();
                        }
                        case 6 -> {
                            utilidades.limpiarPantalla();
                            System.out.print("El entrenador enseña TRX (0: NO) (1: SÍ): ");
                            respuestaDisciplinas = scanner.nextLine();
                            
                            if( respuestaDisciplinas.equals("1") ) {
                                System.out.println("TRX: Sí");
                                setTrx(true);
                            } else {
                                System.out.println("TRX: No");
                                setTrx(false);            
                            }

                            utilidades.pausa();
                        }
                        case 7 -> {
                            utilidades.limpiarPantalla();
                            System.out.print("El entrenador enseña Spinning (0: NO) (1: SÍ): ");
                            respuestaDisciplinas = scanner.nextLine();
                            
                            if( respuestaDisciplinas.equals("1") ) {
                                System.out.println("Spinning: Sí");
                                setSpinning(true);
                            } else {
                                System.out.println("Spinning: No");
                                setSpinning(false);         
                            }

                            utilidades.pausa();
                        }
                        case 8 -> {
                            utilidades.limpiarPantalla();
                            System.out.print("El entrenador enseña Cross Fit(0: NO) (1: SÍ): ");
                            respuestaDisciplinas = scanner.nextLine();
                            
                            if( respuestaDisciplinas.equals("1") ) {
                                System.out.println("Cross Fit: Sí");
                                setCrossFit(true);
                            } else {
                                System.out.println("Cross Fit: No");
                                setCrossFit(false);
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

        public boolean isZumba() {
            return zumba;
        }

        public void setZumba(boolean zumba) {
            this.zumba = zumba;
        }

        public boolean isHiit() {
            return hiit;
        }

        public void setHiit(boolean hiit) {
            this.hiit = hiit;
        }

        public boolean isPilates() {
            return pilates;
        }

        public void setPilates(boolean pilates) {
            this.pilates = pilates;
        }

        public boolean isBoxeo() {
            return boxeo;
        }

        public void setBoxeo(boolean boxeo) {
            this.boxeo = boxeo;
        }

        public boolean isAquaFitness() {
            return aquaFitness;
        }

        public void setAquaFitness(boolean aquaFitness) {
            this.aquaFitness = aquaFitness;
        }

        public boolean isTrx() {
            return trx;
        }

        public void setTrx(boolean trx) {
            this.trx = trx;
        }

        public boolean isSpinning() {
            return spinning;
        }

        public void setSpinning(boolean spinning) {
            this.spinning = spinning;
        }

        public boolean isCrossFit() {
            return crossFit;
        }

        public void setCrossFit(boolean crossFit) {
            this.crossFit = crossFit;
        }
    }
}