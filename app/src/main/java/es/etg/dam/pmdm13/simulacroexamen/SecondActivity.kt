package es.etg.dam.pmdm13.simulacroexamen

import android.content.Intent
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import es.etg.dam.pmdm13.simulacroexamen.recyclerView.DataAdapter
import es.etg.dam.pmdm13.simulacroexamen.recyclerView.ItemViewModel

private const val max = 10

private const val min = 1

private const val ESTAS_PINCHANDO_EN_UNA_OPCION = "Estas pinchando en una opción"

private const val DESCRIPCION = "Descripción "

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val data = dataSet()

        mostrarRecyclerView(data)


        usuario()

        val btnVolver = findViewById<Button>(R.id.btnVolver)
        btnVolver.setOnClickListener {

            avanzarActividadMain()

        }

    }

    private fun avanzarActividadMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun usuario() {
        val usuario = getUsuario()
        val msg = usuario.toString()

        imprimirPopUp(msg)
    }

    private fun mostrarRecyclerView(data: ArrayList<ItemViewModel>) {
        val adapter = DataAdapter(data)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.adapter = adapter

        adapter.setOnClickListener(object : DataAdapter.OnClickListener{
            override fun onClick(position: Int, model: ItemViewModel) {
                val msg = ESTAS_PINCHANDO_EN_UNA_OPCION
                imprimirPopUp(msg)
            }
        })
    }

    private fun dataSet(): ArrayList<ItemViewModel> {
        val data = ArrayList<ItemViewModel>()
        for (i in min..max) {
            val image = android.R.drawable.btn_star
            val descp = DESCRIPCION + i
            data.add(ItemViewModel(image, descp))
        }
        return data
    }

    private fun imprimirPopUp(msg: String?) {

        val toast = Toast.makeText(this, msg, Toast.LENGTH_LONG)

        toast.show()
    }

    private fun getUsuario() = if (VERSION.SDK_INT >= VERSION_CODES.TIRAMISU) {
        intent.getParcelableExtra(MainActivity.EXTRA_SECOND, Usuario::class.java)
    } else {
        @Suppress("DEPRECATION")
        intent.getParcelableExtra<Usuario>(MainActivity.EXTRA_SECOND)
    }
}