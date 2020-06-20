
public class vertices implements Comparable<vertices> {

	private double distancia_atual;
	private vertices anterior;
	private String nome;
	private vertices pai;
	private int cor = -1;
	private int grau = 0;
	
	

	public int getGrau() {
		return grau;
	}

	public void setGrau(int grau) {
		this.grau = grau;
	}

	public int getCor() {
		return cor;
	}

	public void setCor(int cor) {
		this.cor = cor;
	}

	public vertices getPai() {
		return pai;
	}

	public void setPai(vertices pai) {
		this.pai = pai;
	}

	public double getDistancia_atual() {
		return distancia_atual;
	}

	public void setDistancia_atual(double distancia_atual) {
		this.distancia_atual = distancia_atual;
	}

	public vertices getAnterior() {
		return anterior;
	}

	public void setAnterior(vertices anterior) {
		this.anterior = anterior;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public int compareTo(vertices o) {
		if (this.getGrau() > o.getGrau()) {
			return 1;
		} else if (this.getGrau() < o.getGrau()) {
			return -1;
		}
		return 0;
	}
}
