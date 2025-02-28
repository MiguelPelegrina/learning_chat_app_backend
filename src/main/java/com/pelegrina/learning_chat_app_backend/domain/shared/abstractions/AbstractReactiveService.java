package com.pelegrina.learning_chat_app_backend.domain.shared.abstractions;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public abstract class AbstractReactiveService<R extends R2dbcRepository<E, K>, E, I, O, K> {
    // Fields
    protected final R repository;
    protected final ModelMapper mapper;

    public List<O> findAll(){
        return repository.findAll().toStream().map(
                this::toOutputDto)
                .collect(Collectors.toList());
    }

    public O getById(K id){
        return repository.findById()
    }

    @Transactional
    public O save(I inputDto){
        E instance = toEntity(inputDto);
        E savedInstance = repository.save(instance);
        return toOutputDto(savedInstance);
    }

    @Transactional
    public void deleteById(K id){
        repository.deleteById(id);
    }

    // Abstract methods for mapping
    protected abstract O toOutputDto(E entity);

    protected abstract E toEntity(I inputDto);
}
