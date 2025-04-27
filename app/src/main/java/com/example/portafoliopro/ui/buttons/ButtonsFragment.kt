package com.example.portafoliopro.ui.buttons

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.portafoliopro.databinding.FragmentButtonBinding
import com.example.portafoliopro.ui.main.TwoPaneFragment
import com.example.portafoliopro.R

class ButtonsFragment : Fragment() {

    private var _binding: FragmentButtonBinding? = null
    private val binding get() = _binding!!

    private var profileId: Int = 0

    companion object {
        /** Crea un fragmento de botones para un perfil específico */
        fun newInstance(profileId: Int): ButtonsFragment {
            return ButtonsFragment().apply {
                arguments = Bundle().apply { putInt("profileId", profileId) }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Recuperar profileId de argumentos
        profileId = arguments?.getInt("profileId") ?: 0
        _binding = FragmentButtonBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Cargar URLs de ejemplo para CVs
        val pdfUrls = resources.getStringArray(R.array.cv_pdf_urls)
        val docUrls = resources.getStringArray(R.array.cv_doc_urls)
        binding.apply {
            // Abrir PDF en visor externo de Google Docs
            button1.setOnClickListener {
                val url = pdfUrls.getOrNull(profileId) ?: pdfUrls.first()
                val previewUrl = "https://docs.google.com/gview?embedded=true&url=$url"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(previewUrl))
                try {
                    startActivity(intent)
                } catch (e: ActivityNotFoundException) {
                    Toast.makeText(requireContext(), "No hay aplicación para abrir PDF", Toast.LENGTH_SHORT).show()
                }
            }
            // Abrir Word en visor externo de Office Online
            button2.setOnClickListener {
                val url = docUrls.getOrNull(profileId) ?: docUrls.first()
                val encoded = java.net.URLEncoder.encode(url, "UTF-8")
                val previewUrl = "https://view.officeapps.live.com/op/view.aspx?src=$encoded"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(previewUrl))
                try {
                    startActivity(intent)
                } catch (e: ActivityNotFoundException) {
                    Toast.makeText(requireContext(), "No hay aplicación para abrir Word", Toast.LENGTH_SHORT).show()
                }
            }
            // Regresar al perfil inicial usando TwoPaneFragment
            button3.setOnClickListener {
                (parentFragment as? TwoPaneFragment)?.showProfile(profileId)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 