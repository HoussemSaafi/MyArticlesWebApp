package com.java.userrestapi.repository;

import com.java.userrestapi.model.Stories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface StoriesRepository extends JpaRepository<Stories,Long> {

}

