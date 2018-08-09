package com.ib.adaro.repository;

import com.ib.adaro.domain.Variable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Variable entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VariableRepository extends JpaRepository<Variable, Long> {

}
