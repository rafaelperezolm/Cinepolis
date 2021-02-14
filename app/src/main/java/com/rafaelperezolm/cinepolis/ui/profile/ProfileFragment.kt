package com.rafaelperezolm.cinepolis.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.rafaelperezolm.cinepolis.databinding.FragmentProfileBinding
import com.rafaelperezolm.cinepolis.utils.Resource
import com.rafaelperezolm.cinepolis.utils.SessionManager
import com.rafaelperezolm.cinepolis.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint

//Get and display user information.
@AndroidEntryPoint
class ProfileFragment : Fragment() {

    //View Binding to vinculate the xml components to the code
    private var binding: FragmentProfileBinding by autoCleared()
    //Store and manage UI-related data
    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sessionManager = SessionManager(requireContext())
        //Initiates the view model
        viewModel.start("${sessionManager.sharedPreferences.getString("token", "")}")
        //Starts the observer
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.profile.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    if (!it.data.isNullOrEmpty()) {
                        binding.profilePgbLoading.visibility = View.INVISIBLE
                        val name: String = "${it.data[0].firstName} ${it.data[0].lastName}"
                        binding.profileTxvName.text = name
                        val email: String = "${it.data[0].email}"
                        binding.profileTxvEmail.text = email
                        val card: String = "${it.data[0].cardNumber}"
                        binding.profileTxvCard.text = card
                    }
                }

                Resource.Status.ERROR -> {
                    binding.profilePgbLoading.visibility = View.INVISIBLE
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }

                Resource.Status.LOADING -> {
                    binding.profilePgbLoading.visibility = View.VISIBLE
                }
            }
        })
    }

}