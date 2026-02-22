package dao;

import model.Medicament;
import java.util.List;

public interface MedicamentDAO extends GenericDAO<Medicament> {
    List<Medicament> findByFamille(String famille);
    List<Medicament> findLowStock();
    boolean updateStock(int medicamentId, int newStock);
    List<Medicament> findByFournisseur(int fournisseurId);
    List<String> getAllFamilles();

}
