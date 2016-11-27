package controle;

import outin.InputTransmissao;
import estrutura.Frame;
import controle.Rede;

public class Enlace {
	// Classe responsável pelo meio receptor

	InputTransmissao destino = new InputTransmissao();
	Rede r = new Rede();
	Frame fr;

	// Receptor compara strings de macD, se for igual, pega o objeto

	public void AddNoMeio(Frame f) {
		this.fr = f;

		String d = fr.dest;

		if(f.dados <=1500){

			System.out.println("#\tHá conexão com a rede!");

			// //MAC não está na rede
			if (d.equalsIgnoreCase("00:00:00:00:00:00")) {
				System.out.println("Esta máquina não tem o MAC identificado na rede\n\n");
			}
			// MAC destino = máquina 1
			if (d.equalsIgnoreCase("4C:34:89:48:FB:AE")) {
				destino.Recebe(fr);
			}
			// MAC destino = máquina 2
			if (d.equalsIgnoreCase("4C:34:88:39:BB:AE")) {
				destino.Recebe(fr);
			}
			// MAC destino = máquina 3
			if (d.equalsIgnoreCase("4C:34:88:4A:CB:AF")) {
				destino.Recebe(fr);
			}
			// MAC destino = máquina 4
			if (d.equalsIgnoreCase("4C:34:88:4B:DB:AF")) {
				destino.Recebe(fr);
			}

		}else{
			Frame rr = r.Fragment(f);
			destino.Recebe(rr);
			
		}
		
	}

}
