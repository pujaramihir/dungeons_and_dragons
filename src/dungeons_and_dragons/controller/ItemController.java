package dungeons_and_dragons.controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import dungeons_and_dragons.model.ItemModel;
import dungeons_and_dragons.view.CreateGameView;
import dungeons_and_dragons.view.ItemView;
/**
 * This class call item controller 
 * 
 * @author Urmil Kansara 
 *
 */
public class ItemController implements ActionListener {
	
	/**
	 *	This creates new model
	 *  @type ItemModel
	 */
	ItemModel item_model;
	
	/**
	 * This create observer object
	 * 
	 * @type ItemView
	 */
	ItemView item_view;
	
	/**
	 * Default constructor of item controller
	 * <p>
	 * Item model and view are initialized and also view is binded to observer.
	 * <p>
	 * all the events of view are registered in constructor
	 */
	public ItemController() {
		// TODO Auto-generated constructor stub
		//this.field_item = item_field;
		this.item_model = new ItemModel();
		this.item_view = new ItemView();
		
		this.item_model.addObserver(item_view);
		this.item_view.setListener(this);
		this.item_view.setVisible(true);
	}
	
	public ItemController(ItemModel itemModel) {
		
		this.item_model = itemModel; 
		this.item_view = new ItemView(itemModel);
		this.item_model.addObserver(item_view);
		this.item_view.setListener(this);
		this.item_view.setVisible(true);
	}
	
	/**
	 * Action event of all the events
	 * 
	 */

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("Inside Controller action performed");
		System.out.println(arg0.getSource()+" "+arg0.getActionCommand());
		if(arg0.getSource().equals(item_view.item_type_field))
		{
			String get_item_type = (String) item_view.item_type_field.getSelectedItem();
			System.out.println(get_item_type);
			
			item_model.itemTypeSelected(get_item_type);
			
		}else if(arg0.getSource().equals(item_view.save_item))
		{	
			String item_name = item_view.item_name_field.getText();
			item_model.setItem_name(item_name);
			String item_type = (String) item_view.item_type_field.getSelectedItem();
			item_model.setItem_type(item_type);
			String item_ability = (String) item_view.item_ability_field.getSelectedItem();
			item_model.setItem_ability(item_ability);
			int item_point = Integer.parseInt(item_view.item_score_field.getText());
			item_model.setItem_point(item_point);
			//item_model.itemTypeSelected(item_type);
			item_model.save();
			new ManageItemController();			
			item_view.dispose();
		}
		else if(arg0.getSource().equals(item_view.back_button))
		{	
			new ManageItemController();
			
			item_view.dispose();
		}
		else if(arg0.getSource().equals(item_view.update_item))
		{	
			String item_name = item_view.item_name_field.getText();
			item_model.setItem_name(item_name);
			String item_type = (String) item_view.item_type_field.getSelectedItem();
			item_model.setItem_type(item_type);
			String item_ability = (String) item_view.item_ability_field.getSelectedItem();
			item_model.setItem_ability(item_ability);
			int item_point = Integer.parseInt(item_view.item_score_field.getText());
			item_model.setItem_point(item_point);
			item_model.update();
			
			new ManageItemController();
			
			item_view.dispose();
		}
		
	}
	
	

}