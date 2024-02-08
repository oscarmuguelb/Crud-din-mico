package servicios.uno.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import servicios.uno.model.Pelicula;

import java.util.List;

@Repository
public interface PeliculasRepository extends JpaRepository<Pelicula,Long> {
    @Query("SELECT p FROM Pelicula p WHERE " +
            "LOWER(p.nombre) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(p.director) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "p.duracion = :query OR " +
            "LOWER(p.genero.nombre) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Pelicula> findByAllFieldsContaining(@Param("query") String query);


}
