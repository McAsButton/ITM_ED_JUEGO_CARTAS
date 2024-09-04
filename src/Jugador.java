import java.util.*;
import java.util.stream.Collectors;
import javax.swing.*;

public class Jugador {
    private final int TOTAL_CARTAS = 10; // Declarar la constante TOTAL_CARTAS
    private final int MARGEN = 10; // Declarar la constante MARGEN
    private final int DISTANCIA = 50; // Declarar la constante DISTANCIA

    private boolean ordenarPorPinta = true; // Variable para alternar el tipo de ordenación

    private Carta[] cartas = new Carta[TOTAL_CARTAS]; // Declarar un arreglo de cartas

    private Random r = new Random(); // Declarar un objeto de la clase Random

    // Método para repartir las cartas
    public void repartir() {
        // Instanciar las 10 cartas
        int i = 0;
        for (@SuppressWarnings("unused")
        Carta c : cartas) {
            cartas[i++] = new Carta(r); // Crear una nueva carta
        }
    }

    // Método para mostrar las cartas en un panel
    public void mostrarCartas(JPanel pnl) {
        pnl.removeAll(); // Eliminar todos los componentes del panel
        int x = 0; // Inicializar la variable x
        for (Carta c : cartas) { // Recorrer todas las cartas
            c.mostrar(pnl, MARGEN + x++ * DISTANCIA, MARGEN); // Mostrar la carta en el panel
        }
        pnl.repaint(); // Volver a pintar el panel
    }

    public String getGruposYCalcularPuntaje() {
        StringBuilder mensaje = new StringBuilder("No se encontraron grupos"); // Usar StringBuilder para eficiencia
        int[] contadores = new int[NombreCarta.values().length]; // Arreglo de contadores
        Set<Carta> cartasEnGrupo = new HashSet<>(); // Set para cartas en grupos
    
        // Contar cartas de cada tipo
        for (Carta c : cartas) {
            contadores[c.getNombre().ordinal()]++; // Incrementar el contador del tipo de carta
        }
    
        boolean hayGrupos = false; // Variable para indicar si hay grupos
        // Verificar si hay grupos de cartas iguales
        for (int i = 0; i < contadores.length; i++) {
            final int INDEX = i; // Variable final para el índice actual
            if (contadores[i] >= 2) { // Si hay dos o más cartas del mismo tipo
                if (!hayGrupos) { // Si se han encontrado grupos
                    mensaje = new StringBuilder("Los grupos encontrados son:\n"); // Inicializar el mensaje
                    hayGrupos = true; // Indicar que se encontraron grupos
                }
                Grupo grupo = Grupo.values()[contadores[i]]; // Obtener el grupo correspondiente
                mensaje.append(grupo).append(" de ").append(NombreCarta.values()[i]).append("\n"); // Agregar al mensaje
    
                // Agregar las cartas al set de cartas en grupo si forman un grupo
                Arrays.stream(cartas)
                        .filter(c -> c.getNombre() == NombreCarta.values()[INDEX])
                        .forEach(cartasEnGrupo::add);
            }
        }
    
        // Agrupar cartas por pinta
        boolean hayGruposDeColor = false; // Variable para indicar si hay grupos de color
        // Recorrer todas las pintas
        for (Pinta pinta : Pinta.values()) {
            List<Integer> valores = Arrays.stream(cartas) // Convertir el arreglo a un stream de cartas y filtrar por pinta
                    .filter(c -> c.getPinta() == pinta)
                    .map(c -> c.getNombre().ordinal())
                    .sorted()
                    .distinct()
                    .collect(Collectors.toList());
    
            // Verificar si hay cartas para formar un grupo
            if (valores.size() >= 2) {
                List<Integer> grupoActual = new ArrayList<>(); // Lista para el grupo actual
                for (int i = 0; i < valores.size(); i++) { // Recorrer los valores
                    if (grupoActual.isEmpty() || valores.get(i) == grupoActual.get(grupoActual.size() - 1) + 1) { // Si el valor es consecutivo al anterior
                        grupoActual.add(valores.get(i)); // Agregar al grupo actual
                    } else {
                        if (grupoActual.size() >= 2) { // Si hay dos o más cartas en el grupo
                            Grupo grupo = Grupo.values()[grupoActual.size()]; // Obtener el grupo correspondiente
                            if (grupo != Grupo.VACIO) { // Si el grupo no está vacío
                                hayGruposDeColor = true; // Indicar que se encontraron grupos de color
                                mensaje.append(grupo).append(" de ").append(pinta).append(": ") // Agregar al mensaje
                                        .append(grupoActual.stream().map(n -> NombreCarta.values()[n].toString())
                                                .collect(Collectors.joining(", ")))
                                        .append("\n");
    
                                // Agregar las cartas al set de cartas en grupo si forman un grupo
                                grupoActual.forEach(n -> Arrays.stream(cartas)
                                        .filter(c -> c.getPinta() == pinta && c.getNombre() == NombreCarta.values()[n])
                                        .forEach(cartasEnGrupo::add));
                            }
                        }
                        grupoActual.clear(); // Limpiar el grupo actual
                        grupoActual.add(valores.get(i)); // Agregar el valor al grupo actual
                    }
                }
    
                // Verificar el último grupo
                if (grupoActual.size() >= 2) {
                    Grupo grupo = Grupo.values()[grupoActual.size()]; // Obtener el grupo correspondiente
                    hayGruposDeColor = true; // Indicar que se encontraron grupos de color
                    mensaje.append(grupo).append(" de ").append(pinta).append(": ") // Agregar al mensaje
                            .append(grupoActual.stream().map(n -> NombreCarta.values()[n].toString())
                                    .collect(Collectors.joining(", ")))
                            .append("\n");
    
                    // Agregar las cartas al set de cartas en grupo si forman un grupo
                    grupoActual.forEach(n -> Arrays.stream(cartas)
                            .filter(c -> c.getPinta() == pinta && c.getNombre() == NombreCarta.values()[n])
                            .forEach(cartasEnGrupo::add));
                }
            }
        }
    
        if (!hayGrupos && !hayGruposDeColor) { // Si no se encontraron grupos
            mensaje.append("No se encontraron grupos\n"); // Agregar al mensaje
        }
    
        // Calcular el puntaje de las cartas que no están en grupos
        int puntajeNoEnGrupo = Arrays.stream(cartas) // Calcular el puntaje de las cartas que no están en grupos y sumarlos
                .filter(c -> !cartasEnGrupo.contains(c))
                .mapToInt(c -> c.getNombre().retornarNumero())
                .sum();
    
        mensaje.append("Puntaje de cartas que no están en grupos: ").append(puntajeNoEnGrupo); // Agregar al mensaje
    
        return mensaje.toString(); // Retornar el mensaje
    }

    // Método para ordenar las cartas
    public void ordenarCartas() {
        if (ordenarPorPinta) {
            // Ordenar por pinta y luego por nombre de carta
            Arrays.sort(cartas, Comparator.comparing(Carta::getPinta).thenComparing(Carta::getNombre));
        } else {
            // Ordenar por nombre de carta y luego por pinta
            Arrays.sort(cartas, Comparator.comparing(Carta::getNombre).thenComparing(Carta::getPinta));
        }
    }

    // Método para obtener el estado de la variable ordenarPorPinta
    public boolean isOrdenarPorPinta() {
        return ordenarPorPinta; 
    }

    // Método para establecer el estado de la variable ordenarPorPinta
    public void setOrdenarPorPinta(boolean ordenarPorPinta) {
        this.ordenarPorPinta = ordenarPorPinta;
    }
}
