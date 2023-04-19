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

    @PayloadRoot(localPart = "EliminarProductoRequest", namespace = "https://t4is.uv.mx/productos")
    @ResponsePayload
    public EliminarProductoResponse Eliminar(@RequestPayload EliminarProductoRequest peticion){
        EliminarProductoResponse respuesta = new EliminarProductoResponse();
        Producto producto = new Producto();
        producto = iProductos.findById(peticion.getId()).get();
        iProductos.deleteById(peticion.getId());
        respuesta.setRespuesta("Se ha eliminado el elemento "+producto.getNombre());
        return respuesta;
    }
}
