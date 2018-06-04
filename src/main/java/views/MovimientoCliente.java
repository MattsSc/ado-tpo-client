package views;

import delegates.ClienteDelegate;
import dtos.ClienteDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

public class MovimientoCliente extends JFrame{
    private JTextField dniTextField;
    private JButton verMovimientosButton;
    private JButton cerrarButton;
    private JTable tableMov;
    private JLabel montoDispLabel;
    private JLabel limiteCtaLabel;
    private JLabel limiteCtaValueLabel;
    private JLabel montoDispValueLabel;
    private JPanel contentPane;
    private JLabel nombreValueLabel;


    public MovimientoCliente(){
        setSize(500,500);
        setContentPane(contentPane);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        String col[] = {"Id", "Fecha","Importe($)","Tipo"};

        DefaultTableModel tableModel = new DefaultTableModel(col,0);
        tableMov.setModel(tableModel);
        verMovimientosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Integer dni = Integer.valueOf(dniTextField.getText());
                    ClienteDTO clienteDTO = ClienteDelegate.getInstance().obtenerCliente(dni);
                    limiteCtaValueLabel.setText(String.valueOf(clienteDTO.getLimiteCredito()));
                    montoDispValueLabel.setText(String.valueOf(clienteDTO.getMontoDisponible()));
                    nombreValueLabel.setText(clienteDTO.getNombre() + " " + clienteDTO.getApellido());

                    ClienteDelegate.getInstance().obtenerMovDeCliente(dni).stream().forEach(mov ->{
                        Object[] obj= {
                                mov.getIdMovimiento(),
                                mov.getFecha(),
                                mov.getImporte(),
                                mov.getTipo()
                        };
                        tableModel.addRow(obj);
                    });
                    if(tableModel.getRowCount() == 0){
                        JOptionPane.showMessageDialog(null, "El cliente no posee movimientos");
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
