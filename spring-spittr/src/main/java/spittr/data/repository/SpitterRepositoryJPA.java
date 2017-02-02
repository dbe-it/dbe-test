package spittr.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import spittr.data.Spitter;


@Repository
public interface SpitterRepositoryJPA extends JpaRepository<Spitter, Long> {

    @Query("SELECT s.firstname FROM Spitter s WHERE s.username=:username")
    public String findFirstnameByUsername(@Param("username") String username);

    @Query("SELECT s FROM Spitter s WHERE s.username=:username")
    public Spitter findByUsername(@Param("username") String username);

}
