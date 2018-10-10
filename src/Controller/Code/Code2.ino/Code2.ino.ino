#include <IRremote.h>
#include <IRremoteInt.h>
IRsend irsend;
volatile int sendcode = 0;
uint8_t count = 0;




void setup()
{
  //adc_disable();          // adc disable for low power consumption
  DDRB = 0b00010000;
  GIMSK = 0b00100000;    // turns on pin change interrupts
  PCMSK = 0b00000110;    // turn on interrupts on pins 6 and 7
  sei();
}
void loop() {

  if (sendcode == 1) {
 //   irsend.sendNEC(0xFF30CF, 32); // button1 cod   
      PORTB = 0b00010000;

  }

  else if (sendcode == 2) {
    // irsend.sendNEC(0x1FE40BF, 32); // button2 code
      PORTB = 0b00000000;

  }


  sendcode = 0;

}

ISR(PCINT0_vect)
{


  static uint8_t prev = 0x00;
  uint8_t current, changed;
  count++;
  current = PINB; // get input state of portB as it has now changed
  changed = current ^ prev; // use XOR to find out which bit(s) have changed
  if (changed & (1 << PB1))
  {
    //if (count >= 2) {
      sendcode = 1;

      count = 0;
    //}
  }

  if (changed & (1 << PB2)) {
    //if (count >= 2) {

      // handle change on PB5
      sendcode = 2;
      count = 0;
   // }
  }

}

