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
import org.apache.poi.ss.util.CellRangeAddress;
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
		registro1.put("NOMBRE", "C.C. ALBAN BORJA");
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
		registro2.put("NOMBRE", "C.C. ALBAN BORJA");
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
		registro3.put("NOMBRE", "C.C. ALBAN BORJA");
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
		registro4.put("NOMBRE", "C.C. SAN MARINO");
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
		registro5.put("NOMBRE", "C.C. ALBAN BORJA");
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
	private static final Set<String> nombresPermitidosGuayaquil = new HashSet<>(Arrays.asList("C.C. ALBAN BORJA",
			"C.C. ALBOCENTRO", "C.C. ALHAMBRA", "C.C. AVENTURA PLAZA", "C.C. BLUE COAST CENTER", "C.C. BOCCA",
			"C.C. CITY MALL", "C.C. DICENTRO", "C.C. GARZOCENTRO MALL", "C.C. GRAN MANZANA",
			"C.C. LA PIAZZA CIUDAD CELESTE", "C.C. LA PIAZZA SAMBORONDON", "C.C. LA ROTONDA", "C.C. LA TORRE",
			"C.C. LAGOS PLAZA", "C.C. LAGUNA CLUB", "C.C. LAS TERRAZAS", "C.C. MALL DEL PACIFICO", "C.C. MALL DEL SOL",
			"C.C. MALL DEL SUR", "C.C. MALL EL FORTIN", "C.C. ORO PLAZA", "C.C. PASEO SHOPPING BABAHOYO",
			"C.C. PASEO SHOPPING BAHIA", "C.C. PASEO SHOPPING DAULE", "C.C. PASEO SHOPPING DURAN",
			"C.C. PASEO SHOPPING MACHALA", "C.C. PASEO SHOPPING MANTA", "C.C. PASEO SHOPPING MILAGRO",
			"C.C. PASEO SHOPPING PENINSULA", "C.C. PASEO SHOPPING PLAYAS", "C.C. PASEO SHOPPING PORTOVIEJO",
			"C.C. PASEO SHOPPING QUEVEDO", "C.C. PASEO SHOPPING VIA DAULE", "C.C. PIAZZA CEIBOS", "C.C. PIAZZA MACHALA",
			"C.C. PIAZZA VILLA CLUB", "C.C. PLAZA MAYOR", "C.C. PLAZA NAVONA", "C.C. PLAZA QUIL",
			"C.C. PLAZA TRIANGULO", "C.C. PLAZA VICTORIA", "C.C. POLICENTRO", "C.C. RIOCENTRO CEIBOS",
			"C.C. RIOCENTRO EL DORADO", "C.C. RIOCENTRO ENTRE RIOS", "C.C. RIOCENTRO NORTE", "C.C. RIOCENTRO SUR",
			"C.C. SAMBORONDON PLAZA", "C.C. SAN MARINO", "C.C. TERMINAL TERRESTRE", "C.C. UNICENTRO",
			"C.C. VILLAGE PLAZA"));
	private static final Set<String> nombresPermitidosCuenca = new HashSet<>(Arrays.asList("C.C. BARTOLOME SERRANO",
			"C.C. CORAL CENTRO", "C.C. EL VERGEL", "C.C. LA PRADERA", "C.C. LAS ORQUIDEAS", "C.C. MALL DEL RIO",
			"C.C. MILENIUM PLAZA", "C.C. MIRAFLORES", "C.C. MONAY SHOPPING", "C.C. RACAR"));

	private static final Set<String> nombresPermitidosQuito = new HashSet<>(Arrays.asList("C.C. ATAHUALPA",
			"C.C. CARACOL", "C.C. EL BOSQUE", "C.C. EL CONDADO", "C.C. EL PORTAL", "C.C. EL RECREO",
			"C.C. GRANADOS OUTLET", "C.C. I#AQUITO", "C.C. LA PLAZA", "C.C. LAGUNA MALL", "C.C. MALL DE LOS ANDES",
			"C.C. MALL EL JARDIN", "C.C. MALTERIA", "C.C. MEGAMAXI", "C.C. MULTIPLAZA", "C.C. MULTIPLAZA RIOBAMBA",
			"C.C. PASEO SAN FRANCISCO", "C.C. PASEO SHOPPING AMBATO", "C.C. PASEO SHOPPING RIOBAMBA",
			"C.C. PASEO SHOPPING ST DOMINGO", "C.C. PLAZA CUMBAYA", "C.C. PLAZA DE LAS AMERICAS",
			"C.C. PLAZA DEL RANCHO", "C.C. QUICENTRO SHOPPING", "C.C. QUICENTRO SUR", "C.C. RIVER MALL",
			"C.C. SAN LUIS SHOPPING", "C.C. SCALA SHOPPING", "C.C. VENTURA MALL"));

	public List<Map<String, Object>> filtrarYSumarRegistros(List<Map<String, Object>> registros, String regional,
			String com, String tipo) {
		nombresPermitidos = new HashSet<>();
		// Filtro basado en los parámetros proporcionados y nombres permitidos
		switch (regional) {
		case "GUAYAQUIL":
			nombresPermitidos = nombresPermitidosGuayaquil;
			break;
		case "QUITO":
			nombresPermitidos = nombresPermitidosQuito;
			break;
		case "CUENCA":
			nombresPermitidos = nombresPermitidosCuenca;
			break;
		}

		List<Map<String, Object>> registrosFiltrados = registros.stream()
				.filter(registro -> nombresPermitidos.contains(registro.get("NOMBRE"))
						&& regional.equals(registro.get("REGIONAL")) && com.equals(registro.get("COM"))
						&& tipo.equals(registro.get("TIPO")))
				.collect(Collectors.toList());

		Map<String, Double> totalesPorNombre = new HashMap<>();
		double sumaTotal = 0.0;
		for (Map<String, Object> registro : registrosFiltrados) {
			Double total = obtenerDoubleDeObjeto(registro.get("TOTALES"));
			if (total != null) {
				// Redondear a dos decimales
				total = Math.round(total * 100.0) / 100.0;
				totalesPorNombre.merge((String) registro.get("NOMBRE"), total, Double::sum);
				sumaTotal += total;
			}
		}

		// Convertir los totales en una lista de mapas para el resultado deseado
		List<Map<String, Object>> resultado = totalesPorNombre.entrySet().stream().map(entry -> {
			Map<String, Object> map = new HashMap<>();
			map.put("NOMBRE", entry.getKey());
			map.put("TOTALES", entry.getValue());
			return map;
		}).collect(Collectors.toList());

		// Agregar la fila final con el total general
		Map<String, Object> totalGeneral = new HashMap<>();
		totalGeneral.put("NOMBRE", "TOTAL");
		// Redondear la suma total a dos decimales
		totalGeneral.put("TOTALES", Math.round(sumaTotal * 100.0) / 100.0);
		resultado.add(totalGeneral);

		return resultado;
	}

	private Double obtenerDoubleDeObjeto(Object obj) {
		if (obj instanceof Number) {
			return ((Number) obj).doubleValue();
		} else if (obj instanceof String) {
			try {
				return Double.parseDouble((String) obj);
			} catch (NumberFormatException e) {
				// Manejo de error, por ejemplo, log
				System.err.println("No se pudo convertir el valor a Double: " + obj);
			}
		}
		return null; // En caso de que no se pueda convertir
	}

	private Map<String, Object> crearRegistro(String nombre, String tipo, String zona, int subzona, int totales,
			int mes, int anio, String com, String usuario, String activo, String banco, String regional) {
		Map<String, Object> registro = new HashMap<>();
		registro.put("NOMBRE", nombre);
		registro.put("TIPO", tipo);
		registro.put("ZONA", zona);
		registro.put("SUBZONA", subzona);
		registro.put("TOTALES", totales);
		registro.put("MES", mes);
		registro.put("ANIO", anio);
		registro.put("COM", com);
		registro.put("USUARIO", usuario);
		registro.put("ACTIVO", activo);
		registro.put("BANCO", banco);
		registro.put("REGIONAL", regional);
		return registro;
	}

	public List<Map<String, Object>> crearDatosPruebasCentrosComercialesNew() {
		List<Map<String, Object>> registros = new ArrayList<>();
		registros.add(crearRegistro("C.C. ALBAN BORJA", "VS", "G4", 1, 77000000, 1, 2024, "N", "EALFONZO", "ACT", "ALL",
				"GUAYAQUIL"));
		registros.add(crearRegistro("C.C. CITY MALL", "VS", "G4", 1, 155000000, 1, 2024, "N", "EALFONZO", "ACT", "ALL",
				"GUAYAQUIL"));
		registros.add(crearRegistro("C.C. MALL DEL SOL", "VS", "G4", 1, 250000000, 1, 2024, "N", "EALFONZO", "ACT",
				"ALL", "GUAYAQUIL"));
		return registros;
	}

	//Reporte tarjetas "VS","BG","DC","MC","DI"
	public void reporteCentrosComercialesNew(String nombreArchivo) throws IOException {
		//List<Map<String, Object>> registros = crearDatosPruebasCentrosComercialesNew();
	    List<Map<String, Object>> registros = repositoryReporteMids.reporteCentrosComercialesNew("JMUNOZ");

	    String tipoCom = "N"; // Asumiendo que este es el filtro inicial y podría cambiar para otros reportes

	    // Mapa para las siglas de las ciudades
	    Map<String, String> siglasCiudad = new HashMap<>();
	    siglasCiudad.put("QUITO", "UIO");
	    siglasCiudad.put("GUAYAQUIL", "GYE");
	    siglasCiudad.put("CUENCA", "CUE");

	    // Mapas para gestionar el inicio de fila por ciudad
	    Map<String, Integer> inicioFilaPorCiudad = new HashMap<>();
	    inicioFilaPorCiudad.put("QUITO", 0);
	    inicioFilaPorCiudad.put("GUAYAQUIL", 0);
	    inicioFilaPorCiudad.put("CUENCA", 0);

	    List<String> excludedKeys = Arrays.asList("");
	    SXSSFWorkbook workbook = new SXSSFWorkbook();

	    // Usar el nombre completo de la ciudad para generarReportesPorCiudad y las siglas para el nombre de las hojas
	    generarReportesCCNewPorCiudad("QUITO", registros, tipoCom, workbook, inicioFilaPorCiudad, excludedKeys, siglasCiudad);
	    generarReportesCCNewPorCiudad("GUAYAQUIL", registros, tipoCom, workbook, inicioFilaPorCiudad, excludedKeys, siglasCiudad);
	    generarReportesCCNewPorCiudad("CUENCA", registros, tipoCom, workbook, inicioFilaPorCiudad, excludedKeys, siglasCiudad);

	    // Escribir a archivo
	    try (FileOutputStream outputStream = new FileOutputStream(nombreArchivo + ".xlsx")) {
	        workbook.write(outputStream);
	    } finally {
	        workbook.dispose(); // Liberar recursos de SXSSFWorkbook
	    }
	}
	
	private void generarReportesCCNewPorCiudad(String ciudad, List<Map<String, Object>> registros, String tipoCom,
	        SXSSFWorkbook workbook, Map<String, Integer> inicioFilaPorCiudad, List<String> excludedKeys, Map<String, String> siglasCiudad) {
	    String[] tiposReporte = {"VS","BG","DC","MC","DI"};
	    String[] titulosReporte = {"VISA","AMEX","DINERS","MASTERCARD","DISCOVER"};

	    for (int i = 0; i < tiposReporte.length; i++) {
	        List<Map<String, Object>> registrosFiltrados = filtrarYSumarRegistros(registros, ciudad, tipoCom, tiposReporte[i]);
	        // Usa las siglas de la ciudad para el nombre de la hoja
	        //generaHoja(workbook, siglasCiudad.get(ciudad), registrosFiltrados, excludedKeys, titulosReporte[i], inicioFilaPorCiudad.get(ciudad));
	        // Actualizar el contador de inicio de fila para la siguiente sección
	        int nuevaFilaInicio = inicioFilaPorCiudad.get(ciudad) + registrosFiltrados.size() + 3; // +3 para dejar espacio
	        inicioFilaPorCiudad.put(ciudad, nuevaFilaInicio);
	    }
	}

	public void reporteCentrosComercialesDebit(String nombreArchivo) throws IOException {
	    //List<Map<String, Object>> registros = crearDatosPruebasInteroperabilidad();
	    List<Map<String, Object>> registros = repositoryReporteMids.reporteCentrosComercialesDebit("JMUNOZ");

	    String tipoCom = "N"; // Asumiendo que este es el filtro inicial y podría cambiar para otros reportes

	    // Mapa para las siglas de las ciudades
	    Map<String, String> siglasCiudad = new HashMap<>();
	    siglasCiudad.put("QUITO", "UIO");
	    siglasCiudad.put("GUAYAQUIL", "GYE");
	    siglasCiudad.put("CUENCA", "CUE");

	    // Mapas para gestionar el inicio de fila por ciudad
	    Map<String, Integer> inicioFilaPorCiudad = new HashMap<>();
	    inicioFilaPorCiudad.put("QUITO", 0);
	    inicioFilaPorCiudad.put("GUAYAQUIL", 0);
	    inicioFilaPorCiudad.put("CUENCA", 0);

	    List<String> excludedKeys = Arrays.asList("");
	    SXSSFWorkbook workbook = new SXSSFWorkbook();

	    // Usar el nombre completo de la ciudad para generarReportesPorCiudad y las siglas para el nombre de las hojas
	    generarReportesCCDEBITPorCiudad("QUITO", registros, tipoCom, workbook, inicioFilaPorCiudad, excludedKeys, siglasCiudad);
	    generarReportesCCDEBITPorCiudad("GUAYAQUIL", registros, tipoCom, workbook, inicioFilaPorCiudad, excludedKeys, siglasCiudad);
	    generarReportesCCDEBITPorCiudad("CUENCA", registros, tipoCom, workbook, inicioFilaPorCiudad, excludedKeys, siglasCiudad);

	    // Escribir a archivo
	    try (FileOutputStream outputStream = new FileOutputStream(nombreArchivo + ".xlsx")) {
	        workbook.write(outputStream);
	    } finally {
	        workbook.dispose(); // Liberar recursos de SXSSFWorkbook
	    }
	}

	private void generarReportesCCDEBITPorCiudad(String ciudad, List<Map<String, Object>> registros, String tipoCom,
	        SXSSFWorkbook workbook, Map<String, Integer> inicioFilaPorCiudad, List<String> excludedKeys, Map<String, String> siglasCiudad) {
	    String[] tiposReporte = {"EL", "MCDEB", "INTER"};
	    String[] titulosReporte = {"ELECTRON", "MCDEBIT", "INTERDIN"};

	    for (int i = 0; i < tiposReporte.length; i++) {
	        List<Map<String, Object>> registrosFiltrados = filtrarYSumarRegistros(registros, ciudad, tipoCom, tiposReporte[i]);
	        // Usa las siglas de la ciudad para el nombre de la hoja
	        generaHoja(workbook, siglasCiudad.get(ciudad), registrosFiltrados, excludedKeys, titulosReporte[i], inicioFilaPorCiudad.get(ciudad));
	        // Actualizar el contador de inicio de fila para la siguiente sección
	        int nuevaFilaInicio = inicioFilaPorCiudad.get(ciudad) + registrosFiltrados.size() + 3; // +3 para dejar espacio
	        inicioFilaPorCiudad.put(ciudad, nuevaFilaInicio);
	    }
	}

	public void generaHoja(SXSSFWorkbook workbook, String nombreHoja, List<Map<String, Object>> registros,
			List<String> excludedKeys, String titulo, int inicioFila) {
		Sheet sheet = workbook.getSheet(nombreHoja);
		if (sheet == null) {
			sheet = workbook.createSheet(nombreHoja);
		}

		// Insertar el título del reporte
		Row tituloRow = sheet.createRow(inicioFila);
		Cell tituloCell = tituloRow.createCell(0);
		tituloCell.setCellValue(titulo);

		// Fusionar celdas para el título
		sheet.addMergedRegion(new CellRangeAddress(inicioFila, inicioFila, 0, registros.get(0).keySet().size() - 1));

		// Incrementar el inicioFila para empezar a escribir los encabezados debajo del
		// título
		// inicioFila += 1; // Solo incrementa en 1 para no dejar espacio adicional.

		Row headerRow = sheet.createRow(++inicioFila); // Usa pre-incremento para mover al siguiente índice.
		int cellIndex = 0;
		for (String key : registros.get(0).keySet()) {
			if (!excludedKeys.contains(key)) {
				Cell cell = headerRow.createCell(cellIndex++);
				cell.setCellValue(key);
			}
		}

		// Crear filas de datos
		for (Map<String, Object> registro : registros) {
			Row row = sheet.createRow(++inicioFila); // Usa pre-incremento para mover al siguiente índice.
			cellIndex = 0;
			for (Map.Entry<String, Object> entry : registro.entrySet()) {
				if (!excludedKeys.contains(entry.getKey())) {
					Cell cell = row.createCell(cellIndex++);
					Object value = entry.getValue();
					// Asignar valor a la celda basado en su tipo
					if (value instanceof Date) {
						cell.setCellValue((Date) value);
					} else if (value instanceof Boolean) {
						cell.setCellValue((Boolean) value);
					} else if (value instanceof Number) {
						cell.setCellValue(((Number) value).doubleValue());
					} else {
						cell.setCellValue(value.toString());
					}
				}
			}
		}

		// Ajustar el tamaño de las columnas
		for (int i = 0; i < cellIndex; i++) {
			sheet.autoSizeColumn(i);
		}
	}

	// Genera Excel con n hojas de trabajo
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
	
	public void generarReportesUnificados(String nombreArchivo) throws IOException {
		List<Map<String, Object>> registrosDebit = crearDatosPruebasInteroperabilidad();
	    //List<Map<String, Object>> registrosDebit = repositoryReporteMids.reporteCentrosComercialesDebit("JMUNOZ");
		List<Map<String, Object>> registrosNew = crearDatosPruebasCentrosComercialesNew();
	    //List<Map<String, Object>> registrosNew = repositoryReporteMids.reporteCentrosComercialesNew("JMUNOZ");

	    String tipoCom = "N"; // Asumiendo que este es el filtro inicial y podría cambiar para otros reportes

	    // Mapa para las siglas de las ciudades
	    Map<String, String> siglasCiudad = new HashMap<>();
	    siglasCiudad.put("QUITO", "UIO");
	    siglasCiudad.put("GUAYAQUIL", "GYE");
	    siglasCiudad.put("CUENCA", "CUE");

	    // Mapas para gestionar el inicio de fila por ciudad
	    Map<String, Integer> inicioFilaPorCiudad = new HashMap<>();
	    inicioFilaPorCiudad.put("QUITO", 0);
	    inicioFilaPorCiudad.put("GUAYAQUIL", 0);
	    inicioFilaPorCiudad.put("CUENCA", 0);

	    List<String> excludedKeys = Arrays.asList("");
	    SXSSFWorkbook workbook = new SXSSFWorkbook();

	    // Usar el nombre completo de la ciudad para generarReportesPorCiudad y las siglas para el nombre de las hojas
	    generarReportesCCDEBITNewPorCiudad("QUITO", registrosDebit,registrosNew, tipoCom, workbook, inicioFilaPorCiudad, excludedKeys, siglasCiudad);
	    generarReportesCCDEBITNewPorCiudad("GUAYAQUIL", registrosDebit,registrosNew, tipoCom, workbook, inicioFilaPorCiudad, excludedKeys, siglasCiudad);
	    generarReportesCCDEBITNewPorCiudad("CUENCA", registrosDebit,registrosNew, tipoCom, workbook, inicioFilaPorCiudad, excludedKeys, siglasCiudad);

	    // Escribir a archivo
	    try (FileOutputStream outputStream = new FileOutputStream(nombreArchivo + ".xlsx")) {
	        workbook.write(outputStream);
	    } finally {
	        workbook.dispose(); // Liberar recursos de SXSSFWorkbook
	    }
	}
	
	private void generarReportesCCDEBITNewPorCiudad(String ciudad, List<Map<String, Object>> registros,List<Map<String, Object>> registrosNew, String tipoCom,
	        SXSSFWorkbook workbook, Map<String, Integer> inicioFilaPorCiudad, List<String> excludedKeys, Map<String, String> siglasCiudad) {
	    String[] tiposReporte = {"EL", "MCDEB", "INTER"};
	    String[] titulosReporte = {"ELECTRON", "MCDEBIT", "INTERDIN"};

	    for (int i = 0; i < tiposReporte.length; i++) {
	        List<Map<String, Object>> registrosFiltrados = filtrarYSumarRegistrosNewDebit(registros,registrosNew, ciudad, tipoCom, tiposReporte[i]);
	        // Usa las siglas de la ciudad para el nombre de la hoja
	        generaHoja(workbook, siglasCiudad.get(ciudad), registrosFiltrados, excludedKeys, titulosReporte[i], inicioFilaPorCiudad.get(ciudad));
	        // Actualizar el contador de inicio de fila para la siguiente sección
	        int nuevaFilaInicio = inicioFilaPorCiudad.get(ciudad) + registrosFiltrados.size() + 3; // +3 para dejar espacio
	        inicioFilaPorCiudad.put(ciudad, nuevaFilaInicio);
	    }
	}
	
	public List<Map<String, Object>> filtrarYSumarRegistrosNewDebit(List<Map<String, Object>> registros,List<Map<String, Object>>registrosNew, String ciudad, String com, String tipo) {
		nombresPermitidos = new HashSet<>();
		// Filtro basado en los parámetros proporcionados y nombres permitidos
		switch (ciudad) {
		case "GUAYAQUIL":
			nombresPermitidos = nombresPermitidosGuayaquil;
			break;
		case "QUITO":
			nombresPermitidos = nombresPermitidosQuito;
			break;
		case "CUENCA":
			nombresPermitidos = nombresPermitidosCuenca;
			break;
		}

		List<Map<String, Object>> registrosFiltrados = registros.stream()
				.filter(registro -> nombresPermitidos.contains(registro.get("NOMBRE"))
						&& ciudad.equals(registro.get("REGIONAL")) && com.equals(registro.get("COM"))
						&& tipo.equals(registro.get("TIPO")))
				.collect(Collectors.toList());
		
		/*List<Map<String, Object>> listaFinal = new ArrayList<>();
		for (Map<String, Object> registro : registrosFiltrados) {
			Map<String, Object> registroNew = new HashMap<>();
			registroNew.put("NOMBRE", registro.get("NOMBRE"));
			//registroNew.put("TARJETA", agregarRegistroTargeta("GUAYAQUIL", registrosDebit,registrosNew, tipoCom, workbook, inicioFilaPorCiudad, excludedKeys, siglasCiudad));//aqui quiero agregar la columna q falta
			registroNew.put("TARJETA", agregarRegistroTarjeta((String)registro.get("NOMBRE"), "VS", registrosNew, ciudad, com, tipo));
			listaFinal.add(registroNew);
		}
		registrosFiltrados = listaFinal;*/
		Map<String, Double> totalesPorNombre = new HashMap<>();
		double sumaTotal = 0.0;
		for (Map<String, Object> registro : registrosFiltrados) {
			Double total = obtenerDoubleDeObjeto(registro.get("TOTALES"));
			if (total != null) {
				// Redondear a dos decimales
				total = Math.round(total * 100.0) / 100.0;
				totalesPorNombre.merge((String) registro.get("NOMBRE"), total, Double::sum);
				sumaTotal += total;
			}
		}

		// Convertir los totales en una lista de mapas para el resultado deseado
		List<Map<String, Object>> resultado = totalesPorNombre.entrySet().stream().map(entry -> {
			Map<String, Object> map = new HashMap<>();
			map.put("NOMBRE", entry.getKey());
			map.put("TARJETA", agregarRegistroTarjeta(map.get("NOMBRE").toString(), "VS", registrosNew, ciudad, com, tipo));
			map.put("TOTALES", entry.getValue());
			
			return map;
		}).collect(Collectors.toList());

		// Agregar la fila final con el total general
		Map<String, Object> totalGeneral = new HashMap<>();
		totalGeneral.put("NOMBRE", "TOTAL");
		// Redondear la suma total a dos decimales
		totalGeneral.put("TOTALES", Math.round(sumaTotal * 100.0) / 100.0);
		resultado.add(totalGeneral);

		return resultado;
	}

	private Double obtenerDoubleDeObjetoNew(Object obj) {
	    if (obj instanceof Number) {
	        return ((Number) obj).doubleValue();
	    }
	    return null; // O manejar según sea necesario
	}

	private String agregarRegistroTarjeta(String nombreCC, String tipoTarjeta, List<Map<String, Object>> registrosNew, String ciudad, String com, String tipo) {
	    // Filtrar registros por ciudad, com, tipo y tipo de tarjeta
	    boolean existeRegistro = registrosNew.stream()
	            .anyMatch(registro -> nombreCC.equals(registro.get("NOMBRE")) && ciudad.equals(registro.get("CIUDAD")) && com.equals(registro.get("COM")) && tipoTarjeta.equals(registro.get("TIPO_TARJETA")));

	    // Retornar el tipo de tarjeta si existe al menos un registro que cumpla con los criterios
	    if (existeRegistro) {
	        return tipoTarjeta;
	    } else {
	        return null; // O manejar de otra manera si no se encuentra ningún registro
	    }
	}

	
}
