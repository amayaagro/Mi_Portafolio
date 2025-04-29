package com.example.portafoliopro.ui.profile

import android.os.Bundle
import android.text.Html
import android.text.Spanned
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.portafoliopro.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
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
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        binding.apply {
            // Información principal
            profileName.text = "Ivan Alaguna Ramirez"
            profileTitle.text = "Desarrollador Android"
            profileDescription.text = "Estudiante de Ingeniería en Sistemas\nApasionado por el desarrollo móvil y las nuevas tecnologías"
            
            // Información personal
            personalInfo.text = """
                <b>Edad:</b> 22 años<br>
                <b>Ubicación:</b> Ciudad de México, México<br>
                <b>Idiomas:</b> Español (Nativo), Inglés (Avanzado)<br>
                <b>Intereses:</b> Desarrollo móvil, Inteligencia Artificial, IoT
            """.trimIndent().fromHtml()
            
            // Formación académica
            educationInfo.text = """
                <b>Ingeniería en Sistemas Computacionales</b><br>
                Universidad Nacional Autónoma de México (UNAM)<br>
                2019 - Presente<br>
                Promedio general: 8.5<br>
                Especialización en desarrollo móvil<br>
                Participación en proyectos de investigación<br><br>

                <b>Técnico en Programación</b><br>
                Centro de Estudios Científicos y Tecnológicos (CECyT)<br>
                2016 - 2019<br>
                Promedio general: 9.0<br>
                Especialización en desarrollo de software
            """.trimIndent().fromHtml()
            
            // Experiencia laboral
            experienceInfo.text = """
                <b>Desarrollador Android Junior</b><br>
                Tech Solutions S.A. de C.V.<br>
                Junio 2023 - Presente<br>
                Desarrollo de aplicaciones Android nativas en Kotlin<br>
                Implementación de arquitectura MVVM<br>
                Integración con APIs REST<br>
                Trabajo en equipo usando metodologías ágiles<br><br>

                <b>Practicante de Desarrollo Móvil</b><br>
                Mobile Apps Inc.<br>
                Enero 2023 - Mayo 2023<br>
                Desarrollo de aplicaciones híbridas con React Native<br>
                Mantenimiento de aplicaciones existentes<br>
                Pruebas de calidad y debugging
            """.trimIndent().fromHtml()
            
            // Habilidades técnicas
            skillsInfo.text = """
                <b>Lenguajes de Programación:</b><br>
                Kotlin (Avanzado)<br>
                Java (Intermedio)<br>
                Python (Intermedio)<br>
                JavaScript (Intermedio)<br><br>
                
                <b>Frameworks y Herramientas:</b><br>
                Android Studio<br>
                Git y GitHub<br>
                Firebase<br>
                SQLite<br>
                REST APIs<br>
                Material Design<br><br>

                <b>Metodologías:</b><br>
                Desarrollo Ágil<br>
                MVVM Architecture<br>
                Clean Architecture<br>
                TDD (Test Driven Development)
            """.trimIndent().fromHtml()
            
            // Información de contacto
            contactInfo.text = """
                <b>Email:</b> ivan.laguna@example.com<br>
                <b>Teléfono:</b> +52 (55) 1234-5678<br>
                <b>GitHub:</b> github.com/ivanlaguna<br>
                <b>LinkedIn:</b> linkedin.com/in/ivanlaguna<br>
                <b>Portfolio:</b> ivanlaguna.dev
            """.trimIndent().fromHtml()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
