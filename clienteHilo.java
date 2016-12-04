import java.net.*;
import java.io.*;
import java.util.*;

public class clienteHilo extends Thread{
	
	private Socket cliente;
	private clienteHilo hilos[];
	private int totalClientes;
	private DataInputStream entrada, entradamute;
	private PrintStream salida;
	private String nickname, nicknamemute;

	public clienteHilo(Socket cliente, clienteHilo t[]){
		this.cliente = cliente;
		this.hilos = t;
		totalClientes = hilos.length;
	}

	void muteUsuario(){
		try {
			int j;
			int os = 0;
			for(j = 0 ; j < totalClientes; j++){
			if(hilos[j] == this) os = j;
			}

				hilos[os].salida.println("¿A quien quieres silenciar?");	
				entradamute = new DataInputStream(cliente.getInputStream());
				nicknamemute = entradamute.readLine();
				entradamute.close();

			
		}catch(Exception e){
			System.out.println("Mute no sirve ");
		}
	}

	void muestraLista(){
		try {
			int i;
			int pos = 0;
			for(i = 0 ; i < totalClientes; i++){
			if(hilos[i] == this) pos = i;
			}

			salida.println("\t Los usuarios conectados son :");
			for(i = 0; i< totalClientes; i++){
				
				hilos[pos].salida.println("\n\t -" + hilos[i].nickname );
			}
		}catch(Exception e){
			System.out.println("Algo salio mal ");
		}
	}
	public void run(){
		int i; 
		String mensaje;

		try{
			entrada = new DataInputStream(cliente.getInputStream());
			salida = new PrintStream(cliente.getOutputStream());

			salida.println("\t ... Bienvenido al Chat  !! ... ");
			salida.println("\t *¿Cual sera su nickname? >> ");
			nickname = entrada.readLine();
			salida.println("\t Hola " + nickname + " :D ! \n");

			for(i = 0 ; i < totalClientes; i++){
				if(hilos[i] != null && hilos[i] != this){
					hilos[i].salida.println("\r\r\t ***---------------- Acaba de conectarse : " + nickname + "------------------");

				}
			}

			while(true){
				mensaje = entrada.readLine();
				if(mensaje.compareTo("/salir")==0){
					break;
				}
				if(mensaje.contains("pinche" )){
					System.out.println("Cadena grosera");
					mensaje = mensaje.replaceAll("pinche", "p****");
					System.out.println(mensaje);
					
				}
				if(mensaje.contains("puto" )){
					System.out.println("Cadena grosera");
					mensaje = mensaje.replaceAll("puto", "p****");
					System.out.println(mensaje);
					
				}
				if(mensaje.contains("puta" )){
					System.out.println("Cadena grosera");
					mensaje = mensaje.replaceAll("puta", "p****");
					System.out.println(mensaje);
					
				}
				if(mensaje.contains("culero" )){
					System.out.println("Cadena grosera");
					mensaje = mensaje.replaceAll("culero", "c****");
					System.out.println(mensaje);
					
				}
				if(mensaje.contains("culera" )){
					System.out.println("Cadena grosera");
					mensaje = mensaje.replaceAll("culera", "c****");
					System.out.println(mensaje);
					
				}
				if(mensaje.contains("chingada" )){
					System.out.println("Cadena grosera");
					mensaje = mensaje.replaceAll("chingada", "c****");
					System.out.println(mensaje);
				}
				if(mensaje.contains("zorr" )){
					System.out.println("Cadena grosera");
					mensaje = mensaje.replaceAll("zorr", "z****");
					System.out.println(mensaje);
					
				}
				if(mensaje.contains("mamada" )){
					System.out.println("Cadena grosera");
					mensaje = mensaje.replaceAll("mamada", "m****");
					System.out.println(mensaje);
					
				}
				if(mensaje.contains("mierda" )){
					System.out.println("Cadena grosera");
					mensaje = mensaje.replaceAll("mierda", "m****");
					System.out.println(mensaje);
					
				}
				if(mensaje.startsWith("/lista")){
					muestraLista();
					mensaje = mensaje.replaceAll("/lista", "****");
					
				}
				if(mensaje.startsWith("/mute" )){
					muteUsuario();
					mensaje = mensaje.replaceAll("/mute", "****");
				}
				for(i = 0 ; i < totalClientes; i++){
					if(hilos[i] != null && hilos[i] != this){
						hilos[i].salida.println("\r\t" + nickname + " dice>> " + mensaje + "\n");
						hilos[i].salida.print("\r" + " \t Tu >> ");
						hilos[i].salida.print("  ");
					}
				}
			}

			for(i = 0 ; i < totalClientes; i++){
				if(hilos[i] != null && hilos[i] != this){
					hilos[i].salida.println("\n ***-------------- Oye! " + nickname + " Se fue :c  -------------");
				}
			}

			salida.println("\n\t ----------------- Que te vaya bien " + nickname + " Adios ------------------");

			for(i = 0 ; i < totalClientes; i++){
				if(hilos[i] == this){
					hilos[i] = null;
				}
			}

			entrada.close();
			salida.close();
			cliente.close();

		}catch(Exception e){
			System.out.println("No funciona ");
		}

	}


}
