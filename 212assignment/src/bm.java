
public class bm{
  public static int pattern(String pattern, String target) {
    int tlen =target.length();//length of main string 
    int plen=pattern.length();//length of pattern string 
    if(plen>tlen )
    {
      return -1;//if the pattern string is longer than the main string ,it will return -1

    }
    int[] bad_table =bulid_bad_table (pattern);//get the bad character table 
    int[] good_table =bulid_good_table (pattern);//get the good suffix table 
    for(int i=plen,j;i<tlen;)
    {
      for(j=plen-1;target.charAt(i)==pattern.charAt(j);i--,j--)//compare the pattern string and the main string from right to the lift ,it is a looking-glass heuristic  
      {
      if(j==0)//found the match one ,it point the first one of the pattern ,can return i,it is the position 
          return i;
      
    }
    //if the bad character  appears ,compare the  the good suffix and the bad character ,use the biger one ,it is Character-Jump Heuristic
    i+=Math.max(good_table[plen-j-1],bad_table[target.charAt(i)]);
  }


    return -1;//don't found match
}
// Build the bad character table
public static int[]  bulid_bad_table (String pattern){
  final int table_size=256;
  int []bad_table =new int [table_size];//make array to store the bad character  
  int plen =pattern.length();//length
  for(int i=0;i<bad_table.length;i++)//intialize all part to the length of the pattern
   bad_table[i]=plen;
  for(int i =0;i<plen-1;i++) 
  {
    int k=pattern.charAt(i);//get the ASCII value of the character 
    
    //If a bad character appears in the last character of the pattern string,
    // which is the first character at the beginning of the match, 
    //then moving this value directly can make the rightmost character face the bad character.
    bad_table[k]=plen-1-i;//update the bad table 
  } 



  return bad_table;
}
// Build the good suffix table
public static int [] bulid_good_table (String pattern)
{
int plen=pattern.length();
int[ ]good_table=new int[plen];//make array to store the good suffix
                          //Used to record the relative position of the latest prefix, initialized to the length of the pattern string,
                          // because it means that the current suffix string is empty
int lastprefixposition=plen;//intialize to the length of the pattern
for(int i=plen-1;i>=0;--i)
{
  if(isprefix(pattern,i+1))
  {
    lastprefixposition=i+1;//update the last prefix position 
  }
  good_table[plen-1-i]=lastprefixposition-i+plen-1;//update the new good table 
}
for(int i=0;i<plen-1;++i)
{
  int slen =suffixlength(pattern,i);// prefix length of the match 
  good_table[slen]=plen-1-i+slen;
}
  return good_table;
}

//check if the prefix starting form p is the suffix 
private static boolean isprefix(String pattern,int p)
{
int patternlength=pattern.length();
for(int i=p,j=0;i<patternlength;++i,++j)
{
if(pattern.charAt(i)!=pattern.charAt(j))
{
  return false;//is not match
}
}
return true;//is match
}


//the length of match suffix of at the p ending of the pattern 
private static int suffixlength(String pattern,int p)
{
int plen=pattern.length();
int len=0;
for(int i=p,j=plen-1;i>=0&&pattern.charAt(i)==pattern.charAt(j);i--)
{
  len+=1;
}
return len;
}


public static void main(String[] args)
{
String pattern="abaa";
String target="absda abaa";


int position =pattern(pattern,target);
if(position==-1)
{
System.out.println("don't found the pattern");
}
else{
System.out.println("position is "+position);
}
}
}