package estrutura;

import estrutura.Frame;
import controle.CRC;

import java.util.zip.CRC32;

import controle.Enlace;

public class PlacaRede extends Thread {
	// Classe responsável pela estruturação dos frames utilizados

	CRC check = new CRC();
	Frame f;
	byte r[];
	int tF;
	Enlace enlace;
	String macsD[] = { "4C:34:89:48:FB:AE", "4C:34:88:39:BB:AE",
			"4C:34:88:4A:CB:AF", "4C:34:88:4B:DB:AF", "00:00:00:00:00:00" };

	// Definição de todos os componentes do Frame em nº de bytes
	final int preamb = 7;
	final int sd = 1;
	final int len = 2;
	final int crc = 4;
	final int tfont = 6;
	final int tdest = 6;
	String font;
	String dest;
	int maqq;
	int info;
	int pad=0;
	long l;

	public PlacaRede(Enlace enl, String macO, int maq, int dados) {
		super();
		this.enlace = enl;
		this.font = macO;
		this.maqq = maq;
		this.info = dados;

		if (info < 46) {
			pad = 46 - info;
			System.out.println("data: "+info);
			System.out.println("paddle: " + pad);
		}
		tF = tfont + tdest + len + info + pad + crc;
		System.out.println("Tamanho do frame: " + tF);

		if ((1 <=maqq ) && (maqq <= 4)) {
			System.out.println("maq: "+maqq+" mac = "+macsD[maqq-1]);
			dest = macsD[maqq - 1];
		} else {
			dest = macsD[4];
			System.out.println("Recebeu mac 00000000, mac: "+macsD[4]);
		}

		// gera CRC dos endereços, tam e dados
		long l = check.crc(font,dest,len,info);

		f = new Frame(preamb, sd, dest, font, len, info, pad, l);

		enlace.AddNoMeio(f);
	}

}