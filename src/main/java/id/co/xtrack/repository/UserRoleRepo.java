package id.co.xtrack.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import id.co.xtrack.domain.UserRole;

public interface UserRoleRepo extends PagingAndSortingRepository<UserRole, String>{
	
	UserRole findByUser_Id(String userId);
}
