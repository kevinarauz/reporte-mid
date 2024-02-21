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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import conexion_prueba.entity.DatosHoja;
import conexion_prueba.repository.RepositoryReporteMids;

@Service
public class ReporteMidsService {

	private static final Logger log = LoggerFactory.getLogger(ReporteMidsService.class);

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

		// Registro para Guayaquil
		Map<String, Object> registro4 = new HashMap<>();
		registro4.put("NOMBRE", "C.C. BARTOLOME SERRANO");
		registro4.put("TIPO", "EL");
		registro4.put("TOTALES", "150.00");
		registro4.put("MES", 2);
		registro4.put("ANIO", 2024);
		registro4.put("COM", "N");
		registro4.put("USUARIO", "JPEREZ");
		registro4.put("ACTIVO", "ACT");
		registro4.put("BANCO", "ALL");
		registro4.put("REGIONAL", "GUAYAQUIL");
		listaRegistros.add(registro4);

		// Registro para Guayaquil
		Map<String, Object> registro5 = new HashMap<>();
		registro5.put("NOMBRE", "C.C. BARTOLOME SERRANO");
		registro5.put("TIPO", "EL");
		registro5.put("TOTALES", "null");
		registro5.put("MES", 2);
		registro5.put("ANIO", 2024);
		registro5.put("COM", "N");
		registro5.put("USUARIO", "JPEREZ");
		registro5.put("ACTIVO", "ACT");
		registro5.put("BANCO", "ALL");
		registro5.put("REGIONAL", "GUAYAQUIL");
		listaRegistros.add(registro5);

