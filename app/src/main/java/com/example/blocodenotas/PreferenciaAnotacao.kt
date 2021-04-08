package com.example.blocodenotas

import android.content.Context
import android.content.SharedPreferences

class PreferenciaAnotacao(private val context: Context) {

    //criamos a variavel "val context: Context pois precisamos recuperar essa classe na "MainActivity.kt"
    //para que podemos pegar todos os metodos e atributos dessa classe criada aqui

    //criando o Shared Preference para recuperar o conteudo quando o app inicializar
    private val preferences: SharedPreferences

    //editor para salvar nossas anotações
    private val editor: SharedPreferences.Editor

    //criando arquivo para usar no init no parametro ()
    private val ARQUIVO = "anotacao.preferencia"

    //criando Key para por no () editor.putString()
    private val CHAVE = "nome"

    fun SalvarAnotacao(anotacao: String?){
        //para que o botão salve a anotação
        editor.putString(CHAVE, anotacao)
        editor.commit()
    }

    fun RecuperarAnotacao(): String?{ //repare como não passamos a string como parametro e sim depois dele
        //quando o usuario fechar o app e abrir de novo a anotação vai estar la (esse metodo vai salvar na memoria do celular)
        return preferences.getString(CHAVE, "")
    }

    //inicializando as variaveis
    init {
        preferences = context.getSharedPreferences(ARQUIVO, 0) //nao precisa escrever mode: só coloque o 0 !!
        editor = preferences.edit()
    }

}