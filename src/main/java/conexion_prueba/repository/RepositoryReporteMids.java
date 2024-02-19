package conexion_prueba.repository;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class RepositoryReporteMids {
	private static final Logger log= LoggerFactory.getLogger(RepositoryReporteMids.class);

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
	public List<Map<String, Object>> getJm() {
        String sql = "select * from jm";
        log.info("getJm {} ", sql);
        List<Map<String, Object>> resultado = ejecutarConsultaParametrizada(sql, jdbcDfreportes);
        if (!resultado.isEmpty()) {
            return resultado;
        } else {
            return new ArrayList<>();        }
    }

	public List<Map<String, Object>> consultaMidActivosActualizados(String usuario) {
        Timestamp now = new Timestamp(new Date().getTime());

        String sql = "EXEC sp_MidActivos_Caja_Act @FECHA = ?, @USUARIO = ?";
        List<Map<String, Object>> result = jdbcDfreportes.queryForList(sql, now, usuario);

        return result;
    }
	
	public List<Map<String, Object>> reporteInteroperabilidad(String usuario) {
        Timestamp now = new Timestamp(new Date().getTime());

        String sql = "EXEC sp_reportes_r_ProcesoCentrosComerciales_debitAct @FECHA = ?, @USUARIO = ?";
        List<Map<String, Object>> result = jdbcDfreportes.queryForList(sql, now, usuario);

        return result;
    }
	
	
}
