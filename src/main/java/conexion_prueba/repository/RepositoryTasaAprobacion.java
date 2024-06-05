package conexion_prueba.repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RepositoryTasaAprobacion {
	
	private static final Logger log= LoggerFactory.getLogger(RepositoryTasaAprobacion.class);

	@Autowired 
    @Qualifier("jdbcDfreportes")
    protected JdbcTemplate jdbcDfreportes;
	
	private List<Map<String, Object>> ejecutarConsultaParametrizada(String consulta, JdbcTemplate jdbcTemplate, Object... params) {
        List<Map<String, Object>> resultado = new ArrayList<>();
        try {
            resultado = jdbcTemplate.queryForList(consulta, params);
        } catch (Exception e) {
            log.error("Error en ejecutarConsultaParametrizada {}", e.getMessage());
            
        }
        return resultado;
    }	
	
	public List<Map<String, Object>> consultaTasaAprobacion() {
        Timestamp now = new Timestamp(new Date().getTime());

        String sql = "EXEC sp_MidActivos_Caja_Act @FECHA = ?";
        List<Map<String, Object>> result = jdbcDfreportes.queryForList(sql, now);

        return result;
    }
	
	
}
