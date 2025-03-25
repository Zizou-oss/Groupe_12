<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Interaction extends Model
{
    use HasFactory;

    protected $fillable = [
        'client_id',
        'type',
        'notes',
        'date',
    ];

    /**
     * Relation avec le client.
     */
    public function client()
    {
        return $this->belongsTo(Client::class);
    }
}
