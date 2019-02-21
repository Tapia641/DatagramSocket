import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

public class ClienteMulticastB_Escucha {
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

                /* ----------------COMENZAMOS A RECIBIR EL MENSAJE------------------ */
                /* OBTENEMOS EL MENSAJE DEL SERVIDOR */
                DatagramPacket paqueteServidor = new DatagramPacket(new byte[100], 100);
                clienteSocket.receive(paqueteServidor);

                /* LO TRANSFORMAMOS A UNA CADENA */
                String mensaje = new String(paqueteServidor.getData());
                System.out.println("Datagrama recibido: " + mensaje);

                /* DATOS DEL PAQUETE RECIBIDO */
                System.out.println(
                        "Servidor descubierto " + paqueteServidor.getAddress() + ":" + paqueteServidor.getPort());

                try {
                    Thread.sleep(3000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}