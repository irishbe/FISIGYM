
package fisigym.clases;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class  Distrito {
    
    final Scanner scanner = new Scanner(System.in);
    
    int idDistrito;
    String nombreDistrito;
    int idCiudad;
    
    
    
    
 
    

    public Distrito() {
    }
    
    @Override
    public String toString() {
        return  idDistrito + "><" + idCiudad + "><" + nombreDistrito;
    }
    
    public void registrarDistrito(int idCiudades){
        
        System.out.println("\n------------------- REGISTRAR CIUDAD -------------------");
        System.out.print("Nombre de ciudad:  "); nombreDistrito  = scanner.nextLine();
        idDistrito = (int)(Math.random() * 1000);
        idCiudad  = idCiudades;
        scanner.nextLine();
        System.out.println("---------------------------------------------------------");
        
         
        this.guardarDistrito();
        //this.guardarIdsEnArchivo();
        
    }
    
    public void guardarDistrito(){
        var utilidades = new Utilidades();
        
        try (BufferedWriter archivoCiudades = new BufferedWriter(new FileWriter("distritos.txt", true))) {
            
            archivoCiudades.write(this.toString());
            archivoCiudades.newLine();
            
            utilidades.limpiarPantalla();
            System.out.println("\n¡Distrito registrado con exito!");
            utilidades.pausa();
            
        } catch (IOException e) {
            System.out.println("Ocurrió un error al registrar el distrito" );
        }
    }
    
    /*public void guardarIdsDistrito() {
    
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
}*/
    
    public void leerDistrito(){
        try (BufferedReader archivoDistritos = new BufferedReader( new FileReader("distritos.txt") )){
            String line;
            while((line = archivoDistritos.readLine()) != null){
                String[] datos = line.split("><");
                String idDistritoGuardado = datos[0];
                String nombreDistritoGuardado = datos[1];
                String idCiudadGuardada = datos[2];
                System.out.println("idDis: "+ idDistritoGuardado  +  "Nombre :" + nombreDistritoGuardado + "idCiudad : " + idCiudadGuardada);
            }
          
        }
        catch(IOException e){
            System.out.println("Ocurrio un error al leer"  );
        }
        
    }
    
    public void actualizarDistrito(){
        
        boolean distritoEncontrado= false;
        String nuevoNombreDistrito;
        String idDistritoIngresado;
        
        System.out.print("Ingrese el id del distrito : ");idDistritoIngresado =scanner.nextLine();
        
        List<String> distritos = new ArrayList<>();
        
        try (BufferedReader archivoDistritos = new BufferedReader( new FileReader("distritos.txt") )){
            String line;
            while((line = archivoDistritos.readLine()) != null){
                String[] datos = line.split("><");
                String idDistritoGuardado = datos[0];
                String nombreDistritoGuardado = datos[1];
                if(idDistritoGuardado.equals(idDistritoIngresado) ){
                    System.out.println("Ingrese el nuevo nombre del distrito  : ");nuevoNombreDistrito= scanner.nextLine();
                    distritos.add(idDistritoGuardado + "><" + nuevoNombreDistrito);
                    distritoEncontrado = true; 
                }
                else {
                    distritos.add(line);
                }
                
            }
          
        }
        catch(IOException e){
            System.out.println("Ocurrio un error al iniciar leer el mensaje" );
        }
        
        if(distritoEncontrado){
            try(BufferedWriter archivoDistrito = new BufferedWriter(new FileWriter("distrito.txt"))){
                for (String distrito : distritos) {
                archivoDistrito.write(distrito);
                archivoDistrito.newLine(); 
            }
            System.out.println("El distrito ha sido actualizado exitosamente.");
        } catch (IOException e) {
            System.out.println("Ocurrio un error al actualizar ");
            }
            
        }
        else {
            System.out.println("No se encontro el distrito con el ID proporcionado.");
        }
    }
    
    public void eliminarDistrito(){
        
        boolean distritoEncontrado = false;
        String idDistritoIngresado;
        System.out.println("Ingrese el id del distrito a eliminar : ");idDistritoIngresado = scanner.nextLine();
        List<String> distritos = new ArrayList<>();
        
        try (BufferedReader archivoDistritos = new BufferedReader( new FileReader("distritos.txt") )){
            String line;
            while((line = archivoDistritos.readLine()) != null){
                String[] datos = line.split("><");
                String idDistritosGuardado = datos[0];
                String nombreDistritoGuardado = datos[1];
                if(idDistritosGuardado.equals(idDistritoIngresado) ){
                    distritoEncontrado = true; 
                }
                else {
                    distritos.add(line);
                }
                
            }
          
        }
        catch(IOException e){
            System.out.println("Ocurrio un error al iniciar leer el mensaje" );
        }
        if(distritoEncontrado){
            try(BufferedWriter archivoDistritos = new BufferedWriter(new FileWriter("distritos.txt"))){
                for(String distrito : distritos){
                archivoDistritos.write(distrito);
                archivoDistritos.newLine();
                }
                System.out.println("El distrito se elimino correctamente");
            }
            catch(IOException e){
                System.out.println("Ocurrio un error al eliminar el distrito." );
            }
            
        }
         else {
            System.out.println("No se encontro el distrito con el ID proporcionado.");
        }
        
    }
    
    public void eliminarDistritoPorCiudad(String idCiudades){
        
        boolean distritoEncontrado = false;
        
        
        List<String> distritos = new ArrayList<>();
         
        try (BufferedReader archivoDistritos = new BufferedReader( new FileReader("distritos.txt") )){
            String line;
            while((line = archivoDistritos.readLine()) != null){
                String[] datos = line.split("><");
                String idCiudadGuardada = datos[1];
                
                if(idCiudadGuardada.equals(idCiudades) ){
                    distritoEncontrado = true; 
                }
                else {
                    distritos.add(line);
                }
                
            }
          
        }
        catch(IOException e){
            System.out.println("Ocurrio un error al iniciar leer el mensaje" );
        }
        if(distritoEncontrado){
            try(BufferedWriter archivoDistritos = new BufferedWriter(new FileWriter("distritos.txt"))){
                for(String distrito : distritos){
                archivoDistritos.write(distrito);
                archivoDistritos.newLine();
                }
                
            }
            catch(IOException e){
                System.out.println("Ocurrio un error al eliminar el distrito." );
            }
            
        }
         else {
            System.out.println("No se encontro el distrito con el ID proporcionado.");
        }
        
    }

    public int getIdDistrito() {
        return idDistrito;
    }

    public void setIdDistrito(int idDistrito) {
        this.idDistrito = idDistrito;
    }

    public String getNombreDistrito() {
        return nombreDistrito;
    }

    public void setNombreDistrito(String nombreDistrito) {
        this.nombreDistrito = nombreDistrito;
    }

    public int getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(int idCiudad) {
        this.idCiudad = idCiudad;
    }
    
    
}

