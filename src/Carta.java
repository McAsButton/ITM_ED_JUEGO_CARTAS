import javax.swing.*;
import java.util.Random;

public class Carta {
    private int indice; // Declarar la variable indice

    // Constructor de la clase Carta
    public Carta(Random r) {
        // Generar el indice de la carta de manera aleatoria
        indice = r.nextInt(52) + 1;
    }

    // Método para mostrar la carta en un panel
    public void mostrar(JPanel pnl, int x, int y) {
        String nombreImagen = "/img/CARTA" + String.valueOf(indice) + ".jpg"; // Obtener el nombre del archivo de la carta
        ImageIcon imgCarta = new ImageIcon(getClass().getResource(nombreImagen)); // Obtener la imagen de la carta
        JLabel lblCarta = new JLabel(imgCarta); // Crear un objeto JLabel con la imagen de la carta
        lblCarta.setBounds(x, y, imgCarta.getIconWidth(), imgCarta.getIconHeight()); // Establecer la posición y tamaño del JLabel
        pnl.add(lblCarta); // Agregar el JLabel al panel
    }

    // Método para obtener la pinta de la carta
    public Pinta getPinta() {
        if (indice <= 13) { // Si el indice es menor o igual a 13
            return Pinta.TREBOL; // Retornar la pinta TREBOL
        } else if (indice <= 26) { // Si el indice es menor o igual a 26 
            return Pinta.PICA; // Retornar la pinta PICA
        } else if (indice <= 39) { // Si el indice es menor o igual a 39
            return Pinta.CORAZON; // Retornar la pinta CORAZON
        } else { // Si no se cumple ninguna de las condiciones anteriores
            return Pinta.DIAMANTE; // Retornar la pinta DIAMANTE
        }
    }

    // Método para obtener el nombre de la carta (enumeración)
    public NombreCarta getNombre() { // Método para obtener el nombre de la carta
        int residuo = indice % 13; // Calcular el residuo de la división del indice entre 13
        if (residuo == 0) { // Si el residuo es igual a 0
            residuo = 13; // Asignar el valor 13 al residuo
        }
        return NombreCarta.values()[residuo - 1]; // Retornar el nombre de la carta
    }
}
