package mx.uv.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import https.t4is_uv_mx.productos.*;

@Endpoint
public class EndPoint {
    @Autowired
    private IProductos iProductos;

    @PayloadRoot(localPart = "AgregarProductoRequest", namespace = "https://t4is.uv.mx/productos")
    @ResponsePayload
    public AgregarProductoResponse Agregar(@RequestPayload AgregarProductoRequest peticion){
        AgregarProductoResponse respuesta = new AgregarProductoResponse();
        respuesta.setRespuesta("Se agrego el producto: "+peticion.getNombre());
        Producto producto = new Producto();
        producto.setNombre(peticion.getNombre());
        producto.setCantidad(peticion.getCantidad());
        iProductos.save(producto);
        return respuesta;
    }

    @PayloadRoot(localPart = "BuscarProductoRequest", namespace = "https://t4is.uv.mx/productos")
    @ResponsePayload
    public BuscarProductoResponse Buscar(@RequestPayload BuscarProductoRequest peticion){
        BuscarProductoResponse respuesta = new BuscarProductoResponse();
        Producto producto = new Producto();
        producto = iProductos.findById(peticion.getId()).get();
        respuesta.setRespuesta(producto.getNombre());
        return respuesta;
    }

    @PayloadRoot(localPart = "VisualizarProductoRequest", namespace = "https://t4is.uv.mx/productos")
    @ResponsePayload
    public VisualizarProductoResponse Visualizar(){
        VisualizarProductoResponse respuesta = new VisualizarProductoResponse();
        Iterable<Producto> lista = iProductos.findAll();
        for(Producto e: lista){
            respuesta.getRespuesta().add("Id: "+e.getId()+" Nombre: "+e.getNombre()+" Cantidad: "+e.getCantidad());
        }
        return respuesta;
    }
}
