import java.net.*;
import java.io.*;
import java.util.*;

public class ServidorChat {

	static Socket cliente;
	static clienteHilo t[] = new clienteHilo[10];

	public static void main(String[] args) throws IOException {
		int i = 0;

		try {

			ServerSocket servidor = new ServerSocket(9000);
			System.out.println("¡Servidor Activo!!");
			ArrayList <String> listanick = new ArrayList <String> ();

			while (i < 10){

				cliente = servidor.accept();
				InetAddress localizacion = cliente.getInetAddress();
				String ip = localizacion.getHostName();
				System.out.println("Online : " + ip);
				listanick.add(ip);
				for (String z:listanick){
					System.out.println("Array : " + z);
				}
				clienteHilo ch =new clienteHilo(cliente, t);
				t[i] = ch;
				ch.start();
				i++;
			}
		}catch(Exception e){

			System.out.println("Error---vas a morir!");
		}
		
	}





}
