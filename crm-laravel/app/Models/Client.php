<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Client extends Model
{
    use HasFactory;

    protected $fillable = [
        'nom',
        'email',
        'telephone',
        'entreprise',
    ];

    /**
     * Relation avec les interactions.
     */
    public function interactions()
    {
        return $this->hasMany(Interaction::class);
    }
}
