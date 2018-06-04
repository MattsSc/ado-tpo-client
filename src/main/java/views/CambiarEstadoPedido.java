package views;

import delegates.PedidoDelegate;

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

        if(aprobar){
           motivoLabel.setVisible(false);
           motivoTextField.setVisible(false);
        }

        aceptarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(aprobar){
                        PedidoDelegate.getInstance().aprobarPedido(Integer.valueOf(pedidoTextField.getText()));
                    }else{
                        PedidoDelegate.getInstance().rechazarPedido(Integer.valueOf(pedidoTextField.getText()));
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
