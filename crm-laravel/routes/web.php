<?php

use Illuminate\Support\Facades\Route;
use App\Http\Controllers\ClientController;
use App\Http\Controllers\InteractionController;
use App\Http\Controllers\AuthController; // Ajoutez cette ligne

// Route pour la page de connexion
Route::get('/', [AuthController::class, 'showLoginForm'])->name('login');

// Route pour traiter la soumission du formulaire de connexion
Route::post('/login', [AuthController::class, 'login'])->name('login.submit');

// Vos routes existantes
Route::get('/clients/statistics', [ClientController::class, 'statistics'])->name('clients.statistics');
Route::resource('clients', ClientController::class);
Route::resource('interactions', InteractionController::class);
// Route pour le profil de l'admin
Route::get('/admin/profile', [AuthController::class, 'profile'])->name('admin.profile');

// Route pour la déconnexion
Route::post('/logout', [AuthController::class, 'logout'])->name('logout');


