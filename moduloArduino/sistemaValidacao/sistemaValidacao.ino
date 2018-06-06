#include<SD.h>

String inData = "-1";
String inData2 = "-2";
char inChar= -1;
int index = 0;
File myFile;

void setup(){
  Serial.begin(9600);
  while (!Serial){
    ;
  }
}
void loop(){

  if(index == 0){
    Serial.print("Digite o CPF 1\n");  
      // there you know there is data
        while(index == 0){
         
        inData = Serial.readString(); // Read a character]
        
        if(inData.length() == 11){
          index = 1;
        }
        else{
          index = 0;
        }
        delay(100);
      }
  
  }

  if (index == 1){
    Serial.print("Digite o CPF 2\n");
      
      while(index == 1){
        inData2 = Serial.readString(); // Read a character
        if(inData2.length() == 11){
          index = 2;
        }else{
          index = 1;
        }
        
      delay(100);
        
      }
      if(index==2){
          index = 3;
        }else { 
          index = 0;
        }   
    
  }
  if(index == 3){
    if (inData.equals(inData2) != 0){
      Serial.print("Chave: ");
      Serial.print(match());
      index = 4;
      inData = "-1";
      inData2 = "-2";
    }
    else{
      inData = "-1";
      inData2 = "-2";
      Serial.print("Falha, CPF não compativeis!\n");
      index = 0;
    }
  }


}

String match(){
  return "123456";
}


