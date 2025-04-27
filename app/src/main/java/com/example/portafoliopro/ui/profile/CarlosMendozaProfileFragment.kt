package com.example.portafoliopro.ui.profile

import android.os.Bundle
import android.text.Html
import android.text.Spanned
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.portafoliopro.R
import com.example.portafoliopro.databinding.FragmentCustomProfileBinding

class CarlosMendozaProfileFragment : Fragment() {

    private var _binding: FragmentCustomProfileBinding? = null
    private val binding get() = _binding!!

    @Suppress("DEPRECATION")
    private fun String.fromHtml(): Spanned {
        return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            Html.fromHtml(this, Html.FROM_HTML_MODE_COMPACT)
        } else {
            Html.fromHtml(this)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCustomProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupButtons()
    }

    private fun setupButtons() {
        binding.apply {
            btnPhotos.setOnClickListener { findNavController().navigate(R.id.galleryFragment) }
            btnVideos.setOnClickListener { findNavController().navigate(R.id.videoFragment) }
            btnWeb.setOnClickListener { findNavController().navigate(R.id.webFragment) }
            btnButtons.setOnClickListener { findNavController().navigate(R.id.buttonsFragment) }
        }
    }

    private fun setupUI() {
        binding.apply {
            profileName.text = "Brayan Alvarez Mahecha"
            profileTitle.text = "Ingeniero de Software Full Stack"
            profileDescription.text = "Especializado en desarrollo de aplicaciones web y servicios backend escalables."

            personalInfo.text = """
                <b>Edad:</b> 30 años<br>
                <b>Ubicación:</b> Bogotá, Colombia<br>
                <b>Idiomas:</b> Español (Nativo), Inglés (Avanzado)<br>
                <b>Intereses:</b> Node.js, React, Docker, Kubernetes
            """.trimIndent().fromHtml()

            educationInfo.text = """
                <b>Ingeniería de Sistemas</b><br>
                Universidad Nacional de Colombia<br>
                2008 - 2013<br>
                • Promedio: 9.1<br>
                • Tesis: "Autenticación en APIs REST"
            """.trimIndent().fromHtml()

            experienceInfo.text = """
                <b>Full Stack Developer Senior</b><br>
                WebSolutions Ltd.<br>
                2018 - Presente<br>
                • Desarrollo frontend con React.js<br>
                • Servicios backend con Node.js y Express<br>
                • Integración con bases de datos PostgreSQL y MongoDB<br><br>

                <b>Backend Developer</b><br>
                TechCorp S.A.<br>
                2013 - 2018<br>
                • Implementación de API RESTful<br>
                • Mantenimiento y pruebas unitarias
            """.trimIndent().fromHtml()

            skillsInfo.text = """
                <b>Lenguajes:</b><br>
                • JavaScript, Python, Java<br><br>

                <b>Frameworks y Herramientas:</b><br>
                • React.js, Node.js<br>
                • Docker, Kubernetes<br>
                • PostgreSQL, MongoDB
            """.trimIndent().fromHtml()

            contactInfo.text = """
                <b>Email:</b> brayan.alvarez@example.com<br>
                <b>LinkedIn:</b> linkedin.com/in/brayanalvarezm<br>
                <b>GitHub:</b> github.com/brayanalvarezm
            """.trimIndent().fromHtml()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 