package com.banco.accountapi.repository;

import com.banco.accountapi.model.Extract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface ExtractRepository extends JpaRepository<Extract, Long> {

    @Query("SELECT e FROM Extract e WHERE (:numberAccount is null or e.numberAccount = :numberAccount) AND " +
    "(:category is null or e.category = :category)"
    )

    List<Extract> findParameters(Long numberAccount, Integer category);

}
