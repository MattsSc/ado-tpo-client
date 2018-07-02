package views;

import delegates.PedidoDelegate;
import dtos.ItemAProcesarDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.List;

public class ListarPedido extends JFrame {
    private JLabel titleLabel;
    private JTable pedidosTable;
    private JPanel contentPane;
    private JButton cerrarButton;
    private JButton CambiarEstadoBtn;
    private JLabel tipoFacLabel;
    private JTextField facturaTextField;

    public ListarPedido(String estado, String sigEstado) {
        setSize(500,500);
        setContentPane(contentPane);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        if(!sigEstado.isEmpty()){
            CambiarEstadoBtn.setText("Cambiar a " + sigEstado);
        }else{
            tipoFacLabel.setVisible(false);
            facturaTextField.setVisible(false);
            CambiarEstadoBtn.setVisible(false);
        }

        String col[] = {"Id", "Cliente", "Estado", "Precio"};

        DefaultTableModel tableModel = new DefaultTableModel(col,0);
        pedidosTable.setModel(tableModel);

        try {
            PedidoDelegate.getInstance().listarPedidos(estado).forEach(pedido -> {
                String cliNombre = pedido.getCliente().getNombre() + " " + pedido.getCliente().getApellido();
                String cliDni= "(" + pedido.getCliente().getDni() + ")";
                Object[] obj= {
                        pedido.getId(),
                        cliNombre + " " + cliDni,
                        pedido.getEstado(),
                        pedido.getItems().stream().mapToDouble(item-> item.getCantidad()*item.getArticulo().getPrecio()).sum()
                };
                tableModel.addRow(obj);
            });
            if(tableModel.getRowCount() == 0){
                JOptionPane.showMessageDialog(null, "No hay pedidos en estado " + estado);
                setVisible(false);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        CambiarEstadoBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Integer pedidoId = Integer.valueOf(tableModel.getValueAt(pedidosTable.getSelectedColumn()-1, 0).toString());
                   if(sigEstado.equals("DESPACHO")) {
                       List<ItemAProcesarDTO> items = PedidoDelegate.getInstance().despacharPedido(
                               pedidoId,
                               facturaTextField.getText()
                       );

                       String desc = "";
                       for(ItemAProcesarDTO it : items){
                           desc = desc.concat("Descripcion: " + it.getArticuloDescripcion() + "\n Ubicaciones: " + it.getUbicaciones() + "\n Cantidad: " + it.getCantidad() );
                       }

                       JOptionPane.showMessageDialog(null, desc);
                       setVisible(false);
                   }
               } catch (RemoteException e1) {
                   e1.printStackTrace();
               }
            }
        });

        cerrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
    }

}
