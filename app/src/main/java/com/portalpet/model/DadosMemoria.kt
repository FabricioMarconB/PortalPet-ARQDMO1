package com.portalpet.model

import com.portalpet.R

object DadosMemoria {

    const val PERFIL_TUTOR = "TUTOR"
    const val PERFIL_CLINICA = "CLINICA"

    const val STATUS_AGENDADA = "Agendada"
    const val STATUS_CONFIRMADA = "Confirmada"
    const val STATUS_CONCLUIDA = "Concluída"

    private var proximoIdPet = 4
    private var proximoIdConsulta = 4

    private val usuarios = mutableListOf(
        Usuario(
            nome = "Ana Souza",
            email = "tutor@portalpet.com",
            senha = "123",
            telefone = "16999991111",
            perfil = PERFIL_TUTOR
        ),
        Usuario(
            nome = "Dr. Carlos Mendes",
            email = "clinica@portalpet.com",
            senha = "123",
            telefone = "1633339999",
            perfil = PERFIL_CLINICA
        )
    )

    private val pets = mutableListOf(
        Pet(
            id = 1,
            nome = "Thor",
            especieRaca = "Cão - Golden Retriever",
            idade = "3 anos",
            foto = R.drawable.ic_pet_cao,
            tutorNome = "Ana Souza",
            tutorTelefone = "16999991111",
            tutorEmail = "tutor@portalpet.com"
        ),
        Pet(
            id = 2,
            nome = "Luna",
            especieRaca = "Gato - Siamês",
            idade = "2 anos",
            foto = R.drawable.ic_pet_gato,
            tutorNome = "Ana Souza",
            tutorTelefone = "16999991111",
            tutorEmail = "tutor@portalpet.com"
        ),
        Pet(
            id = 3,
            nome = "Bidu",
            especieRaca = "Cão - Schnauzer",
            idade = "5 anos",
            foto = R.drawable.ic_pet_cao,
            tutorNome = "Bruno Lima",
            tutorTelefone = "16988882222",
            tutorEmail = "bruno@email.com"
        )
    )

    private val consultas = mutableListOf(
        Consulta(
            id = 1,
            pet = pets[0],
            dataHora = "2026-11-25 14:30",
            motivo = "Vacinação anual (V10)",
            status = STATUS_CONFIRMADA,
            observacoes = "Trazer carteira de vacinação."
        ),
        Consulta(
            id = 2,
            pet = pets[2],
            dataHora = "2026-11-20 09:00",
            motivo = "Check-up dermatológico",
            status = STATUS_AGENDADA,
            observacoes = "Aguardando avaliação."
        ),
        Consulta(
            id = 3,
            pet = pets[1],
            dataHora = "2026-11-22 10:00",
            motivo = "Revisão pós-castração",
            status = STATUS_AGENDADA,
            observacoes = "Verificar pontos."
        )
    )

    fun autenticar(email: String, senha: String): Usuario? {
        return usuarios.find {
            it.email.equals(email.trim(), ignoreCase = true) && it.senha == senha
        }
    }

    fun listarPetsPorTutor(emailTutor: String): List<Pet> {
        return pets
            .filter { it.tutorEmail.equals(emailTutor, ignoreCase = true) }
            .sortedBy { it.nome }
    }

    fun adicionarPet(
        nome: String,
        especieRaca: String,
        idade: String,
        foto: Int,
        tutor: Usuario
    ) {
        val novoPet = Pet(
            id = proximoIdPet++,
            nome = nome,
            especieRaca = especieRaca,
            idade = idade,
            foto = foto,
            tutorNome = tutor.nome,
            tutorTelefone = tutor.telefone,
            tutorEmail = tutor.email
        )
        pets.add(novoPet)
    }

    fun listarConsultasPorTutor(emailTutor: String): List<Consulta> {
        return consultas
            .filter { it.pet.tutorEmail.equals(emailTutor, ignoreCase = true) }
            .sortedBy { it.dataHora }
    }

    fun listarTodasConsultas(): List<Consulta> {
        return consultas.sortedBy { it.dataHora }
    }

    fun agendarConsulta(pet: Pet, dataHora: String, motivo: String) {
        val novaConsulta = Consulta(
            id = proximoIdConsulta++,
            pet = pet,
            dataHora = dataHora,
            motivo = motivo,
            status = STATUS_AGENDADA,
            observacoes = "Aguardando atendimento da clínica."
        )
        consultas.add(novaConsulta)
    }

    fun atualizarConsulta(idConsulta: Int, novoStatus: String, novasObservacoes: String) {
        val indice = consultas.indexOfFirst { it.id == idConsulta }
        if (indice != -1) {
            val atual = consultas[indice]
            consultas[indice] = atual.copy(
                status = novoStatus,
                observacoes = novasObservacoes
            )
        }
    }
}