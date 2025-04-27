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

class PaulaGarciaProfileFragment : Fragment() {

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
            profileName.text = "Alexander Amaya Arango"
            profileTitle.text = "Full Stack Developer"
            profileDescription.text = "Desarrollador full-stack con experiencia en React.js y Node.js"

            personalInfo.text = """
                <b>Edad:</b> 29 años<br>
                <b>Ubicación:</b> Medellín, Colombia<br>
                <b>Idiomas:</b> Español (Nativo), Inglés (Avanzado)<br>
                <b>Intereses:</b> React.js, Node.js, DevOps
            """.trimIndent().fromHtml()

            educationInfo.text = """
                <b>Ingeniería de Sistemas</b><br>
                Universidad de Antioquia<br>
                2010 - 2014<br>
                • Promedio: 9.0<br>
                • Tesis: "Aplicaciones Escalables en la Nube"
            """.trimIndent().fromHtml()

            experienceInfo.text = """
                <b>Full Stack Developer Senior</b><br>
                TechSoft Ltd.<br>
                2016 - Presente<br>
                • Desarrollo de aplicaciones web con React.js y Node.js<br>
                • Diseño de APIs REST con Express.js<br><br>

                <b>Full Stack Developer Jr.</b><br>
                WebStart Inc.<br>
                2014 - 2016<br>
                • Desarrollo frontend con Angular<br>
                • Mantenimiento y pruebas de aplicaciones existentes
            """.trimIndent().fromHtml()

            skillsInfo.text = """
                <b>Lenguajes:</b><br>
                • JavaScript, TypeScript<br>
                • Java, Python<br><br>

                <b>Frameworks y Herramientas:</b><br>
                • React.js, Node.js, Angular<br>
                • Docker, Kubernetes<br>
                • Git, GitHub
            """.trimIndent().fromHtml()

            contactInfo.text = """
                <b>Email:</b> alexander.amaya@example.com<br>
                <b>Teléfono:</b> +57 300 1234567<br>
                <b>LinkedIn:</b> linkedin.com/in/alexander-amaya<br>
                <b>Portfolio:</b> alexanderamaya.dev
            """.trimIndent().fromHtml()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 