# Projets CRM

Ce dossier contient trois projets : Laravel, Java et Python, tous utilisant la base de données `crm-laravel`.

## 1. Base de données
- Le fichier `crm-laravel.sql` se trouve dans le dossier `Database`.
- Pour importer la base de données :
  1. Créez une base de données vide nommée `crm-laravel` dans MySQL.
  2. Importez le fichier `crm-laravel.sql` via phpMyAdmin ou la ligne de commande.

## 2. Projet Laravel
### Prérequis
- PHP 8.x
- Laravel 14
- Composer
- WAMP/XAMPP (pour MySQL et Apache)

### Installation
1. Accédez au dossier `crm-laravel`.
2. Exécutez `composer install` pour installer les dépendances.
3. Copiez `.env.example` vers `.env` et configurez les informations de la base de données.
4. Exécutez `php artisan migrate` pour importer les tables.
5. Lancez le serveur avec `php artisan serve`.
6. Email: `admin@crm.com`, Password: `password123` le compte admin pour les deux applications Laravel et Java.


## 3. Projet Java
### Prérequis
- JDK 17 ou supérieur
- Maven (si utilisé)

### Installation
1. Accédez au dossier `JavaBureau`.`CrmJava` contient le code source de l'app et `Executable de l'app` contient le fichier jar d'execution de l'application Java
2. Si vous utilisez Maven, exécutez `mvn clean install`.
3. Ouvrez le projet dans un IDE (Eclipse, IntelliJ, etc.) Pour notre part Eclipse.
4. Configurez les informations de la base de données dans le fichier de configuration.
5. Exécutez le projet.
6. Email: `admin@crm.com`, Password: `password123` le compte admin pour les deux applications Laravel et Java.


## 4. Projet Python
### Prérequis
- Python 3.x
- pip

### Installation
1. Accédez au dossier `crm_python_project_v2`.
2. Exécutez `pip install -r requirements.txt` pour installer les dépendances.
3. Configurez les informations de la base de données dans le fichier de configuration.
4. Exécutez `python crm_analysis.py` pour lancer le projet.
5. Le script génère automatiquement en fonction de la base de donnée deux fichiers un en pdf et l'autre en png

## 5. Contact
Pour toute question, contactez-nous à thiombianoa12@gmail.com