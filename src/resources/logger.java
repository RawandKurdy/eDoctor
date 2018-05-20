
package resources;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Instant;

/**
 *
 * @author rawandkurdy
 */

public class logger {
    
    /**this  logs all the operations and incase if there was an error you can easily know
     * 
     * @param txt 
     */
    public static void appendnewLog(String txt){
    
  try(FileWriter fw = new FileWriter("log.txt", true);
    BufferedWriter bw = new BufferedWriter(fw);
    PrintWriter out = new PrintWriter(bw))
{
    out.println(Instant.now());
    //
    out.println(txt);
    //
} catch (IOException e) {
    //nothing
}
    
    }
}
