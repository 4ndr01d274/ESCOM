import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
from sklearn import preprocessing
from sklearn.neighbors import KNeighborsClassifier
from sklearn.preprocessing import MinMaxScaler
from sklearn.model_selection import train_test_split

clientes = pd.read_csv("creditos.csv")
pagadores = clientes[clientes["cumplio"]==1]
deudores = clientes[clientes["cumplio"]==0]

plt.scatter(deudores["edad"], deudores["credito"], label='Deudores', color='green')
plt.scatter(pagadores["edad"], pagadores["credito"], label='Pagadores', color='brown')

# Etiquetas y leyenda
plt.xlabel('Edad')
plt.ylabel('Crédito')
plt.legend()
plt.title('Distribución de deudores y pagadores')

# Mostrar el gráfico
plt.show()

print("Deudores:")
print(deudores)

# Imprimir la lista de pagadores
print("\nPagadores:")
print(pagadores)

# Imprimir la cantidad de elementos en cada lista
print("\nCantidad de deudores:", len(deudores))
print("Cantidad de pagadores:", len(pagadores))

# Seleccionar las columnas relevantes para escalar
columnas_escalables = ["edad", "credito"]
datos_escalables = clientes[columnas_escalables]

# Escalar los datos
escalador = MinMaxScaler()
datos_escalados = escalador.fit_transform(datos_escalables)

# Crear un nuevo DataFrame con los datos escalados
datos_escalados_df = pd.DataFrame(datos_escalados, columns=columnas_escalables)
datos_escalados_df["cumplio"] = clientes["cumplio"]

# Imprimir la lista de datos escalados
print("Datos escalados:")
print(datos_escalados_df)

# Dividir el conjunto de datos en características (X) y etiquetas (y)
X = clientes[["edad", "credito"]]
y = clientes["cumplio"]

# Escalar los datos
escalador = MinMaxScaler()
X_scaled = escalador.fit_transform(X)

# Dividir los datos en conjuntos de entrenamiento y prueba
X_train, X_test, y_train, y_test = train_test_split(X_scaled, y, test_size=0.2, random_state=42)

# Crear y entrenar el modelo
algoritmo = KNeighborsClassifier(n_neighbors=3)  # Puedes ajustar los parámetros según sea necesario
algoritmo.fit(X_train, y_train)

# Realizar predicciones
y_pred = algoritmo.predict(X_test)

# Imprimir las primeras 5 predicciones
print("Predicciones para los primeros 5 solicitantes:")
print(y_pred[:5])

# Imprimir las clases asignadas por el clasificador a cada uno
print("\nClases asignadas por el clasificador:")
print(algoritmo.classes_)

# Imprimir las probabilidades para cada clase (solo aplicable para clasificadores que proporcionan probabilidades)
if hasattr(algoritmo, "predict_proba"):
    probabilidades = algoritmo.predict_proba(X_test[:5])
    print("\nProbabilidades para las primeras 5 predicciones:")
    print(probabilidades)

# Visualizar las regiones por clase
# (Nota: Este código es solo un ejemplo y puede no funcionar adecuadamente para todos los clasificadores)
xx, yy = np.meshgrid(np.linspace(0, 1, 100), np.linspace(0, 1, 100))
Z = algoritmo.predict(np.c_[xx.ravel(), yy.ravel()])
Z = Z.reshape(xx.shape)

plt.contourf(xx, yy, Z, alpha=0.3, cmap='viridis')

# Pintar deudores en café y pagadores en verde
plt.scatter(X_test[y_test == 0][:, 0], X_test[y_test == 0][:, 1], color='brown', label='Deudores')
plt.scatter(X_test[y_test == 1][:, 0], X_test[y_test == 1][:, 1], color='green', label='Pagadores')

# Etiquetas y leyenda
plt.xlabel('Edad')
plt.ylabel('Crédito')
plt.legend()
plt.title('Regiones por clase')

# Mostrar el gráfico
plt.show()