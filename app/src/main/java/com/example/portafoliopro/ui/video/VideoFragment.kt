package com.example.portafoliopro.ui.video

import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.SeekBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.portafoliopro.R
import com.example.portafoliopro.databinding.FragmentVideoBinding
import java.util.concurrent.TimeUnit

class VideoFragment : Fragment() {

    private var _binding: FragmentVideoBinding? = null
    private val binding get() = _binding!!
    private val handler = Handler(Looper.getMainLooper())
    private var isPlaying = false
    private var isControlsVisible = true
    private val hideControlsDelay = 3000L // 3 segundos

    private val updateSeekBar: Runnable by lazy {
        Runnable {
            if (_binding != null) {
                // Actualizar barra de progreso y tiempo
                val currentPosition = binding.videoView.currentPosition
                binding.seekBar.progress = currentPosition
                binding.currentTime.text = formatTime(currentPosition.toLong())
                // Programar próxima actualización
                handler.postDelayed(updateSeekBar, 1000)
            }
        }
    }

    private val hideControlsRunnable: Runnable = Runnable {
        if (_binding != null && isPlaying) {
            binding.mediaControls.animate()
                .alpha(0f)
                .setDuration(500)
                .withEndAction {
                    binding.mediaControls.visibility = View.GONE
                }
            isControlsVisible = false
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVideoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupVideoPlayer()
        setupControls()
        setupTouchListener()
    }

    private fun setupVideoPlayer() {
        binding.apply {
            try {
                // Intentar cargar el video desde assets
                val videoPath = ("https://jotajotavm.com/img/video.mp4")
                videoView.setVideoPath(videoPath)
                
                videoView.setOnPreparedListener { mediaPlayer ->
                    seekBar.max = mediaPlayer.duration
                    totalTime.text = formatTime(mediaPlayer.duration.toLong())
                    // Ajustar volumen inicial
                    mediaPlayer.setVolume(0.7f, 0.7f)
                }

                videoView.setOnCompletionListener {
                    btnPlayPause.setImageResource(android.R.drawable.ic_media_play)
                    isPlaying = false
                    handler.removeCallbacks(updateSeekBar)
                    showControls()
                }

                videoView.setOnErrorListener { _, what, extra ->
                    val message = when (what) {
                        1 -> "Error desconocido"
                        -1004 -> "Error al cargar el video"
                        -1007 -> "Error de red"
                        else -> "Error de reproducción"
                    }
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                    true
                }
            } catch (e: Exception) {
                Toast.makeText(context, "Error al cargar el video: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun setupControls() {
        binding.apply {
            btnPlayPause.setOnClickListener {
                togglePlayPause()
            }

            btnRewind.setOnClickListener {
                val newPosition = videoView.currentPosition - 5000 // Retroceder 5 segundos
                videoView.seekTo(maxOf(0, newPosition))
                showControls()
            }

            btnForward.setOnClickListener {
                val newPosition = videoView.currentPosition + 5000 // Avanzar 5 segundos
                videoView.seekTo(minOf(videoView.duration, newPosition))
                showControls()
            }

            seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    if (fromUser) {
                        videoView.seekTo(progress)
                        currentTime.text = formatTime(progress.toLong())
                    }
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {
                    handler.removeCallbacks(updateSeekBar)
                    handler.removeCallbacks(hideControlsRunnable)
                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                    if (isPlaying) {
                        handler.post(updateSeekBar)
                        scheduleHideControls()
                    }
                }
            })

            // Ocultar botones de anterior/siguiente
            btnPrevious.visibility = View.GONE
            btnNext.visibility = View.GONE
        }
    }

    private fun setupTouchListener() {
        binding.videoView.setOnClickListener {
            if (isControlsVisible) {
                hideControls()
            } else {
                showControls()
            }
        }
    }

    private fun togglePlayPause() {
        binding.apply {
            if (isPlaying) {
                videoView.pause()
                btnPlayPause.setImageResource(android.R.drawable.ic_media_play)
                handler.removeCallbacks(updateSeekBar)
                handler.removeCallbacks(hideControlsRunnable)
                showControls()
            } else {
                videoView.start()
                btnPlayPause.setImageResource(android.R.drawable.ic_media_pause)
                handler.post(updateSeekBar)
                scheduleHideControls()
            }
            isPlaying = !isPlaying
        }
    }

    private fun showControls() {
        binding.mediaControls.apply {
            visibility = View.VISIBLE
            alpha = 1f
        }
        isControlsVisible = true
        scheduleHideControls()
    }

    private fun hideControls() {
        if (isPlaying) {
            binding.mediaControls.animate()
                .alpha(0f)
                .setDuration(500)
                .withEndAction {
                    binding.mediaControls.visibility = View.GONE
                }
            isControlsVisible = false
        }
    }

    private fun scheduleHideControls() {
        handler.removeCallbacks(hideControlsRunnable)
        handler.postDelayed(hideControlsRunnable, hideControlsDelay)
    }

    private fun formatTime(milliseconds: Long): String {
        return String.format(
            "%d:%02d",
            TimeUnit.MILLISECONDS.toMinutes(milliseconds),
            TimeUnit.MILLISECONDS.toSeconds(milliseconds) -
                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(milliseconds))
        )
    }

    override fun onPause() {
        super.onPause()
        binding.videoView.pause()
        handler.removeCallbacks(updateSeekBar)
        handler.removeCallbacks(hideControlsRunnable)
        isPlaying = false
        binding.btnPlayPause.setImageResource(android.R.drawable.ic_media_play)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        handler.removeCallbacks(updateSeekBar)
        handler.removeCallbacks(hideControlsRunnable)
        _binding = null
    }
}
