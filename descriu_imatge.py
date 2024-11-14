import subprocess
import json

# Defineix el model i la ruta de la imatge
model = "llava:7b"
ruta_imatge = "imatge.jpg"

# Executa Ollama mitjançant subprocess per descriure la imatge
try:
    resultat = subprocess.run(
        ["ollama", "run", model, "--image", ruta_imatge],
        capture_output=True,
        text=True,
        check=True
    )

    # Verifica si la resposta està en format JSON i la parseja
    respuesta = json.loads(resultat.stdout)
    print("Descripció de la imatge:", respuesta['content'])

except subprocess.CalledProcessError as e:
    print("Error al executar Ollama:", e.stderr)
except json.JSONDecodeError:
    print("Error al parsejar la resposta JSON.")
