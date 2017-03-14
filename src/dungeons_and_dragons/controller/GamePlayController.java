package dungeons_and_dragons.controller;

import dungeons_and_dragons.model.CampaignModel;
import dungeons_and_dragons.model.CharacterModel;
import dungeons_and_dragons.model.GamePlayModel;
import dungeons_and_dragons.view.GamePlayView;

/**
 * The GamePlayController translates the user's interactions with the
 * GamePlayView into actions that the GamePlayModel will perform that may use
 * some additional/changed data gathered in a user-interactive view.
 * 
 * @author Tejas Sadrani & Urmil Kansara
 */
public class GamePlayController {

	
	/**
	 * This creates new game play model which is being observed
	 * 
	 * @type GameMapModel
	 */
	GamePlayModel gamePlayModel;
	
	/**
	 * This create game play view object which is an observer
	 * 
	 * @type MapGridView
	 */
	GamePlayView gamePlayView;
	
	/**
	 * This creates new campaign model which is being observed
	 * 
	 * @type GameMapModel
	 */
	CampaignModel campaignModel;
	
	/**
	 * This create a new character model object which is an observer
	 * 
	 * @type MapGridView
	 */
	CharacterModel charachterModel;

	/**
	 * Default constructor of Map Grid controller
	 * <p>
	 * MapGrid model and view are initialized Also view is binded to observer.
	 * <p>
	 * All the events of view are registered in constructor
	 */
	public GamePlayController() {
		
		this.gamePlayModel = new GamePlayModel();
		this.campaignModel = gamePlayModel.getCampaignModel();
		this.charachterModel = gamePlayModel.getCharacterModel();
		
		this.gamePlayView = new GamePlayView(this.campaignModel,this.charachterModel);

		this.gamePlayModel.addObserver(gamePlayView);
		this.gamePlayView.setListener(this);
	}
	
	
}