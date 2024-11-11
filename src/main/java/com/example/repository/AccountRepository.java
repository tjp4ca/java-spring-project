package com.example.repository;

// /**
import com.example.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

//import java.sql.Timestamp;
import java.util.List;
// */



/**
public interface AccountRepository {
}
*/

// /**
public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findAccountByUsername(String username);
}
// */