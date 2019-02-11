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

            /* CREAMOS EL DATAGRAMSOCKET, PUERTO DESDE DONDE VOY A ENVIAR (NO IMPORTA) */
            DatagramSocket clienteSocket = new DatagramSocket();
            System.out.println("Cliente iniciado con exito ...");

            /* ARREGLO ESTANDAR PARA ESCRIBIR MENSAJE */
            BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Ingresa un mensaje");
            String mensaje = teclado.readLine();
            byte[] b = mensaje.getBytes();

            /* ENVIANDO AL LOCALHOST CON PUERTO 2000 (DONDE ESTA ESCUCHANDO) */
            String destino = "127.0.0.1";
            int port = 2000;

            /* CREO EL PAQUETE, Y LO ENVIO CON EL SOCKET */
            DatagramPacket packet = new DatagramPacket(b, b.length, InetAddress.getByName(destino), port);
            clienteSocket.send(packet);
            clienteSocket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}