import tensorflow as tf
import numpy as np
import matplotlib.pyplot as plt

# Datos de temperatura en Celsius y su equivalente en Fahrenheit
celsius = np.array([0, -6.66667, -9.44444, 10, 18.3333, 22.2222, -15, 29.4444, 37.7778, -17.7778], dtype=float)
fahrenheit = np.array([32, 20, 15, 50, 65, 72, 5, 85, 100, 0], dtype=float)

# Definición de la capa del modelo con una neurona
capa = tf.keras.layers.Dense(units=1, input_shape=[1])

# Creación del modelo secuencial con la capa definida
modelo = tf.keras.Sequential([capa])

# Se pueden añadir capas adicionales para mayor complejidad (comentadas en el código)
oculta1 = tf.keras.layers.Dense(units=3, input_shape=[1])
oculta2 = tf.keras.layers.Dense(units=3)
salida = tf.keras.layers.Dense(units=1)
modelo = tf.keras.Sequential([oculta1, oculta2, salida])

# Compilación del modelo con el optimizador Adam y la función de pérdida de error cuadrático medio
modelo.compile(
    optimizer=tf.keras.optimizers.Adam(0.1),
    loss='mean_squared_error'
)

# Entrenamiento del modelo
print("Comenzando entrenamiento...")
historial = modelo.fit(celsius, fahrenheit, epochs=1000, verbose=False)
print("Modelo entrenado!")

# Visualización de la magnitud de pérdida durante el entrenamiento
plt.xlabel("# Epoca")
plt.ylabel("Magnitud de pérdida")
plt.plot(historial.history["loss"])
plt.show()

# Predicción utilizando el modelo entrenado
print("Hagamos una predicción!")
resultado = modelo.predict([100.0])
print("El resultado es " + str(resultado) + " Fahrenheit!")

# Impresión de las variables internas del modelo (pesos y sesgos)
print("Variables internas del modelo")
print(capa.get_weights())
print(oculta1.get_weights())
print(oculta2.get_weights())
print(salida.get_weights())
