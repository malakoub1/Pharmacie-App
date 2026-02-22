package Service;

import dao.FournisseurDAO;
import dao.FournisseurDAOImpl;
import model.Fournisseur;

import java.util.List;
import java.util.Optional;

public class FournisseurService {
    private final FournisseurDAO dao = new FournisseurDAOImpl();

    public Fournisseur ajouter(Fournisseur f) {
        validate(f);
        return dao.create(f);
    }

    public boolean modifier(Fournisseur f) {
        if (f.getId() <= 0) throw new IllegalArgumentException("ID fournisseur invalide");
        validate(f);
        return dao.update(f);
    }

    public boolean supprimer(int id) {
        if (id <= 0) throw new IllegalArgumentException("ID fournisseur invalide");
        return dao.delete(id);
    }

    public Optional<Fournisseur> chercherParId(int id) {
        if (id <= 0) return Optional.empty();
        return dao.findById(id);
    }

    public List<Fournisseur> listerTous() {
        return dao.findAll();
    }

    private void validate(Fournisseur f) {
        if (f == null) throw new IllegalArgumentException("Fournisseur null");
        if (isBlank(f.getNom())) throw new IllegalArgumentException("Nom fournisseur obligatoire");
        if (isBlank(f.getVille())) throw new IllegalArgumentException("Ville obligatoire");
        if (isBlank(f.getContact())) throw new IllegalArgumentException("Contact obligatoire");
    }

    private boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }
}
