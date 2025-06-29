package com.example.mqtt_temperature.mqtt

import android.util.Log
import org.eclipse.paho.client.mqttv3.*

class MqttHelper {

    private val clientId = MqttClient.generateClientId()
    private lateinit var mqttClient: MqttClient

    fun connect(onMessageReceived: (topic: String, payload: String) -> Unit) {
        try {
            mqttClient = MqttClient(
                "tcp://mqtt-temperature.cloud.shiftr.io:1883",
                clientId,
                null
            )

            val options = MqttConnectOptions().apply {
                isCleanSession = true
                isAutomaticReconnect = true
                userName = ""
                password = "".toCharArray()
                connectionTimeout = 10
                keepAliveInterval = 30
            }

            mqttClient.setCallback(object : MqttCallback {
                override fun connectionLost(cause: Throwable?) {
                    Log.e("MQTT", "Conexión perdida: ${cause?.message}")
                }

                override fun messageArrived(topic: String?, message: MqttMessage?) {
                    topic?.let {
                        onMessageReceived(it, message.toString())
                    }
                }

                override fun deliveryComplete(token: IMqttDeliveryToken?) {
                    // No usas publishing, así que puedes dejar esto vacío
                }
            })

            mqttClient.connect(options)

            mqttClient.subscribe("casa/salon/temperatura", 0)
            mqttClient.subscribe("casa/salon/humedad", 0)

        } catch (e: Exception) {
            Log.e("MQTT", "Error al conectar: ${e.localizedMessage}")
        }
    }

    fun disconnect() {
        try {
            if (::mqttClient.isInitialized && mqttClient.isConnected) {
                mqttClient.disconnect()
                mqttClient.close()
            }
        } catch (e: Exception) {
            Log.e("MQTT", "Error al desconectar: ${e.localizedMessage}")
        }
    }
}
