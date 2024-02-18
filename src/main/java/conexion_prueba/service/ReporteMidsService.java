package conexion_prueba.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import conexion_prueba.repository.RepositoryReporteMids;

@Service
public class ReporteMidsService {
	@Autowired
	RepositoryReporteMids repositoryReporteMids;

	public List<Map<String, Object>> getJm() {

		List<Map<String, Object>> registros = null;

		registros = repositoryReporteMids.getJm();

		return registros != null ? registros : new ArrayList<>();
	}

	public List<Map<String, Object>> consultaMidActivosActualizados() {

		List<Map<String, Object>> registros = null;

		String usuario = "JMUNOZ";

		registros = repositoryReporteMids.consultaMidActivosActualizados(usuario);

		return registros != null ? registros : new ArrayList<>();
	}

	public List<Map<String, Object>> crearDatosPruebasMids() {
		List<Map<String, Object>> registros = new ArrayList<>();
		Map<String, Object> registro1 = new HashMap<>();
		registro1.put("TID", "00112001");
		registro1.put("FECHA_ASIGNACION", "2000-01-01");
		registro1.put("MID", "2000001121");
		registro1.put("COMERCIO", "SUPERMAXI TUMBACO");
		registro1.put("DIRECCION", "VIA INTEREOCEANICA SN Y G SUAREZ");
		registro1.put("CIUDAD", "QUITO");
		registro1.put("RUC", "1790016919001");
		registro1.put("TELEFONO", null);
		registro1.put("CORREO", null);
		registro1.put("MARCA", "   ");
		registro1.put("MODELO", "CAJA-REG");
		registro1.put("DISPOSITIVO", "C");
		registro1.put("POLITICA_EQUIPO", "V");
		registro1.put("COMUNICACION", "DIAL");
		registro1.put("FECHA_ACTUAL", "2024-02-17T21:22:15.333+0000");
		registro1.put("USUARIO", "JMUNOZ");
		registro1.put("ACTIVO", "ACT");
		registro1.put("MCC", "5411");
		registro1.put("GIRO_NEGOCIO", "ABARROTES SUPERMERCADOS");
		registro1.put("COD_BCO_DINERS", "532508");
		registro1.put("ADQMC", "BANCO GUAYAQUIL  BANCO PICHINCHA  PACIFICARD");
		registro1.put("ADQVISA", "BANCO PICHINCHA");
		registro1.put("INTEROPERABILIDAD", "AUSTRO - MEDIANET");
		registro1.put("ADQMCD", "BANCO GUAYAQUIL  PACIFICARD");
		registro1.put("ADQVSD", "BANCO PICHINCHA");

		registros.add(registro1);

		Map<String, Object> registro2 = new HashMap<>();
		registro2.put("TID", "00112002");
		registro2.put("FECHA_ASIGNACION", "2000-01-01");
		registro2.put("MID", "2000001121");
		registro2.put("COMERCIO", "SUPERMAXI TUMBACO");
		registro2.put("DIRECCION", "VIA INTEREOCEANICA SN Y G SUAREZ");
		registro2.put("CIUDAD", "QUITO");
		registro2.put("RUC", "1790016919001");
		registro2.put("TELEFONO", null);
		registro2.put("CORREO", null);
		registro2.put("MARCA", "   ");
		registro2.put("MODELO", "CAJA-REG");
		registro2.put("DISPOSITIVO", "C");
		registro2.put("POLITICA_EQUIPO", "V");
		registro2.put("COMUNICACION", "DIAL");
		registro2.put("FECHA_ACTUAL", "2024-02-17T21:22:15.333+0000");
		registro2.put("USUARIO", "JMUNOZ");
		registro2.put("ACTIVO", "ACT");
		registro2.put("MCC", "5411");
		registro2.put("GIRO_NEGOCIO", "ABARROTES SUPERMERCADOS");
		registro2.put("COD_BCO_DINERS", "532508");
		registro2.put("ADQMC", "BANCO GUAYAQUIL  BANCO PICHINCHA  PACIFICARD");
		registro2.put("ADQVISA", "BANCO PICHINCHA");
		registro2.put("INTEROPERABILIDAD", "AUSTRO - MEDIANET");
		registro2.put("ADQMCD", "BANCO GUAYAQUIL  PACIFICARD");
		registro2.put("ADQVSD", "BANCO PICHINCHA");

		registros.add(registro2);
		return registros;
	}

	public void generaExcelDeConsulta(String nombreArchivo) throws IOException {
		// List<Map<String, Object>> registros =
		// repositoryReporteMids.consultaMidActivosActualizados("USUARIO");
		// Simula la obtención de registros de alguna fuente
		List<Map<String, Object>> registros = crearDatosPruebasMids();
		generaExcelXlsx("Reportes Mids", registros);
	}

	public void generaExcelXlsx(String nombreArchivo, List<Map<String, Object>> registros) throws IOException {
		// Usando SXSSFWorkbook para manejar grandes volúmenes de filas
		SXSSFWorkbook workbook = new SXSSFWorkbook();
		Sheet sheet = workbook.createSheet("Datos");
		
		Calendar fecha = Calendar.getInstance();
        int anio = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH) + 1;
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        String hoy=""+dia+"-"+mes+"-"+anio;

		// Crear fila de encabezado
		Row headerRow = sheet.createRow(0);
		if (!registros.isEmpty()) {
			int cellIndex = 0;
			for (String key : registros.get(0).keySet()) {
				Cell cell = headerRow.createCell(cellIndex++);
				cell.setCellValue(key);
			}

			// Crear las filas de datos
			int rowIndex = 1;
			for (Map<String, Object> registro : registros) {
				Row row = sheet.createRow(rowIndex++);
				cellIndex = 0;
				for (String key : registro.keySet()) {
					Cell cell = row.createCell(cellIndex++);
					Object value = registro.get(key);
					if (value instanceof String) {
						cell.setCellValue((String) value);
					} else if (value instanceof Number) {
						cell.setCellValue(((Number) value).doubleValue());
					} else if (value != null) {
						cell.setCellValue(value.toString());
					} else {
						cell.setCellValue("");
					}
				}
			}
		}

		// Ajustar el tamaño de todas las columnas para que se ajusten al contenido
		for (int i = 0; i < headerRow.getLastCellNum(); i++) {
			sheet.autoSizeColumn(i);
		}

		// Escribir el archivo
		try (FileOutputStream outputStream = new FileOutputStream(nombreArchivo +" - "+hoy+".xlsx")) {
			workbook.write(outputStream);
		} finally {
			workbook.dispose(); // Liberar recursos utilizados por SXSSFWorkbook
		}
	}

}
