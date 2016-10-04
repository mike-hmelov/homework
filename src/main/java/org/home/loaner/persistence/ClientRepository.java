package org.home.loaner.persistence;

import org.home.loaner.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByPersonalId(String personalId);

    @Query(nativeQuery = true, value = "select exists (select personalid from personalid_blacklist where personalid = :personalId)")
    boolean isPersonalIdBlacklisted(@Param("personalId") String personalId);
}
