package Main;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Edwin
 */
import java.io.File;
import java.lang.reflect.*;
import java.net.URL;
import java.util.*;
import javax.swing.JPanel;

public class AnimalFactory {
    Constructor animalConstructor;
    public HashMap<String, ArrayList<Animal>> animalMap = new HashMap<>();
    public ArrayList<Animal> listAnimal;
    int defaultSize = 10;
    String tempAnimalName;
    private static AnimalFactory instance;
    
    public static void registerAllAnimalClasses(String scannedPackage)
    {
        final char DOT = '.';
        final char SLASH = '/';
        final String CLASS_SUFFIX = ".class";
        final String BAD_PACKAGE_ERROR = "Unable to get resources from path '%s'. Are you sure the package '%s' exists?";

        String scannedPath = scannedPackage.replace(DOT, SLASH);
        URL scannedUrl = Thread.currentThread().getContextClassLoader().getResource(scannedPath);
        if (scannedUrl == null) {
            throw new IllegalArgumentException(String.format(BAD_PACKAGE_ERROR, scannedPath, scannedPackage));
        }
        File scannedDir = new File(scannedUrl.getFile());
        for (File file : scannedDir.listFiles()) {
            String resource = scannedPackage + DOT + file.getName();
            if (resource.endsWith(CLASS_SUFFIX))
            {
                int endIndex = resource.length() - CLASS_SUFFIX.length();
                String className = resource.substring(0, endIndex);
                try
                {
                    Class.forName(className);
                }catch(Exception e){}
            }
        }
    }
    
    static
    {
        registerAllAnimalClasses("Animal");
    }
    
    private AnimalFactory()
    {}
    
    public synchronized static AnimalFactory getInstance()
    {
        if (instance == null)
            instance = new AnimalFactory();
        return instance;
    }
    
    public void registerAnimal(Class animal) {
        try {
            listAnimal = new ArrayList<>();
            animalConstructor = animal.getDeclaredConstructor(new Class[] {});
            Field animalName = animal.getField("animalName");
            tempAnimalName = (String)animalName.get(null);
        }catch(Exception e) {}
            
        for (int j=0;j<defaultSize;j++)
        {
            try {
                Animal tempAnimal = (Animal)animalConstructor.newInstance(new Object[] {});
                listAnimal.add(tempAnimal);
            }catch(Exception e) {
            }
        }
        animalMap.put(tempAnimalName.toLowerCase(),listAnimal);
    }
    
    public synchronized Animal getAnimal() {
        // get all key of animalMap   
        ArrayList<String> animalKey = new ArrayList<>();
        animalKey.addAll(animalMap.keySet());
        
        //get random key
        Random rand = new Random();
        int i = rand.nextInt(animalKey.size()); //random key index
        
        // get animal based on key
        ArrayList<Animal> animalList = animalMap.get(animalKey.get(i).toLowerCase());
        //return animalList.remove(animalList.size()-1);
        return animalMap.get("cyclist").remove(0);
    }
    
    public synchronized void putAnimal(Animal animal) {
        try {
                Field animalName = animal.getClass().getField("animalName");
                tempAnimalName = (String)animalName.get(null);
            }catch(Exception e) {}
        animal.setLive(animal.getDefaultLive());
        ArrayList<Animal> animalList = animalMap.get(tempAnimalName.toLowerCase());
        animalList.add(animal);
    }
}
