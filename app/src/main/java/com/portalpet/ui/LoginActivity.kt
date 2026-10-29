package com.portalpet.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import com.portalpet.R
import com.portalpet.databinding.ActivityLoginBinding
import com.portalpet.model.DadosMemoria

class LoginActivity : AppCompatActivity() {

    companion object {
        const val TAG = "LoginActivity"
    }

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate chamado")

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadData()
        setupViews()
        setupListeners()
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart chamado")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume chamado")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause chamado")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop chamado")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart chamado")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy chamado")
    }

    private fun loadData() {
        Log.d(TAG, "loadData: repositório em memória pronto")
    }

    private fun setupViews() {
        Log.d(TAG, "setupViews: interface de login inicializada")
    }

    private fun setupListeners() {
        binding.btnEntrar.setOnClickListener {
            realizarLogin()
        }

        binding.btnDayNight.setOnClickListener {
            mudarTema()
        }

        binding.btnIdioma.setOnClickListener {
            alternarIdioma()
        }
    }

    private fun realizarLogin() {
        val email = binding.editEmail.text.toString()
        val senha = binding.editSenha.text.toString()

        if (email.isBlank() || senha.isBlank()) {
            Toast.makeText(this, getString(R.string.erro_login_vazio), Toast.LENGTH_SHORT).show()
            return
        }

        val usuario = DadosMemoria.autenticar(email, senha)
        if (usuario == null) {
            Toast.makeText(this, getString(R.string.erro_login_invalido), Toast.LENGTH_SHORT).show()
            return
        }

        Log.d(TAG, "Login efetuado com perfil: ${usuario.perfil}")

        val intent = if (usuario.perfil == DadosMemoria.PERFIL_CLINICA) {
            Intent(this, ClinicaHomeActivity::class.java)
        } else {
            Intent(this, TutorHomeActivity::class.java)
        }

        intent.putExtra("usuario", usuario)
        startActivity(intent)
        finish()
    }

    private fun mudarTema() {
        val isNight = AppCompatDelegate.getDefaultNightMode() ==
                AppCompatDelegate.MODE_NIGHT_YES

        val novoModo = if (isNight) {
            AppCompatDelegate.MODE_NIGHT_NO
        } else {
            AppCompatDelegate.MODE_NIGHT_YES
        }
        AppCompatDelegate.setDefaultNightMode(novoModo)
    }

    private fun alternarIdioma() {
        val idiomaAtual = AppCompatDelegate.getApplicationLocales()
        val usandoIngles = !idiomaAtual.isEmpty && idiomaAtual[0]?.language == "en"
        val proximoIdioma = if (usandoIngles) "pt" else "en"

        AppCompatDelegate.setApplicationLocales(
            LocaleListCompat.forLanguageTags(proximoIdioma)
        )
    }
}