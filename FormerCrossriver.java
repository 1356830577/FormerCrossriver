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
  if((farmer(location)!=sheep(location))&&(sheep(location)==cabbage(location)))//���ũ���ڳ�����Ͱײ���һ���򲻰�ȫ
   return false;
  if((farmer(location)!=wolf(location))&&(wolf(location)==sheep(location)))//���ũ�������Ǻ�����һ���򲻰�ȫ
   return false;
  return true;//���������ȫ
 }
 public static void main(String[] args)
 { 
  FormerCrossriver fcr=new FormerCrossriver();
  int location = 0x00;
  int newlocation;
  int route[]=new int[16];
  
  for(int i=0;i<route.length;i++)
   route[i]=-1;
  route[0]=0;//���ȿ���0000״̬
  LinkedList ll=new LinkedList();
  LinkedList route_list=new LinkedList();//�洢route[]�����·��ͼ
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
       //��������״̬
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
       //�����޽�
   int length=route_list.size();
  for(int i=0;i<length;i++)
  {
   int lo=(Integer)route_list.removeLast();
    System.out.print("��"+i+"��,�ӵ���߻��У� ");
         if(fcr.farmer(lo)==1)
          System.out.print("ũ�� ");
         if(fcr.wolf(lo)==1)
          System.out.print("�� ");
         if(fcr.sheep(lo)==1)
          System.out.print("�� ");
         if(fcr.cabbage(lo)==1)
          System.out.print("�ײ�");
         System.out.println();
         System.out.print("�ӵĶ԰��У� ");
         if(fcr.farmer(lo)==0)
          System.out.print("ũ�� ");
         if(fcr.wolf(lo)==0)
          System.out.print("�� ");
         if(fcr.sheep(lo)==0)
          System.out.print("�� ");
         if(fcr.cabbage(lo)==0)
          System.out.print("�ײ�");
         System.out.println();
         System.out.println();             
  }
 }
}