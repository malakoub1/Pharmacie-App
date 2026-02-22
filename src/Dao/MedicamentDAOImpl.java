package dao;

import dao.MedicamentDAO;
import model.Medicament;
import util.DBConnection;

import java.sql.*;
import java.util.*;

public class MedicamentDAOImpl implements MedicamentDAO {

    @Override
    public Medicament create(Medicament m) {
        String sql = "INSERT INTO medicament(nom,famille,prix,stock,seuil_alerte,fournisseur_id) VALUES (?,?,?,?,?,?)";
        
        try (Connection cn = DBConnection.getInstance().getCn();
             PreparedStatement ps = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, m.getNom());
            ps.setString(2, m.getFamille());
            ps.setDouble(3, m.getPrix());
            ps.setInt(4, m.getStock());
            ps.setInt(5, m.getSeuilAlerte());
            if (m.getFournisseurId() == null) ps.setNull(6, Types.INTEGER);
            else ps.setInt(6, m.getFournisseurId());

            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) m.setId(rs.getInt(1));
            }
            return m;
        } catch (SQLException e) {
            throw new RuntimeException("Create Medicament échoué", e);
        }
    }


    @Override
    public boolean update(Medicament m) {
        String sql = "UPDATE medicament SET nom=?, famille=?, prix=?, stock=?, seuil_alerte=?, fournisseur_id=? WHERE id=?";
        try (Connection cn = DBConnection.getInstance().getCn();

             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, m.getNom());
            ps.setString(2, m.getFamille());
            ps.setDouble(3, m.getPrix());
            ps.setInt(4, m.getStock());
            ps.setInt(5, m.getSeuilAlerte());
            if (m.getFournisseurId() == null) ps.setNull(6, Types.INTEGER);
            else ps.setInt(6, m.getFournisseurId());
            ps.setInt(7, m.getId());

            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new RuntimeException("Update Medicament échoué", e);
        }
    }
    
    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM medicament WHERE id=?";
        try (Connection cn = DBConnection.getInstance().getCn();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new RuntimeException("Delete Medicament échoué", e);
        }
    }
    
    @Override
  public List<Medicament> findByFournisseur(int fournisseurId) {
    String sql = "SELECT * FROM medicament WHERE fournisseur_id=? ORDER BY nom";
    List<Medicament> list = new ArrayList<>();

    try (Connection cn = DBConnection.getInstance().getCn();
         PreparedStatement ps = cn.prepareStatement(sql)) {

        ps.setInt(1, fournisseurId);
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(map(rs));
        }
        return list;

    } catch (SQLException e) {
        throw new RuntimeException("Filtrage medicament par fournisseur échoué", e);
    }
}
    @Override
public List<String> getAllFamilles() {
    List<String> familles = new ArrayList<>();
    String sql = "SELECT DISTINCT famille FROM medicament";

    try (Connection cn = DBConnection.getInstance().getCn();
         PreparedStatement ps = cn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            familles.add(rs.getString("famille"));
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return familles;
}

    @Override
    public Optional<Medicament> findById(int id) {
        String sql = "SELECT * FROM medicament WHERE id=?";
        try (Connection cn = DBConnection.getInstance().getCn();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return Optional.empty();
                return Optional.of(map(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Find Medicament échoué", e);
        }
    }

    @Override
    public List<Medicament> findAll() {
        String sql = "SELECT * FROM medicament ORDER BY id DESC";
        List<Medicament> list = new ArrayList<>();
        try (Connection cn = DBConnection.getInstance().getCn();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(map(rs));
            return list;
        } catch (SQLException e) {
            throw new RuntimeException("FindAll Medicament échoué", e);
        }
    }

    @Override
    public List<Medicament> findByFamille(String famille) {
        String sql = "SELECT * FROM medicament WHERE famille=? ORDER BY nom";
        List<Medicament> list = new ArrayList<>();
        try (Connection cn = DBConnection.getInstance().getCn();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, famille);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(map(rs));
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException("Filtre famille échoué", e);
        }
    }

    @Override
    public List<Medicament> findLowStock() {
        String sql = "SELECT * FROM medicament WHERE stock <= seuil_alerte ORDER BY stock ASC";
        List<Medicament> list = new ArrayList<>();
        try (Connection cn = DBConnection.getInstance().getCn();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(map(rs));
            return list;
        } catch (SQLException e) {
            throw new RuntimeException("Low stock échoué", e);
        }
    }

    @Override
    public boolean updateStock(int medicamentId, int newStock) {
        String sql = "UPDATE medicament SET stock=? WHERE id=?";
        try (Connection cn = DBConnection.getInstance().getCn();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, newStock);
            ps.setInt(2, medicamentId);
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new RuntimeException("Update stock échoué", e);
        }
    }

    private Medicament map(ResultSet rs) throws SQLException {
        Integer fid = rs.getObject("fournisseur_id") == null ? null : rs.getInt("fournisseur_id");
        return new Medicament(
                rs.getInt("id"),
                rs.getString("nom"),
                rs.getString("famille"),
                rs.getDouble("prix"),
                rs.getInt("stock"),
                rs.getInt("seuil_alerte"),
                fid
        );
    }
}
