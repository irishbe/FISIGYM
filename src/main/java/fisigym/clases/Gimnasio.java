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

    private final Scanner scanner=new Scanner(System.in);
    private final String archivoGimnasio="gimnasios.txt";
    private final Utilidades utilidades=new Utilidades();
    private ServiciosGimnasio serviciosGimnasio;
    // Constructor
    public Gimnasio(int idGimnasio, int idCiudad, int idDistrito, String nombreGimnasio, String direccion, ServiciosGimnasio serviciosGimnasio) {
        this.idGimnasio=idGimnasio;
        this.idCiudad=idCiudad;
        this.idDistrito=idDistrito;
        this.nombreGimnasio=nombreGimnasio;
        this.direccion=direccion;
        this.serviciosGimnasio = new ServiciosGimnasio();
    }

    public Gimnasio(){
        this.serviciosGimnasio = new ServiciosGimnasio();
    }
    
    public class ServiciosGimnasio{
        private boolean piscina;
        private boolean spa;
        private boolean cochera;
        private boolean cafeteria;
        private boolean vestidores;
        private boolean wifi;

        public ServiciosGimnasio(boolean piscina,boolean spa,boolean cochera,boolean cafeteria,boolean vestidores,boolean wifi){
            this.piscina=piscina;
            this.spa=spa;
            this.cochera=cochera;
            this.cafeteria=cafeteria;
            this.vestidores=vestidores;
            this.wifi=wifi;
            
        } 
        public ServiciosGimnasio(){           
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

    // Método toString para formatear el guardado en archivo
    @Override
    public String toString() {
    return idGimnasio+"><"+idCiudad+"><"+idDistrito+"><"+nombreGimnasio+"><"+direccion+"><"+serviciosGimnasio.getPiscina()+"><"+
            serviciosGimnasio.getSpa()+"><"+serviciosGimnasio.getCochera()+"><"+serviciosGimnasio.getCafeteria()+"><"+serviciosGimnasio.getVestidores()+"><"+serviciosGimnasio.getWifi();
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
    
    private void listarDistritosPorCiudad(int idCiudad){
        System.out.println("\n------------------- LISTA DE DISTRITOS -------------------");
        try (BufferedReader archivoDistritos = new BufferedReader(new FileReader("distritos.txt"))){
            String line;
            boolean hayDistritos = false;
            System.out.println("\tID Distrito\tNombres");
            
            while ((line = archivoDistritos.readLine()) != null){
                String[] datos = line.split("><");
                        if (datos.length < 3) {
                            System.out.println("Línea malformada en el archivo: " + line);
                            continue;
                        }
                        
                        int idDistritoGuardado = Integer.parseInt(datos[0]);
                        int idCiudadGuardada = Integer.parseInt(datos[1]);
                        
                        if (idCiudadGuardada == idCiudad){
                            System.out.println("\t" + idDistritoGuardado + "\t\t" + datos[2]);
                            hayDistritos = true;
                        }
            }
            if (!hayDistritos){
                System.out.println("No hay distritos registrados para mostrar.");
                utilidades.pausa();
            }
        } catch(IOException e){
            System.out.println("Ocurrió un error al leer los distritos.");
            utilidades.pausa();
        } catch(NumberFormatException e){
            System.out.println("Error al convertir los IDs. Asegúrate de que el formato es correcto.");
            utilidades.pausa();
        }
    }
    
    private String obtenerNombreCiudad(int idCiudad){
        try (BufferedReader archivoCiudades = new BufferedReader(new FileReader("ciudades.txt"))){
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
    
    private String obtenerNombreDistrito(int idDistrito, int idCiudad) {
        try (BufferedReader archivoDistritos = new BufferedReader(new FileReader("distritos.txt"))){
            String line;
            while((line = archivoDistritos.readLine()) != null){
                String[] datos = line.split("><");
                int idDistritoGuardado = Integer.parseInt(datos[0]);
                int idCiudadGuardada = Integer.parseInt(datos[1]);
                if (idDistritoGuardado == idDistrito && idCiudadGuardada == idCiudad){
                    return datos[2];
                }
            }
        }catch(IOException e){
            System.out.println("Ocurrió un error al leer los distritos.");
        }
        return "Desconocido";
    }

    public void registrarGimnasio(){
        System.out.println("\n------------------- REGISTRAR GIMNASIO -------------------");
        System.out.println("\n                 Presione 0 para regresar                 ");
        String rpta1,rpta2,rpta3,rpta4,rpta5,rpta6;
        Ciudad ciudad = new Ciudad();
        ciudad.listarCiudades();
        
        System.out.print("Ingrese el ID de la ciudad: ");
        idCiudad = scanner.nextInt();
        scanner.nextLine();
        if (idCiudad == 0){
            System.out.println("Regresando al menú anterior...");
            utilidades.pausa();
            return;
        }
        if (idCiudad <= 0){
            System.out.println("El ID de la ciudad no puede ser inválido.");
            utilidades.pausa();
            return;
        }
        
        listarDistritosPorCiudad(idCiudad);
        System.out.print("Ingrese el ID del distrito: ");
        idDistrito = scanner.nextInt();
        scanner.nextLine();
        if (idDistrito == 0){
            System.out.println("Regresando al menú anterior...");
            utilidades.pausa();
            return;
        }
        if (idDistrito <= 0){
            System.out.println("El ID del distrito no puede ser inválido.");
            utilidades.pausa();
            return;
        }
        
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
        
        System.out.print("El gimnasio tiene piscina?");
        rpta1=scanner.nextLine();
        if(rpta1.compareTo("Y")==0){
            System.out.println("Piscina: Si");
            serviciosGimnasio.setPiscina(true);
        }else{
            System.out.println("Piscina: No");
            serviciosGimnasio.setPiscina(false);            
        }
        
        
        System.out.print("El gimnasio tiene spa?");
        rpta2=scanner.nextLine();
        if(rpta2.compareTo("Y")==0){
            System.out.println("Spa: Si");
            serviciosGimnasio.setSpa(true);
        }else{
            System.out.println("Spa: No");
            serviciosGimnasio.setSpa(false);            
        }
        
        
        System.out.print("El gimnasio tiene cochera?");        
        rpta3=scanner.nextLine();
        if(rpta3.compareTo("Y")==0){
            System.out.println("Cochera: Si");
            serviciosGimnasio.setCochera(true);
        }else{
            System.out.println("Cochera: No");
            serviciosGimnasio.setCochera(false);            
        }
        
        
        System.out.print("El gimnasio tiene cafeteria?");        
        rpta4=scanner.nextLine();
        if(rpta4.compareTo("Y")==0){
            System.out.println("cafeteria: Si");
            serviciosGimnasio.setCafeteria(true);
        }else{
            System.out.println("cafeteria: No");
            serviciosGimnasio.setCafeteria(false);            
        }
        
        
        System.out.print("El gimnasio tiene vestidores?");        
        rpta5=scanner.nextLine();
        if(rpta5.compareTo("Y")==0){
            System.out.println("vestidores: Si");
            serviciosGimnasio.setVestidores(true);
        }else{
            System.out.println("vestidores: No");
            serviciosGimnasio.setVestidores(false);            
        }
        
        
        System.out.print("El gimnasio tiene wifi?");        
        rpta6=scanner.nextLine();
        if(rpta6.compareTo("Y")==0){
            System.out.println("wifi: Si");
            serviciosGimnasio.setWifi(true);
        }else{
            System.out.println("wifi: No");
            serviciosGimnasio.setWifi(false);            
        }
        
        idGimnasio = generarIdUnico();
        System.out.println("ID del gimnasio: " + idGimnasio);
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
            System.out.println("Ocurrió un error al registrar el gimnasio.");
            utilidades.pausa();
        }
    }
    
    public void listarGimnasios() {
        System.out.println("\n------------------- LISTA DE GIMNASIOS -------------------");
        
        try(BufferedReader archivoGimnasios = new BufferedReader(new FileReader(archivoGimnasio))){
            String line;
            boolean hayGimnasios=false;
            System.out.println("\tID Gimnasio\tCiudad\t\tDistrito\tNombres\t\t\tDirección");
            while((line = archivoGimnasios.readLine()) !=null){
                String[] datos = line.split("><");
                int idCiudad=Integer.parseInt(datos[1]);
                int idDistrito=Integer.parseInt(datos[2]);
                String nombreCiudad=obtenerNombreCiudad(idCiudad);
                String nombreDistrito=obtenerNombreDistrito(idDistrito, idCiudad);
                System.out.println("\t" + datos[0] + "\t\t" + nombreCiudad + "\t\t" + nombreDistrito + "\t\t" + datos[3] + "\t\t" + datos[4]);
                hayGimnasios=true;
            }
            if (!hayGimnasios){
                System.out.println("No hay gimnasios registrados.");
                utilidades.pausa();
            }
        }catch (IOException e){
            System.out.println("Ocurrió un error al leer los gimnasios.");
            utilidades.pausa();
        }
    }
    public void listarServicios() {
        System.out.println("\n------------------- LISTA DE SERVICIOS -------------------");
        
        try(BufferedReader archivoGimnasios = new BufferedReader(new FileReader(archivoGimnasio))){
            String line;
            boolean hayGimnasios=false;
            System.out.println("\tID Gimnasio\tPiscina\t\tSpa\t\tCochera\t\tCafeteria\tVestidores\tWifi");
            while((line = archivoGimnasios.readLine()) !=null){
                String[] datos = line.split("><");
                int idCiudad=Integer.parseInt(datos[1]);
                int idDistrito=Integer.parseInt(datos[2]);
                String nombreCiudad=obtenerNombreCiudad(idCiudad);
                String nombreDistrito=obtenerNombreDistrito(idDistrito, idCiudad);
                System.out.println("\t" + datos[0] + "\t\t" +datos[5]+"\t\t"+datos[6]+"\t\t"+datos[7]+"\t\t"+datos[8]+"\t\t"+datos[9]+"\t\t"+datos[10]);
                hayGimnasios=true;
            }
            if (!hayGimnasios){
                System.out.println("No hay gimnasios registrados.");
                utilidades.pausa();
            }
        }catch (IOException e){
            System.out.println("Ocurrió un error al leer los gimnasios.");
            utilidades.pausa();
        }
    }


    public void actualizarGimnasio(){
        String idGimnasioIngresada;
        boolean gimnasioEncontrado=false;
        String nuevoNombreGimnasio;
        String nuevaDireccionGimnasio;
        List<String> gimnasios = new ArrayList<>();
        listarGimnasios();
    try (BufferedReader archivoGimnasios = new BufferedReader(new FileReader(archivoGimnasio))) {
        if (archivoGimnasios.readLine() == null) {
            System.out.println("No hay gimnasios registrados.");
            utilidades.pausa();
            return;
        }
    } catch (IOException e) {
        System.out.println("Ocurrió un error al leer los gimnasios.");
        utilidades.pausa();
        return;
    }
    
    scanner.nextLine();
    System.out.print("\nIngrese el ID del gimnasio a actualizar: ");
    idGimnasioIngresada = scanner.nextLine();
    
    try (BufferedReader archivoGimnasios = new BufferedReader(new FileReader(archivoGimnasio))) {
        String line;
        while ((line = archivoGimnasios.readLine()) != null) {
            String[] datos = line.split("><");
            String idGimnasioGuardado = datos[0];
            if (idGimnasioGuardado.equals(idGimnasioIngresada)) {
                System.out.print("\nIngrese el nuevo nombre del gimnasio (deje vacío para mantener el actual): ");
                nuevoNombreGimnasio = scanner.nextLine();

                if (nuevoNombreGimnasio.isEmpty()) {
                    nuevoNombreGimnasio = datos[3];
                }
                
                System.out.print("\nIngrese la nueva dirección del gimnasio (presione enter para mantener la actual): ");
                nuevaDireccionGimnasio = scanner.nextLine();

                if (nuevaDireccionGimnasio.isEmpty()) {
                    if (datos.length > 4) {
                        nuevaDireccionGimnasio = datos[4];
                    } else {
                        nuevaDireccionGimnasio = "";
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
            System.out.println("El gimnasio ha sido actualizado exitosamente.");
            utilidades.pausa();
        } catch (IOException e) {
            System.out.println("Ocurrió un error al actualizar el gimnasio.");
            utilidades.pausa();
        }
    } else {
        System.out.println("No se encontró un gimnasio con el ID proporcionado.");
        utilidades.pausa();
    }
    }
    
    public void eliminarGimnasio(){
        String idGimnasioIngresada;
        boolean gimnasioEncontrado = false;
        List<String> gimnasios = new ArrayList<>();
        listarGimnasios();
        scanner.nextLine();
        System.out.print("\nIngrese el ID del gimnasio a eliminar: ");
        idGimnasioIngresada = scanner.nextLine();
        try (BufferedReader archivoGimnasios = new BufferedReader(new FileReader(archivoGimnasio))){
            String line;
            while((line = archivoGimnasios.readLine()) != null){
                String[] datos = line.split("><");
                String idGimnasioGuardado = datos[0];
                if (idGimnasioGuardado.equals(idGimnasioIngresada)){
                    gimnasioEncontrado = true;
                }else {
                    gimnasios.add(line);
                }
            }
        }catch (IOException e){
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
                System.out.println("El gimnasio ha sido eliminado exitosamente.");
                utilidades.pausa();
            }catch (IOException e){
                System.out.println("Ocurrió un error al eliminar el gimnasio.");
                utilidades.pausa();
            }
        }else {
            System.out.println("No se encontró un gimnasio con el ID proporcionado.");
            utilidades.pausa();
        }
    }
    
    public void menuServicios() {
    System.out.println("Cargando servicios...");
    }

    
    public void menuGimnasio(){
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

                switch (opcion) {
                    case 0 -> {
                        utilidades.limpiarPantalla();
                        System.out.println("Regresando al menú anterior...");
                        utilidades.pausa();
                    }
                    case 1 -> {
                        utilidades.limpiarPantalla();
                        registrarGimnasio();
                        utilidades.pausa();
                    }
                    case 2 -> {
                        utilidades.limpiarPantalla();
                        listarGimnasios();
                        utilidades.pausa();
                    }
                    case 3 -> {
                        utilidades.limpiarPantalla();
                        actualizarGimnasio();
                        utilidades.pausa();
                    }
                    case 4 -> {
                        utilidades.limpiarPantalla();
                        eliminarGimnasio();
                        utilidades.pausa();
                    }
                    case 5 -> {
                        utilidades.limpiarPantalla();
                        listarServicios();
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
                scanner.nextLine();
            }
        } while (opcion != 0);
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
