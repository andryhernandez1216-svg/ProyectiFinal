package Servidor;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import Ligca.*;

public class ServidorAltice {
    private static final int PUERTO = 5000;

    public static void main(String[] args) {
        try (ServerSocket servidor = new ServerSocket(PUERTO)) {
            System.out.println("=== SERVIDOR CENTRAL ALTICE OPERACIONAL ===");

            while (true) {
                Socket cliente = servidor.accept();
                
                ObjectOutputStream salida = new ObjectOutputStream(cliente.getOutputStream());
                salida.flush(); 
                ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());

                try {
                    String tipo = (String) entrada.readObject();
                    String operacion = (String) entrada.readObject();

                    if (operacion.equals("GUARDAR")) {
                        ArrayList<?> lista = (ArrayList<?>) entrada.readObject();
                        guardarYRespaldar(lista, tipo.toLowerCase() + ".dat");
                        salida.writeObject("OK");
                    } 
                    else if (operacion.equals("CARGAR")) {
                        ArrayList<?> listaLeida = leerFichero(tipo.toLowerCase() + ".dat");
                        salida.writeObject(listaLeida);
                    }
                    else if (operacion.equals("RESTAURAR")) {
                        restaurarDesdeUltimoRespaldo(tipo.toLowerCase());
                        salida.writeObject("OK");
                    }

                } catch (Exception e) {
                    System.err.println("Error procesando cliente: " + e.getMessage());
                } finally {
                    cliente.close(); 
                }
            }
        } catch (IOException e) { 
            e.printStackTrace();
        }
    } 

    private static synchronized void guardarYRespaldar(ArrayList<?> lista, String archivo) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(lista);
        }
        
        File carpeta = new File("backups/" + archivo.replace(".dat", ""));
        if (!carpeta.exists()) carpeta.mkdirs();

        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmm").format(new Date());
        Path origen = Paths.get(archivo);
        Path destino = Paths.get(carpeta.getPath() + "/respaldo_" + timestamp + ".dat");
        
        Files.copy(origen, destino, StandardCopyOption.REPLACE_EXISTING);
        System.out.println("Sincronizado: " + archivo + " | Respaldo: " + destino.getFileName());
    }

    private static ArrayList<?> leerFichero(String archivo) {
        File f = new File(archivo);
        
        if (!f.exists()) {
            System.out.println("Archivo " + archivo + " no encontrado. Buscando respaldos...");
            restaurarDesdeUltimoRespaldo(archivo.replace(".dat", ""));
            f = new File(archivo); 
        }

        if (!f.exists()) return new ArrayList<Object>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            return (ArrayList<?>) ois.readObject();
        } catch (Exception e) {
            return new ArrayList<Object>();
        }
    }
    
    private static void restaurarDesdeUltimoRespaldo(String tipo) {
        File carpetaBackups = new File("backups/" + tipo);
        if (!carpetaBackups.exists()) return;

        File[] respaldos = carpetaBackups.listFiles((dir, name) -> name.endsWith(".dat"));
        
        if (respaldos != null && respaldos.length > 0) {
            File ultimo = respaldos[0];
            for (File f : respaldos) {
                if (f.lastModified() > ultimo.lastModified()) {
                    ultimo = f;
                }
            }

            try {
                Files.copy(ultimo.toPath(), Paths.get(tipo + ".dat"), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("(!) ARCHIVO FALTANTE: Se ha restaurado " + tipo + " desde " + ultimo.getName());
            } catch (IOException e) {
                System.err.println("Error en auto-restauracion: " + e.getMessage());
            }
        }
    }
} 