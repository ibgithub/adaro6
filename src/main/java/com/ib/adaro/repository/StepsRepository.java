package com.ib.adaro.repository;

import com.ib.adaro.domain.Steps;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Steps entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StepsRepository extends JpaRepository<Steps, Long> {

}
