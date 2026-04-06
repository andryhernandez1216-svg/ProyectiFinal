package Visual;

import java.util.ArrayList;
import Ligca.SistemaAltice;

public class GestionSistema {
    private static SistemaAltice instancia;

    public static SistemaAltice getInstancia() {
        if (instancia == null) {
            instancia = new SistemaAltice(new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), 
                                         new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        }
        return instancia;
    }
}
