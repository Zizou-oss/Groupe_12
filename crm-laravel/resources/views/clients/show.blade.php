@extends('layouts.app')

@section('content')
<div class="container">
    <h1>Détails du Client</h1>
    <div class="card">
        <div class="card-body">
            <h5 class="card-title">{{ $client->nom }}</h5>
            <p class="card-text"><strong>Email :</strong> {{ $client->email }}</p>
            <p class="card-text"><strong>Téléphone :</strong> {{ $client->telephone }}</p>
            <p class="card-text"><strong>Entreprise :</strong> {{ $client->entreprise }}</p>
            <a href="{{ route('clients.edit', $client->id) }}" class="btn btn-warning">Modifier</a>
            <form action="{{ route('clients.destroy', $client->id) }}" method="POST" style="display:inline;">
                @csrf
                @method('DELETE')
                <button type="submit" class="btn btn-danger" onclick="return confirm('Êtes-vous sûr de vouloir supprimer ce client ?')">Supprimer</button>
            </form>
        </div>
    </div>
</div>
@endsection
