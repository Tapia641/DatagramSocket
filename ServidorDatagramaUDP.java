import java.net.DatagramPacket;
import java.net.DatagramSocket;

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

                /* LO CONVERTIMOS A UN STRING */
                String mensaje = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Con el mensaje: " + mensaje);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}