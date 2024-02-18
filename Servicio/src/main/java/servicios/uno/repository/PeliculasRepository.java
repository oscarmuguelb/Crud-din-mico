package servicios.uno.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import servicios.uno.model.Genero;
import servicios.uno.model.Pelicula;

import java.util.List;

@Repository
public interface PeliculasRepository extends JpaRepository<Pelicula,Long> {



    List<Pelicula> findByDirectorAndCreadaEnBetween(String director, String fechaInicio, String fechaFin);
    List<Pelicula> findByDirector(String director);
    List<Pelicula> findByCreadaEnBetween(String fechaInicio, String fechaFin);

    List<Pelicula> findByCreadaEnBetweenAndGenero(String fechaInicio, String fechaFin, Genero genero);


    List<Pelicula> findByGenero(Genero genero);
}
