package br.com.cadastrocliente.ejbs;

import java.util.Collection;

import br.com.cadastrocliente.entities.Cliente;

public interface ClienteLocal {
	void persist (Cliente cliente);
	
	void merge(Cliente cliente);
	
	void remove(Cliente cliente);
	
	Collection<Cliente> findAll();
	
	Cliente getById(Integer id);
}
