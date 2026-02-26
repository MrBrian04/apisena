package com.sena.service.implementation;

import com.sena.dto.ClienteDTO;
import com.sena.entity.Cliente;
import com.sena.mapper.ClienteMapper;
import com.sena.repository.ClienteRepository;
import com.sena.service.ClienteService;
import com.sena.validator.ClienteValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
public class ClienteServiceImplementation implements ClienteService {

    private final ClienteRepository repository;
    private final ClienteMapper mapper;

    public ClienteServiceImplementation(ClienteRepository repository, ClienteMapper mapper){
        this.repository=repository;
        this.mapper=mapper;
    }
    @Override
    @Transactional(readOnly = true)
    public Page<ClienteDTO> findAll(Pageable pageable, String search) {
        Page<Cliente> clientes;
        //Alt+ 124
        if(search==null || search.trim().isEmpty()){
            clientes= repository.findAll(pageable);
        }else{
            clientes= repository.findByNombreContainingIgnoreCase(pageable, search);
        }
        return new PageImpl<>(
                clientes.getContent().stream()
                        .map(mapper::toDTO)
                        .collect(Collectors.toList()),
                pageable,
                clientes.getTotalElements()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public ClienteDTO findById(Long id) {
        Cliente entidad= repository.findById(id).orElseThrow(
                ()->new NoDataFoundException("No existe un registro con ese ID."));
        return mapper.toDTO(entidad);
    }

    @Override
    public ClienteDTO create(ClienteDTO obj) {
        ClienteValidator.save(obj);
        Cliente entidad=mapper.toEntity(obj);
        Cliente saved=repository.save(entidad);
        return mapper.toDTO(saved);
    }

    @Override
    public ClienteDTO update(Long id, ClienteDTO obj) {
        ClienteValidator.save(obj);
        Cliente entidad=mapper.toEntity(obj);
        if(repository.existsById(id)){
            entidad.setId(id);
            Cliente saved=repository.save(entidad);
            return mapper.toDTO(saved);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        Cliente entidad=repository.findById(id).orElseThrow(
                ()->new NoDataFoundException("No existe un registro con ese ID."));
        repository.delete(entidad);
    }

}
