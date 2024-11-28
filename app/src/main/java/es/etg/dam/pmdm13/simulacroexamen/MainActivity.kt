package es.etg.dam.pmdm13.simulacroexamen

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import es.etg.dam.pmdm13.simulacroexamen.preferencias.AccionPreferencias

class MainActivity : AppCompatActivity() {
    
    companion object{
        const val EXTRA_SECOND = "SecondActibity:Usuario"
    }
    
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val accionPreferencias = AccionPreferencias(this)

        recuperarPreferencias(accionPreferencias)


        val btnIniciarSesion = findViewById<Button>(R.id.btnIniciarSesion)
        btnIniciarSesion.setOnClickListener {

            avanzarSegundaActividad()

            val user = nuevoUsuario()
            enviarInformacion(user)

            guardarPreferencia(accionPreferencias)
        }
    }

    private fun recuperarPreferencias(accionPreferencias: AccionPreferencias) {
        val nombre = accionPreferencias.recuperarPreferencia()

        if (nombre != null) {
            val nombreUser = findViewById<EditText>(R.id.editTextNombre).setText(nombre)
        }
    }

    private fun guardarPreferencia(accionPreferencias: AccionPreferencias) {
        val contenidoNombre = findViewById<EditText>(R.id.editTextNombre).text
        accionPreferencias.guardarPreferencia(contenidoNombre.toString())
    }

    private fun avanzarSegundaActividad() {
        val intent = Intent(this, SecondActivity::class.java)
        startActivity(intent)
    }

    private fun enviarInformacion(user: Usuario) {
        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtra(EXTRA_SECOND, user)
        startActivity(intent)
    }

    private fun nuevoUsuario(): Usuario {
        val nombre = findViewById<EditText>(R.id.editTextNombre).text
        val edad = findViewById<EditText>(R.id.editTextEdad).text
        val user = Usuario(nombre.toString(), edad.toString().toInt())
        return user
    }




}