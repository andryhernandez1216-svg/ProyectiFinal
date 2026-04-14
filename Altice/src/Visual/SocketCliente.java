package Visual;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class SocketCliente {
    private static final String IP = "localhost";
    private static final int PUERTO = 5000;

    public static boolean enviarDatos(String tipo, ArrayList<?> lista) {
        try (Socket s = new Socket(IP, PUERTO);
             ObjectOutputStream salida = new ObjectOutputStream(s.getOutputStream())) {
            
            salida.flush(); 
            ObjectInputStream entrada = new ObjectInputStream(s.getInputStream());
            
            salida.writeObject(tipo);
            salida.writeObject("GUARDAR");
            salida.writeObject(lista);
            
            Object respuesta = entrada.readObject();
            return respuesta != null && respuesta.equals("OK");
            
        } catch (Exception e) {
            System.err.println("Error en enviarDatos: " + e.getMessage());
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> ArrayList<T> recibirDatos(String tipo) {
        try (Socket s = new Socket(IP, PUERTO);
             ObjectOutputStream salida = new ObjectOutputStream(s.getOutputStream())) {
            
            salida.flush();
            ObjectInputStream entrada = new ObjectInputStream(s.getInputStream());
            
            salida.writeObject(tipo);
            salida.writeObject("CARGAR");
            
            Object lista = entrada.readObject();
            return (lista instanceof ArrayList) ? (ArrayList<T>) lista : new ArrayList<>();
            
        } catch (Exception e) {
            System.err.println("Error al recibir " + tipo + ": " + e.getMessage());
            return new ArrayList<>(); 
        }
    }
}
