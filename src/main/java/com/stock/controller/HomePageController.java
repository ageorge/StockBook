package com.stock.controller;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.stock.model.Post;
import com.stock.model.Reply;
import com.stock.model.Stock;
import com.stock.model.User;
import com.stock.service.PostService;
import com.stock.service.StockService;
import com.stock.service.UserService;
import com.stock.util.Constants;

/**
 * Controller class to handle user interactions 
 *  
 * @author anitageorge and bhavya
 *
 */
@Controller
@RequestMapping("/")
@SessionAttributes("user")
public class HomePageController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private StockService stockService;
	
	/**
	 * Instantiating user model attribute
	 * @return
	 */
	@ModelAttribute("user")
	public User getUser(){
		return new User();
	}
	
	/**
	 * Instantiating post model attribute
	 * @return
	 */
	@ModelAttribute("newpost") 
	public Post getPost() {
		return new Post();
	}
	
	/**
	 * Instantiating reply model attribute
	 * @return
	 */
	@ModelAttribute("reply") 
	public Reply getReply() {
		return new Reply();
	}
	
	/**
	 * Mapping for / page
	 * @param model
	 * @return
	 */
	@RequestMapping("/")
	public String index(Model model) {
		model.addAttribute(new User());
		return "index";
	}
	
	/**
	 * Mapping for home page
	 * @param model
	 * @return
	 */
	@RequestMapping("/home")
	public String home(Model model) {
		return "index";
	}
	
	/**
	 * Mapping for error page
	 * @param model
	 * @return
	 */
	@RequestMapping("/error")
	public String error(Model model) {
		return "error";
	}
	
	/**
	 * Mapping for login page
	 * 1. validate user login 
	 * 2. redirect based on object returned
	 * 	2.1 null - back to home page
	 * 	2.2 user object
	 * 		2.2.1 user account on hold - redirect to home page
	 * 		2.2.2 user account active - redirect to stock_home page based on user role
	 * @param user
	 * @param map
	 * @return
	 */
	@RequestMapping("/login")
	public String validateLogin(@ModelAttribute User user, ModelMap map) {
		System.out.println("user: " + user.getUser_name());
		String res = "";
		User user2 = userService.validateUser(user.getUser_name(), user.getPassword());
		if(user2 == null) {
			map.addAttribute("mssg", "Invalid User name/password.");
			res = "index";
		} else if(user2.getAccount_status().equals(Constants.ACCOUNT_HOLD) || user2.getAccount_status().equals(Constants.ACCOUNT_CANCEL)) {
			map.addAttribute("mssg", "Your account is yet to be approved or cancelled.");
			res = "index";
		} else {
			map.addAttribute("mssg", "Welcome to Stock Center");
			map.addAttribute("user", user2);
			map.addAttribute("stocks", stockService.getStocks());
			switch(user2.getRole()) {
			case Constants.USER_ROLE_TRADER:
				map.addAttribute("posts", postService.getPosts());
				map.addAttribute("users", userService.getUsers());
				res = "home";
				break;
			case Constants.USER_ROLE_EXPERT:
				map.addAttribute("posts", postService.getPosts());
				map.addAttribute("users", userService.getUsers());
				res = "home";
				break;
			case Constants.USER_ROLE_ADMIN:
				res = "redirect:/admin";
				break;
			case Constants.USER_ROLE_OFFICER:
				map.addAttribute("stocknames", stockService.getStockNames());
				res = "officer";
				break;
			case Constants.USER_ROLE_STAFF:
				map.addAttribute("posts", postService.getPosts());
				map.addAttribute("users", userService.getUsers());
				res = "home";
				break;
			}
			
		}
		return res;
	}
	
	/**
	 * Mapping for new user registration
	 * @param user
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String userRegistration(@ModelAttribute User user, ModelMap map) {
		user.setAccount_status(Constants.ACCOUNT_HOLD);
		userService.addUser(user);
		map.addAttribute("mssg", "You can login only after the admin approves.");
		return "index";
	}
	
	/**
	 * Mapping for the stock_home page
	 * @param map
	 * @param user
	 * @return
	 */
	@RequestMapping("/stockHome")
	public String mainHome(Model map, @SessionAttribute("user") User user) {
		map.addAttribute("mssg", "Welcome to Stock Center");
		map.addAttribute("user", user);
		map.addAttribute("stocks", stockService.getStocks());
		map.addAttribute("posts", postService.getPosts());
		map.addAttribute("users", userService.getUsers());
		System.out.println("stocks = " + stockService.getStocks());
		return "home";
	}
	
	/**
	 * Mapping for adding a new post 
	 * 
	 * @param newpost
	 * @param map
	 * @param stockid
	 * @param userid
	 * @param file
	 * @return
	 */
	@RequestMapping(value="/addpost", method=RequestMethod.POST)
	public String newPost(@ModelAttribute Post newpost, ModelMap map, @RequestParam(name="stockid") int stockid,@RequestParam(name = "userid") int userid, @RequestParam(value="file", required=false) MultipartFile file) {
		
		newpost.setPost_status(Constants.POST_NEW);
		
		Stock stock = stockService.getStock(stockid);
		newpost.setStock(stock);
		
		User user = userService.getUser(userid);
		newpost.setUser(user);
		
		System.out.println("Post: " + newpost);
		
		System.out.println("file details: " + file);
		if(file != null) {
			System.out.println("file details: " + file.getOriginalFilename());
			String filename = file.getOriginalFilename();
			newpost.setFilename(filename);
			try {
				newpost.setContents(file.getBytes());
			} catch (IOException e1) {
				e1.printStackTrace();
				return "error";
			}
		} 
		
		System.out.println("Post details: " + newpost);
		
		postService.addPost(newpost);
		map.addAttribute("mssg", "You have successfully added a new post. It will appear as soon as it is approved by the admin");
		map.addAttribute("user", newpost.getUser());
		map.addAttribute("posts", postService.getPosts());
		map.addAttribute("stocks", stockService.getStocks());
		map.addAttribute("users", userService.getUsers());
		
		return "home";
	}
	
	/**
	 * Mapping for replying to a post
	 * 
	 * @param reply
	 * @param map
	 * @param postid
	 * @param userid
	 * @return
	 */
	@RequestMapping(value="/replypost", method=RequestMethod.POST)
	public String replyToPost(@ModelAttribute Reply reply, ModelMap map, @RequestParam(name = "postid") int postid, @RequestParam(name = "userid") int userid) {
		
		System.out.println("Reply details: " + reply);
		
		User user = userService.getUser(userid);
		reply.setUser(user);
		
		Post parentPost = postService.getPost(postid);
		reply.setPost(parentPost);
		
		System.out.println("Reply: " + reply);
		
		postService.addReply(reply);
		map.addAttribute("mssg", "You have successfully replied to a post.");
		map.addAttribute("user", user);
		map.addAttribute("posts", postService.getPosts());
		map.addAttribute("stocks", stockService.getStocks());
		map.addAttribute("users", userService.getUsers());
		
		return "home";
	}
	
	/**
	 * Mapping for chat page
	 * 
	 * @param userid
	 * @param map
	 * @return
	 */
	@RequestMapping("/chat")
	public String chat(@RequestParam("userid") int userid, ModelMap map) {
		User user = userService.getUser(userid);
		map.addAttribute("user", user.getName());
		String ip = "";
		try {
			ip = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		System.out.println("Name = " + user + " \n ip = " + ip);
		map.addAttribute("ip",ip);
		map.addAttribute("img", "user");
		return "chat";
	}
	
	/**
	 * Mapping for the search page for officers
	 * 
	 * @param map
	 * @param stockname
	 * @return
	 */
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String search(ModelMap map, @RequestParam("stock_name") String stockname) {
		Set<User> users = new HashSet();
		
		List<Post> posts = postService.getAllPost();
		
		for(Post post:posts) {
			if(post.getStock().getStock_name().equals(stockname)) {
				User user = post.getUser();
				users.add(user);
			}
		}
		
		System.out.println("User list = " + users);
		
		map.addAttribute("userlist",users);
		map.addAttribute("stocks", stockService.getStocks());
		map.addAttribute("stocknames", stockService.getStockNames());
		
		return "officer";
	}
	
	/**
	 * Mapping for the officer home page
	 * 
	 * @param user
	 * @param map
	 * @return
	 */
	@RequestMapping("/officer")
	public String officerhome(@SessionAttribute User user, ModelMap map) {
		map.addAttribute("mssg", "Welcome to Stock Center");
		map.addAttribute("user", user);
		map.addAttribute("stocks", stockService.getStocks());
		map.addAttribute("stocknames", stockService.getStockNames());
		return "officer";
	}

}
