import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

 class FileLoggerConfigurationLoader {
    static String[]  correctKeys = {"filePath","level","maxFileSize","format"};
    public static FileLoggerConfiguration load(String filename){
        Arrays.sort(correctKeys);

        try
        {
            FileLoggerConfiguration flc = new FileLoggerConfiguration();
            BufferedReader br = new BufferedReader (new FileReader(filename));
            String s;
            while((s=br.readLine())!=null){
                String[] ps = parseString(s);

                if(!ps[0].equals("false") && isCorrectKey(ps[0])>=0){
                flc.setParams(ps[0],ps[1]);

                }

            }
            System.out.println(flc.getFilePath());
            return   flc;

        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }

        return   new FileLoggerConfiguration();
    }

    private static   String[] parseString(String string){
        if(string.startsWith("//")||string.isEmpty()){
            return new String[] {"false"};
        }
        int separator = string.indexOf(":");
         if(!string.contains(":") || separator!=string.lastIndexOf(":")){
             return new String[] {"false"};
         }
         String key = string.substring(0,separator);
         String value = string.substring(separator+1);
         return  new String[] {key,value};
    }

    private static int isCorrectKey(String key){

        return Arrays.binarySearch(correctKeys,key);
    }

}
