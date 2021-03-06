package dungeons_and_dragons.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.google.gson.JsonSyntaxException;

import dungeons_and_dragons.helper.FileHelper;
import dungeons_and_dragons.helper.LogHelper;
import dungeons_and_dragons.model.CampaignModel;
import dungeons_and_dragons.model.GameMapModel;
import dungeons_and_dragons.view.CampaignView;

/**
 * The CampaignController translates the user's interactions with the
 * CampaignView into actions that the CampaignModel will perform that may use
 * some additional/changed data gathered in a user-interactive view.
 * 
 * @author Tejas Sadrani
 */
public class CampaignController implements ActionListener {

	/**
	 * Creates an object for Campaign Model class
	 * 
	 * @type CampaignModel
	 */
	private CampaignModel campaignModel;

	/**
	 * Creates an object for Campaign View class
	 * 
	 * @type CampaignView
	 */
	private CampaignView campaignView;

	/**
	 * Creates an object for game map model class
	 * 
	 * @type GameMapModel
	 */
	private GameMapModel map_model = new GameMapModel();

	/**
	 * Creates an array list of object named gameMapModel as an input list used
	 * by comboBox in the CampaignView
	 * 
	 * @type ArrayList<GameMapModel>
	 */
	private ArrayList<GameMapModel> input_map_list;

	/**
	 * Creates an array list of object named gameMapModel as an output list that
	 * is used to store campaigns is displayed as a JList in CampaignView
	 * 
	 * @type CampaignView
	 */
	private ArrayList<GameMapModel> output_map_list;

	/**
	 * Constructor of Campaign Controller used to construct campaign models and
	 * campaign views view is binded to observer
	 */
	public CampaignController() {

		// creating campaign model
		this.campaignModel = new CampaignModel();

		this.input_map_list = new ArrayList<GameMapModel>();
		this.output_map_list = new ArrayList<GameMapModel>();

		try {
			this.input_map_list = FileHelper.getMaps();
		} catch (JsonSyntaxException | IOException e) {
			LogHelper.Log(LogHelper.TYPE_ERROR, e.getMessage());
		}

		this.campaignModel.setInput_map_list(input_map_list);

		// creating campaign view
		this.campaignView = new CampaignView(input_map_list);

		this.campaignModel.addObserver(campaignView);

		// set listener
		this.campaignView.setActionListener(this);

		// show game view
		this.campaignView.update_button.setVisible(false);
		this.campaignView.setVisible(true);

	}

