#include <IRremote.h>
#include <IRremoteInt.h>
IRsend irsend;
volatile int sendcode = 0;
uint8_t count = 0;


void setup() {
  // put your setup code here, to run once:
  DDRB |= 0x01;
  PCICR  |= 0b00000001;    // turn on port b
  PCMSK0 |= 0b00000110;
  Serial.begin(9600);
}

void loop() {
  if (sendcode == 1) {
    irsend.sendNEC(0xFF30CF, 32); // button1 code
    delay(200);
  }

  else if (sendcode == 2) {
    irsend.sendNEC(0x1FE40BF, 32); // button2 code
    delay(200);
  }

  sendcode = 0;

}

ISR(PCINT0_vect)
{

  static uint8_t prev = 0x00;
  uint8_t current, changed;
  count++;
  Serial.print(count);
  current = PINB; // get input state of portC as it has now changed
  changed = current ^ prev; // use XOR to find out which bit(s) have changed
  if (changed & (1 << PB1))
  {
    if (count >= 2) {
      Serial.write("Send enable code");
      sendcode = 1;
      count = 0;
    }
  }

  if (changed & (1 << PB2)) {
    if (count >= 2) {

      // handle change on PC5
      Serial.write("send shot code");
      sendcode = 2;
      count = 0;
    }
  }




}
