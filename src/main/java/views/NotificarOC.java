package views;

import delegates.CompraDelegate;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class NotificarOC extends  JFrame{
    private JTextField idCompraTextField;
    private JButton cerrarButton;
    private JButton aceptarButton;
    private JLabel idCompraLabel;
    private JPanel contentPane;
    private JTextField vencDateField;
    private JLabel dateVencLabel;

    public NotificarOC(){
        setSize(500,500);
        setContentPane(contentPane);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        aceptarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    DateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");

                    CompraDelegate.getInstance().cerrarOrdenDeCompra(
                            Integer.valueOf(idCompraTextField.getText()),
                            sourceFormat.parse(vencDateField.getText())
                    );
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
