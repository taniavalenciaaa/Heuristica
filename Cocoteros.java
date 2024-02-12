import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cocoteros extends JFrame {
    private JButton btnResolver;
    private JLabel lblResultado;
    private JLabel lblAnimacion;

    public Cocoteros() {
        setTitle("Cocoteros");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));

        btnResolver = new JButton("Resolver");
        panel.add(btnResolver);

        panel.add(new JLabel("Resultado:"));
        lblResultado = new JLabel();
        panel.add(lblResultado);

        lblAnimacion = new JLabel();
        panel.add(lblAnimacion);

        btnResolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resolver();
            }
        });

        add(panel);
    }

    private void resolver() {
        // Establecer la cantidad de cocos iniciales
        int cocosIniciales = 3121; // Cantidad fija de cocos iniciales

        lblAnimacion.setText("Dividiendo cocos...");
        Timer timer = new Timer(1000, new ActionListener() {
            int cocos = cocosIniciales;
            int contador = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (contador < 5) {
                    if (cocos % 5 == 1) {
                        cocos = (cocos - 1) / 5 * 4;
                    } else {
                        ((Timer) e.getSource()).stop();
                        lblResultado.setText("No hay solución válida");
                        return;
                    }
                    lblAnimacion.setText("Día " + (contador + 1) + ": " + cocos + " cocos restantes");
                    contador++;
                } else {
                    ((Timer) e.getSource()).stop();
                    lblResultado.setText("Cocos finales: " + cocos);
                }
            }
        });
        timer.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Cocoteros().setVisible(true);
            }
        });
    }
}
