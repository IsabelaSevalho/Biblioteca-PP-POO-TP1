package br.edu.uea.biblioteca.control;

import java.util.ArrayList;

import br.edu.uea.biblioteca.model.Cargo;

public class CargoController {
	private ArrayList<Cargo> cargos;

	public CargoController() {
		cargos = new ArrayList<>();
	}
	
	public Cargo buscarCargo(int codigo) {
		for(Cargo c : cargos) {
			if(codigo==c.getCodigo()) {
				return c;
			}
		}
		return null;
	}
	
	public boolean cadastrarCargo(Cargo cargo) {
		Cargo c = buscarCargo(cargo.getCodigo());
	    if (c != null) return false;
	    
	    cargos.add(cargo);
		return true;
	}
	
	public ArrayList<Cargo> getCargos(){
		return this.cargos;
	}
}
