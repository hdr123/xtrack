package id.co.xtrack.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import id.co.xtrack.domain.Role;

@Repository
public interface RoleRepo extends PagingAndSortingRepository<Role, String> {
	List<Role> findByRoleUsersUserUsername(String username);
}
