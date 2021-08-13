package com.example.projetovicintegrador

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.projetovicintegrador.ui.main.MainActivity
import org.hamcrest.CoreMatchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTeste {

    @get:Rule
    val rule = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Test
    fun nonExistentMovieSearch() {
        onView(withId(R.id.campo_pesquisa)).perform(typeText("filme inexistente"))
        onView(withId(R.id.btn_pesquisa)).perform(click())
        onView(withId(R.id.textView3))
            .check(matches(isDisplayed()))
    }

    @Test
    fun systemFailed() {
        onView(withId(R.id.btn_pesquisa)).perform(click())
        onView(withId(R.id.textView6))
            .check(matches(isDisplayed()))
    }
}