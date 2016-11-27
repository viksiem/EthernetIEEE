package controle;

import java.util.zip.CRC32;

import estrutura.Frame;

public class CRC {
	CRC32 Crc;
	byte[] r;
	long g;
	String fdest;
	String ffont;
	int flen;
	int finfo;	
	
	public long crc(String dest, String font, int len, int info) {
		this.fdest = dest;
		this.ffont = font;
		this.flen = len;
		this.finfo = info;
		
		Crc = new CRC32();
		System.out.println("fazendo CRC");
		r = (fdest + ffont + flen + finfo).toString().getBytes();
		Crc.update(r);
		g= Crc.getValue();
		System.out.println("CRC: "+g);
				
		return g;

	}

}
