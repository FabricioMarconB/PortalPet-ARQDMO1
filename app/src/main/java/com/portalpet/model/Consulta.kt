package com.portalpet.model

import java.io.Serializable

data class Consulta(
    val id: Int,
    val pet: Pet,
    val dataHora: String,
    val motivo: String,
    val status: String,
    val observacoes: String
) : Serializable