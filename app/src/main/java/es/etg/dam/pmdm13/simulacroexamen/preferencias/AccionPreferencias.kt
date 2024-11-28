package es.etg.dam.pmdm13.simulacroexamen.preferencias

import android.content.Context

private const val PREFERENCIA_USUARIO = "PreferenciaUsuario"

private const val NOMBRE = "nombre"

private const val VACIO = ""

class AccionPreferencias(private val context: Context): Preferencias {

    override fun guardarPreferencia(nombre: String) {
        val sharePref = context.getSharedPreferences(PREFERENCIA_USUARIO, Context.MODE_PRIVATE)
        val editor = sharePref.edit()
        editor.putString(NOMBRE, nombre)
        editor.apply()
    }

    override fun recuperarPreferencia(): String? {
        val sharePref = context.getSharedPreferences(PREFERENCIA_USUARIO ,Context.MODE_PRIVATE)
        return sharePref.getString(NOMBRE, VACIO)
    }
}