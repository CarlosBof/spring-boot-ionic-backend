package com.carlosbof.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.carlosbof.cursomc.domain.Cliente;
import com.carlosbof.cursomc.domain.enums.TipoCliente;
import com.carlosbof.cursomc.dto.ClienteNewDTO;
import com.carlosbof.cursomc.repositories.ClienteRepository;
import com.carlosbof.cursomc.resources.exception.FieldMessage;
import com.carlosbof.cursomc.services.validation.utils.BR;

import org.springframework.beans.factory.annotation.Autowired;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
    
    @Autowired
    private ClienteRepository repo;

    @Override
    public void initialize(ClienteInsert ann) {
    }

    @Override
    public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();
        // inclua os testes aqui, inserindo erros na lista

        if(objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && BR.isValidCPF(objDto.getCpfOuCnpj()) == false) {
            list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
        }

        if(objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && BR.isValidCNPJ(objDto.getCpfOuCnpj()) == false) {
            list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
        }

        Cliente aux = repo.findByEmail(objDto.getEmail());
        if(aux != null) {
            list.add(new FieldMessage("email", "E-mail já existente"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}
