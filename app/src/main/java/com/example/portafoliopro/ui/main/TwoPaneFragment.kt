package com.example.portafoliopro.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import android.widget.Button
import com.example.portafoliopro.R
import com.example.portafoliopro.databinding.FragmentTwoPaneBinding
import com.example.portafoliopro.ui.profile.CustomProfileFragment
import com.example.portafoliopro.ui.profile.PaulaGarciaProfileFragment
import com.example.portafoliopro.ui.profile.CarlosMendozaProfileFragment
import com.example.portafoliopro.ui.profile.MariaLopezProfileFragment
import com.example.portafoliopro.ui.profile.JuanPerezProfileFragment
import com.example.portafoliopro.ui.gallery.GalleryFragment
import com.example.portafoliopro.ui.video.VideoFragment
import com.example.portafoliopro.ui.web.WebFragment
import com.example.portafoliopro.ui.buttons.ButtonsFragment
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView

class TwoPaneFragment : Fragment() {

    private var _binding: FragmentTwoPaneBinding? = null
    private val binding get() = _binding!!

    var profileId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        profileId = arguments?.getInt("profileId") ?: 0
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTwoPaneBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Configurar botón de hamburguesa para togglear panel izquierdo
        binding.twoPaneToolbar.setNavigationOnClickListener {
            if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                binding.drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                binding.drawerLayout.openDrawer(GravityCompat.START)
            }
        }
        // Manejar selección del menú del Navigation Drawer
        binding.navView.setNavigationItemSelectedListener { menuItem ->
            when(menuItem.itemId) {
                R.id.nav_profile -> showProfile(profileId)
                R.id.nav_pictures -> showFotos()
                R.id.nav_video -> showVideo()
                R.id.nav_web -> showWeb()
                R.id.nav_button -> showButtons()
            }
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
        // Mostrar perfil inicial en rightPane
        showProfile(profileId)
    }

    fun showProfile(id: Int) {
        childFragmentManager.commit {
            replace(binding.rightPane.id, when (id) {
                0 -> CustomProfileFragment()
                1 -> AlexanderAmayaProfileFragment()
                2 -> BrayanAlvarezProfileFragment()
                3 -> IlderAguilarProfileFragment()
                4 -> GustavoMahechaProfileFragment()
                else -> CustomProfileFragment()
            })
        }
    }

    fun showFotos() {
        childFragmentManager.commit {
            replace(binding.rightPane.id, GalleryFragment.newInstance(profileId))
        }
    }

    fun showVideo() {
        childFragmentManager.commit {
            replace(binding.rightPane.id, VideoFragment())
        }
    }

    fun showWeb() {
        childFragmentManager.commit {
            replace(binding.rightPane.id, WebFragment())
        }
    }

    fun showButtons() {
        childFragmentManager.commit {
            replace(binding.rightPane.id, ButtonsFragment.newInstance(profileId))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

// Opciones fragment
class OptionsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_options, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.btnOptionProfile).setOnClickListener {
            (parentFragment as TwoPaneFragment).showProfile(
                (parentFragment as TwoPaneFragment).profileId
            )
        }
        view.findViewById<Button>(R.id.btnOptionFotos).setOnClickListener {
            (parentFragment as TwoPaneFragment).showFotos()
        }
        view.findViewById<Button>(R.id.btnOptionVideo).setOnClickListener {
            (parentFragment as TwoPaneFragment).showVideo()
        }
        view.findViewById<Button>(R.id.btnOptionWeb).setOnClickListener {
            (parentFragment as TwoPaneFragment).showWeb()
        }
        view.findViewById<Button>(R.id.btnOptionButtons).setOnClickListener {
            (parentFragment as TwoPaneFragment).showButtons()
        }
    }
} 
