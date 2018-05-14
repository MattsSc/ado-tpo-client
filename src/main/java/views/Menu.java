package views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.awt.event.ActionEvent;

public class Menu extends JFrame {

    private JPanel contentPane;
    /**
     * Create the frame.
     */
    public Menu() {
        setTitle("Menu Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton btnListarClientes = new JButton("Listar Clientes");
        btnListarClientes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ListarClientes lc;
                lc = new ListarClientes();
                lc.setVisible(true);
            }
        });
        btnListarClientes.setBounds(143, 31, 99, 41);
        contentPane.add(btnListarClientes);

        JButton btnCrearCliente = new JButton("Crear Cliente");
        btnCrearCliente.setBounds(10, 11, 123, 23);
        contentPane.add(btnCrearCliente);

        JButton btnModificarCliente = new JButton("Modificar Cliente");
        btnModificarCliente.setBounds(10, 40, 123, 23);
        contentPane.add(btnModificarCliente);

        JButton btnEliminarCliente = new JButton("Eliminar Cliente");
        btnEliminarCliente.setBounds(10, 68, 123, 23);
        contentPane.add(btnEliminarCliente);
    }
}

