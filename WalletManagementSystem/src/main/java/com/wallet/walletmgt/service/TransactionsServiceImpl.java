package com.wallet.walletmgt.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.wallet.walletmgt.entity.Transactions;
import com.wallet.walletmgt.entity.User;
import com.wallet.walletmgt.messege.Messege;
import com.wallet.walletmgt.repository.TransactionsRepository;
import com.wallet.walletmgt.repository.UserRepository;

@Service
public class TransactionsServiceImpl {
	@Autowired
	private UserRepository userrepo;

	@Autowired
	private TransactionsRepository transcactionrepo;

	@Autowired
	private UserDetailsServiceImpl userservice;

	public String transactionHistory(Model model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User us = userrepo.getUserByUsername(((UserDetails) principal).getUsername());
		List<Transactions> tr = transcactionrepo.findByUserId(us.getId());
		model.addAttribute("transactionhistory", tr);
		return "transactions";
	}

	public String balence(HttpSession session) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User us = userrepo.getUserByUsername(((UserDetails) principal).getUsername());
		List<Transactions> tr = transcactionrepo.findByUserId(us.getId());
		int temp = 0, max = 0;
		for (int i = 0; i < tr.size(); i++) {
			temp = tr.get(i).getId();
			if (max < temp) {
				max = temp;
			}

		}
		Transactions tempTransactions = transcactionrepo.getById(max);
		double balence = tempTransactions.getBalence();

		String str = "Balence is ₹" + balence;
		Messege msg = new Messege();
		msg.setContent(str);
		session.setAttribute("messege5", msg);
		return "redirect:/";
	}

	public String debit(double debit, HttpSession session) {
		if (debit <= 0) {
			String str = "invalide amount !";
			Messege msg = new Messege();
			msg.setContent(str);
			session.setAttribute("messege", msg);
			return "withdrawMoney";
		} else {

			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			User us = userrepo.getUserByUsername(((UserDetails) principal).getUsername());
			System.out.println(us.toString());
			// int id =transcactionrepo.getUserIdByLetestDate(us.getId());
			// Transactions tempTransactions = transcactionrepo.getById(us.getId());
			List<Transactions> tr = transcactionrepo.findByUserId(us.getId());
			int temp = 0, max = 0;
			for (int i = 0; i < tr.size(); i++) {
				temp = tr.get(i).getId();
				if (max < temp) {
					max = temp;
				}

			}
			Transactions tempTransactions = transcactionrepo.getById(max);
			System.out.println(tr.size());
			System.out.println(tr.size());
			Transactions transactions = new Transactions();
			transactions.setUserId(us.getId());
			LocalDate lc = LocalDate.now();
			Date date = Date.valueOf(lc);
			transactions.setDate(date);
			double balence = tempTransactions.getBalence();

			transactions.setDebit(debit);
			transactions.setCredit(0);
			if (debit <= balence) {
				balence = balence - debit;
				transactions.setBalence(balence);
				transcactionrepo.save(transactions);

				String str = "Amount " + "₹" + debit + " is Debitted form  Wallet";
				Messege msg = new Messege();
				msg.setContent(str);
				session.setAttribute("messege4", msg);
				return "redirect:/";
			} else {
				String str = "Enter the valid amount !";
				Messege msg = new Messege();
				msg.setContent(str);
				session.setAttribute("messege2", msg);
				return "withdrawMoney";
			}

		}

	}

	public String credit(double credit, HttpSession session) {
		if (credit <= 0) {
			String str = "₹" + credit + " is invalide amount !";

			Messege msg = new Messege();
			msg.setContent(str);
			session.setAttribute("messege", msg);
			return "addMoney";
		} else {

			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			User us = userrepo.getUserByUsername(((UserDetails) principal).getUsername());

			List<Transactions> tr = transcactionrepo.findByUserId(us.getId());
			int temp = 0, max = 0;
			for (int i = 0; i < tr.size(); i++) {
				temp = tr.get(i).getId();
				if (max < temp) {
					max = temp;
				}

			}
			Transactions tempTransactions = transcactionrepo.getById(max);
			Transactions transactions = new Transactions();
			transactions.setUserId(us.getId());

			LocalDate lc = LocalDate.now();
			Date date = Date.valueOf(lc);
			transactions.setDate(date);
			double balence = tempTransactions.getBalence();

			transactions.setCredit(credit);
			balence = balence + credit;
			transactions.setBalence(balence);
			transactions.setDebit(0);

			transcactionrepo.save(transactions);
			String str = "Amount " + "₹" + credit + " is Creditted to Wallet";
			Messege msg = new Messege();
			msg.setContent(str);
			session.setAttribute("messege3", msg);
			return "redirect:/";
		}
	}

	public String withdrawMoneyW(Model model) {
		Transactions transactions = new Transactions();
		model.addAttribute("transactions", transactions);
		return "withdrawMoney";
	}

	public String addMoneyW(Model model) {
		Transactions transactions = new Transactions();
		model.addAttribute("transactions", transactions);
		return "addMoney";
	}

	public String saveUser(User user) {
		userservice.userSave(user);

		return "redirect:/";
	}

	public String addBookForm(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "new_user";
	}

	public String viewHomePage(Model model, HttpServletRequest request, HttpSession session) {
		model.addAttribute("users", userrepo.findAll());
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		boolean hasUserRole = authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ADMIN"));

		if (hasUserRole) {
			return "adminindex";

		} else {
			return "userindex";
		}

	}

}
