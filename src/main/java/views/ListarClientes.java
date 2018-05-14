package views;

import delegates.ClienteDelegate;
import dtos.ClienteDTO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListarClientes  extends JFrame {

    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ListarClientes frame = new ListarClientes();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ListarClientes() {
        setTitle("Listar Clientes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JTextPane textPane = new JTextPane();
        textPane.setBounds(10, 11, 414, 239);

        String salida = null;
        try {
            List<ClienteDTO> clientes = ClienteDelegate.getInstance().listarClientes();
            salida = clientes.stream().map(cliente -> "Cliente: " +  cliente.getNombre() +" " + cliente.getApellido() + " DNI: " + cliente.getDni() +"\n").collect(Collectors.joining());
            salida = salida + clientes.stream().map(cliente -> {
                String movimientos = "*****MOVIMIENTOS " + cliente.getNombre() + " " + cliente.getApellido() + "*****\n";
                System.out.println(cliente.getMovimientosCC().size());
                if(!cliente.getMovimientosCC().isEmpty()){
                    movimientos = movimientos + cliente.getMovimientosCC().stream().map(mov -> "tipo: " + mov.getTipo() + " $" + mov.getImporte() +" Fecha: " +  mov.getFecha() + "\n"  ).collect(Collectors.joining());
                }
                return  movimientos;
            }).collect(Collectors.joining());
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        textPane.setText(salida);
        contentPane.add(textPane);
    }
}
