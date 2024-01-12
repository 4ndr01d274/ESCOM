import numpy as np
import matplotlib.pyplot as plt
from sklearn.linear_model import Perceptron

# Datos de 10 personas -> [edad, ahorro]
personas = np.array([[0.3, 0.4], [0.4, 0.3],
                     [0.3, 0.2], [0.4, 0.1], 
                     [0.5, 0.2], [0.4, 0.8],
                     [0.6, 0.8], [0.5, 0.6], 
                     [0.7, 0.6], [0.8, 0.5]])

# 1: Aprobada, 0: Denegada
clases = np.array([0, 0, 0, 0, 0, 1, 1, 1, 1, 1])

# Gráfica de dispersión (edad, ahorro)
plt.figure(figsize=(7, 7))
plt.title("¿Tarjeta Platinum?", fontsize=20)
plt.scatter(personas[clases == 0].T[0], 
            personas[clases == 0].T[1], 
            marker="x", s=180, color="brown",
            linewidths=5, label="Denegada")
plt.scatter(personas[clases == 1].T[0],
            personas[clases == 1].T[1], 
            marker="o", s=180, color="green",
            linewidths=5, label="Aprobada")
plt.xlabel("Edad", fontsize=15)
plt.ylabel("Ahorro", fontsize=15)
plt.legend(bbox_to_anchor=(1.3, 0.15))
plt.box(False)
plt.xlim((0, 1.01))
plt.ylim((0, 1.01))
plt.grid()
plt.show()

# Definimos la función de activación con valores de pesos, ahorro y edad
def activacion(pesos, x, b):
    z = pesos * x
    if z.sum() + b > 0:
        return 1
    else:
        return 0

# Generamos números aleatorios para representar los pesos y el umbral
pesos = np.random.uniform(-1, 1, size=2)    
b = np.random.uniform(-1, 1)

# Imprimimos los valores iniciales y la activación para un ejemplo
print("Valores iniciales:", pesos, b)
print("Activación para [0.7, 0.9]:", activacion(pesos, [0.7, 0.9], b))

# Parámetros de entrenamiento
tasa_de_aprendizaje = 0.01
epocas = 100

# Entrenamiento del perceptrón
for epoca in range(epocas):
    error_total = 0
    for i in range(len(personas)):
        # Realizamos la predicción
        prediccion = activacion(pesos, personas[i], b)
        
        # Calculamos el error
        error = clases[i] - prediccion
        error_total += error**2
        
        # Ajustamos los pesos y el umbral
        pesos[0] += tasa_de_aprendizaje * personas[i][0] * error
        pesos[1] += tasa_de_aprendizaje * personas[i][1] * error
        b += tasa_de_aprendizaje * error 
    print(error_total, end=" ")

print("\n",activacion(pesos, [0.5, 0.5], b))

# Creamos un objeto perceptrón
perceptron = Perceptron()

# Entrenamos el perceptrón
perceptron.fit(personas, clases)

# Hacemos dos predicciones
prediccion1 = perceptron.predict([[0.7, 0.9]])
prediccion2 = perceptron.predict([[0.2, 0.2]])

# Imprimimos las predicciones
print("Predicción 1:", prediccion1)
print("Predicción 2:", prediccion2)