import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Tester {
	static String inputFile;

private static void writeToFile(String content)
{
BufferedWriter writer=null;
try {
	writer = new BufferedWriter(new FileWriter(inputFile));
	writer.write(content);
	writer.close();	

} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

}



public static void main(String args[]) 
{
inputFile="hands.txt";
String content= "Black: 2H 4S 4C 2D 4H White: 2S 8S AS QS 3S";
writeToFile(content);
System.out.println(content);
PokerHands poker=new PokerHands(inputFile);
poker.judgeHands();

content= "Black: 2H 3D 5S 9C KD White: 2C 3H 4S 8C AH";
writeToFile(content);
System.out.println(content);
PokerHands poker2=new PokerHands(inputFile);
poker2.judgeHands();




}


}
