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

class JuanPerezProfileFragment : Fragment() {

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
            profileName.text = "Gustavo Mahecha Campos"
            profileTitle.text = "Ingeniero de Datos"
            profileDescription.text = "Especialista en sistemas de Big Data y procesamiento en tiempo real."

            personalInfo.text = """
                <b>Edad:</b> 29 años<br>
                <b>Ubicación:</b> Cali, Colombia<br>
                <b>Idiomas:</b> Español (Nativo), Inglés (Intermedio)<br>
                <b>Intereses:</b> Machine Learning, Data Engineering, Apache Spark
            """.trimIndent().fromHtml()

            educationInfo.text = """
                <b>Ingeniería de Sistemas</b><br>
                Universidad del Valle<br>
                2011 - 2016<br>
                • Proyecto de grado: "Optimización de pipelines de ETL"
            """.trimIndent().fromHtml()

            experienceInfo.text = """
                <b>Data Engineer Senior</b><br>
                BigData Solutions<br>
                2019 - Presente<br>
                • Diseño de pipelines de datos con Apache Spark y Kafka<br>
                • Implementación de arquitecturas Lambda y Kappa<br><br>

                <b>Data Engineer</b><br>
                Analytics Corp.<br>
                2016 - 2019<br>
                • Desarrollo de ETL en Python y Airflow
            """.trimIndent().fromHtml()

            skillsInfo.text = """
                <b>Lenguajes:</b><br>
                • Python, Scala<br>
                • SQL, Java<br><br>

                <b>Herramientas:</b><br>
                • Apache Spark, Kafka<br>
                • Airflow, Hadoop<br>
                • PostgreSQL, Cassandra
            """.trimIndent().fromHtml()

            contactInfo.text = """
                <b>Email:</b> gusravi.mahecha@example.com<br>
                <b>LinkedIn:</b> linkedin.com/in/gusravi-mahecha<br>
                <b>GitHub:</b> github.com/gusravimahecha
            """.trimIndent().fromHtml()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 
