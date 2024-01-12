def binary_search(arr, target):
    left, right = 0, len(arr) - 1
    
    while left <= right:
        mid = left + (right - left) // 2  # Calcular el índice medio
        
        if arr[mid] == target:
            return mid  # El elemento fue encontrado, devuelve su índice
        elif arr[mid] < target:
            left = mid + 1  # El objetivo está en la mitad derecha
        else:
            right = mid - 1  # El objetivo está en la mitad izquierda
    
    return -1  # Si no se encontró el elemento, devuelve -1

# Ejemplo de uso
sorted_list = [2, 4, 7, 10, 13, 18, 23, 27, 33, 40]
target_number = 33

result = binary_search(sorted_list, target_number)

if result != -1:
    print(f"El número {target_number} fue encontrado en el índice {result}.")
else:
    print(f"El número {target_number} no fue encontrado en la lista.")

