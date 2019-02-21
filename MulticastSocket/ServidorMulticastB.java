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

            /* UNIENDOSE AL GRUPO */
            String mensaje = "Hola chicos, soy el servidor. ^^'";
            byte[] b = mensaje.getBytes();
            grupo = InetAddress.getByName("228.1.1.1");
            servidorSocket.joinGroup(grupo);

            while (true) {

                /* ----------------COMENZAMOS A ENVIAR MENSAJE---------------- */
                /* SERVIDOR CON UN PUERTO DIFERENTE */
                DatagramPacket paqueteMensaje = new DatagramPacket(b, b.length, grupo, 9999);

                /* MANDANDO SALUDO */
                servidorSocket.send(paqueteMensaje);
                System.out.println("Mensaje: " + mensaje + ", con TTL: " + servidorSocket.getTimeToLive());

                try {
                    Thread.sleep(3000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                /* ----------------COMENZAMOS A RECIBIR LA RESPUESTA---------- */
                /*
                 * /* OBTENIENDO LAS RESPUESTAS DatagramPacket paqueteRespuesta = new
                 * DatagramPacket(new byte[10], 10); servidorSocket.receive(paqueteRespuesta);
                 * 
                 * /* LO TRANSFORMAMOS A UNA CADENA String respuesta = new
                 * String(paqueteRespuesta.getData()); System.out.println("Datagrama recibido: "
                 * + respuesta);
                 * 
                 * /* DATOS DEL PAQUETE RECIBIDO System.out.println( "Servidor descubierto " +
                 * paqueteRespuesta.getAddress() + ":" + paqueteRespuesta.getPort());
                 */
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}