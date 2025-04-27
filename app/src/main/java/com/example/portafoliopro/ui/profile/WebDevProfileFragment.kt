package com.example.portafoliopro.ui.profile

import android.os.Bundle
import android.text.Html
import android.text.Spanned
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.portafoliopro.databinding.FragmentProfileBinding

class WebDevProfileFragment : Fragment() {

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
            profileName.text = "Ana Martínez"
            profileTitle.text = "Desarrolladora Web Full-Stack"
            profileDescription.text = "Desarrolladora web apasionada por crear experiencias digitales innovadoras\nEspecialista en tecnologías front-end y back-end"
            
            // Información personal
            personalInfo.text = """
                <b>Edad:</b> 25 años<br>
                <b>Ubicación:</b> Guadalajara, México<br>
                <b>Idiomas:</b> Español (Nativo), Inglés (Avanzado), Francés (Básico)<br>
                <b>Intereses:</b> UX/UI, Cloud Computing, DevOps, Arquitectura de Software
            """.trimIndent().fromHtml()
            
            // Formación académica
            educationInfo.text = """
                <b>Maestría en Ingeniería de Software</b><br>
                Instituto Tecnológico y de Estudios Superiores de Monterrey<br>
                2021 - 2023<br>
                • Tesis: "Arquitecturas Serverless para Aplicaciones Web Escalables"<br>
                • Promedio: 95/100<br><br>

                <b>Ingeniería en Tecnologías de la Información</b><br>
                Universidad de Guadalajara<br>
                2016 - 2020<br>
                • Premio a la Excelencia Académica<br>
                • Líder del Club de Programación
            """.trimIndent().fromHtml()
            
            // Experiencia laboral
            experienceInfo.text = """
                <b>Desarrolladora Full-Stack Senior</b><br>
                Digital Solutions México<br>
                2021 - Presente<br>
                • Desarrollo de aplicaciones web con React y Node.js<br>
                • Implementación de arquitecturas serverless en AWS<br>
                • Liderazgo de equipo de desarrollo (6 personas)<br>
                • Optimización de rendimiento y SEO<br><br>

                <b>Desarrolladora Front-end</b><br>
                Tech Innovators S.A.<br>
                2019 - 2021<br>
                • Desarrollo de interfaces con React y TypeScript<br>
                • Implementación de diseños responsivos<br>
                • Integración con APIs RESTful<br>
                • Testing con Jest y React Testing Library
            """.trimIndent().fromHtml()
            
            // Habilidades técnicas
            skillsInfo.text = """
                <b>Front-end:</b><br>
                • React.js / Next.js<br>
                • TypeScript / JavaScript<br>
                • HTML5 / CSS3 / Sass<br>
                • Redux / Context API<br><br>
                
                <b>Back-end:</b><br>
                • Node.js / Express<br>
                • Python / Django<br>
                • GraphQL / REST APIs<br>
                • MongoDB / PostgreSQL<br><br>

                <b>DevOps & Cloud:</b><br>
                • AWS (Lambda, S3, EC2)<br>
                • Docker / Kubernetes<br>
                • CI/CD (GitHub Actions)<br>
                • Terraform / CloudFormation
            """.trimIndent().fromHtml()
            
            // Información de contacto
            contactInfo.text = """
                <b>Email:</b> ana.martinez@example.com<br>
                <b>Teléfono:</b> +52 (33) 9876-5432<br>
                <b>GitHub:</b> github.com/anamartinez-dev<br>
                <b>LinkedIn:</b> linkedin.com/in/anamartinez<br>
                <b>Portfolio:</b> anamartinez.dev
            """.trimIndent().fromHtml()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 