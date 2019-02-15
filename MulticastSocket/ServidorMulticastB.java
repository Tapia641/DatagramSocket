import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

public class ServidorMulticastB {
    public static void main(String[] args) {
        iniciarCliente();
    }

    public static void iniciarCliente() {

        /* CREAMOS EL GRUPO */
        InetAddress grupo = null;

        try {

            /* SOCKET MULTICAST CON PUERTO 9999 */
            MulticastSocket servidorSocket = new MulticastSocket(9976);
            servidorSocket.setReuseAddress(true);
            servidorSocket.setTimeToLive(1);

            String mensaje = "Hola :)";
            byte[] b = mensaje.getBytes();
            grupo = InetAddress.getByName("228.1.1.1");

            servidorSocket.joinGroup(grupo);

            while (true) {

                /* SERVIDOR CON UN PUERTO DIFERENTE */
                DatagramPacket paquete = new DatagramPacket(b, b.length, grupo, 9999);
                servidorSocket.send(paquete);
                System.out.println("Mensaje: " + mensaje + ", con TTL: " + servidorSocket.getTimeToLive());

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