package com.capgemini.core.test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.junit.*;

import com.capgemini.core.beans.Customer;
import com.capgemini.core.beans.Wallet;
import com.capgemini.core.exceptions.InsufficientBalanceException;
import com.capgemini.core.exceptions.InvalidInputException;
import com.capgemini.core.service.WalletService;
import com.capgemini.core.service.WalletServiceImpl;

public class TestClass {

	static WalletService walletService;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception 
	{
		walletService = new WalletServiceImpl();
	
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreateAccount() throws InvalidInputException {
		Customer customer = new Customer();
		Wallet wallet = new Wallet();
		String name = "Aravinda";
		String number = "9652160279";
		BigDecimal amount = new BigDecimal(25000);
		
		customer.setName(name);
		customer.setMobileNo(number);
		wallet.setBalance(amount);
		customer.setWallet(wallet);
		
		customer = walletService.createAccount(name, number, amount);
		
		assertNotEquals(null, customer);
	}
	
	@Test
	public void testshowBalance() throws InvalidInputException {
		String number = "9652160279";
		Customer customer = walletService.showBalance(number);
		assertNotEquals(null,customer);
	}
	
	@Test(expected = InsufficientBalanceException.class)
	public void testWithdraw3() throws InvalidInputException, InsufficientBalanceException {
		String name = "mucha";
		String mobileNumber = "9177370364";
		BigDecimal balance = new BigDecimal(6000);
		
		walletService.createAccount(name, mobileNumber, balance);
		
		BigDecimal amount = new BigDecimal(8963240);
		
		walletService.withdrawAmount(mobileNumber, amount);
	}
	
	
	@Test(expected = InvalidInputException.class)
	public void testMobileNumber() throws InvalidInputException, InsufficientBalanceException {
		String name = "manasa";
		String mobileNumber = "96521602796";
		BigDecimal balance = new BigDecimal(9026);
		
		walletService.createAccount(name, mobileNumber, balance);
		
		BigDecimal amount = new BigDecimal(6532);
		
		walletService.withdrawAmount(mobileNumber, amount);
	}
	@Test(expected=InvalidInputException.class)
	public void testCreateAccount1() throws InvalidInputException 
	{
		walletService.createAccount(null, "9848241732", new BigDecimal(9653));
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testCreateAccount2() throws InvalidInputException 
	{
		walletService.createAccount("", "9635874621", new BigDecimal(9865));
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testCreateAccount3() throws InvalidInputException 
	{
		walletService.createAccount("aravi", "567", new BigDecimal(7962));
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testCreateAccount4() throws InvalidInputException 
	{
		walletService.createAccount("Aravinda", "", new BigDecimal(5692));
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testCreateAccount5() throws InvalidInputException 
	{
		walletService.createAccount("", "", new BigDecimal(6980));
	}
	
	@Test
	public void testCreateAccount6() throws InvalidInputException 
	{
		Customer customer1 = walletService.createAccount("srilakshmi", "9865472364", new BigDecimal(9658));
		Customer customer2 = null;
		
		assertNotEquals(customer2, customer1);
	}
	
	@Test(expected=InvalidInputException.class)
	public void testCreateAccount7() throws InvalidInputException 
	{
		walletService.createAccount("sai", "80088177", new BigDecimal(8989));
		walletService.createAccount("sony", "9652160278", new BigDecimal(12658));
	}
	@Test(expected=InvalidInputException.class)
	public void testWithdraw() throws InvalidInputException, InsufficientBalanceException {
		String mobileNumber = "9653241";
		BigDecimal amount = new BigDecimal(5690);
		walletService.withdrawAmount(mobileNumber, amount);
	}
	@Test(expected=NullPointerException.class)
	public void testWithdraw1() throws InvalidInputException, InsufficientBalanceException
	{
		walletService.withdrawAmount(null, null);
	}
	@Test(expected=InvalidInputException.class)
	public void testWithdraw2() throws InvalidInputException, InsufficientBalanceException
	{
		walletService.withdrawAmount("900000000", new BigDecimal(4560));
	}
		
	@Test(expected=NullPointerException.class)
	public void testWithdraw4() throws InvalidInputException, InsufficientBalanceException
	{
		walletService.withdrawAmount(null, new BigDecimal(6500));
	}
	@Test(expected=InvalidInputException.class)
	public void testWithdraw5() throws InvalidInputException, InsufficientBalanceException {
		String mobileNumber = "9652160";
		BigDecimal amount = new BigDecimal(-896);
		walletService.withdrawAmount(mobileNumber, amount);
	}
	@Test(expected=InvalidInputException.class)
	public void testDeposit() throws InvalidInputException,InsufficientBalanceException {
		
		String mobileNumber = "9652160";
		Customer customer1 = walletService.depositAmount(mobileNumber, new BigDecimal(3000));
		System.out.println(customer1.getWallet().getBalance());
		assertEquals(8000 , customer1.getWallet().getBalance());
	}
	@Test(expected=InvalidInputException.class)
	public void testDeposit1() throws InvalidInputException
	{
		walletService.depositAmount("900000000", new BigDecimal(2000));
	}
		
	@Test(expected=InvalidInputException.class)
	public void testDeposit2() throws InvalidInputException,InsufficientBalanceException
	{
		Customer customer=walletService.depositAmount("9963242422", new BigDecimal(2000));
		BigDecimal actual=customer.getWallet().getBalance();
		BigDecimal expected=new BigDecimal(8000);
		assertEquals(expected, actual);
	}
	
	@Test(expected=NullPointerException.class)
	public void testDeposit3() throws InvalidInputException
	{
		walletService.depositAmount(null, new BigDecimal(2000));
	}
	@Test(expected=NullPointerException.class)
	public void testDeposit4() throws InvalidInputException
	{
		String mobileNumber="9550045666";
		walletService.depositAmount(mobileNumber, null);
	}
	@Test(expected=NullPointerException.class)
	public void testDeposit5() throws InvalidInputException
	{
		walletService.depositAmount(null, null);
	}
	
	@Test(expected=InvalidInputException.class)
	public void testDeposit6() throws InvalidInputException
	{
		walletService.depositAmount("900000000", new BigDecimal(-2000));
	}
}
