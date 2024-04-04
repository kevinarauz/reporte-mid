package conexion_prueba.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
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
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
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
	
	public List<Map<String, Object>> reporteCentrosComercialesDebit(String usuario) {
        Timestamp now = new Timestamp(new Date().getTime());

        String sql = "EXEC sp_reportes_r_ProcesoCentrosComerciales_debitAct @FECHA = ?, @USUARIO = ?";
        List<Map<String, Object>> result = jdbcDfreportes.queryForList(sql, now, usuario);

        return result;
    }
	
	public List<Map<String, Object>> reporteCentrosComercialesNew(String usuario) {
        Timestamp now = new Timestamp(new Date().getTime());

        String sql = "EXEC sp_reportes_r_ProcesoCentrosComerciales_NEW @FECHA = ?, @USUARIO = ?";
        List<Map<String, Object>> result = jdbcDfreportes.queryForList(sql, now, usuario);

        return result;
    }
	
	public List<Map<String, Object>> reporteCentrosComercialesCredito(String usuario) {
        Timestamp now = new Timestamp(new Date().getTime());

        String sql = "EXEC SP_PROCESOCENTROSCOMERCIALESCREDITO_ACT @FECHA = ?, @USUARIO = ?";
        List<Map<String, Object>> result = jdbcDfreportes.queryForList(sql, now, usuario);

        return result;
    }
	
	public int insertarNivelesServicios(Map<String, Object> reporte) {
        String sql = "INSERT INTO tmpnivelesservicio " +
                     "(Fecha, Inicia, Finaliza, Duracion, Motivo, Contacto_Datafast, Contacto_Emisor, Solucion) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            return jdbcDfreportes.update(sql, new PreparedStatementSetter() {
                @SuppressWarnings("deprecation")
				@Override
                public void setValues(PreparedStatement ps) throws SQLException {
                	try {
                		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
						Date fechaConvertida = format.parse(reporte.get("Fecha").toString());
						ps.setDate(1, (java.sql.Date) fechaConvertida);
					} catch (ParseException e) {
						log.error("Error al parsear la fecha: {}", e.getMessage());
	                    throw new SQLException("Error al parsear la fecha", e);
					}
                    ps.setString(2, reporte.get("Inicia").toString());
                    ps.setString(3, reporte.get("Finaliza").toString());
                    ps.setString(4, reporte.get("Duración").toString());
                    ps.setString(5, reporte.get("Motivo").toString());
                    ps.setString(6, reporte.get("Contacto Datafast").toString());
                    ps.setString(7, reporte.get("Contacto Emisor").toString());
                    ps.setString(8, reporte.get("Solución").toString());
                }
            });
        } catch (Exception e) {
            log.error("Error al insertar reporte: {}", e.getMessage());
            return 0;
        }
    }
	
}
