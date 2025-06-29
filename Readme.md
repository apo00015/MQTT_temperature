# 🌡️ MQTT Temperature App

Una aplicación Android desarrollada en Kotlin que se conecta a un **broker MQTT de Shiftr.io** para visualizar en tiempo real los valores de **temperatura** y **humedad** publicados desde un sensor DHT22 a través de una placa ESP8266.

---

## 📲 Funcionalidad

- Se conecta automáticamente al broker MQTT `mqtt-temperature.cloud.shiftr.io`.
- Se suscribe a los topics:
    - `casa/salon/temperatura`
    - `casa/salon/humedad`
- Muestra los valores actualizados en pantalla.
- Indica la hora exacta del **último mensaje recibido**.
- Interfaz simple, sin bloqueo de la UI.

---


## ⚙️ Tecnologías utilizadas

- Kotlin
- AndroidX
- MQTT con [Eclipse Paho Client (MqttClient)](https://www.eclipse.org/paho/)
- ViewBinding
- [Shiftr.io](https://shiftr.io) como broker MQTT

---