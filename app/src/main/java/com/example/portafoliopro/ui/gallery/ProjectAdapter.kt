package com.example.portafoliopro.ui.gallery

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.portafoliopro.R
import com.example.portafoliopro.databinding.ItemProjectBinding
import coil.load
import com.example.portafoliopro.ui.gallery.Project

class ProjectAdapter(
    private val projects: List<Project>,
    private val onClick: (Project) -> Unit
) : RecyclerView.Adapter<ProjectAdapter.ProjectViewHolder>() {

    inner class ProjectViewHolder(private val binding: ItemProjectBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(project: Project) {
            // Cargar imagen desde URL con Coil
            binding.imageProject.load(project.imageUrl) {
                crossfade(true)
                placeholder(R.drawable.ic_photo)
            }
            binding.textProjectTitle.text = project.title
            binding.root.setOnClickListener { onClick(project) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectViewHolder {
        val binding = ItemProjectBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProjectViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {
        holder.bind(projects[position])
    }

    override fun getItemCount() = projects.size
} 