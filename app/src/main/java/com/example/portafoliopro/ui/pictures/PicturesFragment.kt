package com.example.portafoliopro.ui.pictures

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.portafoliopro.R
import com.example.portafoliopro.databinding.FragmentPicturesBinding

class PicturesFragment : Fragment() {

    private var _binding: FragmentPicturesBinding? = null
    private val binding get() = _binding!!
    private lateinit var imageAdapter: ImageAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPicturesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        imageAdapter = ImageAdapter { projectImage ->
            // Actualizar la descripción cuando se selecciona una imagen
            binding.apply {
                selectedImageTitle.text = projectImage.title
                selectedImageDescription.text = projectImage.description
                descriptionCard.visibility = View.VISIBLE
            }
        }

        binding.picturesRecyclerView.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = imageAdapter
        }

        // Lista de proyectos con sus imágenes
        val projects = listOf(
            ProjectImage(
                id = 1,
                imageResId = R.drawable.project_todo,
                title = "App de Gestión de Tareas",
                description = "Aplicación Android para organizar y gestionar tareas diarias. Incluye características como:\n" +
                        "• Creación y edición de tareas\n" +
                        "• Categorización por prioridad\n" +
                        "• Recordatorios y notificaciones\n" +
                        "• Sincronización en la nube"
            ),
            ProjectImage(
                id = 2,
                imageResId = R.drawable.project_restaurant,
                title = "Sistema de Reservas",
                description = "Sistema de reservas para restaurantes con interfaz moderna. Características:\n" +
                        "• Reservas en tiempo real\n" +
                        "• Gestión de mesas\n" +
                        "• Sistema de puntos de fidelidad\n" +
                        "• Historial de reservas"
            ),
            ProjectImage(
                id = 3,
                imageResId = R.drawable.project_weather,
                title = "App del Clima",
                description = "Aplicación para mostrar el clima en tiempo real. Incluye:\n" +
                        "• Pronóstico por hora\n" +
                        "• Múltiples ubicaciones\n" +
                        "• Widgets personalizables\n" +
                        "• Alertas meteorológicas"
            ),
            ProjectImage(
                id = 4,
                imageResId = R.drawable.project_calculator,
                title = "Calculadora Científica",
                description = "Calculadora con funciones científicas avanzadas. Características:\n" +
                        "• Operaciones matemáticas complejas\n" +
                        "• Conversión de unidades\n" +
                        "• Historial de cálculos\n" +
                        "• Modo programador"
            )
        )

        imageAdapter.submitList(projects)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 