package views;

import delegates.PedidoDelegate;
import dtos.PedidoDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

public class ListarPedido extends JFrame {
    private JLabel titleLabel;
    private JTable pedidosTable;
    private JPanel contentPane;
    private JButton cerrarButton;

    public ListarPedido(String estado) {
        setSize(500,500);
        setContentPane(contentPane);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        String col[] = {"Id", "Cliente", "Estado"};

        DefaultTableModel tableModel = new DefaultTableModel(col,0);
        pedidosTable.setModel(tableModel);

        try {
            PedidoDelegate.getInstance().listarPedidos(estado).forEach(pedido -> {
                String cliNombre = pedido.getCliente().getNombre() + " " + pedido.getCliente().getApellido();
                String cliDni= "(" + pedido.getCliente().getDni() + ")";
                Object[] obj= {
                        pedido.getId(),
                        cliNombre + " " + cliDni,
                        pedido.getEstado()
                };
                tableModel.addRow(obj);
            });

            if(tableModel.getRowCount() == 0){
                JOptionPane.showMessageDialog(null, "No hay pedidos en estado " + estado);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }


        cerrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
    }

}
