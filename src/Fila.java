
public class Fila {
	public No inicio, fim;
	int t = 0;

	public Fila() {
		inicio = fim = null;
	}

	public void insere(vertices e) {

		No novo = new No(e);
		if (inicio == null) {
			inicio = novo;
			fim = novo;
			t++;
			System.out.println("inseriu na Fila: "+novo.Elemento.getNome());
//		} else if (t == 0) {
//			inicio.proximo = novo;
//			fim = novo;
//			t++;
//			System.out.println("inseriu: "+novo.Elemento.getNome());
		} else {
			fim.proximo = novo;
			fim = novo;
			t++;
			System.out.println("inseriu na Fila: "+novo.Elemento.getNome());
		}
	}

	public String remover() {
		vertices e;
		e=inicio.Elemento;
		inicio = inicio.proximo;
		return e.getNome();
	}
	
	public int tamanho() {
		int c = 0;
		No aux = inicio;
		while (aux.proximo!=null) {
			aux = inicio.proximo;
			c++;
		}
		return c;
	}

	public boolean Vazio() {
		return (inicio==null);
	}
	
	
}
