package br.com.cadastrocliente.ejbs.impl;

import java.io.Serializable;
import java.util.Collection;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.cadastrocliente.dao.ClienteDAO;
import br.com.cadastrocliente.ejbs.ClienteLocal;
import br.com.cadastrocliente.entities.Cliente;

@Stateless
public class ClienteBean implements Serializable, ClienteLocal {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ClienteDAO dao;
	
	@Override
	public void persist(Cliente cliente) {
		validarCliente(cliente);
		dao.persist(cliente);
	}

	@Override
	public void merge(Cliente cliente) {
		validarCliente(cliente);
		dao.merge(cliente);
	}

	@Override
	public void remove(Cliente cliente) {
		if(cliente.getId()==0 || cliente.getId()==null){
			throw new IllegalArgumentException("Campo id não pode ser nulo ou vazio");
		}
		dao.removeById(cliente.getId());
	}
	
	@Override
	public Collection<Cliente> findAll() {
		return dao.findAll();
	}

	@Override
	public Cliente getById(Integer id) {
		if(id==0||id==null){
			throw new IllegalArgumentException("Campo id não pode ser nulo ou vazio");
		}
		return dao.getById(id);
	}
	
	private void validarCliente(Cliente cliente){
		if ("".equals(cliente.getNome()) || cliente.getNome() == null) {
			throw new IllegalArgumentException(
					"Campo nome não pode ser nulo ou vazio");
		}
		if ("".equals(cliente.getProfissao()) || cliente.getProfissao() == null) {
			throw new IllegalArgumentException(
					"Campo profissão não pode ser nulo ou vazio");
		}
		if ("".equals(cliente.getTelefone()) || cliente.getTelefone() == null) {
			throw new IllegalArgumentException(
					"Campo telefone não pode ser nulo ou vazio");
		}
		if (cliente.getIdade().equals(0) || cliente.getIdade() == null) {
			throw new IllegalArgumentException(
					"Campo idade não pode ser nulo ou vazio");
		}
	}
	
}
