package com.example.repository;

// /**
import com.example.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

//import java.sql.Timestamp;
// */



/**
public interface AccountRepository {
}
*/

// /**
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findAccountByUsername(String username);

    boolean existsByUsername(String username);
}
// */