package Visual;

import Ligca.SistemaAltice;

public class GestionSistema {
    private static SistemaAltice instancia;

    public static SistemaAltice getInstancia() {
        if (instancia == null) {
            // Ahora llamamos al constructor vacío que definimos
            instancia = new SistemaAltice();
        }
        return instancia;
    }
}