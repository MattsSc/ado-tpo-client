package views;

import delegates.ArticuloDelegate;
import delegates.CompraDelegate;
import dtos.ArticuloDTO;
import dtos.OrdenDeCompraDTO;
import dtos.ProveedorDTO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.List;

public class CrearOC extends JFrame{
    private JPanel contentPane;
    private JLabel proveedorLabel;
    private JComboBox proveedorComboBox;
    private JComboBox artComboBox;
    private JButton aceptarButton;
    private JButton cerrarButton;
    private JLabel cantRepoLabel;
    private JLabel valueRepoLabel;

    public CrearOC(){
        setSize(500,500);
        setContentPane(contentPane);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        try {
            List<ArticuloDTO> articuloDTOList = ArticuloDelegate.getInstance().obtenerArticulosFaltantes();

            articuloDTOList.forEach(art->{
                artComboBox.addItem(art);
            });


        } catch (RemoteException e) {
            e.printStackTrace();
        }

        aceptarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ArticuloDTO articuloDTO = (ArticuloDTO)artComboBox.getSelectedItem();
                    ProveedorDTO proveedorDTO = (ProveedorDTO)proveedorComboBox.getSelectedItem();
                    OrdenDeCompraDTO ordenDeCompraDTO = new OrdenDeCompraDTO(articuloDTO,articuloDTO.getCantReposicion(),false,proveedorDTO);

                    Integer id = CompraDelegate.getInstance().crearOrdenDeCompra(ordenDeCompraDTO);
                    JOptionPane.showMessageDialog(null, "Orden de pedido creada. Id: " + id);
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Hubo un error, intente mas tarde!.");
                }
            }
        });
        cerrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        artComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    proveedorComboBox.removeAllItems();
                    valueRepoLabel.setText("");

                    ArticuloDTO articuloDTO = (ArticuloDTO)artComboBox.getSelectedItem();
                    List<ProveedorDTO> proveedorDTOS = CompraDelegate.getInstance().obtenerUltimos3Proveedores(articuloDTO.getCodigo());

                    valueRepoLabel.setText(articuloDTO.getCantReposicion().toString());

                    proveedorDTOS.forEach(proveedorDTO -> proveedorComboBox.addItem(proveedorDTO));
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }
}
