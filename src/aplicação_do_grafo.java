import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.swing.JOptionPane;

public class aplicação_do_grafo {

	boolean orientado = false;
	boolean valorado = false;

	ArrayList<vertices> vertices = new ArrayList<vertices>();
	ArrayList<arestas> arestas = new ArrayList<arestas>();

	public boolean Valorado() {
		String val = JOptionPane.showInputDialog("Valorado? S/N");
		if (val.equalsIgnoreCase("S")) {
			return true;
		} else {
			return false;
		}
	}

	public boolean Orientado() {

		return false;
	}

	public void addvertices() {
		String mostrar = "";
		String continua = "S";
		do {

			String vertice = JOptionPane.showInputDialog("Vértices:" + "\n" + mostrar + "\n" + "Informe o vertice");
			vertices v = new vertices();
			v.setNome(vertice);
			vertices.add(v);

			mostrar = "";
			for (vertices m : vertices) {
				mostrar += m.getNome() + " - ";
			}

			continua = JOptionPane.showInputDialog("Continuar? S/N");
		} while (continua.equalsIgnoreCase("S"));
	}

	public void addarestas() {
		String mostrar = "";
		String continua = "S";

		valorado = Valorado();
		orientado = Orientado();

		do {
			arestas a = new arestas();
			String seta = " - ";
			String aresta1 = JOptionPane
					.showInputDialog("Arestas:" + "\n" + mostrar + "\n" + "Vertice de SAIDA da arestas");
			for (vertices v : vertices) {
				if (aresta1.equals(v.getNome())) {
					a.setSaida(v);
				}
			}

			String aresta2 = JOptionPane
					.showInputDialog("Arestas:" + "\n" + mostrar + "\n" + "Vertice de CHEGADA da aresta");
			for (vertices v : vertices) {
				if (aresta2.equals(v.getNome())) {
					a.setChegada(v);
				}
			}
			if (orientado == true) {
				a.setOrientado(true);
				seta = " --> ";
			}
			if (valorado == true) {
				a.setValorado(true);
				a.setValor(Double.parseDouble(
						JOptionPane.showInputDialog("VALOR da Aresta " + aresta1 + seta + aresta2 + ": ")));
			} else {
				a.setValor(1.0);
			}

			arestas.add(a);

			mostrar = "";
			for (arestas v : arestas) {
				mostrar += v.getSaida().getNome() + seta + v.getChegada().getNome() + "  //  ";
			}

			continua = JOptionPane.showInputDialog("Continuar? S/N");
		} while (continua.equalsIgnoreCase("S"));

	}

	public String[][] mostrarGrafos() {

		String vetorVertices[] = new String[vertices.size()];
		String matrizArestas[][] = new String[vertices.size()][vertices.size()];

		for (int i = 0; i < vertices.size(); i++) {
			vetorVertices[i] = vertices.get(i).getNome();

		}
		if (orientado == false) { // coloquei false pq bugou
			System.out.println("");
			//////// matriz de adjcacência normal////////
			System.out.println("---- Matriz de Adjacência: ----");

			int c1 = 0;
			for (int i = 0; i < vetorVertices.length; i++) {
				if (c1 == 0) {
					System.out.print("   " + "|" + vetorVertices[i] + "|");
					c1++;
				} else {
					System.out.print("|" + vetorVertices[i] + "|");
				}

			}
			for (int i = 0; i < vetorVertices.length; i++) {
				System.out.print("\n" + "|" + vetorVertices[i] + "|");
				for (int j = 0; j < vetorVertices.length; j++) {
					matrizArestas[i][j] = " 0 ";
					for (arestas ar : arestas) {

						if (ar.getSaida().getNome().equals(vertices.get(i).getNome())
								&& ar.getChegada().getNome().equals(vertices.get(j).getNome())
								|| ar.getSaida().getNome().equals(vertices.get(j).getNome())
										&& ar.getChegada().getNome().equals(vertices.get(i).getNome())) {
							if (ar.isValorado()) {
								matrizArestas[i][j] = ar.getValor().toString();
							} else {
								matrizArestas[i][j] = " 1 ";
							}
						} else {

						}

					}
					System.out.print(matrizArestas[i][j]);

				}

			}

		} else {
			//////// matriz de adjcacência orientada////////
			System.out.println("");
			System.out.println("---- Matriz de Adjacência: ----");
			System.out.println("");
			int c1 = 0;
			for (int i = 0; i < vetorVertices.length; i++) {
				if (c1 == 0) {
					System.out.print("   " + "|" + vetorVertices[i] + "|");
					c1++;
				} else {
					System.out.print("|" + vetorVertices[i] + "|");
				}

			}
			for (int i = 0; i < vetorVertices.length; i++) {
				System.out.print("\n" + "|" + vetorVertices[i] + "|");
				for (int j = 0; j < vetorVertices.length; j++) {
					matrizArestas[i][j] = " 0 ";
					for (arestas ar : arestas) {

						if (ar.getSaida().getNome().equals(vertices.get(i).getNome())
								&& ar.getChegada().getNome().equals(vertices.get(j).getNome())) {

							if (ar.isValorado()) {
								matrizArestas[i][j] = ar.getValor().toString();
							} else {
								matrizArestas[i][j] = " 1 ";
							}
						} else {

						}

					}
					System.out.print(matrizArestas[i][j]);

				}
			}

		}
		return matrizArestas;
	}

