package Main;

import org.junit.Test;

public class AnimalFactoryTest {

	Animal tempAnimal;
	
	@Test
    public void testGetInstance() {
        System.out.println("getInstance");
        AnimalFactory result = AnimalFactory.getInstance();
        assertNotNull(result);
    }

    @Test
    public void testGetAnimal() {
        System.out.println("getAnimal");
        tempAnimal = AnimalFactory.getInstance().getAnimal();
        assertNotNull(tempAnimal);
    }
    
    @Test
    public void testPutAnimal() {
        System.out.println("putAnimal");
        tempAnimal = AnimalFactory.getInstance().getAnimal();
        try
        {
        AnimalFactory.getInstance().putAnimal(tempAnimal);
        }catch(Exception e)
        {
        	fail("WHOOPS! Threw Exception" + e);
        }
    }

}
