package org.koko.gift;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * GiftRepository
 */
@RepositoryRestResource(collectionResourceRel = "gifts", path = "gifts")
public interface GiftRepository extends MongoRepository<Gift, String> {

    List<Gift> findByName(@Param("name") String name);

}