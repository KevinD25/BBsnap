#include "Arduino.h"
//The setup function is called once at startup of the sketch


void setup()
{
// Add your initialization code here
  DDRB  |= 0x01;
  GIMSK |= (1 << PCIE);                  // pin change interrupt enable
  PCMSK |= (1 << PCINT1);                // pin change interrupt enabled for PCINT1
  sei();                                 // enable interrupts
}

// The loop function is called in an endless loop
void loop()
{
//Add your repeated code here
}

ISR(PCINT0_vect)
{
  if (PORTB = B00000001){
    PORTB = B00000000;
  }
  else{
    PORTB = B00000001;
  }
}
