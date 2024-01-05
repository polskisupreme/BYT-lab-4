package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MoneyTest {
	Currency SEK, DKK, NOK, EUR;
	Money SEK100, EUR10, SEK200, EUR20, SEK0, EUR0, SEKn100;
	
	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
		SEK100 = new Money(10000, SEK);
		EUR10 = new Money(1000, EUR);
		SEK200 = new Money(20000, SEK);
		EUR20 = new Money(2000, EUR);
		SEK0 = new Money(0, SEK);
		EUR0 = new Money(0, EUR);
		SEKn100 = new Money(-10000, SEK);
	}

	@Test
	public void testGetAmount() {
		//	Ten test sprawdza, czy metoda getAmount() zwraca prawidłową wartość.
		//	W tym przypadku oczekuje się, że zwróci 10000 dla obiektu SEK100
		assertEquals("Testing getAmount", new Integer(10000), SEK100.getAmount());
	}

	@Test
	public void testGetCurrency() {
//		Ten test sprawdza, czy metoda getCurrency() zwraca prawidłowy obiekt Currency.
//		Oczekuje się, że zwróci obiekt SEK dla obiektu SEK100.
		assertEquals("Testing getCurrency", SEK, SEK100.getCurrency());
	}

	@Test
	public void testToString() {
//		Ten test sprawdza, czy metoda toString() zwraca prawidłowy łańcuch znaków.
//		Oczekuje się, że zwróci "10000 SEK" dla obiektu SEK100.
		assertEquals("Testing toString", "10000 SEK", SEK100.toString());
	}

	@Test
	public void testGlobalValue() {
//		Ten test sprawdza, czy metoda globalValue() zwraca prawidłową wartość.
//		Oczekuje się, że zwróci 1500 dla obiektu SEK100.
		assertEquals("Testing globalValue", new Integer(1500), SEK100.universalValue());
	}

	@Test
	public void testEqualsMoney() {
//		Ten test sprawdza, czy metoda equals(Money other) działa prawidłowo.
//		Oczekuje się, że zwróci true, gdy porównuje SEK100 z nowym obiektem Money
//		o wartości 10000 SEK.
		assertTrue("Testing equalsMoney", SEK100.equals(new Money(10000, SEK)));	
	}

	@Test
	public void testAdd() {
//		Ten test sprawdza, czy metoda add(Money other) działa prawidłowo.
//		Oczekuje się, że dodanie SEK100 do SEK100 da wynik równy SEK200.
		assertEquals("Testing add", SEK200.getAmount(), SEK100.add(SEK100).getAmount());
	}

	@Test
	public void testSub() {
//		Ten test sprawdza, czy metoda sub(Money other) działa prawidłowo.
//		Oczekuje się, że odjęcie SEK100 od SEK200 da wynik równy SEK100.
		assertEquals("Testing sub", SEK100.getAmount(), SEK200.sub(SEK100).getAmount());
	}

	@Test
	public void testIsZero() {
//		Ten test sprawdza, czy metoda isZero() działa prawidłowo.
//		Oczekuje się, że zwróci true dla obiektu SEK0 i false dla obiektu SEK100.
		assertTrue("Testing isZero", SEK0.isZero());
		assertFalse("Testing isZero", SEK100.isZero());
	}

	@Test
	public void testNegate() {
//		Ten test sprawdza, czy metoda negate() działa prawidłowo.
//		Oczekuje się, że zwróci obiekt SEKn100 dla obiektu SEK100.
		assertEquals("Testing negate", SEKn100.getAmount(), SEK100.negate().getAmount());
	}

	@Test
	public void testCompareTo() {
//		Ten test sprawdza, czy metoda compareTo(Object other) działa prawidłowo.
//		Oczekuje się, że zwróci 0, gdy porównuje SEK100 z nowym obiektem Money
//		o wartości 10000 SEK, wartość mniejszą od 0, gdy porównuje SEK100 z SEK200,
//		i wartość większą od 0, gdy porównuje SEK200 z SEK100.
		assertEquals("Testing compareTo", 0, SEK100.compareTo(new Money(10000, SEK)));
		assertTrue("Testing compareTo", SEK100.compareTo(SEK200) < 0);
		assertTrue("Testing compareTo", SEK200.compareTo(SEK100) > 0);
	}
}
