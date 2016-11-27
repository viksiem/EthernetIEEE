package outin;

import java.util.Random;
import java.util.zip.CRC32;

import controle.CRC;
import estrutura.Frame;

public class InputTransmissao {
	/*
	 * Classe responsável por receber os pacotes confere CRC
	 */

	String macD;
	String macO;
	int tam;
	int dados;
	int pad;
	long c;
	Frame fin;
	int maq;
	CRC check;
	long l;
	Random r;
	String pseudo = "0";
	String macsD[] = { "4C:34:89:48:FB:AE", "4C:34:88:39:BB:AE",
			"4C:34:88:4A:CB:AF", "4C:34:88:4B:DB:AF", "00:00:00:00:00:00" };

	public void Recebe( Frame f) {
		this.fin = f;
		this.macD = fin.dest;
		this.macO = fin.font;
		this.tam = fin.tamanho;
		this.dados = fin.dados;
		this.pad = fin.pad;
		this.c = fin.crc;
	//	System.out.println("DADOS FRAME SO RECEPTOR"+macD+ ""+macO+" "+tam+""+ dados);
	
		for (int i =0;i<=3;i++){
			if(macD == macsD[i]){
				System.out.println("\t>> Máquina " + (i+1) + " recebeu o frame");
			}
		}

		// informações do frame
		System.out.println("\t\t-> MAC origem: " + macO
				+ "\n\t\t-> Mac destino: " + macD);
		int tf = (6 + 6 + tam + pad + dados + 4);
		System.out.println("\t\t-> Tamanho do quadro: " + tf);

		// gera dados aleatórios
		r = new Random();
		System.out.print("\t\t-> Dados: ");
		for (int i = 0; i <= 40; i++) {
			pseudo = pseudo + r.nextInt(2);
		}
		System.out.println("..." + pseudo + "...");

		// Confere CRC
		check = new CRC();		
		if(check!=null){
		l = check.crc(macD , macO , tam , dados);
//		System.out.println("DADOS FRAME SO RECEPTOR"+macD+ ""+macO+" "+tam+""+ dados);
		
		if (c == l) {
			System.out.println("\t\t-> Frame sem falha, CRC origem: " + c
					+ ", CRC destino: " + l);
		}else{
			System.out.println("XXXX Frame corrompido XXXX");
		}
		}else {
			System.out.println("Erro ao iniciar o check()");
		}
		

	}

}