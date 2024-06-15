package conexion_prueba;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.mail.SimpleMailMessage;

import conexion_prueba.service.EmailService;
import conexion_prueba.service.ReporteMidsService;

@SpringBootApplication
public class ConexionApplication {
	
	private static final Logger log = LoggerFactory.getLogger(ConexionApplication.class);
	private static final String DATOS = "datos";
	
	@Autowired
    private static EmailService emailService;

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(ConexionApplication.class, args);
		ConexionApplication app = ctx.getBean(ConexionApplication.class);
		app.verificaEmailKevin();
		app.verificaEmailJessy1();
		app.verificaEmailJessy2();
		app.verificaEmailJessy3();
		app.verificaEmailJessy4();
	}
	
	public void verificaEmailKevin() {
		log.info("Verificando kevin...");
	    Properties props = new Properties();
	    props.put("mail.smtp.host", "smtp.gmail.com"); // Servidor SMTP de Gmail
	    props.put("mail.smtp.socketFactory.port", "465"); // Puerto para SSL
	    props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.port", "465"); // Usar el puerto 465 para SSL

	    // Autenticación
	    Session session = Session.getDefaultInstance(props,
	        new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication("kevin.arauzg@gmail.com", "yjnf xwlo mkbv ybqh");
	            }
	        });

	    try {
	    	// Primero intentamos establecer una conexión con el servidor SMTP
	        Transport transport = session.getTransport("smtp");
	        transport.connect("smtp.gmail.com", "kevin.arauzg@gmail.com", "yjnf xwlo mkbv ybqh");
	        transport.close(); // Cerramos la conexión una vez verificada
	        log.info("Conexión con el servidor SMTP exitosa kevin!");

	        // Si la conexión es exitosa, procedemos a enviar el correo
	        enviarCorreoKevin(session);
	    } catch (MessagingException e) {
	        log.error("Error al enviar el correo kevin", e);
	    }
	}
	
	private void enviarCorreoKevin(Session session) throws MessagingException {
	    Message message = new MimeMessage(session);
	    message.setFrom(new InternetAddress("kevin.arauzg@gmail.com"));
	    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("kevin.arauzg@gmail.com"));
	    message.setSubject("Prueba de Conexión y Envío");
	    message.setText("Este correo confirma que la conexión al servidor SMTP fue exitosa y el correo ha sido enviado.");

	    Transport.send(message);
	    log.info("Correo enviado exitosamente!");
	}
	
	public void verificaEmailJessy1() {
		log.info("Verificando Jessy1...");
	    Properties props = new Properties();
	    props.put("mail.smtp.host", "192.168.11.31"); // Servidor SMTP de Gmail
	    props.put("mail.smtp.socketFactory.port", "465"); // Puerto para SSL
	    props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.port", "465"); // Usar el puerto 465 para SSL

	    // Autenticación
	    Session session = Session.getDefaultInstance(props,
	        new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication("notificacionesti@datafast.com.ec", "5ygcckxV");
	            }
	        });

	    try {
	    	// Primero intentamos establecer una conexión con el servidor SMTP
	        Transport transport = session.getTransport("smtp");
	        transport.connect("192.168.11.31", "notificacionesti@datafast.com.ec", "5ygcckxV");
	        transport.close(); // Cerramos la conexión una vez verificada
	        log.info("Conexión con el servidor SMTP exitosa Jessy!");

	        // Si la conexión es exitosa, procedemos a enviar el correo
	        enviarCorreoKevin(session);
	    } catch (MessagingException e) {
	        log.error("Error al enviar el correo Jessy1", e);
	    }
	}
	
	public void verificaEmailJessy2() {
	    Properties props = new Properties();
	    props.put("mail.smtp.host", "192.168.11.31"); // Servidor
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.port", "587"); // Usar el puerto 465 para SSL

	    // Autenticación
	    Session session = Session.getDefaultInstance(props,
	        new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication("notificacionesti@datafast.com.ec", "5ygcckxV");
	            }
	        });

	    try {
	    	// Primero intentamos establecer una conexión con el servidor SMTP
	        Transport transport = session.getTransport("smtp");
	        transport.connect("192.168.11.31", "notificacionesti@datafast.com.ec", "5ygcckxV");
	        transport.close(); // Cerramos la conexión una vez verificada
	        log.info("Conexión con el servidor SMTP exitosa Jessy2!");

	        // Si la conexión es exitosa, procedemos a enviar el correo
	        enviarCorreoKevin(session);
	    } catch (MessagingException e) {
	        log.error("Error al enviar el correo Jessy2", e);
	    }
	}
	
	public void verificaEmailJessy3() {
	    Properties props = new Properties();
	    props.put("mail.smtp.host", "192.168.11.31"); // Servidor
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.port", "25");

	    // Autenticación
	    Session session = Session.getDefaultInstance(props,
	        new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication("notificacionesti@datafast.com.ec", "5ygcckxV");
	            }
	        });

	    try {
	    	// Primero intentamos establecer una conexión con el servidor SMTP
	        Transport transport = session.getTransport("smtp");
	        transport.connect("192.168.11.31", "notificacionesti@datafast.com.ec", "5ygcckxV");
	        transport.close(); // Cerramos la conexión una vez verificada
	        log.info("Conexión con el servidor SMTP exitosa Jessy3!");

	        // Si la conexión es exitosa, procedemos a enviar el correo
	        enviarCorreoKevin(session);
	    } catch (MessagingException e) {
	        log.error("Error al enviar el correo Jessy3", e);
	    }
	}
	
	public void verificaEmailJessy4() {
	    Properties props = new Properties();
	    props.put("mail.smtp.host", "192.168.11.6"); // Servidor
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.port", "25");

	    // Autenticación
	    Session session = Session.getDefaultInstance(props,
	        new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication("notificacionesti@datafast.com.ec", "5ygcckxV");
	            }
	        });

	    try {
	    	// Primero intentamos establecer una conexión con el servidor SMTP
	        Transport transport = session.getTransport("smtp");
	        transport.connect("192.168.11.6", "notificacionesti@datafast.com.ec", "5ygcckxV");
	        transport.close(); // Cerramos la conexión una vez verificada
	        log.info("Conexión con el servidor SMTP exitosa Jessy4!");

	        // Si la conexión es exitosa, procedemos a enviar el correo
	        enviarCorreoKevin(session);
	    } catch (MessagingException e) {
	        log.error("Error al enviar el correo Jessy4", e);
	    }
	}
	
	private void enviarCorreoJessy(Session session) throws MessagingException {
	    Message message = new MimeMessage(session);
	    message.setFrom(new InternetAddress("notificacionesti@datafast.com.ec"));
	    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("kevin.arauzg@gmail.com"));
	    message.setSubject("Prueba de Conexión y Envío");
	    message.setText("Este correo confirma que la conexión al servidor SMTP fue exitosa y el correo ha sido enviado.");

	    Transport.send(message);
	    log.info("Correo enviado exitosamente!");
	}
	
}
