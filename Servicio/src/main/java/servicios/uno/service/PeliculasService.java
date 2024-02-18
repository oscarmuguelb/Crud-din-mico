package servicios.uno.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import servicios.uno.model.Pelicula;
import servicios.uno.repository.PeliculasRepository;
import servicios.uno.utils.Response;

import java.sql.SQLException;
import java.util.List;

@Service
@Transactional
public class PeliculasService {
    @Autowired
    private PeliculasRepository repository;
    @Transactional(readOnly = true)
    public Response<List<Pelicula>> getAll() throws SQLException {
        return new Response<>(this.repository.findAll(), false, 200, "Correcto");
    }

    @Transactional(readOnly = true)
    public Response<Pelicula> getById(Long id) throws SQLException {
        return new Response<>(this.repository.findById(id).orElse(null), false, 200, "Correcto");
    }

    @Transactional(readOnly = true)
    public Response<List<Pelicula>> getByDirectorAndCreadaEnBetween(String director, String fechaInicio, String fechaFin) throws SQLException {
        if (director != null && fechaInicio != null && fechaFin != null) {
            return new Response<>(this.repository.findByDirectorAndCreadaEnBetween(director, fechaInicio, fechaFin), false, 200, "Correcto");
        } else if (director != null) {
            return new Response<>(this.repository.findByDirector(director), false, 200, "Correcto");
        } else if (fechaInicio != null && fechaFin != null) {
            return new Response<>(this.repository.findByCreadaEnBetween(fechaInicio, fechaFin), false, 200, "Correcto");
        } else {
            return new Response<>(null, true, 400, "Error");
        }
    }

    @Transactional(rollbackFor = {SQLException.class})
    public Response<Pelicula> save(Pelicula pelicula) throws SQLException {
        return new Response<>(this.repository.save(pelicula), false, 200, "Correcto");
    }

    @Transactional(rollbackFor = {SQLException.class})
    public Response<Pelicula> update(Pelicula pelicula) throws SQLException {
        return new Response<>(this.repository.save(pelicula), false, 200, "Correcto");
    }

    @Transactional(rollbackFor = {SQLException.class})
    public Response<Pelicula> delete(Long id) throws SQLException {
        this.repository.deleteById(id);
        return new Response<>(null, false, 200, "Correcto");
    }


}
