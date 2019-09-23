package com.zhouqb.pm.user;


import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    @Override
    Optional<User> findById(Long id);
    Optional<User> findByUsernameAndPassword(String username,String password);
}
