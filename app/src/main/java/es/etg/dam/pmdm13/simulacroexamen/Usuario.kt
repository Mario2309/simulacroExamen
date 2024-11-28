package es.etg.dam.pmdm13.simulacroexamen

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Usuario(
    var nombre: String,
    var edad: Int
): Parcelable {

    override fun toString(): String {
        return "Soy $nombre y tengo $edad años."
    }

    fun incrementarEdad(){
        edad += 1
    }
}
