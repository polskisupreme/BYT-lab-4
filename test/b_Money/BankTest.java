package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BankTest {
	Currency SEK, DKK;
	Bank SweBank, Nordea, DanskeBank;
	
	@Before
	public void setUp() throws Exception {
		DKK = new Currency("DKK", 0.20);
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		Nordea = new Bank("Nordea", SEK);
		DanskeBank = new Bank("DanskeBank", DKK);
		SweBank.openAccount("Ulrika");
		SweBank.openAccount("Bob");
		Nordea.openAccount("Bob");
		DanskeBank.openAccount("Gertrud");
	}

	@Test
	public void testGetName() {
//		Ta metoda testuje, czy metoda getName() klasy Bank zwraca poprawną nazwę banku.
		assertEquals("Testing getName", "SweBank", SweBank.getName());
		assertEquals("Testing getName", "Nordea", Nordea.getName());
		assertEquals("Testing getName", "DanskeBank", DanskeBank.getName());
	}

	@Test
	public void testGetCurrency() {
//		Ta metoda testuje, czy metoda getCurrency() klasy Bank zwraca poprawną walutę, która została ustawiona dla danego banku.
		assertEquals("Testing getCurrency", SEK, SweBank.getCurrency());
		assertEquals("Testing getCurrency", SEK, Nordea.getCurrency());
		assertEquals("Testing getCurrency", DKK, DanskeBank.getCurrency());
	}

	@Test
	public void testOpenAccount() throws AccountExistsException, AccountDoesNotExistException {
//		Ta metoda testuje, czy metoda openAccount() klasy Bank poprawnie otwiera nowe konto.
//		Dodatkowo sprawdza, czy można na to konto wpłacić pieniądze.
		SweBank.openAccount("Test");
		SweBank.deposit("Test", new Money(100, SEK));
	}

	@Test
	public void testDeposit() throws AccountDoesNotExistException { //za pierwszym Failed
//		Ta metoda testuje, czy metoda deposit() klasy Bank poprawnie wpłaca określoną kwotę na konto.
//		Sprawdza również, czy saldo na koncie jest poprawnie aktualizowane.
		SweBank.deposit("Ulrika", new Money(1000, SEK));
		assertEquals("Testing deposit", new Integer(1000), SweBank.getBalance("Ulrika"));
	}

	@Test
	public void testWithdraw() throws AccountDoesNotExistException { //za pierwszym Failed
//		Ta metoda testuje, czy metoda withdraw() klasy Bank poprawnie wypłaca określoną kwotę z konta.
//		Sprawdza również, czy saldo na koncie jest poprawnie aktualizowane.
		SweBank.deposit("Bob",new Money(20000,SEK));
		SweBank.withdraw("Bob",new Money(20000,SEK));
		assertEquals(Integer.valueOf(0),SweBank.getBalance("Bob"));
	}
	
	@Test
	public void testGetBalance() throws AccountDoesNotExistException, Exception { //za pierwszym Failed
//		Ta metoda testuje, czy metoda getBalance() klasy Bank zwraca poprawne saldo dla danego konta.
		assertEquals(Integer.valueOf(0),SweBank.getBalance("Bob"));
		assertEquals(Integer.valueOf(0),SweBank.getBalance("Ulrika"));
	}
	
	@Test
	public void testTransfer() throws AccountDoesNotExistException { //za pierwszym Failed
//		Ta metoda testuje, czy metoda transfer() klasy Bank poprawnie przekazuje określoną kwotę
//		z jednego konta na drugie. Sprawdza również, czy salda na obu kontach są poprawnie aktualizowane.
		SweBank.transfer("Bob",Nordea,"Bob",new Money(20000,SEK));
		assertEquals(Integer.valueOf(-20000),SweBank.getBalance("Bob"));
		assertEquals(Integer.valueOf(20000),Nordea.getBalance("Bob"));

		SweBank.transfer("Ulrika",DanskeBank,"Gertrud",new Money(20000,SEK));
		assertEquals(Integer.valueOf(-20000),SweBank.getBalance("Ulrika"));
		assertEquals(Integer.valueOf(15000),DanskeBank.getBalance("Gertrud"));

	}
	
	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
//		Ta metoda testuje, czy metoda addTimedPayment() klasy Bank poprawnie dodaje zaplanowaną płatność.
//		Sprawdza również, czy metoda tick() poprawnie aktualizuje salda na obu kontach po wykonaniu
//		zaplanowanej płatności. Na koniec sprawdza, czy metoda removeTimedPayment() poprawnie usuwa zaplanowaną płatność.
				int transferAmountBob = SweBank.getBalance("Bob");
				int transferAmountBobNordea = Nordea.getBalance("Bob");

				SweBank.addTimedPayment("Bob", "Test", 1, 1, new Money(100, SEK), Nordea, "Bob");
				SweBank.tick();

				assertEquals(transferAmountBob, SweBank.getBalance("Bob").intValue());
				assertEquals(transferAmountBobNordea, Nordea.getBalance("Bob").intValue());

				SweBank.tick();

				assertEquals(transferAmountBob - 100, SweBank.getBalance("Bob").intValue());
				assertEquals(transferAmountBobNordea + 100, Nordea.getBalance("Bob").intValue());

				SweBank.tick();
				SweBank.tick();

				assertEquals(transferAmountBob - 200, SweBank.getBalance("Bob").intValue());
				assertEquals(transferAmountBobNordea + 200, Nordea.getBalance("Bob").intValue());

				SweBank.removeTimedPayment("Bob", "Test");

				SweBank.tick();
				SweBank.tick();
				SweBank.tick();
				SweBank.tick();

				assertEquals(transferAmountBob - 200, SweBank.getBalance("Bob").intValue());
				assertEquals(transferAmountBobNordea + 200, Nordea.getBalance("Bob").intValue());
	}
}
