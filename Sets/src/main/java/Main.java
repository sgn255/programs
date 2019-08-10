import java.io.PrintStream;
import java.math.BigInteger;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.HashMap;

public class Main {

	private HashMap<Identifier,Set<BigInteger>> map;
	Scanner in ;
	PrintStream out;
	Main(){
		out = new PrintStream(System.out);
		in = new  Scanner(System.in);
		map = new HashMap<>();
	}
	private void start() {
		while(in.hasNextLine()){
			Scanner input = new Scanner(in.nextLine());
			input.useDelimiter("");
			try{
				statement(input);
			}catch (APException e) {
				out.println(e.getMessage());
			}
		}
	}
	private boolean nextIsLetter(Scanner input){
		return input.hasNext("[a-zA-Z]+");
	}
	private boolean nextIsNumber(Scanner input){
		return (zero(input)||nextIsNonZero(input));
	}
	private boolean zero(Scanner input){
		return input.hasNext("[0]");
	}
	private boolean nextIsNonZero(Scanner input){
		return input.hasNext("[1-9]");
	}
	private boolean nextCharIs(Scanner input, char c){
		removeSpaces(input);
		return input.hasNext(Pattern.quote(c+""));
	}
	private void removeSpaces(Scanner input){
		while (input.hasNext(" ")){
			nextChar(input);
		}	
	}
	private void character(Scanner input, char c) throws APException{
		removeSpaces(input);
		if (!nextCharIs(input,c)){
			throw new APException(String.valueOf(c)+"is expected");
		}
		nextChar(input);
		removeSpaces(input);
	}
	private boolean nextIsAdditiveOperator(Scanner input){
		if (nextCharIs(input,'+')|| nextCharIs(input, '|') || nextCharIs(input, '-')){
			return true;
		}
		return false;
	}
	private boolean nextIsMultiplicativeOperator(Scanner input){
		if (nextCharIs(input, '*')){
			nextChar(input);
			removeSpaces(input);
			return true;
		}
		return false;
	}
	private char nextChar(Scanner input){
		return input.next().charAt(0);
	}
	private char letter(Scanner input)throws APException{
		if (!input.hasNext("[a-zA-Z]+")){
			throw new APException("invalid identifer input");
		}
		return nextChar(input);
	}
	private char number(Scanner input) throws APException{
		if (zero(input)||nextIsNonZero(input)){
			return nextChar(input);
		}
		throw new APException("input is not a valid number");
	}
	private void eoln(Scanner input) throws APException{
		if (input.hasNext()){
			throw new APException("no end of line");
		}
	}
	private void statement(Scanner input) throws APException{
		if(nextIsLetter(input)){
			assignment(input);
		}else if (nextCharIs(input, '?')){
			printStatement(input);
		}else if (nextCharIs(input, '/')){
			comment(input);	
		}else{
			throw new APException("Input should start with either a letter or '/' or '?");
		}
	}
	private void assignment(Scanner input) throws APException{
		Identifier id = createIdentifier(input);
		character(input,'=');
		Set<BigInteger> set = expression(input);
		eoln(input);
		map.put(id, set);
	}
	private void printStatement(Scanner input) throws APException{
		character(input, '?');
		Set<BigInteger> set = expression(input);
		eoln(input);
		print(set);
	}
	private void comment(Scanner input) throws APException{
		character(input,'/');
		if (input.hasNext()){
			input.nextLine();
		}
		eoln(input);
	}
	private Identifier createIdentifier(Scanner input) throws APException{
		StringBuffer result = new StringBuffer();
		result.append(letter(input));
		while (nextIsLetter(input) || nextIsNumber(input)){
			result.append(nextChar(input));
		}
		Identifier id = new IdentifierImp(result.toString());
		return id;
	}
	private Set<BigInteger> expression(Scanner input) throws APException{
		Set<BigInteger> set = term(input);
		while(nextIsAdditiveOperator(input)){
			if (nextCharIs(input, '+')){
				character(input, '+');
				Set<BigInteger> other = term(input);
				set=set.uninon(other);
			}
			if (nextCharIs(input, '|')){
				character(input, '|');
				Set<BigInteger> other = term(input);
				set=set.symmetricDifference(other);
			}
			if (nextCharIs(input, '-')){
				character(input, '-');
				Set<BigInteger> other = term(input);
				set=set.complement(other);
			}
		}
		return set;
	}
	private Set<BigInteger> term(Scanner input) throws APException{
		Set<BigInteger> set =factor(input);
		while (nextIsMultiplicativeOperator(input)){
			Set<BigInteger> other =factor(input);
			set = set.intersection(other);
		}
		return set;
	}
	private Set<BigInteger> factor(Scanner input) throws APException{
		if(nextIsLetter(input)){
			Identifier id = createIdentifier(input);
			if(map.get(id)==null){								
				throw new APException ("the id does not exist");
			}
			else 
				return (map.get(id));										
		}																
		else if (nextCharIs(input, '(')){
			return (complexFactor(input));
		}
		else if (nextCharIs(input, '{')){
			return ( set(input));
		}
		else{
			throw new APException("incomplete input");
		}
	}
	private Set<BigInteger> complexFactor(Scanner input) throws APException{
		character(input, '(');
		Set<BigInteger> set = expression(input);
		character(input, ')');
		return set;
	}
	private Set<BigInteger> set(Scanner input) throws APException{
		Set<BigInteger> set = new SetImp<BigInteger>();
		character(input, '{');
		if (nextIsNumber(input)){
			String row = rowNaturalNumber(input);
			String array[]=row.split(",");
			for (int i =0; i<array.length;i++){
				set.add(new BigInteger(array[i]));
			}
		}
		character(input, '}');
		return set;
	}
	private String rowNaturalNumber(Scanner input) throws APException{
		StringBuffer result = new StringBuffer();
		if(nextIsNumber(input)){
			result.append(naturalNumber(input)+",");
			while(nextCharIs(input, ',')){
				character(input,',');
				result.append(naturalNumber(input)+",");
			}
		}
		return result.toString();
	}
	private String naturalNumber(Scanner input) throws APException{
		if(zero(input)){
			nextChar(input);
			return"0"; 
		}
		else if(nextIsNonZero(input)){
			return positiveNumber(input);
		}
		else throw new APException("missing number after ,");
	}
	private String positiveNumber(Scanner input) throws APException{
		StringBuffer result = new StringBuffer();
		result.append(nextChar(input));
		while (nextIsNumber(input)){
			result.append(number(input));
		}
		return result.toString();
	}
	private void print(Set<BigInteger> set ){
		if (!set.isEmpty()){
			Set<BigInteger> printable = set.copy();
			while(!printable.isEmpty()){
				out.print(printable.get().toString()+" ");
				printable.remove(printable.get());
			}
		}
		out.println();
	}
	public static void main(String[] argv) {
		new Main().start();
	}
}
