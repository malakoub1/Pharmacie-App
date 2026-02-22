package Service;

import dao.IUserDao;
import model.User;
import util.DBConnection;
import util.PasswordUtil;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Random;

public class UserService implements IUserDao {

    private final Connection connexion;

    public UserService() {
        connexion = DBConnection.getInstance().getCn();
    }
    
    @Override
    public boolean addUser(User user) {
        String sql = "INSERT INTO user (login, password_hash, salt, securityQuestion, security_answer_hash, email) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connexion.prepareStatement(sql)) {

            String salt = PasswordUtil.generateSalt();

            
            String passHash = PasswordUtil.hashPassword(user.getPassword(), salt);

            
            String answerHash = PasswordUtil.hashPassword(user.getSecurityAnswer(), salt);

            ps.setString(1, user.getLogin());
            ps.setString(2, passHash);
            ps.setString(3, salt);
            ps.setString(4, user.getSecurityQuestion());
            ps.setString(5, answerHash);
            ps.setString(6, user.getEmail());

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            System.out.println("Erreur ajout utilisateur : " + ex.getMessage());
            return false;
        }
    }

    @Override
    public User findUserByLogin(String login) {
        String sql = "SELECT login, password_hash, salt, securityQuestion, security_answer_hash, email " +
                     "FROM user WHERE login = ?";

        try (PreparedStatement ps = connexion.prepareStatement(sql)) {
            ps.setString(1, login);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    User u = new User();
                    u.setLogin(rs.getString("login"));
                    u.setPasswordHash(rs.getString("password_hash"));
                    u.setSalt(rs.getString("salt"));
                    u.setSecurityQuestion(rs.getString("securityQuestion"));
                    u.setSecurityAnswerHash(rs.getString("security_answer_hash"));
                    u.setEmail(rs.getString("email"));
                    return u;
                }
            }

        } catch (SQLException ex) {
            System.out.println("Erreur recherche utilisateur : " + ex.getMessage());
        }
        return null;
    }


    @Override
    public boolean authenticate(String login, String password) {
        User u = findUserByLogin(login);
        if (u == null) return false;

        return PasswordUtil.verifyPassword(password, u.getSalt(), u.getPasswordHash());
    }

    // RESET PASSWORD PAR QUESTION SECRETE

    public boolean resetPasswordBySecurityQuestion(String login, String securityAnswerPlain) {
        User user = findUserByLogin(login);

        if (user == null) {
            JOptionPane.showMessageDialog(null, "Utilisateur introuvable.");
            return false;
        }

        // vérifier la réponse secrète en Java (hash + salt)
        boolean okAnswer = PasswordUtil.verifyPassword(
                securityAnswerPlain,
                user.getSalt(),
                user.getSecurityAnswerHash()
        );

        if (!okAnswer) {
            JOptionPane.showMessageDialog(null, "Réponse secrète incorrecte.");
            return false;
        }

        String newPassword = generateTemporaryPassword();

        if (updatePassword(login, newPassword)) {
            sendPasswordByEmail(user.getEmail(), newPassword);
            JOptionPane.showMessageDialog(null, "Un nouveau mot de passe a été envoyé à votre adresse e-mail.");
            return true;
        }

        JOptionPane.showMessageDialog(null, "Échec de mise à jour du mot de passe.");
        return false;
    }


    public boolean updatePassword(String login, String newPasswordPlain) {
        User u = findUserByLogin(login);
        if (u == null) return false;

        String newHash = PasswordUtil.hashPassword(newPasswordPlain, u.getSalt());

        String sql = "UPDATE user SET password_hash = ? WHERE login = ?";
        try (PreparedStatement ps = connexion.prepareStatement(sql)) {
            ps.setString(1, newHash);
            ps.setString(2, login);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erreur mise à jour mot de passe : " + e.getMessage());
            return false;
        }
    }


    public String generateTemporaryPassword() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder(12);
        for (int i = 0; i < 12; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }

    private void sendPasswordByEmail(String recipientEmail, String newPassword) {
        try {
            String username = "malakoub87@gmail.com";
            String appPassword = "vkkowpbprsouzvfy"; // (mot de passe d'application)

            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");

            Session session = Session.getInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, appPassword);
                }
            });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject("Réinitialisation de votre mot de passe");
            message.setText("Votre nouveau mot de passe est : " + newPassword);

            Transport.send(message);

        } catch (MessagingException e) {
            System.out.println("Erreur envoi email : " + e.getMessage());
        }
    }
}