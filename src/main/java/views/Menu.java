package views;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu  extends JFrame{
    private JPanel contentPane;
    private JButton crearPedidoButton;
    private JButton listarPedidosRecibidoButton;
    private JButton aprobarPedidosButton;
    private JButton rechazarPedidoButton;
    private JButton crearClienteButton;
    private JButton listarPedidosDespachablesButton;
    private JButton listarPedidosPendientesButton;

    public Menu() {
        setSize(500,500);
        setContentPane(contentPane);
        setLocationRelativeTo(null);
        crearClienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame crearCliente = new CrearCliente();
                crearCliente.setVisible(true);
            }
        });
        crearPedidoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame crearPedido = new CrearPedido();
                crearPedido.setVisible(true);
            }
        });
        listarPedidosRecibidoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame crearCliente = new ListarPedido("RECIBIDO");
                crearCliente.setVisible(true);
            }
        });
        listarPedidosDespachablesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame crearCliente = new ListarPedido("DESPACHABLE");
                crearCliente.setVisible(true);
            }
        });
        listarPedidosPendientesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame crearCliente = new ListarPedido("FALTA_STOCK");
                crearCliente.setVisible(true);
            }
        });
    }
}
