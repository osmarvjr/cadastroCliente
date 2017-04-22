package br.com.cadastrocliente.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.cadastrocliente.ejbs.ClienteLocal;
import br.com.cadastrocliente.entities.Cliente;

@Named
@SessionScoped
public class GerenciamentoClientesMB implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final String HOME = "clientes?faces-redirect=true";
	private static final String NOVO_CLIENTE = "novoCliente?faces-redirect=true";
	
	@Inject
	private ClienteLocal bean;
	
	private Cliente cliente;
	
	private List<Cliente> clientes;
	
	@PostConstruct
	private void init(){
		clientes = (List<Cliente>) bean.findAll();
		cliente = new Cliente();
	}
	
	public String cadastrar(){
		try {
			if(cliente.getId()==null){
				bean.persist(cliente);
				clientes = (List<Cliente>) bean.findAll();
			}else{
				atualizar();
			}
		} catch (Exception e) {
			addMessage(e.getMessage());
		}
		return HOME;
	}
	
	public String atualizar(){
		try {
			bean.merge(cliente);
			clientes = (List<Cliente>) bean.findAll();
		} catch (Exception e) {
			addMessage(e.getMessage());
		}
		return HOME;
	}
	
	public void excluir(){
		try {
			bean.remove(cliente);
			clientes = (List<Cliente>) bean.findAll();
		} catch (Exception e) {
			addMessage(e.getMessage());
		}
	}
	
	public String novoCliente(){
		cliente = new Cliente();
		return NOVO_CLIENTE;
	}
	
	public String editarCliente(){
		return NOVO_CLIENTE;
	}
	
	private void addMessage(String message){
		FacesMessage msg = new FacesMessage();
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}	
	
}
