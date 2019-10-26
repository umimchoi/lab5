package building;

import building.base.Building;
import building.base.ItemProducer;
import building.base.ItemReceiver;
import item.Item;
import item.ItemType;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import logic.Direction;
import logic.Field;
import ui.BuildingIcon;
import ui.ItemIcon;

public class ConveyorBelt extends Building implements ItemReceiver, ItemProducer {	
	private Direction direction;
	private boolean beginn = false;
	private Item onthisbelt=null;
	public ConveyorBelt(Direction direction) {
		this.direction = direction;
	}

	
	@Override
	public void operate() {
		// TODO Auto-generated method stub
		 Building back = Field.instance.getBuildingInBack(direction);
		 Building front = Field.instance.getBuildingInFront(direction);
		 if(this.canProduceItem()) {
			 if(front instanceof ItemReceiver) {
			if(((ItemReceiver) front).canReceiveItem(this.getItemOnBelt().getType())) {
				((ItemReceiver) front).receiveItem(this.getItemOnBelt());
			}
			 }
		 }
		 else {
			 if(back instanceof ItemProducer) {
			 if(((ItemProducer) back).canProduceItem() && !beginn && this.onthisbelt == null){
				 this.receiveItem(((ItemProducer) back).getProducedItem());
			 }
			 else {
				 this.receiveItem(null);
			 }
			 }
		 }
	}


	@Override
	public void beforeCycle() {
		this.beginn = false;
	}
	
	@Override
	public void render(StackPane target) {
		
		BuildingIcon icon = new BuildingIcon("file:res/belt.png");
		
		switch(this.getDirection()) {
		case RIGHT:
			icon.setRotate(90);
			break;
		case LEFT:
			icon.setRotate(270);
			break;
		case DOWN:
			icon.setRotate(180);
			break;
		default:
			break;
		}
		
		target.getChildren().add(icon);
		
		Item itemOnBelt = this.getItemOnBelt();
		
		if(itemOnBelt != null) {
			ItemIcon itemIcon = itemOnBelt.getType().toItemIcon();
			StackPane.setAlignment(itemIcon, Pos.TOP_CENTER);
			target.getChildren().add(itemIcon);
		}
	}


	@Override
	public boolean canReceiveItem(ItemType ofType) {
		// TODO Auto-generated method stub
		if(this.onthisbelt == null && !beginn && ofType != null) {
			return true;
		}
		return false;
	}


	@Override
	public void receiveItem(Item item) {
		// TODO Auto-generated method stub
		beginn = true;
		this.onthisbelt = item;
		
	}


	@Override
	public boolean canProduceItem() {
		// TODO Auto-generated method stub
		if(this.onthisbelt != null && !beginn) {
			return true;
		}
		return false;
	}


	@Override
	public Item getProducedItem() {
		// TODO Auto-generated method stub
		beginn = true;
		Item send = this.onthisbelt;
		this.onthisbelt = null;
		return send;
	}


	/* getters & setters */
	public Item getItemOnBelt() {
		return this.onthisbelt;
	}
	
	public Direction getDirection() {
		return this.direction;
	}
}
