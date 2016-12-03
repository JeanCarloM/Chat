import java.net.*;
import java.io.*;
import java.util.*;

public class clienteHilo extends Thread{
	
	private Socket cliente;
	private clienteHilo hilos[];
	private int totalClientes;
	private DataInputStream entrada;
	private PrintStream salida;
	private String nickname;

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
			for(j = 0; j < totalClientes; j++){
				
				hilos[os].salida.close();
			}
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

			salida.println("Los usuarios conectados son :");
			for(i = 0; i< totalClientes; i++){
				
				hilos[pos].salida.println("-" + hilos[i].nickname + "\r");
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

			salida.println("... Bienvenido al Chat  !! ... ");
			salida.println(" *¿Cual sera su nickname? >> ");
			nickname = entrada.readLine();
			salida.println("Hola " + nickname + " :D !");

			for(i = 0 ; i < totalClientes; i++){
				if(hilos[i] != null && hilos[i] != this){
					hilos[i].salida.println("Acaba de conectarse : " + nickname);

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
				if(mensaje.contains("zorra" )){
					System.out.println("Cadena grosera");
					mensaje = mensaje.replaceAll("zorra", "z****");
					System.out.println(mensaje);
					
				}
				if(mensaje.contains("mamada" )){
					System.out.println("Cadena grosera");
					mensaje = mensaje.replaceAll("mamada", "m****");
					System.out.println(mensaje);
					
				}
				if(mensaje.startsWith("/lista")){
					muestraLista();
					
				}
				if(mensaje.startsWith("/Mute" )){
					muteUsuario();
				}
				for(i = 0 ; i < totalClientes; i++){
					if(hilos[i] != null && hilos[i] != this){
						hilos[i].salida.println(nickname + " dice>> " + mensaje);
						hilos[i].salida.print("\r Tu >> ");
					}
				}
			}

			for(i = 0 ; i < totalClientes; i++){
				if(hilos[i] != null && hilos[i] != this){
					hilos[i].salida.println(nickname + " Se fue :c");
				}
			}

			salida.println("Adios " + nickname);

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
