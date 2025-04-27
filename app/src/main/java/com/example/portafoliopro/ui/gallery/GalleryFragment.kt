package com.example.portafoliopro.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.portafoliopro.R
import com.example.portafoliopro.databinding.FragmentGalleryBinding
import com.example.portafoliopro.ui.gallery.ProjectAdapter
import com.example.portafoliopro.ui.gallery.Project

class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = _binding!!
    private var profileId: Int = 0

    companion object {
        fun newInstance(profileId: Int): GalleryFragment {
            return GalleryFragment().apply {
                arguments = Bundle().apply { putInt("profileId", profileId) }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        profileId = arguments?.getInt("profileId") ?: 0
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Cargar datos de proyectos según perfil (usando URLs)
        val pkg = requireContext().packageName
        val urlsResId = resources.getIdentifier("gallery_image_urls_$profileId", "array", pkg).takeIf { it != 0 } ?: R.array.gallery_image_urls
        val titlesResId = resources.getIdentifier("gallery_titles_$profileId", "array", pkg).takeIf { it != 0 } ?: R.array.gallery_titles
        val descResId = resources.getIdentifier("gallery_descriptions_$profileId", "array", pkg).takeIf { it != 0 } ?: R.array.gallery_descriptions
        val urls = resources.getStringArray(urlsResId)
        val titles = resources.getStringArray(titlesResId)
        val descriptions = resources.getStringArray(descResId)
        val projects = urls.mapIndexed { index, url ->
            Project(
                url,
                titles.getOrNull(index) ?: "",
                descriptions.getOrNull(index) ?: ""
            )
        }

        // Configurar RecyclerView en grid con columnas dinámicas según resource
        val spanCount = resources.getInteger(R.integer.gallery_span_count)
        binding.recyclerViewProjects.layoutManager = GridLayoutManager(requireContext(), spanCount)
        val adapter = ProjectAdapter(projects) { project -> binding.projectDescription.text = project.description }
        binding.recyclerViewProjects.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 