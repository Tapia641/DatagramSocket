import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.io.DataInputStream;
import java.io.ByteArrayInputStream;

public class ServidorDatagramaUDP {
    public static void main(String[] args) {
        iniciarServidor();
    }

    public static void iniciarServidor() {

        try {

            /* INICIAMOS EL SERVIDOR CON PUERTO PARA ESCUCHAR 2000 */
            DatagramSocket servidor = new DatagramSocket(2000);
            System.out.println("Servidor iniciado...");

            while (true) {

                /* SOPORTA HASTA UN ARREGLO DE 2000 BYTES */
                DatagramPacket packet = new DatagramPacket(new byte[2000], 2000);

                /* BLOQUEO */
                servidor.receive(packet);

                /* DATOS DEL PAQUETE */
                System.out.println("Datagrama recibido desde " + packet.getAddress() + " : " + packet.getPort());

                /* CREANDO UN FLUJO SIN LIGAR AL SOCKET, SOLOA LOS DATOS RECIBIDOS */
                DataInputStream destino = new DataInputStream(new ByteArrayInputStream(packet.getData()));

                /* PREPARAMOS LOS DATOS A LEER DATOS PRIMITIVOS */
                int tam = destino.readInt();

                System.out.println("Tam: " + tam);

                String mensaje, part = "";

                while (part.length() != tam) {

                    /* BLOQUEO, RECIBIMOS EL PAQUETE */
                    servidor.receive(packet);

                    /* DATOS DEL PAQUETE */
                    //System.out.println("Datagrama recibido desde " + packet.getAddress() + " : " + packet.getPort());

                    /* LO CONVERTIMOS A UN STRING */
                    mensaje = new String(packet.getData(), 0, packet.getLength());
                    System.out.println("Mensaje: " + mensaje);

                    /* CONCATENAMOS EL PAQUETE RECIBIDO PARA RECONSTRUIR EL MENSAJE */
                    part += mensaje;
                    //System.out.println("Tam de part: " + part.length());

                }

                System.out.println("Mensaje reconstruido: " + part);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}