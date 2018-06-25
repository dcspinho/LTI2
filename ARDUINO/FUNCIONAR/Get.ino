boolean getV_ADC(){  
  enviar++;
  if(trama_START.PM_recebido<=(trama_START.NS_recebido*(trama_START.PA_recebido/1000) + 38)){
    trama_ERRO.TS_enviar=trama_START.TS_recebido;
    trama_ERRO.ER_enviar=1;
    return false;
  }
  if(trama_START.NS_recebido<NS_min){
    trama_ERRO.ER_enviar=3;
    trama_ERRO.TS_enviar=trama_START.TS_recebido;
    return false;
  }
  if(trama_START.PA_recebido<PA_min){
    trama_ERRO.ER_enviar=4;
    trama_ERRO.TS_enviar=trama_START.TS_recebido;
    return false;
  }
  if(trama_START.PM_recebido<PM_min){
    trama_ERRO.ER_enviar=5;
    trama_ERRO.TS_enviar=trama_START.TS_recebido;
    return false;
  }
  if(trama_START.NS_recebido>NS_max){
    trama_ERRO.ER_enviar=6;
    trama_ERRO.TS_enviar=trama_START.TS_recebido;
    return false;
  }
  
  amostras=0;
  
  while(amostras<trama_START.NS_recebido){ 
        TS_atual = millis();
    
        V_ADC = analogRead(sensor_A);  
        
        if(amostras==0){
          TS_inicial=TS_atual;
          conta=abs(TS_atual-TS_ler); 
          trama_DATA.TS_enviar=conta+trama_START.TS_recebido + 3;
          trama_ERRO.TS_enviar=conta+trama_START.TS_recebido + 3;
        }
        
        while(micros() - TS_atual/1000 < trama_START.PA_recebido){
          lerSerial();
        }
        
        if(millis() - TS_inicial > trama_START.PM_recebido){
          return false;  
        }

        array_Amostras[amostras]=V_ADC;
      
        amostras++;
        lerSerial();
  }

  for(int s=0; s<amostras; s++){    
    trama_DATA.sequencia_enviar[s]=array_Amostras[s];
  }
  
  return true;
}
