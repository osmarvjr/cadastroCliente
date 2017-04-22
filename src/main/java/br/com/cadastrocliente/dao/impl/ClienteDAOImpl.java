package br.com.cadastrocliente.dao.impl;

import java.io.Serializable;

import javax.ejb.Stateless;

import br.com.cadastrocliente.dao.ClienteDAO;
import br.com.cadastrocliente.entities.Cliente;

@Stateless
public class ClienteDAOImpl extends GenericDAOImpl<Cliente, Integer> implements Serializable,
			ClienteDAO{
	
	private static final long serialVersionUID = 1L;

}
