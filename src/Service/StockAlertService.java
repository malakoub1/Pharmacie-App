package Service;

import jakarta.mail.MessagingException;
import model.Medicament;

public class StockAlertService {
    private final EmailService emailService = new EmailService();

    public void checkAndAlert(Medicament m) throws MessagingException {
        if (m.getStock() <= m.getSeuilAlerte()) {
            String subject = "ALERTE STOCK FAIBLE: " + m.getNom();
            String body = "Stock faible détecté\n"
                    + "Médicament: " + m.getNom() + "\n"
                    + "Famille: " + m.getFamille() + "\n"
                    + "Stock actuel: " + m.getStock() + "\n"
                    + "Seuil alerte: " + m.getSeuilAlerte() + "\n";
             emailService.send("malakoub87@gmail.com", subject, body);        
        }
    }
}
