package com.ib.adaro.repository;

import com.ib.adaro.domain.HeartRate;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the HeartRate entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HeartRateRepository extends JpaRepository<HeartRate, Long> {

}
