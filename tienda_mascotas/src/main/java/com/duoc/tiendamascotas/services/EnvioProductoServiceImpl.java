package com.duoc.tiendamascotas.services;

import com.duoc.tiendamascotas.entities.Producto;
import com.duoc.tiendamascotas.entities.Envio;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class EnvioProductoServiceImpl implements EnvioProductoService {

    private List<Producto> productos = new ArrayList<>();
    private List<Envio> envios = new ArrayList<>();
    private AtomicInteger envioIdCounter = new AtomicInteger(1);

    public EnvioProductoServiceImpl() {
        // Inicializar objetos productos
        productos.add(new Producto(1, "Alimento para Perros Dogshow", 12500, 10));
        productos.add(new Producto(2, "Arena para Gatos", 11990, 15));
        productos.add(new Producto(3, "Collar para Perros", 2000, 30));
        productos.add(new Producto(4, "Juguete para Gatos", 3500, 20));
        productos.add(new Producto(5, "Acuario de Peces", 30000, 5));

        // Inicializar lista de envios
        envios.add(new Envio(envioIdCounter.getAndIncrement(), List.of(productos.get(0), productos.get(1), productos.get(2)), "En Bodega", "Calle Nueva York 123, Santiago, Región Metropolitana"));
        envios.add(new Envio(envioIdCounter.getAndIncrement(), List.of(productos.get(4), productos.get(1)), "Entregado", "Avenida del Mar 456, La Serena, Coquimbo"));
        envios.add(new Envio(envioIdCounter.getAndIncrement(), List.of(productos.get(3), productos.get(2)), "En Bodega", "Calle Prat 789, Valparaíso, Valparaíso"));
        envios.add(new Envio(envioIdCounter.getAndIncrement(), List.of(productos.get(0), productos.get(4)), "En Bodega", "Avenida Alemania 101, Temuco, La Araucanía"));
        envios.add(new Envio(envioIdCounter.getAndIncrement(), List.of(productos.get(1), productos.get(4)), "En Camino Destino", "Calle Colón 202, Punta Arenas, Magallanes"));
    }

    @Override
    public List<Envio> obtenerEnvios() {
        return envios;
    }

    @Override
    public Envio consultarEnvioById(int id) {
        return envios.stream()
                .filter(envio -> envio.getIdEnvio() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Envio no encontrado"));
    }

    public String consultarUbicacion(int idEnvio) {
        Envio envio = envios.stream()
                .filter(e -> e.getIdEnvio() == idEnvio)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Ubicación de producto no encontrado"));

        return envio.getUbicacionActual();
    }


}
