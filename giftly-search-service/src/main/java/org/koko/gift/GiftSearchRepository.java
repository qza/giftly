package org.koko.gift;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * GiftSearchRepository
 */
public interface GiftSearchRepository extends ElasticsearchRepository<Gift, Long> {

}
