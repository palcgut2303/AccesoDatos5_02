/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.basex.core.BaseXException;
import org.basex.core.Context;
import org.basex.core.cmd.CreateDB;
import org.basex.core.cmd.ShowUsers;
import org.basex.core.cmd.XQuery;

/**
 *
 * @author manana
 */
public class Controlador {

    private Context contexto;
    private String directorioBD = "src/recurso/coches.xml";

    public void crearBD() {
        if (contexto == null) {
            contexto = new Context();

            try {
                CreateDB baseDatos = new CreateDB("Coches", directorioBD);

                baseDatos.execute(contexto);

                System.out.print(new ShowUsers().execute(contexto));
            } catch (BaseXException ex) {
                System.out.println("-->LA BASE DE DATOS NO HA PODIDO CREARSE:");
                System.err.println(ex);
            }
        }

    }
    
    public String consultaCoches(){
        try {
            if (contexto == null) {
                crearBD();
            }
            String xq = leerXQueryDesdeArchivo("src/script/consultaCochesCordoba2012.xq");
            String resultado = new XQuery(xq).execute(contexto);
            return resultado;
        } catch (BaseXException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    
    public String consultaCochesMadrid(){
        try {
            if (contexto == null) {
                crearBD();
            }
            String xq = leerXQueryDesdeArchivo("src/script/consultaCochesMadrid.xq");
            String resultado = new XQuery(xq).execute(contexto);
            return resultado;
        } catch (BaseXException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    
     public String consultaNumCoches(){
        try {
            if (contexto == null) {
                crearBD();
            }
            String xq = leerXQueryDesdeArchivo("src/script/consultaNumCoches.xq");
            String resultado = new XQuery(xq).execute(contexto);
            return resultado;
        } catch (BaseXException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
     
      public String consultaMaxPrecio(){
        try {
            if (contexto == null) {
                crearBD();
            }
            String xq = leerXQueryDesdeArchivo("src/script/consultaPrecioMax.xq");
            String resultado = new XQuery(xq).execute(contexto);
            return resultado;
        } catch (BaseXException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    
    public static String leerXQueryDesdeArchivo(String rutaArchivo) {
        try {
            // Lee todo el contenido del archivo como una cadena de texto
            return new String(Files.readAllBytes(Paths.get(rutaArchivo)));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
