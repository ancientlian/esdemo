package com.example.esdemo.repository;


import com.example.esdemo.entity.ModuleTestData;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuleRepository extends  ElasticsearchRepository<ModuleTestData, String>{


}
