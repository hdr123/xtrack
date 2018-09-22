package id.co.xtrack.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import id.co.xtrack.domain.User;

@Repository
public interface UserRepo extends PagingAndSortingRepository<User, String>{

	User findByUsername(String username);
}
