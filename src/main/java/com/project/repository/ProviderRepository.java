package com.project.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.entities.Provider;

@Repository
public interface ProviderRepository extends CrudRepository<Provider, Long> {

}
