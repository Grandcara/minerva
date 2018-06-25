#include <Crypto.h>
#include <Ed25519.h>
#include <string.h>
#include <SHA3.h>

struct TestVector
{
    uint8_t privateKey[32];
    uint8_t publicKey[32];
};

static TestVector const testVectorEd25519_1 PROGMEM = {
    .privateKey = {0x9d, 0x61, 0xb1, 0x9d, 0xef, 0xfd, 0x5a, 0x60,
                   0xba, 0x84, 0x4a, 0xf4, 0x92, 0xec, 0x2c, 0xc4,
                   0x44, 0x49, 0xc5, 0x69, 0x7b, 0x32, 0x69, 0x19,
                   0x70, 0x3b, 0xac, 0x03, 0x1c, 0xae, 0x7f, 0x60},
    .publicKey  = {0xd7, 0x5a, 0x98, 0x01, 0x82, 0xb1, 0x0a, 0xb7,
                   0xd5, 0x4b, 0xfe, 0xd3, 0xc9, 0x64, 0x07, 0x3a,
                   0x0e, 0xe1, 0x72, 0xf3, 0xda, 0xa6, 0x23, 0x25,
                   0xaf, 0x02, 0x1a, 0x68, 0xf7, 0x07, 0x51, 0x1a}
};
static TestVector testVector;
    size_t len = 0;
String inData = "-1";
String inData2 = "-2";
int index = 0;

void setup(){
  Serial.begin(9600);
  Serial.println("Gerando Chaves... ");
//  Ed25519::generatePrivateKey(privateKey);
//  Ed25519::derivePublicKey(publicKey, privateKey);
  delay(100);
  memcpy_P(&testVector, &testVectorEd25519_1, sizeof(TestVector));
  printNumber("chave publica",testVector.publicKey);
   printNumber("chave privada",testVector.privateKey);

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
        delay(500);
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
      index = 0;
      match(inData);
      inData = "-1";
      inData2 = "-2";
    }
    else{
      inData = "-1";
      inData2 = "-2";
      Serial.print("Erro\n");
      index = 0;
    }
  }


}

void match(String cpf){
    // Sign using the test vector.
    uint8_t signature[64];
    uint8_t message[11];
    printNumber("PrivateKey: ",testVector.privateKey);
    printNumber("PublicKey: ",testVector.publicKey);
   //   printNumber("menssage",testVector.message);
    delay(100);
    cpf.getBytes(message,11);
    delay(500);
      //memcpy_P(&testVector.message, &message, sizeof(uint8_t));
     // memcmp(testVector.message, &message, 11);
      printNumber("mensage",message);
    Serial.println("Assinando cpf");
    delay(500);
    Ed25519::sign(signature, testVector.privateKey, testVector.publicKey, message, 11);
    Serial.println("99");
    Serial.println((char*)testVector.publicKey);
    Serial.println("aa");
    Serial.println((char*)signature);
    Serial.println("bb");
    Serial.println((char*)message);
    Serial.println("cc");
    
    delay(500);
    printNumber("assinatura",signature);
    delay(500);

   }
   
 
void printNumber(const char *name,const uint8_t *x){
    static const char hexchars[] = "0123456789ABCDEF";
    Serial.print(name);
    Serial.print(" = ");
    for (uint8_t posn = 0; posn < 64; ++posn) {
        Serial.print(hexchars[(x[posn] >> 4) & 0x0F]);
        Serial.print(hexchars[x[posn] & 0x0F]);
    }
    Serial.println();
}


