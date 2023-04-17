package ObjectActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class SaveAndLoad {
    public ArrayList<ArrayList> getFileInfo(String fileName, ArrayList<ArrayList> listMatrix) throws IOException{
        File file = new File(fileName);

        if(!file.exists()){ //If the file doesnt exist, create it
            file.createNewFile();
        }
        else{
            try {
                FileInputStream fis = new FileInputStream(fileName);
                ObjectInputStream ois = new ObjectInputStream(fis);
   
                listMatrix = (ArrayList<ArrayList>) ois.readObject();

                fis.close();
                ois.close();
                return listMatrix;
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return listMatrix;
    }

    public void saveFileInfo(String fileName, ArrayList<ArrayList> listMatrix){
        try {
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(listMatrix);
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
