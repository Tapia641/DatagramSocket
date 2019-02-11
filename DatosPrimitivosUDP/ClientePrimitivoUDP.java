import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClientePrimitivoUDP {
    public static void main(String[] args) {
        iniciarCliente();
    }

    public static void iniciarCliente() {
        try {

            /* CONFIGURACION */
            int port = 2000;
            InetAddress destino =InetAddress.getByName("127.0.0.1");

            /* FLUJO DE SALIDA DEL LADO DEL CLIENTE */
            DatagramSocket clienteSocket = new DatagramSocket();
            ByteArrayOutputStream arregloSalida = new ByteArrayOutputStream();
            DataOutputStream dataSalida = new DataOutputStream(arregloSalida);

            /* ENVIAMOS DATOS EN EL MISMO ORDEN QUE SE RECIBEN */
            dataSalida.writeInt(4);
            dataSalida.flush();
            dataSalida.writeFloat(4.1f);
            dataSalida.flush();
            dataSalida.writeLong(72);
            dataSalida.flush();

            byte[] b = arregloSalida.toByteArray();

            DatagramPacket packet = new DatagramPacket(b,b.length,destino,port);
            clienteSocket.send(packet);
            clienteSocket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}