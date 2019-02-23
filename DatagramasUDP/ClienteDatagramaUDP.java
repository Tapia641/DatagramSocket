import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.io.DataOutputStream;
import java.io.ByteArrayOutputStream;

public class ClienteDatagramaUDP {
    public static void main(String[] args) {
        iniciarCliente();
    }

    public static void iniciarCliente() {
        try {

            /* CREAMOS EL DATAGRAMSOCKET, EL PUERTO DESDE DONDE VOY A ENVIAR (NO IMPORTA) */
            DatagramSocket clienteSocket = new DatagramSocket();
            System.out.println("Cliente iniciado con exito ...");

            /* ENVIANDO AL LOCALHOST CON PUERTO 2000 (DONDE ESTA ESCUCHANDO) */
            String destino = "127.0.0.1";
            int port = 2000;

            /* ARREGLO ESTANDAR PARA ESCRIBIR MENSAJE */
            BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Ingresa un mensaje: ");
            String mensaje = teclado.readLine();

            ByteArrayOutputStream arregloSalida = new ByteArrayOutputStream();
            DataOutputStream dataSalida = new DataOutputStream(arregloSalida);

            /* ENVIAMOS DATOS EN EL MISMO ORDEN QUE SE RECIBEN */
            dataSalida.writeInt(mensaje.length());
            dataSalida.flush();

            byte[] c = arregloSalida.toByteArray();

            DatagramPacket newpacket = new DatagramPacket(c, c.length, InetAddress.getByName(destino), port);
            clienteSocket.send(newpacket);

            /* CREO EL PAQUETE, Y LO ENVIO CON EL SOCKET */
            String part = "";
            char[] caracteres = mensaje.toCharArray();

            for (int i = 0, j = 0; i < caracteres.length; i++, j++) {

                part += caracteres[i];

                if (j == 19) {
                    j = 0;
                    byte[] b = part.getBytes();
                    DatagramPacket packet = new DatagramPacket(b, b.length, InetAddress.getByName(destino), port);
                    clienteSocket.send(packet);
                    part = "";
                }
            }

            /* SI SOBRAN DATOS POR ENVIAR, ENTONCES ENVIAMOS EL RESIDUO */
            if (part.length() > 2) {
                byte[] b = part.getBytes();
                DatagramPacket packet = new DatagramPacket(b, b.length, InetAddress.getByName(destino), port);
                clienteSocket.send(packet);
            }

            /* CERRAMOS LOS FLUJOS EN EL MISMO ORDEN */
            dataSalida.close();
            clienteSocket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}