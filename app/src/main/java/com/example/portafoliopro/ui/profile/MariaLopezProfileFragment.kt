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

class MariaLopezProfileFragment : Fragment() {

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
            profileName.text = "Ilder Aguilar Martinez"
            profileTitle.text = "Arquitecto de Software"
            profileDescription.text = "Diseña y lidera arquitecturas escalables en la nube con enfoque en microservicios"

            personalInfo.text = """
                <b>Edad:</b> 33 años<br>
                <b>Ubicación:</b> Quito, Ecuador<br>
                <b>Idiomas:</b> Español (Nativo), Inglés (Avanzado)<br>
                <b>Intereses:</b> Microservicios, Cloud, DevOps
            """.trimIndent().fromHtml()

            educationInfo.text = """
                <b>Ingeniería de Software</b><br>
                Universidad Central del Ecuador<br>
                2007 - 2012<br>
                • Tesis: "Migración a microservicios de sistemas monolíticos"
            """.trimIndent().fromHtml()

            experienceInfo.text = """
                <b>Arquitecto de Software</b><br>
                Cloud Architects S.A.<br>
                2018 - Presente<br>
                • Definición de roadmap de arquitectura<br>
                • Coordinación de equipos DevOps<br>
                • Migración a Kubernetes y Docker Swarm<br><br>

                <b>Ingeniero de Desarrollo</b><br>
                Software Solutions ECU<br>
                2012 - 2018<br>
                • Desarrollo backend con Spring Boot y Node.js
            """.trimIndent().fromHtml()

            skillsInfo.text = """
                <b>Tecnologías:</b><br>
                • Kubernetes, Docker<br>
                • Spring Boot, Node.js<br>
                • Terraform, Ansible<br>
            """.trimIndent().fromHtml()

            contactInfo.text = """
                <b>Email:</b> ilder.martinez@example.com<br>
                <b>LinkedIn:</b> linkedin.com/in/ildermartinez<br>
                <b>GitHub:</b> github.com/ildermartinez
            """.trimIndent().fromHtml()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 
