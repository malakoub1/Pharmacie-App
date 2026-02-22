package Service;

import dao.MedicamentDAO;
import dao.MedicamentDAOImpl;
import jakarta.mail.MessagingException;
import model.Medicament;

import java.util.List;
import java.util.Optional;

public class MedicamentService {
    private final MedicamentDAO dao = new MedicamentDAOImpl();
    private final StockAlertService alertService = new StockAlertService();
    
    private void validate(Medicament m) {
        if (m == null) throw new IllegalArgumentException("Médicament null");
        if (isBlank(m.getNom())) throw new IllegalArgumentException("Nom obligatoire");
        if (isBlank(m.getFamille())) throw new IllegalArgumentException("Famille obligatoire");
        if (m.getPrix() < 0) throw new IllegalArgumentException("Prix doit être >= 0");
        if (m.getStock() < 0) throw new IllegalArgumentException("Stock doit être >= 0");
        if (m.getSeuilAlerte() < 0) throw new IllegalArgumentException("Seuil doit être >= 0");
    }
    
    public Medicament ajouter(Medicament m) throws MessagingException {
        validate(m);
        Medicament saved = dao.create(m);
        alertService.checkAndAlert(saved);
        return saved;
    }

    public boolean modifier(Medicament m) throws MessagingException {
        if (m.getId() <= 0) throw new IllegalArgumentException("ID médicament invalide");
        validate(m);
        boolean ok = dao.update(m);
        if (ok) alertService.checkAndAlert(m);
        return ok;
    }

    public boolean supprimer(int id) {
        if (id <= 0) throw new IllegalArgumentException("ID médicament invalide");
        return dao.delete(id);
    }

    public Optional<Medicament> chercherParId(int id) {
        if (id <= 0) return Optional.empty();
        return dao.findById(id);
    }

    public List<Medicament> listerTous() {
        return dao.findAll();
    }

    public List<Medicament> filtrerParFamille(String famille) {
        if (isBlank(famille)) return dao.findAll();
        return dao.findByFamille(famille);
    }
    
    public List<String> getAllFamilles() {
    return dao.getAllFamilles();
}

    public List<Medicament> stockFaible() {
        return dao.findLowStock();
    }

    public boolean modifierStock(int medicamentId, int newStock) {
        if (medicamentId <= 0) throw new IllegalArgumentException("ID médicament invalide");
        if (newStock < 0) throw new IllegalArgumentException("Stock doit être >= 0");
        boolean ok = dao.updateStock(medicamentId, newStock);
        // on peut relire et déclencher alerte si besoin
     dao.findById(medicamentId).ifPresent(m -> {
    try {
        alertService.checkAndAlert(m);
    } catch (Exception e) {
        System.out.println("Alerte email non envoyée: " + e.getMessage());
    }
});        return ok;
    }

    
    
public java.util.List<model.Medicament> filtrerParFournisseur(model.Fournisseur fournisseur) {
    if (fournisseur == null) return java.util.Collections.emptyList();
    int fid = fournisseur.getId();
    java.util.List<model.Medicament> res = new java.util.ArrayList<>();
    for (model.Medicament m : dao.findAll()) {
        if (m.getFournisseurId() != null && m.getFournisseurId() == fid) {
            res.add(m);
        }
    }
    return res;
}


    private boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }
}
