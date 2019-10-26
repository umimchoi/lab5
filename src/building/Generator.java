package building;

import building.base.Building;
import building.base.ItemReceiver;
import item.Item;
import item.ItemType;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import logic.GameState;
import ui.BuildingIcon;
import ui.ItemIcon;

public class Generator extends Building implements ItemReceiver {
	
	private static final int GENERATION_DELAY = 3;
	private static final int GENERATED_ELECTRICITY = 5;
	private int cycle =0;
	private int wood=0;
public void operate() {
if(cycle < GENERATION_DELAY) {
	cycle++;
}
if(cycle == GENERATION_DELAY) {
	GameState.instance.generateElectricity(GENERATED_ELECTRICITY);
	cycle =0;
	wood=0;}
}
	@Override
	public void render(StackPane target) {
		BuildingIcon icon = new BuildingIcon("file:res/generator.png");
		target.getChildren().add(icon);
		
		// TODO: fill this boolean
		boolean hasWood = (wood ==1) ? true : false;		// does this generator has wood in it?
		if(hasWood) {
			ItemIcon itemIcon = ItemType.WOOD.toItemIcon();
			StackPane.setAlignment(itemIcon, Pos.TOP_CENTER);
			target.getChildren().add(itemIcon);
		}
	}
	public boolean canReceiveItem(ItemType ofType) {
		if(wood ==0 && ofType == ItemType.WOOD) {
			return true;
		}
		return false;
	}
	public void receiveItem(Item item) {
		wood++;
	}
}
