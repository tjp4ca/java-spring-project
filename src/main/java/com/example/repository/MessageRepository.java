package com.example.repository;

// /**
import com.example.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

//import java.sql.Timestamp;
import java.util.List;
// */

 /**
public interface MessageRepository {
}
*/

// /**
public interface MessageRepository extends JpaRepository<Message, Long> {
    // List<Message> findMessageByAccountId(Long accountId);
}
// */