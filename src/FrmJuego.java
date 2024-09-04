import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class FrmJuego extends JFrame {

    private JButton btnRepartir, btnVerificar, btnOrdenar; // Declarar los botones
    private JPanel pnlJugador1, pnlJugador2; // Declarar los paneles
    private JTabbedPane tpJugadores; // Declarar el panel de pestañas

    Jugador jugador1 = new Jugador(); // Declarar un objeto de la clase Jugador
    Jugador jugador2 = new Jugador(); // Declarar un objeto de la clase Jugador

    public FrmJuego() {
        // Inicializar los componentes
        btnRepartir = new JButton(); // Crear un botón
        btnVerificar = new JButton(); // Crear un botón
        btnOrdenar = new JButton(); // Crear un botón
        tpJugadores = new JTabbedPane(); // Crear un panel de pestañas
        pnlJugador1 = new JPanel(); // Crear un panel
        pnlJugador2 = new JPanel(); // Crear un panel

        // Establecer las propiedades de la ventana
        setSize(590, 260); // Establecer el tamaño de la ventana
        setTitle("Juego de Cartas"); // Establecer el título de la ventana
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // Establecer la acción al cerrar la ventana

        // Establecer las propiedades de los componentes
        pnlJugador1.setBackground(new Color(153, 255, 51)); // Establecer el color de fondo del panel
        pnlJugador1.setLayout(null); // Establecer el layout del panel
        pnlJugador2.setBackground(new Color(0, 255, 255)); // Establecer el color de fondo del panel
        pnlJugador2.setLayout(null); // Establecer el layout del panel

        tpJugadores.setBounds(10, 40, 550, 170); // Establecer la posición y tamaño del panel de pestañas
        tpJugadores.addTab("Martín Estrada Contreras", pnlJugador1); // Agregar una pestaña al panel de pestañas
        tpJugadores.addTab("Raul Vidal", pnlJugador2); // Agregar una pestaña al panel de pestañas

        btnRepartir.setBounds(10, 10, 100, 25); // Establecer la posición y tamaño del botón
        btnRepartir.setText("Repartir"); // Establecer el texto del botón
        btnRepartir.addActionListener(new ActionListener() { // Agregar un evento al botón
            public void actionPerformed(ActionEvent evt) { // Método que se ejecuta al hacer clic en el botón
                btnRepartirClick(evt); // Llamar al método btnRepartirClick
            }
        });

        btnVerificar.setBounds(120, 10, 100, 25); // Establecer la posición y tamaño del botón
        btnVerificar.setText("Verificar"); // Establecer el texto del botón
        btnVerificar.addActionListener(new ActionListener() { // Agregar un evento al botón
            public void actionPerformed(ActionEvent evt) { // Método que se ejecuta al hacer clic en el botón
                btnVerificarClick(evt); // Llamar al método btnVerificarClick
            }
        });

        btnOrdenar.setBounds(230, 10, 100, 25); // Establecer la posición y tamaño del botón
        btnOrdenar.setText("Ordenar"); // Establecer el texto del botón
        btnOrdenar.addActionListener(new ActionListener() { // Agregar un evento al botón
            public void actionPerformed(ActionEvent evt) { // Método que se ejecuta al hacer clic en el botón
                btnOrdenarClick(evt); // Llamar al método btnOrdenarClick
            }
        });

        getContentPane().setLayout(null); // Establecer el layout del contenedor
        getContentPane().add(tpJugadores); // Agregar el panel de pestañas al contenedor
        getContentPane().add(btnRepartir); // Agregar el botón al contenedor
        getContentPane().add(btnVerificar); // Agregar el botón al contenedor
        getContentPane().add(btnOrdenar); // Agregar el botón al contenedor
    }

    private void btnRepartirClick(ActionEvent evt) {
        jugador1.repartir(); // Repartir las cartas
        jugador1.mostrarCartas(pnlJugador1); // Mostrar las cartas en el panel

        jugador2.repartir(); // Repartir las cartas
        jugador2.mostrarCartas(pnlJugador2); // Mostrar las cartas en el panel
    }

    private void btnVerificarClick(ActionEvent evt) {
        int pestana = tpJugadores.getSelectedIndex(); // Obtener el índice de la pestaña seleccionada
        switch (pestana) { // Evaluar el índice de la pestaña seleccionada
            case 0: // Si el índice es 0
                JOptionPane.showMessageDialog(null, jugador1.getGruposYCalcularPuntaje()); // Mostrar un mensaje con los grupos encontrados
                break;
            case 1: // Si el índice es 1
                JOptionPane.showMessageDialog(null, jugador2.getGruposYCalcularPuntaje()); // Mostrar un mensaje con los grupos encontrados
                break;
            default:
                break;
        }
    }

    private void btnOrdenarClick(ActionEvent evt) {
        int pestana = tpJugadores.getSelectedIndex(); // Obtener el índice de la pestaña seleccionada
        switch (pestana) { // Evaluar el índice de la pestaña seleccionada
            case 0: // Si el índice es 0
                // Alternar el criterio de ordenación para el próximo clic
                jugador1.setOrdenarPorPinta(!jugador1.isOrdenarPorPinta()); // Alternar el criterio de ordenación
                jugador1.ordenarCartas(); // Ordenar las cartas
                jugador1.mostrarCartas(pnlJugador1); // Mostrar las cartas en el panel
                break;
            case 1: // Si el índice es 1
                // Alternar el criterio de ordenación para el próximo clic
                jugador2.setOrdenarPorPinta(!jugador2.isOrdenarPorPinta()); // Alternar el criterio de ordenación
                jugador2.ordenarCartas(); // Ordenar las cartas
                jugador2.mostrarCartas(pnlJugador2); // Mostrar las cartas en el panel
                break;
            default:
                break;
        }
    }
}