import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ServidorPrimitivoUDP {
    public static void main(String[] args) {
        iniciarServidor();
    }

    public static void iniciarServidor() {
        try {

            /* INICIAMOS EL SERVIDOR CON PUERTO PARA ESCUCHAR 2000 */
            DatagramSocket socketCliente = new DatagramSocket(2000);
            System.out.println("Servidor iniciado ...");

            while (true) {

                /* SOPORTA HASTA UN ARREGLO DE 2000 BYTES */
                DatagramPacket packet = new DatagramPacket(new byte[2000], 2000);

                /* BLOQUEO */
                socketCliente.receive(packet);

                /* DATOS DEL PAQUETE */
                System.out.println("Datagrama recibido desde " + packet.getAddress() + " : " + packet.getPort());

                /* CREANDO UN FLUJO SIN LIGAR AL SOCKET, SOLOA  LOS DATOS RECIBIDOS */
                DataInputStream destino = new DataInputStream(new ByteArrayInputStream(packet.getData()));

                /* PREPARAMOS LOS DATOS A LEER DATOS PRIMITIVOS */
                int x = destino.readInt();
                float f = destino.readFloat();
                long l = destino.readLong();
                System.out.println("Entero: " + x + "\nFlotante: " + f + "\nLong: " + l);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}