void lerSerial() {
  byte ler_java[14];

  if (start != 0) {
    if (Serial.available()) {
      Serial.readBytes(ler_java, 2);
      if (ler_java[0] == B00000011) {
        trama_STOP.Tipo_recebido = ler_java[0];
        trama_STOP.SR_recebido = 1;
        start = 0;
      }
    }
  }

  while (start == 0) {

    if (start == 0) {
      for (int u = 0; u < 14; u++) {
        ler_java[u] = B00000000;
      }

      while (!Serial.available()) {}
      Serial.readBytes(ler_java, 14);

      TS_ler = millis();

      if (ler_java[0] == B00000011) { /*STOP*/
        //Serial.println("Recebeu STOP...");
        start = 0;
      }
      else if (ler_java[0] == B00000000) { /*START*/
        //Serial.println("Recebeu START...");

        trama_START.Tipo_recebido = ler_java[0];

        trama_START.TS_recebido = (ler_java[1] << 8) | ler_java[2];
        trama_START.TS_recebido = trama_START.TS_recebido << 8;
        trama_START.TS_recebido = trama_START.TS_recebido | ler_java[3];
        trama_START.TS_recebido = trama_START.TS_recebido << 8;
        trama_START.TS_recebido = trama_START.TS_recebido | ler_java[4];

        trama_START.PM_recebido = (ler_java[5] << 8) | ler_java[6];
        trama_START.PM_recebido = trama_START.PM_recebido << 8;
        trama_START.PM_recebido = trama_START.PM_recebido | ler_java[7];
        trama_START.PM_recebido = trama_START.PM_recebido << 8;
        trama_START.PM_recebido = trama_START.PM_recebido | ler_java[8];

        trama_START.PA_recebido = (ler_java[9] << 8) | ler_java[10];
        trama_START.PA_recebido = trama_START.PA_recebido << 8;
        trama_START.PA_recebido = trama_START.PA_recebido | ler_java[11];
        trama_START.PA_recebido = trama_START.PA_recebido << 8;
        trama_START.PA_recebido = trama_START.PA_recebido | ler_java[12];

        trama_START.NS_recebido = ler_java[13];

        if (trama_START.PM_recebido == B00000000) {
          trama_START.PM_recebido = (trama_START.NS_recebido * trama_START.PA_recebido + 38) + 2000;
        }
        if (trama_START.NS_recebido == B00000000) {
          trama_START.NS_recebido = 20;
        }
        if (trama_START.PA_recebido == B00000000) {
          trama_START.PA_recebido = 200;
        }
        start = 1;
      }
      else if (ler_java[0] == B00000000 && ler_java[0] == B00000011) {  //antes era o erro 7
        trama_ERRO.ER_enviar = 2;
        trama_ERRO.TS_enviar = trama_START.TS_recebido;
        Enviar_ERRO();
      }
    }

    else if (start != 0) {
      if (Serial.available()) {
        Serial.readBytes(ler_java, 2);
        if (ler_java[0] == B00000011) {
          trama_STOP.Tipo_recebido = ler_java[0];
          trama_STOP.SR_recebido = 1;
          start = 0;
        }
      }
    }
  }
}
