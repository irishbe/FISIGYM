
package fisigym.clases;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Ciudad {
    private Distrito distrito = new Distrito();
    private int idCiudad;
    private String nombreCiudad;
    
    final Scanner scanner = new Scanner(System.in);
    //contructor 
    public Ciudad(int idCiudad, String nombreCiudad){
        
        this.idCiudad = idCiudad;
        this.nombreCiudad = nombreCiudad;
        
    }

    public Ciudad() {
        
    }
    
    
    @Override
    public String toString() {
        return  idCiudad + "><" + nombreCiudad;
    }
    
    public void registrarCiudad(){
        int opcion=0;
        System.out.println("\n------------------- REGISTRAR CIUDAD -------------------");
        System.out.print("Nombre de ciudad:  "); nombreCiudad  = scanner.nextLine();
        idCiudad = (int)(Math.random() * 1000);
        scanner.nextLine();
        System.out.println("---------------------------------------------------------");
        do{
            distrito.registrarDistrito(idCiudad);
            System.out.print("Desea ingresar otro distrito? ( si = 1/ no = 0) : "); opcion = scanner.nextInt();
        }while(opcion == 1 );
       
         
        this.guardarCiudad();
        this.guardarIdsEnArchivo();
        
    }
    
    public void guardarIdsEnArchivo() {
    
    File archivoIds = new File("ids_ciudades.txt");
    try (BufferedReader archivoCiudades = new BufferedReader(new FileReader("ciudades.txt"));
         BufferedWriter archivoIdsWriter = new BufferedWriter(new FileWriter(archivoIds))) {

        String line;
        while ((line = archivoCiudades.readLine()) != null) {
            
            String[] datos = line.split("><");
            String idCiudadGuardada = datos[0]; 

            
            archivoIdsWriter.write(idCiudadGuardada);
            archivoIdsWriter.newLine(); 

          
            
        }

        

    } catch (IOException e) {
        System.out.println("Ocurrio un error al leer/escribir los archivos");
    }
}

    public void guardarCiudad(){
        var utilidades = new Utilidades();
        
        try (BufferedWriter archivoCiudades = new BufferedWriter(new FileWriter("ciudades.txt", true))) {
            
            archivoCiudades.write(this.toString());
            archivoCiudades.newLine();
            
            utilidades.limpiarPantalla();
            System.out.println("\n¡Ciudad registrado con exito!");
            utilidades.pausa();
            
        } catch (IOException e) {
            System.out.println("Ocurrió un error al registrar la ciudad" );
        }
    }
    
    public void leerCiudades(){
        try (BufferedReader archivoCiudades = new BufferedReader( new FileReader("ciudades.txt") )){
            String line;
            while((line = archivoCiudades.readLine()) != null){
                String[] datos = line.split("><");
                String idCiudadGuardada = datos[0];
                String nombreCiudadGuardada = datos[1];
                System.out.println("id: "+ idCiudadGuardada + "  " + "Nombre : "+ nombreCiudadGuardada);
            }
          
        }
        catch(IOException e){
            System.out.println("Ocurrio un error al leer"  );
        }
        
    }
    
    public void actualizarCiudad(){
        
        boolean ciudadEncontrada= false;
        String nuevoNombreCiudad;
        String idCiudadIngresada;
        
        System.out.print("Ingrese el id de la ciudad : ");idCiudadIngresada =scanner.nextLine();
        
        List<String> ciudades = new ArrayList<>();
        
        try (BufferedReader archivoCiudades = new BufferedReader( new FileReader("ciudades.txt") )){
            String line;
            while((line = archivoCiudades.readLine()) != null){
                String[] datos = line.split("><");
                String idCiudadGuardada = datos[0];
                String nombreCiudadGuardada = datos[1];
                if(idCiudadGuardada.equals(idCiudadIngresada) ){
                    System.out.println("Ingrese el nuevo nombre de la ciudad  : ");nuevoNombreCiudad = scanner.nextLine();
                    ciudades.add(idCiudadGuardada + "><" + nuevoNombreCiudad);
                    ciudadEncontrada = true; 
                }
                else {
                    ciudades.add(line);
                }
                
            }
          
        }
        catch(IOException e){
            System.out.println("Ocurrio un error al iniciar leer el mensaje" );
        }
        
        if(ciudadEncontrada){
            try(BufferedWriter archivoCiudades = new BufferedWriter(new FileWriter("ciudades.txt"))){
                for (String ciudad : ciudades) {
                archivoCiudades.write(ciudad);
                archivoCiudades.newLine(); 
            }
            System.out.println("La ciudad ha sido actualizada exitosamente.");
        } catch (IOException e) {
            System.out.println("Ocurrio un error al actualizar la ciudad.");
            }
            
        }
        else {
            System.out.println("No se encontro una ciudad con el ID proporcionado.");
        }
    }
    
    public void eliminarCiudad(){
        
        boolean ciudadEncontrada = false;
        String idCiudadIngresada;
        System.out.println("Ingrese el id de la ciudad a eliminar : ");idCiudadIngresada = scanner.nextLine();
        List<String> ciudades = new ArrayList<>();
        
        try (BufferedReader archivoCiudades = new BufferedReader( new FileReader("ciudades.txt") )){
            String line;
            while((line = archivoCiudades.readLine()) != null){
                String[] datos = line.split("><");
                String idCiudadGuardada = datos[0];
                String nombreCiudadGuardada = datos[1];
                if(idCiudadGuardada.equals(idCiudadIngresada) ){
                    ciudadEncontrada = true; 
                }
                else {
                    ciudades.add(line);
                }
                
            }
          
        }
        catch(IOException e){
            System.out.println("Ocurrio un error al iniciar leer el mensaje" );
        }
        if(ciudadEncontrada){
            try(BufferedWriter archivoCiudades = new BufferedWriter(new FileWriter("ciudades.txt"))){
                for(String ciudad : ciudades){
                archivoCiudades.write(ciudad);
                archivoCiudades.newLine();
                }
                System.out.println("La ciudad se elimino correctamente");
            }
            catch(IOException e){
                System.out.println("Ocurrio un error al eliminar la ciudad.\n" + e.getMessage());
            }
            
        }
         else {
            System.out.println("No se encontro una ciudad con el ID proporcionado.");
        }
        
        
        distrito.eliminarDistritoPorCiudad(idCiudadIngresada);
    }
    
    

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
    
}

