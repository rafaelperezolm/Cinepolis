package com.rafaelperezolm.cinepolis.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.rafaelperezolm.cinepolis.R
import com.rafaelperezolm.cinepolis.databinding.FragmentLoginEmailBinding
import com.rafaelperezolm.cinepolis.ui.main.MainActivity
import com.rafaelperezolm.cinepolis.utils.Resource
import com.rafaelperezolm.cinepolis.utils.SessionManager
import com.rafaelperezolm.cinepolis.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint

//This class provides a form to enter a username and password.
//Contains a button that starts user validation when pressed.
@AndroidEntryPoint
class LoginEmailFragment : Fragment() {

    //View Binding to vinculate the xml components to the code
    private var binding: FragmentLoginEmailBinding by autoCleared()
    //Store and manage UI-related data
    private val viewModel: LoginEmailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginEmailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Username text listener to clear the error state
        binding.loginTietUsername.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                binding.loginTilUsername.error = null
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun afterTextChanged(s: Editable) {}
        })

        //Password text listener to clear the error state
        binding.loginTietPassword.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                binding.loginTilUsername.error = null
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun afterTextChanged(s: Editable) {}
        })

        //Assign password done action keyboard button
        binding.loginTietPassword.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                attemptLogin()
            }
            false
        }

        //Loggin button action
        binding.loginBtnSign.setOnClickListener {
            attemptLogin()
        }
    }

    private fun showLoading() {
        binding.loginPgbLoading.visibility = View.VISIBLE
        binding.loginTilUsername.isEnabled = false
        binding.loginTilPassword.isEnabled = false
        binding.loginBtnSign.isEnabled = false
    }

    private fun hideLoading() {
        binding.loginPgbLoading.visibility = View.INVISIBLE
        binding.loginTilUsername.isEnabled = true
        binding.loginTilPassword.isEnabled = true
        binding.loginBtnSign.isEnabled = true
    }

    //Check the username and password before making the api call
    private fun attemptLogin() {
        val username = binding.loginTietUsername.text.toString()
        val password = binding.loginTietPassword.text.toString()

        binding.loginTietUsername.error = null
        binding.loginTietPassword.error = null

        var cancel = false

        if (TextUtils.isEmpty(username)) {
            binding.loginTilUsername.error = this.resources.getString(R.string.login_username_error)
            cancel = true
        }
        if (TextUtils.isEmpty(password)) {
            binding.loginTilPassword.error = this.resources.getString(R.string.login_password_error)
            cancel = true
        }
        if (!cancel) {
            //Initiate the viewmodel
            viewModel.start("$username|$password")
            //Starting the observer
            setupObservers()
        }
    }

    private fun setupObservers() {
        viewModel.loginfo.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    if (!it.data.isNullOrEmpty()) {
                        createSession(it.data[0].username, it.data[0].accessToken)
                    }
                }

                Resource.Status.ERROR -> {
                    hideLoading()
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }

                Resource.Status.LOADING -> {
                    showLoading()
                }
            }
        })
    }

    //If the call is "SUCCESS" saves the login state and access the app content
    private fun createSession(username: String, password: String) {
        val sessionManager = SessionManager(requireContext())
        sessionManager.createLoginSession(username, password)
        hideLoading()
        val intentActivityChange = Intent(context, MainActivity::class.java)
        startActivity(intentActivityChange)
        activity?.finish()
    }

}