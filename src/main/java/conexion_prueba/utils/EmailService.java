package conexion_prueba.utils;

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

public class EmailService {

    private static final Logger log = LoggerFactory.getLogger(EmailService.class);

    private final String smtpHost;
    private final int smtpPort;
    private final String username;
    private final String password;
    private final boolean useSSL;

    public EmailService(String smtpHost, int smtpPort, String username, String password, boolean useSSL) {
        this.smtpHost = smtpHost;
        this.smtpPort = smtpPort;
        this.username = username;
        this.password = password;
        this.useSSL = useSSL;
    }

    public void sendEmail(String from, String to, String subject, String body) {
        Properties props = crearPropiedadesSMTP();
        //Properties props = new Properties();
        /*props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", String.valueOf(smtpPort));
        props.put("mail.smtp.auth", "true");

        if (useSSL) {
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.socketFactory.port", String.valueOf(smtpPort));
        } else {
            props.put("mail.smtp.starttls.enable", "true");
        }*/

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
        	verificarConectividad(smtpHost, smtpPort);
        	
        	conectarCorreo(session);
        	log.info("Conexión con el servidor exitosa!");
        	
            /*Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(body);
            Transport.send(message);*/
        	enviarCorreo(session,from,to,subject,body);
            //log.info("Correo enviado exitosamente a " + to);
        } catch (Exception e) {
            log.info("Error al enviar el correo a " + to, e);
        }
    }
    
    private Properties crearPropiedadesSMTP() {
        Properties props = new Properties();
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", String.valueOf(smtpPort));
        props.put("mail.smtp.auth", "true");
        if (useSSL) {
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.socketFactory.port", String.valueOf(smtpPort));
        } else {
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
    
    private void conectarCorreo(Session session) throws Exception {
        Transport transport = session.getTransport("smtp");
        transport.connect(smtpHost, username, password);
        transport.close();
    }
    
    private void enviarCorreo(Session session,String from, String to, String subject, String body) throws Exception {
        Message message = crearMensaje(session, from, to);
        message.setSubject(subject);
        message.setText(body);
        Transport.send(message);
        log.info("Correo enviado exitosamente");
        log.info("Desde: "+from);
        log.info("Para: "+to);
    }
    
    private Message crearMensaje(Session session, String from, String to) throws Exception {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        return message;
    }
    
}
