package com.dhernandez.market.persistence.crud;

import com.dhernandez.market.persistence.entity.Compra;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CompraCrudRepository extends CrudRepository<Compra,Long> {

    Optional<List<Compra>> findByIdCliente(String idCliente);
}
