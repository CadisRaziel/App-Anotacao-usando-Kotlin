package com.example.blocodenotas

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.blocodenotas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var Binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(Binding.root)


        //toda vez que o usuario fechar e abrir o app, as anotações deles vao ficar salva sem um banco de dados
        //para isso vamos utilizar o Shared Preferences que é igual o AscynStorage do react native

        //criando o PreferenciaAnotacao.kt
        //para criar uma kotlin class
        //acesse a pasta "java" > com o botão direito clique sobre "com.example.blocodenotas"
        //em seguida vá no "new" > "Kotlin class" e pronto é só digitar o nome


        //criamos a variavel que vai receber nossa classe PreferenciaAnotacao e usamos o "applicationContext" para recuperar os metodos e atributos criados dentro dela (PreferenciaAnotacao)
        val preferencia = PreferenciaAnotacao(applicationContext)


        //deixando o botao salvar clicavel (com verificação se o usuario digitou algo)
        val botaoSalvar = Binding.fab

        //observação o editContainer esta no layout "activity_main.xml" e o editAnotacao esta na "content_main.xml"
        botaoSalvar.setOnClickListener {
            val anotacaoRecuperado = Binding.editContainer.editAnotacao.text.toString()

            if(anotacaoRecuperado == ""){
                //caso o usuario nao digite nada e clique para salvar
                Toast.makeText(this, "Digite alguma coisa", Toast.LENGTH_SHORT).show()
            } else {
                //salvando a anotação
                preferencia.SalvarAnotacao(anotacaoRecuperado) //anotacaoRecuperado pois eu vou pegar o que o usuario digitou pelo "text" convertido em string .toString()
                Toast.makeText(this, "Anotação salva com sucesso!", Toast.LENGTH_SHORT).show()
            }
        }

        //recuperando as anotações ao fechar e abrir o app
        val anotacao = preferencia.RecuperarAnotacao()
        if(anotacao != ""){ //se a anotacao for diferente de vazio, quer dizer que eu quero recuperar ela
            Binding.editContainer.editAnotacao.setText(anotacao)
        }

    }

}