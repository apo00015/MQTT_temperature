package com.example.mqtt_temperature

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.mqtt_temperature.databinding.FragmentFirstBinding
import com.example.mqtt_temperature.mqtt.MqttHelper
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class FirstFragment : Fragment() {

    private lateinit var txtTemp: TextView
    private lateinit var txtHum: TextView
    private lateinit var txtTimestamp: TextView
    private lateinit var mqttHelper: MqttHelper

    private var _binding: FragmentFirstBinding? = null

    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        txtTemp = view.findViewById(R.id.txtTemperatura)
        txtHum = view.findViewById(R.id.txtHumedad)
        txtTimestamp = view.findViewById(R.id.txtTimestamp)
        mqttHelper = MqttHelper()

        // Llama a connect en un hilo aparte para no bloquear el hilo principal
        Thread {
            mqttHelper.connect { topic, value ->
                val currentTime = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date())

                activity?.runOnUiThread {
                    when (topic) {
                        "casa/salon/temperatura" -> txtTemp.text = "$value °C"
                        "casa/salon/humedad" -> txtHum.text = "$value %"
                    }
                    binding.txtTimestamp.text = "Última actualización: $currentTime"
                }
            }
        }.start()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mqttHelper.disconnect()
    }

}