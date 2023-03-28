package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import model.characters.Feral;
import model.characters.Normal;
import model.characters.State;
import model.characters.Zombie;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Q1_V5 {

		@Test
		public void testZombieConstructor() {
			Zombie zombie = new Zombie(5);
			assertEquals("The speed property should be 5", 5, zombie.getSpeed());
			assertEquals("The name property should be \"Zombie 11\"", "Zombie 11", zombie.getName());
			
			Zombie anotherZombie = new Zombie(10);
			assertEquals("The speed property should be 10", 10, anotherZombie.getSpeed());
			assertEquals("The name property should be \"Zombie 12\"", "Zombie 12", anotherZombie.getName());
		}

		@Test
		public void testSetSpeed() {
			Zombie zombie = new Zombie(5);
			zombie.setSpeed(10);
			assertEquals("The speed property should be 10", 10, zombie.getSpeed());
		}
		
		@Test
		public void testSpeedAttributeIsPrivate() {
		    Zombie zombie = new Zombie(5);
		    Class<?> zombieClass = zombie.getClass();
		    Field[] fields = zombieClass.getDeclaredFields();
		    for (Field field : fields) {
		    	if(!field.getName().equals("ZOMBIES_COUNT"))
		    		assertTrue("The " + field.getName() + " attribute should be private", Modifier.isPrivate(field.getModifiers()));
		    }
		}
		@Test
	    public void testZombieInheritsFromCharacter() {
	    	Zombie zombie = new Zombie(10);
	        assertTrue("Zombie should inherit from Character", zombie instanceof model.characters.Character);
	    }
		
		@Test
		public void testNormalInheritsFromZombie() {
		    Normal normal = new Normal(50);
		    assertTrue("Normal should inherit from Zombie", normal instanceof Zombie);
		}

		@Test
		public void testNormalConstructor() {
		    Normal normal = new Normal(50);
		    assertEquals("The speed property should be 50", 50, normal.getSpeed());
		    assertFalse("The isRunner property should be false", normal.isRunner());
		}

		@Test
		public void testNormalAttributesArePrivate() {
		    Normal normal = new Normal(50);
		    Class<?> normalClass = normal.getClass();
		    Field[] fields = normalClass.getDeclaredFields();
		    for (Field field : fields) {
		        assertTrue("The " + field.getName() + " attribute should be private", Modifier.isPrivate(field.getModifiers()));
		    }
		}

		@Test
		public void testNormalGetterAndSetterInClass() {
			Normal normal = new Normal(50);
		    normal.setRunner(true);
		    assertTrue("The isRunner property should be true", normal.isRunner());
		}
		
		@Test
		public void testConstructor() {
			Feral feral = new Feral(50, State.STALKER);
			assertEquals("The speed should be 50", 50, feral.getSpeed());
			assertEquals("The state should be STALKER", State.STALKER, feral.getState());
		}
		
		@Test
		public void testGettersAndSetters() {
			Feral feral = new Feral(50, State.STALKER);
			feral.setState(State.CLICKER);
			assertEquals("The state should be CLICKER", State.CLICKER, feral.getState());
		}
		
		@Test
		public void testInheritsFromZombie() {
			Feral feral = new Feral(50, State.STALKER);
			assertTrue("Feral should inherit from Zombie", feral instanceof Zombie);
		}
		
		@Test
		public void testAttributesArePrivate() {
		    Feral feral = new Feral(50, State.CLICKER);
		    Class<?> feralClass = feral.getClass();
		    Field[] fields = feralClass.getDeclaredFields();
		    for (Field field : fields) {
		        assertTrue("The " + field.getName() + " attribute should be private", Modifier.isPrivate(field.getModifiers()));
		    }
		}
		
}
