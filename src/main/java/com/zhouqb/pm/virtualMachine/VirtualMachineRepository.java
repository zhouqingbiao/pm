package com.zhouqb.pm.virtualMachine;


import org.springframework.data.repository.PagingAndSortingRepository;

public interface VirtualMachineRepository extends PagingAndSortingRepository<VirtualMachine, Long> {

    @Override
    Iterable<VirtualMachine> findAll();

    Iterable<VirtualMachine> findByPhysicalMachineIdOrderById(Long physicalMachineId);

    Iterable<VirtualMachine> findByMacLikeOrIpLikeAndPhysicalMachineIdOrderById(String mac, String ip, Long physicalMachineId);

    Iterable<VirtualMachine> findByMacLikeAndPhysicalMachineIdOrderById(String mac, Long physicalMachineId);

    Iterable<VirtualMachine> findByIpLikeAndPhysicalMachineIdOrderById(String ip, Long physicalMachineId);


    Iterable<VirtualMachine> findByMacLikeOrIpLikeOrderById(String mac, String ip);

    Iterable<VirtualMachine> findByMacLikeOrderById(String mac);

    Iterable<VirtualMachine> findByIpLikeOrderById(String ip);
}
