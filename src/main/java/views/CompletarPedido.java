package views;

import delegates.PedidoDelegate;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CompletarPedido extends JFrame{
    private JTextField idPedidoTextField;
    private JPanel contentPane;
    private JTextField dateTextField;
    private JButton aceptarButton;
    private JButton cerrarButton;

    public CompletarPedido(){
        setSize(500,500);
        setContentPane(contentPane);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        aceptarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                DateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");

                try {
                    PedidoDelegate.getInstance().completarPedido(
                            Integer.valueOf(idPedidoTextField.getText()),
                            sourceFormat.parse(dateTextField.getText())
                    );
                    JOptionPane.showMessageDialog(null, "Pedido Completo!");
                    setVisible(false);
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                } catch (ParseException e1) {
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
