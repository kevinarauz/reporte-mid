package conexion_prueba.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import conexion_prueba.entity.DatosHoja;
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

	public void consultaMidActivosActualizados(String nombreArchivo) throws IOException {
		List<String> excludedKeys = Arrays.asList("FECHA_ACTUAL", "USUARIO", "ACTIVO");
		// Simulación de la obtención de registros
		List<Map<String, Object>> registrosCaja = crearDatosPruebasMids(); // "caja"
		List<Map<String, Object>> registrosPost = crearDatosPruebasMids(); // "post"

		SXSSFWorkbook workbook = new SXSSFWorkbook();
		generaHoja(workbook, "caja", registrosCaja, excludedKeys);
		generaHoja(workbook, "post", registrosPost, excludedKeys);

		// Escribir a archivo
		try (FileOutputStream outputStream = new FileOutputStream(nombreArchivo + ".xlsx")) {
			workbook.write(outputStream);
		} finally {
			workbook.dispose(); // Liberar recursos de SXSSFWorkbook
		}
	}
	
	private List<Map<String, Object>> crearDatosPruebasInteroperabilidad() {
        List<Map<String, Object>> listaRegistros = new ArrayList<>();

        // Registro para Quito
        Map<String, Object> registro1 = new HashMap<>();
        registro1.put("NOMBRE", "C.C. GRANADOS VC");
        registro1.put("TIPO", "EL");
        registro1.put("TOTALES", 247.114990);
        registro1.put("MES", 1);
        registro1.put("AÑO", 2024);
        registro1.put("COM", "N");
        registro1.put("USUARIO", "EALFONZO");
        registro1.put("ACTIVO", "ACT");
        registro1.put("BANCO", "ALL");
        registro1.put("REGIONAL", "QUITO");
        listaRegistros.add(registro1);

        // Registro para Guayaquil
        Map<String, Object> registro2 = new HashMap<>();
        registro2.put("NOMBRE", "C.C. BARTOLOME SERRANO");
        registro2.put("TIPO", "EL");
        registro2.put("TOTALES", 150.00);
        registro2.put("MES", 2);
        registro2.put("ANIO", 2024);
        registro2.put("COM", "N");
        registro2.put("USUARIO", "JPEREZ");
        registro2.put("ACTIVO", "ACT");
        registro2.put("BANCO", "ALL");
        registro2.put("REGIONAL", "GUAYAQUIL");
        listaRegistros.add(registro2);

        // Registro para Cuenca
        Map<String, Object> registro3 = new HashMap<>();
        registro3.put("NOMBRE", "C.C. CUENCA VC");
        registro3.put("TIPO", "VC");
        registro3.put("TOTALES", 300.00);
        registro3.put("MES", 3);
        registro3.put("ANIO", 2024);
        registro3.put("COM", "N");
        registro3.put("USUARIO", "MRAMOS");
        registro3.put("ACTIVO", "INACT");
        registro3.put("BANCO", "ALL");
        registro3.put("REGIONAL", "CUENCA");
        listaRegistros.add(registro3);

        return listaRegistros;
    }
	
	private static final Set<String> nombresPermitidos = new HashSet<>(Arrays.asList(
            "C.C. BARTOLOME SERRANO", "C.C. CORAL CENTRO", "C.C. EL VERGEL", "C.C. LA PRADERA",
            "C.C. LAS ORQUIDEAS", "C.C. MALL DEL RIO", "C.C. MILENIUM PLAZA", "C.C. MIRAFLORES",
            "C.C. MONAY SHOPPING", "C.C. RACAR"));
	
	public List<Map<String, Object>> filtrarYSumarRegistros(String regional, String com, String tipo) {
      //  List<Map<String, Object>> registros = crearDatosPruebasInteroperabilidad();
		List<Map<String, Object>> registros = repositoryReporteMids.reporteInteroperabilidad("JMUNOZ");
        List<Map<String, Object>> registrosFiltrados = new ArrayList<>();

        // Filtro basado en los parámetros proporcionados y nombres permitidos
        for (Map<String, Object> registro : registros) {
            if (nombresPermitidos.contains(registro.get("NOMBRE")) &&
                    regional.equals(registro.get("REGIONAL")) &&
                    com.equals(registro.get("COM")) &&
                    tipo.equals(registro.get("TIPO"))) {
                registrosFiltrados.add(registro);
            }
        }

        // Sumar los totales para cada nombre de C.C. permitido
        Map<String, Double> totalesPorNombre = registrosFiltrados.stream()
                .collect(Collectors.groupingBy(
                        registro -> (String) registro.get("NOMBRE"),
                        Collectors.summingDouble(registro -> (Double) registro.get("TOTALES"))
                ));

        // Convertir los totales en una lista de mapas para el resultado deseado
        List<Map<String, Object>> resultado = new ArrayList<>();
        totalesPorNombre.forEach((nombre, total) -> {
            Map<String, Object> registroSuma = new HashMap<>();
            registroSuma.put("NOMBRE", nombre);
            registroSuma.put("TOTAL", total);
            resultado.add(registroSuma);
        });

        return resultado;
    }
	
	public void reporteInteroperabilidad(String nombreArchivo) throws IOException {
		List<Map<String, Object>> registrosGuayaquil = filtrarYSumarRegistros("GUAYAQUIL", "N", "EL");
		List<String> excludedKeys = Arrays.asList("");
		SXSSFWorkbook workbook = new SXSSFWorkbook();
		generaHoja(workbook, "Gye", registrosGuayaquil, excludedKeys);

		// Escribir a archivo
		try (FileOutputStream outputStream = new FileOutputStream(nombreArchivo + ".xlsx")) {
			workbook.write(outputStream);
		} finally {
			workbook.dispose(); // Liberar recursos de SXSSFWorkbook
		}
	}

	public void generaHoja(SXSSFWorkbook workbook, String nombreHoja, List<Map<String, Object>> registros,
			List<String> excludedKeys) {
		Sheet sheet = workbook.createSheet(nombreHoja);

		// Crear el estilo para el encabezado (opcional, similar a lo mostrado
		// anteriormente)
		// CellStyle headerStyle = workbook.createCellStyle();
		// Configurar headerStyle...

		// Encabezados dinámicos y creación de filas de datos
		Row headerRow = sheet.createRow(0);
		if (!registros.isEmpty()) {
			int cellIndex = 0;
			Map<String, Object> firstRecord = registros.get(0);
			for (String key : firstRecord.keySet()) {
				if (!excludedKeys.contains(key)) {
					Cell cell = headerRow.createCell(cellIndex++);
					cell.setCellValue(key);
					// cell.setCellStyle(headerStyle);
				}
			}

			int rowIndex = 1;
			for (Map<String, Object> registro : registros) {
	            Row row = sheet.createRow(rowIndex++);
	            cellIndex = 0;
	            for (Map.Entry<String, Object> entry : registro.entrySet()) {
	                if (!excludedKeys.contains(entry.getKey())) {
	                    Cell cell = row.createCell(cellIndex++);
	                    Object value = entry.getValue();
	                    if (value != null) {
	                        if (value instanceof Date) {
	                            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	                            cell.setCellValue(dateFormat.format((Date) value));
	                        } else if (value instanceof Double) {
	                            cell.setCellValue((Double) value);
	                        } else if (value instanceof Boolean) {
	                            cell.setCellValue((Boolean) value);
	                        } else {
	                            cell.setCellValue(value.toString());
	                        }
	                    } else {
	                        cell.setCellValue("");
	                    }
	                }
	            }
	        }

			// Ajustar el tamaño de las columnas aquí si se desea
			for (int i = 0; i < headerRow.getLastCellNum(); i++) {
				sheet.autoSizeColumn(i);
			}

		}

	}

}
