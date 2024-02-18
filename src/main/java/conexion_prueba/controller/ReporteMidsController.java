package conexion_prueba.controller;

import java.io.IOException;
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
        
        try {
			this.reporteMidsService.generaExcelDeConsulta("reportes");
		} catch (IOException e) {
			e.printStackTrace();
		}
        return new ResponseEntity<>("Oks", HttpStatus.OK);
    }
    
}
