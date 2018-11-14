import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DataStore implements Serializable {
    private HashMap<String, Integer> scores;
    static DataStore instance = null;

    private DataStore(){
        scores = new HashMap<>();
    }

    void addScore(String name, Integer score){
        scores.put(name, score);
        try {
            this.writeObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    int getScoreByName(String name){
        Iterator it = scores.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            if(pair.getKey().equals(name)){
                return (int)pair.getValue();
            }
        }
        return -1;
    }

    HashMap<String, Integer> getScores(){
        return scores;
    }


    static DataStore getInstance(){
        if(instance==null){
            instance = new DataStore();
        }
        return instance;
    }

    public final void writeObject() throws IOException {
        try {
            FileOutputStream fileOut = new FileOutputStream("/tmp/datastore.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in /tmp/datastore.ser");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public final DataStore readObject() throws IOException, ClassNotFoundException{
        DataStore ds = null;
        try {
            FileInputStream fileIn = new FileInputStream("/tmp/employee.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            ds = (DataStore) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("DataStore class not found");
            c.printStackTrace();
        }
        return ds;
    }
}
