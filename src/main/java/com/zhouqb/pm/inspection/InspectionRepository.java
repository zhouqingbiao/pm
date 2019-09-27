package com.zhouqb.pm.inspection;


import org.springframework.data.repository.PagingAndSortingRepository;

public interface InspectionRepository extends PagingAndSortingRepository<Inspection, Long> {
    Iterable<Inspection> findByPhysicalMachineIdOrderByXjrq(Long physicalMachineId);

    Iterable<Inspection> findByVirtualMachineIdOrderByXjrq(Long virtualMachineId);
}