	/**
	 * Constructor used for viewing and editing the contents of a campaign in a
	 * manage campaign controller
	 * 
	 * @param campaign
	 * @param string
	 */
	public CampaignController(CampaignModel campaign, String screen_type) {

		if (screen_type == "view") {

			// creating campaign model
			this.campaignModel = campaign;

			this.input_map_list = this.campaignModel.getInput_map_list();
			this.output_map_list = this.campaignModel.getOutput_map_list();

			// Creating campaign view and initializing the variables
			this.campaignView = new CampaignView(this.input_map_list);

			// Set the name of the campaign in campaign name label
			this.campaignView.campaign_name_label.setText(this.campaignModel.getCampaign_name());
			this.campaignView.campaign_name_label.setBounds(225, 10, 100, 25);
			this.campaignView.campaign_name_label.setForeground(Color.BLACK);
			this.campaignView.campaign_name_label.setFont(new Font("Serif", Font.BOLD, 15));

			// Setting all contents that are not required as invisible
			this.campaignView.output_map_list.setVisible(false);
			this.campaignView.campaign_add.setVisible(false);
			this.campaignView.campaign_combobox.setVisible(false);
			this.campaignView.campaign_name_text.setVisible(false);
			this.campaignView.moveMapDown.setVisible(false);
			this.campaignView.moveMapUP.setVisible(false);
			this.campaignView.removeMap.setVisible(false);
			this.campaignView.mapScrollPane.setVisible(false);
			this.campaignView.save_button.setVisible(false);
			this.campaignView.update_button.setVisible(false);
			this.campaignView.campaign_label.setVisible(false);

			// Aligning back button at the center
			this.campaignView.back_button.setBounds(200, 360, 65, 30);

			// Setting the view type as true so that different view can be
			// rendered
			this.campaignView.isView = true;

			// generating a view list
			this.campaignView.updateWindow(this.campaignModel.getOutput_map_list());

			// Setting observers that can be notified
			this.campaignModel.addObserver(campaignView);

			// Set listener for any action
			this.campaignView.setActionListener(this);

			// Show game view
			this.campaignView.setVisible(true);

		} else if (screen_type == "edit") {

			// creating campaign model
			this.campaignModel = campaign;

			try {
				this.input_map_list = FileHelper.getMaps();
			} catch (JsonSyntaxException | IOException e) {
				LogHelper.Log(LogHelper.TYPE_ERROR, e.getMessage());
			}

			this.output_map_list = this.campaignModel.getOutput_map_list();
			
			ArrayList<GameMapModel> temp = this.output_map_list;

			for (int j = 0; j < this.input_map_list.size(); j++) {
				for (int i = 0; i < temp.size(); i++) {

					if (this.input_map_list.get(j).getMap_id() == temp.get(i).getMap_id()) {
						this.input_map_list.remove(this.input_map_list.get(j));
						j=0;
					}
				}
			}

			// creating campaign view
			this.campaignView = new CampaignView(this.input_map_list);
			this.campaignView.campaign_name_text.setText(this.campaignModel.getCampaign_name());
			this.campaignView.updateWindow(this.campaignModel.getOutput_map_list());

			this.campaignModel.addObserver(campaignView);
			this.campaignView.isEdit = true;

			// set listener
			this.campaignView.setActionListener(this);

			// show game view
			this.campaignView.save_button.setVisible(false);
			this.campaignView.setVisible(true);
		}

	}

