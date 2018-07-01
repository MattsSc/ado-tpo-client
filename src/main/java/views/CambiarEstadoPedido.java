package views;

import delegates.PedidoDelegate;
import dtos.PedidoDTO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

public class CambiarEstadoPedido extends JFrame{
    private JTextField pedidoTextField;
    private JButton aceptarButton;
    private JLabel pedidoLabel;
    private JButton cerrarButton;
    private JLabel motivoLabel;
    private JTextField motivoTextField;
    private JPanel contentPane;

    public CambiarEstadoPedido(Boolean aprobar){

        setSize(500,500);
        setContentPane(contentPane);
        setLocationRelativeTo(null);


        aceptarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(aprobar){
                        PedidoDTO aux = null;
                        aux = PedidoDelegate.getInstance().obtenerPedido(Integer.valueOf(pedidoTextField.getText()));
                        if (aux != null && aux.getEstado().equals("RECIBIDO")){
                            PedidoDelegate.getInstance().aprobarPedido(Integer.valueOf(pedidoTextField.getText()),motivoTextField.getText());
                        }else{
                            JOptionPane.showMessageDialog(null, "El pedido no esta dentro de los recibidos.");
                        }
                    }else{
                        PedidoDTO aux = null;
                        aux = PedidoDelegate.getInstance().obtenerPedido(Integer.valueOf(pedidoTextField.getText()));
                        if (aux != null && aux.getEstado().equals("RECIBIDO")){
                            PedidoDelegate.getInstance().rechazarPedido(Integer.valueOf(pedidoTextField.getText()),motivoTextField.getText());
                        }else{
                            JOptionPane.showMessageDialog(null, "El pedido no esta dentro de los recibidos.");
                        }
                    }
                    setVisible(false);
                } catch (RemoteException e1) {
                    JOptionPane.showMessageDialog(null, "El pedido no existe!.");
                    System.out.println(e1);
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
