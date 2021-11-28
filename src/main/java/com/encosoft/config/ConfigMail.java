package com.encosoft.config;

import com.encosoft.modelo.Encomienda;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * @author echamaya
 */
public class ConfigMail {

    public void enviarCorreoRegistroEncomienda(String correoDestinatario, Encomienda encomienda) {
        final String SUBJECT = "ENCOSOFT - REGISTRO DE ENCOMIENDA";
        final String FROM = "encosoft.utp@gmail.com";
        final String PASSWORD = "encosoft.utp2021";
        final String HOST = "smtp.gmail.com";

        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", HOST);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM, PASSWORD);
            }
        });

        session.setDebug(true);

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(FROM));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(correoDestinatario));
            message.setSubject(SUBJECT);
            message.setContent("<h1>El registro de encomienda ha sido exitoso</h1>"
                    + "<hr/>"
                    + "<h2>Datos de la encomienda:</h2>"
                    + "<h2>Número de encomienda: " + encomienda.getId() + "</h2>"
                    + "<h2>Fecha de encomienda: " + encomienda.getFecha() + "</h2>"
                    + "<h2>Precio total de encomienda: S/. " + encomienda.getPreciototal() + "</h2>"
                    + "<hr/>"
                    + "<h2>Datos del repector:</h2>"
                    + "<h3>Apellido paterno: " + encomienda.getReceptorapepat() + "</h3>"
                    + "<h3>Apellido materno: " + encomienda.getReceptorapemat() + "</h3>"
                    + "<h3>Nombres: " + encomienda.getReceptornombres() + "</h3>", "text/html");
            System.out.println("enviando correo...");
            Transport.send(message);
            System.out.println("correo enviando.");
        } catch (MessagingException mex) {
            System.out.println("ocurrió un error al enviar el correo: " + mex.getMessage());
            mex.printStackTrace();
        }
    }

}
