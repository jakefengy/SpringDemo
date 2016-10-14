package com.web.repository;

import com.web.model.UserEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 2016-10-14.
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {


}
