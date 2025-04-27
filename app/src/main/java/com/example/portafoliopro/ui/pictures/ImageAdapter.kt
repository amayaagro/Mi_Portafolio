package com.example.portafoliopro.ui.pictures

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.portafoliopro.databinding.ItemImageBinding
import com.example.portafoliopro.R

class ImageAdapter(
    private val onImageClick: (ProjectImage) -> Unit
) : ListAdapter<ProjectImage, ImageAdapter.ImageViewHolder>(ImageDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = ItemImageBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ImageViewHolder(
        private val binding: ItemImageBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onImageClick(getItem(position))
                }
            }
        }

        fun bind(image: ProjectImage) {
            binding.apply {
                projectImage.setImageResource(image.imageResId)
                projectTitle.text = image.title
                projectDescription.text = image.description
            }
        }
    }

    private class ImageDiffCallback : DiffUtil.ItemCallback<ProjectImage>() {
        override fun areItemsTheSame(oldItem: ProjectImage, newItem: ProjectImage): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ProjectImage, newItem: ProjectImage): Boolean {
            return oldItem == newItem
        }
    }
}

data class ProjectImage(
    val id: Int,
    val imageResId: Int,
    val title: String,
    val description: String
) 