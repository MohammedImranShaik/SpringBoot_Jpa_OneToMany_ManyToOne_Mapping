package com.associations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.associations.entity.BankAccount;
import com.associations.entity.Customer;
import com.associations.repository.BankAccountRepository;
import com.associations.repository.CustomerRepository;

import jakarta.transaction.Transactional;

@SpringBootApplication
public class SpringBootJpaAssociationApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJpaAssociationApplication.class, args);
	}

	@Autowired
	private BankAccountRepository bankAccountRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	@Transactional
	public void run(String... args) throws Exception {

		insertMultipleBankAccountsWithOneCustomer();

		// Fetching the specific BankAccount Details along with Customer Details if we
		// needed
		/*
		 * BankAccount bankAccount = bankAccountRepository.findById(2).get();
		 * System.out.println("BankAccountDetails: " + bankAccount);
		 * System.out.println("Customer Details: " + bankAccount.getCustomer());
		 */
		  Customer customer = customerRepository.findById(1).get();
		  System.out.println("Customer Details: "+customer);
		  System.out.println("BankAcccount Details: "+customer.getBankAccountList());
		 
	}

	private void insertMultipleBankAccountsWithOneCustomer() {
		
		System.out.println("___Creating Mohammed Imran Shaik Details_____");
		
		customerMohammedImranShaikInsertion();
		
		
		System.out.println("___Creating Abdul Rasheed Shaik Details_____");
		
		customerAbdulRasheedShaikInsertion();
		
	}

	private void customerAbdulRasheedShaikInsertion() {
		//Customer-2 
		Customer customer2 = new Customer();
		customer2.setName("Abdul Rasheed Shaik");
		
		//BankAccount-2
		BankAccount bankAccount4 = new BankAccount();
		bankAccount4.setName("HDB Bank");
		bankAccount4.setNumber("444444");
		bankAccount4.setCustomer(customer2);
		
		bankAccountRepository.save(bankAccount4);
		
		//BankAccount-2
		BankAccount bankAccount5 = new BankAccount();
		bankAccount5.setName("Union Bank");
		bankAccount5.setNumber("555555");
		bankAccount5.setCustomer(customer2);
		
		bankAccountRepository.save(bankAccount5);
	}

	private void customerMohammedImranShaikInsertion() {
		// Customer-1 starting the execution
		Customer customer1 = new Customer();
		customer1.setName("Mohammed Imran Shaik");
		// customerRepository.save(customer1);

		// Bank_Account-1
		BankAccount bankAccount1 = new BankAccount();
		bankAccount1.setName("SBI Bank");
		bankAccount1.setNumber("111111");
		bankAccount1.setCustomer(customer1); // Transaction starts hear and ends hear

		bankAccountRepository.save(bankAccount1);

		BankAccount bankAccount2 = new BankAccount();
		bankAccount2.setName("ICIC Bank");
		bankAccount2.setNumber("22222");
		bankAccount2.setCustomer(customer1);

		bankAccountRepository.save(bankAccount2);

		BankAccount bankAccount3 = new BankAccount();
		bankAccount3.setName("Axis Bank");
		bankAccount3.setNumber("333333");
		bankAccount3.setCustomer(customer1);

		bankAccountRepository.save(bankAccount3);
		
		//customer-1 end execution
	}

}
