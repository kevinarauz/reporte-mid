package conexion_prueba.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import conexion_prueba.service.ReporteMidsService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/ReporteMids")
public class ReporteMidsController {
	
	@Autowired
	ReporteMidsService reporteMidsService;
    
    @GetMapping("/ObtenerMids")
    public ResponseEntity<?> getJm() {
        ThreadContext.put("sid", UUID.randomUUID().toString());
        return new ResponseEntity<>(reporteMidsService.getJm(), HttpStatus.OK);
    }
    
    /*@GetMapping("/ConsultaMidActivos")
    public ResponseEntity<?> consultaMidActivosActualizados() {
        ThreadContext.put("sid", UUID.randomUUID().toString());
        return new ResponseEntity<>(reporteMidsService.consultaMidActivosActualizados(), HttpStatus.OK);
    }*/

    @GetMapping("/ConsultaMidActivos")
    public ResponseEntity<?> consultaMidActivosActualizados() {
        ThreadContext.put("sid", UUID.randomUUID().toString());
        String nombreArchivo = "Reporte Mids - " + new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        try {
            this.reporteMidsService.consultaMidActivosActualizados(nombreArchivo);
            String mensajeRespuesta = String.format("El reporte '%s.xlsx' ha sido generado exitosamente.", nombreArchivo);
            // Puedes retornar el nombre del archivo para que el cliente sepa cómo acceder a él
            Map<String, String> respuesta = new HashMap<>();
            respuesta.put("mensaje", mensajeRespuesta);
            respuesta.put("nombreArchivo", nombreArchivo + ".xlsx");
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Ocurrió un error al generar el reporte.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/ConsultaCentrosComercialesDebit")
    public ResponseEntity<?> reporteCentrosComercialesDebit() {
        ThreadContext.put("sid", UUID.randomUUID().toString());
        String nombreArchivo = "Reporte Centros Comerciales Debit - " + new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        try {
            this.reporteMidsService.reporteCentrosComercialesDebit(nombreArchivo);
            String mensajeRespuesta = String.format("El reporte '%s.xlsx' ha sido generado exitosamente.", nombreArchivo);
            // Puedes retornar el nombre del archivo para que el cliente sepa cómo acceder a él
            Map<String, String> respuesta = new HashMap<>();
            respuesta.put("mensaje", mensajeRespuesta);
            respuesta.put("nombreArchivo", nombreArchivo + ".xlsx");
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Ocurrió un error al generar el reporte centros comerciales debit.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/ConsultaCentrosComercialesNew")
    public ResponseEntity<?> reporteCentrosComercialesNew(@RequestParam String tipoCom) {
        ThreadContext.put("sid", UUID.randomUUID().toString());
        String nombreArchivo = "Reporte Centros Comerciales New - " + new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        try {
            this.reporteMidsService.reporteCentrosComercialesNew(nombreArchivo,tipoCom);
            String mensajeRespuesta = String.format("El reporte '%s.xlsx' ha sido generado exitosamente.", nombreArchivo);
            // Puedes retornar el nombre del archivo para que el cliente sepa cómo acceder a él
            Map<String, String> respuesta = new HashMap<>();
            respuesta.put("mensaje", mensajeRespuesta);
            respuesta.put("nombreArchivo", nombreArchivo + ".xlsx");
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Ocurrió un error al generar el reporte centros comerciales new.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/ConsultaCentrosComercialesCredito")
    public ResponseEntity<?> reporteCentrosComercialesCredito(@RequestParam String tipoCom) {
    	ThreadContext.put("sid", UUID.randomUUID().toString());
        String nombreArchivo = "Reporte Centros Comerciales Credito - " + new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        try {
            this.reporteMidsService.reporteCentrosComercialesCredito(nombreArchivo,tipoCom);
            String mensajeRespuesta = String.format("El reporte '%s.xlsx' ha sido generado exitosamente.", nombreArchivo);
            // Puedes retornar el nombre del archivo para que el cliente sepa cómo acceder a él
            Map<String, String> respuesta = new HashMap<>();
            respuesta.put("mensaje", mensajeRespuesta);
            respuesta.put("nombreArchivo", nombreArchivo + ".xlsx");
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Ocurrió un error al generar el reporte centros comerciales credito.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/ConsultaCentrosComerciales")
    public ResponseEntity<?> reporteCentrosComerciales(@RequestParam String tipoCom) {
        ThreadContext.put("sid", UUID.randomUUID().toString());
        String nombreArchivo = "Reporte Centros Comerciales New - " + new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        try {
            this.reporteMidsService.generarReportesUnificados(nombreArchivo,tipoCom);
            String mensajeRespuesta = String.format("El reporte '%s.xlsx' ha sido generado exitosamente.", nombreArchivo);
            // Puedes retornar el nombre del archivo para que el cliente sepa cómo acceder a él
            Map<String, String> respuesta = new HashMap<>();
            respuesta.put("mensaje", mensajeRespuesta);
            respuesta.put("nombreArchivo", nombreArchivo + ".xlsx");
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Ocurrió un error al generar el reporte centros comerciales new.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/ConsultaExcel")
    public ResponseEntity<?> procesarExcel() {
        ThreadContext.put("sid", UUID.randomUUID().toString());
        String nombreArchivo = "Consulta Excel New - " + new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        try {
            this.reporteMidsService.procesarReporte();
            String mensajeRespuesta = String.format("El reporte '%s.xlsx' ha sido generado exitosamente.", nombreArchivo);
            Map<String, String> respuesta = new HashMap<>();
            respuesta.put("mensaje", mensajeRespuesta);
            respuesta.put("nombreArchivo", nombreArchivo + ".xlsx");
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Ocurrió un error al generar el reporte centros comerciales new.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/NivelesServicios")
    public ResponseEntity<?> nivelesServicios() {
        ThreadContext.put("sid", UUID.randomUUID().toString());
        String nombreArchivo = "Consulta Niveles De Servicios - " + new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        try {
            this.reporteMidsService.nivelesServicios();
            String mensajeRespuesta = String.format("El reporte '%s.xlsx' ha sido generado exitosamente.", nombreArchivo);
            Map<String, String> respuesta = new HashMap<>();
            respuesta.put("mensaje", mensajeRespuesta);
            respuesta.put("nombreArchivo", nombreArchivo + ".xlsx");
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Ocurrió un error al generar el reporte Niveles De Servicios.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
