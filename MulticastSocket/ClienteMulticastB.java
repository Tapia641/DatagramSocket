import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

public class ClienteMulticastB {
    public static void main(String[] args) {
        iniciarCliente();
    }

    public static void iniciarCliente() {

        /* CREAMOS EL GRUPO */
        InetAddress grupo = null;

        try {

            /* SOCKET MULTICAST CON PUERTO 9999 */
            MulticastSocket clienteSocket = new MulticastSocket(9999);
            System.out.println("Cliente escuchando puerto " + clienteSocket.getPort());
            clienteSocket.setReuseAddress(true);

            try {
                
                /* DIRECCION DEL GRUPO */
                grupo = InetAddress.getByName("228.1.1.1");

            } catch (UnknownHostException e) {
                System.out.println("Direccion erronea ");
            }

            /* GRUPO POR EL CUAL SE RECIBE MULTIPLES TRAMAS */
            clienteSocket.joinGroup(grupo);
            System.out.println("Unido al grupo :)");

            while (true) {
                DatagramPacket paquete = new DatagramPacket(new byte[10], 10);
                clienteSocket.receive(paquete);
                String mensaje = new String(paquete.getData());
                System.out.println("Datagrama recibido: " + mensaje);

                System.out.println("Servidor descubierto " + paquete.getAddress() + ":" + paquete.getPort());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}