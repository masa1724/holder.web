package holder.api;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import holder.domain.model.User;
import holder.domain.repository.login.UserRepository;

@RestController
public class JSONSample {
	@Autowired
	UserRepository userRepository;
	
    @RequestMapping(value = "/json1/{email}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public List<User> json1(@PathVariable("email") String email) throws DataAccessException, IOException {
    	User user = userRepository.find(email);
    	User user2 = user;
    	
    	return Arrays.asList(user,user2);
    }
}
