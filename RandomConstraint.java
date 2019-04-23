import java.util.Arrays;

public class RandomConstraint {
   
   final static boolean INT = true;
   final static boolean FLOAT = false;
   String name;
   boolean intType;
   int iMinRange;
   float fMinRange;
   int iMaxRange;
   float fMaxRange;
   int iExcludedValues[];
   float fExcludedValues[];
   Relation rel[];
   
   public RandomConstraint() {
      
      name = "";
      intType = true;
      iMinRange = -100000000;
      fMinRange = -100000000f;
      iMaxRange = 100000000;
      fMaxRange = 100000000f;
      iExcludedValues = new int[0];
      fExcludedValues = new float[0];
      rel = new Relation[0];
      
   }
   
   public void setName(String n) {
      
      name = n;
      
   }
   
   public void setType(boolean type) {
      
      intType = type;
      
   }
   
   public void setMinRange(float f) {
      
      if(intType == INT) {
         
         iMinRange = Math.round(f);
         
      }
      else {
         
         fMinRange = f;
         
      }
      
   }
   
   public void setMaxRange(float f) {
      
      if(intType == INT) {
         
         iMaxRange = Math.round(f);
         
      }
      else {
         
         fMaxRange = f;
         
      }
      
   }
   
   public void addExcludedValue(float f) {
      
      if(intType == INT) {
         
         iExcludedValues = Arrays.copyOf(iExcludedValues, iExcludedValues.length + 1);
         iExcludedValues[iExcludedValues.length - 1] = Math.round(f);
         
      }
      else {
         
         fExcludedValues = Arrays.copyOf(fExcludedValues, fExcludedValues.length + 1);
         fExcludedValues[fExcludedValues.length - 1] = f;
         
      }
      
   }
   
   public void addRelation(int type, float v) {
      
      rel = Arrays.copyOf(rel,  rel.length + 1);
      rel[rel.length - 1] = new Relation(type, v);
      
   }
   
   public String toString() {
      
      StringBuilder randc = new StringBuilder();
      randc.append("The name of the random number is " + name + ".\n");
      
      if(intType) {
         
         randc.append("The type is integer.\n");
         randc.append("The minimum value is " + iMinRange + ".\n");
         randc.append("The minimum value is " + iMaxRange + ".\n");
         randc.append("Excluded values include: ");
         
         for(int i = 0; i < iExcludedValues.length; i++) {
            
            if(i < iExcludedValues.length - 1) {
               
               randc.append(iExcludedValues[i] + ", ");
               
            }
            else if(iExcludedValues.length < 2) {

               randc.append("and " + iExcludedValues[i]);
               
            }
            else {
               
               randc.append(iExcludedValues[i]);
               
            }
            
         }
         
         randc.append(".\n");
         
      }
      else{
         
         randc.append("The type is decimal.\n");
         randc.append("The minimum value is " + fMinRange + ".\n");
         randc.append("The minimum value is " + fMaxRange + ".\n");
         randc.append("Excluded values include: ");
         
         for(int i = 0; i < fExcludedValues.length; i++) {
            
            if(i < fExcludedValues.length - 1) {
               
               randc.append(fExcludedValues[i] + ", ");
               
            }
            else if(fExcludedValues.length < 2) {

               randc.append("and " + fExcludedValues[i]);
               
            }
            else {
               
               randc.append(fExcludedValues[i]);
               
            }
            
         }
         
         randc.append(".\n");
      }
      
      return randc.toString();
      
   }

}