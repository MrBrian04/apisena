package com.sena.service.implementation;

import com.sena.dto.UsuarioDTO;
import com.sena.entity.Usuario;
import com.sena.exception.NoDataFoundException;
import com.sena.exception.ValidateException;
import com.sena.mapper.UsuarioMapper;
import com.sena.repository.UsuarioRepository;
import com.sena.service.UsuarioService;
import com.sena.validator.UsuarioValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@Transactional
public class UsuarioServiceImplementation implements UsuarioService {
    private final UsuarioRepository repository;
    private final UsuarioMapper mapper;

    public UsuarioServiceImplementation(UsuarioRepository repository, UsuarioMapper mapper){
        this.repository=repository;
        this.mapper=mapper;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UsuarioDTO> findAll(Pageable pageable, String search) {
        Page<Usuario> usuarios;
        if(search==null || search.trim().isEmpty()){
            usuarios= repository.findAll(pageable);
        }else{
            usuarios= repository.findByEmailContainingIgnoreCase(pageable, search);
        }
        return new PageImpl<>(
                usuarios.getContent().stream()
                        .map(mapper::toDTO)
                        .collect(Collectors.toList()),
                pageable,
                usuarios.getTotalElements()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public UsuarioDTO findById(Integer id) {
        Usuario entidad= repository.findById(id).orElseThrow(
                ()->new NoDataFoundException("No existe un registro con ese ID."));
        return mapper.toDTO(entidad);
    }

    @Override
    public UsuarioDTO create(UsuarioDTO obj) {
        UsuarioValidator.save(obj);
        if (repository.findByEmail(obj.getEmail()).isPresent())          {
            throw new ValidateException("El email ya está registrado");
        }
        Usuario entidad=mapper.toEntity(obj);
        Usuario saved=repository.save(entidad);
        return mapper.toDTO(saved);
    }

    @Override
    public UsuarioDTO update(Integer id, UsuarioDTO obj) {
        UsuarioValidator.save(obj);
        Usuario entidad=mapper.toEntity(obj);
        if(repository.existsById(id)){
            entidad.setId(id);
            Usuario saved=repository.save(entidad);
            return mapper.toDTO(saved);
        }
        return null;
    }

    @Override
    public void delete(Integer id) {
        Usuario entidad=repository.findById(id).orElseThrow(
                ()->new NoDataFoundException("No existe un registro con ese ID."));
        repository.delete(entidad);
    }
}