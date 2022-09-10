package com.wallet.walletmgt.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.wallet.walletmgt.entity.User;
import com.wallet.walletmgt.service.TransactionsServiceImpl;

@Controller
public class HomeController {

	@Autowired
	private TransactionsServiceImpl transactionsService;

	@GetMapping(value = "/")
	public String viewHomePage(Model model, HttpServletRequest request, HttpSession session) {

		return transactionsService.viewHomePage(model, request, session);
	}

	@GetMapping(value = "/user/add")
	public String addBookForm(Model model) {

		return transactionsService.addBookForm(model);
	}

	@PostMapping(value = "/user/save")
	public String saveUser(@ModelAttribute("user") User user) {

		return transactionsService.saveUser(user);
	}

	@GetMapping(value = "/credit")
	public String addMoneyW(Model model) {

		return transactionsService.addMoneyW(model);
	}

	@GetMapping(value = "/debit")
	public String withdrawMoneyW(Model model) {

		return transactionsService.withdrawMoneyW(model);
	}

	@PostMapping(value = "/transactions/credit")
	public String addMoney(@RequestParam(value = "credit", required = false) double credit, HttpSession session) {

		return transactionsService.credit(credit, session);
	}

	@PostMapping(value = "/transactions/debit")
	public String withdrawMoney(@RequestParam(value = "debit") double debit, HttpSession session) {

		return transactionsService.debit(debit, session);

	}

	@GetMapping(value = "/balence")
	public String balence(HttpSession session) {

		return transactionsService.balence(session);
	}

	@GetMapping("/transactions/history")
	public String transactionsHistory(Model model) {

		return transactionsService.transactionHistory(model);

	}
}
