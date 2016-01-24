package org.koko.gift;

import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by qza on 1/24/2016.
 */

@RestController
@RequestMapping("/gifts")
public class GiftSearchController {

    @Autowired
    GiftSearchRepository repository;

    @RequestMapping("/search")
    public Iterable<Gift> searchByName(@RequestParam(value = "q") String query) {
        return repository.search(QueryBuilders.termQuery("name", query));
    }

}
