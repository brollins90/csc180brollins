package exercise2;

/**
 * This class is for the csc180 class to practice creating JavaDoc comments and a JavaDoc
 * @author Blake Rollins
 *
 */
public class Annotate
{
/**
 * var is an int that we use for ...
 */
 private int var;

 /**
  * parse is used to find an int in a String
  * @param str 	the String that represents an int
  * @return		the int that was in the String
  * @throws NumberFormatException	If there is not parsable int in the String
  */
 public int parse(String str) throws NumberFormatException
 {
     return Integer.parseInt(str);
 }

 /**
  * Returns the var stored in this object
  * @return	the value of var
  */
 public int getVar()
 {
     return var;
 }

 /**
  * Sets the value of var in this Object
  * @param var	the new value for var
  */
 public void setVar(int var)
 {
     this.var = var;
 }
}
