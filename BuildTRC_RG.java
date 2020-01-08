/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buildtrc_rg;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 *
 * @author PengLin
 */
public class BuildTRC_RG {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
      
      new BuildTRC_RG();
    }
     public BuildTRC_RG() {
    
         try{
         // assuming all nodes are ordered such that the triplet X Y Z is ordered in a way X is always defined before Y, and
         //Z is the child node
             // BufferedReader bf2= new BufferedReader(new FileReader("promedas_ordered.uai"));// input ordered npt file
              BufferedReader bf2= new BufferedReader(new FileReader("pedigree_ordered.uai"));
              //pedigree_ordered.uai//another test file
        String s2 = null;
       
        
        Map indexmap=new HashMap();// 
        List <String> mnodes=new ArrayList();
      
        Map indexmapr=new HashMap();
     
        List<String[]> npts=new ArrayList();
     
        while((s2 = bf2.readLine())!=null){//
            if(s2.length()!=0){
            String [] pots=s2.split(" ");
         
            mnodes.add(pots[pots.length-1]);
            
            npts.add(pots);// save NPT
             
            
            }
        }
         ///////
         
            int c=1;
         
         System.out.println("original nodes size:"+mnodes.size());
         for (int t=0;t<mnodes.size();t++){
           //  System.out.println(mnodes.get(t));
             
            indexmap.put(mnodes.get(t), "x"+t);
            indexmapr.put("x"+t, mnodes.get(t));// x0 to x_(size-1) as the original node
         
           // System.out.println("x"+t+" "+mnodes.get(t));
         
         }
         
         //build the primary triplets and interaction tripelts by BFG mapping
         List<String []> primarytriplets=new ArrayList();
         List<String []> interactiontriplets=new ArrayList();
         
         System.out.println("npts size:"+npts.size());
         
         
         
         int Enodesize=mnodes.size();// the number of E nodes needed
         
         
         for (int t=0;t<npts.size();t++){ // the ordered NPTs
         
         
            if (t==0 || t==1){} // x0 and x1 will be merged to {x0 x1 x2}
            
            else{
            
                String [] npt=npts.get(t); // build from t==2
                
                if (npt[0].equals("1")) {
                
                  //  System.out.println(npt[0]+" "+npt[1]);
                    
                    if (t==2){ String [] str=new String [3]; str[0]="x0";str[1]="x1";str[2]="x2"; // no moral// {x0 x1 x2}
                    if (!areallexisted(primarytriplets,str))
                        primarytriplets.add(str);
                    }else{
                    
                        String [] str=new String [3]; str[0]="E"+Enodesize;   str[2]=indexmap.get(npt[1]).toString(); //moral {str[0] str[1]}
                        String [] temp=str[2].split("x");
                       // System.out.println("test "+temp[1]);
                        str[1]="x"+(Integer.valueOf(temp[1])-1);
                        
                        Enodesize++;//increase the size
                        if (!areallexisted(primarytriplets,str))
                            primarytriplets.add(str);
                       // System.out.println(str[0]+" "+str[1]+" "+str[2]);
                       
                        String [] str1=new String [3]; str1[0]="x"+(Integer.valueOf(temp[1])-2); str1[1]=str[0]; str1[2]=str[1];
                        if (!areallexisted(interactiontriplets,str1))
                            interactiontriplets.add(str1);
                        //System.out.println(str1[0]+" "+str1[1]+" "+str1[2]);
                  
                    
                    }
                
                }
                  
                if (npt[0].equals("2")){
                
                   // System.out.println(npt[0]+" "+npt[1]+" "+npt[2]);
                 if (t==2){ String [] str=new String [3]; str[0]="x0";str[1]="x1";str[2]="x2"; // no moral// {x0 x1 x2}
                    if (!areallexisted(primarytriplets,str))
                        primarytriplets.add(str);
                    
                    
                    }else{
                 
                 
                     int distance=Integer.valueOf(indexmap.get(npt[2]).toString().substring(1, indexmap.get(npt[2]).toString().length()))-
                             Integer.valueOf(indexmap.get(npt[1]).toString().substring(1,indexmap.get(npt[1]).toString().length()));// calculate the distance of the two nodes
                     
                    // System.out.println("distance="+distance);
                     
                     if (distance==1){// just add one primary and one interaction triplet
                     
                         String [] str= new String [3]; str[0]="E"+Enodesize; str[1]=indexmap.get(npt[1]).toString(); str[2]=indexmap.get(npt[2]).toString();
                        Enodesize++;//increase the size
                        if (!areallexisted(primarytriplets,str))
                            primarytriplets.add(str);
                       // System.out.println(str[0]+" "+str[1]+" "+str[2]);
                        String []str1=new String [3]; str1[0]="x"+(Integer.valueOf(indexmap.get(npt[2]).toString().substring(1, indexmap.get(npt[2]).toString().length()))-2);
                        str1[1]=str[0];
                        str1[2]=str[1];
                        if (!areallexisted(interactiontriplets,str1))
                            interactiontriplets.add(str1);
                        //System.out.println(str1[0]+" "+str1[1]+" "+str1[2]);
                       // System.out.println("adding...");
                     }else{// distance >=2
                     
                   //  System.out.println("distance:"+distance);
                     
                           
                     if (indexmap.get(npt[1]).toString().equals("x0")  ){
                     
                         // distance=distance-1;
                          int copies=distance-2;
                          
                          String [] str=new String [3]; str[0]=indexmap.get(npt[1]).toString();//one primary
                          str[2]=indexmap.get(npt[2]).toString();
                          str[1]="x"+(Integer.valueOf(str[2].substring(1, str[2].length()))-1);
                          
                          if (!areallexisted(primarytriplets,str))
                              primarytriplets.add(str);
                          
                          for (int p=0;p<copies;p++){ // number of copied nodes
                          
                          
                              String str1[]=new String [3];
                              
                              str1[1]=str[0];
                              str1[2]="x"+(Integer.valueOf(str[1].substring(1, str[1].length()))-p);
                              str1[0]="x"+(Integer.valueOf(str1[2].substring(1, str1[2].length()))-1);
                          if (str1[0].equals("x1")) str1[0]="x0";
                          if (!areallexisted(interactiontriplets,str1))
                              interactiontriplets.add(str1);
                          
                          }
                          
                          
                          
                          
                     
                     }else{
                     
                     
                         int copies=distance-1;
                         
                         
                     String [] str=new String [3]; str[0]=indexmap.get(npt[1]).toString();//one primary
                          str[2]=indexmap.get(npt[2]).toString();
                          str[1]="x"+(Integer.valueOf(str[2].substring(1, str[2].length()))-1);
                     if (!areallexisted(primarytriplets,str))
                         primarytriplets.add(str);
                     
                     //System.out.println("test:"+str[0]+" "+str[1]+" "+str[2]);
                         for (int p=0;p<copies;p++){ // number of copied nodes
                          
                          String str1[]=new String [3];
                              
                              str1[1]=str[0];
                              str1[2]="x"+(Integer.valueOf(str[1].substring(1, str[1].length()))-p);
                              str1[0]="x"+(Integer.valueOf(str1[2].substring(1, str1[2].length()))-1);
                          //if (str1[0].equals("x1")) str1[0]="x0";
                          if (!areallexisted(interactiontriplets,str1))
                              interactiontriplets.add(str1);
                          
                          
                          
                       
                          
                          }
                     
                     }
                     
                     
                     
                     }
                     
                 
                 
                 
                 }
                    
                
                }
            
                if (npt[0].equals("3")){
                
                
                  //  System.out.println(npt[0]+" "+npt[1]+" "+npt[2]+" "+npt[3]);
                
                    if (t==2){ String [] str=new String [3]; str[0]="x0";str[1]="x1";str[2]="x2"; // no moral// {x0 x1 x2}
                    if (!areallexisted(primarytriplets,str))
                        primarytriplets.add(str);
                    
                    
                    }else{
                    
                    
                    
                        int distance12=Integer.valueOf(indexmap.get(npt[3]).toString().substring(1, indexmap.get(npt[3]).toString().length()))-
                             Integer.valueOf(indexmap.get(npt[2]).toString().substring(1,indexmap.get(npt[2]).toString().length()));// calculate the distance of the two nodes
                     
                        
                        int distance02=Integer.valueOf(indexmap.get(npt[3]).toString().substring(1, indexmap.get(npt[3]).toString().length()))-
                             Integer.valueOf(indexmap.get(npt[1]).toString().substring(1,indexmap.get(npt[1]).toString().length()));// calculate the distance of the two nodes
                     
                        
                    if (distance12==1){ // no sink // equivalent to two nodes case
                    
                         
                        if (indexmap.get(npt[1]).toString().equals("x0")  ){
                     
                         // distance=distance-1;
                          int copies=distance02-2;
                          
                          String [] str=new String [3]; str[0]=indexmap.get(npt[1]).toString();//one primary
                          str[2]=indexmap.get(npt[3]).toString();
                          str[1]="x"+(Integer.valueOf(str[2].substring(1, str[2].length()))-1);
                          
                          if (!areallexisted(primarytriplets,str))
                              primarytriplets.add(str);
                          
                          for (int p=0;p<copies;p++){ // number of copied nodes
                          
                          
                              String str1[]=new String [3];
                              
                              str1[1]=str[0];
                              str1[2]="x"+(Integer.valueOf(str[1].substring(1, str[1].length()))-p);
                              str1[0]="x"+(Integer.valueOf(str1[2].substring(1, str1[2].length()))-1);
                          if (str1[0].equals("x1")) str1[0]="x0";
                          if (!areallexisted(interactiontriplets,str1))
                              interactiontriplets.add(str1);
                          
                          }
                          
                         
                          
                          
                     
                     }else{
                     
                     
                         int copies=distance02-1;
                         
                         
                          String [] str=new String [3]; str[0]=indexmap.get(npt[1]).toString();//one primary
                          str[2]=indexmap.get(npt[3]).toString();
                          str[1]="x"+(Integer.valueOf(str[2].substring(1, str[2].length()))-1);
                   
                          if (!areallexisted(primarytriplets,str))
                              primarytriplets.add(str);
                     
                     //System.out.println("test:"+str[0]+" "+str[1]+" "+str[2]);
                         for (int p=0;p<copies;p++){ // number of copied nodes
                          
                              String str1[]=new String [3];
                              
                              str1[1]=str[0];
                              str1[2]="x"+(Integer.valueOf(str[1].substring(1, str[1].length()))-p);
                              str1[0]="x"+(Integer.valueOf(str1[2].substring(1, str1[2].length()))-1);
                          //if (str1[0].equals("x1")) str1[0]="x0";
                          if (!areallexisted(interactiontriplets,str1))
                              interactiontriplets.add(str1);
                           
                          
                          }
                     
                        
                     }
                        
                    
                    
                    }else{// sink
                    
                    
                        
                        
                        int sink=distance12-1;// the number of copies of npt[3]
                        
                        
                        if (indexmap.get(npt[1]).toString().equals("x0")  ){
                        
                       // System.out.println("test run x0");
                            int copies=distance02-2-sink;
                            
                          String [] str=new String [3]; 
                          str[0]=indexmap.get(npt[1]).toString();//one primary
                          //str[2]=indexmap.get(npt[3]).toString();
                          str[1]=indexmap.get(npt[2]).toString();
                          str[2]="E"+Enodesize;// copy of npt[3]
                          Enodesize++;
                          
                          if (!areallexisted(primarytriplets,str))
                              primarytriplets.add(str);  
                            
                           for (int p=0;p<copies;p++){
                           
                           
                           String str1[]=new String [3];
                           
                              str1[1]=str[0];//npt[1]
                              str1[2]="x"+(Integer.valueOf(str[1].substring(1, str[1].length()))-p);// from npt[2] node to low dimensions
                              str1[0]="x"+(Integer.valueOf(str1[2].substring(1, str1[2].length()))-1);
                          if (str1[0].equals("x1")) str1[0]="x0";
                          if (!areallexisted(interactiontriplets,str1))
                              interactiontriplets.add(str1);
                           
                           
                           } 
                            
                          //add sink triplets// interaction triplets
                           
                         
                            String str3[]=new String [3];
                          str3[0]=str[2];
                                  
                          str3[2]= indexmap.get(npt[3]).toString(); 
                          str3 [1]=  "x"+(Integer.valueOf(str3[2].substring(1, str3[2].length()))-1); 
                           
                           if (!areallexisted(primarytriplets,str3))
                          { primarytriplets.add(str3); 
                         // System.out.println("primary:"+str[0]+" "+str[1]+" "+str[2]);
                          }
                           
                           for (int p=0;p<sink;p++){
                          
                         
                           
                           String str2[]=new String[3];
                           str2[0]="x"+(Integer.valueOf(str[1].substring(1,str[1].length()))+p);//npt[2]
                           str2[1]=str[2];//npt[3]
                           str2[2]="x"+(Integer.valueOf(str2[0].substring(1,str2[0].length()))+1);
                           
                           if (!areallexisted(interactiontriplets,str2))
                               interactiontriplets.add(str2);
                           }
                           
                           
                           
                           
                        }else{
                        
                       // System.out.println("test sink:"+npt[1]+" "+npt[2]+" "+npt[3]);
                            int copies =distance02-1-sink;
                        
                          String [] str=new String [3]; 
                         // str[0]="E"+Enodesize;//one primary//copy of npt[1]
                          str[0]=indexmap.get(npt[1]).toString();//npt[1]
                        
                          str[1]=indexmap.get(npt[2]).toString();
                          str[2]="E"+Enodesize;// copy of npt[3]
                          Enodesize++;
                        //  str[2]=indexmap.get(npt[3]).toString();
                          
                            
                          if (!areallexisted(primarytriplets,str))
                          { primarytriplets.add(str); 
                         // System.out.println("primary:"+str[0]+" "+str[1]+" "+str[2]);
                          }
                          
                        for (int p=0;p<copies;p++){
                           
                           
                           String str1[]=new String [3];
                           
                              str1[1]=str[0];//npt[1]
                              str1[2]="x"+(Integer.valueOf(str[1].substring(1, str[1].length()))-p);// from npt[2] node to low dimensions
                              str1[0]="x"+(Integer.valueOf(str1[2].substring(1, str1[2].length()))-1);
                         // if (str1[0].equals("x1")) str1[0]="x0";
                          if (!areallexisted(interactiontriplets,str1))
                          { interactiontriplets.add(str1);
                          
                           // System.out.println("inter:"+str1[0]+" "+str1[1]+" "+str1[2]);
                          }
                           
                           
                           } 
                          
                          
                          // add sink triplets
                         String str3[]=new String [3];
                          str3[0]=str[2];
                                  
                          str3[2]= indexmap.get(npt[3]).toString(); 
                          str3 [1]=  "x"+(Integer.valueOf(str3[2].substring(1, str3[2].length()))-1); 
                           
                           if (!areallexisted(primarytriplets,str3))
                          { primarytriplets.add(str3); 
                         // System.out.println("primary:"+str[0]+" "+str[1]+" "+str[2]);
                          }
                         for (int p=0;p<sink;p++){
                           
                         
                          
                           String str2[]=new String[3];
                           str2[0]="x"+(Integer.valueOf(str[1].substring(1,str[1].length()))+p);//npt[2]
                           str2[1]=str[2];//copy of npt[3]
                           str2[2]="x"+(Integer.valueOf(str2[0].substring(1,str2[0].length()))+1);
                           
                          if (!areallexisted(interactiontriplets,str2))
                          { interactiontriplets.add(str2);
                            //System.out.println("sink:"+str2[0]+" "+str2[1]+" "+str2[2]);
                          
                          }
                           }
                        
                        }
                        
                       
                    
                  
                    
                    
                    
                    }
                    
                    
                    
                    }
                    
                
                }
            }
         
         
         
         
         
         
         
         }
         
         System.out.println("primary triplets:"+primarytriplets.size());
         System.out.println("interaction triplets:"+interactiontriplets.size());
         System.out.println("all outer regions:"+(primarytriplets.size()+interactiontriplets.size()));
         
       
         
     
     
           
           List<String> allnodes=new ArrayList();
    // int netnnode=0;
      
      for (int k=0;k<primarytriplets.size();k++){ // all primary triplets npts
      String [] str=primarytriplets.get(k);
     // System.out.println(str[0]+" "+str[1]+" "+str[2]);
     // System.out.println();
      
          String str1=str[0];
          String str2=str[1];
          String str3=str[2];
          
          if (!isexist(str1,allnodes)){ allnodes.add(str1); //if (!str1.contains("m")) netnnode++;
          }
          if (!isexist(str2,allnodes)) { allnodes.add(str2);//if (!str2.contains("m")) netnnode++;
          }
          if (!isexist(str3,allnodes)) {allnodes.add(str3);//if (!str3.contains("m")) netnnode++;
          }
          
      }
           System.out.println("allnodes:"+allnodes.size());
          System.out.println();
          System.out.println("MARKOV");
          System.out.println(allnodes.size());
           for (int i=0;i<allnodes.size();i++){
           
           System.out.print("2 ");
       
           }
           
           System.out.println();
           System.out.println();
           System.out.println((primarytriplets.size()+interactiontriplets.size()));
           System.out.println();
           System.out.println();
           System.out.println();
           System.out.println();
           for (int i=0;i<mnodes.size();i++){
           
           //System.out.println(mnodes.get(i));
           
           }
           
         
           
           
           
           Map namemap=new HashMap();
     
      
     
      for (int k=0;k<allnodes.size();k++){   
            
          String str=allnodes.get(k);
          if (str.contains("E"))
          
            namemap.put(str, str.substring(1, str.length()));
            //allnodesordered.add(k);
          
          else namemap.put(str, indexmapr.get(str));
      }
      
      
      
           for (int u=0;u<primarytriplets.size();u++){
      
      String [] triplet=primarytriplets.get(u);
     System.out.println("3 "+namemap.get(triplet[0])+" "+namemap.get(triplet[1])+" "+namemap.get(triplet[2])+" ");
    //System.out.println("8 1 1 1 1 1 1 1 1");
    
      }
           for (int u=0;u<interactiontriplets.size();u++)
           {
      
      String [] triplet=interactiontriplets.get(u);
    System.out.println("3 "+namemap.get(triplet[0])+" "+namemap.get(triplet[1])+" "+namemap.get(triplet[2])+" ");
    //System.out.println("8 1 1 1 1 1 1 1 1"); 
  
       }
           ////
           
           System.out.println();
           System.out.println();
           for (int u=0;u<(primarytriplets.size()+interactiontriplets.size());u++){
           
           System.out.println("8 1 1 1 1 1 1 1 1"); 
           
           
           }
           
        
                   
        
     
           
           
           
         }catch(Exception e){}
    
    
    }
    
    
    
     private boolean isexist(String str, List lst){
  
       boolean flag=false;
       
       for (int i=0;i<lst.size();i++){
       
            
           String str3=lst.get(i).toString();
       
           if (str3.equals(str)) {
           
           flag=true;
           break;
           
           }
       
       }
  
  
  return flag;
  }
     private boolean areallexisted(List<String[]> list, String [] npt){
     
         boolean flag=false;
         
        for (int j=0;j<list.size();j++){
         
              String [] npt0=list.get(j);
         
      
         
         
                  if (npt[0].equals(npt0[0]) || npt[0].equals(npt0[1]) || npt[0].equals(npt0[2])){
                  
                       if (npt[1].equals(npt0[0]) || npt[1].equals(npt0[1]) || npt[1].equals(npt0[2])){
                       
                       
                          if (npt[2].equals(npt0[0]) || npt[2].equals(npt0[1]) || npt[2].equals(npt0[2])){
                          
                            
                            return true; 
                          }
                       
                       }
                  
                  
                  }
         
         
         
       
     
     
        }
     
        return flag;
        
     }
}
