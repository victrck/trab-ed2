package Arquivo;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

import Arvore.AVLTree;


public class Arquivo {
	private ArrayList<AVLTree<String>> arvore = new ArrayList<AVLTree<String>>();
	
	public String[] gerarColunas(File file){
		try {
			BufferedReader input = new BufferedReader(new FileReader(file)); 
			String linhas = input.readLine();
			String[] combobox = linhas.split(";");
			input.close();
			return combobox;
		} catch(Exception e){
			System.out.println("Erro.");	
		}
		return null;
	}
	
	
	public ArrayList<AVLTree<String>> ler(int quant, File file) {
		try {
			BufferedReader input = new BufferedReader(new FileReader(file)); 
			String linhas = input.readLine();
			String[] arvoresColunas;
			arvore = gerar(quant);
			 for (linhas = input.readLine(); linhas != null; linhas = input.readLine()) {
					for(int i = 0; i < quant; i++){
						arvoresColunas = linhas.split(";");
						arvore.get(i).insert(new String(arvoresColunas[i]), linhas);
					}	
		     }
		    input.close();
			return arvore;
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
		return null;
	}
	
	public ArrayList<AVLTree<String>> gerar(int quant){
		ArrayList<AVLTree<String>> arvore = new ArrayList<AVLTree<String>>();
		for (int i = 0; i < quant; i++){
			AVLTree<String> arv = new AVLTree<String>();
			arvore.add(arv);
		}
		return arvore;
	}
}
