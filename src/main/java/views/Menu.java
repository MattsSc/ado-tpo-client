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
                JFrame crearCliente = new ListarPedido("RECIBIDO", "");
                crearCliente.setVisible(true);
            }
        });
        listarPedidosDespachablesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame crearCliente = new ListarPedido("DESPACHABLE", "DESPACHO");
                crearCliente.setVisible(true);
            }
        });
        listarPedidosPendientesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame crearCliente = new ListarPedido("FALTA_STOCK","");
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
        pedidoDespachadosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame crearCliente = new ListarPedido("DESPACHO", "");
                crearCliente.setVisible(true);
            }
        });
        completarPedidoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame completarPedido = new CompletarPedido();
                completarPedido.setVisible(true);
            }
        });
        pedidosCompletadosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame crearCliente = new ListarPedido("COMPLETO", "");
                crearCliente.setVisible(true);
            }
        });
        crearOrdenDeCompraButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame crearOC = new CrearOC();
                crearOC.setVisible(true);
            }
        });
        notificarOCButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame notificarOC = new NotificarOC();
                notificarOC.setVisible(true);
            }
        });

    }
}
