package dungeons_and_dragons.helper;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import dungeons_and_dragons.model.ItemModel;



/**
 * this class use for manage game data into file
 * basically store data in json file  
 *	
 * @author Mihir Pujara
 *
 */
public class FileHelper {
	
	/**
	 * static variable for set character file path
	 * 
	 *  @type String
	 */
	private static final String CHARACTER_FILE = "res/character.json";
	
	/**
	 * static variable for set map file path
	 * 
	 *  @type String
	 */
	private static final String MAP_FILE = "res/map.json";
	
	/**
	 * static variable for set campaign file path
	 * 
	 *  @type String
	 */
	private static final String CAMPAIGN_FILE = "res/campaign.json";
	
	/**
	 * static variable for set item file path
	 * 
	 *  @type String
	 */
	private static final String ITEM_FILE = "res/item.json";
	
	/**
	 * this method used to save character into character file
	 *  
	 * @param character
	 * @throws IOException
	 */
	public static void saveCharacter(Character character) throws IOException {
		
		//fetch old data from file and store that into array list
		ArrayList<Character> item_list = getCharcters();
		
		//add new data to arraylist
		item_list.add(character);
		
		//create writer object for character file
		Writer file_writer = new FileWriter(CHARACTER_FILE);
		
		// store object to json 
		Gson gson = new Gson();
		gson.toJson(item_list,file_writer);
		
		// close file
		file_writer.close();	
	}
	
	
	/**
	 * this method gives the list of characters that saved in file
	 * 
	 * @return ArrayList<Character>
	 * @throws IOException
	 * @throws JsonSyntaxException
	 */
	public static ArrayList<Character> getCharcters() throws IOException,JsonSyntaxException {
		
		//create reader objecr to read data from item file
		Reader reader = new FileReader(CHARACTER_FILE);
		
		// read data from json file convert it into arraylist and return it
		Gson gson = new Gson();
		return gson.fromJson(reader, new TypeToken<ArrayList<Character>>(){}.getType());	
	}
	
	
	/**
	 * this method used to save item into item file
	 * 
	 * @param item
	 * @throws IOException
	 */
	public static void saveItem(ItemModel item) throws IOException {
		
		//fetch old data from file and store that into array list
		ArrayList<ItemModel> item_list = getItems();
		
		//add new data to arraylist
		item_list.add(item);
		
		//create writer object for item file
		Writer file_writer = new FileWriter(ITEM_FILE);
		
		// store object to json 
		Gson gson = new Gson();
		gson.toJson(item_list,file_writer);
		
		// close file
		file_writer.close();	
	}
	
	/**
	 * this method gives the list of items that saved in file
	 * 
	 * @return ArrayList<Character>
	 * @throws IOException
	 * @throws JsonSyntaxException
	 */
	public static ArrayList<ItemModel> getItems() throws IOException,JsonSyntaxException {
		
		//create reader objecr to read data from item file
		Reader reader = new FileReader(ITEM_FILE);
		
		// read data from json file convert it into arraylist and return it
		Gson gson = new Gson();
		return gson.fromJson(reader, new TypeToken<ArrayList<ItemModel>>(){}.getType());		
	}
}