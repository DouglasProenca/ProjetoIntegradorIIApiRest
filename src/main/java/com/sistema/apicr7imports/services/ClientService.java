package com.sistema.apicr7imports.services;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.sistema.apicr7imports.data.dto.request.ClientRequest;
import com.sistema.apicr7imports.data.dto.response.ClientDTO;
import com.sistema.apicr7imports.data.model.Client;
import com.sistema.apicr7imports.data.model.User;
import com.sistema.apicr7imports.exception.ForeignKeyException;
import com.sistema.apicr7imports.exception.ObjectNotFoundException;
import com.sistema.apicr7imports.mapper.DozerMapper;
import com.sistema.apicr7imports.repository.IClientRepository;

@Service
public class ClientService {

	@Autowired
	IClientRepository repository;
	
	@Autowired
	ICPFService cpfService;
	
	public ClientDTO findbyId(Integer id) {
		Client client = repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado!"));
		return DozerMapper.parseObject(client, ClientDTO.class);
	}
	
	public ClientDTO save(ClientRequest clientRequest) throws Exception {
		Client client = new Client();
		client.setClientName(clientRequest.getClientName());
		client.setCpf(verifyCPF(clientRequest.getCpf()));
		client.setDate(LocalDate.now());
		client.setUser(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
		return DozerMapper.parseObject(repository.save(client), ClientDTO.class);
	}
	
	public void delete(Integer id) {
		findbyId(id);
		try {
			repository.deleteById(id);
		} catch (Exception e) {
			throw new ForeignKeyException("O Registro possui relação com outros registros e não pode ser excluido!");
		}
	}
	
	public String verifyCPF(String cpf) throws Exception {
		
		if(!cpfService.verifyCPF(cpf).getIsValid()) throw new Exception("CPF Invalido");	
		
		return cpf;
	}
}
