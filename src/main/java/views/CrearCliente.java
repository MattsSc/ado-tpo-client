package views;

import delegates.ClienteDelegate;
import dtos.ClienteDTO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

public class CrearCliente extends JFrame {
    private JTextField nameTextField;
    private JLabel nombreLabel;
    private JLabel apellidoLabel;
    private JLabel dniLabel;
    private JLabel domicilioLabel;
    private JLabel cuitLabel;
    private JLabel razonSocLabel;
    private JTextField apellidoTextField;
    private JTextField dniTextField;
    private JTextField domicilioTextField;
    private JTextField cuitTextField;
    private JTextField razSocTextField;
    private JButton aceptarBtn;
    private JButton cerrarButton;
    private JPanel contentPane;
    private JTextField limiteCredTextField;
    private JLabel limiteCreditoLabel;

    public CrearCliente() {
        setSize(500,500);
        setContentPane(contentPane);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        aceptarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ClienteDTO clienteDTO = new ClienteDTO(
                            Integer.valueOf(dniTextField.getText()),
                            nameTextField.getText(),
                            apellidoTextField.getText(),
                            domicilioTextField.getText(),
                            cuitTextField.getText(),
                            razonSocLabel.getText(),
                            Float.parseFloat(limiteCredTextField.getText()),
                            Float.parseFloat(limiteCredTextField.getText())
                    );
                    ClienteDelegate.getInstance().crearCliente(clienteDTO);
                    setVisible(false);
                } catch (RemoteException e1) {
                    System.out.println("ERRORRRRRRR");
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