	public void percorre() {

		for (vertices v : vertices) {
			v.setGrau(retornaVizinhos(v).size());
		}

		Collections.sort(vertices); // COLOCA EM ORDEM DECRESCENTE DE GRAU
		System.out.println("---");

		int c = 0;
		int cor = 0;
		
		
		for (vertices v : vertices) { // PARA CADA VERTICE
			System.out.println("\n \n");
			System.out.println("entrou no :"+v.getNome());
			int quantidade = 0;
			int tipos = 0;
			if (v.getCor() == -1 && c == 0) { // SE NAO TEM COR E FOR O PRIMEIRO VERTICE A SER VISITADO
				v.setCor(cor); // ELE RECEBE A PRIMEIRA COR
				c++;
				for (vertices v2 : vertices) { // PARA CADA VERTICE (COMPARANDO AO VERTICE ATUAL)
					System.out.println("\n");
					System.out.println("entrando em v: "+v.getNome()+" -  v2: "+v2.getNome());
					 System.out.println("Naoevizinho: "+NaoeVizinho(v, v2));
                    System.out.println("vizinhoNaoTemCorIgual: "+vizinhoNaoTemCorIgual(v2, cor));
                    System.out.println("cor de v2 é -1? ->: "+v2.getCor());
					// SE OS DOIS NAO SAO VIZINHOS E NAO TEM CORES E O VERTICE V2 NAO TEM ADJACENTES COM A COR ATUAL
					if ((NaoeVizinho(v, v2)) && vizinhoNaoTemCorIgual(v2, cor) && (v.getCor() == -1 || v.getCor() == 0)) { 

						v2.setCor(cor); // ENTAO ELE RECEBE A COR ATUAL
						System.out.println("entrou no if e recebeu a cor: "+cor);
						tipos++;
						if (tipos == 2) {
							break;
						}
					}
				}

				cor++; // APOS O V2 PERCORRER TODOS OS VERTICES, A COR ALTERA

			} else if (v.getCor() == -1 && c > 0) { // SE NAO TEM COR E NAO FOR O PRIMEIRO VERTICE A SER VISITADO

				for (vertices v2 : vertices) { // PARA CADA VERTICE (COMPARANDO AO VERTICE ATUAL)
					System.out.println("\n");
                    System.out.println("entrando em v: "+v.getNome()+" -  v2: "+v2.getNome());
					// SE OS DOIS NAO SAO VIZINHOS E NAO TEM CORES E O VERTICE V2 NAO TEM ADJACENTES COM A COR ATUAL

					 System.out.println("Naoevizinho: "+NaoeVizinho(v, v2));
                     System.out.println("vizinhoNaoTemCorIgual: "+vizinhoNaoTemCorIgual(v2, cor));
                     System.out.println("cor do v2 é -1? ->: "+v2.getCor());
					if ((NaoeVizinho(v, v2)) && vizinhoNaoTemCorIgual(v2, cor) && (v.getCor() == -1 || v.getCor() == 0) && v2.getCor() == -1) {
						
						v2.setCor(cor); // ENTAO ELE RECEBE A COR ATUAL
						System.out.println("entrou no if e recebeu a cor: "+cor);
						
						tipos++;
						if (tipos == 2) {
							break;
						}
					}
				}

				cor++; // APOS O V2 PERCORRER TODOS OS VERTICES, A COR ALTERA

			}

		}
		System.out.println("\n \n");
		for (vertices v : vertices) {
			System.out.println("vértice: " + v.getNome() + "  -  cor: " + v.getCor());

		}
	}

	public ArrayList<vertices> retornaVizinhos(vertices v) {

		ArrayList<vertices> vizinhos = new ArrayList<vertices>();

		for (arestas a : arestas) {
			if (a.getSaida() == v) {
				vizinhos.add(a.getChegada());
			}
			if (a.getChegada() == v) {
				vizinhos.add(a.getSaida());
			}
		}
		return vizinhos;

	}

	public boolean NaoeVizinho(vertices v, vertices v2) {

		for (arestas a : arestas) {
			if (a.getSaida() == v && a.getChegada() == v2) {
				return false;
			}
			if (a.getChegada() == v && a.getSaida() == v2) {
				return false;
			}
		}
		return true;

	}

	public boolean vizinhoNaoTemCorIgual(vertices v, int cor) {

		for (arestas a : arestas) {
			if (a.getSaida() == v && a.getChegada().getCor() == cor) {
				return false;
			}
			if (a.getChegada() == v && a.getSaida().getCor() == cor) {
				return false;
			}
		}
		return true;

	}

	public boolean verificaCorAdjacente(int cor, ArrayList<vertices> vizinhos) {

		for (vertices viz : vizinhos) {
			if (cor == viz.getCor()) {
				return true;
			}
		}

		return false;
	}

	public void preencheGrafo() {
		addvertices();
		addarestas();
	}
}
