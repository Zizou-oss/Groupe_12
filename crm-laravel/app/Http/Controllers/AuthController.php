<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;

class AuthController extends Controller
{
    // Afficher le formulaire de connexion
    public function showLoginForm()
    {
        return view('auth.login');
    }

    // Traiter la soumission du formulaire de connexion
    public function login(Request $request)
    {
        // Informations de connexion stockées dans le code
        $validCredentials = [
            'email' => 'admin@crm.com',
            'password' => 'password123',
        ];

        // Valider les champs du formulaire
        $request->validate([
            'email' => 'required|email',
            'password' => 'required',
        ]);

        // Vérifier les informations de connexion
        if ($request->email === $validCredentials['email'] && $request->password === $validCredentials['password']) {
            // Authentification réussie
            session(['admin_logged_in' => true]); // Stocker l'état de connexion dans la session
            return redirect()->route('clients.index'); // Rediriger vers le tableau de bord
        } else {
            // Authentification échouée
            return back()->withErrors([
                'login_error' => 'Les informations de connexion sont incorrectes.',
            ])->withInput();
        }
    }

    // Afficher le profil de l'admin
    public function profile()
    {
        // Vérifier si l'admin est connecté
        if (!session('admin_logged_in')) {
            return redirect()->route('login')->withErrors([
                'error' => 'Vous devez être connecté pour accéder à cette page.',
            ]);
        }

        // Informations de l'admin (simulées)
        $admin = [
            'name' => 'Admin Aziz',
            'email' => 'admin@crm.com',
        ];

        // Passer les informations à la vue
        return view('admin.profile', compact('admin'));
    }

    // Déconnexion de l'admin
    public function logout(Request $request)
    {
        // Supprimer l'état de connexion de la session
        $request->session()->forget('admin_logged_in');
        $request->session()->regenerateToken();

        // Rediriger vers la page de connexion
        return redirect()->route('login')->with('success', 'Vous avez été déconnecté avec succès.');
    }
}
