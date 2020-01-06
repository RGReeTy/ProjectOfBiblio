package HomeLibrary.mail;


import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class SendEmail {
    //работает!!!!!!!

    public void sendEmailAsAdministrator(EmailAuthenticator emailAuthenticator, User user, Book book) {

        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.socketFactory.port", 465);
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", 465);

        Session session = Session.getInstance(properties,emailAuthenticator);

        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress("AdminEmail@gmail.com"));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(user.geteMail()));
            message.setSubject("Исправлена библиотека");
            message.setText("Добавлена книга"+book.toString());

            Transport.send(message);

            System.out.println("Письмо успешно отправлено");

        } catch (MessagingException e) {
            System.out.println("Письмо не отправилось");
            e.printStackTrace();
        }

    }

    public void sendEmailAsUser(EmailAuthenticator emailAuthenticator, User user, String string) {

        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.socketFactory.port", 465);
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", 465);

        Session session = Session.getInstance(properties,emailAuthenticator);

        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(user.geteMail()));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress("adminEmail@gmail.com"));
            message.setSubject("Предлагаю добавить книгу");
            message.setText(string);

            Transport.send(message);

            System.out.println("Письмо успешно отправлено");

        } catch (MessagingException e) {
            System.out.println("Письмо не отправилось");
            e.printStackTrace();
        }

    }
}

