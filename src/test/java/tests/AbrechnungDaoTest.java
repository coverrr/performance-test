package tests;

import java.util.Random;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AbrechnungDaoTest {
	private Random random;
	
	@BeforeClass
	public void setUp() {
		this.random = new Random();
	}
	
	@Test
	public void abrechnungErstellenTest_valid() throws InterruptedException {
		simulateWork(2000);
	}
	
	@Test
	public void abrechnungErstellenTest_invalid() throws InterruptedException {
		simulateWork(1000);
	}
	
	@Test
	public void validierenTest_valid() throws InterruptedException {
		simulateWork(1000);
	}
	
	@Test
	public void validierenTest_invalid() throws InterruptedException {
		simulateWork(1000);
	}
	
	@Test
	public void abrechnungLoeschenTest_valid() throws InterruptedException {
		simulateWork(1500);
	}
	
	@Test
	public void abrechnungLoeschenTest_invalid() throws InterruptedException {
		simulateWork(1000);
	}

	private void simulateWork(int upperbound) throws InterruptedException {
		Thread.sleep(random.nextInt(upperbound));
	}
}
