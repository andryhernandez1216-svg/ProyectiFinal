package Visual;

import Ligca.SistemaAltice;

public class GestionSistema {
    private static SistemaAltice instancia;

    public static SistemaAltice getInstancia() {
        if (instancia == null) {
            instancia = new SistemaAltice();
        }
        return instancia;
    }
}