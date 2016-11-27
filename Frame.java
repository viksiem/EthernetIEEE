package estrutura;

public class Frame implements Cloneable {
	
	//Interface do frame
	
	public int pream;
	public int sd;
	public String dest;
	public String font;
	public int tamanho;
	public int dados;
	public int pad;
	public long crc;
	
	public Frame(int prem, int sd, String dest, 
			String font, int tamanho, int dados,
			int pad, long crc) {
		super();
		this.pream = prem;
		this.sd = sd;
		this.dest = dest;
		this.font = font;
		this.tamanho = tamanho;
		this.dados = dados;
		this.pad = pad;
		this.crc = crc;
	}

	@Override
	public Frame clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return (Frame) super.clone();
	}

}