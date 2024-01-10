package com.springboot.mydairy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.mydairy.entity.Entry;
import com.springboot.mydairy.entity.User;
import com.springboot.mydairy.service.EntryService;
import com.springboot.mydairy.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeContoller {
	
	@Autowired
	private UserService userservice;
	@Autowired
	HttpSession session;
	
	public UserService getUserService() {
		return userservice;
	}
	
	public void setUserService(UserService userservice) {
		this.userservice=userservice;
	}
	
	@Autowired
	private EntryService entryservice;
	
	public EntryService getEntryService() {
		return entryservice;
	}
	
	public void setEntryService(EntryService entryservice) {
		this.entryservice=entryservice;
	}
	
	@GetMapping("home")
	public String homepage()
	{
		
		String model = new String("loginpage");
		
		
		
		return model;
	}
	
	@GetMapping("register")
	public String registrationpage()
	{
		
		String model = new String("registrationpage");
		
		
		
		return model;
	}
	
	@PostMapping(value="saveuser")
	public String saveuser(@ModelAttribute("user") User user)
	{
		//code to save the user details in the database
		
		
        String model = new String("registersuccess");
		
        userservice.saveUser(user);	
        
        
		return model;
		
	}
	
	
	@PostMapping(value="/authenticate")
	public String authenticateuser(@ModelAttribute("user") User user,Model model)
	{
		String viewname = "loginpage";
		
		User user1 = userservice.findByUsername(user.getUsername());
		
		if(user1!=null && user.getPassword().equals(user1.getPassword()))
		{
			viewname="userhomepage";
			model.addAttribute("user", user1);
			
			session.setAttribute("user", user1);
			
			List<Entry> entries=null;
			
			try {
				entries=entryservice.findByUserid(user1.getId());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			model.addAttribute("entrieslist", entries);
			
		}
		
		
		return viewname;
	}
	
	@GetMapping("addentry")
	public String addentry()
	{
		String model=new String("addentryform");
		
		
		return model;
	}
	
	
	@PostMapping("saveentry")
	public String saveentry(@ModelAttribute("entry") Entry entry, Model model)
	{
		String viewname="userhomepage";
		
		
		entryservice.saveEntry(entry);
		
		User user1=(User)session.getAttribute("user");
		
		List<Entry> entries=null;
		
		try {
			entries=entryservice.findByUserid(user1.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.addAttribute("entrieslist", entries);
		
		return viewname;
	}
	
	
	@GetMapping("viewentry")
	public String viewentry(@RequestParam("id") int id, Model model)
	{
		String viewname="displayentry";
		
		Entry entry = entryservice.findById(id);
		
		model.addAttribute("entry", entry);
		
		return viewname;
	}
	
	
	@GetMapping("userhome")
	public String userhomepage(Model model)
	{
		
		String viewname="userhomepage";
		User user1=(User)session.getAttribute("user");
		
		List<Entry> entries=null;
		
		try {
			entries=entryservice.findByUserid(user1.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.addAttribute("entrieslist", entries);
		
		return viewname;
	}
	
	@GetMapping("updateentry")
	public String updateentry(@RequestParam("id") int id, Model model)
	{
		String viewname="displayupdateentry";
		
		Entry entry = entryservice.findById(id);
		
		model.addAttribute("entry", entry);
		
		User user1=(User)session.getAttribute("user");
		
		if(user1==null)
			viewname="loginpage";
		
		return viewname;
	}
	
	@PostMapping("processentryupdate")
	public String processentryupdate(@ModelAttribute("entry") Entry entry, Model model)
	{
		String viewname="userhomepage";
		
		
		entryservice.updateEntry(entry);
		
		User user1=(User)session.getAttribute("user");
		
		List<Entry> entries=null;
		
		try {
			entries=entryservice.findByUserid(user1.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.addAttribute("entrieslist", entries);
		
		return viewname;
	}
	
	
	@GetMapping("deleteentry")
	public String deleteentry(@RequestParam("id") int id, Model model)
	{
		String viewname="userhomepage";
		
         User user1=(User)session.getAttribute("user");
		
		
		Entry entry = entryservice.findById(id);
		
		if(user1==null)
			viewname="loginpage";
		else
			entryservice.deleteEntry(entry);
		
		
        List<Entry> entries=null;
		
		try {
			entries=entryservice.findByUserid(user1.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.addAttribute("entrieslist", entries);
		
		
		
		
		return viewname;
	}
	
	
	@GetMapping("signout")
	public String signout()
	{
		
		String model = new String("loginpage");
		
		session.invalidate();
		
		
		return model;
	}
	
	
	
	
	
	
	

}
