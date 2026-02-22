package model;

public class Medicament {
    private int id;
    private String nom;
    private String famille;
    private double prix;
    private int stock;
    private int seuilAlerte;
    private Integer fournisseurId; // nullable

    public Medicament() {}

    public Medicament(int id, String nom, String famille, double prix, int stock, int seuilAlerte, Integer fournisseurId) {
        this.id = id; this.nom = nom; this.famille = famille; this.prix = prix;
        this.stock = stock; this.seuilAlerte = seuilAlerte; this.fournisseurId = fournisseurId;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getFamille() { return famille; }
    public void setFamille(String famille) { this.famille = famille; }
    public double getPrix() { return prix; }
    public void setPrix(double prix) { this.prix = prix; }
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
    public int getSeuilAlerte() { return seuilAlerte; }
    public void setSeuilAlerte(int seuilAlerte) { this.seuilAlerte = seuilAlerte; }
    public Integer getFournisseurId() { return fournisseurId; }
    public void setFournisseurId(Integer fournisseurId) { this.fournisseurId = fournisseurId; }

    @Override public String toString() { return nom ; }
}
