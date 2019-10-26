package building;

import building.base.Building;
import building.base.ItemProducer;
import building.base.ItemReceiver;
import item.Item;
import item.ItemType;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import logic.GameState;
import ui.BuildingIcon;
import ui.ItemIcon;

public class Furnace extends Building implements ItemProducer, ItemReceiver {
	private static final int CRAFT_DELAY = 3;
	private static final int ENERGY_CONSUMPTION = 10;
	private int iron=0;
	private int cycle=0;
		public void operate() {
			if(cycle < CRAFT_DELAY) {
				cycle++;
			}
			GameState.instance.consumeElectricity(ENERGY_CONSUMPTION);
			
		}
	@Override
	public void render(StackPane target) {
		BuildingIcon icon = new BuildingIcon("file:res/furnace.png");
		target.getChildren().add(icon);
		
		// TODO: fill these booleans
		boolean readyToProduceIronPlate = this.canProduceItem();		// is this furnace ready to produce an iron plate?
		boolean hasIron = iron==1 ? true : false ;						// does this furnace has iron in it?
		
		ItemIcon itemIcon = null;
		if (readyToProduceIronPlate) {
			itemIcon = ItemType.IRON_PLATE.toItemIcon();
		} else if(hasIron) {
			itemIcon = ItemType.IRON.toItemIcon();
		}
		
		if(itemIcon != null) {
			StackPane.setAlignment(itemIcon, Pos.TOP_CENTER);
			target.getChildren().add(itemIcon);
		}
	}
	public boolean canReceiveItem(ItemType ofType) {
		if(ofType == ItemType.IRON) {
			if(iron == 0) {
				return true;
			}}
			return false;
		
	}
	public void receiveItem(Item item) {
		iron++;
	}
	public boolean canProduceItem() {
		if(iron == 1) {
			return true;
		}
		return false;
	}
	public Item getProducedItem() {
		iron=0;
		cycle=0;
		return Item.ironPlate();
	}
}
