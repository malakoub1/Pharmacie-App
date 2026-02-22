package model;

import java.sql.Date;

public class Vente {
    private int id;
    private int medicamentId;
    private Date dateVente;
    private int quantite;
    private String medicamentNom;

    public Vente() {}
    public Vente(int id, int medicamentId, Date dateVente, int quantite) {
        this.id = id; this.medicamentId = medicamentId; this.dateVente = dateVente; this.quantite = quantite;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getMedicamentNom() { return medicamentNom; }
    public void setMedicamentNom( String medicamentNom) { this.medicamentNom = medicamentNom; }
    public int getMedicamentId() { return medicamentId; }
    public void setMedicamentId(int medicamentId) { this.medicamentId = medicamentId; }
    public Date getDateVente() { return dateVente; }
    public void setDateVente(Date dateVente) { this.dateVente = dateVente; }
    public int getQuantite() { return quantite; }
    public void setQuantite(int quantite) { this.quantite = quantite; }
}