	/**
	 * Action events for all the events that get performed under CampaignView
	 * 
	 * @param e
	 *            ActionEvent argument to control event
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource().equals(this.campaignView.campaign_add)) {

			this.map_model = (GameMapModel) this.campaignView.campaign_combobox.getSelectedItem();
			this.output_map_list.add(map_model);
			this.input_map_list.remove(map_model);

			if (map_model != null) {
				this.campaignModel.setInput_map_list(this.input_map_list);
				this.campaignModel.setOutput_map_list(this.output_map_list);
			} else {
				JOptionPane.showOptionDialog(null, "There are no more maps", "Invalid", JOptionPane.DEFAULT_OPTION,
						JOptionPane.INFORMATION_MESSAGE, null, new Object[] {}, null);
				return;
			}
		} else if (e.getSource().equals(this.campaignView.moveMapUP)) {

			int moveMe = this.campaignView.output_map_list.getSelectedIndex();
			
			if(moveMe==-1)
			{
				JOptionPane.showOptionDialog(null, "Please select a map to move the map up the campaign list", "Invalid", JOptionPane.DEFAULT_OPTION,
						JOptionPane.INFORMATION_MESSAGE, null, new Object[] {}, null);
				return;
			}
			else if(moveMe==0){
				JOptionPane.showOptionDialog(null, "Selected Map is already at the beginning of the campaign", "Invalid", JOptionPane.DEFAULT_OPTION,
						JOptionPane.INFORMATION_MESSAGE, null, new Object[] {}, null);
				return;
			}else{
				// not already at top
				swap(moveMe, moveMe - 1);
			}
		} else if (e.getSource().equals(this.campaignView.moveMapDown)) {
			int moveMe = this.campaignView.output_map_list.getSelectedIndex();
			
			if(moveMe==-1)
			{
				JOptionPane.showOptionDialog(null, "Please select a map to move the map down the campaign list", "Invalid", JOptionPane.DEFAULT_OPTION,
						JOptionPane.INFORMATION_MESSAGE, null, new Object[] {}, null);
				return;
			}
			else if(moveMe==campaignModel.getOutput_map_list().size()-1){
				JOptionPane.showOptionDialog(null, "Selected Map is already at the last spot in the campaign", "Invalid", JOptionPane.DEFAULT_OPTION,
						JOptionPane.INFORMATION_MESSAGE, null, new Object[] {}, null);
				return;
			}else{
				// not already at down
				swap(moveMe, moveMe + 1);
			}
		} else if (e.getSource().equals(this.campaignView.removeMap)) {
			int moveMe = this.campaignView.output_map_list.getSelectedIndex();
			
			if(moveMe==-1)
			{
				JOptionPane.showOptionDialog(null, "Please select a map to remove from the campaign list", "Invalid", JOptionPane.DEFAULT_OPTION,
						JOptionPane.INFORMATION_MESSAGE, null, new Object[] {}, null);
				return;
			}
			else{
				// remove a map
				this.output_map_list.remove(moveMe);
				this.input_map_list.add(this.campaignView.output_map_list.getSelectedValue());
				this.campaignModel.setInput_map_list(this.input_map_list);
				this.campaignModel.setOutput_map_list(this.output_map_list);
			}
			
		} else if (e.getSource().equals(this.campaignView.back_button)) {
			new ManageCampaignController();
			this.campaignView.dispose();
		} else if (e.getSource().equals(this.campaignView.save_button)) {
			
			// first lets validate for a map name
			if (this.campaignView.campaign_name_text.getText() == null
					|| this.campaignView.campaign_name_text.getText().equals("")) {
				JOptionPane.showOptionDialog(null, "Please provide a campaign name", "Invalid",
						JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] {}, null);
				return;
				
			} else if(this.campaignModel.getOutput_map_list().isEmpty()){
				JOptionPane.showOptionDialog(null, "Please provide at least one map to create a Campaign", "Invalid",
						JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] {}, null);
				return;				
			} else {

				this.campaignModel.setCampaign_name(this.campaignView.campaign_name_text.getText());
				this.campaignModel.save();
				JOptionPane.showMessageDialog(this.campaignView,
						"Campaign " + this.campaignView.campaign_name_text.getText() + " has been saved succesfully");
				new ManageCampaignController();
				this.campaignView.dispose();
			}

		} else if (e.getSource().equals(this.campaignView.update_button)) {
			
			// first lets validate for a map name
			System.out.println(this.campaignView.campaign_name_text.getText());
			if (this.campaignView.campaign_name_text.getText() == null
					|| this.campaignView.campaign_name_text.getText().equals("")) {
				JOptionPane.showOptionDialog(null, "Please provide a campaign name", "Invalid",
						JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] {}, null);
				return;
				
				
			} else if(this.campaignModel.getOutput_map_list().isEmpty()){
				JOptionPane.showOptionDialog(null, "Please provide at least one map for a Campaign", "Invalid",
						JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] {}, null);
				return;				
			}
			else {

				this.campaignModel.setCampaign_name(this.campaignView.campaign_name_text.getText());
				this.campaignModel.update();
				JOptionPane.showMessageDialog(this.campaignView,
						"Campaign " + this.campaignView.campaign_name_text.getText() + " has been saved succesfully");
				new ManageCampaignController();
				this.campaignView.dispose();
			}

		}
	}

	/**
	 * Swap function used to swap values between two variables received for the
	 * List
	 * 
	 * @param a
	 * @param b
	 */
	private void swap(int a, int b) {
		GameMapModel objectA = this.campaignModel.getOutput_map_list().get(a);
		GameMapModel objectB = this.campaignModel.getOutput_map_list().get(b);
		this.output_map_list.set(b, objectA);
		this.output_map_list.set(a, objectB);
		this.campaignModel.setOutput_map_list(this.output_map_list);
	}

}
