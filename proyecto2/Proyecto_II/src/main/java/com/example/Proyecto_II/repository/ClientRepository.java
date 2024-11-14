package com.example.Proyecto_II.repository;

        import com.example.Proyecto_II.model.Client;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    boolean existsByDni(String dni);
}
