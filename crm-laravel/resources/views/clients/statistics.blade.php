@extends('layouts.app')

@section('content')
<div class="container mx-auto px-4 py-8">
    <h1 class="text-3xl font-bold text-gray-800 mb-8 text-center">Statistiques des Clients et Interactions</h1>

    <!-- Statistiques générales -->
    <div class="grid grid-cols-1 md:grid-cols-2 gap-6 mb-8">
        <!-- Nombre total de clients -->
        <div class="bg-white p-6 rounded-lg shadow-md border border-gray-100 text-center">
            <h5 class="text-lg font-semibold text-gray-700 mb-2">Nombre total de clients</h5>
            <p class="text-4xl font-bold text-blue-600">{{ $totalClients }}</p>
        </div>

        <!-- Nombre total d'interactions -->
        <div class="bg-white p-6 rounded-lg shadow-md border border-gray-100 text-center">
            <h5 class="text-lg font-semibold text-gray-700 mb-2">Nombre total d'interactions</h5>
            <p class="text-4xl font-bold text-teal-600">{{ $totalInteractions }}</p>
        </div>
    </div>

    <!-- Graphique en barres pour les interactions par client -->
    <div class="bg-white p-6 rounded-lg shadow-md border border-gray-100">
        <h5 class="text-xl font-semibold text-gray-800 mb-4 text-center">Interactions par client</h5>
        <canvas id="interactionsPerClientChart" width="400" height="150"></canvas>
    </div>
</div>

<!-- Script pour initialiser le graphique -->
<script>
    // Données pour le graphique en barres (interactions par client)
    const interactionsPerClientData = {
        labels: {!! json_encode($interactionsPerClient->pluck('nom')) !!},
        datasets: [{
            label: 'Nombre d\'interactions',
            data: {!! json_encode($interactionsPerClient->pluck('interactions_count')) !!},
            backgroundColor: 'rgba(99, 102, 241, 0.6)', // Couleur indigo avec transparence
            borderColor: 'rgba(79, 70, 229, 1)', // Couleur indigo plus foncée
            borderWidth: 1,
            borderRadius: 8, // Bords arrondis pour les barres
            hoverBackgroundColor: 'rgba(99, 102, 241, 1)', // Couleur au survol
        }]
    };

    // Options pour le graphique en barres
    const interactionsPerClientOptions = {
        responsive: true,
        plugins: {
            legend: {
                display: false,
            },
            title: {
                display: false,
            }
        },
        scales: {
            y: {
                beginAtZero: true,
                grid: {
                    color: 'rgba(0, 0, 0, 0.05)', // Couleur de la grille
                },
                ticks: {
                    color: 'rgba(0, 0, 0, 0.6)', // Couleur des ticks
                }
            },
            x: {
                grid: {
                    display: false, // Masquer la grille de l'axe X
                },
                ticks: {
                    color: 'rgba(0, 0, 0, 0.6)', // Couleur des ticks
                }
            }
        },
        // Activer les animations
        animation: {
            duration: 1000, // Durée de l'animation en millisecondes
            easing: 'easeInOutQuad', // Type d'animation
        }
    };

    // Initialisation du graphique en barres
    const interactionsPerClientCtx = document.getElementById('interactionsPerClientChart').getContext('2d');
    new Chart(interactionsPerClientCtx, {
        type: 'bar',
        data: interactionsPerClientData,
        options: interactionsPerClientOptions
    });
</script>
@endsection
