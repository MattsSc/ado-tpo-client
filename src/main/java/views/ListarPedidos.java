package views;

import delegates.ClienteDelegate;
import delegates.PedidoDelegate;
import dtos.ClienteDTO;
import dtos.PedidoDTO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListarPedidos  extends JFrame {

    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ListarPedidos frame = new ListarPedidos();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ListarPedidos() {
        setTitle("Listar Pedidos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JTextPane textPane = new JTextPane();
        textPane.setBounds(10, 11, 414, 239);

        String salida = null;
        try {
        	List<PedidoDTO> pedidos = PedidoDelegate.getInstance().listarPedidos();
        	salida = pedidos.stream().map(pedido -> "Pedido: " + " " + pedido.getId() + " Estado: " + pedido.getEstado() +"\n").collect(Collectors.joining());
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        textPane.setText(salida);
        contentPane.add(textPane);
    }
}
