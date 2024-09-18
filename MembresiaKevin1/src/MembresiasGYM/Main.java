/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MembresiasGYM;

/**
 *
 * @author Kevin
 */
public class Main {
    
    public static void main(String [] args){
        
        TiposMembresia diamante = new TiposMembresia("DIAMANTE", "TODAS", "TODAS","Prioridad en clases de alta demanda", "Acceso total", "TODAS");
        TiposMembresia zafiro = new TiposMembresia("ZAFIRO", "Solo gimnasio escogido", "TODAS","Reserva anticipada sin prioridad", "Acceso con descuento", "Solo seleccionados");       
        TiposMembresia esmeralda = new TiposMembresia("ESMERALDA", "Solo gimnasio escogido", "TODAS","Sujeta a disponibilidad general", "No incluido, disponible con pago adicional", "Limitado a eventos comunitarios basicos");       
        diamante.mostrarDatos();        
        zafiro.mostrarDatos();
        esmeralda.mostrarDatos();
    }
    
}
