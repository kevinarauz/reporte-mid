package conexion_prueba;

import java.io.File;
import java.net.Socket;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import conexion_prueba.utils.EmailService;

@SpringBootApplication
public class ConexionApplication {
    
    private static final Logger log = LoggerFactory.getLogger(ConexionApplication.class);

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(ConexionApplication.class, args);
        ConexionApplication app = ctx.getBean(ConexionApplication.class);
        //EmailService gmailService = new EmailService("smtp.gmail.com", 465, "kevin.arauzg@gmail.com", "yjnf xwlo mkbv ybqh", true);
        //gmailService.sendEmail("kevin.arauzg@gmail.com", "kevin.arauzg@gmail.com", "Prueba Gmail", "Este es el cuerpo del mensaje para Gmail.");
        
        // Para enviar un correo con archivo adjunto
        File attachment = new File("C:\\Java\\Test.xlsx");
        //gmailService.sendEmail("kevin.arauzg@gmail.com", "kevin.arauzg@gmail.com", "Prueba Gmail con archivo", "Este es el cuerpo del mensaje para Gmail con archivo.", attachment);
        
        EmailService dataFastServices = new EmailService("192.168.11.6", 25, "notificacionesti@datafast.com.ec", "5ygcckxV", false);
        dataFastServices.sendEmail("notificacionesti@datafast.com.ec", "jmunoz@datafast.com.ec", "Prueba Correo", "Este es el cuerpo del mensaje para SMTP.");
        dataFastServices.sendEmail("notificacionesti@datafast.com.ec", "kevin.arauzg@gmail.com", "Prueba Correo", "Este es el cuerpo del mensaje para SMTP.");
        
        //File attachment = new File("C:\\Java\\Test.xlsx");
        dataFastServices.sendEmail("notificacionesti@datafast.com.ec", "jmunoz@datafast.com.ec", "Prueba Gmail con archivo", "Este es el cuerpo del mensaje para Gmail con archivo.", attachment);
        
        EmailService dataFastServices2 = new EmailService("192.168.11.6", 25, "noreply@datafast.com.ec", "", false);
        dataFastServices2.sendEmail("noreply@datafast.com.ec", "jmunoz@datafast.com.ec", "Prueba Correo", "Este es el cuerpo del mensaje para SMTP.");
        
        //File attachment = new File("C:\\Java\\Test.xlsx");
        dataFastServices2.sendEmail("noreply@datafast.com.ec", "jmunoz@datafast.com.ec", "Prueba Gmail con archivo", "Este es el cuerpo del mensaje para Gmail con archivo.", attachment);
        
