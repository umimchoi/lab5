package test.student;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import building.ConveyorBelt;
import item.Item;
import logic.Direction;
import logic.Field;
import logic.GameState;

class TestConveyorBelt{
	ConveyorBelt belt1;
	ConveyorBelt belt2;
	ConveyorBelt belt3;
	ConveyorBelt belt4;
	@BeforeEach
	 void setUpBeforeEachTest() {
		belt1 = new ConveyorBelt(Direction.RIGHT);
		belt2 = new ConveyorBelt(Direction.LEFT);
		belt3 = new ConveyorBelt(Direction.UP);
		belt4 = new ConveyorBelt(Direction.DOWN);
		Field.instance.at(1, 1).placeBuildingOnCell(belt1);
		Field.instance.at(5, 5).placeBuildingOnCell(belt2);
		Field.instance.at(0, 7).placeBuildingOnCell(belt3);
		Field.instance.at(8, 0).placeBuildingOnCell(belt4);
	}

	@Test
	void test() {
		belt1.receiveItem(Item.wood());
		belt2.receiveItem(Item.iron());
		belt3.receiveItem(Item.ironPlate());
		belt4.receiveItem(Item.ironSword());
		
		assertNotNull(belt1.getItemOnBelt());
		GameState.instance.update();
		assertNotNull(belt2.getItemOnBelt());
		GameState.instance.update();
		assertNotNull(belt3.getItemOnBelt());
		GameState.instance.update();
		assertNotNull(belt4.getItemOnBelt());
	}

}
