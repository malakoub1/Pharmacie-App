# 📚 Gestion d'une Pharmacie

---

## 📁 Table de matieres

- [🗂 Contexte](#-Contexte)
- [❓ Problématique](#-Problématique)
- [🎯 Objectif](#-Objectif)
- [📊 Diagrammes](#-Diagrammes)
- [🗃 Tables de Données](#-Tables-de-Données)
- [✨ Fonctionnalités Principales](#-Fonctionnalités-Principales)
- [🔍 Requêtes SQL](#-Requêtes-sql)
- [🏛 Architecture](#-Architecture)
- [🛠 Technologies Utilisées](#-Technologies-Utilisées)
- [🎥 Démo Vidéo](#-Démo-video)

---
## 🗂 Contexte :

Dans le domaine pharmaceutique, la gestion efficace des médicaments, des fournisseurs et des ventes est essentielle pour assurer la disponibilité des produits et éviter les ruptures de stock.
Les méthodes traditionnelles basées sur des fichiers Excel ou une gestion manuelle peuvent entraîner des erreurs, une mauvaise organisation et une perte de temps.
Afin d’améliorer la gestion quotidienne d’une pharmacie, il est nécessaire de mettre en place une application informatique permettant d’automatiser les opérations principales comme la gestion du stock, le suivi des ventes et l’organisation des fournisseurs.

---
## ❓ Problématique:

Les pharmacies rencontrent souvent plusieurs difficultés dans leur gestion quotidienne :
- Difficulté à suivre le stock réel des médicaments
- Risque de rupture ou de surstock
- Gestion manuelle des ventes peu fiable
- Manque de centralisation des informations fournisseurs
- Absence de statistiques pour analyser les ventes
- Ces limites rendent la gestion moins efficace et compliquent la prise de décision.

---
## 🎯 Objectif:
L’objectif de ce projet est de développer une application desktop permettant de faciliter la gestion d’une pharmacie à travers une interface simple et intuitive.
L’application vise à :
- Gérer les médicaments et leur stock
- Organiser les fournisseurs
- Enregistrer les ventes
- Générer des statistiques utiles à la prise de décision

---
## L'application doit :
- Permettre l’ajout, modification et suppression des médicaments
- Gérer les fournisseurs liés aux médicaments
- Enregistrer les ventes effectuées
- Mettre à jour automatiquement le stock après chaque vente
- Afficher les statistiques (ex : ventes par famille)

---
## 📊 Diagrammes :

##  Diagramme use case:
<img width="830" height="499" alt="Diagramme Cas d&#39;utilisation " src="https://github.com/user-attachments/assets/738b7082-8886-4231-91da-26c45bca44fc" />

---
##  Diagramme de classe :
<img width="473" height="365" alt="Diagramme de classe " src="https://github.com/user-attachments/assets/5bca089c-1d80-4dfc-ae4f-abf02454a1bf" />

---
## 🗃 Tables de Données:

-Medicament ( id , nom , famille , prix , stock , seuil_stock , fournisseur_id)

-Fournisseur ( id , nom , ville , contact)

-Vente( id , medicament_id , date vente , quantite )

---
## ✨ Fonctionnalités Principales:

### 1. Gestion des médicaments
- **Ajouter un médicament** : Formulaire pour saisir le nom, famille, prix, stock et fournisseur.
- **Modifier un médicament** : Mettre à jour les informations d’un médicament existant.
- **Supprimer un médicament** : Retirer un médicament de la base de données.
- **Lister les médicaments** : Afficher tous les médicaments dans un tableau
- **Alerte de stock faible par email** : envoie automatique d'un email lorsque le stock devient inférieur au seuil défini.


### 2. Gestion des fournisseurs
- **Ajouter un fournisseur** : Saisie des informations d’un fournisseur.
- **Modifier un fournisseur** : Mettre à jour les données d’un fournisseur existant.
- **Supprimer un fournisseur** : Retirer un fournisseur de la base de données.
- **Lister les fournisseurs** : Afficher la liste des fournisseurs enregistrés.

  ### 3. Gestion des ventes
- **Enregistrer une vente** : Sélectionner un médicament et la quantité .
- **Mise à jour du stock** : Le stock est décrémenté après chaque vente.
- **Supprimer une vente** : Retirer une vente de la base de données.
- **Lister les ventes** : Afficher la liste des ventes enregistrés.

 ### 4. Filtrage et recherche
- **Filtrer les médicaments par famille** : Afficher les médicaments appartenant à une famille spécifique.
- **Filtrer les médicaments par fournisseur** : Voir les médicaments fournis par un fournisseur donné.
- **Filtrer les ventes par date** : Consulter les ventes sur une période donnée.

---
##  🔍 Requêtes sql 

### Tables

```sql

CREATE TABLE fournisseur (
                             id INT AUTO_INCREMENT PRIMARY KEY,
                             nom VARCHAR(100) NOT NULL,
                             ville VARCHAR(80) NOT NULL,
                             contact VARCHAR(120) NOT NULL
);

CREATE TABLE medicament (
                            id INT AUTO_INCREMENT PRIMARY KEY,
                            nom VARCHAR(120) NOT NULL,
                            famille VARCHAR(80) NOT NULL,
                            prix DECIMAL(10,2) NOT NULL CHECK (prix >= 0),
                            stock INT NOT NULL CHECK (stock >= 0),
                            seuil_alerte INT NOT NULL DEFAULT 5 CHECK (seuil_alerte >= 0),
                            fournisseur_id INT,
                            CONSTRAINT fk_med_fournisseur FOREIGN KEY (fournisseur_id) REFERENCES fournisseur(id)
                                ON UPDATE CASCADE ON DELETE SET NULL
);

CREATE TABLE vente (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       medicament_id INT NOT NULL,
                       date_vente DATE NOT NULL,
                       quantite INT NOT NULL CHECK (quantite > 0),
                       CONSTRAINT fk_vente_med FOREIGN KEY (medicament_id) REFERENCES medicament(id)
                           ON UPDATE CASCADE ON DELETE RESTRICT
);
```
## 🏛  Architecture
![Architecture](https://github.com/user-attachments/assets/599286f3-2f4f-40d6-b8be-7030876be02d)

---
## 🛠 Technologies Utilisées:

- **Framework d'interface graphique :** Java Swing
- **Base de données :** MySQL
- **Accès aux données :** JDBC
- **Outils de développement :**
NetBeans (IDE Java)
StarUml (Outil de diagramme)
- **Gestion de base de données :** MYSQL Workbench

---
## 🎥 Démo video


https://github.com/user-attachments/assets/a7d9d23d-5c3b-4210-a3cf-fe8c67f3cb22


