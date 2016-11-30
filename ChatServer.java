import java.net.*;
import java.io.*;

public class ChatServer{
	
	static Socket cliente;
	static clienteHilo t[] = new clienteHilo[10];

	public static void main(String[] args) throws IOException {
		int i = 0;

		try {

			ServerSocket servidor = new ServerSocket(9000);
			System.out.println("¡Servidor Activo!!");

			while (i < 10){

				cliente = servidor.accept();
				clienteHilo ch = new clienteHilo(cliente, t);
				t[i] = ch;
				ch.start();
				i++;
			}
		}catch(Exception e){

			System.out.println("Error---vas a morir!");
		}
		
	}





}
