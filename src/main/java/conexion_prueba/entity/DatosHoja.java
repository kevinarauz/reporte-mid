package conexion_prueba.entity;

import java.util.List;
import java.util.Map;

public class DatosHoja {
    private String nombreHoja;
    private List<Map<String, Object>> registros;

    public DatosHoja(String nombreHoja, List<Map<String, Object>> registros) {
        this.nombreHoja = nombreHoja;
        this.registros = registros;
    }

    public String getNombreHoja() {
        return nombreHoja;
    }

    public List<Map<String, Object>> getRegistros() {
        return registros;
    }
}
