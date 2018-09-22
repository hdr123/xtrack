package id.co.xtrack.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import id.co.xtrack.domain.RolePrivilege;

public interface RolePrivilegeRepo extends PagingAndSortingRepository<RolePrivilege, String> {
	
	Long countByRole_Id(String roleId);
}
