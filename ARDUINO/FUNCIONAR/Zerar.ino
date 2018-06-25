void Zerar(){
  trama_DATA.Tipo_enviar=B00001010;
  trama_DATA.AD_enviar=sensor_A;                            //está com o nr do sensor mas não sei se se tem de alterar
  trama_DATA.TS_enviar=B00000000;
  trama_DATA.PM_enviar=B00000000;
  trama_DATA.PA_enviar=B00000000;
  trama_DATA.NS_enviar=B00000000;
  trama_DATA.END_enviar=B11111111;
  
  trama_ERRO.Tipo_enviar=B00001111;
  trama_ERRO.AD_enviar=sensor_A;                            //está com o nr do sensor mas não sei se se tem de alterar
  trama_ERRO.TS_enviar=B00000000;
  trama_ERRO.ER_enviar=B00000000;
  trama_ERRO.END_enviar=B11111111;

  trama_STOP.Tipo_recebido=B00000000;
  trama_STOP.SR_recebido=B00000000;          /*não vai ser utilizado o 00 por causa dos erros*/
 
  for(int j=0; j<amostras; j++){    
      array_Amostras[j]=0;
  }
  
}
