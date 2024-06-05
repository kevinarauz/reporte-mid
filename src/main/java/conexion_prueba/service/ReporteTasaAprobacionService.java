package conexion_prueba.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import conexion_prueba.repository.RepositoryTasaAprobacion;
import conexion_prueba.utils.GenerarExcel;;

@Service
public class ReporteTasaAprobacionService {

	private static final Logger log = LoggerFactory.getLogger(ReporteTasaAprobacionService.class);

	ExcelReader excelReader = new ExcelReader();
	GenerarExcel generarExcel = new GenerarExcel();
	
	@Autowired
	RepositoryTasaAprobacion RepositoryTasaAprobacion;
	
	public List<Map<String, Object>> crearDatosPruebasTasaAprobacion() {
		List<Map<String, Object>> registros = new ArrayList<>();
		Map<String, Object> registro1 = new HashMap<>();
		
		registro1.put("TID", "CA460354");
		registro1.put("MID", "7100087911");
		registro1.put("CIUDAD", "GUAYAQUIL");

		registros.add(registro1);
		
		Map<String, Object> registro2 = new HashMap<>();
		registro2.put("TID", "CA460282");
		registro2.put("MID", "7200115597");
		registro2.put("CIUDAD", "QUITO");

		registros.add(registro2);
		return registros;
	}
	
	public void consultaTasaAprobacion(String nombreArchivo) throws IOException {
		List<String> excludedKeys = Arrays.asList("");
		// Simulación de la obtención de registros
		//List<Map<String, Object>> registros = crearDatosPruebasTasaAprobacion();
		List<Map<String, Object>> registros = RepositoryTasaAprobacion.consultaTasaAprobacion();

		SXSSFWorkbook workbook = new SXSSFWorkbook();
		generarExcel.generaHoja(workbook, "reporte", registros, excludedKeys);

		// Escribir a archivo
		try (FileOutputStream outputStream = new FileOutputStream(nombreArchivo + ".xlsx")) {
			workbook.write(outputStream);
		} finally {
			workbook.dispose(); // Liberar recursos de SXSSFWorkbook
		}
	}
}
