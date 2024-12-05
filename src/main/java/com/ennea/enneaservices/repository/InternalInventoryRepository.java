package com.ennea.enneaservices.repository;

import com.ennea.enneaservices.model.InternalInventory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface InternalInventoryRepository extends CrudRepository<InternalInventory, Long> {

    List<InternalInventory> findByGenkeyIn(List<String> keys);

    Optional<InternalInventory> findFirstByGenkey(String key);

    @Query("SELECT ii.genkey FROM InternalInventory ii")
    Set<String> findAllKeys();

    @Query("select ii from InternalInventory ii join ii.mapping iim where ii.modificationDateTime > :date and iim.creationDateTime > :date")
    List<InternalInventory> findAllByModificationDateTimeGreaterThanEqual(LocalDateTime date);

    @Query("SELECT ii FROM InternalInventory ii WHERE LOWER(REPLACE(ii.genkey, ' ', '')) LIKE CONCAT('%',:key,'%') OR LOWER(REPLACE(ii.name, ' ', '')) LIKE CONCAT('%',:key,'%') ORDER BY ii.modificationDateTime desc")
    Page<InternalInventory> findAllByGenkeyContainingIgnoreCase(@Param("key") String search, Pageable pageable);
}
