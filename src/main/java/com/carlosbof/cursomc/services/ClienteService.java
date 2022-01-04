package com.carlosbof.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carlosbof.cursomc.domain.Cliente;
import com.carlosbof.cursomc.exceptions.ObjectNotFoundException;
import com.carlosbof.cursomc.repositories.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;
	
	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: (" + id + "), Tipo: " + Cliente.class.getName()));
	}
	
}
