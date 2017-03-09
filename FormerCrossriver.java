package Java;
import java.util.LinkedList;
public class FormerCrossriver {
 int[] route=new int[16];
 
 int farmer(int location)
 {
  if((location & 0x08)!=0)
   return 1; 
  else return 0;
 }
 int wolf(int location)
 {
  if((location & 0x04)!=0)
   return 1; 
  else return 0;
 }
 int sheep(int location)
 {
  if((location & 0x02)!=0)
   return 1; 
  else return 0;
 }
 int cabbage(int location)
 {
  if((location & 0x01)!=0)
   return 1; 
  else return 0;
 }
 boolean isSafe(int location)
 {
  if((farmer(location)!=sheep(location))&&(sheep(location)==cabbage(location)))//如果农夫不在场且羊和白菜在一起则不安全
   return false;
  if((farmer(location)!=wolf(location))&&(wolf(location)==sheep(location)))//如果农夫不在且狼和羊在一起则不安全
   return false;
  return true;//其余情况安全
 }
 public static void main(String[] args)
 { 
  FormerCrossriver fcr=new FormerCrossriver();
  int location = 0x00;
  int newlocation;
  int route[]=new int[16];
  
  for(int i=0;i<route.length;i++)
   route[i]=-1;
  route[0]=0;//首先考虑0000状态
  LinkedList ll=new LinkedList();
  LinkedList route_list=new LinkedList();//存储route[]非零的路线图
  ll.addFirst(location);
  while(!ll.isEmpty()&&route[15]==-1)
  {
   location=(Integer)ll.removeFirst();
   for(int movers=1;movers<=8;movers<<=1)
   {
    if((0!=(location&0x08))==(0!=(location&movers)))
    {
     newlocation=location^(0x08|movers);
     if(fcr.isSafe(newlocation)&&(route[newlocation]==-1))
     {
      route[newlocation]=location;
      ll.addFirst(newlocation);
     }
    }
   }
  }
  if (route[15] !=  - 1)
       //到达最终状态
      {
          for (location = 15; location >= 0; location = route[location])
          {
           if(location!=0)
            route_list.addFirst(location);
           else
            {
             route_list.addFirst(location);
             break;
            }  
          }
      } 
      else
       System.out.println("No solution.\n");
       //问题无解
   int length=route_list.size();
  for(int i=0;i<length;i++)
  {
   int lo=(Integer)route_list.removeLast();
    System.out.print("第"+i+"步,河的这边还有： ");
         if(fcr.farmer(lo)==1)
          System.out.print("农夫 ");
         if(fcr.wolf(lo)==1)
          System.out.print("狼 ");
         if(fcr.sheep(lo)==1)
          System.out.print("羊 ");
         if(fcr.cabbage(lo)==1)
          System.out.print("白菜");
         System.out.println();
         System.out.print("河的对岸有： ");
         if(fcr.farmer(lo)==0)
          System.out.print("农夫 ");
         if(fcr.wolf(lo)==0)
          System.out.print("狼 ");
         if(fcr.sheep(lo)==0)
          System.out.print("羊 ");
         if(fcr.cabbage(lo)==0)
          System.out.print("白菜");
         System.out.println();
         System.out.println();             
  }
 }
}