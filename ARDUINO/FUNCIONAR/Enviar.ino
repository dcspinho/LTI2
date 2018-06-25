void Enviar_DATA(){
  byte trama_enviar[417];
  uint16_t juntarSequencia16;
  
  for(int q=0; q<417; q++){
    trama_enviar[q]=B00000000;  
  }

  trama_DATA.PA_enviar=trama_START.PA_recebido;
  trama_DATA.PM_enviar=trama_START.PM_recebido;
  trama_DATA.NS_enviar=amostras;
        
  trama_enviar[0]=trama_DATA.Tipo_enviar;

  trama_enviar[2]=trama_DATA.AD_enviar;
  trama_enviar[1]=trama_DATA.AD_enviar>>8;

  for(int n=0; n<4;n++){
     trama_enviar[n+3]=Conversao4bytes(n,trama_DATA.TS_enviar);
  }
  
  for(int n=0; n<4;n++){
     trama_enviar[n+7]=Conversao4bytes(n,trama_DATA.PM_enviar);
  }
  
  for(int n=0; n<4;n++){
     trama_enviar[n+11]=Conversao4bytes(n,trama_DATA.PA_enviar);
  }

  trama_enviar[15]=trama_DATA.NS_enviar;

  int h=0;
  for(int j=0; j<(amostras*2); j++){
      juntarSequencia16=array_Amostras[h];
      trama_enviar[j+17]=juntarSequencia16;   
      juntarSequencia16=juntarSequencia16>>8;      
      trama_enviar[j+16]=juntarSequencia16;     
      j++;
      h++;
  } 
            
  trama_enviar[416]=trama_DATA.END_enviar;


  Serial.write(trama_enviar,417);
    
}

void Enviar_ERRO(){
  byte trama_enviar[9];
  
  start=0;
  
  trama_enviar[0]=trama_ERRO.Tipo_enviar;  
  trama_enviar[1]=trama_ERRO.AD_enviar>>8;
  trama_enviar[2]=trama_ERRO.AD_enviar;  
  
  for(int n=0; n<4;n++){
     trama_enviar[n+3]=Conversao4bytes(n,trama_ERRO.TS_enviar);
  }
  
  trama_enviar[7]=trama_ERRO.ER_enviar;
  trama_enviar[8]=trama_ERRO.END_enviar;
  
  Serial.write(trama_enviar, 9);
}

