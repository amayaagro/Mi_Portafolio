package com.example.portafoliopro.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.portafoliopro.R
import com.example.portafoliopro.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private lateinit var profiles: Array<String>
    private lateinit var passwords: Array<String>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        profiles = resources.getStringArray(R.array.login_profiles)
        passwords = resources.getStringArray(R.array.login_passwords)

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, profiles)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerProfiles.adapter = adapter

        binding.btnLogin.setOnClickListener {
            val pos = binding.spinnerProfiles.selectedItemPosition
            val input = binding.inputPassword.text.toString().trim()
            if (input == passwords.getOrNull(pos)) {
                val action = LoginFragmentDirections.actionLoginToTwoPane(profileId = pos)
                findNavController().navigate(action)
            } else {
                Toast.makeText(requireContext(), "Contraseña incorrecta", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 