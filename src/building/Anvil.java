package building;

import building.base.Building;
import building.base.ItemProducer;
import building.base.ItemReceiver;
import item.Item;
import item.ItemType;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import logic.GameState;
import ui.BuildingIcon;


public class Anvil extends Building implements ItemProducer, ItemReceiver {
	private static final int CRAFT_DELAY = 5;
	private static final int ENERGY_CONSUMPTION = 15;
	private int wood=0;
	private int ironplate=0;
	private int cycle=0; 
	public void operate() {
		if(cycle < CRAFT_DELAY) {
			cycle++;
		}
		GameState.instance.consumeElectricity(ENERGY_CONSUMPTION);
	}
	@Override
	public void render(StackPane target) {
		BuildingIcon icon = new BuildingIcon("file:res/anvil.png");
		target.getChildren().add(icon);
		
		
		HBox status = new HBox();
		StackPane.setAlignment(status, Pos.TOP_CENTER);
		
		// TODO: fill this booleans
		boolean ready = this.canProduceItem();			// is this anvil ready (finished crafting iron sword)?
		boolean hasWood = !this.canReceiveItem(ItemType.WOOD);		// does this anvil has wood on it?
		boolean hasIronPlate=!this.canReceiveItem(ItemType.IRON_PLATE);;	// does this anvil has an iron plate on it?
		
		if(ready) {
			status.getChildren().add(ItemType.IRON_SWORD.toItemIcon());
		} else {
			if(hasWood) {
				status.getChildren().add(ItemType.WOOD.toItemIcon());
			}
			if(hasIronPlate) {
				status.getChildren().add(ItemType.IRON_PLATE.toItemIcon());
			}
		}
		target.getChildren().add(status);
	}
	public boolean canReceiveItem(ItemType ofType) {
		switch (ofType) {
		case WOOD:
			if(wood==0) {
				return true;
			}
		case IRON_PLATE:
			if(ironplate ==0) {
				return true;
			}
		default:
			return false;
		}
	}
	public void receiveItem(Item item) {
		if(item.getType() == ItemType.WOOD) {
			wood = 1;
		}
		else {
			ironplate = 1;
		}
	}
	public boolean canProduceItem() {
		if(wood == 1 && ironplate == 1 && cycle == CRAFT_DELAY) {
			return true;
		}
		return false;
	}
	public Item getProducedItem() {
		cycle=0;
		wood=0;
		ironplate=0;
		return Item.ironSword();
	}
}
