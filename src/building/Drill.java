package building;

import building.base.Building;
import building.base.ItemProducer;
import item.Item;
import item.ItemType;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import logic.GameState;
import ui.BuildingIcon;
import ui.ItemIcon;

public class Drill extends Building implements ItemProducer {

	private static final int HARVEST_INTERVAL = 4;
	private static final int ENERGY_CONSUMPTION = 5;
	private int cycle=0;
public void operate() {
	if(cycle < HARVEST_INTERVAL) {
		cycle++;
	}
	GameState.instance.consumeElectricity(ENERGY_CONSUMPTION);
}
	@Override
	public void render(StackPane target) {
		BuildingIcon icon = new BuildingIcon("file:res/drill.png");
		target.getChildren().add(icon);
		
		// TODO: fill this boolean
		boolean readyToProduceIron = this.canProduceItem();		// is this drill ready to produce iron?
		
		if(readyToProduceIron) {
			ItemIcon itemIcon = ItemType.IRON.toItemIcon();
			StackPane.setAlignment(itemIcon, Pos.TOP_CENTER);
			target.getChildren().add(itemIcon);
		}
	}
	public boolean canProduceItem() {
		if(cycle == HARVEST_INTERVAL ){
			return true;
		}
		return false;
	}
	public Item getProducedItem() {
		cycle=0;
		return Item.iron();
	}
}
