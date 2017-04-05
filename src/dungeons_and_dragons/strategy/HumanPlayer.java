/**
 * 
 */
package dungeons_and_dragons.strategy;

import dungeons_and_dragons.helper.LogHelper;
import dungeons_and_dragons.model.GamePlayModel;

/**
 * @author Mihir Pujara
 *
 */
public class HumanPlayer implements Strategy {

	@Override
	public void move(GamePlayModel gamePlayModel) {
		System.out.println(gamePlayModel.getCharacterModel().getCharacter_name() + " name");
		System.out.println("Human Player Move");
		try {
			synchronized (gamePlayModel.gameThread) {
				gamePlayModel.gameThread.wait();
			}
		} catch (InterruptedException e) {
			LogHelper.Log(LogHelper.TYPE_ERROR, e.getMessage());
		}
		try {
			synchronized (gamePlayModel.gameThread) {
				gamePlayModel.gameThread.wait();
			}
		} catch (InterruptedException e) {
			LogHelper.Log(LogHelper.TYPE_ERROR, e.getMessage());
		}
		try {
			synchronized (gamePlayModel.gameThread) {
				gamePlayModel.gameThread.wait();
			}
		} catch (InterruptedException e) {
			LogHelper.Log(LogHelper.TYPE_ERROR, e.getMessage());
		}
		System.out.println("after wait");
	}

	@Override
	public void attack(GamePlayModel gamePlayModel) {
		System.out.println("Human Player Attack");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void interact(GamePlayModel gamePlayModel) {
		System.out.println("Human Player Interact");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
