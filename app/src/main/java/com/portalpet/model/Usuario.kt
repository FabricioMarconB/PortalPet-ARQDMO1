package com.portalpet.model

import java.io.Serializable

data class Usuario(
    val nome: String,
    val email: String,
    val senha: String,
    val telefone: String,
    val perfil: String
) : Serializable