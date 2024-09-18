/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MembresiasGYM;

/**
 *
 * @author Kevin
 */
public class TiposMembresia {
    
    String nombre;
    String sedes;
    String clases;
    String reservas;
    String piscina_spa;
    String talleres;
    
    public TiposMembresia(String nombre, String sedes, String clases, String reservas, String piscina_spa,String talleres){
        
        this.nombre = nombre;
        this.sedes = sedes;
        this.clases = clases;
        this.reservas = reservas;
        this.piscina_spa = piscina_spa;
        this.talleres = talleres;        
    }
    
    public void mostrarDatos(){
        System.out.println("La membresia Diamante cuenta con: ");
        System.out.println("Nombre: "+nombre);
        System.out.println("Acceso a sedes: "+sedes);
        System.out.println("Acceso a clases: "+clases);
        System.out.println("Reserva de clases: "+reservas);
        System.out.println("Acceso a piscina y spa"+piscina_spa);
        System.out.println("Acceso a talleres: "+talleres);
        System.out.println("\n\n");
    }
    
    
    
}
