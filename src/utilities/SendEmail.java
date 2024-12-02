package utilities;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;
import java.util.Random;

public class SendEmail {

    /**
     * Phương thức gửi email chứa mã OTP.
     *
     * @param recipient Email người nhận.
     * @param otp Mã OTP để gửi.
     */
   
    public void sendOtpEmail(String recipient, String otp) {
        // Cấu hình SMTP server
        final String host = "smtp.gmail.com";
        final String senderEmail = "pharmahome.help@gmail.com";
        
        // Lấy mật khẩu từ biến môi trường (để bảo mật tốt hơn).
        final String senderPassword = "gcxb ovkg akwn cyoh";

        if (senderPassword == null || senderPassword.isEmpty()) {
            System.err.println("ERROR: Missing environment variable EMAIL_PASSWORD. Please set it before running.");
            return;
        }

        // Thuộc tính cho giao thức SMTP
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        // Tạo session với thông tin xác thực
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });
        
        

        try {
            // Tạo nội dung email
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject("Your OTP Code");
            message.setText("Your OTP code is: " + otp);

            // Gửi email
            Transport.send(message);
            System.out.println("Email đã được gửi thành công tới: " + recipient);
        } catch (MessagingException e) {
            System.err.println("Lỗi khi gửi email: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public String generateOTP() {
        String numbers = "0123456789";
        Random random = new Random();
        StringBuilder otp = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            otp.append(numbers.charAt(random.nextInt(numbers.length())));
        }
        return otp.toString();
    }
    /**
     * Phương thức main để kiểm tra chức năng gửi email.
     */
    public static void main(String[] args) {
        // Địa chỉ email người nhận
        String recipient = "nhantran03042021@gmail.com";
        // Mã OTP để gửi
       // String otp = generateOTP()+"";
        //;sendOtpEmail(recipient, otp);
    }
}
