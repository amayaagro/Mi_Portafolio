package com.example.portafoliopro.ui.profile

import android.content.Intent
import android.net.Uri
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

class CustomProfileFragment : Fragment() {

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
            btnPhotos.setOnClickListener {
                findNavController().navigate(R.id.galleryFragment)
            }

            btnVideos.setOnClickListener {
                findNavController().navigate(R.id.videoFragment)
            }

            btnWeb.setOnClickListener {
                val websiteUrl = "https://ivanalaguna.dev"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(websiteUrl))
                startActivity(intent)
            }

            // Navegar a ejemplo de botones
            btnButtons.setOnClickListener {
                findNavController().navigate(R.id.buttonsFragment)
            }
        }
    }

    private fun setupUI() {
        binding.apply {
            // Información principal
            profileName.text = "Ivan Alaguna"
            profileTitle.text = "Ingeniero DevOps"
            profileDescription.text = "Especialista en automatización de infraestructuras y pipelines CI/CD"
            
            // Información personal
            personalInfo.text = """
                <b>Edad:</b> 27 años<br>
                <b>Ubicación:</b> Medellín, Colombia<br>
                <b>Idiomas:</b> Español (Nativo), Inglés (Intermedio)<br>
                <b>Intereses:</b> Kubernetes, Terraform, Docker
            """.trimIndent().fromHtml()
            
            // Formación académica
            educationInfo.text = """
                <b>Ingeniería de Sistemas</b><br>
                Universidad de Antioquia<br>
                2014 - 2018<br>
                • Promedio: 8.8<br>
                • Tesis: "Implementación de CI/CD en entornos Cloud"
            """.trimIndent().fromHtml()
            
            // Experiencia laboral
            experienceInfo.text = """
                <b>DevOps Engineer</b><br>
                CloudOps SA<br>
                2019 - Presente<br>
                • Diseño y mantenimiento de pipelines CI/CD<br>
                • Administración de clusters Kubernetes<br>
                • Automatización con Ansible y Terraform<br><br>

                <b>Ingeniero de Sistemas Jr.</b><br>
                TechDev Co<br>
                2018 - 2019<br>
                • Mantenimiento de servidores Linux<br>
                • Scripting en Bash y Python
            """.trimIndent().fromHtml()
            
            // Habilidades técnicas
            skillsInfo.text = """
                <b>Herramientas:</b><br>
                • Docker, Kubernetes<br>
                • Jenkins, GitLab CI<br>
                • Terraform, Ansible<br>
                • AWS, GCP
            """.trimIndent().fromHtml()
            
            // Información de contacto
            contactInfo.text = """
                <b>Email:</b> ivan.alaguna@example.com<br>
                <b>GitHub:</b> github.com/ivanalaguna<br>
                <b>LinkedIn:</b> linkedin.com/in/ivan-alaguna
            """.trimIndent().fromHtml()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 