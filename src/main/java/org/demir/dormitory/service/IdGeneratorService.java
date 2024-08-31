package org.demir.dormitory.service;

import org.demir.dormitory.entity.Counter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class IdGeneratorService {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public IdGeneratorService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public synchronized Long generateNextSequenceId(String sequenceName) {
        Query query = new Query(Criteria.where("_id").is(sequenceName));
        Update update = new Update().inc("seq", 1);
        Counter counter = mongoTemplate.findAndModify(query, update, Counter.class);

        if (counter == null) {
            counter = new Counter();
            counter.setId(sequenceName);
            counter.setSeq(1L);
            mongoTemplate.save(counter);
        }

        return counter.getSeq();
    }
}

