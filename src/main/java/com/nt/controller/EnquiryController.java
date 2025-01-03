package com.nt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nt.binding.CounsellorDTO;
import com.nt.binding.DashboardResponseDTO;
import com.nt.binding.EnquiryDTO;
import com.nt.binding.EnquiryFilterDTO;
import com.nt.service.EquiryService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class EnquiryController {
    @Autowired
	private EquiryService enqService;
    @GetMapping("/enquiry-page")
    private String loadEnqPage(Model model) {
    	EnquiryDTO enqDto=new EnquiryDTO();
    	model.addAttribute("enquiry", enqDto);
    	return "addEnquiry";
    }
    
    @GetMapping("/edit-enquiry")
    private String EnqPEditage(@RequestParam("enqId") Integer enqId,Model model) {
    	
    	EnquiryDTO enqDto=enqService.getEnquirya(enqId);
    	model.addAttribute("enquiry", enqDto);
    	return "addEnquiry";
    }
    
    @PostMapping("addEnquiry")
    public String addEnquiry(HttpServletRequest req,@ModelAttribute("enquiry")EnquiryDTO  enquiry,Model model) {
    	HttpSession session = req.getSession(false);
    	Integer counsellorId = (Integer) session.getAttribute("counsellorId");
    	
    	System.out.println("--counsellorId-----"+counsellorId);
    	boolean status = enqService.addEnquiry(enquiry, counsellorId);
    	if(status) {
    		model.addAttribute("smsg", "Enquiry Saved");
    	}
    	
    	else {
    		model.addAttribute("emsg", "Failed to save Enquiry ");
    	}
		return "addEnquiry";
    	
    }
    
    @GetMapping("/view-enquiries")
    private String loadEnqPage(HttpServletRequest request ,Model model) {
    	HttpSession session = request.getSession(false);
    	Integer counsellorId = (Integer) session.getAttribute("counsellorId");
    	List<EnquiryDTO> enquireList = enqService.getEnquiry(counsellorId);
    	model.addAttribute("enquires",enquireList);
    	EnquiryFilterDTO filterdto=new EnquiryFilterDTO();
    	model.addAttribute("filterdto",filterdto);
    	return "viewEnquiry";
    }
    
    @PostMapping("/filter-enquires")
    private String filterEnqPage(HttpServletRequest request ,@ModelAttribute("filterdto") EnquiryFilterDTO filterdto,Model model) {
    	System.out.println("filer---->");
    	HttpSession session = request.getSession(false);
    	Integer counsellorId = (Integer) session.getAttribute("counsellorId");
    	System.out.println("String"+filterdto.toString());
    	List<EnquiryDTO> enquireList = enqService.getFilterEnquiry(filterdto,counsellorId);
    	System.out.println("----enquireList------"+enquireList.size());
    	model.addAttribute("enquires",enquireList);
    	return "viewEnquiry";
    }
    
	@GetMapping("/dashboard")
	public String dashBoard(HttpServletRequest request,Model model) {
		HttpSession session = request.getSession(false);
    	Integer counsellorId = (Integer) session.getAttribute("counsellorId");
			System.out.println("--------"+counsellorId);  
			DashboardResponseDTO dashboardDto=	enqService.getDashboardInfo(counsellorId);
		model.addAttribute("dashboardDto", dashboardDto);
		return "dashboard";
		}
			

    
}
