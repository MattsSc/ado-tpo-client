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
    private JButton completarPedidoButton;
    private JButton crearOrdenDeCompraButton;
    private JButton notificarOCButton;
    private JButton pedidoDespachadosButton;
    private JButton pedidosCompletadosButton;
    private JButton movimientoClienteButton;

    public Menu() {
        setSize(500,500);
        setContentPane(contentPane);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        crearClienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame crearCliente = new CrearCliente();
                crearCliente.setVisible(true);
            }
        });
        listarPedidosRecibidoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame crearCliente = new ListarPedido("RECIBIDO", "");
                crearCliente.setVisible(true);
            }
        });
        movimientoClienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame movimientoCliente = new MovimientoCliente();
                movimientoCliente.setVisible(true);
            }
        });
        aprobarPedidosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame cambiarEstadoPedido = new CambiarEstadoPedido(true);
                cambiarEstadoPedido.setVisible(true);
            }
        });
        rechazarPedidoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame cambiarEstadoPedido = new CambiarEstadoPedido(false);
                cambiarEstadoPedido.setVisible(true);
            }
        });

    }
}
