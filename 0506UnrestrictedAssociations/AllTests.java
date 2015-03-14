import banking.money.*;
import banking.shares.*;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import stockMarket.ShareTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({ MoneyAmountTest.class, ShareTest.class,
	PurchaseTest.class, WalletTest.class })
public class AllTests {
}