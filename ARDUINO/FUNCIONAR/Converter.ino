byte Conversao4bytes(int n, uint32_t trama_DATA_ERRO){
  byte trama_returnar[4];
  int k=3;

  trama_returnar[k]=trama_DATA_ERRO;
  
  while(k>0){
    k--;
    trama_DATA_ERRO=trama_DATA_ERRO>>8;
    trama_returnar[k]=trama_DATA_ERRO; 
  }

  return (trama_returnar[n]);  
}
