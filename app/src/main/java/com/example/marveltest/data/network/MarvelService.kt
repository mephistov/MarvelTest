package com.example.marveltest.data.network

import android.util.Log
import com.example.marveltest.data.model.MarvelModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.math.BigInteger
import java.security.MessageDigest
import java.util.*
import javax.inject.Inject

class MarvelService @Inject constructor(private val api:MarvelApiClient) {

    companion object{
        val PUBLICKEY = "f9325e3ab3155b69455bed78011793c1"
        val PRIVATEKEY = "cc3e8cf8dd89ed371a915ba35c7119a432bc0d1b"
    }

    suspend fun getCharacters(): MarvelModel {
        return withContext(Dispatchers.IO) {
            val ts = "${Date().time}"
            val response = api.getMarvelCharacters(
                apikey = PUBLICKEY,
                ts = ts,
                hash = getHas(ts)
            )
            (response.body() ?: MarvelModel()) as MarvelModel
        }
    }

    suspend fun getCharacterById(id:Int): MarvelModel {
        return withContext(Dispatchers.IO) {
            val ts = "${Date().time}"
            val response = api.getMarvelCharacterById(
                characterId = id,
                apikey = PUBLICKEY,
                ts = ts,
                hash = getHas(ts)
            )
            (response.body() ?: MarvelModel()) as MarvelModel
        }
    }

    private fun getHas(ts:String):String{
        val input = "${ts}${PRIVATEKEY}${PUBLICKEY}"
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')

    }

}