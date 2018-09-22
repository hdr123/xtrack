package id.co.xtrack.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import id.co.xtrack.domain.Privilege;

public interface PrivilegeRepo extends PagingAndSortingRepository<Privilege, String> {

}
