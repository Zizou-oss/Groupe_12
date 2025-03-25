@extends('layouts.app')

@section('content')
<div class="container">
    <h1>Profil de l'Administrateur</h1>
    <div class="card">
        <div class="card-body">
            <p><strong>Nom :</strong> {{ $admin['name'] }}</p>
            <p><strong>Email :</strong> {{ $admin['email'] }}</p>
        </div>
    </div>
</div>
@endsection
