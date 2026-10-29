package com.portalpet.model

import java.io.Serializable

data class Pet(
    val id: Int,
    val nome: String,
    val especieRaca: String,
    val idade: String,
    val foto: Int,
    val tutorNome: String,
    val tutorTelefone: String,
    val tutorEmail: String
) : Serializable