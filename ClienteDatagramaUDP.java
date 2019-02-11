import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClienteDatagramaUDP {
    public static void main(String[] args) {
        iniciarCliente();
    }

    public static void iniciarCliente() {
        try {
            DatagramSocket clienteSocket = new DatagramSocket();
            System.out.println("Cliente iniciado con exito ...");
            BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Ingresa un mensaje");
            String mensaje = teclado.readLine();
            byte[] b = mensaje.getBytes();
            String destino = "127.0.0.1";
            int port = 2000;

            DatagramPacket packet = new DatagramPacket(b,b.length, InetAddress.getByName(destino), port);
            clienteSocket.send(packet);
            clienteSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}