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
    
    @GetMapping("/ConsultaInteroperabilidad")
    public ResponseEntity<?> reporteInteroperabilidad() {
        ThreadContext.put("sid", UUID.randomUUID().toString());
        String nombreArchivo = "Reporte Interoperabilidad - " + new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        try {
            this.reporteMidsService.reporteInteroperabilidad(nombreArchivo);
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

    
}
