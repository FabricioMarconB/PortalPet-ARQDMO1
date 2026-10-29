package com.portalpet.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.portalpet.R
import com.portalpet.databinding.ActivityCadastroPetBinding
import com.portalpet.model.DadosMemoria
import com.portalpet.model.Pet
import com.portalpet.model.Usuario

class CadastroPetActivity : AppCompatActivity() {

    companion object {
        const val TAG = "CadastroPetActivity"
    }

    private lateinit var binding: ActivityCadastroPetBinding
    private lateinit var tutor: Usuario
    private lateinit var petRascunho: Pet
    private var fotoSelecionada: Int = R.drawable.ic_pet_cao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate chamado")

        binding = ActivityCadastroPetBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadData()
        setupViews()
        setupListeners()
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause chamado - guardando rascunho do formulário")
        petRascunho = Pet(
            id = 0,
            nome = binding.edtNomePet.text.toString(),
            especieRaca = binding.edtEspecieRaca.text.toString(),
            idade = binding.edtIdadePet.text.toString(),
            foto = fotoSelecionada,
            tutorNome = tutor.nome,
            tutorTelefone = tutor.telefone,
            tutorEmail = tutor.email
        )
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, "onSaveInstanceState chamado")
        outState.putSerializable("pet_rascunho", petRascunho)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.d(TAG, "onRestoreInstanceState chamado")
        petRascunho = savedInstanceState.getSerializable("pet_rascunho") as Pet
        fotoSelecionada = petRascunho.foto
        restaurarCampos()
    }

    private fun loadData() {
        tutor = intent.getSerializableExtra("usuario") as Usuario
    }

    private fun setupViews() {
        binding.imgPreviewPet.setImageResource(fotoSelecionada)
    }

    private fun restaurarCampos() {
        binding.edtNomePet.setText(petRascunho.nome)
        binding.edtEspecieRaca.setText(petRascunho.especieRaca)
        binding.edtIdadePet.setText(petRascunho.idade)
        binding.imgPreviewPet.setImageResource(fotoSelecionada)
    }

    private fun setupListeners() {
        binding.btnIconeCao.setOnClickListener {
            fotoSelecionada = R.drawable.ic_pet_cao
            binding.imgPreviewPet.setImageResource(fotoSelecionada)
        }

        binding.btnIconeGato.setOnClickListener {
            fotoSelecionada = R.drawable.ic_pet_gato
            binding.imgPreviewPet.setImageResource(fotoSelecionada)
        }

        binding.btnSalvarPet.setOnClickListener {
            salvarPet()
        }

        binding.btnVoltarCadastroPet.setOnClickListener {
            finish()
        }
    }

    private fun salvarPet() {
        val nome = binding.edtNomePet.text.toString().trim()
        val especieRaca = binding.edtEspecieRaca.text.toString().trim()
        val idade = binding.edtIdadePet.text.toString().trim()

        if (nome.isEmpty() || especieRaca.isEmpty() || idade.isEmpty()) {
            Toast.makeText(
                this,
                getString(R.string.erro_campos_obrigatorios),
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        DadosMemoria.adicionarPet(
            nome = nome,
            especieRaca = especieRaca,
            idade = idade,
            foto = fotoSelecionada,
            tutor = tutor
        )

        Toast.makeText(this, getString(R.string.msg_pet_salvo), Toast.LENGTH_SHORT).show()
        finish()
    }
}