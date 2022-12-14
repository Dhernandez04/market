package com.dhernandez.market.persistence.crud;

import com.dhernandez.market.persistence.entity.Producto;
import org.springframework.data.repository.CrudRepository;

import javax.swing.*;
import java.util.List;
import java.util.Optional;

public interface ProductoCrudRepository  extends CrudRepository<Producto,Long> {

    List<Producto> findByIdCategoriaOrderByNombreAsc(Long idCategoria);

    Optional<List<Producto>> findByCantidadStockLessThanAndEstado(int cantidadStock,boolean estado);
}
