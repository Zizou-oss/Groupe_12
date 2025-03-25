<<<<<<< HEAD
<p align="center"><a href="https://laravel.com" target="_blank"><img src="https://raw.githubusercontent.com/laravel/art/master/logo-lockup/5%20SVG/2%20CMYK/1%20Full%20Color/laravel-logolockup-cmyk-red.svg" width="400" alt="Laravel Logo"></a></p>

<p align="center">
<a href="https://github.com/laravel/framework/actions"><img src="https://github.com/laravel/framework/workflows/tests/badge.svg" alt="Build Status"></a>
<a href="https://packagist.org/packages/laravel/framework"><img src="https://img.shields.io/packagist/dt/laravel/framework" alt="Total Downloads"></a>
<a href="https://packagist.org/packages/laravel/framework"><img src="https://img.shields.io/packagist/v/laravel/framework" alt="Latest Stable Version"></a>
<a href="https://packagist.org/packages/laravel/framework"><img src="https://img.shields.io/packagist/l/laravel/framework" alt="License"></a>
</p>

## About Laravel

Laravel is a web application framework with expressive, elegant syntax. We believe development must be an enjoyable and creative experience to be truly fulfilling. Laravel takes the pain out of development by easing common tasks used in many web projects, such as:

- [Simple, fast routing engine](https://laravel.com/docs/routing).
- [Powerful dependency injection container](https://laravel.com/docs/container).
- Multiple back-ends for [session](https://laravel.com/docs/session) and [cache](https://laravel.com/docs/cache) storage.
- Expressive, intuitive [database ORM](https://laravel.com/docs/eloquent).
- Database agnostic [schema migrations](https://laravel.com/docs/migrations).
- [Robust background job processing](https://laravel.com/docs/queues).
- [Real-time event broadcasting](https://laravel.com/docs/broadcasting).

Laravel is accessible, powerful, and provides tools required for large, robust applications.

## Learning Laravel

Laravel has the most extensive and thorough [documentation](https://laravel.com/docs) and video tutorial library of all modern web application frameworks, making it a breeze to get started with the framework.

You may also try the [Laravel Bootcamp](https://bootcamp.laravel.com), where you will be guided through building a modern Laravel application from scratch.

If you don't feel like reading, [Laracasts](https://laracasts.com) can help. Laracasts contains thousands of video tutorials on a range of topics including Laravel, modern PHP, unit testing, and JavaScript. Boost your skills by digging into our comprehensive video library.

## Laravel Sponsors

We would like to extend our thanks to the following sponsors for funding Laravel development. If you are interested in becoming a sponsor, please visit the [Laravel Partners program](https://partners.laravel.com).

### Premium Partners

- **[Vehikl](https://vehikl.com/)**
- **[Tighten Co.](https://tighten.co)**
- **[WebReinvent](https://webreinvent.com/)**
- **[Kirschbaum Development Group](https://kirschbaumdevelopment.com)**
- **[64 Robots](https://64robots.com)**
- **[Curotec](https://www.curotec.com/services/technologies/laravel/)**
- **[Cyber-Duck](https://cyber-duck.co.uk)**
- **[DevSquad](https://devsquad.com/hire-laravel-developers)**
- **[Jump24](https://jump24.co.uk)**
- **[Redberry](https://redberry.international/laravel/)**
- **[Active Logic](https://activelogic.com)**
- **[byte5](https://byte5.de)**
- **[OP.GG](https://op.gg)**

## Contributing

Thank you for considering contributing to the Laravel framework! The contribution guide can be found in the [Laravel documentation](https://laravel.com/docs/contributions).

## Code of Conduct

In order to ensure that the Laravel community is welcoming to all, please review and abide by the [Code of Conduct](https://laravel.com/docs/contributions#code-of-conduct).

## Security Vulnerabilities

If you discover a security vulnerability within Laravel, please send an e-mail to Taylor Otwell via [taylor@laravel.com](mailto:taylor@laravel.com). All security vulnerabilities will be promptly addressed.

## License

The Laravel framework is open-sourced software licensed under the [MIT license](https://opensource.org/licenses/MIT).
=======
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
>>>>>>> e6674586b47988664d1b3683f54ee8d5fb73626e
