package com.example.boletimonline

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.boletimonline.Notas
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buscaDados()
    }

    private fun buscaDados() {
        val serviceRetrofit = RetrofitService()
        serviceRetrofit.api?.obterNotas()?.enqueue(object : Callback<List<Notas>> {
            override fun onResponse(call: Call<List<Notas>?>?, response: Response<List<Notas>?>?) {
                val lista = response?.body();
                if (lista != null) {
                    for (notas in lista) {
                        Log.d("Resposta", "\nNome: "+notas.nome.toString()+" -- Nota: "+notas.nota?.toInt())
                    }

                    nomeAluno1.text = lista?.get(0)?.nome
                    nomeAluno2.text = lista?.get(1)?.nome
                    nomeAluno3.text = lista?.get(2)?.nome

                    notaAluno1.text = lista?.get(0)?.nota.toString()
                    notaAluno2.text = lista?.get(1)?.nota.toString()
                    notaAluno3.text = lista?.get(2)?.nota.toString()

                    image1.setImageResource(if(lista?.get(0)?.nota!! <6)R.drawable.vermelho else R.drawable.azul)
                    image2.setImageResource(if(lista?.get(1)?.nota!! <6)R.drawable.vermelho else R.drawable.azul)
                    image3.setImageResource(if(lista?.get(2)?.nota!! <6)R.drawable.vermelho else R.drawable.azul)

                    notaAluno1.setTextColor(if(lista?.get(0)?.nota!! <6)Color.RED else Color.BLUE)
                    notaAluno2.setTextColor(if(lista?.get(1)?.nota!! <6)Color.RED else Color.BLUE)
                    notaAluno3.setTextColor(if(lista?.get(2)?.nota!! <6)Color.RED else Color.BLUE)
                }
            }

            override fun onFailure(call: Call<List<Notas>?>?, t: Throwable?) {
                Log.e("Erro", "************** erro **********\n" + t?.message.toString())
            }
        })
    }
}