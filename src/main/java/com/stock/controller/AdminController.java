package com.stock.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.stock.model.Post;
import com.stock.model.User;
import com.stock.service.AdminServices;
import com.stock.util.Constants;

/**
 * Controller class to handle admin interactions 
 *  
 * @author anitageorge and bhavya
 *
 */
@Controller
@RequestMapping(value = "/")
public class AdminController {
	
private static final Logger logger = Logger.getLogger(AdminController.class);

@Autowired
private AdminServices adminService;

/**
 * Mapping for admin page
 * @param model
 * @return
 * @throws IOException
 */
@RequestMapping(value = "/admin")
public ModelAndView listUser(ModelAndView model) throws IOException {
    List<User> listUser = adminService.getAllUser();
    model.addObject("listUser", listUser);
    model.setViewName("admin");
    return model;
}

/**
 * Mapping for pending posts page
 * @param model
 * @return
 * @throws IOException
 */
@RequestMapping(value = "/PendingPosts")
public ModelAndView listPost(ModelAndView model) throws IOException {
    List<Post> listPost = adminService.getAllPost();
    model.addObject("listPost", listPost);
    model.setViewName("PendingPosts");
    return model;
}

/**
 * Mapping for traders list
 * @param model
 * @return
 * @throws IOException
 */
@RequestMapping(value = "/tradersList")
public ModelAndView tradersList(ModelAndView model) throws IOException {
    List<User> tradersList = adminService.getAllUser();
    System.out.println("traders: " + tradersList);
    model.addObject("tradersList", tradersList);
    model.setViewName("tradersList");
    return model;
}

/**
 * Mapping for experts list page
 * @param model
 * @return
 * @throws IOException
 */
@RequestMapping(value = "/expertsList")
public ModelAndView expertsList(ModelAndView model) throws IOException {
    List<User> expertsList = adminService.getAllUser();
    model.addObject("expertsList", expertsList);
    model.setViewName("expertsList");
    return model;
}

/**
 * Mapping for the list of placement officers
 * @param model
 * @return
 * @throws IOException
 */
@RequestMapping(value = "/placementOfficersList")
public ModelAndView placementOfficersList(ModelAndView model) throws IOException {
    List<User> placementOfficersList = adminService.getAllUser();
    model.addObject("placementOfficersList", placementOfficersList);
    model.setViewName("placementOfficersList");
    return model;
}

/**
 * Mapping for the list of staff
 * @param model
 * @return
 * @throws IOException
 */
@RequestMapping(value = "/staffList")
public ModelAndView staffList(ModelAndView model) throws IOException {
    List<User> staffList = adminService.getAllUser();
    model.addObject("staffList", staffList);
    model.setViewName("staffList");
    return model;
}

/**
 * Mapping for adding a new user
 * @param model
 * @return
 */
@RequestMapping(value = "/UserForm", method = RequestMethod.GET)
public ModelAndView newUser(ModelAndView model) {
	User user = new User();
	user.setAccount_status(Constants.ACCOUNT_HOLD);
    model.addObject("user", user);
    model.setViewName("UserForm");
    return model;
}

/**
 * Mapping for saving user information
 * @param user
 * @return
 */
@RequestMapping(value = "/saveUser", method = RequestMethod.POST)
public ModelAndView saveUser(@ModelAttribute User user) {
    if (user.getUserid() == 0) { // if Users id is 0 then creating the
        // Users other updating the Users
    	adminService.addUser(user);
    } else {
    	adminService.updateUser(user);
    }
    return new ModelAndView("redirect:/admin");
}


/**
 * Mapping for deleting a user 
 * @param request
 * @return
 */
@RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
public ModelAndView deleteUser(HttpServletRequest request) {
    int userid = Integer.parseInt(request.getParameter("userid"));
    User user = adminService.getUser(userid);
    user.setAccount_status(Constants.ACCOUNT_CANCEL);
    adminService.updateUser(user);
	return new ModelAndView("redirect:/admin");
}

/**
 * Mapping for viewing posts of a particular user
 * @param model
 * @param request
 * @return
 * @throws IOException
 */
@RequestMapping(value = "/viewPosts", method = RequestMethod.GET)
public ModelAndView viewPosts(ModelAndView model,HttpServletRequest request) throws IOException {
	int userid = Integer.parseInt(request.getParameter("userid"));
    List<Post> listSpecificPost = adminService.getSpecificPosts(userid);
    model.addObject("listSpecificPost", listSpecificPost);
    model.setViewName("viewPost");
    return model;
}

/**
 * Mapping for approving a user - change user account status to active
 * @param request
 * @return
 */
@RequestMapping(value = "/approveUser", method = RequestMethod.GET)
public ModelAndView approveUser(HttpServletRequest request) {
	int userid = Integer.parseInt(request.getParameter("userid"));
    User user = adminService.getUser(userid);
    user.setAccount_status(Constants.ACCOUNT_APPROVED);
    adminService.updateUser(user);
	return new ModelAndView("redirect:/admin");
}

/**
 * Mapping for canceling a user account - change user account to cancel
 * @param request
 * @return
 */
@RequestMapping(value = "/cancelUser", method = RequestMethod.GET)
public ModelAndView cancelUser(HttpServletRequest request) {
	int userid = Integer.parseInt(request.getParameter("userid"));
    User user = adminService.getUser(userid);
    user.setAccount_status(Constants.ACCOUNT_CANCEL);
    adminService.updateUser(user);
	return new ModelAndView("redirect:/admin");
}

/**
 * Mapping for deleting a user post - change status of post to cancel
 * @param request
 * @return
 */
@RequestMapping(value = "/deletePost", method = RequestMethod.GET)
public ModelAndView deletePost(HttpServletRequest request) {
    int postid = Integer.parseInt(request.getParameter("postid"));
    Post post = adminService.getPost(postid);
	post.setPost_status(Constants.POST_DELETE);
    adminService.updatePost(post);
	return new ModelAndView("redirect:/admin");
}

/**
 * Mapping for approving a user post - change the status of post to approved
 * @param request
 * @return
 */
@RequestMapping(value = "/approvePost", method = RequestMethod.GET)
public ModelAndView approvePost(HttpServletRequest request) {
	int postid = Integer.parseInt(request.getParameter("postid"));
	Post post = adminService.getPost(postid);
	post.setPost_status(Constants.POST_APPROVED);
    adminService.updatePost(post);
	return new ModelAndView("redirect:/admin");
}

/**
 * Mapping for canceling the post - change post status to cancelled
 * @param request
 * @return
 */
@RequestMapping(value = "/cancelPost", method = RequestMethod.GET)
public ModelAndView cancelPost(HttpServletRequest request) {
	int postid = Integer.parseInt(request.getParameter("userid"));
	Post post = adminService.getPost(postid);
	post.setPost_status(Constants.POST_DELETE);
    adminService.updatePost(post);
	return new ModelAndView("redirect:/admin");
}

}
