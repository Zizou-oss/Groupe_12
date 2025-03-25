import pandas as pd
import matplotlib.pyplot as plt
from reportlab.lib.pagesizes import letter
from reportlab.pdfgen import canvas
from datetime import datetime
from sqlalchemy import create_engine

# Configuration de matplotlib pour utiliser un backend non graphique
import matplotlib
matplotlib.use('Agg')  # Utiliser un backend non graphique

# Connexion MySQL avec SQLAlchemy
engine = None
try:
    # Établir la connexion à la base de données
    engine = create_engine('mysql+pymysql://root:@localhost/crm_laravel')

    # Charger les données des clients
    query = "SELECT * FROM clients"
    df = pd.read_sql(query, engine)

    # Vérifier si la colonne 'entreprise' existe dans la table
    if 'entreprise' not in df.columns:
        print("Erreur : La colonne 'entreprise' n'existe pas dans la table 'clients'.")
        print("Colonnes disponibles :", df.columns.tolist())
        exit()

    # Analyse des données : compter le nombre de clients par entreprise
    entreprise_counts = df['entreprise'].value_counts()

    # Générer un graphique
    plt.figure(figsize=(8, 6))
    entreprise_counts.plot(kind='bar', color='skyblue')
    plt.title("Répartition des clients par entreprise", fontsize=16)
    plt.xlabel("Entreprise", fontsize=14)
    plt.ylabel("Nombre de clients", fontsize=14)
    plt.xticks(rotation=45)
    plt.tight_layout()
    plt.savefig("client_report.png", dpi=300)  # Sauvegarder le graphique en haute résolution

    # Générer un rapport PDF
    pdf_file = "client_report.pdf"
    c = canvas.Canvas(pdf_file, pagesize=letter)
    
    # Ajouter un titre au PDF
    c.setFont("Helvetica-Bold", 16)
    c.drawString(100, 750, "Rapport des Clients")
    
    # Ajouter la date de génération du rapport
    c.setFont("Helvetica", 12)
    c.drawString(100, 730, f"Date de génération : {datetime.now().strftime('%Y-%m-%d %H:%M:%S')}")
    
    # Ajouter des informations générales
    c.drawString(100, 700, "Nombre total de clients :")
    c.drawString(250, 700, str(len(df)))
    
    # Insérer le graphique dans le PDF
    c.drawImage("client_report.png", 100, 500, width=400, height=200)
    
    # Ajouter un tableau des clients
    c.drawString(100, 450, "Liste des clients :")
    y_position = 430
    for index, row in df.iterrows():
        client_info = f"{row['nom']} ({row['entreprise']}) - Tél: {row['telephone']}, Email: {row['email']}"
        c.drawString(100, y_position, client_info)
        y_position -= 20
        if y_position < 50:  # Nouvelle page si nécessaire
            c.showPage()
            y_position = 750
    
    # Sauvegarder le PDF
    c.save()

    print(f"Rapport généré : {pdf_file}")

except Exception as e:
    print(f"Erreur : {e}")
finally:
    # Fermer la connexion à la base de données si elle existe
    if engine:
        engine.dispose()
        print("Connexion à la base de données fermée.")