/*SENSOR TA12-100         trama DATA e trama ERROR*/

uint8_t sensor_A = 4;

float V_ADC;      /*voltagem proveniente do V_ADC*/
uint32_t TS_atual, TS_inicial, TS_ler;
unsigned int conta;
boolean BOL;

int amostras = 0;

int PA_min = 20;
int PM_min = 39;


int NS_max = 200;
int NS_min = 20;

int enviar = 0;
uint16_t array_Amostras[200];
uint32_t atencao;

/*TIPO MENSAGEM
  00 - trama START
  01 - trama DATA
  10 - trama ERROR
  11 - trama STOP
*/

struct START {                                /*inicio da recolha e envio das amostras*/
  uint8_t Tipo_recebido;                            /*8 bits = 1 byte*/
  uint32_t TS_recebido;                             /*32 bits = 4 bytes             timestamp*/
  uint32_t PM_recebido;                             /*32 bits = 4 bytes             periodo mensagem*/
  uint32_t PA_recebido;                             /*32 bits = 4 bytes             periodo amostra*/
  uint8_t NS_recebido;                              /*8 bits = 1 byte               nr amostras presentes na trama*/
};
struct START trama_START;


struct DATA {                                 /*valores das amostras*/
  uint8_t Tipo_enviar;                              /*8 bits = 1 byte*/
  uint16_t AD_enviar;                               /*16 bits = 2 bytes             identificação Arduino*/
  uint32_t TS_enviar;                               /*32 bits = 4 bytes             timestamp do momento da recolha da primeira amostra presente na mensagem*/
  uint32_t PM_enviar;                               /*32 bits = 4 bytes             periodo mensagem*/
  uint32_t PA_enviar;                               /*32 bits = 4 bytes             periodo amostra*/
  uint8_t NS_enviar;                                /*8 bits = 1 byte               nr amostras presentes na trama*/
  uint16_t sequencia_enviar[200];
  uint8_t END_enviar;
};
struct DATA trama_DATA;


struct STOP {
  uint8_t Tipo_recebido;
  uint8_t SR_recebido;                                 /* razão para a paragem*/
} ;
struct STOP trama_STOP;


struct ERRO {
  uint8_t Tipo_enviar;                              /*8 bits = 1 byte*/
  uint16_t AD_enviar;                               /*16 bits = 2 bytes             identificação Arduino*/
  uint32_t TS_enviar;                               /*32 bits = 4 bytes             timestamp*/
  uint8_t ER_enviar;                                /*8 bits = 1 byte               tipo de erro*/
  uint8_t END_enviar;
} ;
struct ERRO trama_ERRO;

/*0 é porque ainda não recebeu a trama_START
  Muda para 1 quando lê essa trama.
*/
int start = 0;

void setup() {
  Serial.begin(57600);
  pinMode(sensor_A, INPUT);

  trama_DATA.Tipo_enviar = B00001010;
  trama_DATA.AD_enviar = sensor_A;                          //está com o nr do sensor mas não sei se se tem de alterar
  trama_DATA.TS_enviar = B00000000;
  trama_DATA.PM_enviar = B00000000;
  trama_DATA.PA_enviar = B00000000;
  trama_DATA.NS_enviar = B00000000;
  trama_DATA.END_enviar = B11111111;

  trama_ERRO.Tipo_enviar = B00001111;
  trama_ERRO.AD_enviar = sensor_A;                          //está com o nr do sensor mas não sei se se tem de alterar
  trama_ERRO.TS_enviar = B00000000;
  trama_ERRO.ER_enviar = B00000000;
  trama_ERRO.END_enviar = B11111111;

  trama_STOP.Tipo_recebido = B00000000;
  trama_STOP.SR_recebido = B00000000;        /*não vai ser utilizado o 00 por causa dos erros*/
}



void loop() {

  lerSerial();

  if (getV_ADC()) {
    Enviar_DATA();
  }
  else {
    Enviar_ERRO();
  }

  Zerar();

  if (start == 1) {
    while (millis() - TS_inicial < trama_START.PM_recebido) {}
  }
}
