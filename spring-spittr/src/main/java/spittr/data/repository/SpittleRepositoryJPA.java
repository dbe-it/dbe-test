package spittr.data.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import spittr.data.Spittle;


@Repository
public interface SpittleRepositoryJPA extends JpaRepository<Spittle, Long>{

    @Query("SELECT s FROM Spittle s ORDER BY s.time DESC")
    public List<Spittle> findLatestSpittles(Pageable pageable);

    @Query("SELECT s FROM Spittle s ORDER BY s.time DESC")
    public List<Spittle> findLatestSpittles();
}
