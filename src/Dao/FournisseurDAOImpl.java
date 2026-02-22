package dao;

import dao.FournisseurDAO;
import model.Fournisseur;
import util.DBConnection;

import java.sql.*;
import java.util.*;

public class FournisseurDAOImpl implements FournisseurDAO {

    @Override
    public Fournisseur create(Fournisseur f) {
        String sql = "INSERT INTO fournisseur(nom, ville, contact) VALUES (?,?,?)";
        
        try (Connection cn = DBConnection.getInstance().getCn();
             PreparedStatement ps = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, f.getNom());
            ps.setString(2, f.getVille());
            ps.setString(3, f.getContact());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) f.setId(rs.getInt(1));
            }
            return f;
        } catch (SQLException e) {
            throw new RuntimeException("Create Fournisseur échoué", e);
        }
    }

    @Override
    public boolean update(Fournisseur f) {
        String sql = "UPDATE fournisseur SET nom=?, ville=?, contact=? WHERE id=?";
        try (Connection cn = DBConnection.getInstance().getCn();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, f.getNom());
            ps.setString(2, f.getVille());
            ps.setString(3, f.getContact());
            ps.setInt(4, f.getId());
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new RuntimeException("Update Fournisseur échoué", e);
        }
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM fournisseur WHERE id=?";
        try (Connection cn = DBConnection.getInstance().getCn();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new RuntimeException("Delete Fournisseur échoué", e);
        }
    }

    @Override
    public Optional<Fournisseur> findById(int id) {
        String sql = "SELECT * FROM fournisseur WHERE id=?";
        try (Connection cn = DBConnection.getInstance().getCn();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return Optional.empty();
                return Optional.of(map(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("FindById Fournisseur échoué", e);
        }
    }

    @Override
    public List<Fournisseur> findAll() {
        String sql = "SELECT * FROM fournisseur ORDER BY id DESC";
        List<Fournisseur> list = new ArrayList<>();
        try (Connection cn = DBConnection.getInstance().getCn();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) list.add(map(rs));
            return list;
        } catch (SQLException e) {
            throw new RuntimeException("FindAll Fournisseur échoué", e);
        }
    }

    private Fournisseur map(ResultSet rs) throws SQLException {
        return new Fournisseur(
                rs.getInt("id"),
                rs.getString("nom"),
                rs.getString("ville"),
                rs.getString("contact")
        );
    }
}
