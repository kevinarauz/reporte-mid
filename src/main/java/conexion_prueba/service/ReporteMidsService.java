package conexion_prueba.service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import conexion_prueba.repository.RepositoryReporteMids;
@Service
public class ReporteMidsService {
	@Autowired
	RepositoryReporteMids	repositoryReporteMids;
	
	public List<Map<String, Object>> getJm() {
       
        List<Map<String, Object>> registros = null;
        
        registros = repositoryReporteMids.getJm();
    
        
        return registros != null ? registros : new ArrayList<>();
    }
	
	
	public List<Map<String, Object>> consultaMidActivosActualizados() {
	       
        List<Map<String, Object>> registros = null;
        
        String usuario="JMUNOZ";
        
        registros = repositoryReporteMids.consultaMidActivosActualizados( usuario );
    
        
        return registros != null ? registros : new ArrayList<>();
    }
	
	public void generaExcelDeConsulta(String nombreArchivo) throws IOException {
        List<Map<String, Object>> registros = consultaMidActivosActualizados();

        Workbook workbook = new XSSFWorkbook(); // Crea un nuevo libro de trabajo para archivos XLSX
        Sheet sheet = workbook.createSheet("Datos");

        // Crear la fila de encabezado
        Row headerRow = sheet.createRow(0);
        if (!registros.isEmpty()) {
            int cellIndex = 0;
            for (String key : registros.get(0).keySet()) { // Asumimos que todos los mapas tienen las mismas claves
                Cell cell = headerRow.createCell(cellIndex++);
                cell.setCellValue(key);
            }

            // Crear las filas de datos
            int rowIndex = 1;
            for (Map<String, Object> registro : registros) {
                Row row = sheet.createRow(rowIndex++);
                cellIndex = 0;
                for (Object value : registro.values()) {
                    Cell cell = row.createCell(cellIndex++);
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
        try (FileOutputStream fileOut = new FileOutputStream(nombreArchivo)) {
            workbook.write(fileOut);
        } finally {
            workbook.close(); // Asegúrate de cerrar el libro de trabajo para evitar fugas de memoria
        }
    }

}
