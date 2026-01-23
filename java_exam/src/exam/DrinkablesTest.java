package exam;

/**
 * 음료수 자판기 실행
 */

public class DrinkablesTest {
	Drinkables bacchus = new Drinkables("박카스", 900, 15);
	Drinkables monster = new Drinkables("몬스터", 1500, 20);
	Drinkables hotSix = new Drinkables("핫식스", 1300, 10);
	Drinkables milkis = new Drinkables("밀키스", 1400, 5);

	VendingMachine vending = new VendingMachine(bacchus, monster, hotSix, milkis);

//	vending.printAllDrinkInfo();

}
