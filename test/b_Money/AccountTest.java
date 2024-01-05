package b_Money;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AccountTest {
	Currency SEK, DKK;
	Bank Nordea;
	Bank DanskeBank;
	Bank SweBank;
	Account testAccount;
	
	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		SweBank.openAccount("Alice");
		testAccount = new Account("Hans", SEK);
		testAccount.deposit(new Money(10000000, SEK));

		SweBank.deposit("Alice", new Money(1000000, SEK));
	}
	
	@Test
	public void testAddRemoveTimedPayment() {  //za pierwszym Failed
//		Ta metoda testuje, czy metoda addTimedPayment() klasy Account poprawnie dodaje zaplanowaną płatność do konta,
//		a następnie czy metoda removeTimedPayment() poprawnie usuwa tę płatność.
				testAccount.addTimedPayment("Test", 1, 1, new Money(100, SEK), SweBank, "Alice");
				assertTrue(testAccount.timedPaymentExists("Test"));

				testAccount.removeTimedPayment("Test");
				assertFalse(testAccount.timedPaymentExists("Test"));
			}
	
	@Test
	public void testTimedPayment() throws AccountDoesNotExistException { //za pierwszym Failed
//		Ta metoda testuje, czy metoda timedPaymentExists() klasy Account poprawnie identyfikuje,
//		czy dana zaplanowana płatność istnieje na koncie.
		assertFalse(testAccount.timedPaymentExists("test"));
	}

	@Test
	public void testAddWithdraw() { //za pierwszym Failed
//		Ta metoda testuje, czy metody deposit() i withdraw() klasy Account poprawnie dodają i wypłacają środki z konta.
//		Sprawdza również, czy saldo na koncie jest poprawnie aktualizowane po każdej operacji.
		int preTransferAmountTestaccount = testAccount.getBalance().getAmount();

		testAccount.deposit(new Money(200, SEK));
		assertEquals(preTransferAmountTestaccount + 200, testAccount.getBalance().getAmount().intValue());

		testAccount.withdraw(new Money(200, SEK));
		assertEquals(preTransferAmountTestaccount, testAccount.getBalance().getAmount().intValue());
	}
	
	@Test
	public void testGetBalance() { //za pierwszym Failed
//		Ta metoda testuje, czy metoda getBalance() klasy Account zwraca poprawne saldo dla danego konta.
		assertEquals(10000000, testAccount.getBalance().getAmount().intValue());
	}
}
