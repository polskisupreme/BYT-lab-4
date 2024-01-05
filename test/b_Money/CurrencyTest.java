package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CurrencyTest {
	Currency SEK, DKK, NOK, EUR, USD, GBP, JPY;
	
	@Before
	public void setUp() throws Exception {
		/* Setup currencies with exchange rates */
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
		USD = new Currency("USD", 1.0); 
		GBP = new Currency("GBP", 1.3);
		JPY = new Currency("JPY", 0.009);
	}

	@Test
	public void testGetName() {
//		Ten test sprawdza, czy metoda getName() zwraca prawidłową nazwę waluty.
//		Dla obiektu SEK oczekuje się, że zwróci "SEK", dla DKK - "DKK", a dla EUR - "EUR".
		assertEquals("Testing getName", "SEK", SEK.getName());
		assertEquals("Testing getName", "DKK", DKK.getName());
		assertEquals("Testing getName", "EUR", EUR.getName());
	}
	
	@Test
	public void testGetRate() {
//		Ten test sprawdza, czy metoda getRate() zwraca prawidłowy kurs waluty.
//		Dla obiektu SEK oczekuje się, że zwróci 0.15, dla DKK - 0.20, a dla EUR - 1.5.
		assertEquals("Testing getRate", new Double(0.15), SEK.getRate());
		assertEquals("Testing getRate", new Double(0.20), DKK.getRate());
		assertEquals("Testing getRate", new Double(1.5), EUR.getRate());
	}
	
	@Test
	public void testSetRate() {
//		Ten test sprawdza, czy metoda setRate(Double rate) prawidłowo ustawia kurs waluty.
//		W teście zmieniamy kurs SEK na 0.10 i sprawdzamy, czy metoda getRate() zwraca nową wartość.
		SEK.setRate(new Double(0.10));
		assertEquals("Testing setRate", new Double(0.10), SEK.getRate());
	}
	
	@Test
	public void testGlobalValue() {
//		Ten test sprawdza, czy metoda universalValue(Integer amount) prawidłowo przelicza kwotę na “uniwersalną” walutę.
//		Dla obiektu SEK oczekuje się, że 10000 SEK przeliczy na 1500, dla DKK - 10000 DKK na 2000, a dla EUR - 10000 EUR na 15000.
		assertEquals("Testing globalValue", new Integer(1500), SEK.universalValue(10000));
		assertEquals("Testing globalValue", new Integer(2000), DKK.universalValue(10000));
		assertEquals("Testing globalValue", new Integer(15000), EUR.universalValue(10000));
	}
	
	@Test
	public void testValueInThisCurrency() {
//		Ten test sprawdza, czy metoda valueInThisCurrency(Integer amount, Currency othercurrency)
//		prawidłowo przelicza kwotę z innej waluty na tę walutę. Dla każdej waluty oczekuje się,
//		że 1500, 2000 lub 15000 w “uniwersalnej” walucie przeliczy na 10000 w tej walucie.
		String msg = "wrong ValueInThisCurrency";

		Integer valueInEurFromSek = EUR.valueInThisCurrency(100, SEK);

        assertTrue(msg, valueInEurFromSek.equals(10));
	}

}
