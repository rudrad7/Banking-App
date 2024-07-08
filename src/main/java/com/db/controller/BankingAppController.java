package com.db.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.db.entity.Account;
import com.db.entity.Customer;
import com.db.entity.Transaction;
import com.db.service.AccountMgmService;
import com.db.service.CustomerService;
import com.db.service.TransactionMgmService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/bank")
public class BankingAppController {
	
	@Autowired 
	private CustomerService customerService;
	
	@Autowired
	private AccountMgmService accountMgmService;
	
	@Autowired
	private TransactionMgmService trasact;
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) 
	{
		
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
	
	@GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }
	
	@RequestMapping("/main")
	public String mainMenu(Model model, HttpServletRequest request) {
		return "account";
	}
	
	@GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        // Clear session and redirect to login page
        request.getSession().invalidate();
        return "redirect:/bank/login?logout";
    }
	
	@RequestMapping("/validateLogin")
	public String showIndexPage(@ModelAttribute("userName") String userName,
			@ModelAttribute("password") String password, Model model, HttpServletRequest request) {

		try {
			
			System.out.println("userName : " + userName + " password : " + password);
			Customer customer = customerService.findByUserNameAndPassword(userName, password);
			model.addAttribute("customer", customer);
			model.addAttribute("accountNumber", customer.getAccount().getAccountNumber());
			request.getSession().setAttribute("accountNumber", customer.getAccount().getAccountNumber());
			request.getSession().setAttribute("customer", customer);
			request.getSession().setAttribute("account", customer.getAccount());
		} catch (Exception e) {
			return "exception";
		}

		return "account";
	}
	
	
	@RequestMapping("/balance")
	public String balance(Model model, HttpServletRequest request) {

		try {
			Long accountNumbe = Long.valueOf(request.getSession().getAttribute("accountNumber").toString());
			System.out.println("accountNumber : " + accountNumbe);
			Double balance = accountMgmService.getBalance(accountNumbe);
			model.addAttribute("balance", balance);
			model.addAttribute("accountNumber", accountNumbe);
		} catch (Exception e) {
			return "exception";
		}

		return "balance";
	}
	
	@PostMapping("/withdraw")
	public String withdraw(@ModelAttribute("amount") Double amount, Model model, HttpServletRequest request) {

		try {
			System.out.println(request.getSession().getAttribute("accountNumber"));
			Long accountNumbe = Long.valueOf(request.getSession().getAttribute("accountNumber").toString());
			System.out.println("accountNumber : " + accountNumbe);
			Account account = accountMgmService.withdraw(accountNumbe, amount);
			
			request.getSession().setAttribute("account", account);
			
			model.addAttribute("transaction", "withdraw");
		} catch (Exception e) {
			return "exception";
		}

		return "success";
	}
	
	@PostMapping("/deposit")
	public String deposit(@ModelAttribute("amount") Double amount, Model model, HttpServletRequest request) {

		try {
			Long accountNumbe = Long.valueOf(request.getSession().getAttribute("accountNumber").toString());
			System.out.println("accountNumber : " + accountNumbe);
			Account account = accountMgmService.deposit(accountNumbe, amount);
			request.getSession().setAttribute("account", account);
			model.addAttribute("transaction", "Deposit");
		} catch (Exception e) {
			return "exception";
		}

		return "success";
	}
	
	@PostMapping("/transfer")
	public String transfer(@ModelAttribute("destAccNum") Long destAccNum, @ModelAttribute("amount") Double amount, Model model, HttpServletRequest request) {

		try {
			Long accountNumbe = Long.valueOf(request.getSession().getAttribute("accountNumber").toString());
			System.out.println("accountNumber : " + accountNumbe);
			System.out.println("destAccNum : "+ destAccNum + " amount : "+ amount);
			Account account = accountMgmService.transfer(accountNumbe,destAccNum, amount);
			request.getSession().setAttribute("account", account);
			model.addAttribute("transaction", "Transfered");
		
		} catch (Exception e) {
			return "exception";
		}

		return "success";
	}
	
	@GetMapping("/transactions")
	public String displayLastTenTransactions(Model model, HttpServletRequest request) {

		try {
			Long accountNumbe = Long.valueOf(request.getSession().getAttribute("accountNumber").toString());
			System.out.println("accountNumber in transactions : " + accountNumbe);
			List<Transaction> transactions = trasact.getTransactions(accountNumbe);
			model.addAttribute("transactionsList", transactions);
			request.getSession().setAttribute("transactionsList", transactions);
			request.setAttribute("transactionsList", transactions);
			System.out.println("accountNumber in all transactions : " + transactions);
		} catch (Exception e) {
			return "exception";
		}

		return "transactions";
	}
	
	@GetMapping("/withdraw")
	public String withdraw() {
		return "withdraw";
	}
	
	@GetMapping("/deposit")
	public String deposit() {
		return "deposit";
	}
	
	@GetMapping("/transfer")
	public String transfer() {
		return "transfer";
	}

}
