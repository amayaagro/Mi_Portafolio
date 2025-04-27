package com.example.portafoliopro.ui.button

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.portafoliopro.databinding.FragmentButtonBinding

class ButtonFragment : Fragment() {

    private var _binding: FragmentButtonBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentButtonBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        binding.apply {
            button1.setOnClickListener {
                Toast.makeText(context, "Botón 1 presionado", Toast.LENGTH_SHORT).show()
            }
            button2.setOnClickListener {
                Toast.makeText(context, "Botón 2 presionado", Toast.LENGTH_SHORT).show()
            }
            button3.setOnClickListener {
                Toast.makeText(context, "Botón 3 presionado", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}