package com.metrodata.serverapp.repositories;

import com.metrodata.serverapp.models.Region;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends JpaRepository<Region, Integer> {
  // Native => identik database -> attribut
  @Query(
    value = "SELECT * FROM `tb_region` r WHERE r.region_name LIKE ?1",
    nativeQuery = true
  )
  List<Region> searchAllByNameNative(String name);

  // JPQL => mempresentasikan ke object/attribute code != attribute pada database
  @Query("SELECT r FROM Region r WHERE r.nama LIKE :name")
  List<Region> searchAllByNameJPQL(String name);

  // Query Method => generate method name -> base JPQL
  Optional<Region> findByNama(String name);
}
/*
 * Parameterized query:
 * - ?1, ?2, ?3   => position base parameters
 * - :name, :age  => parameter name
 */
