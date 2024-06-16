package conexion_prueba.utils;

import java.io.File;
import java.net.Socket;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;

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
        sendEmail(from, to, subject, body, null);
    }

    public void sendEmail(String from, String to, String subject, String body, File attachment) {
        Properties props = crearPropiedadesSMTP();
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            verificarConectividad(smtpHost, smtpPort);
            conectarCorreo(session);
            log.info("Conexión con el servidor exitosa!");

            if (attachment != null) {
                enviarCorreoConAdjunto(session, from, to, subject, body, attachment);
            } else {
                enviarCorreo(session, from, to, subject, body);
            }
        } catch (Exception e) {
            log.error("Error al enviar el correo a " + to, e);
        }
    }

    private void enviarCorreo(Session session, String from, String to, String subject, String body) throws Exception {
        Message message = crearMensaje(session, from, to);
        message.setSubject(subject);
        message.setText(body);
        Transport.send(message);
        log.info("Correo enviado exitosamente a " + to);
    }

    private void enviarCorreoConAdjunto(Session session, String from, String to, String subject, String body, File attachment) throws Exception {
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(subject);

        MimeBodyPart textPart = new MimeBodyPart();
        textPart.setText(body);

        MimeBodyPart attachmentPart = new MimeBodyPart();
        DataSource source = new FileDataSource(attachment);
        attachmentPart.setDataHandler(new DataHandler(source));
        attachmentPart.setFileName(attachment.getName());

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(textPart);
        multipart.addBodyPart(attachmentPart);

        message.setContent(multipart);
        Transport.send(message);
        log.info("Correo con archivo adjunto enviado exitosamente a " + to);
    }

    private Message crearMensaje(Session session, String from, String to) throws MessagingException {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        return message;
    }

    private Properties crearPropiedadesSMTP() {
        Properties props = new Properties();
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", String.valueOf(smtpPort));
        props.put("mail.smtp.auth", "true");
        if (useSSL) {
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.socketFactory.port", String.valueOf(smtpPort));
        } else if(smtpPort == 587){
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
}
