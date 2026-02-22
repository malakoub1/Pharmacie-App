package dao;

import dao.VenteDAO;
import model.Vente;
import util.DBConnection;
import java.sql.Date;


import java.sql.*;
import java.util.*;

public class VenteDAOImpl implements VenteDAO {

    @Override
    public Vente create(Vente v) {
        String sql = "INSERT INTO vente(medicament_id, date_vente, quantite) VALUES (?,?,?)";
        
        try (Connection cn = DBConnection.getInstance().getCn();
             PreparedStatement ps = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, v.getMedicamentId());
            ps.setDate(2, v.getDateVente());
            ps.setInt(3, v.getQuantite());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) v.setId(rs.getInt(1));
            }
            return v;
        } catch (SQLException e) {
            throw new RuntimeException("Create Vente échoué", e);
        }
    }

    @Override
    public boolean update(Vente v) {
        String sql = "UPDATE vente SET medicament_id=?, date_vente=?, quantite=? WHERE id=?";

        try (Connection cn = DBConnection.getInstance().getCn();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, v.getMedicamentId());
            ps.setDate(2, v.getDateVente());
            ps.setInt(3, v.getQuantite());
            ps.setInt(4, v.getId());
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new RuntimeException("Update Vente échoué", e);
        }
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM vente WHERE id=?";
        try (Connection cn = DBConnection.getInstance().getCn();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new RuntimeException("Delete Vente échoué", e);
        }
    }

    @Override
    public Optional<Vente> findById(int id) {
        String sql = "SELECT * FROM vente WHERE id=?";
        try (Connection cn = DBConnection.getInstance().getCn();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return Optional.empty();
                return Optional.of(map(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("FindById Vente échoué", e);
        }
    }

    @Override
    public List<Vente> findAll() {
        String sql =
            "SELECT v.id, v.medicament_id, v.date_vente, v.quantite, m.nom AS medicamentNom " +
            "FROM vente v " +
            "JOIN medicament m ON v.medicament_id = m.id " +
            "ORDER BY v.id DESC";

        List<Vente> list = new ArrayList<>();
        

        try (Connection cn = DBConnection.getInstance().getCn();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(map(rs));
            }
            return list;

        } catch (SQLException e) {
            throw new RuntimeException("FindAll Vente échoué", e);
        }
    }

    @Override
public List<Vente> findByMedicament(int medicamentId) {
    List<Vente> list = new ArrayList<>();
    String sql = "SELECT * FROM vente WHERE medicament_id = ?";

    try (Connection cn = DBConnection.getInstance().getCn();
         PreparedStatement ps = cn.prepareStatement(sql)) {

        ps.setInt(1, medicamentId);
        
        try (ResultSet rs = ps.executeQuery()) {
    while (rs.next()) {
            list.add(new Vente(
                    rs.getInt("id"),
                    rs.getInt("medicament_id"),
                    rs.getDate("date_vente"),
                    rs.getInt("quantite")
            ));
        
    }
}

    } catch (Exception e) {
       throw new RuntimeException("Find ventes par medicament échoué", e);
    }

    return list;
}

    
    @Override
    public List<Vente> findBetweenDates(java.util.Date d1, java.util.Date d2) {
    String sql = "SELECT v.id, v.medicament_id, v.date_vente, v.quantite, m.nom AS medicamentNom\n" +
           "FROM vente v\n" +
            "JOIN medicament m ON v.medicament_id = m.id\n" +
             "WHERE v.date_Vente BETWEEN ? AND ?";
    List<Vente> list = new ArrayList<>();
    try (Connection cn = DBConnection.getInstance().getCn();
         PreparedStatement ps = cn.prepareStatement(sql)) {

        ps.setDate(1, new java.sql.Date(d1.getTime()));
        ps.setDate(2, new java.sql.Date(d2.getTime()));

        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(map(rs));
        }
        return list;

    } catch (SQLException e) {
        throw new RuntimeException("Recherche vente entre dates échouée", e);
    }
}


    @Override
    public Map<String, Integer> statsVentesParFamille() {
        String sql =
            "SELECT m.famille AS famille, SUM(v.quantite) AS total " +
            "FROM vente v " +
            "JOIN medicament m ON m.id = v.medicament_id " +
            "GROUP BY m.famille " +
            "ORDER BY total DESC";
        Map<String, Integer> map = new LinkedHashMap<>();
        try (Connection cn = DBConnection.getInstance().getCn();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                map.put(rs.getString("famille"), rs.getInt("total"));
            }
            return map;
        } catch (SQLException e) {
            throw new RuntimeException("Stats ventes par famille échoué", e);
        }
    }

    private Vente map(ResultSet rs) throws SQLException {
    Vente v = new Vente();
    v.setId(rs.getInt("id"));
    v.setMedicamentId(rs.getInt("medicament_id"));
    v.setDateVente(rs.getDate("date_vente"));
    v.setQuantite(rs.getInt("quantite"));
    v.setMedicamentNom(rs.getString("medicamentNom"));

    return v;
}
}
