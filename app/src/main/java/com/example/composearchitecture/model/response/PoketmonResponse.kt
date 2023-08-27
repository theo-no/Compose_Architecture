package com.example.composearchitecture.model.response

data class PoketmonResponse (
    val species: Species,
    val sprites: Sprites
) {
    data class Species(var name: String)

    data class Sprites(var frontDefault: String)
}