package com.rafaelperezolm.cinepolis.ui.login

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withHint
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.rafaelperezolm.cinepolis.R
import org.hamcrest.CoreMatchers
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginEmailFragmentTest {

    @Test
    fun hintIsDisplayedInEditTextUserNameTest() {
        Espresso.onView(withId(R.id.login_tiet_username))
            .check(ViewAssertions.matches(withHint(R.string.login_user_text)))
    }

    @Test
    fun hintIsDisplayedInEditTextPasswordTest() {
        Espresso.onView(withId(R.id.login_tiet_password))
            .check(ViewAssertions.matches(withHint(R.string.login_password_text)))
    }

    @Test
    fun validUserNameAndPasswordTest() {
        Espresso.onView(withId(R.id.login_tiet_username))
            .perform(ViewActions.typeText("pruebas_beto_ia@yahoo.com"))
        Espresso.onView(withId(R.id.login_tiet_password))
            .perform(ViewActions.typeText("Pruebas01"))
        Espresso.onView(withId(R.id.login_btn_sign))
            .perform(ViewActions.click())
    }

}