package apisustentavel.fullstack.repositories;

import apisustentavel.fullstack.entities.SustainableAction;
import apisustentavel.fullstack.enums.ActionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SustainableActionRepository extends JpaRepository<SustainableAction, Long>
{
    List<SustainableAction> findByType(ActionType type);
}
