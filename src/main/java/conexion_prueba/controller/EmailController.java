package conexion_prueba.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/Email")
public class EmailController {
	
	@Autowired
    //private EmailService emailService;
	
	@GetMapping("/send-email")
    public ResponseEntity<?> sendEmail() {
        //emailService.sendSimpleMessage("destinatario@gmail.com", "Asunto del Correo", "Contenido del correo");
		//emailService.sendSimpleMessage("kevin.arauzg@gmail.com", "Prueba de Correo", "Esto es una prueba :).");
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Correo enviado!");
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }
	
}
