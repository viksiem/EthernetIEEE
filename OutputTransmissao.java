package outin;

import java.util.Random;
import java.util.Scanner;

import controle.Enlace;
import estrutura.PlacaRede;

public class OutputTransmissao extends Thread {
	/*
	 * Classe responsável por fazer a entrada dos dados do frame, o nº de frames
	 * e iniciar as threads responsáveis pelos quadros e interação com o meio .
	 * Padrão de referência: IEEE 802.3;
	 * 
	 * Para finalizar o uso, insira "-1";
	 */

	public static void main(String[] args) {

		Enlace enl;
		PlacaRede pr;
		Scanner in;
		int dados;
		final String macO = "4C:34:88:38:AB:AD";
		int maq = 0;
		Random r;

		enl = new Enlace();
		in = new Scanner(System.in);

		System.out.println("Gerar envios aleatórios?");
		String k = in.next();
		if (k.equalsIgnoreCase("sim")) {
			System.out.println("gerando envios.....");
			r = new Random();
			while (true) {
				dados = r.nextInt(2001);
				System.out.println("dados: "+dados);
				maq = r.nextInt(6);
				System.out.println("maq: "+maq);
				pr = new PlacaRede(enl, macO, maq,dados);
				pr.start();
				try {
					pr.sleep(1000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					pr.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		else {

			do {

				System.out.println("\nInsira o tamanho da sequência de dados (bytes): ");
				dados = in.nextInt();

				if (dados > 2000) {
					System.out.println("Tente um valor inferior ou igual a 2000 bytes");
					dados = in.nextInt();
				}
				if (dados <= 2000) {
					System.out.println("Para qual máquina? (1-4) ");
					maq = in.nextInt();

						pr = new PlacaRede(enl, macO, maq, dados);
						pr.start();
						try {
							pr.join();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					
				}
			} while ((dados != -1) || (maq != -1));
			in.close();
			System.out.println("Sessão finalizada.");
		}
	}
}