        //app.verificarConectividad("smtp.gmail.com", 465);
        //app.verificarConectividad("192.168.11.31", 465);
        //app.verificarConectividad("192.168.11.31", 587);
        //app.verificarConectividad("192.168.11.31", 25);
        //app.verificarConectividad("192.168.11.6", 465);
        //app.verificarConectividad("192.168.11.6", 587);
        //app.verificarConectividad("192.168.11.6", 25);
        //app.verificarConectividad("smtp.office365.com", 465);
        //app.verificarConectividad("smtp.office365.com", 587);
        //app.verificarConectividad("smtp.office365.com", 25);
        //pruebas fallidas
        //app.verificaEmailKevin();
        //app.verificaEmailJessy1();
        //app.verificaEmailJessy2();
        //app.verificaEmailJessy3();
        //app.verificaEmailJessy4();
        //Pruebas Exitosa
        //app.verificaEmailKevin();
        app.verificaEmailJessy4();
        //Pruebas
        app.verificaEmailJessy5();
    }

    public void verificaEmailKevin() {
        log.info("Verificando kevin...");
        Properties props = crearPropiedadesSMTP("smtp.gmail.com", 465);
        props.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("kevin.arauzg@gmail.com", "yjnf xwlo mkbv ybqh");
            }
        });

        try {
            conectarYEnviarCorreo(session, "smtp.gmail.com", "kevin.arauzg@gmail.com", "yjnf xwlo mkbv ybqh");
            log.info("Conexión con el servidor SMTP exitosa kevin!");
            enviarCorreoKevin(session);
        } catch (Exception e) {
            log.error("Error al enviar el correo kevin", e);
        }
    }

    public void verificaEmailJessy1() {
        log.info("Verificando Jessy1...");
        Properties props = crearPropiedadesSMTP("192.168.11.31", 465);
        props.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("notificacionesti@datafast.com.ec", "5ygcckxV");
            }
        });

        try {
            conectarYEnviarCorreo(session, "192.168.11.31", "notificacionesti@datafast.com.ec", "5ygcckxV");
            log.info("Conexión con el servidor SMTP exitosa Jessy1!");
            enviarCorreoJessy(session);
        } catch (Exception e) {
            log.error("Error al enviar el correo Jessy1", e);
        }
    }

    public void verificaEmailJessy2() {
        log.info("Verificando Jessy2...");
        Properties props = crearPropiedadesSMTP("192.168.11.31", 587);
        props.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("notificacionesti@datafast.com.ec", "5ygcckxV");
            }
        });

        try {
            conectarYEnviarCorreo(session, "192.168.11.31", "notificacionesti@datafast.com.ec", "5ygcckxV");
            log.info("Conexión con el servidor SMTP exitosa Jessy2!");
            enviarCorreoJessy(session);
        } catch (Exception e) {
            log.error("Error al enviar el correo Jessy2", e);
        }
    }

    public void verificaEmailJessy3() {
        log.info("Verificando Jessy3...");
        Properties props = crearPropiedadesSMTP("192.168.11.31", 25);
        props.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("notificacionesti@datafast.com.ec", "5ygcckxV");
            }
        });

        try {
            conectarYEnviarCorreo(session, "192.168.11.31", "notificacionesti@datafast.com.ec", "5ygcckxV");
            log.info("Conexión con el servidor SMTP exitosa Jessy3!");
            enviarCorreoJessy(session);
        } catch (Exception e) {
            log.error("Error al enviar el correo Jessy3", e);
        }
    }

    public void verificaEmailJessy4() {
        log.info("Verificando Jessy4...");
        Properties props = crearPropiedadesSMTP("192.168.11.6", 25);
        props.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("notificacionesti@datafast.com.ec", "5ygcckxV");
            }
        });

        try {
            conectarYEnviarCorreo(session, "192.168.11.6", "notificacionesti@datafast.com.ec", "5ygcckxV");
            log.info("Conexión con el servidor SMTP exitosa Jessy4!");
            enviarCorreoJessy(session);
        } catch (Exception e) {
            log.error("Error al enviar el correo Jessy4", e);
        }
    }
    
    public void verificaEmailJessy5() {
        log.info("Verificando Jessy5...");
        Properties props = crearPropiedadesSMTP("192.168.11.6", 25);
        props.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("noreply@datafast.com.ec", "");
            }
        });

        try {
            conectarYEnviarCorreo(session, "192.168.11.6", "noreply@datafast.com.ec", "");
            log.info("Conexión con el servidor SMTP exitosa Jessy5!");
            enviarCorreoJessy(session);
        } catch (Exception e) {
            log.error("Error al enviar el correo Jessy5", e);
        }
    }

    private void conectarYEnviarCorreo(Session session, String smtpHost, String username, String password) throws Exception {
        Transport transport = session.getTransport("smtp");
        transport.connect(smtpHost, username, password);
        transport.close();
    }

    private void enviarCorreoKevin(Session session) throws Exception {
        Message message = crearMensaje(session, "kevin.arauzg@gmail.com", "kevin.arauzg@gmail.com");
        message.setSubject("Prueba de Conexión y Envío");
        message.setText("Este correo confirma que la conexión al servidor SMTP fue exitosa y el correo ha sido enviado.");
        Transport.send(message);
        log.info("Correo enviado exitosamente a Kevin!");
    }

    private void enviarCorreoJessy(Session session) throws Exception {
        Message message = crearMensaje(session, "notificacionesti@datafast.com.ec", "kevin.arauzg@gmail.com");
        message.setSubject("Prueba de Conexión y Envío");
        message.setText("Este correo confirma que la conexión al servidor SMTP fue exitosa y el correo ha sido enviado.");
        Transport.send(message);
        log.info("Correo enviado exitosamente a Jessy!");
    }

    private Message crearMensaje(Session session, String from, String to) throws Exception {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        return message;
    }

    private Properties crearPropiedadesSMTP(String host, int port) {
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", String.valueOf(port));
        props.put("mail.smtp.auth", "true");
        if (port == 465) {
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.socketFactory.port", String.valueOf(port));
        } else if (port == 587) {
            // Usar STARTTLS
            props.put("mail.smtp.starttls.enable", "true");
        }
        return props;
    }

    private void verificarConectividad(String smtpHost, int smtpPort) {
        try (Socket socket = new Socket(smtpHost, smtpPort)) {
            log.info("Conexión exitosa a " + smtpHost + " en el puerto " + smtpPort);
        } catch (Exception e) {
            log.error("No se pudo conectar a " + smtpHost + " en el puerto " + smtpPort, e);
        }
    }
}
