package com.example.portafoliopro.ui.web

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.example.portafoliopro.databinding.FragmentWebBinding

class WebFragment : Fragment() {

    private var _binding: FragmentWebBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWebBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupWebView()
    }

    private fun setupWebView() {
        binding.apply {
            webView.webViewClient = WebViewClient()
            val settings: WebSettings = webView.settings
            settings.javaScriptEnabled = true

            btnLoad.setOnClickListener {
                val url = inputUrl.text.toString().trim()
                if (url.isNotEmpty()) {
                    val fullUrl = if (url.startsWith("http")) url else "https://$url"
                    webView.loadUrl(fullUrl)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}