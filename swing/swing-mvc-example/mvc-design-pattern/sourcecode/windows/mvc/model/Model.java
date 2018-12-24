package mvc.model;
import java.util.ArrayList;
import java.util.Collections;

public class Model{

    private ArrayList<String> persons;
    public Model(){
        initList();
    }

    private void initList(){
        persons = new ArrayList<String>();
        persons.add("Arne Anka");
        persons.add("Sockerconny");
        persons.add("Ragnar Frunk");
        persons.add("Peter Parker");
        persons.add("Agent Carter");
        persons.add("Mandel Karlsson");
        persons.add("Archibald Haddock");
        persons.add("Stan Lee");
        persons.add("Lucky Luke");
        Collections.sort(persons);
    }
    public void delete(String name){
        persons.remove(name);
    }
    public ArrayList<String> search(String target){
        ArrayList<String> matches = new ArrayList<String>();
        for(String person : persons){
            if(person.toLowerCase().contains(target.toLowerCase())){
                matches.add(person);
            }
        }
        return matches;
    }
}
