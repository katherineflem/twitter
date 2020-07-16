package com.tts.TechTalentTwitter.controller;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.tts.TechTalentTwitter.model.User;
import com.tts.TechTalentTwitter.service.UserService;
@Controller
public class FollowController {
	@Autowired
	private UserService userService;
	
	//called when we make a post request
	@PostMapping(value = "/follow/{username}")
	public String follow(@PathVariable(value="username") String username, HttpServletRequest request) {
		//call userservice to get the currently logged in user as well as the one we want to follow
		User loggedInUser = userService.getLoggedInUser();
		//we get all of the usertofollows current followers, add the currently logged in user to the list and then reassign the updated list to usertofollow
    	User userToFollow = userService.findByUsername(username);
		List<User> followers = userToFollow.getFollowers();
		followers.add(loggedInUser);
		userToFollow.setFollowers(followers);
		//we save usertofollow and redirect to the last page
		userService.save(userToFollow);
		return "redirect:" + request.getHeader("Referer");
	}
	//unfollow method
	@PostMapping(value = "/unfollow/{username}")
	public String unfollow(@PathVariable(value="username") String username, HttpServletRequest request) {
		User loggedInUser = userService.getLoggedInUser();
    	User userToFollow = userService.findByUsername(username);
		List<User> followers = userToFollow.getFollowers();
		followers.remove(loggedInUser);
		userToFollow.setFollowers(followers);
		userService.save(userToFollow);
		return "redirect:" + request.getHeader("Referer");
	}
}