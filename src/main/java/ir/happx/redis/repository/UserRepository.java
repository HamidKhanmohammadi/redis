package ir.happx.redis.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import ir.happx.redis.domain.BUser;

@Repository
public interface UserRepository extends MongoRepository<BUser, String> {

}
