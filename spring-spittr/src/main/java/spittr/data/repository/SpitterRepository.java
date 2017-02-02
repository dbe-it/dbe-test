package spittr.data.repository;

import spittr.data.Spitter;


public interface SpitterRepository {

    String findFirstnameByUsername(String username);

    Spitter findByUsername(String username);

    Spitter findById(Long id);

    Spitter saveSpitter(Spitter spitter);
}
