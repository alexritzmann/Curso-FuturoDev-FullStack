package apisustentavel.fullstack.repositories;

import apisustentavel.fullstack.entities.SustainableAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SustainableActionRepository extends JpaRepository<SustainableAction, Integer>
{

}