		return listaRegistros;
	}
	private Set<String> nombresPermitidos = new HashSet<>(Arrays.asList(""));
	private static final Set<String> nombresPermitidosGuayaquil = new HashSet<>(Arrays.asList(
            "C.C. ALBAN BORJA",
            "C.C. ALBOCENTRO",
            "C.C. ALHAMBRA",
            "C.C. AVENTURA PLAZA",
            "C.C. BLUE COAST CENTER",
            "C.C. BOCCA",
            "C.C. CITY MALL",
            "C.C. DICENTRO",
            "C.C. GARZOCENTRO MALL",
            "C.C. GRAN MANZANA",
            "C.C. LA PIAZZA CIUDAD CELESTE",
            "C.C. LA PIAZZA SAMBORONDON",
            "C.C. LA ROTONDA",
            "C.C. LA TORRE",
            "C.C. LAGOS PLAZA",
            "C.C. LAGUNA CLUB",
            "C.C. LAS TERRAZAS",
            "C.C. MALL DEL PACIFICO",
            "C.C. MALL DEL SOL",
            "C.C. MALL DEL SUR",
            "C.C. MALL EL FORTIN",
            "C.C. ORO PLAZA",
            "C.C. PASEO SHOPPING BABAHOYO",
            "C.C. PASEO SHOPPING BAHIA",
            "C.C. PASEO SHOPPING DAULE",
            "C.C. PASEO SHOPPING DURAN",
            "C.C. PASEO SHOPPING MACHALA",
            "C.C. PASEO SHOPPING MANTA",
            "C.C. PASEO SHOPPING MILAGRO",
            "C.C. PASEO SHOPPING PENINSULA",
            "C.C. PASEO SHOPPING PLAYAS",
            "C.C. PASEO SHOPPING PORTOVIEJO",
            "C.C. PASEO SHOPPING QUEVEDO",
            "C.C. PASEO SHOPPING VIA DAULE",
            "C.C. PIAZZA CEIBOS",
            "C.C. PIAZZA MACHALA",
            "C.C. PIAZZA VILLA CLUB",
            "C.C. PLAZA MAYOR",
            "C.C. PLAZA NAVONA",
            "C.C. PLAZA QUIL",
            "C.C. PLAZA TRIANGULO",
            "C.C. PLAZA VICTORIA",
            "C.C. POLICENTRO",
            "C.C. RIOCENTRO CEIBOS",
            "C.C. RIOCENTRO EL DORADO",
            "C.C. RIOCENTRO ENTRE RIOS",
            "C.C. RIOCENTRO NORTE",
            "C.C. RIOCENTRO SUR",
            "C.C. SAMBORONDON PLAZA",
            "C.C. SAN MARINO",
            "C.C. TERMINAL TERRESTRE",
            "C.C. UNICENTRO",
            "C.C. VILLAGE PLAZA"
    ));
	private static final Set<String> nombresPermitidosCuenca = new HashSet<>(Arrays.asList(
            "C.C. BARTOLOME SERRANO",
            "C.C. CORAL CENTRO",
            "C.C. EL VERGEL",
            "C.C. LA PRADERA",
            "C.C. LAS ORQUIDEAS",
            "C.C. MALL DEL RIO",
            "C.C. MILENIUM PLAZA",
            "C.C. MIRAFLORES",
            "C.C. MONAY SHOPPING",
            "C.C. RACAR"
    ));
	
	private static final Set<String> nombresPermitidosQuito = new HashSet<>(Arrays.asList(
            "C.C. ATAHUALPA",
            "C.C. CARACOL",
            "C.C. EL BOSQUE",
            "C.C. EL CONDADO",
            "C.C. EL PORTAL",
            "C.C. EL RECREO",
            "C.C. GRANADOS OUTLET",
            "C.C. I#AQUITO",
            "C.C. LA PLAZA",
            "C.C. LAGUNA MALL",
            "C.C. MALL DE LOS ANDES",
            "C.C. MALL EL JARDIN",
            "C.C. MALTERIA",
            "C.C. MEGAMAXI",
            "C.C. MULTIPLAZA",
            "C.C. MULTIPLAZA RIOBAMBA",
            "C.C. PASEO SAN FRANCISCO",
            "C.C. PASEO SHOPPING AMBATO",
            "C.C. PASEO SHOPPING RIOBAMBA",
            "C.C. PASEO SHOPPING ST DOMINGO",
            "C.C. PLAZA CUMBAYA",
            "C.C. PLAZA DE LAS AMERICAS",
            "C.C. PLAZA DEL RANCHO",
            "C.C. QUICENTRO SHOPPING",
            "C.C. QUICENTRO SUR",
            "C.C. RIVER MALL",
            "C.C. SAN LUIS SHOPPING",
            "C.C. SCALA SHOPPING",
            "C.C. VENTURA MALL"
    ));

	public List<Map<String, Object>> filtrarYSumarRegistros(List<Map<String, Object>> registros, String regional, String com, String tipo) {
		//List<Map<String, Object>> registros = crearDatosPruebasInteroperabilidad();
		//List<Map<String, Object>> registros = repositoryReporteMids.reporteInteroperabilidad("JMUNOZ");
		nombresPermitidos = new HashSet<>(Arrays.asList(""));
		List<Map<String, Object>> registrosFiltrados = new ArrayList<>();
		List<Map<String, Object>> registrosNoValidos = new ArrayList<>();
		// Filtro basado en los parámetros proporcionados y nombres permitidos
		// Sumar los totales para cada nombre de C.C. permitido y registrar los que no
		// son válidos

		// Filtro basado en los parámetros proporcionados y nombres permitidos
		if(regional.equals("GUAYAQUIL")) {
			nombresPermitidos = nombresPermitidosGuayaquil;
		} else if(regional.equals("QUITO")){
			nombresPermitidos = nombresPermitidosQuito;
		} else if(regional.equals("CUENCA")){
			nombresPermitidos = nombresPermitidosCuenca;
		}
		for (Map<String, Object> registro : registros) {
			if (nombresPermitidos.contains(registro.get("NOMBRE")) && regional.equals(registro.get("REGIONAL"))
					&& com.equals(registro.get("COM")) && tipo.equals(registro.get("TIPO"))) {
				registrosFiltrados.add(registro);
			}
		}

		Map<String, Double> totalesPorNombre = new HashMap<>();
		for (Map<String, Object> registro : registrosFiltrados) {
			try {
				// Intenta convertir el objeto a Double
				Object totalObj = registro.get("TOTALES");

				Double total = null;
				if (totalObj instanceof Number) {
					total = ((Number) totalObj).doubleValue();
				} else if (totalObj instanceof String) {
					try {
						total = Double.parseDouble((String) totalObj);
					} catch (NumberFormatException e) {
						log.error("No se pudo convertir el valor a Double: " + totalObj);
					}
				} else if (totalObj == null) {
					log.warn("El valor de TOTALES es null para este registro.");
				}
				totalesPorNombre.merge((String) registro.get("NOMBRE"), total, Double::sum);
			} catch (ClassCastException | NullPointerException e) {
				// Si no es un Double o es null, registra el error y agrega al array de
				// registros no válidos
				log.info("Registro con nombre " + registro.get("NOMBRE") + " no tiene un total válido: "
						+ registro.get("TOTALES"));
				registrosNoValidos.add(registro);
			}
		}

		// Convertir los totales en una lista de mapas para el resultado deseado
		List<Map<String, Object>> resultado = totalesPorNombre.entrySet().stream().map(entry -> {
			Map<String, Object> map = new HashMap<>();
			map.put("NOMBRE", entry.getKey());
			map.put("TOTALES", entry.getValue());
			return map;
		}).collect(Collectors.toList());

		return resultado;
	}

	public void reporteInteroperabilidad(String nombreArchivo) throws IOException {
		//List<Map<String, Object>> registros = crearDatosPruebasInteroperabilidad();
		List<Map<String, Object>> registros = repositoryReporteMids.reporteInteroperabilidad("JMUNOZ");
		List<Map<String, Object>> registrosGuayaquil = filtrarYSumarRegistros(registros,"GUAYAQUIL", "N", "EL");
		List<Map<String, Object>> registrosQuito = filtrarYSumarRegistros(registros,"QUITO", "N", "EL");
		List<Map<String, Object>> registrosCuenta = filtrarYSumarRegistros(registros,"CUENCA", "N", "EL");
		
		List<String> excludedKeys = Arrays.asList("");
		SXSSFWorkbook workbook = new SXSSFWorkbook();
		generaHoja(workbook, "UIO", registrosQuito, excludedKeys);
		generaHoja(workbook, "GYE", registrosGuayaquil, excludedKeys);
		generaHoja(workbook, "CUE", registrosCuenta, excludedKeys);

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
