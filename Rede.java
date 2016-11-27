package controle;

import outin.InputTransmissao;
import estrutura.Frame;

public class Rede {

	Frame f1;
	Frame f2;
	InputTransmissao inp = new InputTransmissao();
	CRC Crc = new CRC();

	public Frame Fragment(Frame f) {
		try {
			this.f1 = f.clone();
			this.f2 = f.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		int n = f1.dados - 1500;
		f1.dados = 1500;
		System.out.println("##Mandando f1");
		f1.crc = Crc.crc(f1.dest, f1.font, f1.tamanho, f1.dados);
		inp.Recebe(f1);
		
		System.out.println("f2.dados:"+f2.dados);
		
		f2.dados = n;
		System.out.println("Frame fragmentado2 recebe "+ n+"bytes");
		if (n <= 46) {
			f2.pad = (46 - f2.dados);
		}
		
		//atualiza paddle pq frame foi fragmentado
		f2.crc = Crc.crc(f2.dest, f2.font, f2.tamanho, f2.dados);

		return f2;

	}

}
