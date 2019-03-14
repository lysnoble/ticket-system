
package org.alyssanoble.models.data;

import org.alyssanoble.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface UserDao extends CrudRepository<User,Integer> {

    User findByEmail(String email);

    User findByPhone(String phone);

    User findById(int userId);
}