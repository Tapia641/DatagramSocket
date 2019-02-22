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
                
                /* ----------------COMENZAMOS A ENVIAR UNA RESPUESTA---------------- */
                /* SERVIDOR CON UN PUERTO DIFERENTE */

                String respuesta = "Hola chicos, soy el cliente 1";
                byte[] b = respuesta.getBytes();
                DatagramPacket paqueteRespuesta = new DatagramPacket(b, b.length, grupo, 9999);

                /* MANDANDO SALUDO */
                clienteSocket.send(paqueteRespuesta);
                System.out.println("Mensaje enviado: " + respuesta + ", con TTL: " + clienteSocket.getTimeToLive());

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}