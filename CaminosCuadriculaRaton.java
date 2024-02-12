import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class CaminosCuadriculaRaton extends JFrame {
    private static final int GRID_SIZE = 5;
    private static final int SQUARE_SIZE = 50;
    private static final int WINDOW_WIDTH = GRID_SIZE * SQUARE_SIZE;
    private static final int WINDOW_HEIGHT = GRID_SIZE * SQUARE_SIZE;
    private static final int[][] GRID = new int[GRID_SIZE][GRID_SIZE];

    public CaminosCuadriculaRaton() {
        setTitle("Caminos en una cuadrícula");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        
        drawGrid(g2d);
        int numCaminos = calcularCaminos();
        System.out.println("Número de caminos posibles: " + numCaminos);
    }

    private void drawGrid(Graphics2D g2d) {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                Rectangle2D square = new Rectangle2D.Double(i * SQUARE_SIZE, j * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
                if (esCamino(i, j)) {
                    g2d.setColor(Color.GREEN); // Color para los cuadrados en el camino
                } else {
                    g2d.setColor(Color.LIGHT_GRAY); // Color para los cuadrados fuera del camino
                }
                g2d.fill(square);
                g2d.setColor(Color.BLACK);
                g2d.draw(square);
            }
        }
    }

    private boolean esCamino(int x, int y) {
        // Marca los cuadrados que están en el camino como verdadero
        return (x == GRID_SIZE - 1 || y == GRID_SIZE - 1);
    }

    private int calcularCaminos() {
        for (int i = 0; i < GRID_SIZE; i++) {
            GRID[i][0] = 1;
            GRID[0][i] = 1;
        }

        for (int i = 1; i < GRID_SIZE; i++) {
            for (int j = 1; j < GRID_SIZE; j++) {
                GRID[i][j] = GRID[i - 1][j] + GRID[i][j - 1];
            }
        }

        return GRID[GRID_SIZE - 1][GRID_SIZE - 1];
    }

    public static void main(String[] args) {
        CaminosCuadricula caminosCuadricula = new CaminosCuadricula();
        caminosCuadricula.setVisible(true);
    }
}
