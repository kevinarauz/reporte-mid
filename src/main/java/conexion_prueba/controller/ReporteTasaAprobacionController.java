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

import conexion_prueba.service.ReporteTasaAprobacionService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/TasaAprobacion")
public class ReporteTasaAprobacionController {

	@Autowired
	ReporteTasaAprobacionService reporteTasaAprobacionService;

	@GetMapping("/ConsultaTasaAprobacion")
	public ResponseEntity<?> consultaTasaAprobacion() {
		ThreadContext.put("sid", UUID.randomUUID().toString());
		String nombreArchivo = "Reporte Tasa Aprobacion - " + new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		try {
			this.reporteTasaAprobacionService.consultaTasaAprobacion(nombreArchivo);
			String mensajeRespuesta = String.format(
					"El reporte consultaTasaAprobacion '%s.xlsx' ha sido generado exitosamente.", nombreArchivo);
			// Puedes retornar el nombre del archivo para que el cliente sepa cómo acceder a
			// él
			Map<String, String> respuesta = new HashMap<>();
			respuesta.put("mensaje", mensajeRespuesta);
			respuesta.put("nombreArchivo", nombreArchivo + ".xlsx");
			return new ResponseEntity<>(respuesta, HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<>("Ocurrió un error al generar el reporte consultaTasaAprobacion.",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/enviarEmail")
	public ResponseEntity<?> enviarEmail() {
		ThreadContext.put("sid", UUID.randomUUID().toString());
		Map<String, String> respuesta = new HashMap<>();
		respuesta.put("mensaje", "Correo enviado correctamente");
		return new ResponseEntity<>(respuesta, HttpStatus.OK);
	}

}
