import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class Main {

	public static void main(String[] args) {

		String fileName = "in.txt";
		stableMarriage marriage = new stableMarriage(fileName);
		marriage.writeData();

	}

}
