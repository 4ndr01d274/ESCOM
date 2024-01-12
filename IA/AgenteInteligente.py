import random  # Importa la librería random para generar números aleatorios

class GuessingAgent:    # Define la clase GuessingAgent, que representa al agente adivinador.
    def __init__(self, min_range, max_range):
        self.min_range = min_range
        self.max_range = max_range
    
    def guess(self):    # El agente se inicializa con un rango mínimo y máximo en el que adivinará el número secreto.
        return random.randint(self.min_range, self.max_range)

def main():             # Configura el rango de números entre los que el agente adivinará y se genera un número secreto aleatorio.
    print("Bienvenido al juego 'Adivina el número'!")
    min_range = 1
    max_range = 100
    number_to_guess = random.randint(min_range, max_range)
    agent = GuessingAgent(min_range, max_range)
    
    while True:         # El agente hace una suposición, muestra el número adivinado y solicita la respuesta del usuario.
        agent_guess = agent.guess()
        print(f"El agente adivina: {agent_guess}")
        user_response = input("Es demasiado alto (A), demasiado bajo (B) o correcto (C)? ").upper()
        
        if user_response == "A":
            agent.max_range = agent_guess - 1
        elif user_response == "B":
            agent.min_range = agent_guess + 1
        elif user_response == "C":
            print("El agente ha adivinado el número correctamente!")
            break
        else:
            print("Respuesta no válida. Por favor, responde con A, B o C.")
        
        # Basado en la respuesta del usuario (A, B o C), el agente ajusta su rango de búsqueda para la próxima adivinanza.
        # Si la respuesta es "C", el juego termina.

if __name__ == "__main__":
    main()

