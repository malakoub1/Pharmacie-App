package dao;

import model.Vente;

import java.util.List;
import java.util.Map;

public interface VenteDAO extends GenericDAO<Vente> {
    List<Vente> findByMedicament(int medicamentId);
    Map<String, Integer> statsVentesParFamille(); 
    List<Vente> findBetweenDates(java.util.Date d1, java.util.Date d2);


}
