package mx.uv.backend;

import org.springframework.data.repository.CrudRepository;

public interface IProductos extends CrudRepository<Producto,Integer> {
    
}
