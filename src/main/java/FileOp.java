import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import scala.util.parsing.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class FileOp {
    public static void main(String[] args) throws InterruptedException, IOException {

        //FILE
        File json_file=new File("C:\\Users\\Zehra\\Desktop\\Data\\search.json");
        FileWriter fileWriter=new FileWriter(json_file,true);
        BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);

        List<String> items= Arrays.asList("Nike airmax","iphone","adidas","dell laptop","dell inspiron","dell gaming laptop","" +
                "monster game laptop","ford focus","ford","toyota corolla","toyota auris","iphone 7");

        while(true){
            UserSearchModel usm=new UserSearchModel();
            int userID= ThreadLocalRandom.current().nextInt(200,250+1);
            int cityID= ThreadLocalRandom.current().nextInt(1,81+1);
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            Random random=new Random();
            String item=items.get(random.nextInt(items.size()));
            usm.setUserId(userID);
            usm.setCity(cityID);
            usm.setCurrent_ts(timestamp.toString());
            usm.setSearch(item);
            Gson gson=new GsonBuilder().setPrettyPrinting().create();
            String data=gson.toJson(usm);

            JsonObject json_obj=new Gson().fromJson(data,JsonObject.class);

            bufferedWriter.write(json_obj.toString());
            bufferedWriter.newLine();
            bufferedWriter.flush();

        }
    }
}
