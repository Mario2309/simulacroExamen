package es.etg.dam.pmdm13.simulacroexamen.preferencias

interface Preferencias {

    fun guardarPreferencia(nombre: String)

    fun recuperarPreferencia(): String?
}