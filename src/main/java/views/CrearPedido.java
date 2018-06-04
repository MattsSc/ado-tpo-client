package views;

import delegates.ArticuloDelegate;
import delegates.ClienteDelegate;
import delegates.PedidoDelegate;
import dtos.ArticuloDTO;
import dtos.ClienteDTO;
import dtos.ItemPedidoDTO;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class CrearPedido extends JFrame{
    private JTextField dniTextField;
    private JButton obtenerClienteButton;
    private JTextField dirTextField;
    private JTable pedidosTable;
    private JButton aceptarButton;
    private JButton cerrarButton;
    private JPanel contentPane;
    private JLabel nombreLabel;

    public CrearPedido(){
        setSize(500,500);
        setContentPane(contentPane);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        List<ArticuloDTO> articuloDTOList = null;

        String col[] = {"Id", "Descripcion", "Cantidad"};

        DefaultTableModel tableModel = new DefaultTableModel(col,0);
        pedidosTable.setModel(tableModel);

        try {
            articuloDTOList = ArticuloDelegate.getInstance().obtenerArticulos();
            articuloDTOList.stream().forEach(articuloDTO -> {
                Object[] obj= {
                        articuloDTO.getCodigo(),
                        articuloDTO.getDescripcion(),
                        0
                };
                tableModel.addRow(obj);
            });
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        obtenerClienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ClienteDTO clienteDTO = ClienteDelegate.getInstance().obtenerCliente(Integer.valueOf(dniTextField.getText()));
                    nombreLabel.setText(nombreLabel.getText() + " " + clienteDTO.getNombre() + " " + clienteDTO.getApellido());
                } catch (RemoteException e1) {
                    JOptionPane.showMessageDialog(null, "El cliente no existe!.");
                }
            }
        });

        aceptarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    List<ItemPedidoDTO> itemPedidoDTOS = new ArrayList<>();
                    for(int i=0; i < tableModel.getRowCount(); i++){
                        Integer cantidad = Integer.valueOf(tableModel.getValueAt(i,2).toString());
                        if(cantidad > 0){
                                Integer idArticulo = Integer.valueOf(tableModel.getValueAt(i,0).toString());
                                ItemPedidoDTO itemPedidoDTO = new ItemPedidoDTO(
                                        cantidad,
                                        ArticuloDelegate.getInstance().obtenerArticulo(idArticulo)
                                );
                                itemPedidoDTOS.add(itemPedidoDTO);
                        }
                    }
                    if(!itemPedidoDTOS.isEmpty() && !dniTextField.getText().isEmpty()){
                        Integer idPedido = PedidoDelegate.getInstance().crearPedido(
                                ClienteDelegate.getInstance().obtenerCliente(Integer.valueOf(dniTextField.getText())),
                                dirTextField.getText(),
                                itemPedidoDTOS
                        );
                        JOptionPane.showMessageDialog(null, "Pedido creado, Numero de pedido: " + idPedido);
                    }else{

                        JOptionPane.showMessageDialog(null, "Falta completar los tados o ningun articulo ha sido seleccionado");
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
