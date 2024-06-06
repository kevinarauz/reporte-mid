package conexion_prueba.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import conexion_prueba.service.EmailService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/Email")
public class EmailController {
	
	@Autowired
    private EmailService emailService;
	
	@GetMapping("/send-email")
    public String sendEmail() {
        //emailService.sendSimpleMessage("destinatario@gmail.com", "Asunto del Correo", "Contenido del correo");
		emailService.sendSimpleMessage("kevin.arauzg@gmail.com", "Prueba de Correo", "Esto es una prueba :).");
        return "Correo enviado!";
    }
	
}
