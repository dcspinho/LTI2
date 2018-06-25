Formato do ficheiro conf:


[PORTA_COM ; BAUD-RATE ; PA ; PM ; NS ; PORTA_SOCKETS_SENSOR ; IP_GESTOR ; PORTA_SOCKETS_GESTOR ; PORTA_SOCKETS_CONCENTRADOR]


PORTA_COM: nº da COM (zero se não existir sensor real)
PA: período entre amostras em micros
PM: período da mensagem em milissegundos
NS: nº de amostras
PORTA_SOCKETS_SENSOR para comunicar com o sensor: ex: 7777	(zero se não existir sensor simulado)
IP_GESTOR: ex: 127.0.0.1
PORTA_SOCKETS_GESTOR para comunicar com o sistema Gestor: ex: 7778
PORTA_SOCKETS_CONCENTRADOR para comunicar com o sistema Gestor