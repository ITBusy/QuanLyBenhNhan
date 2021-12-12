package com.QLBN.Poly.Utils;

import java.awt.Component;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

public class XOther {

    public static String IDVerify;

    public static String code() {
        String ma = null;
        for (int i = 0; i < 100000; i++) {
            ma = String.valueOf(100000 + Math.round(Math.random() * 899900));
        }
        return ma;
    }

    public static void sendCodeConfirm(Component parent, String toMail, String name) {
        try {
            final String formMail = "hunglamchan6@gmail.com";
            final String password = "Vanhung1995";

            final String subject = "Mã Xác Nhận";
            final String body = code();
            IDVerify = body;
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");

            props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
            props.put("mail.smtp.port", "465"); //SMTP Port
            props.setProperty("mail.pop3.socketFactory.class",
                    "javax.net.ssl.SSLSocketFactory");
            props.setProperty("mail.smtp.socketFactory.class",
                    "javax.net.ssl.SSLSocketFactory");
            props.setProperty("mail.smtp.socketFactory.fallback", "false");
            props.setProperty("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.startssl.enable", "true");
            props.put("mail.smtp.auth", "true");

            Authenticator auth = new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(formMail, password);
                }
            };
            Session session = Session.getInstance(props, auth);
            MimeMessage msg = new MimeMessage(session);

            msg.addHeader("Content-type", "text/HTML; charset = UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-transfer-Encoding", "8bit");
            msg.setFrom(new InternetAddress(formMail, "NoReply"));
            msg.setReplyTo(InternetAddress.parse(formMail, false));
            msg.setSubject(subject, "UTF-8");
            String htmlContent = "<h4 style=\"color: #333;\">Xin chào " + name + ",</h4>\n"
                    + "    <div style=\"display: flex;\">\n"
                    + "        <span style=\"color: #333;\">Mã thay đổi mật khẩu của bạn và\n"
                    + "            có hiệu lực trong 1 phút:</span>\n"
                    + "    </div>\n"
                    + "\n"
                    + "    <div style=\"display: flex;\">\n"
                    + "        <button style=\"height: 80px; width: 340px; margin: auto; align-items: center; \n"
                    + "                              justify-content: center; font-size: 3.5rem;border: 1px solid blue; color: white; \n"
                    + "                              text-align: center; background-color: lightgray; \" disable>" + body.substring(0, 3) + "-" + body.substring(3) + "</button>\n"
                    + "    </div>";
            msg.setContent(htmlContent, "text/html; charset=utf-8");
            msg.setSentDate(new Date());
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toMail, false));
            Transport.send(msg);
        } catch (MessagingException ex) {
            if (ex.getMessage().equals("Couldn't connect to host, port: smtp.gmail.com, 587; timeout -1")) {
                MsgBox.alert(parent, "Could't to connect due to interrupted network connection.Try again");
            } else {
                System.out.println("Error sendCodeConfirm: " + ex.getMessage());
            }
        } catch (UnsupportedEncodingException ex) {
            System.out.println("Error sendCodeConfirm: " + ex.getMessage());
        }
    }

    public static String MaHoaPass(String pass) {
        byte[] digest = null;
        String hashtext = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(pass.getBytes());
            digest = md5.digest();
            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, digest);
            // Convert message digest into hex value
            hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("Error MaHoaPass: " + ex.getMessage());
        }
        return hashtext.toUpperCase();
    }

    public static boolean convertPass(String txt, String passByte) {
        byte[] digest = null;
        String convert = null;
        String hashtext = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(txt.getBytes());
            digest = md5.digest();
            BigInteger no = new BigInteger(1, digest);
            // Convert message digest into hex value
            hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            convert = hashtext.toUpperCase();
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        return passByte.equals(convert);
    }

    public static String Rename(String name) {
        String[] split = name.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < split.length; i++) {
            sb.append(Character.toUpperCase(split[i].charAt(0))).append(split[i].substring(1)).append(" ");
        }
        return sb.toString().trim();
    }

    public static BigDecimal parse(final String amount) {
        Locale locale = new Locale("vi", "VN");
        BigDecimal BigDecimal = null;
        try {
            final NumberFormat format = NumberFormat.getNumberInstance(locale);
            if (format instanceof DecimalFormat) {
                ((DecimalFormat) format).setParseBigDecimal(true);
            }
            BigDecimal = (BigDecimal) format.parse(amount.replace(",", ".").replaceAll("[^\\d.,]", ""));
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return BigDecimal;
    }

    public static String convertCurrency(Double money) {
        DecimalFormat format = new DecimalFormat("#,###,###");
        return format.format(money);
    }

    public static String convertString(Double money) {
        DecimalFormat format = new DecimalFormat("###");
        return format.format(money);
    }

    public static double convertDouble(String money) {
        return Double.parseDouble(money.replaceAll("(,| VND)", ""));
    }

    public static void main(String[] args) {
        String a = "39,000,000 VND";
        System.out.println(convertDouble(a));
    }

    static class DigitsDocument extends PlainDocument {

        private char b;

        public DigitsDocument(char b) {
            this.b = b;
        }

        @Override
        public void insertString(int offs, String str, AttributeSet a)
                throws BadLocationException {
            if (str == null) {
                return;
            }
            char[] addedFigures = str.toCharArray();
//            System.out.println(addedFigures);
            char c;
            for (int i = addedFigures.length; i > 0; i--) {
                c = addedFigures[i - 1];
                if (Character.isDigit(c) || c == b) {
                    super.insertString(offs, new String(new Character(c).toString()), a);
                }
            }
        }
    }

    static class AlphabeticDocument extends PlainDocument {

        private char d;

        public AlphabeticDocument(char d) {
            this.d = d;
        }

        @Override
        public void insertString(int offs, String str, AttributeSet a)
                throws BadLocationException {
            if (str == null) {
                return;
            }
            char[] addedFigures = str.toCharArray();
//            System.out.println(addedFigures);
            char c;
            for (int i = addedFigures.length; i > 0; i--) {
                c = addedFigures[i - 1];
                if (Character.isAlphabetic(c) || c == d) {
                    super.insertString(offs, new String(new Character(c).toString()), a);
                }
            }
        }
    }

    public static void isDigits(char c, JTextField... txt) {
        for (JTextField txt1 : txt) {
            txt1.setDocument(new DigitsDocument(c));
        }
    }

    public static void isAlphabetic(char c, JTextField... txt) {
        for (JTextField txt1 : txt) {
            txt1.setDocument(new AlphabeticDocument(c));
        }
    }
}
