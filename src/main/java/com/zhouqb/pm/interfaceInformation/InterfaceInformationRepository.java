package com.zhouqb.pm.interfaceInformation;


import org.springframework.data.repository.PagingAndSortingRepository;

public interface InterfaceInformationRepository extends PagingAndSortingRepository<InterfaceInformation, Long> {
    Iterable<InterfaceInformation> findByInterfaceUrlLikeAndHistory(String interfaceUrl, String history);

    Iterable<InterfaceInformation> findByHistory(String history);
}
