package tests;

import java.util.Random;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class IcdDaoTest {
		private Random random;
		
		@BeforeClass
		public void setUp() {
			this.random = new Random();
		}
		
		@Test
		public void icdErstellenTest_valid() throws InterruptedException {
			simulateWork(2000);
		}
		
		@Test
		public void icdErstellenTest_invalid() throws InterruptedException {
			simulateWork(1000);
		}
		
		@Test
		public void icdUeberpruefenTest_valid() throws InterruptedException {
			simulateWork(1000);
		}
		
		@Test
		public void icdUeberpruefenTest_invalid() throws InterruptedException {
			simulateWork(1000);
		}
		
		@Test
		public void icdTest_valid() throws InterruptedException {
			simulateWork(1500);
		}
		
		@Test
		public void icdTest_invalid() throws InterruptedException {
			simulateWork(1000);
		}

		private void simulateWork(int upperbound) throws InterruptedException {
			int sleepTime = random.nextInt(upperbound);
			System.out.println("\nSleeping for " + sleepTime + "\n");
			Thread.sleep(sleepTime);
		}
}
