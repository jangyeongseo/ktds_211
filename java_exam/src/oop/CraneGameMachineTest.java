package oop;

public class CraneGameMachineTest {

	public static void main(String[] args) {
		CraneGameMachine game = new CraneGameMachine();
		game.isInsertCoin = true;
		game.dolls = 20;

		game.isSertCoin();
		int doGame = game.doGame();

		System.out.println("isInsertCoin" + game.isInsertCoin);
		System.out.println("남은 인형개수 : " + game.dolls);
		System.out.println("뽑은 이형개수 : " + doGame);
	}

}
