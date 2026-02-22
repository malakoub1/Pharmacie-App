package Service;

import dao.MedicamentDAO;
import dao.VenteDAO;
import dao.MedicamentDAOImpl;
import dao.VenteDAOImpl;
import jakarta.mail.MessagingException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import model.Medicament;
import model.Vente;

import java.util.Optional;

public class VenteService {
    private final VenteDAO venteDAO = new VenteDAOImpl();
    private final MedicamentDAO medDAO = new MedicamentDAOImpl();
    private final StockAlertService alertService = new StockAlertService();

    public Vente enregistrerVente(Vente v) throws MessagingException {
    if (v.getQuantite() <= 0)
        throw new IllegalArgumentException("Quantité invalide");

    Optional<Medicament> opt = medDAO.findById(v.getMedicamentId());
      if (!opt.isPresent())
    throw new IllegalArgumentException("Médicament introuvable");

    Medicament m = opt.get();


    if (m.getStock() < v.getQuantite()) {
        throw new IllegalStateException("Stock insuffisant. Stock actuel: " + m.getStock());
    }

    Vente saved = venteDAO.create(v);

    int newStock = m.getStock() - v.getQuantite();
    medDAO.updateStock(m.getId(), newStock);

    m.setStock(newStock);
    alertService.checkAndAlert(m);

    return saved;
}
    public List<Vente> findByMedicament(int medicamentId) {
    return venteDAO.findByMedicament(medicamentId);
}
public java.util.List<model.Vente> listerTous() {
    return venteDAO.findAll();
}
public boolean modifier(Vente v) {
    if (v.getId() <= 0) throw new IllegalArgumentException("ID vente invalide");
    if (v.getQuantite() <= 0) throw new IllegalArgumentException("Quantité invalide");
    return venteDAO.update(v);
}
public List<Vente> findBetweenDates(Date d1, Date d2) {
    return venteDAO.findBetweenDates(d1, d2);
}

public boolean supprimer(int id) {
    if (id <= 0) throw new IllegalArgumentException("ID vente invalide");
    return venteDAO.delete(id);
}


    public Map<String, Integer> statsVentesParFamille() {
        return venteDAO.statsVentesParFamille();
    }
}


