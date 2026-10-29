package com.portalpet.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.portalpet.R
import com.portalpet.adapter.PetAdapter
import com.portalpet.databinding.ActivityTutorHomeBinding
import com.portalpet.model.DadosMemoria
import com.portalpet.model.Pet
import com.portalpet.model.Usuario

class TutorHomeActivity : AppCompatActivity() {

    companion object {
        const val TAG = "TutorHomeActivity"
    }

    private lateinit var binding: ActivityTutorHomeBinding
    private lateinit var usuarioLogado: Usuario
    private lateinit var pets: List<Pet>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate chamado")

        binding = ActivityTutorHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        usuarioLogado = intent.getSerializableExtra("usuario") as Usuario

        loadData()
        setupViews()
        setupListeners()
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume chamado - atualizando lista de pets")
        loadData()
        setupViews()
    }

    private fun loadData() {
        pets = DadosMemoria.listarPetsPorTutor(usuarioLogado.email)
            .sortedBy { it.nome }
    }

    private fun setupViews() {
        binding.tvTituloTutor.text = "${getString(R.string.titulo_tutor_home)} - ${usuarioLogado.nome}"
        val adapter = PetAdapter(this, pets)
        binding.listViewPets.adapter = adapter
    }

    private fun setupListeners() {
        binding.listViewPets.setOnItemClickListener { _, _, position, _ ->
            val petSelecionado = pets[position]
            val intent = Intent(this, AgendarConsultaActivity::class.java)
            intent.putExtra("pet", petSelecionado)
            startActivity(intent)
        }

        binding.btnNovoPet.setOnClickListener {
            val intent = Intent(this, CadastroPetActivity::class.java)
            intent.putExtra("usuario", usuarioLogado)
            startActivity(intent)
        }

        binding.btnMinhasConsultas.setOnClickListener {
            val intent = Intent(this, ConsultasTutorActivity::class.java)
            intent.putExtra("usuario", usuarioLogado)
            startActivity(intent)
        }

        binding.btnSobreClinica.setOnClickListener {
            val intent = Intent(this, SobreClinicaActivity::class.java)
            startActivity(intent)
        }

        binding.btnSairTutor.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}