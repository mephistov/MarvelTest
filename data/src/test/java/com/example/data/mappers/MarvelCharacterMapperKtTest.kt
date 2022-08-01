package com.example.data.mappers

import com.example.data.model.*
import com.example.domain.entities.MarvelCharacter
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsInstanceOf.instanceOf
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test


class MarvelCharacterMapperKtTest{

    lateinit var resultCharTest: ResultCharacters
    private val FAKE_USER_ID = 1234

    @Before
    fun onBefore(){
        resultCharTest = ResultCharacters(
            description = "some description",
            id = FAKE_USER_ID,
            modified = "n/a",
            name = "name marvel",
            resourceURI = "some info",
            thumbnail = Thumbnail("jpg","someImage")
        )
    }

    @Test
    fun `test tranform result entity to marvel character`(){
        val marvelCharacter = resultCharTest.toMarvelCharacter()

        assertThat(marvelCharacter, instanceOf(MarvelCharacter::class.java))
        assert(marvelCharacter.id == FAKE_USER_ID)

    }



}