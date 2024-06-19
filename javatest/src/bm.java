public class bm{
    public static int pattern(String pattern, String target) {
      int tlen =target.length();
      int plen=pattern.length();
      if(plen>tlen )
      {
        return -1;

      }
      int[] bad_table =bulid_bad_table (pattern);
      int[] good_table =bulid_good_table (pattern);
      for(int i=plen,j;i<tlen;)
      {
        for(j=plen-1;target.charAt(i)==pattern.charAt(j);i--,j--)
        {
        if(j==0)
            return i;
        
      }
      i+=Math.max(good_table[plen-j-1],bad_table[target.charAt(i)]);
    }
  

      return -1;
}

public static int[]  bulid_bad_table (String pattern){
    final int table_size=256;
    int []bad_table =new int [table_size];
    int plen =pattern.length();
    for(int i=0;i<bad_table.length;i++)
     bad_table[i]=plen;
    for(int i =0;i<plen-1;i++) 
    {
      int k=pattern.charAt(i);
      bad_table[k]=plen-1-i;
    } 



    return bad_table;
}
public static int [] bulid_good_table (String pattern)
{
  int plen=pattern.length();
  int[ ]good_table=new int[plen];
  int lastprefixposition=plen;
  for(int i=plen-1;i>=0;--i)
  {
    if(isprefix(pattern,i+1))
    {
      lastprefixposition=i+1;
    }
    good_table[plen-1-i]=lastprefixposition-i+plen-1;
  }
  for(int i=0;i<plen-1;++i)
  {
    int slen =suffixlength(pattern,i);
    good_table[slen]=plen-1-i+slen;
  }
    return good_table;
}


private static boolean isprefix(String pattern,int p)
{
int patternlength=pattern.length();
for(int i=p,j=0;i<patternlength;++i,++j)
{
  if(pattern.charAt(i)!=pattern.charAt(j))
  {
    return false;
  }
}
return true;
}



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

