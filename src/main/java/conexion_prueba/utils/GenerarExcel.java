package conexion_prueba.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

public class GenerarExcel {

	public GenerarExcel() {
		
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

}
