package views;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import delegates.ClienteDelegate;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.awt.event.ActionEvent;
import javax.swing.JList;

public class CrearPedido extends JFrame {

	private JPanel contentPane;
	private JTextField textDNI;
	private JTextField textNombre;
	private JButton btnCrearCliente;
	private JButton btnIrAlMenu;
	private JTextField textArticulo;
	private JTextField textCantidad;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CrearPedido frame = new CrearPedido();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CrearPedido() {
		setTitle("Crear Pedido");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblDni = new JLabel("DNI Cliente");
		lblDni.setBounds(15, 37, 61, 14);
		
		JLabel lblNombre = new JLabel("Direccion Entrega");
		lblNombre.setBounds(15, 75, 95, 14);
		
		textDNI = new JTextField();
		textDNI.setBounds(114, 34, 86, 20);
		textDNI.setColumns(10);
		
		textNombre = new JTextField();
		textNombre.setBounds(114, 72, 86, 20);
		textNombre.setColumns(10);
		
		btnCrearCliente = new JButton("Crear Pedido");
		btnCrearCliente.setBounds(44, 202, 111, 42);
		btnCrearCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ClienteDelegate.getInstance().crearCliente(Integer.parseInt(textDNI.getText()), textNombre.getText(), textApellido.getText(), textDomicilio.getText(), textCuit.getText(), textRazon.getText(), Float.parseFloat(textLimite.getText()), Float.parseFloat(textMonto.getText()));
					Component frame1 = null;
					JOptionPane.showMessageDialog(frame1,"Usuario Creado con Exito.","Sistema",JOptionPane.PLAIN_MESSAGE);
				} catch (NumberFormatException | RemoteException e1) {
					Component frame = null;
					JOptionPane.showMessageDialog(frame,"No se pudo crear el usuario.","Error", JOptionPane.ERROR_MESSAGE);
				}
				Menu mn;
				mn = new Menu();
				mn.setVisible(true);
			}
		});
		
		btnIrAlMenu = new JButton("Ir al Menu");
		btnIrAlMenu.setBounds(283, 202, 95, 42);
		btnIrAlMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu mn;
				mn = new Menu();
				mn.setVisible(true);
			}
		});
		
		JLabel lblIdArticulo = new JLabel("ID Articulo");
		lblIdArticulo.setBounds(233, 37, 61, 14);
		
		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setBounds(233, 75, 61, 14);
		
		textArticulo = new JTextField();
		textArticulo.setBounds(298, 34, 86, 20);
		textArticulo.setColumns(10);
		
		textCantidad = new JTextField();
		textCantidad.setBounds(298, 72, 86, 20);
		textCantidad.setColumns(10);
		
		JButton btnAgregarItem = new JButton("Agregar Item");
		btnAgregarItem.setBounds(149, 131, 124, 44);
		btnAgregarItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		contentPane.setLayout(null);
		contentPane.add(lblIdArticulo);
		contentPane.add(lblCantidad);
		contentPane.add(textArticulo);
		contentPane.add(textCantidad);
		contentPane.add(btnAgregarItem);
		contentPane.add(lblDni);
		contentPane.add(lblNombre);
		contentPane.add(textNombre);
		contentPane.add(textDNI);
		contentPane.add(btnCrearCliente);
		contentPane.add(btnIrAlMenu);
	}
}
