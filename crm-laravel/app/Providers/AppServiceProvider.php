<?php

namespace App\Http\Controllers;

use App\Models\Client; // Importez le modèle Client
use App\Models\Interaction; // Importez le modèle Interaction
use Illuminate\Http\Request;

class ClientController extends Controller
{
    /**
     * Affiche la liste des clients.
     */
    public function index()
    {
        // Récupérer tous les clients depuis la base de données
        $clients = Client::all();

        // Passer les clients à la vue 'clients.index'
        return view('clients.index', compact('clients'));
    }

    /**
     * Affiche le formulaire de création d'un client.
     */
    public function create()
    {
        // Retourne la vue 'clients.create' pour ajouter un nouveau client
        return view('clients.create');
    }

    /**
     * Enregistre un nouveau client dans la base de données.
     */
    public function store(Request $request)
    {
        // Valider les données du formulaire
        $request->validate([
            'nom' => 'required|string|max:255',
            'email' => 'required|email|unique:clients,email',
            'telephone' => 'required|string|max:20',
            'entreprise' => 'required|string|max:255',
        ]);

        // Créer un nouveau client avec les données validées
        Client::create($request->only(['nom', 'email', 'telephone', 'entreprise']));

        // Rediriger vers la liste des clients avec un message de succès
        return redirect()->route('clients.index')
                         ->with('success', 'Client ajouté avec succès.');
    }

    /**
     * Affiche les détails d'un client spécifique.
     */
    public function show(string $id)
    {
        // Récupérer le client par son ID
        $client = Client::findOrFail($id);

        // Retourne la vue 'clients.show' avec les détails du client
        return view('clients.show', compact('client'));
    }

    /**
     * Affiche le formulaire de modification d'un client.
     */
    public function edit(string $id)
    {
        // Récupérer le client par son ID
        $client = Client::findOrFail($id);

        // Retourne la vue 'clients.edit' avec les données du client à modifier
        return view('clients.edit', compact('client'));
    }

    /**
     * Met à jour un client dans la base de données.
     */
    public function update(Request $request, string $id)
    {
        // Valider les données du formulaire
        $request->validate([
            'nom' => 'required|string|max:255',
            'email' => 'required|email|unique:clients,email,' . $id,
            'telephone' => 'required|string|max:20',
            'entreprise' => 'required|string|max:255',
        ]);

        // Récupérer le client par son ID
        $client = Client::findOrFail($id);

        // Mettre à jour le client avec les données validées
        $client->update($request->only(['nom', 'email', 'telephone', 'entreprise']));

        // Rediriger vers la liste des clients avec un message de succès
        return redirect()->route('clients.index')
                         ->with('success', 'Client mis à jour avec succès.');
    }

    /**
     * Supprime un client de la base de données.
     */
    public function destroy(string $id)
    {
        // Récupérer le client par son ID
        $client = Client::findOrFail($id);

        // Supprimer le client
        $client->delete();

        // Rediriger vers la liste des clients avec un message de succès
        return redirect()->route('clients.index')
                         ->with('success', 'Client supprimé avec succès.');
    }

    /**
     * Affiche les statistiques des clients et des interactions.
     */
    public function statistics()
    {
        // Nombre total de clients
        $totalClients = Client::count();

        // Nombre total d'interactions
        $totalInteractions = Interaction::count();

        // Nombre d'interactions par type
        $interactionsByType = Interaction::selectRaw('type, count(*) as total')
                                        ->groupBy('type')
                                        ->get();

        // Nombre d'interactions par client
        $interactionsPerClient = Client::withCount('interactions')
                                      ->orderBy('interactions_count', 'desc')
                                      ->get();

        // Retourne la vue 'clients.statistics' avec les données
        return view('clients.statistics', compact(
            'totalClients',
            'totalInteractions',
            'interactionsByType',
            'interactionsPerClient'
        ));
    }
}
