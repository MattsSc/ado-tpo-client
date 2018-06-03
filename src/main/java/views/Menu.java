package views;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import delegates.ClienteDelegate;
import delegates.PedidoDelegate;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.awt.Component;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class Menu extends JFrame {

    private JPanel contentPane;
    private JTextField txtIngresarIdPedido;
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
        btnListarClientes.setBounds(241, 11, 155, 23);
        contentPane.add(btnListarClientes);

        JButton btnCrearCliente = new JButton("Crear Cliente");
        btnCrearCliente.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		 CrearCliente cc = new CrearCliente();
	             cc.setVisible(true);
        	}
        });
        btnCrearCliente.setBounds(10, 11, 203, 23);
        contentPane.add(btnCrearCliente);

        JButton btnModificarCliente = new JButton("Modificar Cliente (En Construccion)");
        btnModificarCliente.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        	}
        });
        btnModificarCliente.setBounds(10, 40, 203, 23);
        contentPane.add(btnModificarCliente);

        JButton btnEliminarCliente = new JButton("Eliminar Cliente (En Construccion)");
        btnEliminarCliente.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnEliminarCliente.setBounds(10, 68, 203, 23);
        contentPane.add(btnEliminarCliente);
        
        JButton btnCrearPedido = new JButton("Crear Pedido");
        btnCrearPedido.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		 CrearPedido cp = new CrearPedido();
        		 cp.setVisible(true);
        	}
        });
        btnCrearPedido.setBounds(10, 118, 203, 23);
        contentPane.add(btnCrearPedido);
        
        JButton btnAprobarpedido = new JButton("AprobarPedido");
        btnAprobarpedido.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
					PedidoDelegate.getInstance().aprobarPedido((Integer.parseInt(txtIngresarIdPedido.getText())));
					Component frame1 = null;
					JOptionPane.showMessageDialog(frame1,"El pedido se ha aprobado con exito.","Pedido",JOptionPane.PLAIN_MESSAGE);
				} catch (NumberFormatException | RemoteException e1) {
					Component frame = null;
					JOptionPane.showMessageDialog(frame,"No se pudo aprobar el pedido.","Error", JOptionPane.ERROR_MESSAGE);
				}
        	}
        });
        btnAprobarpedido.setBounds(237, 179, 187, 23);
        contentPane.add(btnAprobarpedido);
        
        txtIngresarIdPedido = new JTextField();
        txtIngresarIdPedido.setText("ingresar ID Pedido");
        txtIngresarIdPedido.setBounds(10, 180, 203, 20);
        contentPane.add(txtIngresarIdPedido);
        txtIngresarIdPedido.setColumns(10);
        
        JButton btnVerEstadoDe = new JButton("Ver Estado de Cuenta");
        btnVerEstadoDe.setBounds(241, 45, 165, 23);
        contentPane.add(btnVerEstadoDe);
        
        JButton btnListarPedidos = new JButton("Listar Pedidos");
        btnListarPedidos.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnListarPedidos.setBounds(237, 118, 187, 23);
        contentPane.add(btnListarPedidos);
    }
}

