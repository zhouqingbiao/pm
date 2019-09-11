package com.zhouqb.pm.physicalMachine;


import org.springframework.data.repository.PagingAndSortingRepository;

public interface PhysicalMachineRepository extends PagingAndSortingRepository<PhysicalMachine, Long> {

    @Override
    Iterable<PhysicalMachine> findAll();

    Iterable<PhysicalMachine> findByMacLikeOrIpLikeOrderById(String mac, String ip);

    Iterable<PhysicalMachine> findByMacLikeOrderById(String mac);

    Iterable<PhysicalMachine> findByIpLikeOrderById(String ip);
}
